package sendmail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailSend {

	private static final String USER_NAME = "tomcatzemi2019@gmail.com";
	private static final String USER_PASS = "2019tomcat";
	private static final long SLEEP = 1000;

	private WebDriver driver;
	private WebDriverWait wait;

	public MailSend(){
		//ドライバ名、およびドライバへのパス
		System.setProperty("webdriver.chrome.driver", "C:/S3A1_07/pleiades/tomcat/8/lib/chromedriver_win32/chromedriver.exe");

		//インスタンス化
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
	}



	public boolean mailToSend(String toMail, String toSabject, String toBody){

		boolean isSend = true;

		try {
			//Webブラウザ起動　一度ログアウト処理をかませた後、ログイン画面を表示させる
			driver.get("http://mail.google.com/mail/?logout&hl=ja");

			//ログイン処理①　メールアドレスの入力
			//WebElementでメールアドレスの入力に使うテキストボックスを探し、入力させる。
			WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierId")));
			username.sendKeys(USER_NAME);
			Thread.sleep(SLEEP);


			//次へボタンをクリックさせる
			WebElement identifierNext = wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierNext")));
			Thread.sleep(SLEEP);
			identifierNext.click();

			//パスワード入力
			WebElement userpass = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
			Thread.sleep(SLEEP);
			userpass.sendKeys(USER_PASS);

			//次へボタンをクリックさせる　IDが異なることに注意
			WebElement passwordNext = wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
			Thread.sleep(SLEEP);
			passwordNext.click();

			//簡易HTMLのGmailページに強制的に遷移
			//これをしないとこの後の操作がしづらいため
			Thread.sleep(SLEEP);
			driver.navigate().to("https://mail.google.com/mail/u/0/h/1dqm3ngtw4eri/");

			//メール送信画面へ遷移
			Thread.sleep(SLEEP);
			driver.navigate().to("https://mail.google.com/mail/u/0/h/h21ly78qi5cp/?&cs=b&pv=tl&v=b");

			//宛先入力
			Thread.sleep(SLEEP);
			WebElement mailTo = wait.until(ExpectedConditions.elementToBeClickable(By.name("to")));
			mailTo.sendKeys(toMail);

			//件名入力
			Thread.sleep(SLEEP);
			WebElement subject = wait.until(ExpectedConditions.elementToBeClickable(By.name("subject")));
			subject.sendKeys(toSabject);

			//本文入力
			Thread.sleep(SLEEP);
			WebElement body = wait.until(ExpectedConditions.elementToBeClickable(By.name("body")));
			body.sendKeys(toBody);

			//送信ボタン押下
			Thread.sleep(SLEEP);
			WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.name("nvp_bu_send")));
			next.click();

			//Webブラウザを閉じる
			Thread.sleep(SLEEP);
			driver.quit();


		}catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();

			isSend = false;

		}catch (NoSuchElementException e) {
			// TODO: handle exception
			e.printStackTrace();

			isSend = false;

		}

		return isSend;
	}

	public boolean mailBccSend(String toMail[], String toSabject, String toBody){

		boolean isSend = true;

		try {
			//Webブラウザ起動　一度ログアウト処理をかませた後、ログイン画面を表示させる
			driver.get("http://mail.google.com/mail/?logout&hl=ja");

			//ログイン処理①　メールアドレスの入力
			//WebElementでメールアドレスの入力に使うテキストボックスを探し、入力させる。
			WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierId")));
			username.sendKeys(USER_NAME);
			Thread.sleep(SLEEP);


			//次へボタンをクリックさせる
			WebElement identifierNext = wait.until(ExpectedConditions.elementToBeClickable(By.id("identifierNext")));
			Thread.sleep(SLEEP);
			identifierNext.click();

			//パスワード入力
			WebElement userpass = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
			Thread.sleep(SLEEP);
			userpass.sendKeys(USER_PASS);

			//次へボタンをクリックさせる　IDが異なることに注意
			WebElement passwordNext = wait.until(ExpectedConditions.elementToBeClickable(By.id("passwordNext")));
			Thread.sleep(SLEEP);
			passwordNext.click();

			//簡易HTMLのGmailページに強制的に遷移
			//これをしないとこの後の操作がしづらいため
			Thread.sleep(SLEEP);
			driver.navigate().to("https://mail.google.com/mail/u/0/h/1dqm3ngtw4eri/");

			//メール送信画面へ遷移
			Thread.sleep(SLEEP);
			driver.navigate().to("https://mail.google.com/mail/u/0/h/h21ly78qi5cp/?&cs=b&pv=tl&v=b");

			//宛先入力
			Thread.sleep(SLEEP);
			WebElement mailBcc = wait.until(ExpectedConditions.elementToBeClickable(By.name("bcc")));
			for(String mailTo : toMail){
				mailBcc.sendKeys(mailTo + ",");
			}

			//件名入力
			Thread.sleep(SLEEP);
			WebElement subject = wait.until(ExpectedConditions.elementToBeClickable(By.name("subject")));
			subject.sendKeys(toSabject);

			//本文入力
			Thread.sleep(SLEEP);
			WebElement body = wait.until(ExpectedConditions.elementToBeClickable(By.name("body")));
			body.sendKeys(toBody);

			//送信ボタン押下
			Thread.sleep(SLEEP);
			WebElement next = wait.until(ExpectedConditions.elementToBeClickable(By.name("nvp_bu_send")));
			next.click();

			//Webブラウザを閉じる
			Thread.sleep(SLEEP);
			driver.quit();


		}catch (InterruptedException e) {
			// TODO: handle exception
			e.printStackTrace();

			isSend = false;

		}catch (NoSuchElementException e) {
			// TODO: handle exception
			e.printStackTrace();

			isSend = false;

		}

		return isSend;
	}

}
