package com.masteringselenium;

import com.masteringselenium.config.DriverType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hemantojha on 09/07/16.
 */
public class WebDriverThread {

    private WebDriver driver;
    private DriverType selectedDriverType;

//    private final DriverType defaultDriverType = DriverType.FIREFOX;
    private final DriverType defaultDriverType = DriverType.FIREFOX;

    private final String browser =
            System.getProperty("browser").toUpperCase();
    private final String operatingSystem =
            System.getProperty("os.name").toUpperCase();
    private final String systemArchitecture =
            System.getProperty("os.arch").toUpperCase();
    private final boolean useRemoteWebDriver =
            Boolean.getBoolean("remoteDriver");

    public WebDriver getDriver() throws Exception {

        if (null == driver) {

            selectedDriverType = determineEffectiveDriverType();
            DesiredCapabilities desiredCapabilities =
            selectedDriverType.getDesiredCapabilities();
            instantiateWebDriver(desiredCapabilities);
        }
        return driver;
    }

    private DriverType determineEffectiveDriverType(){

        DriverType driverType = defaultDriverType;
        try{
            driverType = DriverType.valueOf(browser);
        }catch (IllegalArgumentException ignore){
            System.err.println("Unknown DriverType specified... defaulting to " +
                        "\"driverType\"");
        }catch (NullPointerException ignore){
            System.err.println("Unknown DriverType specified... defaulting to " +
                    "\"driverType\"");
        }

        return driverType;
    }

    private void instantiateWebDriver(DesiredCapabilities desiredCapabilities) throws MalformedURLException {

        System.out.println(String.format("Current Operating System : {%s} \n"
                , operatingSystem));
        System.out.println(String.format("Current System Architecture : {%s} \n"
                , systemArchitecture));
        System.out.println(String.format("Current Browser : {%s} \n"
                , browser));

        if(useRemoteWebDriver){
            URL seleniumGridURL = new URL(System.getProperty("gridURL"));
            System.out.println("seleniumGridURL = " + seleniumGridURL);
            String desiredBrowserVersion = System.getProperty("desiredBrowserVersion");
            String desiredPlatform = System.getProperty("desiredPlatform");

            if((null != desiredPlatform) && desiredPlatform.isEmpty()){
                desiredCapabilities.setPlatform(Platform.valueOf(desiredPlatform));
            }

            if (null != desiredBrowserVersion && desiredBrowserVersion.isEmpty()) {
                desiredCapabilities.setVersion(desiredBrowserVersion);
            }
            driver = new RemoteWebDriver(seleniumGridURL,desiredCapabilities);

        }else {
            driver = selectedDriverType.getWebDriverObject(desiredCapabilities);
        }
    }

    public void quitDriver(){
        if(null != driver){
            driver.quit();
            driver=null;
        }
    }


}
