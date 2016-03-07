/* jshint -W117, -W030 */
describe('authorization.service', function() {
    describe('Authentication Service', function() {

        var username = "user";
        var password = "demo";

        beforeEach(function() {
            bard.appModule('app.signin');
            bard.inject('$httpBackend', 'authenticationService');
        });

        it('should not be authenticated', function() {
            expect(authenticationService.isAuthenticated()).to.be.false;
        });

        it('should have authorization header', function() {
            $httpBackend.expectGET("/api/account/current", undefined, function(headers) {
                    var credentials = window.btoa(username + ":" + password);
                    return headers['Authorization'] === ('Basic ' + credentials);
                }).respond(200,{});

            authenticationService.signin(username, password);
            $httpBackend.flush();
        });
    });
});
