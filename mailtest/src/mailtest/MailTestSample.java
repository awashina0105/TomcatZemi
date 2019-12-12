package mailtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailTestSample {

	  public static void main(String args[]) throws InterruptedException {
	    // Optional. If not specified, WebDriver searches the PATH for chromedriver.
	    System.setProperty("webdriver.chrome.driver", "C:/S3A1_07/pleiades/tomcat/8/lib/chromedriver_win32/chromedriver.exe");

	    WebDriver driver = new ChromeDriver();
	    WebDriverWait wait = new WebDriverWait(driver, 10);

	    driver.get("http://mail.google.com/mail/?logout&hl=ja");


	    WebElement username = driver.findElement(By.id("identifierId"));
	    Thread.sleep(1000);
	    username.sendKeys("tomcatzemi2019@gmail.com");

	    WebElement identifierNext = wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierNext")));
	    Thread.sleep(1000);
	    identifierNext.click();


	    WebElement userpass = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
	    Thread.sleep(1000);
	    userpass.sendKeys("2019tomcat");

	    WebElement passwordNext = wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
	    Thread.sleep(1000);
	    passwordNext.click();

	    Thread.sleep(1000);
	    driver.navigate().to("https://mail.google.com/mail/u/0/h/1dqm3ngtw4eri/");

	    Thread.sleep(1000);
	    driver.navigate().to("https://mail.google.com/mail/u/0/h/h21ly78qi5cp/?&cs=b&pv=tl&v=b");

	    Thread.sleep(1000);
	    WebElement mailTo = wait.until(ExpectedConditions.elementToBeClickable(By.name("to")));
	    mailTo.sendKeys("suzuneandmisato1129@gmail.com");

	    Thread.sleep(1000);
	    WebElement subject = wait.until(ExpectedConditions.elementToBeClickable(By.name("subject")));
	    subject.sendKeys("test");

	    Thread.sleep(1000);
	    WebElement body = wait.until(ExpectedConditions.elementToBeClickable(By.name("body")));
	    body.sendKeys("test");

	    Thread.sleep(1000);
	    WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.name("nvp_bu_send")));
	    next.click();

	  }


}
