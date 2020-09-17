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
package com.deltadefense.carina.gui.pages.myDashboard;

import com.deltadefense.carina.gui.components.HeaderMenu;
import com.deltadefense.carina.gui.components.SidebarAccountMenu;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAccountPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyAccountPage.class);

    @FindBy(className = "uscca-header")
    private HeaderMenu headerMenu;

    @FindBy(className="col-lg-8")
    private ExtendedWebElement myAccountContainer;

    @FindBy(id = "AccountDetails")
    private ExtendedWebElement accountDetailsArea;

    @FindBy(className = "js-personal-information-name")
    private ExtendedWebElement accountFullName;

    @FindBy(xpath="//div[contains(@id, 'shipping-information')]//em")
    private ExtendedWebElement accountAddress;

    @FindBy(className="js-phone-numbers-phone")
    private ExtendedWebElement accountNumberPrimary;

    @FindBy(className="js-phone-numbers-phone-other")
    private ExtendedWebElement accountNumberOther;

    @FindBy(className="email-information-email")
    private ExtendedWebElement accountEmail;

    @FindBy(id = "passwordEdit")
    private ExtendedWebElement editPasswordLink;

    @FindBy(id = "password-information-password")
    private ExtendedWebElement editPasswordField;

    @FindBy(id = "password-information-confirm-password")
    private ExtendedWebElement editPasswordConfirmationField;

    @FindBy(id="passwordSave")
    private ExtendedWebElement saveEditPasswordButton;

    @FindBy(id="passwordCancel")
    private ExtendedWebElement cancelEditPasswordButton;

    @FindBy(id="parsley-id-35")
    private ExtendedWebElement passwordsDoNotMatchWarning;

    @FindBy(className = "col-lg-4")
    private SidebarAccountMenu sidebarAccountMenu;

    @FindBy(className="notifyjs-bootstrap-base notifyjs-bootstrap-success")
    private ExtendedWebElement passwordSuccessfullyChanged;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        setPageURL("/dashboard/contact-information");
    }

    public SidebarAccountMenu getSidebarAccountMenu() {
        return sidebarAccountMenu;
    }

    public HeaderMenu getHeaderMenu() {
        return headerMenu;
    }

    public void updatePassword(String password, String passwordConfirmation) {
        assertElementPresent(myAccountContainer);
        clickEditPasswordlink();
    	enterPasswordField(password);
    	enterPasswordConfirmationField(passwordConfirmation);
    	clickSaveEditNewPasswordButton();
	}

	public Boolean isAccountFullNameMatchingExpected(String expectedAccountFullName) {
        String actualAccountFullName = getAccountFullNameText();
        LOGGER.info("MY_ACCOUNT: Comparing expected and actual full name");
        return actualAccountFullName.equalsIgnoreCase(expectedAccountFullName);
    }

    public Boolean isEmailAddressMatchingExpected(String expectedEmailAddress) {
        String actualEmailAddress = getAccountEmailText();
        LOGGER.info("MY_ACCOUNT: Comparing expected and actual email address");
        return expectedEmailAddress.equalsIgnoreCase(actualEmailAddress);
    }

    public Boolean isAddressMatchingExpected(String expectedPhysicalAddress){
        String actualPhysicalAddress = getPhysicalAddressText();
        LOGGER.info("MY_ACCOUNT: Comparing expectd and actual Physical address");
        return actualPhysicalAddress.equalsIgnoreCase(expectedPhysicalAddress);
    }

    private String getPhysicalAddressText() {
        assertElementPresent(accountAddress);
        LOGGER.info("MY_ACCOUNT: Gathering address text");
        return accountAddress.getText();
    }

    private String getAccountEmailText() {
        assertElementPresent(accountEmail);
        LOGGER.info("MY_ACCOUNT: Gathering Email text");
        return accountEmail.getText();
    }

    private String getAccountFullNameText() {
        assertElementPresent(accountFullName);
        LOGGER.info("MY_ACCOUNT: Gathering Full name text");
        return accountFullName.getText();
    }

	private void clickEditPasswordlink() {
        assertElementPresent(editPasswordLink);
        LOGGER.info("MY_ACCOUNT: Clicking Edit password Link");
        editPasswordLink.click();
    }

    private void enterPasswordField(String password) {
        assertElementPresent(editPasswordField);
        LOGGER.info("MY_ACCOUNT: Entering %s into password field", password);
        editPasswordField.type(password);
    }

    private void enterPasswordConfirmationField(String passwordConfirmation) {
        assertElementPresent(editPasswordConfirmationField);
        LOGGER.info("MY_ACCOUNT: Entering %s into password confirmation field", passwordConfirmation);
        editPasswordConfirmationField.type(passwordConfirmation);
    }

    private void clickSaveEditNewPasswordButton() {
        assertElementPresent(saveEditPasswordButton);
        LOGGER.info("MY_ACCOUNT: Clicking Save password Link");
        saveEditPasswordButton.click();
    }

}
