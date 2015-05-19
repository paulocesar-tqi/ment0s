/**
 * Author: hollyschinsky
 * twitter: @devgirfl
 * blog: devgirl.org
 * more tutorials: hollyschinsky.github.io
 */
 var app = angular.module('app', ['ionic','ngCordova'])
    .run(function($ionicPlatform) {
    	$ionicPlatform.ready(function () {
            if (window.StatusBar) {
                // org.apache.cordova.statusbar required
                StatusBar.styleDefault();
            }
        });
	})

	.factory(("ionPlatform"), function( $q ){
	    var ready = $q.defer();

	    ionic.Platform.ready(function( device ){
	        ready.resolve( device );
	    });

	    return {
	        ready: ready.promise
	    }
	})

	.factory('localstorage', ['$window', function($window) {
	  return {
	    set: function(key, value) {
	      $window.localStorage[key] = value;
	    },
	    get: function(key, defaultValue) {
	      return $window.localStorage[key] || defaultValue;
	    },
	    setObject: function(key, value) {
	      $window.localStorage[key] = JSON.stringify(value);
	    },
	    getObject: function(key) {
	      return JSON.parse($window.localStorage[key] || '{}');
	    },
	    removeItem: function(key) {
	      $window.localStorage.removeItem(key);
	    }
	  }
	}])

    .directive('ngcDone', function ($timeout) {
        return function (scope, element, attrs) {
            scope.$watch(attrs.ngcDone, function (callback) {

                if (scope.$last === undefined) {
                    scope.$watch('htmlElement', function () {
                        if (scope.htmlElement !== undefined) {
                            $timeout(eval(callback), 1);
                        }
                    });
                }

                if (scope.$last) {
                    eval(callback)();
                }
            });
        }
    })

	.config(function ($stateProvider, $urlRouterProvider, $sceProvider) {

        $sceProvider.enabled(false);

        $stateProvider

            .state('posts', {
            	url: '/posts',
                templateUrl: 'templates/posts.html',
                controller: 'PostsCtrl'
            })

            .state('post', {
            	url: '/post/:id',
                templateUrl: 'templates/post-detail.html',
                controller: 'PostDetailCtrl'
            });

		$urlRouterProvider.otherwise('/posts');
    });