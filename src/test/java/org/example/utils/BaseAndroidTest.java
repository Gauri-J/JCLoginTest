package org.example.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BaseAndroidTest extends AppiumActions{
    public AppiumDriverLocalService service;
    public AndroidDriver driver;

    @BeforeClass(alwaysRun = true)
    public void configureTests() {

        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .build();

        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
//        options.setDeviceName("TestPhone");
//        options.setApp("/Users/gauri.jamsandekar/IdeaProjects/JioCloudTest/src/test/resources/SIT-flavorInternal-debugJC.apk");
        options.setUdid("4f2bc71d"); //realme
        options.setAppPackage("jio.cloud.drive");
        options.setAppActivity("com.rjil.cloud.tej.client.app.AppStartActivity");
        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        //  Avoid WRITE_SECURE_SETTINGS permission issue (need to do this for execution on real device)
        options.setDisableWindowAnimation(true);
        options.setIgnoreHiddenApiPolicyError(true);
        // ✅ Prevent Appium from clearing app data (need to do this for execution on real device as it gives SessionNotCreatedException)
        options.setNoReset(true);  //Appium won’t reset app state, won’t call pm clear.
//        options.setFullReset(true);
        options.setCapability("settingsAppLaunchTimeout", 20000);
        options.autoGrantPermissions();
        driver = new AndroidDriver(service.getUrl(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void preSetupForLaunch(){
        waitForAppReady(driver);
    }


    @AfterClass(alwaysRun = true)
    public void tearDown(){
        if(driver!=null);
        driver.quit();

        if(service!=null)
            service.stop();
    }

    public void resetToLoginScreen(String appPackage) {
        Map<String, Object> args = new HashMap<>();
        args.put("command", "pm");
        args.put("args", Arrays.asList("clear", appPackage)); // your app package name
        driver.executeScript("mobile: shell", args);
        //adb shell pm clear jio.cloud.drive
        // Relaunch the app
        driver.activateApp(appPackage);
    }


}
