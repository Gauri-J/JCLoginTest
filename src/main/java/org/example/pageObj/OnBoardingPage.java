package org.example.pageObj;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OnBoardingPage extends AndroidActions {
    AndroidDriver driver;
    public OnBoardingPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        By yesButtonLocator = By.xpath("//android.widget.Button[@content-desc=\"Yes, keep my files safe\"]");
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        waitUntilScreenAppears(driver, yesButtonLocator, "content-desc", "Yes, keep my files safe");
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView")
    private WebElement onBoardingImage;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Yes, keep my files safe\"]")
    private WebElement onBoardingYesBtn;

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Dismiss update dialog\"]")
    private WebElement disMissUpdateDialogBtn;

    public void clickOnOnboardingPageYesBtn(){
        onBoardingYesBtn.click();
    }

    public void allowManageFilesPermission() {
        try {
            // Locate the toggle switch in Settings
            By toggleSwitch = By.id("android:id/switch_widget");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement switchBtn = wait.until(ExpectedConditions.elementToBeClickable(toggleSwitch));

            if (switchBtn.getAttribute("checked").equals("false")) {
                switchBtn.click(); // Turn ON
                System.out.println("Manage Files access granted.");
            } else {
                System.out.println("Already granted.");
            }
        } catch (Exception e) {
            System.out.println("No Manage Files permission screen displayed: " + e.getMessage());
        }
    }

    public void dismissUpdateDialog(){
        disMissUpdateDialogBtn.click();
    }

}
