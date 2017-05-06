
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

import weka.core.Stopwords;

public class AttributeSelect {
	private static ArrayList<String> TweetsList = new ArrayList<String>();
	private static ArrayList<String> lists = new ArrayList<String>();
    private static Map<String, Integer> wordsCount = new TreeMap<String,Integer>();
    private static String Place;
    private static Stopwords stopword;
    static String path = "train-tweets.txt";
    
    public static void main(String[] args) {
    	stopword = new Stopwords();
		Scanner keyboard = new Scanner(System.in);
		
		
		System.out.println("Please input your Place");		
		Place = keyboard.nextLine();
		
        int numLine = Filepreprocess.readLine(path);
    	Scanner InputStream=null;
		String[] TweetsContent = new String[4];
        try {
			InputStream=new Scanner(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        for(int i = 0; i<numLine;i++){                                                 //store the contents of the tweets into an arraylist
			if(InputStream.hasNextLine()==true){
				
    				TweetsContent=InputStream.nextLine().split("\t");
			    	if(!TweetsContent.equals("")&&Place.equals(TweetsContent[3])){
	    		    	TweetsList.add(TweetsContent[2]);
			    	}
			    	if(Place.equals("")&&!TweetsContent.equals("")){
			    		TweetsList.add(TweetsContent[2]);
			    	}
				
			}
			
    	}
        for(int i = 0;i<TweetsList.size();i++){
    		if(!TweetsList.get(i).equals("")){
    			String[] WordList = TweetsList.get(i).split(" ");
    			for(int j = 0;j<WordList.length;j++){
    				if(!stopword.is(WordList[j])){
        				lists.add(WordList[j].replaceAll("[^a-zA-Z]","").toLowerCase());
    				}
    			}
    		}
    	}
        for(String Token:lists){
    		if(wordsCount.get(Token)!=null){
    			wordsCount.put(Token,wordsCount.get(Token)+1);
    		}
    		else
    			wordsCount.put(Token, 1);
    	}
    	
    	sortMap(wordsCount);
    	    	
        
	}

    public static void sortMap(Map<String, Integer> wordCount){
		ArrayList<Map.Entry<String, Integer>> WordList = new ArrayList<Map.Entry<String, Integer>>(wordCount.entrySet());
		
		Collections.sort(WordList,new Comparator<Map.Entry<String,Integer>>(){

			@Override
			public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
				// TODO Auto-generated method stub
				return arg1.getValue().compareTo(arg0.getValue());
			}
						
		});
		
		
		PrintWriter OutputStream = null;
		
		try {
			OutputStream =new PrintWriter(new FileOutputStream("NewWordFrequency-W.txt",true));
		} catch (FileNotFoundException e) {
			                                                                // TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0;i<200;i++){
			if(i == 0){
				OutputStream.println("************************************************");
				OutputStream.println(Place);
				OutputStream.println("************************************************");
			}
			OutputStream.println(WordList.get(i).getKey()+"-------"+WordList.get(i).getValue());
		}
		OutputStream.close(); 
		System.out.println("End Writing");
		
	}
    
}
