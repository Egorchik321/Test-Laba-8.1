import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
public class TestSearch {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("appium:automationName", "uiautomator2");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("deviceName", "HUAWEI P smart");
        capabilities.setCapability("app", "C:\\Users\\kopyt\\IdeaProjects\\Lab8\\apks\\org.wikipedia.apk");
        URL remoteUrl = new URL("http://localhost:4723");
    }


    public class firstTest extends CoreTestCase {
        private MainPageObject MainPageObject;

        protected void setUp() throws Exception {
            super.setUp();
            MainPageObject = new MainPageObject(driver);

        }

        public void firstTest() {
            
            MainPageObject.waitForElementAndClick(By.xpath(
                            "//*[contains(@text,'Пропустить')]"),
                    "Невозможно нажать на поле ввода",
                    15);
            MainPageObject.waitForElementAndClick(By.xpath(
                            "//*[contains(@text,'Поиск по Википедии')]"),
                    "Невозможно нажать на поле ввода",
                    15);
            MainPageObject.waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Поиск по Википедии')]"),
                    "Кемеровский государственный университет",
                    "Невозможно найти поле ввода", 15);
            MainPageObject.waitForElementAndClick(By.xpath(
                            "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='высшее учебное заведение в Кемерове']"),
                    "Невозможно найти 'Кемеровский государственный университет'",
                    15);
            WebElement title_element = MainPageObject.waitForElementPresent(By.xpath("//*[@text='Кемеровский государственный университет']"),
                    "Невозможно найти поле ввода", 15);
            String result = title_element.getText();
            Assert.assertEquals("Найдено несовпадение в названии статьи",
                    "Хоббит", result);
        }

        @Test
        public void testSearchElement() {
            SearchPageObject SearchPageObject = new SearchPageObject(driver);

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Кемеровский государственный университет");
            SearchPageObject.waitForSearchResult("высшее учебное заведение в Кемерове");

        }

        @After
        public void tearDown() {
            driver.quit();
        }

    }
}
