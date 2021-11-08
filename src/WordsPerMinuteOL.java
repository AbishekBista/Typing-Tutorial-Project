import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class WordsPerMinuteOL {
    private final SimpleStringProperty file;
    private final SimpleDoubleProperty wpm;
    private final SimpleDoubleProperty acc;
    
    public WordsPerMinuteOL(String f, Double w, Double a){
        file=new SimpleStringProperty(f);
        wpm=new SimpleDoubleProperty(w);
        acc=new SimpleDoubleProperty(a);
    }
    
    public String getFile()
    {
        return file.get();
    }
    
    public Double getWPM()
    {
        return wpm.get();
    }
    
    public Double getAccuracy()
    {
        return acc.get();
    }
    
}
