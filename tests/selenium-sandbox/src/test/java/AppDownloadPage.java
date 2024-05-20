import org.openqa.selenium.WebDriver;

class AppDownloadPage extends BasePage {
    public AppDownloadPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://9anime.pe/app-download");
    }
}
