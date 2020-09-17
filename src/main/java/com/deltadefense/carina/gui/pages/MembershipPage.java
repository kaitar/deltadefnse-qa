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
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MembershipPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(MembershipPage.class);

    @FindBy(className = "uscca-header")
    private HeaderMenu headerMenu;

    public MembershipPage(WebDriver driver) {
        super(driver);
        setPageURL("/membership");
    }

    public HeaderMenu getHeaderMenu() {
        return headerMenu;
    }
}
