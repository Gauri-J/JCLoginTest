package org.example.android_test;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.example.pageObj.*;
import org.example.utils.BaseAndroidTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JCLoginScreenTest extends BaseAndroidTest {

    @BeforeMethod
    public void preSetup(){
        String appPackage = "jio.cloud.drive";  // your actual app package
        driver.terminateApp(appPackage); // Stop the app
//        resetToLoginScreen(appPackage);
        preSetupForLaunch();
    }

//    @Test
//    //can be tested only on playstore app as login otp on mobile comes for prod only
//    public void test001_MobileNumberLoginSuccess(){
//        LoginPage loginPageObj = new LoginPage(driver);
//        loginPageObj.isSignInPageTextVisible();
//        String mobNumber = "9372755302";
//        loginPageObj.enterMobileNumber(mobNumber);
//        Assert.assertTrue(loginPageObj.isSignInBtnEnabled());
//        loginPageObj.clickOnsignIn();
//        loginPageObj.giveMsgPermissionIfAsked();
//
//        VerifyOTPPage verifyOTPPage = new VerifyOTPPage(driver);
//        Assert.assertTrue(verifyOTPPage.verifyOTPTitleTestIsDisplayed());
//        Assert.assertEquals(verifyOTPPage.getTextOfVerifyOTPSubtext1(), "We have sent the OTP to ");
//        verifyOTPPage.waitForOtpToAutofill();
//
//        OnBoardingPage onBoardingPageObj = new OnBoardingPage(driver);
//        onBoardingPageObj.clickOnOnboardingPageYesBtn();
//        onBoardingPageObj.allowManageFilesPermission();
//        driver.pressKey(new KeyEvent(AndroidKey.BACK));
//        onBoardingPageObj.dismissUpdateDialog();
//
//    }

    @Test
    public void test002_MobileNumberLoginInvalidOTP(){
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
        verifyOTPPage.enterOTP("123456");
        Assert.assertTrue(verifyOTPPage.verifyOTPErrorIsDisplayed());
        verifyOTPPage.clickOnBackBtn();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test003_EnterpriseLoginInvalidEmail(){
        LoginPage loginPageObj = new LoginPage(driver);
        EnterpriseSignInPage enterpriseSignInPageObj = loginPageObj.clickEnterPriseSignIn();
        Assert.assertTrue(enterpriseSignInPageObj.verifyEnterpriseSignInTextIsDisplayed());
        enterpriseSignInPageObj.enterEmailId("gfuwg@dd.cg"); //enter invalid email
        enterpriseSignInPageObj.clickOnGenerateOTP();
        Assert.assertTrue(enterpriseSignInPageObj.invalidEmailErrorIsDisplayed());
        Assert.assertEquals(enterpriseSignInPageObj.getInvalidEmailErrorText(), "Please enter verified email.");
    }

    @Test
    public void test004_EnterpriseLoginValidEmail(){
        LoginPage loginPageObj = new LoginPage(driver);
        EnterpriseSignInPage enterpriseSignInPageObj = loginPageObj.clickEnterPriseSignIn();
        Assert.assertTrue(enterpriseSignInPageObj.verifyEnterpriseSignInTextIsDisplayed());
        enterpriseSignInPageObj.enterEmailId("jiocloudqa31@outlook.com"); //enter invalid email
        enterpriseSignInPageObj.clickOnGenerateOTP();
        VerifyEmailIdPage verifyEmailIdPageObj = new VerifyEmailIdPage(driver);
        verifyEmailIdPageObj.enterOTP("047152");//otp is not constant need to read from api
    }
}
