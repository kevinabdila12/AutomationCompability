package test1;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	WebDriver driver;

	@Parameters({"browser"})
	@BeforeMethod
	public void setUp(String browserName) {

		System.out.println("browser name is : " + browserName);
		
		MutableCapabilities sauceOpts = new MutableCapabilities();
		sauceOpts.setCapability("build", "selenium-build-56UD4");
		sauceOpts.setCapability("seleniumVersion", "4.9.0");
	 	sauceOpts.setCapability("username", "oauth-kevinabdillah42-9274d");
		sauceOpts.setCapability("accessKey", "17bcad20-8a31-41ae-a809-efa7e2b6b60d");
		sauceOpts.setCapability("tags", "BISMILLAH BISA");

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("sauce:options", sauceOpts);
		cap.setCapability("browserVersion", "98");
		cap.setCapability("platformName", "windows 8.1");

		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			cap.setCapability("browserName", "chrome");
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			cap.setCapability("browserName", "edge");
		}else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			cap.setCapability("browserName", "firefox");
		}
		
		try {
			driver = new RemoteWebDriver(new URL("https://ondemand.apac-southeast-1.saucelabs.com:443/wd/hub"), cap);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

}