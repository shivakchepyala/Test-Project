package modern.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;

/**
 * Created by Shiva Chepyala on 09/06/2015.
 */
public class BrowserFactory extends BaseClass {

    //Launching selected browser
    public static WebDriver StartBrowser(String Browser) throws MalformedURLException, InterruptedException {
        if (driver == null || !isSessionActive()) {
            driver = startWebBrowser(Browser);
        }
        driver.manage().window().maximize();
        return driver;
    }

   public static boolean isSessionActive() {
        try {

            return driver.findElements(By.tagName("body")).size() > 0;

        } catch (Exception e) {

        }
        return false;
    }

   protected static WebDriver startWebBrowser(String browser) {
                 try {
                if (browser.equalsIgnoreCase("Firefox")) {
                    //Open Firefox Browser
                    driver = new FirefoxDriver();
                } else if (browser.equalsIgnoreCase("Chrome")) {
                    //Open Chrome Browser
                    System.setProperty("webdriver.chrome.driver", "src/test/Browsers/chromedriver.exe");
                    driver = new ChromeDriver();

                } else if (browser.equalsIgnoreCase("IE")) {
                    //Open IE Browser
                    System.setProperty("webdriver.ie.driver", "src/test/Browsers/IEDriverServer.exe");
                    driver = new InternetExplorerDriver();
                } else if (browser.equalsIgnoreCase("Safari")) {
//                    Open Safari Browser
                    driver = new SafariDriver();
                } else {
                    throw new RuntimeException("Browser give " + browser + " did not load..");
                }
            }
            catch(Exception e)
            {
                throw new RuntimeException("Browser give " + browser + " did not load..");

            }
        return driver;
    }
}
