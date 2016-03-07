(function() {
    'use strict';

    angular
        .module('app.layout')
        .directive('htTopNav', htTopNav);

    htTopNav.$inject = ['authenticationService', '$location'];

    /* @ngInject */
    function htTopNav (authenticationService, $location) {
        var directive = {
            bindToController: true,
            controller: TopNavController,
            controllerAs: 'vm',
            restrict: 'EA',
            scope: {
                'navline': '='
            },
            templateUrl: 'app/layout/ht-top-nav.html'
        };

        /* @ngInject */
        function TopNavController() {
            var vm = this;

            vm.logout = logout;

            function logout() {
                authenticationService.signout().then(signoutAction);
            }

            function signoutAction(data) {
                $location.path("/signin");
            }
        }

        return directive;
    }
})();
