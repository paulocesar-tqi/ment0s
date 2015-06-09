/**
 * Author: hollyschinsky
 * twitter: @devgirfl
 * blog: devgirl.org
 * more tutorials: hollyschinsky.github.io
 */
//var URL_ENDPOINTS = 'http://paulocesar.tk/promobugs';
var URL_ENDPOINTS = 'http://192.168.0.102:8080';
//var URL_ENDPOINTS = 'http://localhost:8080';
var admobid = {};
var clickedUrl = "";

app.controller('PostsCtrl', function($scope, $ionicSideMenuDelegate, $ionicScrollDelegate, $ionicModal, $timeout, $sce, $ionicLoading, PostService, ConfigService, $cordovaPush, $cordovaDialogs, $cordovaSocialSharing, $cordovaMedia, $cordovaToast, ionPlatform, localstorage, $http) {
    $scope.posts = [];
    $scope.page = 0;    
    $scope.infiniteLoad = false;
    $scope.formData = {};

    // call to register automatically upon device ready
    ionPlatform.ready.then(function (device) {
        //localstorage.removeItem("preferences");
        //localstorage.removeItem("regId");
        //localstorage.set("regId","APA91bGvMOX6nX_m7PQwiXlyi--oZnePhqDt0lggkcyxA1a8JYZiNT2pCysJ5LtqhMvDVCnbp0H7PLVNr4xSsohebo2b0drUv8o_n7ULUDH01_-lHMNSJSYUEhHPUhh7jN4bZpa2hxXw");
        if(!localstorage.get("regId")) {
            $scope.register();
        } else {
            //$cordovaToast.showShortCenter("Found regId: " + localstorage.get("regId"));
            console.log("Found regId: " + localstorage.get("regId"));
        }

        $scope.checkConfig();
        $scope.setAdMob();
        $scope.loadPosts();

     });

    $scope.scrollTop = function () {
        $ionicScrollDelegate.scrollTop(true);
    }

    $scope.getScrollPosition = function() {
        //monitor the scroll
        $scope.moveData = $ionicScrollDelegate.getScrollPosition().top;

        if($scope.moveData>=250){
            $('.scrollToTop').fadeIn();
        }else if($scope.moveData<250){
            $('.scrollToTop').fadeOut();
        }

    };
    $scope.toggleAbout = function () {
        $ionicSideMenuDelegate.toggleRight();
    };

    $scope.checkConfig = function () {
        if(!localstorage.get("preferences")) {
            if(!localstorage.get("regId")) {
                $timeout(function(){
                    $scope.checkConfig();
                }, 1000);
                return;
            } else {
                ConfigService.getUserConfig(localstorage.get("regId"))
                .success(function(result) {
                    result = decryptText(result);
                    console.log("Preferences retorned: " + result);
                    localstorage.set("preferences", result);
                    $scope.formData = JSON.parse(result);
                    $scope.configToggles();
                })
                .error(function (data, status) {
                    $cordovaToast.showShortCenter("Erro ao recuperar suas configurações");
                    console.log("Error loading config." + data + " " + status);
                });               
            }
        } else {
            $scope.formData = JSON.parse(localstorage.get("preferences"));
            $scope.configToggles();
        }
    }

     $scope.configToggles = function () {
        console.log("Preferences:" + localstorage.get("preferences"));
        if($scope.formData.activeNotifications == 1)
            $scope.toggleNotifications = { checked: true };
        else
            $scope.toggleNotifications = { checked: false };
        if($scope.formData.activeVibration == 1)
            $scope.toggleVibrations = { checked: true };
        else
            $scope.toggleVibrations = { checked: false };
        if($scope.formData.activeFilter == 1)
            $scope.toggleFilter = { checked: true };
        else
            $scope.toggleFilter = { checked: false };

        $scope.$watch('toggleNotifications.checked', function(newValue, oldValue) {
            if(newValue)
                $scope.formData.activeNotifications = 1;
            else
                $scope.formData.activeNotifications = 0;
        });
        $scope.$watch('toggleVibrations.checked', function(newValue, oldValue) {
            if(newValue)
                $scope.formData.activeVibration = 1;
            else
                $scope.formData.activeVibration = 0;
        });
        $scope.$watch('toggleFilter.checked', function(newValue, oldValue) {
            if(newValue)
                $scope.formData.activeFilter = 1;
            else
                $scope.formData.activeFilter = 0;
        });
     }

    $scope.setAdMob = function () {
        if(window.AdMob) {

            if( /(android)/i.test(navigator.userAgent) ) { // for android
                console.log("Android detectado");
                admobid = {
                    banner: 'ca-app-pub-2794315939519770/8766347441', // or DFP format "/6253334/dfp_example_ad"
                    interstitial: 'ca-app-pub-2794315939519770/6392487043'
                };
            } else if(/(ipod|iphone|ipad)/i.test(navigator.userAgent)) { // for ios
                console.log("IOS detectado");
                admobid = {
                    banner: 'ca-app-pub-2794315939519770/4196547047', // or DFP format "/6253334/dfp_example_ad"
                    interstitial: 'ca-app-pub-2794315939519770/4636552243'
                };
            } else { // for windows phone
                console.log("Windows detectado");
                admobid = {
                    banner: 'ca-app-pub-2794315939519770/1543485049', // or DFP format "/6253334/dfp_example_ad"
                    interstitial: 'ca-app-pub-2794315939519770/3020218247'
                };
            }

            window.AdMob.createBanner({
                adId:admobid.banner,
                adSize:'SMART_BANNER', 
                overlap:true, 
                position:AdMob.AD_POSITION.BOTTOM_CENTER, 
                autoShow:true});

            window.AdMob.prepareInterstitial( {adId:admobid.interstitial, autoShow:false} );

            document.addEventListener('onAdDismiss',function(data){
                    if(data.adType == 'interstitial') {
                        window.AdMob.prepareInterstitial( {adId:admobid.interstitial, autoShow:false} );
                        window.open(clickedUrl, '_system', 'location=yes');
                    }
            });

        }
    }

    // function to open the modal urlViewer
    $scope.openUrlViewer = function (url) {
        clickedUrl = url;
        if(window.AdMob) {
            window.AdMob.showInterstitial();
        } else {
            window.open(clickedUrl, '_system', 'location=yes');
        }
    };

    $scope.doShare = function (id, url) {
        $cordovaSocialSharing.share($("#post"+id).text().trim(), 'Alguém enviou um promobug pra você', null, 'http://www.mylink.com');
    };

    $scope.toggleMenu = function () {
        $ionicSideMenuDelegate.toggleLeft();
    };

    $scope.clearWord1 = function () {
        $scope.formData.word1 = "";
    };
    $scope.clearWord2 = function () {
        $scope.formData.word2 = "";
    };
    $scope.clearWord3 = function () {
        $scope.formData.word3 = "";
    };

    $scope.saveConfig = function () {
        $ionicLoading.show({ template: '<p class="item-icon-left">Salvando Configurações<ion-spinner icon="lines"/></p>'});
        ConfigService.saveUserConfig(encryptText(JSON.stringify($scope.formData)))
            .success(function(result) {
                    localstorage.set("preferences", JSON.stringify($scope.formData));
                    $ionicLoading.show({ template: '<p class="item-icon-left">Configurações salvas<i class="icon ion-checkmark-circled"></i></p>'});
                    $timeout(function(){
                       $ionicLoading.hide();
                       $ionicSideMenuDelegate.toggleLeft();
                    }, 1000);
            })
            .error(function (data, status) {
                $ionicLoading.show({ template: '<p class="item-icon-left">Verifique sua conexão<ion-spinner icon="lines"/></p>'});
                $timeout(function(){
                    $scope.saveConfig();
                }, 5000);

                console.log("Error saving config." + data + " " + status);
            });
    };

    // Register
    $scope.register = function () {
        var config = null;

        if (ionic.Platform.isAndroid()) {
            config = {
                "senderID": "931707676497", // REPLACE THIS WITH YOURS FROM GCM CONSOLE - also in the project URL like: https://console.developers.google.com/project/434205989073
                "ecb": "registerNotificationAndroid"
            };
        }
        else if (ionic.Platform.isIOS()) {
            config = {
                "badge": "true",
                "sound": "true",
                "alert": "true",
                "ecb": "handleIOS"
            }
        }

        if(config) {
            $cordovaPush.register(config).then(function (result) {
                console.log("Register success " + result);

                $cordovaToast.showShortCenter('Registered for push notifications');
                // ** NOTE: Android regid result comes back in the pushNotificationReceived, only iOS returned here
                if (ionic.Platform.isIOS()) {
                    $scope.regId = result;
                    storeDeviceToken("ios");
                }
            }, function (err) {
                console.log("Register error " + err);
            });
        }
    }

    // Notification Received
    $scope.$on('pushNotificationReceived', function (event, notification) {
        console.log(JSON.stringify([notification]));
        if (ionic.Platform.isAndroid()) {
            handleAndroid(notification);
        }
        else if (ionic.Platform.isIOS()) {
            handleIOS(notification);
            $scope.$apply(function () {
                $scope.notifications.push(JSON.stringify(notification.alert));
            })
        }
    });

    $scope.loadPosts = function() {
        $scope.infiniteLoad = false;    
        $scope.page = 0;
        $scope.posts = [];
        $scope.$broadcast("scroll.refreshComplete");
        $scope.infiniteLoad = true; 
        $scope.morePosts();
    }
    $scope.morePosts = function() {  
        $scope.infiniteLoad = false;
        $ionicLoading.show({ template: '<p class="item-icon-left">Carregando...<ion-spinner icon="lines"/></p>'});
        $timeout(function () {
            PostService.loadPosts($scope.page)
                .success(function(result) {
                    result = JSON.parse(decryptText(result));
                    if(result.length) {
                        $scope.posts = $scope.posts.concat(result);
                        $scope.page++;
                        $scope.$broadcast("scroll.infiniteScrollComplete");
                        $scope.infiniteLoad = true; 
                        $ionicLoading.hide();
                    } else {
                        if($scope.posts.length) {
                            $scope.infiniteLoad = false;
                            $scope.$broadcast("scroll.infiniteScrollComplete");
                            $ionicLoading.hide();
                        } else {
                            $ionicLoading.show({ template: '<p class="item-icon-left">Ainda não há promoções<ion-spinner icon="lines"/></p>'});
                            $timeout(function(){
                                $scope.morePosts();
                            }, 10000);
                        }
                    }
                })
                .error(function (data, status) {
                    $ionicLoading.show({ template: '<p class="item-icon-left">Verifique sua conexão<ion-spinner icon="lines"/></p>'});
                    $timeout(function(){
                        $scope.morePosts();
                    }, 5000);

                    console.log("Error loading posts." + data + " " + status)
                });
        }, 1000);
    }
    $scope.toTrusted = function(text) {
        return ($sce.trustAsHtml(text));
    }
    
    function decryptText(text) {
        var aesUtil = new AesUtil(128, 10);
        var decrypt = aesUtil.decrypt("3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55", "F27D5C9927726BCEFE7510B1BDD3D137", "i wanna be sedated", text);
        return decrypt;
    }

    function encryptText(text) {
        var aesUtil = new AesUtil(128, 10);
        var encrypt = aesUtil.encrypt("3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55", "F27D5C9927726BCEFE7510B1BDD3D137", "i wanna be sedated", text);
        return encrypt;
    }

    // Android Notification Received Handler
    function handleAndroid(notification) {
        // ** NOTE: ** You could add code for when app is in foreground or not, or coming from coldstart here too
        //             via the console fields as shown.
        console.log("In foreground " + notification.foreground  + " Coldstart " + notification.coldstart);
        if (notification.event == "message") {
            $cordovaDialogs.alert(notification.message, "Push Notification Received");
            $scope.$apply(function () {
                $scope.notifications.push(JSON.stringify(notification.message));
            })
        }
        else if (notification.event == "error")
            $cordovaDialogs.alert(notification.msg, "Push notification error event");
        else $cordovaDialogs.alert(notification.event, "Push notification handler - Unprocessed Event");
    }

    // IOS Notification Received Handler
    function handleIOS(notification) {
        // The app was already open but we'll still show the alert and sound the tone received this way. If you didn't check
        // for foreground here it would make a sound twice, once when received in background and upon opening it from clicking
        // the notification when this code runs (weird).
        if (notification.foreground == "1") {
            // Play custom audio if a sound specified.
            if (notification.sound) {
                var mediaSrc = $cordovaMedia.newMedia(notification.sound);
                mediaSrc.promise.then($cordovaMedia.play(mediaSrc.media));
            }

            if (notification.body && notification.messageFrom) {
                $cordovaDialogs.alert(notification.body, notification.messageFrom);
            }
            else $cordovaDialogs.alert(notification.alert, "Push Notification Received");

            if (notification.badge) {
                $cordovaPush.setBadgeNumber(notification.badge).then(function (result) {
                    console.log("Set badge success " + result)
                }, function (err) {
                    console.log("Set badge error " + err)
                });
            }
        }
        // Otherwise it was received in the background and reopened from the push notification. Badge is automatically cleared
        // in this case. You probably wouldn't be displaying anything at this point, this is here to show that you can process
        // the data in this situation.
        else {
            if (notification.body && notification.messageFrom) {
                $cordovaDialogs.alert(notification.body, "(RECEIVED WHEN APP IN BACKGROUND) " + notification.messageFrom);
            }
            else $cordovaDialogs.alert(notification.alert, "(RECEIVED WHEN APP IN BACKGROUND) Push Notification Received");
        }
    }

    // Removes the device token from the db via node-pushserver API unsubscribe (running locally in this case).
    // If you registered the same device with different userids, *ALL* will be removed. (It's recommended to register each
    // time the app opens which this currently does. However in many cases you will always receive the same device token as
    // previously so multiple userids will be created with the same token unless you add code to check).
    function removeDeviceToken() {
        var tkn = {"token": $scope.regId};
        $http.post(URL_ENDPOINTS + '/unsubscribe', JSON.stringify(tkn))
            .success(function (data, status) {
                console.log("Token removed, device is successfully unsubscribed and will not receive push notifications.");
            })
            .error(function (data, status) {
                console.log("Error removing device token." + data + " " + status)
            }
        );
    }

});


// Android Notification Received Handler
function registerNotificationAndroid(e) {
    switch( e.event )
    {
    case 'registered':
         $.get(URL_ENDPOINTS + "/register?platform=android&regId=" + e.regid, function(){
                console.log("Id registrado com sucesso: " + e.regid);
                window.localStorage['regId'] =  e.regid;
            });


    break;
    }
}

function openUrlViewer(url) {
    clickedUrl = url;
    if(window.AdMob) {
        window.AdMob.showInterstitial();
    } else {
        window.open(clickedUrl, '_system', 'location=yes');
    }
};

app.service('PostService', function($http) {
    this.loadPosts = function(page) {
        var params = { pageNumber: page };
        return ($http.get(URL_ENDPOINTS + "/posts", {
            params: params
        }));
    }

    this.loadPost = function(id) {
        var params = { id: id };
        return ($http.get(URL_ENDPOINTS + "/post", {
            params: params
        }));
    }
})

app.service('ConfigService', function($http) {
    this.getUserConfig = function(id) {
        var params = { id: id };
        return ($http.get(URL_ENDPOINTS + "/getuserconfig", {
            params: params
        }));
    }

    this.saveUserConfig = function(payload) {
        var params = { payload: payload };
        return ($http.get(URL_ENDPOINTS + "/saveuserconfig", {
            params: params
        }));
    }
})
