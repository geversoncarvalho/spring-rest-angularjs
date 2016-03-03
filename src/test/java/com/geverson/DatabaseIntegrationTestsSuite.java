package com.geverson;

import com.geverson.account.AccountDatabaseIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AccountDatabaseIntegrationTest.class
})
public class DatabaseIntegrationTestsSuite {
}
