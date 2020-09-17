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

public class MyDashboardPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyDashboardPage.class);

    @FindBy(xpath = "//h1[@class=\"d-none d-lg-block dashboard__h1--page-title\"]")
    private ExtendedWebElement myDashboardTitle;

    @FindBy(className = "dashboard-wrapper")
    private ExtendedWebElement dashboardMainContentArea;

    @FindBy(className = "uscca-header")
    private HeaderMenu headerMenu;

    @FindBy(className = "col-lg-4")
    private SidebarAccountMenu sidebarAccountMenu;

    public MyDashboardPage(WebDriver driver) {
        super(driver);
        setPageURL("/dashboard");
    }

    public SidebarAccountMenu getSidebarAccountMenu() {
        return sidebarAccountMenu;
    }

    public HeaderMenu getHeaderMenu() {
        return headerMenu;
    }
    
}
