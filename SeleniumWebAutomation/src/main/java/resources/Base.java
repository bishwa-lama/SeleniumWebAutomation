package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	//Class level WebDriver, Properties variable
	public WebDriver driver;
	public Properties prop;
	
	/**
	 * It initializes the WebDriver reading the properties file data.properties. Default is chrome driver. 
	 * Also, it sets the system property for the specific driver 
	 *, eg. chrome, firefox, and edge from resource package
	 * @return WebDriver
	 * @throws IOException
	 */
	public WebDriver initializeWebDriver() throws IOException {
		prop = new Properties();
		//Retrieving data.properties file from resource location of this project using fileInputStream class
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.properties");
		prop.load(fis);
		//loading browser name from property file
		String browserName = prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			//Retrieving chrome driver property from resources package
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox"))
		{
			//Retrieving firefox driver property from resources package
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("IE"))
		{
			////Retrieving edge driver property from resources package
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\src\\main\\java\\resources\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		//Implicitly waits to provide a waiting time of 10 sec between each consecutive test step/command across the entire test script. 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//Maximizing the window
		driver.manage().window().maximize();
		return driver;
	}
}
