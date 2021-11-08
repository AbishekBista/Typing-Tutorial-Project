import java.io.*;
import java.util.*;
import javafx.scene.control.Label;
import java.text.Normalizer;
import java.text.Normalizer.Form;
class TextGraber {
        File filepath; //just to specify the path of the file
	BufferedReader read; //to read the contents of the file
	long skip; // to 
	boolean newWords;
	String linetext="";
        String word;
        boolean specialChars=false;
	public TextGraber(File filelocation, boolean special)
	{
                specialChars=special;  
		filepath=filelocation;
		System.out.println(filepath);
		System.out.println(filepath.exists()); // if file exists, read from the file
		try
		{
			read=new BufferedReader(new FileReader(filepath));
		}
		catch(FileNotFoundException fnfe)	
		{
			System.out.println("Error: "+fnfe);
		}
		newWords=true;
	}	

	public String getLabel1()
	{
                System.out.println("Word by word");
		if(linetext.length()<=1)
		{
			newWords=true;
                        
		}
		if(newWords)
		{
                        System.out.println("New word is: "+newWords);
			linetext=getLabel2();
                        newWords=false;
                        System.out.println("New words is: "+newWords);
		}
                if(linetext==null)
                {
                    return null;
                }
                if(linetext.contains(" "))
                {
		word=linetext.substring(0,linetext.indexOf(" "));
		word=word.trim();
		linetext=linetext.substring(linetext.indexOf(" "),linetext.length());
		linetext=linetext.trim();
                }
                else
                {
                    word=linetext;
                    linetext="";
                }
                System.out.println(linetext+","+word);
                if(word!=null)
                {
                word=fix(word);
                }
                return word;
	}
 public String getLabel2()
{
	System.out.println("Line by line");
	String line="";
        try{
	try
	{
                line=read.readLine().trim();
	}
	catch(IOException iox1)
	{
		System.out.println("Error reading line in file: TextGrabber, getLabel2()");
		line=null; //whenever a null is returned to the main program the game is over
	}
        }
        catch(NullPointerException npe)
        {
            line=null;
        }
        
        System.out.println("Hello");
        if(line!=null)
        {
        line=fix(line);
        }
	return line; 
}

public ArrayList<String> getLabel3()
 {
     System.out.println("Whole file");     
    ArrayList<String> b=new ArrayList<String>();
    try
    {
        String a=read.readLine();
        b.add(a);
        while(a!=null) //while not end of file
        { 
            a=read.readLine(); 
            if(a!=null)
            {
                b.add(a);
            }
        }
    } 
    catch(IOException iox2)
    {     
    }
  for(int i=0;i<b.size();i++)
  {
      if(b.get(i)!=null)
      {
          String o=b.get(i);
          b.remove(i);
          b.add(i,fix(o));
      }
     
 }
     return b;
 }

public String fix(String unfixed)
{
    if(unfixed.indexOf('\u2013') > -1)
        unfixed=unfixed.replace('\u2013','-');
    if(unfixed.indexOf('\u2014')> -1) 
        unfixed=unfixed.replace('\u2014','-');
    if(unfixed.indexOf('\u2015')> -1) 
        unfixed=unfixed.replace('\u2015','-');
    if(unfixed.indexOf('\u2017')> -1) 
        unfixed=unfixed.replace('\u2017','_');
    if(unfixed.indexOf('\u2018')> -1) 
        unfixed=unfixed.replace('\u2018','\'');
    if(unfixed.indexOf('\u2019')> -1) 
        unfixed=unfixed.replace('\u2019','\'');
    if(unfixed.indexOf('\u201a')> -1) 
        unfixed=unfixed.replace('\u201a',',');
    if(unfixed.indexOf('\u201b')> -1) 
        unfixed=unfixed.replace('\u201b','\'');
    if(unfixed.indexOf('\u201c')> -1) 
        unfixed=unfixed.replace('\u201c','\"');
    if(unfixed.indexOf('\u201d')> -1) 
        unfixed=unfixed.replace('\u201d','\"');
    if(unfixed.indexOf('\u201e')> -1) 
        unfixed=unfixed.replace('\u202e','\"');
    if(unfixed.indexOf('\u2026')> -1) 
        unfixed=unfixed.replace("\u2026","…");
    if(unfixed.indexOf('\u2032')> -1) 
        unfixed=unfixed.replace('\u2032','\'');
    if(unfixed.indexOf('\u2033')> -1) 
        unfixed=unfixed.replace('\u2033','\"');
    
    unfixed.replaceAll("[\\u2018\\u2019]", "'").replaceAll("[\\u201C\\u201D]", "\"");
    if(specialChars)
    {
       unfixed = (Normalizer.normalize(unfixed, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "") );
    }
     return unfixed;
} 
}
