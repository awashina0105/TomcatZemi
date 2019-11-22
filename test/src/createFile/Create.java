package createFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Create {

	private static FileWriter fw ;
	private static final String PASS ="Z:/git/TomcatZemi/test/WebContent/";

	public static void main (String args[]) {

		CreateRandom random = new CreateRandom();
		int randomId = random.random();

		System.out.println(randomId);

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy_MM_dd");
		String toDay = sd.format(cal.getTime());

		File file = new File(PASS + toDay);



        if(file.exists()){
            System.out.println("ファイルは存在します");
        }else{
            System.out.println("ファイルは存在しません");
            System.out.println("ファイルを作成します");
            file.mkdir();
        }

		try{
			file = new File(PASS + toDay + "/" + randomId + ".html");
			file.createNewFile();

			fw = new FileWriter(PASS + toDay + "/" + randomId + ".html");

			fw.write("テスト");
            fw.close();

		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}



		if(file.exists()){
            System.out.println("ファイルは存在します");
        }else{
            System.out.println("ファイルは存在しません");
        }


	}

}
