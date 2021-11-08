import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class TypingHistoryOL {
    private final SimpleIntegerProperty number;
    private final SimpleStringProperty letter;
    private final SimpleIntegerProperty missed;
    
    public TypingHistoryOL(Integer n, String l, Integer m){
        number=new SimpleIntegerProperty(n);
        letter=new SimpleStringProperty(l);
        missed=new SimpleIntegerProperty(m);
    }
    
    public Integer getNumber()
    {
        return number.get();
    }
    
    public String getLetter()
    {
        return letter.get();
    }
    
    public Integer getMissed()
    {
        return missed.get();
    }  
}