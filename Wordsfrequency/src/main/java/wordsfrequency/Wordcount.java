package wordsfrequency;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.Scanner;
public class Wordcount{
	static PrintStream out=new PrintStream(new FileOutputStream(FileDescriptor.out));
	    static HashMap<String,Integer> frequency=new HashMap<>();
	    static  String[] temp = null;
	    public static void insertword(String word)
	    {
	    	if(frequency.containsKey(word))
			{
				int count=frequency.get(word)+1;
				frequency.put(word, count);
			}
			else {
				frequency.put(word, 1);
			}
	    }
	    public static void insertwords()
	    {
	    	for(String i:temp)
			{
				if(frequency.containsKey(i))
				{
					int count=frequency.get(i)+1;
					frequency.put(i, count);
				}
				else {
					frequency.put(i, 1);
				}
			}
			temp=null;
	    }
	    public static void main(String[] args) throws FileNotFoundException {
		Logger l= Logger.getLogger("com.api.jar");
		Scanner txt=new  Scanner(new File("\\word\\frequency.txt"));
		while(txt.hasNext())
		{
			String word=txt.next();
			if(word.contains("."))
			{
			      word=word.substring(0,word.length()-1);
			}
			if(word.contains(","))
			{
				temp=word.split(",");
			}
			if(temp==null)
			{
				insertword(word);
			}
			else {
				insertwords();
			}
		}
		txt.close();
		List<Entry<String,Integer>> list=new ArrayList<>(frequency.entrySet()); 
		Collections.sort(list,new Comparator <Entry<String,Integer>>() {
			@Override
			public int compare(Entry<String,Integer> a1,Entry<String,Integer> a2 ) {
				if(a1.getValue()>a2.getValue())
				{
					return -1;
				}
				else if(a1.getValue()<a2.getValue()){
					return 1;
				}
				else {
					return 0;
				}
			}
		});
	    list.forEach(value->{
	      out.println(value.getKey()+"-"+value.getValue());
	    });
	}
}
