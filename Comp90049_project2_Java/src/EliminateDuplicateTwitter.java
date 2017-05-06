import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class EliminateDuplicateTwitter {

	static HashSet<String> EliminateDuplicate = new HashSet<String>();
	   
	   public static void main(String[] args) {
		   //ArrayList<String> TweetsList=new ArrayList<String>();
			
			Scanner InputStream = null;
			String TweetsContent;
			
			int numLine=Filepreprocess.readLine("train-tweets.txt");//initialize file line;
			try {
				InputStream=new Scanner(new FileInputStream("train-tweets.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
			for(int i = 0; i<numLine;i++){
				if (InputStream.hasNextLine()==true){
					TweetsContent=InputStream.nextLine();
					if (!TweetsContent.equals("")){
						EliminateDuplicate.add(TweetsContent);
					}
				}
	}
			PrintWriter OutputStream = null;
			try {
				OutputStream =new PrintWriter(new FileOutputStream("train-tweetsProcess.txt",true));
			} catch (FileNotFoundException e) {				                                                                // TODO Auto-generated catch block
				e.printStackTrace();
			}  
			for(Iterator<String> it = EliminateDuplicate.iterator();it.hasNext();){
				OutputStream.println(it.next());
			}
			
	   }
	   }


