import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import java.net.URL;
import java.net.MalformedURLException;

public class NineAnimeTest {
    private WebDriver driver;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
    }

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    @Test
    public void loadTest() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.getBodyText().contains("Recently Updated"));
    }
}
