package org.example.android_test;

import org.example.pageObj.EnterpriseSignInPage;
import org.example.pageObj.LoginPage;
import org.example.pageObj.VerifyEmailIdPage;
import org.example.pageObj.VerifyOTPPage;
import org.example.utils.BaseAndroidTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JCLoginScreenTest extends BaseAndroidTest {

    @BeforeMethod
    public void preSetup(){
        String appPackage = "jio.cloud.drive";  // your actual app package
        driver.terminateApp(appPackage); // Stop the app
        driver.activateApp(appPackage); // Start the app again
        preSetupForLaunch();
    }

    @Test
    public void test001_MobileNumberLogin(){
        LoginPage loginPageObj = new LoginPage(driver);
        loginPageObj.isSignInPageTextVisible();
        String mobNumber = "9372755303";
        loginPageObj.enterMobileNumber(mobNumber);
        Assert.assertTrue(loginPageObj.isSignInBtnEnabled());
        loginPageObj.clickOnsignIn();
        loginPageObj.giveMsgPermissionIfAsked();

        VerifyOTPPage verifyOTPPage = new VerifyOTPPage(driver);

        Assert.assertTrue(verifyOTPPage.verifyOTPTitleTestIsDisplayed());
        Assert.assertEquals(verifyOTPPage.getTextOfVerifyOTPSubtext1(), "We care about your privacy and security. Let's get started.");
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
    public void test002_EnterpriseLoginInvalidEmail(){
        LoginPage loginPageObj = new LoginPage(driver);
        EnterpriseSignInPage enterpriseSignInPageObj = loginPageObj.clickEnterPriseSignIn();
        Assert.assertTrue(enterpriseSignInPageObj.verifyEnterpriseSignInTextIsDisplayed());
        enterpriseSignInPageObj.enterEmailId("gfuwg@dd.cg"); //enter invalid email
        enterpriseSignInPageObj.clickOnGenerateOTP();
        Assert.assertTrue(enterpriseSignInPageObj.invalidEmailErrorIsDisplayed());
        Assert.assertEquals(enterpriseSignInPageObj.getInvalidEmailErrorText(), "Please enter verified email.");
    }

    @Test
    public void test003_EnterpriseLoginValidEmail(){
        LoginPage loginPageObj = new LoginPage(driver);
        EnterpriseSignInPage enterpriseSignInPageObj = loginPageObj.clickEnterPriseSignIn();
        Assert.assertTrue(enterpriseSignInPageObj.verifyEnterpriseSignInTextIsDisplayed());
        enterpriseSignInPageObj.enterEmailId("jiocloudqa31@outlook.com"); //enter invalid email
        enterpriseSignInPageObj.clickOnGenerateOTP();
        VerifyEmailIdPage verifyEmailIdPageObj = new VerifyEmailIdPage(driver);
        verifyEmailIdPageObj.enterOTP("047152");//otp is not constant need to read from api
    }
}
