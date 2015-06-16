package modern.com;

import org.junit.After;
import org.junit.Before;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Shiva Chepyala on 09/06/2015.
 */
public class BaseTest extends BaseClass{
    String Browser="Chrome";

    @Before
    public void startBrowser() throws MalformedURLException, InterruptedException {
        //Select the Browser
        BrowserFactory.StartBrowser(Browser);
        driver.get("http://www.modern.co.uk/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void stopBrowser() {
        driver.quit();
    }
}
