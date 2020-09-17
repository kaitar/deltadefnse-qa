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
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

public class VideosPage extends AbstractPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(VideosPage.class);

    @FindBy(className = "uscca-header")
    private HeaderMenu headerMenu;

    // This is a complex xpath query, but it should result in getting us both the title of the series of the videos, but
    // also the three individual videos underneath of each one.
    @FindBy(xpath="//div[contains(@class, 'member-library-wrapper')]" +
            "[./div[contains(@class, 'row video-series')]]" +
            "[./div[contains(@class, 'video-series__list')]]")
    private List<ExtendedWebElement> trainingVideoSeriesWithVideos;

    @FindBy(xpath="//div[contains(@class, \"row video-series\")]")
    private List<ExtendedWebElement> trainingVideoSeriesList;

    @FindBy(xpath="//div[contains(@class, \"video-series__list\")]")
    private List<ExtendedWebElement> videosAreaListedWithinSeries;

    public VideosPage(WebDriver driver) {
        super(driver);
        setPageURL("/dashboard/videos");
    }

    public HeaderMenu getHeaderMenu() {
        return headerMenu;
    }

    public HashMap<String, HashMap<String, String>> getVideoDataForCSVReport() {
        HashMap<String, HashMap<String, String>> videoData = new HashMap<String, HashMap<String,String>>();
        Integer videoSeriesNumber = 0;
        for (ExtendedWebElement trainingVideoSeries : trainingVideoSeriesList) {
            HashMap<String, String> videoDataInnerMap = new HashMap<String, String>();
            trainingVideoSeries.assertElementPresent();
            String videoSeriesTitleText = trainingVideoSeries.
                    findExtendedWebElement(By.xpath("//h3[contains(@class, \"video-series__title\")]")).getText();
            String videoSeriesDescriptionText = trainingVideoSeries.
                    findExtendedWebElement(By.xpath("//p[contains(@class, \"video-series__description\")]")).getText();
            videoDataInnerMap.put("Video Series Title Text", videoSeriesTitleText);
            videoDataInnerMap.put("Description Text", videoSeriesDescriptionText);
            List<ExtendedWebElement> individualVideosAreas = videosAreaListedWithinSeries.get(videoSeriesNumber).
                    findExtendedWebElements(By.xpath("//div[contains(@class, \"col-sm-4\")]"));;
                for (ExtendedWebElement individualVideos : individualVideosAreas ) {
                    String videoThumbnailImgSrc = individualVideos.findExtendedWebElement(By.tagName("img")).getAttribute("src");
                    String videoName = individualVideos.findExtendedWebElement(By.className("video-series__video-title")).getText();
                    String videoButtonWistiaData = individualVideos.findExtendedWebElement(By.className("button")).getAttribute("wistia-cta");
                    videoDataInnerMap.put(videoName + " Image Thumbnail", videoThumbnailImgSrc);
                    videoDataInnerMap.put(videoName + " Wistia Data", videoButtonWistiaData);
                }
            videoData.put("Video Series: " + Integer.valueOf(videoSeriesNumber+1).toString(), videoDataInnerMap);
            videoSeriesNumber = videoSeriesNumber ++;
        }
        return videoData;
    }
}
