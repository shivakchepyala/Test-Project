package modern.com;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Shiva Chepyala on 09/06/2015.
 */
public class Utils extends BaseClass{
    public static boolean acceptNextAlert = true;



    public static void waitForElement(By by,int waitTime)
    {
        WebElement dynamicElement = (new WebDriverWait(driver, waitTime))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public static void selectFromDropdownMenu(By by,String text)
    {
        Select sel = new Select(driver.findElement(by));
        sel.selectByVisibleText(text);
    }
    public static boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    public static String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
