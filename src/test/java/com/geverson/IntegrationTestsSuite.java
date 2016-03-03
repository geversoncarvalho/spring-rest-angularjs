package com.geverson;

import com.geverson.account.UserAuthenticationIntegrationTest;
import com.geverson.home.HomeControllerTest;
import com.geverson.signup.SignupControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserAuthenticationIntegrationTest.class,
        SignupControllerTest.class,
        HomeControllerTest.class
})
public class IntegrationTestsSuite {
}
