(function() {
    'use strict';

    angular
        .module('app.signin')
        .factory('authenticationService', authenticationService);

    authenticationService.$inject = ['$http'];

    /* @ngInject */
    function authenticationService($http) {

        var authCrendentials;

        var services = {
            'isAuthenticated': isAuthenticated,
            'signin': signin,
            'signout': signout
        };

        function isAuthenticated() {
            return !!authCrendentials;
        }

        function signin(username, password) {
            authCrendentials = 'Basic ' + btoa(username + ":" + password);
            return $http({
                'url': '/api/account/current',
                'method': 'GET',
                "headers": {
                    "Authorization": authCrendentials
                }
            })
            .then(function(resp) {
                return resp;
            });
        }

        function signout() {
            return $http({
                'url': '/api/logout',
                'method': 'POST'
            })
            .then(function(resp) {
                return resp;
            })
            .catch(function(resp) {
                return resp;
            });
        }

        return services;
    }

})();
