/**
 * Author: hollyschinsky
 * twitter: @devgirfl
 * blog: devgirl.org
 * more tutorials: hollyschinsky.github.io
 */
 var app = angular.module('app', ['ionic','ngCordova'])
    .run(function($ionicPlatform) {
    	$ionicPlatform.ready(function () {
            // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
            // for form inputs)
            if (window.cordova && window.cordova.plugins.Keyboard) {
                cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
            }
            if (window.StatusBar) {
                // org.apache.cordova.statusbar required
                StatusBar.styleDefault();
            }
        });
	})

	.config(function ($stateProvider, $urlRouterProvider) {

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