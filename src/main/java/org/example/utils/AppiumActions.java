package org.example.utils;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class AppiumActions {

    public void waitUntilScreenAppears(AppiumDriver driver, By locator, String attr, String value){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(driver.findElement(locator), attr, value));
    }

    public void waitForAppReady(AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        try {
            // Wait until either Login screen or Home screen is visible
            wait.until(d -> {
                boolean loginVisible = !driver.findElements(By.id("We care about your privacy and security. Let's get started.")).isEmpty();
                boolean homeVisible = !driver.findElements(By.id("home_screen_root")).isEmpty();
                return loginVisible || homeVisible;
            });

            System.out.println("App is ready for test execution.");

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("App did not reach ready state!");
        }
    }


    public String getScreenshot(AppiumDriver driver, String testfileName) throws IOException {
        File srcfile = driver.getScreenshotAs(OutputType.FILE);
        File destnFile = new File(System.getProperty("user_dir") + "/reports/" +  testfileName + ".png");
        FileUtils.copyFile(srcfile, destnFile);
        return destnFile.getAbsolutePath();
    }

}
