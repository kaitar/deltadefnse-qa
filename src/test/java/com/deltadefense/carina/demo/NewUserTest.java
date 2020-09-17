/*
 * Copyright 2013-2018 QAPROSOFT (http://qaprosoft.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.deltadefense.carina.demo;

import com.deltadefense.carina.gui.pages.AccountCreatedPage;
import com.deltadefense.carina.gui.pages.HomePage;
import com.deltadefense.carina.gui.pages.LogInPage;
import com.deltadefense.carina.gui.pages.NewUserSignUpPage;
import com.deltadefense.carina.gui.pages.myDashboard.MyAccountPage;
import com.deltadefense.carina.gui.pages.myDashboard.MyDashboardPage;
import com.github.javafaker.Faker;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.utils.tag.TestTag;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.HashMap;

/**
 * This set of tests verifies our ability to create a new user account
 *
 * @author tbenson
 */
public class NewUserTest extends AbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewUserTest.class);
    HomePage homePage = null;
    LogInPage logInPage = null;
    MyDashboardPage myDashboardPage = null;
    Faker faker = new Faker();
    WebDriver driver = getDriver();

    @BeforeSuite
    public void startDriver() {
        // Open GSM Arena home page and verify page is opened
        homePage = new HomePage(getDriver());
        homePage.open();
        homePage.isPageOpened();
    }

    @Test()
    @MethodOwner(owner= "qpsdemo")
    @TestTag(name = "New User Successful Creation", value="web")
    public void successfullyCreateNewUserThenChangePassword() {
        // Create the dadta for a new user
        LogInPage logInPage = new LogInPage(driver);
        MyDashboardPage myDashboardPage = new MyDashboardPage(driver);
        NewUserSignUpPage newUserSignUpPage = new NewUserSignUpPage(driver);

        HashMap<String, String> newUserAccountData = new HashMap<String, String>() {{
            put("firstName", faker.name().firstName());
            put("lastName", faker.name().lastName());
            put("email", faker.internet().emailAddress());
            put("password", faker.internet().password(8, 10));
            put("successfulCreation", "true");
        }};
        newUserAccountData.put("passwordConfirmation", newUserAccountData.get("password"));
        newUserAccountData.put("newPassword", faker.internet().password(8, 10));

        newUserSignUpPage = attemptToCreateNewUser(newUserAccountData);
        AccountCreatedPage accountCreatedPage = (AccountCreatedPage) newUserSignUpPage.createAccount(newUserAccountData);
        Assert.assertTrue(accountCreatedPage.isPageOpened());
        myDashboardPage = accountCreatedPage.openMyDashboardPage();
        Assert.assertTrue(myDashboardPage.isPageOpened());
        changeUserPassword(newUserAccountData.get("newPassword"), newUserAccountData.get("newPassword"), myDashboardPage);
    }
/*
    @Test()
    @MethodOwner(owner="qpsdemo")
    public void failCreateUserPasswordTooShort() {
        newUserSignUpPage = attemptToCreateNewUser();
        Assert.assertTrue(newUserSignUpPage.getformFieldErrorText().contains(""));
    }

    @Test()
    @MethodOwner(owner="qpsdemo")
    public void failCreateUserPasswordMismatch() {
        Assert.assertTrue(newUserSignUpPage.getformFieldErrorText().contains(""));
    }

    @Test()
    @MethodOwner(owner="qpsdemo")
    public void failCreateUserEmailInUse() {
        Assert.assertTrue(newUserSignUpPage.getformFieldErrorText().contains(""));
    }*/

    private NewUserSignUpPage attemptToCreateNewUser(HashMap <String, String> newUserAccountData) {
        // Open the usConcealedCarry.com home page and verify page is opened
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        // Click on the Log In link
        logInPage = homePage.getHeaderMenu().openLogInPage();
        Assert.assertTrue(logInPage.isPageOpened(), "Log In Page is not open!");
        // Click on the Sign up today button
        NewUserSignUpPage newUserSignUpPage = logInPage.clickSignUpTodayButton();
        Assert.assertTrue(newUserSignUpPage.isPageOpened(), "New Users sign up page is not opened");

        return newUserSignUpPage;
    }

    private void changeUserPassword(String password, String passwordConfirmation, MyDashboardPage myDashboardPage) {
        // Navigate to my account page
        MyAccountPage myAccountPage = myDashboardPage.getSidebarAccountMenu().clickaccountDetailsContactInformationLink();
        // Update password
        myAccountPage.updatePassword(password, passwordConfirmation);
    }

    private void logoutUser(HomePage homePage, MyAccountPage myAccountPage) {
        // Logout
        homePage = myAccountPage.getHeaderMenu().clickLogOutLink();
    }

    private void loginEstablishedUser(LogInPage loginPage, HomePage homePage, String username, String password) {
        loginPage = homePage.getHeaderMenu().openLogInPage();
        loginPage.logInExistingUser(username, password);
    }
}