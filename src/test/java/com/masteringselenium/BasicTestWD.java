package com.masteringselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by hemantojha on 09/07/16.
 */
public class BasicTestWD extends DriverFactory{

    private void googleExampleThatSearchesFor(final String searchString) throws Exception {

        WebDriver driver = DriverFactory.getDriver();

        driver.get("http://google.com");

        WebElement searchField = driver.findElement(By.name("q"));

        searchField.clear();

        searchField.sendKeys(searchString);

        System.out.println("Page title is : " + driver.getTitle());

        searchField.submit();

        (new WebDriverWait(driver, 10)).until(new
              ExpectedCondition<Boolean>() {
                  public Boolean apply(WebDriver driverObject) {
                      return driverObject.getTitle().toLowerCase()
                              .startsWith(searchString.toLowerCase());
                  }
              });

        System.out.println("Page title is : " + driver.getTitle());
    }

    @Test
    public void googleMilkExample() throws Exception {
        googleExampleThatSearchesFor("Milk!!");
    }

    @Test
    public void googleCheezeExample() throws Exception {
        googleExampleThatSearchesFor("Cheese!!");
    }

    @Test
    public void googleButterExample() throws Exception {
        googleExampleThatSearchesFor("Butter!!");
    }

    @Test
    public void googleClickHindiExample() throws Exception {

        final String searchString = "Hindi";

        final WebDriver driver = DriverFactory.getDriver();
        driver.get("http://google.com");

        List<WebElement> languages = driver.findElement(By.id("als")).findElements(By.tagName("a"));
        languages.get(0).click();

        (new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver driverObject){
                List<WebElement> languages = driverObject.findElement(By.id("als")).findElements(By.tagName("a"));
                return languages.get(0).getText().equalsIgnoreCase("English");
            }
        });


        WebElement searchField = driver.findElement(By.name("q"));
        searchField.clear();
        searchField.sendKeys(searchString);
        System.out.println("Page title is : " + driver.getTitle());
        searchField.submit();

        (new WebDriverWait(driver, 10)).until(new
              ExpectedCondition<Boolean>() {
                  public Boolean apply(WebDriver driverObject) {
                      return driverObject.getTitle().toLowerCase()
                              .startsWith(searchString.toLowerCase());
                  }
              });

        System.out.println("Page title is : " + driver.getTitle());
    }

}
