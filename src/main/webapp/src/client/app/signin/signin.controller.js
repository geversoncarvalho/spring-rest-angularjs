(function() {
    'use strict';

    angular.module('app.signin')
        .controller('SigninController', SigninController);

    SigninController.$inject = ['authenticationService', '$location'];

    /* @ngInject */
    function SigninController(authenticationService, $location) {
        var vm = this;

        vm.credentials = {};
        vm.submitLogin = submitLogin;

        function submitLogin() {
            authenticationService.signin(vm.credentials.username, vm.credentials.password)
            .then(signinAction);
        }

        function signinAction(data) {
            $location.path("/");
        }
    }
})();
