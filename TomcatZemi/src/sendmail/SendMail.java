package sendmail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SendMail {
	//日本語用メールのエンコード
	//UTF-8よりもISO-2022-JPがおすすめ
	private static final String ENCODE = "ISO-2022-JP";
	//送信用メールアドレス
	private static final String FROM_MAIL = "TomcatZemi2019@gmail.com";
	//メール送信時のユーザ名及びパスワード
	private static final String USER_NAME = "username";
	private static final String USER_ID = "userid";
	private static final String PASS = "password";

	public void tosend(String tomail, String toUserName, String text){
		final Properties props = new Properties();

		//基本情報。fakeSMTPを使用しているので、ホストはlocalhostを使用
		//SMTPSを使用したいときはポート番号は465を使用
		props.setProperty("mail.smtp.host", "localhost");
		props.setProperty("mail.smtp.port", "25");

		//タイムアウトの設定
		props.setProperty("mail.smtp.connectiontimeout", "60000");
		props.setProperty("mail.smtp.timeout", "60000");

		//認証
		props.setProperty("mail.smtp.auth", "true");

		// SSLを使用するとこはこの設定が必要。
		//        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		//        props.setProperty("mail.smtp.socketFactory.fallback", "false");
		//        props.setProperty("mail.smtp.socketFactory.port", "465");

		//propsに設定した情報を使用して、sessionの作成
		final Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_ID, PASS);
			}
		});

		//メッセージ内容の設定
		final MimeMessage message = new MimeMessage(session);

		try{
			final Address FROM = new InternetAddress(FROM_MAIL, USER_NAME, ENCODE);

			message.setFrom(FROM);

			final Address TO = new InternetAddress(tomail, toUserName, ENCODE);

			//今回は一人にしか送らない
			message.setRecipient(Message.RecipientType.TO, TO);
			//件名
			message.setSubject("お知らせが更新されました", ENCODE);
			//本文
			message.setText(text ,ENCODE);
			//メールソフト名とバージョン
			//これがないと、迷惑メール扱いされる時がある
			message.addHeader("X-Mailer", "blancoMail 0.1");
			//日付情報
			message.setSentDate(new Date());

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// メール送信。
		try {
			Transport.send(message);
		} catch (AuthenticationFailedException e) {
			// 認証失敗
			e.printStackTrace();
		} catch (MessagingException e) {
			// smtpサーバへの接続失敗
			e.printStackTrace();
		}
	}


	public void tosend(String toBccMail[], String toUserName, String text){
		final Properties props = new Properties();

		//基本情報。fakeSMTPを使用しているので、ホストはlocalhostを使用
		//SMTPSを使用したいときはポート番号は465を使用
		props.setProperty("mail.smtp.host", "localhost");
		props.setProperty("mail.smtp.port", "25");

		//タイムアウトの設定
		props.setProperty("mail.smtp.connectiontimeout", "60000");
		props.setProperty("mail.smtp.timeout", "60000");

		//認証
		props.setProperty("mail.smtp.auth", "true");

		// SSLを使用するとこはこの設定が必要。
		//        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		//        props.setProperty("mail.smtp.socketFactory.fallback", "false");
		//        props.setProperty("mail.smtp.socketFactory.port", "465");

		//propsに設定した情報を使用して、sessionの作成
		final Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER_ID, PASS);
			}
		});

		//メッセージ内容の設定
		MimeMessage message = new MimeMessage(session);

		try{
			final Address FROM = new InternetAddress(FROM_MAIL, USER_NAME, ENCODE);

			message.setFrom(FROM);
			for (String toMail : toBccMail){
				message.setRecipient(Message.RecipientType.BCC, new InternetAddress(toMail));
			}

			//件名
			message.setSubject("お知らせが更新されました", ENCODE);
			//本文
			message.setText(text ,ENCODE);
			//メールソフト名とバージョン
			//これがないと、迷惑メール扱いされる時がある
			message.addHeader("X-Mailer", "blancoMail 0.1");
			//日付情報
			message.setSentDate(new Date());

		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// メール送信。
		try {
			Transport.send(message);
		} catch (AuthenticationFailedException e) {
			// 認証失敗
			e.printStackTrace();
		} catch (MessagingException e) {
			// smtpサーバへの接続失敗
			e.printStackTrace();
		}
	}
}
