package org.example.pageObj;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class VerifyEmailIdPage extends AndroidActions {
    AndroidDriver driver;
    By verifyEmailIdPageTitleLocator =  By.xpath("//android.widget.TextView[@resource-id=\"Verify email ID\"]");
    public VerifyEmailIdPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

        waitUntilScreenAppears(driver, verifyEmailIdPageTitleLocator, "text", "Verify email ID");
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"Verify email ID\"]")
    private WebElement verifyEmailIdPageTitle;

    @AndroidFindBy(className = "android.widget.EditText")
    private List<WebElement> otpFields;

    public void enterOTP(String otp){
        char[] otpCharacters = otp.toCharArray();

        for(int i=0; i<otp.length(); i++){
            otpFields.get(i).sendKeys(String.valueOf(otpCharacters[i]));
        }
    }
}
