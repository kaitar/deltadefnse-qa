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

import com.deltadefense.carina.gui.pages.HomePage;
import com.deltadefense.carina.gui.pages.NewUserSignUpPage;
import com.github.javafaker.Faker;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;

/**
 * This set of tests verifies failures surrounding new users
 *
 * @author tbenson
 */
public class NewUserNegativeCases extends AbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewUserNegativeCases.class);
    HomePage homePage = null;
    NewUserSignUpPage newUserSignUpPage= null;
    Faker faker = new Faker();

    @BeforeSuite
    public void startDriver() {
        // Open GSM Arena home page and verify page is opened
        homePage = new HomePage(getDriver());
    }
/*
    @Test()
    @MethodOwner(owner="qpsdemo")
    @TestTag(name="New User Negative Test", value="Password too short")
    public void failCreateUserPasswordTooShort() {
        newUserSignUpPage = attemptToCreateNewUser();
        Assert.assertTrue(newUserSignUpPage.getformFieldErrorText().contains(""));
    }

    @Test()
    @MethodOwner(owner="qpsdemo")
    @TestTag(name="New User Negative Test", value="Password Mismatch")
    public void failCreateUserPasswordMismatch() {
        Assert.assertTrue(newUserSignUpPage.getformFieldErrorText().contains(""));
    }

    @Test()
    @MethodOwner(owner="qpsdemo")
    @TestTag(name="New User Negative Test", value="Email Already in Use")
    public void failCreateUserEmailInUse() {
        Assert.assertTrue(newUserSignUpPage.getformFieldErrorText().contains(""));
    }

    private NewUserSignUpPage attemptToCreateNewUser(HashMap <String, String> newUserAccountData, HomePage homePage, LogInPage logInPage,
                               NewUserSignUpPage newUserSignUpPage) {
        // Open the usConcealedCarry.com home page and verify page is opened
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        // Click on the Log In link
        logInPage = homePage.getHeaderMenu().openLogInPage();
        Assert.assertTrue(logInPage.isPageOpened(), "Log In Page is not open!");
        // Click on the Sign up today button
        newUserSignUpPage = logInPage.clickSignUpTodayButton();
        Assert.assertTrue(newUserSignUpPage.isPageOpened(), "New Users sign up page is not opened");

        return newUserSignUpPage;
    }

    private void changeUserPassword(MyDashboardPage myDashboardPage, String password, String passwordConfirmation, HomePage homePage, LogInPage loginPage, Boolean updateShouldBeSuccessful) {
        // Navigate to my account page
        MyAccountPage myAccountPage = myDashboardPage.getSidebarAccountMenu().clickaccountDetailsContactInformationLink();
        // Update password
        myAccountPage.updatePassword(password, passwordConfirmation);
        ExtendedWebElement passwordChangeNotification = myAccountPage.getPasswordChangedNotification();

        if (updateShouldBeSuccessful) {
            Assert.assertTrue(passwordChangeNotification.isElementPresent(), "Password notification change not visible!");
        }
        else {
            Assert.assertTrue(passwordChangeNotification.isElementNotPresent(20));
        }
    }

    private void logoutUser(HomePage homePage, MyAccountPage myAccountPage) {
        // Logout
        homePage = myAccountPage.getHeaderMenu().clickLogOutLink();
    }

    private void loginEstablishedUser(LogInPage loginPage, HomePage homePage, String username, String password) {
        loginPage = homePage.getHeaderMenu().openLogInPage();
        loginPage.logInExistingUser(username, password);
    }*/
}