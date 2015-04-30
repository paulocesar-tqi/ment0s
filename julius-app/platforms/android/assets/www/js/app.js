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