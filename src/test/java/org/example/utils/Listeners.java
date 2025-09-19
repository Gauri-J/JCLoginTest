package org.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends AppiumActions implements ITestListener {

    ExtentReports report = ExtentReporter.getExtentReporter();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = report.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        AppiumDriver driver = null;
        try {
            driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        test.fail(result.getThrowable());
        try {
            test.addScreenCaptureFromPath(getScreenshot(driver, "JCLoginTest"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onStart(ITestContext context) {
        // not implemented
    }

    @Override
    public void onFinish(ITestContext context) {
        report.flush(); //need to add this to get test report
    }

}
