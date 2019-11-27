package createfile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CreateHTMLFile {
	
	private static final String PASS = "Z:/git/TomcatZemi/TomcatZemi/WebContent/";
	private static final String HTML_HEAD = "";
	
	private FileWriter fw;
	private File file;
	private Calendar cal;
	private SimpleDateFormat sd;
	private CreateRandom random;
	private String today;
	
	public CreateHTMLFile(){
		//今日の日付を取得して、todayに代入しておく
		cal = Calendar.getInstance();
		sd = new SimpleDateFormat("yyyy_MM_dd");
		String toDay = sd.format(cal.getTime());
		
		random = new CreateRandom();
		
		file = new File(PASS + toDay);

		//今日の日付のディレクトリが存在するかを検索し、なければディレクトリを作成する
        if(!file.exists()){ 
            file.mkdir();
        }
	}
	
	public boolean create(String subject, String body){
		int randomId = random.random();
		
		try{
			file = new File(PASS + today + "/" + randomId + ".html");
			file.createNewFile();
			
			fw = new FileWriter(PASS + today + "/" + randomId + ".html");
			
			
		}catch (IOException e) {
			// TODO: handle exception
		}
		
		
		return file.exists();
		
	}

}
