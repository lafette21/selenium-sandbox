import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;


class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected By userBtnBy = By.xpath("//div[@id='user-slot']");

    private By loginBtnBy = userBtnBy.xpath("//a[contains(@class, 'btn-login')]");
    private By loginFormBy = By.xpath("//form[@id='login-form']//ancestor::div[@class='modal-body']");
    private By logoutBtnBy = userBtnBy.xpath("//a[@href='/logout']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    public String getBodyText() {
        WebElement bodyElement = this.waitAndReturnElement(By.tagName("body"));
        return bodyElement.getText();
    }

    protected void login(String email, String password) {
        this.waitAndReturnElement(loginBtnBy).click();
        WebElement loginForm = this.waitAndReturnElement(loginFormBy);
        WebElement inputEmail = loginForm.findElement(By.id("email"));
        WebElement inputPass = loginForm.findElement(By.id("password"));

        inputEmail.sendKeys(email);
        inputPass.sendKeys(password);

        loginForm.findElement(By.id("btn-login")).click();
    }

    protected void logout() {
        this.waitAndReturnElement(userBtnBy).click();
        this.waitAndReturnElement(logoutBtnBy).click();
    }
}
