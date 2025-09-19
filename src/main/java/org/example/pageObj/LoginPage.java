package org.example.pageObj;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage extends AndroidActions {
    AndroidDriver driver;
    public LoginPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "new UiSelector().resourceId(\"Sign in\n" +
            "to JioAICloud\")")
    private WebElement signInTitleText;

    @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"We care about your privacy and security. Let's get started.\")")
    private WebElement subText;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\")")
    private WebElement enterMobNo;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Sign in\")")
    private WebElement signInBtn;

    @AndroidFindBy(accessibility = "Cancel")
    private WebElement closePhoneNumberDialogBtn;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"com.android.permissioncontroller:id/content_container\"]/android.widget.LinearLayout")
    private WebElement messageDialog;

    @AndroidFindBy(id = "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")
    private WebElement allowMsgDialogBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"Enterprise Sign in\"]")
    private WebElement enterpriseSignInBtn;


    public void isSignInPageTextVisible(){
//        signInTitleText.isDisplayed();
        subText.isDisplayed();
    }
    public void enterMobileNumber(String mobNo){
        enterMobNo.click();
        closePhoneNumberDialog();
        enterMobNo.sendKeys(mobNo);
        closeKeyboardIfOpen();
    }

    public EnterpriseSignInPage clickEnterPriseSignIn(){
        closeKeyboardIfOpen();
        enterpriseSignInBtn.click();
        EnterpriseSignInPage enterpriseSignInPage = new EnterpriseSignInPage(driver);
        return enterpriseSignInPage;
    }

    public void closePhoneNumberDialog(){
        closePhoneNumberDialogBtn.click();
    }

    public boolean isSignInBtnEnabled(){
        return signInBtn.isEnabled();
    }

    public void clickOnsignIn(){
        signInBtn.click();
    }

    public void giveMsgPermissionIfAsked(){
        //need to pass locators here, (Webelements gets initialized in constructor when dialog is still not displayed on ui)
        By msgDialogLocator = By.xpath("//android.widget.LinearLayout[@resource-id=\"com.android.permissioncontroller:id/content_container\"]/android.widget.LinearLayout");
        By allowBtnLocator = By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]");
        handlePermissionDialogs(msgDialogLocator, allowBtnLocator);
    }

}
