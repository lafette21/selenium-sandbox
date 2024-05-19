import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Map;

public class NineAnimeTest {
    private WebDriver driver;
    private String email;
    private String password;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();
        this.email = System.getenv("NINEANIME_EMAIL");
        this.password = System.getenv("NINEANIME_PASS");

        String credFile = "";

        try {
            credFile = System.getenv("CREDENTIALS_FILE");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (credFile != null && !credFile.isEmpty()) {
            Yaml yaml = new Yaml();
            Map<String, Object> config = null;

            try (InputStream in = new FileInputStream(credFile)) {
                config = yaml.load(in);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (config != null) {
                if (config.containsKey("email") && config.containsKey("pass")) {
                    this.email = (String) config.get("email");
                    this.password = (String) config.get("pass");
                } else {
                    System.err.println("Missing 'email' or 'pass' key in the config file");
                }
            } else {
                System.err.println("Failed to load the config file");
            }
        } else {
            System.err.println("Environment variable CREDENTIALS_FILE is not set or empty");
        }
    }

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    @Test
    public void mainPageLoad() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(this.driver.getTitle().contains("9Anime"));
        Assert.assertTrue(mainPage.getBodyText().contains("Recently Updated"));
        Assert.assertTrue(mainPage.getFooterText().contains("All Rights Reserved"));
    }

    @Test
    public void login() {
        MainPage mainPage = new MainPage(this.driver);
        WebElement userBtn = mainPage.waitAndReturnElement(mainPage.userBtnBy);
        Assert.assertTrue(userBtn.getText().contains("Sign in"));
        mainPage.login(this.email, this.password);
        WebElement message = mainPage.waitAndReturnElement(By.id("toast-container"));
        Assert.assertTrue(message.getText().contains("Logged in successfully"));
        Assert.assertFalse(userBtn.getText().contains("Sign in"));
    }

    @Test
    public void searchWhileLoggedIn() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.login(this.email, this.password);
        mainPage.waitAndReturnElement(By.id("toast-container"));
        SearchResultPage searchResultPage = mainPage.search("Attack on Titan");
        Assert.assertTrue(searchResultPage.getBodyText().contains("Search result for"));
    }

    @Test
    public void logout() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.login(this.email, this.password);
        mainPage.waitAndReturnElement(By.id("toast-container"));
        mainPage.logout();
        WebElement userBtn = mainPage.waitAndReturnElement(mainPage.userBtnBy);
        Assert.assertTrue(userBtn.getText().contains("Sign in"));
    }
}
