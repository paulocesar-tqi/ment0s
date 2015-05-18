/**
 * Author: hollyschinsky
 * twitter: @devgirfl
 * blog: devgirl.org
 * more tutorials: hollyschinsky.github.io
 */
 var URL_ENDPOINTS = 'http://192.168.0.103:8080';
 //var URL_ENDPOINTS = 'http://localhost:8080';

app.controller('PostsCtrl', function($scope, $ionicModal, $timeout, $sce, $ionicLoading, PostService, $cordovaPush, $cordovaDialogs, $cordovaMedia, $cordovaToast, ionPlatform, localstorage, $http) {
    $scope.posts = [];
    $scope.page = 0;    
    $scope.infiniteLoad = false;

    //init the modal postDetail
    $ionicModal.fromTemplateUrl('templates/post-detail.html', {
      scope: $scope,
      animation: 'slide-in-up'
    }).then(function (modal) {
      $scope.postDetail = modal;
    });

    //init the modal url-viewer
    $ionicModal.fromTemplateUrl('templates/url-viewer.html', {
      scope: $scope,
      animation: 'slide-in-up'
    }).then(function (modal) {
      $scope.urlViewer = modal;
    });

    // call to register automatically upon device ready
    ionPlatform.ready.then(function (device) {
        localstorage.removeItem("regId");
        if(!localstorage.get("regId")) {
            $scope.register();
        } else {
            console.log("Found regId: " + localstorage.get("regId"));
        }

        $scope.morePosts();
    });

    // function to open the modal PostDetail
    $scope.openPostDetail = function (id) {
        $ionicLoading.show({ template: "Loading post"});
        PostService.loadPost(id)
            .success(function(result) {
                $scope.post = result;
                $ionicLoading.hide();
                $scope.postDetail.show();
            });
    };

    // function to close PostDetail
    $scope.closePostDetail = function () {
        $scope.postDetail.hide();
    };

    // function to open the modal urlViewer
    $scope.openUrlViewer = function () {
        //$scope.urlViewer.show();
        window.open('http://www.apache.org', '_system', 'location=yes');
    };

    // function to close urlViewer
    $scope.closeUrlViewer = function () {
        $scope.urlViewer.hide();
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
/*
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
                console.log("Register error " + err)
            }); 
        } */
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
        $ionicLoading.show({ template: '<p class="item-icon-left">Carregando...<ion-spinner icon="lines"/></p>'});
        PostService.loadPosts($scope.page)
            .success(function(result) {
                result = JSON.parse(decryptText(result));
                if(result.length) {
                    $scope.posts = $scope.posts.concat(result);
                    $scope.page++;
                    $scope.$broadcast("scroll.infiniteScrollComplete");
                    $ionicLoading.hide();
                } else {
                    $scope.infiniteLoad = false; 
                    $scope.$broadcast("scroll.infiniteScrollComplete");
                    $ionicLoading.hide();
                }
            })
            .error(function (data, status) {
                $ionicLoading.show({ template: '<p class="item-icon-left">Verifique sua conexão<ion-spinner icon="lines"/></p>'});
                $timeout(function(){
                    $scope.morePosts();
                }, 5000);

                console.log("Error loading posts." + data + " " + status)
            });
    }
    $scope.toTrusted = function(text) {
        return ($sce.trustAsHtml(text));
    }

    function decryptText(text) {
        var aesUtil = new AesUtil(128, 10);
        var decrypt = aesUtil.decrypt("3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55", "F27D5C9927726BCEFE7510B1BDD3D137", "i wanna be sedated", text);
        return decrypt;
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


app.controller('PostDetailCtrl', function($scope, $stateParams, PostService) {
        console.log('details');
        //$ionicLoading.show({ template: "Loading posts..."});
        PostService.loadPost($stateParams.id)
            .success(function(result) {
                $scope.post = result;
//                $ionicLoading.hide();
            });
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
