package com.geverson.account;

import com.geverson.config.DbUnitConfig;
import com.geverson.utils.ColumnReplacingDataSetLoader;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;

@DbUnitConfiguration(dataSetLoaderBean = "dataSetLoader")
public class AccountDatabaseIntegrationTest extends DbUnitConfig {

    @Inject
    public AccountRepository accountRepository;

    @Inject
    public ColumnReplacingDataSetLoader dataSetLoader;

    @Inject
    public EntityManager entityManager;

    @Before
    public void setup(){
    }

    @Test
    @DatabaseSetup("sampleData.xml")
    @ExpectedDatabase(value = "expectedData.xml", assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void testCreateUser(){
        Account account = new Account("nobody2@test.com", "654321", "ROLE_ADMIN");
        SimpleDateFormat fmter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        dataSetLoader.replace("CREATIONDATE", fmter.format(account.getCreated()));
        accountRepository.save(account);
    }

    @Test
    @DatabaseSetup("sampleData.xml")
    public void shouldReturnUser(){
        String email = "nobody@test.com";
        Account account = accountRepository.findOneByEmail("nobody@test.com");
        Assert.assertNotNull(account);
        Assert.assertEquals(email, account.getEmail());
    }
}
