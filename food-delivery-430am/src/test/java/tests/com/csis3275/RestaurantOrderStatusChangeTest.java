package tests.com.csis3275;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class RestaurantOrderStatusChangeTest {
	
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
		System.setProperty("webdriver.chrome.driver", "c:/temp/chromedriver.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void restaurantOrderStatusChange() {
    driver.get("http://localhost:8080/food-delivery-430am/");
    driver.manage().window().setSize(new Dimension(1512, 1040));
    driver.findElement(By.xpath("//a[contains(@href, \'restaurant/restaurant_login\')]")).click();
    driver.findElement(By.id("email")).click();
    driver.findElement(By.id("email")).sendKeys("fast@food.com");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("fastfood");
    driver.findElement(By.xpath("//button[@value=\'Submit\']")).click();
    driver.findElement(By.id("statusBtn2")).click();
    driver.findElement(By.id("statusBtn2")).click();
  }
}
