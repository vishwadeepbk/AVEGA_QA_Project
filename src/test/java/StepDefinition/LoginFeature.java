package StepDefinition;


import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class LoginFeature {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Given("User launches the Chrome browser")
    public void user_launches_browser() {

    }

    @Given("User navigates to OrangeHRM login page")
    public void user_navigates_to_login_page() {

        driver.get("https://opensource-demo.orangehrmlive.com");
    }

    @When("User enters valid username {string}")
    public void enter_username(String username) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("username"))).sendKeys(username);

    }

    @When("User enters valid password {string}")
    public void enter_password(String password) {

        driver.findElement(By.name("password")).sendKeys(password);
    }

    @When("User clicks on Login button")
    public void click_login_button() {

        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("User should be redirected to Dashboard")
    public void verify_dashboard() {

        WebElement dashboard = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h6[text()='Dashboard']")));

        Assert.assertEquals("Dashboard", dashboard.getText());
    }

    @When("User logs out")
    public void logout() {

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[@class='oxd-userdropdown-name']"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Logout"))).click();
    }

    @Then("User should be redirected to Login page")
    public void verify_login_page() {

        WebElement login = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("username")));

        Assert.assertTrue(login.isDisplayed());
    }

    @Then("User should see {string} message")
    public void invalid_credentials(String message) {

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//p[contains(@class,'oxd-alert-content-text')]")));

        Assert.assertEquals(message, error.getText());
    }

    @After
    public void tearDown() {

        driver.quit();
    }
}