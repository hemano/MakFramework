package com.masteringselenium.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by hemantojha on 09/07/16.
 */
public interface DriverSetup {

    WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);
    DesiredCapabilities getDesiredCapabilities();

}
