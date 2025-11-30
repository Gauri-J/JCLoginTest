package org.example.pageObj;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class VerifyOTPPage extends AndroidActions {
    AndroidDriver driver;
    By verifyOTPTitleLocator = By.xpath("//android.widget.TextView[@resource-id=\"Verify number\"]");
    public VerifyOTPPage(AndroidDriver driver){
        super(driver);
        this.driver =  driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

        waitUntilScreenAppears(driver, verifyOTPTitleLocator, "text", "Verify number");
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"Verify number\"]")
    private WebElement verifyOTPTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"We have sent the OTP to \"]")
    private WebElement subtext1;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
    private WebElement otpField1;

    @AndroidFindBy(className = "android.widget.EditText")
    private List<WebElement> otpFields;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"Invalid OTP\"]")
    private WebElement errorInvalidOtp;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Back\"]/com.horcrux.svg.SvgView")
    private WebElement backBtn;

    public void readAndEnterOTP(){
        String otp = readOTPFromMsg();
    }

    public boolean verifyOTPTitleTestIsDisplayed(){
        return verifyOTPTitle.isDisplayed();
    }

    public String getTextOfVerifyOTPSubtext1(){
        return subtext1.getText();
    }

    public void enterOTP(String otp){
        char[] otpCharacters = otp.toCharArray();
//        for(int i=0; i<otp.length(); i++){
//            char otpChar = otpCharacters[i];
//            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(" + i + ")"))
//                    .sendKeys(String.valueOf(otpChar));
//
//        }
        for(int i=0; i<otp.length(); i++){
            otpFields.get(i).sendKeys(String.valueOf(otpCharacters[i]));
        }
    }

    public boolean verifyOTPErrorIsDisplayed(){
        return errorInvalidOtp.isDisplayed();
    }

    public void clickOnBackBtn(){
        backBtn.click();
    }

    public void waitForOtpToAutofill() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver1 -> {
            WebElement otpField = driver1.findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.EditText\").instance(0)"));
            String value = otpField.getText();
            return value != null && !value.isEmpty(); // return true once field is filled
        });
    }

}
