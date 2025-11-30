package org.example.android_test;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.example.pageObj.LoginPage;
import org.example.pageObj.OnBoardingPage;
import org.example.pageObj.VerifyOTPPage;
import org.example.utils.BaseAndroidTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginSuccessTest extends BaseAndroidTest {

    @BeforeMethod
    public void preSetup(){
        String appPackage = "jio.cloud.drive";  // your actual app package
        driver.terminateApp(appPackage); // Stop the app
//        resetToLoginScreen(appPackage);
        preSetupForLaunch();
    }

    @Test
    //can be tested only on playstore app as login otp on mobile comes for prod only
    public void test001_MobileNumberLoginSuccess(){
        LoginPage loginPageObj = new LoginPage(driver);
        loginPageObj.isSignInPageTextVisible();
        String mobNumber = "9372755302";
        loginPageObj.enterMobileNumber(mobNumber);
        Assert.assertTrue(loginPageObj.isSignInBtnEnabled());
        loginPageObj.clickOnsignIn();
        loginPageObj.giveMsgPermissionIfAsked();

        VerifyOTPPage verifyOTPPage = new VerifyOTPPage(driver);
        Assert.assertTrue(verifyOTPPage.verifyOTPTitleTestIsDisplayed());
        Assert.assertEquals(verifyOTPPage.getTextOfVerifyOTPSubtext1(), "We have sent the OTP to ");
        verifyOTPPage.waitForOtpToAutofill();

        OnBoardingPage onBoardingPageObj = new OnBoardingPage(driver);
        onBoardingPageObj.clickOnOnboardingPageYesBtn();
        onBoardingPageObj.allowManageFilesPermission();
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        onBoardingPageObj.dismissUpdateDialog();
    }

}
