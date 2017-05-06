
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Filepreprocess {
	static String AttributePath="Attributes.txt";
	static String path ="test-tweets.txt";
	
	public static int readLine(String FileName){
		Scanner InputStream = null;
		
		try {
		InputStream = new Scanner(new FileInputStream(FileName));
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	int numLine = 0;
	while (InputStream.hasNextLine()==true) {
		String temp=InputStream.nextLine();
		numLine = numLine+1;
	}
    return numLine;
		
	}
	
	public static ArrayList<String> ReadFile(){
		ArrayList<String> TweetsList=new ArrayList<String>();
		
		Scanner InputStream = null;
		String[] TweetsContent = new String[4];
		
		int numLine=readLine(path);//initialize file line;
		try {
			InputStream=new Scanner(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		for(int i = 0; i<numLine;i++){
			if (InputStream.hasNextLine()==true){
				TweetsContent=InputStream.nextLine().split("\t");
				if (!TweetsContent.equals("")){
					TweetsList.add(TweetsContent[2]);
				}
			}
		}
				
		return TweetsList ;		
		}
	
	public static ArrayList<String> ReadID(){

		ArrayList<String> TweetsID=new ArrayList<String>();
		Scanner InputStream = null;
		String[] TweetsContent = new String[4];
		
		try {
			InputStream = new Scanner(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int numLine = readLine(path);
		for (int i=0;i<numLine;i++){
			if(InputStream.hasNextLine()==true){
				TweetsContent=InputStream.nextLine().split("\t");
				if(!TweetsContent.equals("")){
					TweetsID.add(TweetsContent[1]);
				}
			}
		}
		
		return TweetsID;
		
	}
	
	public static ArrayList<String> ReadPlace(){

		 ArrayList<String> TweetsPlace=new ArrayList<String>();
		 Scanner InputStream = null;
			String[] TweetsContent = new String[4];
		 try {
				InputStream = new Scanner(new FileInputStream(path));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 int numLine=readLine(path);
		 for(int i =0;i<numLine;i++){
			 TweetsContent=InputStream.nextLine().split("\t");
			 if(!TweetsContent.equals("")){
				 TweetsPlace.add(TweetsContent[3]);
			 }
			 
		 }
		 
		return TweetsPlace;
	}
	public static ArrayList<String> ReadAttribute(){
	     ArrayList<String> Attributes = new ArrayList<String>(); 
	     Scanner InputStream = null;
	
		try{
    		InputStream=new Scanner(new FileInputStream(AttributePath));
    	}
    	catch (FileNotFoundException e){  
    		return null;
    	}

		int numLine=readLine(AttributePath);
		for(int i = 0; i<numLine;i++){
			if(InputStream.hasNextLine()==true){
				String AttributeName=InputStream.nextLine();
				Attributes.add(AttributeName);
			}
		}
		
		
		return Attributes;
	}
	
	public static void main(String[] args) {
		ArrayList<String> Attributes=ReadAttribute();
		ArrayList<String> Tweets= ReadFile();
		ArrayList<String> TweetsID=ReadID();
		ArrayList<String> TweetsPlaces=ReadPlace();
		
		ArrayList<String> ArrfTweets = new ArrayList<String>();
		int i = 0;
		while(i<Tweets.size()&&!Tweets.get(i).equals("")){
			String[] Tokens = Tweets.get(i).split(" ");
			ArrayList<String> TweetsResult = new ArrayList<String>();
			String result = "";
			int counter = 0;
			for(int j = 0;j<Attributes.size();j++){
				for(int k = 0;k<Tokens.length;k++){
					if((Attributes.get(j).toLowerCase().replaceAll("[^a-zA-Z]","")).equals(Tokens[k].toLowerCase().replaceAll("[^a-zA-Z]",""))){
						counter = counter+1;
					}
				}
				result = String.valueOf(counter);
				counter = 0;
				TweetsResult.add(result);				
			}
			String AllStatue = TweetsResult.toString().replace("[", "").replace("]", "");
			

			
//			System.out.println(TweetsID.get(i)+","+AllStatue+","+TweetsPlace.get(i));
			ArrfTweets.add(AllStatue);
			i = i+1;
//			System.out.println("???");
		}
		
		
		PrintWriter OutputStream = null;
		try {
			OutputStream =new PrintWriter(new FileOutputStream("test60.arff",true));
		} catch (FileNotFoundException e) {				                                                                // TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int x=0;x<Attributes.size();x++){
			if(x==0){
				OutputStream.println("@RELATION twitter-loc-mybest50");
				OutputStream.println("@ATTRIBUTE"+" id"+" "+"NUMERIC");
			}
			OutputStream.println("@ATTRIBUTE"+" "+Attributes.get(x)+" "+"NUMERIC");
		}
		OutputStream.println("@ATTRIBUTE location {B,H,SD,Se,W}");
		
		for(int k = 0;k<ArrfTweets.size();k++){
			if(k==0){
				OutputStream.println("@DATA");
			}
			OutputStream.println(TweetsID.get(k)+","+ArrfTweets.get(k).replace(" ", "")+","+TweetsPlaces.get(k));
		}
		OutputStream.close();
		System.out.println("arffFile Ready!");
		
	}

		
	
		
	
}