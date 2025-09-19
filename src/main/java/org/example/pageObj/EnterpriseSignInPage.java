package org.example.pageObj;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class EnterpriseSignInPage extends AndroidActions{
    AndroidDriver driver;
    By enterpriseSignInPageTitleLocator =  By.xpath("//android.widget.TextView[@resource-id=\"Enterprise sign in\"]");
    public EnterpriseSignInPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

        waitUntilScreenAppears(driver, enterpriseSignInPageTitleLocator, "text", "Enterprise sign in");
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"Enterprise sign in\"]")
    private WebElement enterpriseSignInPageTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"Enter your enterprise Email ID\"]")
    private WebElement enterpriseSignInText;

    @AndroidFindBy(xpath = "//android.widget.EditText")
    private WebElement enterpriseSignInEnterEmail;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Generate OTP\"]")
    private WebElement enterpriseGenerateOTPBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"Please enter verified email.\"]")
    private WebElement errorInvalidEmail;

    public boolean verifyEnterpriseSignInTextIsDisplayed(){
        return enterpriseSignInText.isDisplayed();
    }

    public void enterEmailId(String emailId){
        enterpriseSignInEnterEmail.sendKeys(emailId);
    }

    public void clickOnGenerateOTP(){
        if(enterpriseGenerateOTPBtn.isEnabled()){
            enterpriseGenerateOTPBtn.click();
        }else {
            System.out.println("Invalid email");
        }
    }

    public boolean invalidEmailErrorIsDisplayed(){
        return errorInvalidEmail.isDisplayed();
    }

    public String getInvalidEmailErrorText(){
        return errorInvalidEmail.getText();
    }
}
