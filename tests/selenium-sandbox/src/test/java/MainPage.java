import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;


class MainPage extends PageBase {
    // private By footerBy = By.className("footer-block");
    // private By searchBarTogglerBy = By.xpath("//a[@class='search-bar-toggler']/i");
    // private By searchBarBy = By.name("search");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://9anime.pe/home");
    }

    // public String getFooterText() {
        // return this.waitAndReturnElement(footerBy).getText();
    // }

    // public SearchResultPage search(String searchQuery) {
        // this.waitAndReturnElement(searchBarTogglerBy).click();

        // this.waitAndReturnElement(searchBarBy).sendKeys(searchQuery + "\n");
        // return new SearchResultPage(this.driver);
    // }
}
