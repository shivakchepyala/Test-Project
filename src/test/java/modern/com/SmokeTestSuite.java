package modern.com;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.Random;

/**
 * Created by Shiva Chepyala on 09/06/2015.
 */
public class SmokeTestSuite extends BaseTest {
    //Test Data
    String ValidEmailID="testUser" + new Random().nextInt() + "@email.com";
    String InVlidEmailID="invalidMail.com";
    int productQuantity=3;

    AutomationLibrary blib=new AutomationLibrary();
    //Test-1 Verify user can subscribe with a valid Email ID
    @Test
    public void validEmailVerification()
    {
        blib.emailSubscription(ValidEmailID);
        Assert.assertEquals("Email address subscribed", driver.findElement(By.cssSelector(".newsletter-signup-successmsg")).getText());
    }

    //Test-2 Verify user can not subscribe with invalid Email ID
    @Test
    public void invalidEmailVerification()
    {
        blib.emailSubscription(InVlidEmailID);
        Assert.assertEquals("Invalid email address", driver.findElement(By.cssSelector(".newsletter-signup-errormsg")).getText());
    }

    //Test-3 Verify user can not subscribe with invalid Email ID
    @Test
    public void blankEmailVerification()
    {
        blib.emailSubscription("");
        Assert.assertEquals("Enter an email address",driver.findElement(By.cssSelector(".newsletter-signup-errormsg")).getText());

    }

    //Test-4 Verify the Sort Order
    @Test
    public void sortByLowToHigh() {
        blib.goToSofasProductcatalougue();
        Assert.assertTrue(blib.sortOrder("LowToHigh"));
    }
    //Test-5 Verify the Sort Order
    @Test
    public void sortByHighToLow() {
        blib.goToSofasProductcatalougue();
        Assert.assertTrue(blib.sortOrder("HighToLow"));
    }

    //Test-6 verify the  Price Calculation
    @Test
    public void productPriceCalculation()
    {
        //Go to Sofas product catalogue
        Utils.waitForElement(By.linkText("Sofas"),10);
        driver.findElement(By.linkText("Sofas")).click();
        //Go to product page
        Utils.waitForElement(By.linkText("Madeline 3 Seater Sofa..."),10);
        driver.findElement(By.linkText("Madeline 3 Seater Sofa...")).click();
        //Add item to Shopping Basket
        Utils.waitForElement(By.id("ws-btnaddcart"),10);
        driver.findElement(By.id("ws-btnaddcart")).click();

        //Go to Shopping basket page
        Utils.waitForElement(By.cssSelector("a[title=\"checkout\"]"),10);
        driver.findElement(By.cssSelector("a[title=\"checkout\"]")).click();

        //Capture product Price
        Utils.waitForElement(By.cssSelector("span.product_price"),10);
        double productPrice=Double.valueOf(driver.findElement(By.cssSelector("span.product_price")).getText().substring(1));

        //Capture the Total Price initially
        Utils.waitForElement(By.xpath("//td[2]/span[2]"),10);
        double totalPrice=Double.valueOf(driver.findElement(By.xpath("//td[2]/span[2]")).getText().substring(1));
        Assert.assertTrue(productPrice == totalPrice);

        //change product quantity
        Utils.selectFromDropdownMenu(By.name("quantity"), Integer.toString(productQuantity));
        Assert.assertTrue(Utils.closeAlertAndGetItsText().contains("You are about to alter the quantity of"));

        //Capture Goods Total Price
        Utils.waitForElement(By.xpath("//td[2]/span[2]"),10);
        String GoodsTotal=driver.findElement(By.xpath("//td[2]/span[2]")).getText().substring(1);
        String result = GoodsTotal.replaceAll("[,]", "");
        double finalTotalPrice=Double.valueOf(result);

        //Verify the final total price is correct
        Assert.assertTrue(finalTotalPrice==productQuantity*productPrice);

    }
}
