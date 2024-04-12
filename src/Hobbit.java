import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;


public class Hobbit {
    private AppiumDriver driver;

    @Before
    public void setUp() throws  Exception {
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

    @After
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void Hobbit(){
        //Нажатие поля поиск
        waitForElementAndClick(By.xpath(
                        "//*[contains(@text,'Поиск по Википедии')]"),
                "Невозможно нажать на поле ввода",
                10 );
        //Ввод в поиск
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Поиск по Википедии')]"),
                "Хоббит, или Туда и обратно",
                "Невозможно найти поле ввода",10);
        //Нажатие на статью Хоббита
        waitForElementAndClick(By.xpath(
                        "//*[@resource-id='org.wikipedia:id/page_list_item_description' and @text='повесть английского писателя Джона Р. Р. Толкина']"),
                "Невозможно найти 'повесть английского писателя Джона Р. Р. Толкина'",
                10);
        //Нажатие на кнопку Сохранить
        waitForElementAndClick(By.xpath(
                        "//*[@resource-id='org.wikipedia:id/page_save']"),
                "Невозможно найти кнопку Сохранения",
                10);
        //Ввод названия списка Хоббит
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Название этого списка')]"),
                "Хоббит",
                "Невозможно найти поле ввода",10);
        //Нажатие на кнопку ОК
        waitForElementAndClick(By.xpath(
                        "//*[@resource-id='android:id/button1']"),
                "Невозможно нажать на кнопку ОК",
                10);
        //Нажатие на кнопку Посмотреть список
        waitForElementAndClick(By.xpath(
                        "//*[@resource-id='org.wikipedia:id/snackbar_action']"),
                "Невозможно найти",
                10);
        //Нажатие на три точки
        waitForElementAndClick(By.xpath(
                        "//*[@content-desc='Ещё']"),
                "Невозможно найти",
                10);
        //Нажатие на кнопку Удалить из списка
        waitForElementAndClick(By.xpath(
                        "//*[@resource-id='org.wikipedia:id/title' and @text='Удалить список']"),
                "Невозможно найти",
                10);
        //Нажатие на кнопку ОК
        waitForElementAndClear(By.xpath(
                        "//*[@resource-id='android:id/button1']"),
                "Невозможно найти",
                15);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(error_message + "\n");
        return  wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return  element;
    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return  element;
    }
    //Ожидание поялвения элемента и его очистка
    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by,error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
}
