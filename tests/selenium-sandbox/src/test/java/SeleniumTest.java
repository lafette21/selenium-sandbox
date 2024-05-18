import static org.junit.Assert.*;

import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import java.net.URL;
import java.net.MalformedURLException;

public class SeleniumTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, 10);
    }

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    private void tryLogin(String username, String password) {
        driver.get("http://the-internet.herokuapp.com/login");

        this.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
        WebElement inputUser = driver.findElement(By.name("username"));
        WebElement inputPass = driver.findElement(By.name("password"));

        inputUser.sendKeys(username);
        inputPass.sendKeys(password);

        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    private WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    @Test
    public void loginFailTest() {
        tryLogin("wrong", "passwd");

        WebElement message = waitAndReturnElement(By.id("flash"));
        assertTrue(message.getText().contains("Your username is invalid!"));
    }

    @Test
    public void loginSuccessTest() {
        tryLogin("tomsmith", "SuperSecretPassword!");

        WebElement message = waitAndReturnElement(By.id("flash"));
        assertTrue(message.getText().contains("You logged into a secure area!"));

        waitAndReturnElement(By.cssSelector("a[href='/logout']")).click();

        message = waitAndReturnElement(By.id("flash"));
        assertTrue(message.getText().contains("You logged out of the secure area!"));
    }
}
