package createFile;

import java.io.FileWriter;
import java.io.IOException;

public class Create {
	
	private FileWriter fw ;
	
	public boolean fileCreate(String userName){
		
		CreateRandom random = new CreateRandom();
		int randomId = random.random();

		
		try{
			
			fw = new FileWriter("./" + userName + "/" + randomId + ".html");
			
			
			
		}catch (IOException e) {
			// TODO: handle exception
		}
		
		
		return false;
		
	}

}
