package createFile;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class CreateRandom {

	public int random(){

		int ram = 0;
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ram = random.nextInt();

		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return ram;
	}

}
