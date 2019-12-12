package mailtest;

import mailsend.MailSend;

public class TestMail {

	public static void main(String args[]){
		MailSend send = new MailSend();

		boolean flag = send.mailToSend("suzuneandmisato1129@gmail.com", "テストです", "送信メールテスト");

		if(flag){
			System.out.println("成功しました");

		}else{
			System.out.println("失敗しました");
		}

//		String bcc[] = {"suzuneandmisato1129@gmail.com","mikageyue@gmail.com"};
//
//		boolean flagBcc = send.mailBccSend(bcc, "Bccテストです", "送信メールテスト");
//
//		if(flagBcc){
//			System.out.println("Bcc成功しました");
//
//		}else{
//			System.out.println("Bcc失敗しました");
//		}
	}

}
