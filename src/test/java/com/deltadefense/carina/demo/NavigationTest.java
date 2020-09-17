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
import com.deltadefense.carina.gui.pages.LogInPage;
import com.deltadefense.carina.gui.pages.NewUserSignUpPage;
import com.deltadefense.carina.gui.pages.myDashboard.MyAccountPage;
import com.deltadefense.carina.gui.pages.myDashboard.MyDashboardPage;
import com.qaprosoft.carina.core.foundation.AbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This set of tests verifies our ability to navigate around various parts of the usconcealedcarry.com page.
 *
 * @author tbenson
 */
public class NavigationTest extends AbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(NavigationTest.class);

    @Test()
    @MethodOwner(owner= "qpsdemo")
    public void navigateToNewUserRegistration() {
        // Open the usConcealedCarry.com home page and verify page is opened
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        // Click on the Log In link
        LogInPage loginPage = homePage.getHeaderMenu().openLogInPage();
        loginPage.waitForJSToLoad();
        Assert.assertTrue(loginPage.isPageOpened(), "Log In Page is not open!");
        // Click on the Sign up today button
        NewUserSignUpPage newUserSignUpPage = loginPage.clickSignUpTodayButton();
        newUserSignUpPage.waitForJSToLoad();
        Assert.assertTrue(newUserSignUpPage.isPageOpened(), "New Users sign up page is not opened");
    }
    @Test()
    @MethodOwner(owner = "qpsdemo")
    public void LoggedInUserNavigateToTrainingVideo() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        // Login to the usConcealedCarry.com with an existing user
        LogInPage loginPage = homePage.getHeaderMenu().openLogInPage();
        MyDashboardPage myDashboardPage = loginPage.logInExistingUser("TeresaMBenson@gmail.com", "Testit123" );
        // Verify that My Dashboard page is shown after Log In.
        Assert.assertTrue(myDashboardPage.isPageOpened(), "My Dashboard page is not opened!");
        // Navigate to My Account Page
        MyAccountPage myAccountPage = myDashboardPage.getSidebarAccountMenu().clickaccountDetailsContactInformationLink();
        // Verify that My Account Page is loaded
        Assert.assertTrue(myAccountPage.isPageOpened());
        // Verify User Contact information
        Boolean isAccountFullNameAccurate = myAccountPage.isAccountFullNameMatchingExpected("Teresa Benson");
        LOGGER.info(isAccountFullNameAccurate.toString());
        Assert.assertTrue(myAccountPage.isAccountFullNameMatchingExpected("Teresa Benson"), "Account Full Name is not what is expected!");
        Assert.assertTrue(myAccountPage.isAddressMatchingExpected("No saved primary shipping address."), "Physical address listed is not what is expected!");
        Assert.assertTrue(myAccountPage.isEmailAddressMatchingExpected("TeresaMBenson@gmail.com"), "Email address listed is not what is expected!");
    }

}