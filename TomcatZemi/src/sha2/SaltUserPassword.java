package sha2;

import bean.LoginBean;
import dao.LoginDAO;

public class SaltUserPassword {

	LoginBean lbean = new LoginBean();
	LoginDAO dao = new LoginDAO();





    public String getDigest(String before1, String before2, String before3) {

    	String SALT = before3;

        String after = null;
        ToSHA2 toSha2 = new ToSHA2();

        String con = SALT + toSha2.getDigest(before1) + toSha2.getDigest(before2);

        //SHA256でハッシュ
        after = toSha2.getDigest(con);


        return after;
    }
    
    public String getDigest(String answer, String salt){
    	
    	ToSHA2 toSha2 = new ToSHA2();
    	
    	String con = salt + toSha2.getDigest(answer);
    	
    	String after = toSha2.getDigest(con);
    	
    	return after;
    	
    }

    public static void main (String args[]){

//    	CreateSalt cs = new CreateSalt();
//    	String salt = cs.createSalt();
//    	System.out.println("salt:  " + salt);
    	SaltUserPassword su = new SaltUserPassword();

    	//System.out.println("password:  " +su.getDigest("186058","password", salt ));
    	
    	System.out.println(su.getDigest("", ""));


    }

}
