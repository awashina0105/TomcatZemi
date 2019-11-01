package salt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import sha2.ToSHA2;

public class CreateSalt {

	public String createSalt(){
		String salt = "";
		SecureRandom random;
		ToSHA2 sha2 = new ToSHA2();

		try {
			random = SecureRandom.getInstance("SHA1PRNG");

			String notSalt = String.valueOf(random.nextInt()) + String.valueOf(random.nextInt());
			salt = sha2.getDigest(notSalt);

		}catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
		}


		return salt;
	}

}
