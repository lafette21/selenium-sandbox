import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;


class MainPage extends BasePage {
    private By footerBy = By.id("footer");
    private By searchBarBy = By.xpath("//div[@id='search']//input[@type='text']");
    private By filterAreaBy = By.xpath("//div[@class='block_area-content']");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://9anime.pe/home");
    }

    public String getFooterText() {
        return this.waitAndReturnElement(footerBy).getText();
    }

    public SearchResultPage search(String query) {
        this.waitAndReturnElement(searchBarBy).sendKeys(query + "\n");
        return new SearchResultPage(this.driver);
    }
}
