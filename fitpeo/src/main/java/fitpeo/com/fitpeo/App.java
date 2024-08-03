package fitpeo.com.fitpeo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
    	//Set the chromedriver
    	WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		
		//Navigating to FitPeo homepage
        driver.get("https://www.fitpeo.com");
        
        //maxmizing the window
        driver.manage().window().maximize();

        // Navigating to the Revenue Calculator page
        driver.findElement(By.linkText("Revenue Calculator")).click(); 

        //To load the page
        Thread.sleep(3000);

        //Scrolling down to the slider section
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");
        
        // setting up the slider value to 820
        WebElement slider = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/div/div/span[1]/span[3]/input"));
        Thread.sleep(3000);
        Actions action = new Actions(driver);
        action.dragAndDropBy(slider,94 ,0).perform();
        
        //sending the value to tesxtbook
        WebElement value=driver.findElement(By.id(":r0:")); 
        value.clear();
        value.sendKeys("560");

        // Selecting the checkboxes for CPT codes
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[1]/label/span[1]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[2]/label/span[1]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[3]/label/span[1]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[8]/label/span[1]/input")).click();
        
        // Validating Total Recurring Reimbursement
        WebElement reimbursement = driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div/p[4]/p"));
        String reimbursementValue = reimbursement.getText();
        if ("$110700".equals(reimbursementValue)) {
            System.out.println("Total Recurring Reimbursement value is correct: " + reimbursementValue);
        } else {
            System.out.println("Total Recurring Reimbursement value is incorrect: " + reimbursementValue);
        }
        //To close the browser
        driver.quit();
    }
}

