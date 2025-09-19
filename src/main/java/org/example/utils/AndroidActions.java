package org.example.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AndroidActions extends AppiumActions{
    AndroidDriver driver;
    public AndroidActions(AndroidDriver driver){
        this.driver =  driver;
    }

    public void closeKeyboardIfOpen() {
        if (driver.isKeyboardShown()) {
            try {
                driver.hideKeyboard();
            } catch (Exception e) {
                // fallback if hideKeyboard fails
                driver.pressKey(new KeyEvent(AndroidKey.BACK));
            }
        }
    }

    public String readOTPFromMsg(){
        // Open notifications
        driver.openNotifications();
        // Wait for OTP notification
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement otpNotification = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//android.widget.TextView[contains(@text,'OTP')]")
                )
        );
        // Extract OTP from text
        String otpText = otpNotification.getText();
        String otp = otpText.replaceAll("\\D+", "");  // keeps only digits
        System.out.println("OTP is:" + otp);
        return otp;
    }

    public void handlePermissionDialogs(By dialogLocator, By allowBtnLocator) {
        try {
            List<WebElement> dialogs = driver.findElements(dialogLocator);
            if (!dialogs.isEmpty() && dialogs.get(0).isDisplayed()) {
                List<WebElement> allowButtons = driver.findElements(allowBtnLocator);
                if (!allowButtons.isEmpty() && allowButtons.get(0).isDisplayed()) {
                    allowButtons.get(0).click();
                    System.out.println("Permission granted.");
                    return;
                }
            }
            System.out.println("No permission dialog displayed. Continuing test...");
        } catch (Exception e) {
            System.out.println("No permission dialog detected or error occurred: " + e.getMessage());
        }
    }

}
