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

import com.deltadefense.carina.gui.pages.*;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeaderMenu extends AbstractUIObject {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeaderMenu.class);
    
    @FindBy(id = "uscca-header-logo-home-link")
    private ExtendedWebElement homeLink;
    
    @FindBy(className = "uscca-header__desktop-login")
    private ExtendedWebElement logInLink;

    @FindBy(id = "nav-link-membership")
    private ExtendedWebElement membershipLink;

    @FindBy(id = "nav-link_training")
    private ExtendedWebElement trainingLink;
    
    @FindBy(id = "nav-link_resources")
    private ExtendedWebElement resourcesLink;
    
    @FindBy(id = "nav-link_blog")
    private ExtendedWebElement blogLink;
    
    @FindBy(id = "uscca-header__search-field")
    private ExtendedWebElement searchField;
    
    @FindBy(id = "uscca-header__search-button")
    private ExtendedWebElement searchButton;
    
    @FindBy(linkText = "Join USCCA")
    private ExtendedWebElement joinLink;

    @FindBy(id="nav-link_account-dropdown")
    private ExtendedWebElement accountDropDownMenu;

    @FindBy(id="nav-link_account-dropdown_log-out")
    private ExtendedWebElement accountDropDownLogOutLink;

    public HeaderMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public HomePage openHomePage() {
        assertElementPresent(homeLink);
        LOGGER.info("HEADER_MENU: Clicking the Home link");
        homeLink.click();
        return new HomePage(driver);
    }

    public LogInPage openLogInPage() {
        assertElementPresent(logInLink);
        LOGGER.info("HEADER_MENU: Clicking the Log In link");
        logInLink.click();
        return new LogInPage(driver);
    }

    public MembershipPage openMembershipPage() {
        assertElementPresent(membershipLink);
        LOGGER.info("HEADER_MENU: Clicking the Membership link");
        membershipLink.click();
        return new MembershipPage(driver);
    }
    
    public TrainingPage openTrainingPage() {
        assertElementPresent(trainingLink);
        LOGGER.info("HEADER_MENU: Clicking the Training link");
    	trainingLink.click();
    	return new TrainingPage(driver);
    }
    
    public ResourcesPage openResourcesPage() {
        assertElementPresent(resourcesLink);
        LOGGER.info("HEADER_MENU: Clicking the Resources link");
    	resourcesLink.click();
    	return new ResourcesPage(driver);
    }
    
    public BlogPage openBlogPage() {
        assertElementPresent(blogLink);
        LOGGER.info("HEADER_MENU: Clicking the Blog link");
    	blogLink.click();
    	return new BlogPage(driver);
    }
    
    public SearchPage openSearchPage(String q) {
        assertElementPresent(searchField);
        assertElementPresent(searchButton);
        LOGGER.info("HEADER_MENU: Typing %s into search bar", q);
        searchField.type(q);
        LOGGER.info("HEADER_MENU: Clicking the search button");
        searchButton.click();
        return new SearchPage(driver);
    }
    
    public HomePage clickLogOutLink() {
        LOGGER.info("Attempting to log out user");
        if (accountDropDownLogOutLink.isClickable() == false) {
            LOGGER.info("HEADER_MENU: Clicking on the Account drop down menu");
            accountDropDownMenu.click();
        }
        LOGGER.info("HEADER_MENU: Clicking the Account>Log out link");
        accountDropDownLogOutLink.click();

        return new HomePage(driver);
    }
}
