package ru.netology.qa;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import ru.netology.qa.screens.MainScreen;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.IOSMobileCapabilityType.*;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

@TestInstance(Lifecycle.PER_CLASS)
public class AppTest {

    private AppiumDriver driver;

    @BeforeEach
    public void createDriver() throws MalformedURLException {

    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability(PLATFORM_NAME, "android");
    desiredCapabilities.setCapability(DEVICE_NAME, "any name");
    desiredCapabilities.setCapability(APP_PACKAGE, "ru.netology.testing.uiautomator");
    desiredCapabilities.setCapability(APP_ACTIVITY, "ru.netology.testing.uiautomator.MainActivity");

    driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
    }

    @Test
    public void testInputEmptyString() {
        MainScreen mainScreen = new MainScreen(driver);
        String textBefore = mainScreen.textField.getText();
        mainScreen.input.click();
        mainScreen.input.sendKeys("     ");
        mainScreen.buttonChangeText.click();
        String textAfter= mainScreen.textField.getText();
        Assertions.assertEquals(textAfter, textBefore);
    }

    @Test
    public void testViewNewActivity() {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.input.click();
        mainScreen.input.sendKeys("New Activity Test");
        mainScreen.buttonOpenText.click();
        mainScreen.textNewActivity.isDisplayed();
        Assertions.assertEquals("New Activity Test", mainScreen.textNewActivity.getText());
    }

    @AfterAll
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
