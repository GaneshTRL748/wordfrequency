package wordsfrequency;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;
public class Wordcount{

	public static void main(String[] args) throws FileNotFoundException {
		Logger l= Logger.getLogger("com.api.jar");
		String temp[] = null;
		Scanner txt=new  Scanner(new File("D:\\ganesh practise\\js-Basics\\word\\frequency.txt"));
		HashMap<String,Integer> frequency=new HashMap<>();
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
				if(frequency.containsKey(word))
				{
					int count=frequency.get(word)+1;
					frequency.put(word, count);
				}
				else {
					frequency.put(word, 1);
				}
			}
			else {
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
	      l.log(Level.INFO,()->" "+value.getKey()+"-"+value.getValue());
	    });
	}
}
