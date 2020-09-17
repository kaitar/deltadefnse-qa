/*
 * Copyright 2013-2020 QAPROSOFT (http://qaprosoft.com/).
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
package com.deltadefense.carina.gui.pages;

import com.deltadefense.carina.gui.pages.myDashboard.MyDashboardPage;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/*
* Login Page is displayed when clicking on the Log In button located in the header menu.
*/

public class LogInPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogInPage.class);

    @FindBy(className = "auth-title")
    private ExtendedWebElement loginLabel;

    @FindBy(id = "signinEmail")
    private ExtendedWebElement emailField;
    
    @FindBy(id = "continueButton")
    private ExtendedWebElement continueButton;

    @FindBy(name = "password")
    private ExtendedWebElement passwordField;

    @FindBy(id = "signInSignInButton")
    private ExtendedWebElement logInNowButton;
    
    @FindBy(id = "signUpTodayButton")
    private ExtendedWebElement signUpButton;

    @FindBy(id="forgotPasswordLink")
    private ExtendedWebElement forgotPasswordLink;

    @FindBy(id="emailLoginLink")
    private ExtendedWebElement emailLoginLink;

    @FindBy(id="signInRememberMeCheckBox")
    private ExtendedWebElement rememberMeCheckbox;

    @FindBy(className="resetButton")
    private ExtendedWebElement changeEmailAddressLink;
    
    public LogInPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("https://portal.usconcealedcarry.com/?");
    }

	private void clickSignInButton() {
        assertElementPresent(logInNowButton);
        logInNowButton.click();
    }

	public NewUserSignUpPage clickSignUpTodayButton() {
        assertElementPresent(signUpButton);
        signUpButton.click();
        return new NewUserSignUpPage(driver);
    }

    private void enterAccountEmailField(String email) {
        assertElementPresent(emailField);
        emailField.type(email);
    }

    private void clickContinueButton() {
        assertElementPresent(continueButton);
        continueButton.click();
    }

    private void enterAccountPasswordField(String password) {
        assertElementPresent(passwordField);
        Assert.assertTrue(passwordField.isElementPresent());
        passwordField.click();
        passwordField.type(password);
    }

    private void clickEmailLoginLink() {
        assertElementPresent(emailLoginLink);
        emailLoginLink.click();
    }

    private void clickForgotPasswordLink() {
        assertElementPresent(forgotPasswordLink);
        forgotPasswordLink.click();
    }

    private boolean isRememberCheckboxMarked() {
        assertElementPresent(rememberMeCheckbox);
        return rememberMeCheckbox.isChecked();
    }

    private void clickLoginNowButton() {
        assertElementPresent(logInNowButton);
        Assert.assertTrue(logInNowButton.isClickable());
        logInNowButton.click();
    }

    public MyDashboardPage logInExistingUser(String email, String password) {
        enterAccountEmailField(email);
        clickContinueButton();
        enterAccountPasswordField(password);
        clickSignInButton();

        return new MyDashboardPage(driver);
    }
}
