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
package com.deltadefense.carina.gui.components;

import com.deltadefense.carina.gui.pages.MembershipPage;
import com.deltadefense.carina.gui.pages.myDashboard.MyAccountPage;
import com.deltadefense.carina.gui.pages.myDashboard.MyDashboardPage;
import com.deltadefense.carina.gui.pages.myDashboard.VideosPage;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SidebarAccountMenu extends AbstractUIObject {
    private static final Logger LOGGER = LoggerFactory.getLogger(SidebarAccountMenu.class);
    @FindBy(xpath = "//img[contains(@class, 'sidebar-account__img--logo')]")
    private ExtendedWebElement accountLogo;

    @FindBy(className = "sidebar-account__p--name")
    private ExtendedWebElement accountFullName;

    @FindBy(id = "free_account_join_uscca_now")
    private ExtendedWebElement membershipLink;

    @FindBy(id = "myDashboard")
    private ExtendedWebElement myDashboardHomeLink;

    @FindBy(id = "accountDetailsNav")
    private ExtendedWebElement accountDetailsMenu;

    @FindBy(id = "contactInformation")
    private ExtendedWebElement accountDetailsContactInformationLink;

    @FindBy(id="educationTrainingNav")
    private ExtendedWebElement educationAndTrainingMenu;

    @FindBy(id="videos")
    private ExtendedWebElement educationVideosLink;

    public SidebarAccountMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public String getFirstAndLastNameForAccount() {
        assertElementPresent(accountFullName);
        LOGGER.info("SIDEBAR_MENU: Account name is: %s", accountFullName.getText());
        return accountFullName.getText();
    }

    public boolean isAccountLogoPresent() {
        LOGGER.info("SIDEBAR_MENU: Account logo present: %s", accountLogo.isElementPresent());
        return accountLogo.isElementPresent();
    }

    public MyDashboardPage clickMyDashboardLink() {
        assertElementPresent(myDashboardHomeLink);
        LOGGER.info("SIDEBAR_MENU: Clicking My Dashboard Home Link");
        myDashboardHomeLink.click();
        return new MyDashboardPage(driver);
    }

    public MyAccountPage clickaccountDetailsContactInformationLink() {
        if (accountDetailsContactInformationLink.isClickable() == false) {

            clickAccountDetailsLink();
        }
        assertElementPresent(accountDetailsContactInformationLink);
        LOGGER.info("SIDEBAR_MENU: Clicking Account Details>Contact Information Link");
        accountDetailsContactInformationLink.click();
        return new MyAccountPage(driver);
    }

    public void clickAccountDetailsLink() {
        assertElementPresent(accountDetailsMenu);
        LOGGER.info("SIDEBAR_MENU: Clicking Account Details menu");
        accountDetailsMenu.click();
    }

    public MembershipPage clickMembershipLink() {
        assertElementPresent(membershipLink);
        LOGGER.info("SIDEBAR_MENU: Clicking Membership Link");
        membershipLink.click();
        return new MembershipPage(driver);
    }

    public void clickEducationAndTrainingMenu() {
        assertElementPresent(educationAndTrainingMenu);
        LOGGER.info("SIDEBAR_MENU: Clicking Education and Training Menu Drop Down");
        educationAndTrainingMenu.click();
    }

    public VideosPage clickEducationAndTrainingVideosLink() {
        if (educationAndTrainingMenu.isClickable() == false) {
            clickEducationAndTrainingMenu();
        }
        assertElementPresent(educationVideosLink);
        LOGGER.info("SIDEBAR_MENU: Clicking Education and Training>Videos Link");
        educationVideosLink.click();
        return new VideosPage(driver);
    }

}
