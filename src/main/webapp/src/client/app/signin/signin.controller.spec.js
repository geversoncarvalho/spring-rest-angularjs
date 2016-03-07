/* jshint -W117, -W030 */
describe('SigninController', function() {
    var controller;

    beforeEach(function() {
        bard.appModule('app.signin');
        bard.inject('$controller', '$httpBackend');
    });

    beforeEach(function() {
        controller = $controller('SigninController');
    });

    bard.verifyNoOutstandingHttpRequests();

    describe('Signin Controller', function() {
        it('should have credentials object', function() {
            expect(controller.credentials).to.be.defined;
        });

        it("should do signin on submit", function() {
            
        });
    });
});
