import java.util.*;
public class TypingHistory {
    ArrayList<String> letters=new ArrayList<String>();
    ArrayList<Integer> timesMissed=new ArrayList<Integer>();
    public void addData(String letter, int missed)
    {
        if(letters.size()==0)
        {
            letters.add(letter);
            timesMissed.add(missed);
        }
        else
        {
            for(int i=0; i<=letters.size()-1;i++)
            {
                if(missed>timesMissed.get(i))
                {
                    timesMissed.add(i,missed);
                    letters.add(i, letter);
                    break;
                }
            }
            if(missed<=timesMissed.get(timesMissed.size()-1))
            {
                timesMissed.add(missed);
                letters.add(letter);
            }
        }
    }
    
    public ArrayList<String> getLetters(){
        return letters;
    }
    
    public ArrayList<Integer> getMissed(){
       return timesMissed; 
    }
}