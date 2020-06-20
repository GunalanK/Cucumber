package steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Ajio {

	public ChromeDriver driver;
	public Actions action;
	
	public JavascriptExecutor js;
	@Given("Launch into Ajio Application")
	public void launch_Into_Ajio_Application() {
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.get("https://www.ajio.com/shop/sale");
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Given("Mouseover on Women, CATEGORIES and click on Kurtas")
	public void mouseover_On_Women_CATEGORIES_And_Click_On_Kurtas() throws InterruptedException {
		
		action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//a[text()='WOMEN']")).perform();
	//	action.moveToElement(driver.findElementByXPath("(//a[text()='CATEGORIES'])[2]")).perform();
		driver.findElementByXPath("(//a[text()='Kurtas'])[3]").click();

	    
	}

	@Given("Click on Brands and choose Ajio")
	public void click_On_Brands_And_Choose_Ajio() throws InterruptedException {
	   driver.findElementByXPath("//span[text()='brands']").click();
	   Thread.sleep(5000);
	   driver.findElementByXPath("//label[contains(text(),'AJIO')]").click();
	}

	@Given("Check all the results are Ajio")
	public void check_All_The_Results_Are_Ajio() {
	    List<WebElement> brand = driver.findElementsByXPath("//div[@class='brand']");
	    String brandName="";
	    for (WebElement i : brand) {
	    	brandName=i.getText();}
			if (brandName.equalsIgnoreCase("AJIO")) {
				System.out.println("All the results are Ajio brand");
				
			} else {
				System.out.println("All the results are not  Ajio brand");
			}
		}
	

	@Given("Set Sort by the result as Discount")
	public void setSortByTheResultAsDiscount() {
		WebElement sortBy =driver.findElementByXPath("//div[@class='filter-dropdown']/select");
	    Select select=new Select(sortBy);
	   select.selectByVisibleText("Discount");
		List<WebElement> brandName = driver.findElementsByXPath("//div[@class='name']");
		brandName.get(0).click();
	}

	@Given("Select the Color and click ADD TO BAG")
	public void select_The_Color_And_Click_ADD_TO_BAG() {
  
	  Set<String> windowSet = driver.getWindowHandles();
	  List<String> windowList = new ArrayList<String>(windowSet);
	  driver.switchTo().window(windowList.get(1));
	  
	  driver.findElementByXPath("//img[@title='light-grey']").click();
	  
	}



	@Given("Verify the error message Select your size to know your estimated delivery date")
	public void verify_The_Error_Message_Select_Your_Size_ToKnow_Your_Estimated_Delivery_Date() {
		
		
		
	   if ((driver.findElementByClassName("edd-pincode-msg-details").getText()).
			   equalsIgnoreCase("Select your size to know your estimated delivery date.")) {
		   System.out.println("Warning Message is dispalyed");
	} else {
		System.out.println("Warning Message is not displayed");
	}
	} 

	@Given("Select size and click ADD TO BAG")
	public void select_Size_And_Click_ADD_TO_BAG() {
		driver.findElementByXPath("(//div[text()='XS'])[1]").click();
		
		  driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
	}

	@Given("click on Enter pin-code to know estimated delivery date")
	public void click_On_Enter_PinCode_To_Know_Estimated_Delivery_Date()   {
		js =( JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,100)");
		WebElement pincode=driver.findElementByXPath("//span[contains(@class,'edd-pincode-msg-details')]");
		js.executeScript("arguments[0].click();",pincode);
	}

	@Given("Enter the pincode as 603103 and click Confirm pincode")
	public void enter_The_Pincode_As_And_Click_Confirm_Pincode() {
		js =( JavascriptExecutor)driver;
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("603103");
		WebElement confirm=driver.findElementByXPath("//button[@class='edd-pincode-modal-submit-btn']");
		js.executeScript("arguments[0].click();",confirm);
	}

	@Given("Print the message and click Go to  Bag")
	public void print_The_Message_And_Click_Go_To_Bag() {
		System.out.println("Estimated Delivery date:"+driver.findElementByXPath("//span[@class='edd-message-success-details-highlighted']").getText());
	   driver.findElementByXPath("//span[text()='GO TO BAG']").click();
	}

	@When("Click on Proceed to Shipping")
	public void click_On_Proceed_To_Shipping() {
	    driver.findElementByXPath("//button[text()='Proceed to shipping']").click();
	}

	@Then("close the browser")
	public void close_The_Browser() {
	    driver.quit();
	}
}
