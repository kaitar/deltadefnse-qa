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

public class AccountCreatedPage extends AbstractPage {
    @FindBy(className = "auth-title")
    private ExtendedWebElement titleLabel;

    @FindBy(xpath = "//p[@class=\"form-text\"]")
    private ExtendedWebElement descriptionLabel;

    @FindBy(id = "myAccountButton")
    private ExtendedWebElement myAccountButton;

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL("https://portal.usconcealedcarry.com/register");
    }

    public MyDashboardPage openMyDashboardPage() {
        myAccountButton.click();
        return new MyDashboardPage(driver);
    }

    public String getTitleText() {
        return titleLabel.getText();
    }

    public String getDescriptionText() {
        return descriptionLabel.getText();
    }
    
}
