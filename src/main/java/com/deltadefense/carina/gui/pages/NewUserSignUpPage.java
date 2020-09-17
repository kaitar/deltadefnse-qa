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

import com.deltadefense.carina.gui.components.HeaderMenu;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class NewUserSignUpPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewUserSignUpPage.class);

    @FindBy(className = "uscca-header")
    private HeaderMenu headerMenu;

    @FindBy(className = "auth-title")
    private ExtendedWebElement titleLabel;

    @FindBy(className = "description")
    private ExtendedWebElement descriptionLabel;

    @FindBy(id = "createAccountFirstName")
    private ExtendedWebElement firstNameField;

    @FindBy(id="createAccountLastName")
    private ExtendedWebElement lastNameField;

    @FindBy(id = "createAccountEmail")
    private ExtendedWebElement emailField;

    @FindBy(id = "createAccountPassword")
    private ExtendedWebElement passwordField;

    @FindBy(id = "createAccountReEnterPassword")
    private ExtendedWebElement passwordConfirmationField;

    @FindBy(id = "createAccountSignUpButton")
    private ExtendedWebElement createAccountButton;

    @FindBy(id = "signInTodayButton")
    private ExtendedWebElement existingAccountButton;

    @FindBy(className = "form-field invalid")
    private ExtendedWebElement formFieldValidationErrorArea;

    public NewUserSignUpPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("https://portal.usconcealedcarry.com/register");
    }

    public LogInPage existingUserLogInButton() {
        existingAccountButton.click();
        return new LogInPage(driver);
    }

    private void enterFirstName(String firstName) {
        firstNameField.type(firstName);
    }

    private void enterLastName(String lastName) {
        lastNameField.type(lastName);
    }

    private void enterEmail(String accountEmail) {
        emailField.type(accountEmail);
    }

    private void enterPassword(String accountPassword) {
        passwordField.type(accountPassword);
    }

    private void enterPasswordConfirmation(String passwordConfirmation) {
        passwordConfirmationField.type(passwordConfirmation);
    }

    private void clickAccountSignUpButton() {
        createAccountButton.click();
    }

    public AbstractPage createAccount(HashMap<String, String> accountInfo) {
        enterFirstName(accountInfo.get("firstName"));
        enterLastName(accountInfo.get("lastName"));
        enterEmail(accountInfo.get("email"));
        enterPassword(accountInfo.get("password"));
        enterPasswordConfirmation(accountInfo.get("passwordConfirmation"));
        clickAccountSignUpButton();
        if (accountInfo.get("successfulCreation").equals("true")) {
            return new AccountCreatedPage(driver);
        }
        else {
            return new NewUserSignUpPage(driver);
        }
    }

    public String getTitle() {
        return titleLabel.getText();
    }

    public String getDescription() {
        return descriptionLabel.getText();
    }

    public String getformFieldErrorText() {
        return formFieldValidationErrorArea.getText();
    }


}
