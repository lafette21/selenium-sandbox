import org.openqa.selenium.WebDriver;

class UpcomingPage extends BasePage {
    public UpcomingPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://9anime.pe/upcoming");
    }
}
