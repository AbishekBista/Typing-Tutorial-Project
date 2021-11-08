import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.*;
import javafx.scene.paint.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.paint.Color.rgb;

class BeginGame {
    int mode;
    double endTime;
    Font wordFont=new Font("Verdana",25);
    File location;
    boolean changeAc=true; //this determines if the person has already gotten the letter wrong
    TextGraber getwords = null;
    String currentLine = null;
    int on; 
    FlowPane contenta = new FlowPane(new Label("ERROR"));
    String line;
    Stage gameStage;
    ProfileAccess pA=new ProfileAccess();
    ScrollPane contentb = new ScrollPane();
    VBox content = new VBox(2);
    Scene gameScene;
    String profile;
    boolean changeable=false; //this determines if the profile can be changed or if it is the default profile which shouldn't be changed
    Date day =new Date();
    Date eDay;
    long startType=day.getTime();
    double startTime=System.nanoTime();
    int typednow;
    int typednow1;
    String typedText=new String();
    Label wpmLabel=new Label();
    
    int wordNumberCount = 1;
    int totalNumberOfWords;
    Label wordNumber = new Label();
    
    int letterNumberCount = 1;
    int totalNumberOfLetters = 0;
    Label letterNumber = new Label();
    
    int whiteSpaceCount = 0;
    int lineCount = 0;
    

    BeginGame(int choosenmode, File filename, boolean special, String name) {
        profile=name;
        contenta.setStyle("-fx-border-style:solid");
        pA.setProfile(profile);
        mode = choosenmode;
        location = filename;
        getwords = new TextGraber(filename,special); 
        getText();
        on = 0;
        gameStage = new Stage();
        if(!profile.equals("default"))
        {
            changeable=true;
        }
        content.setStyle("-fx-background-color: lightgreen");
        content.setAlignment(Pos.TOP_CENTER);
        HBox title = new HBox(2);
        title.setAlignment(Pos.CENTER);
        Label titleName=new Label("TypingTutor");
        titleName.setFont(new Font("Ubuntu", 30));
        titleName.setTextFill(Color.DARKBLUE);
        title.getChildren().add(titleName);
        content.getChildren().add(title);
        gameScene=new Scene(content, 1200, 600);
        gameStage.setScene(gameScene);
        gameStage.setResizable(false);
        if(mode==0 || mode==1)
        {
            contenta.setAlignment(Pos.CENTER);
            content.getChildren().add(contenta);
            contenta.setPadding(new Insets(50,10,10,0));
        }
        
        Button exit=new Button("Exit");
        exit.setStyle("-fx-background-color: #3d4cad; -fx-borde-style: solid; -fx-text-fill: white; -fx-border-radius: 50px;");
        exit.setPrefSize(121,20);
        exit.setOnAction(e->{
           gameStage.hide();
           TypingGame.primaryStage.show();
        });
        Button skipLet=new Button("Skip Letter");
        skipLet.setStyle("-fx-background-color: #3d4cad; -fx-borde-style: solid; -fx-text-fill: white; -fx-border-radius: 50px;");
        skipLet.setPrefSize(121,20);
         skipLet.setOnAction(e->{
            on++;
            drawWord(true);
            if(on==currentLine.length())
            {
                getText();
            }
            letterNumberCount++;
            letterNumber.setFont(new Font("Ubuntu", 15));
            letterNumber.setText("Letter Number: "+letterNumberCount+"/"+totalNumberOfLetters);
        });
        exit.setFocusTraversable(false);
        skipLet.setFocusTraversable(false);
        VBox skip = new VBox(5);
        skip.setPadding(new Insets(0,10,10,0));
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream((filename))))) {
                    while((line = reader.readLine()) != null)
                    {
                        lineCount++;
                        if(!line.equals(""))
                        {
                            totalNumberOfLetters = totalNumberOfLetters + line.length();
                            String words[] = line.split(" ");
                            totalNumberOfWords += words.length;
                        }       
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(BeginGameWhole.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(BeginGameWhole.class.getName()).log(Level.SEVERE, null, ex);
                }
        System.out.println("Line Count: "+ lineCount);
        totalNumberOfLetters = totalNumberOfLetters - totalNumberOfWords + lineCount;
        System.out.println("NumberOfLetters: "+totalNumberOfLetters);
        if(mode == 0)
            {
                letterNumber.setFont(new Font("Ubuntu", 15));
                letterNumber.setText("Letter Number: "+letterNumberCount+"/"+totalNumberOfLetters);
                skip.getChildren().addAll(letterNumber, skipLet, exit);
            }
            if(mode == 1)
            {
                wordNumber.setFont(new Font("Ubuntu", 15));
                wordNumber.setText("Word Number: "+wordNumberCount+"/"+totalNumberOfWords);
                skip.getChildren().addAll(wordNumber, skipLet, exit);
            }
        
        skip.setAlignment(Pos.BOTTOM_RIGHT);
        content.getChildren().add(skip);
        VBox.setVgrow(skip, Priority.ALWAYS);
        ImageView iv=new ImageView(new Image("file:fingers.gif", 485,346,true,true));
            content.getChildren().add(iv);
           setKeys(); 
            
        gameScene.setOnKeyTyped(new EventHandler<KeyEvent>() 
        {   
            public void handle(KeyEvent e) {
                String code = e.getCharacter();
                System.out.println("This is the code: "+code.toString());
                checkLet(code);
                System.out.println("The current line is:"+currentLine);
                if(currentLine==null)
                {           
                }
            }
        });
        gameStage.show();
    }

    public void checkLet(String let) //check if the letter pressed is correct or not
    {
        try{
             char w = let.charAt(0); // the key that is being pressed on the keyboard
             System.out.println("The letter I pressed: "+let.charAt(0));
             System.out.println("The character I am at in the current line is: "+currentLine.charAt(on));
             if (let.charAt(0) != (currentLine.charAt(on))) //if the entered letter did not match
             {
                drawWord(false);
                if(!profile.equals("default"))
                {
                   pA.setOtherData(currentLine.charAt(on)+"");
                }
                if(changeAc&&changeable)
                {
                    changeAc=false;
                    pA.letterOccurred(false);
                }
            }
            else // if the entered letter matched
            {   
                on++; //move to the next letter
                if(changeAc && changeable) 
                    {
                        pA.letterOccurred(true);
                    }
                changeAc=true;
                drawWord(true);
                if(mode == 0)
                {
                    letterNumberCount++;
                    letterNumber.setFont(new Font("Ubuntu", 15));
                    letterNumber.setText("Letter Number: "+letterNumberCount+"/"+totalNumberOfLetters);
                }
                if (on == currentLine.length()) // if we are at the last letter of the word
                {
                    getText(); //fetch a new line
                    wordNumberCount++;
                    wordNumber.setFont(new Font("Ubuntu", 15));
                    wordNumber.setText("Word Number: "+wordNumberCount+"/"+totalNumberOfWords);
                    
                }
            }
        setKeys();
        }
        catch(Exception e)
        {
            System.out.print(e);
        }
    }

    //Adds visual indicators changing the color of the text as you type
    public void drawWord(boolean correct) {
        int p = 0;
        String word = currentLine;
        contenta.getChildren().clear();
        for (String s : word.split("")) {
            Label l = new Label(s);
            l.setStyle("-fx-background-color: palegreen");
            if (p < on) {
                l.setTextFill(Color.GREEN);
            }
            if (p == on) {
                if (correct) {
                    if(l.getText().equals(" "))
                    {
                        l.setText("_");
                        wordNumberCount++;
                        wordNumber.setFont(new Font("Ubuntu", 15));
                        wordNumber.setText("Word Number: "+wordNumberCount+"/"+totalNumberOfWords);
                    }
                    l.setTextFill(Color.BLUE);
                } else {
                    if(l.getText().equals(" "))
                    {
                        l.setText("_");
                    }
                    l.setTextFill(Color.RED);
                }
            }
            if (p > on) {
                l.setTextFill(Color.DARKGRAY);
            }
            l.setPrefWidth(20);
            l.setAlignment(Pos.CENTER);
            l.setFont(wordFont);
            contenta.getChildren().add(l);
            p++;
        }
    }

    public void getText()
    {
        on = 0;
        System.out.println("I was called");
        switch (mode) {
            case 0:
                currentLine = getwords.getLabel1();
                break;

            case 1:
                currentLine = getwords.getLabel2();
                break;
                             
            default:
                System.out.println("Error in TypingGame: getText() switch statement. Not given a valid mode.");
                break;
        }
        if (currentLine == null)
        {
            TypingGame.primaryStage.show();
            long typednow=location.length();
            System.out.println("The number of letters typed now is: "+typednow);
            long words=typednow/5;
            eDay=new Date();
            double endType=eDay.getTime();
            double totalTime=((endType-startType)/1000);
            System.out.println("The total time taken is: "+totalTime);
            double wpm=(double)(words/totalTime)*60;
            int wordperm=(int)wpm;
            pA.setWPM(wordperm);
            System.out.println("Your typing speed is "+wordperm+" words per minute.");
            gameStage.close();
        }
        else
        {
            drawWord(true);
            
        }
    }
    
    // Marks the finger on the on-screen keyboard to be used.
    private void setKeys()
    {
        switch(currentLine.charAt(on)){
                case '1':
                case 'q':
                case 'a':
                case 'z':
                    content.getChildren().set(3, (Node) new ImageView(new Image("file:LeftLittleFinger.gif", 485,346,true,true)));
                    break;
             
                case '2':
                case 'w':
                case 's':
                case 'x':
                    content.getChildren().set(3, (Node) new ImageView(new Image("file:LeftRingFinger.gif", 485,346,true,true)));
                    break;
                    
                case '3':
                case 'e':
                case 'd':
                case 'c':
                    content.getChildren().set(3, (Node) new ImageView(new Image("file:LeftMiddleFinger.gif", 485,346,true,true)));
                    break;
                    
                 case '4':
                 case '5':
                 case 'r':
                 case 't':
                 case 'f':
                 case 'g':
                 case 'v':
                 case 'b':
                    content.getChildren().set(3, (Node) new ImageView(new Image("file:LeftIndexFinger.gif", 485,346,true,true)));
                    break;   
                
                 case '6':
                 case '7':
                 case 'y':
                 case 'u':
                 case 'h':
                 case 'j':
                 case 'n':
                 case 'm':
                    content.getChildren().set(3, (Node) new ImageView(new Image("file:RightIndexFinger.gif", 485,346,true,true)));
                    break;  
                    
                 case '8':
                 case 'i':
                 case 'k':
                 case ',':
                     content.getChildren().set(3, (Node) new ImageView(new Image("file:RightMiddleFinger.gif", 485,346,true,true)));
                    break;
                    
                 case '9':
                 case 'o':
                 case 'l':
                 case '.':
                     content.getChildren().set(3, (Node) new ImageView(new Image("file:RightRingFinger.gif", 485,346,true,true)));
                    break;
                    
                 case '0':
                 case '-':
                 case '=':
                 case '[':
                 case ']':
                 case '\'':
                 case '\\':
                 case 'p':
                 case ';':
                 case '/':
                     content.getChildren().set(3, (Node) new ImageView(new Image("file:RightLittleFinger.gif", 485,346,true,true)));
                    break;
                    
                case '!':
                case 'Q':
                case 'A':
                case 'Z':
                    content.getChildren().set(3, (Node) new ImageView(new Image("file:RLLL.gif", 485,346,true,true)));
                    break;
             
                case '@':
                case 'W':
                case 'S':
                case 'X':
                    content.getChildren().set(3, (Node) new ImageView(new Image("file:RLLR.gif", 485,346,true,true)));
                    break;
                    
                case '#':
                case 'E':
                case 'D':
                case 'C':
                    content.getChildren().set(3, (Node) new ImageView(new Image("file:RLLM.gif", 485,346,true,true)));
                    break;
                    
                 case '$':
                 case '%':
                 case 'R':
                 case 'T':
                 case 'F':
                 case 'G':
                 case 'V':
                 case 'B':
                    content.getChildren().set(3, (Node) new ImageView(new Image("file:RLLI.gif", 485,346,true,true)));
                    break;
                    
                 case '^':
                 case '&':
                 case 'Y':
                 case 'U':
                 case 'H':
                 case 'J':
                 case 'N':
                 case 'M':
                    content.getChildren().set(3, (Node) new ImageView(new Image("file:LLRI.gif", 485,346,true,true)));
                    break;  
                    
                 case '*':
                 case 'I':
                 case 'K':
                 case '<':
                     content.getChildren().set(3, (Node) new ImageView(new Image("file:LLRM.gif", 485,346,true,true)));
                    break;
                    
                 case '(':
                 case 'O':
                 case 'L':
                 case '>':
                     content.getChildren().set(3, (Node) new ImageView(new Image("file:LLRR.gif", 485,346,true,true)));
                    break;
                    
                 case ')':
                 case '_':
                 case '+':
                 case '{':
                 case '}':
                 case '\"':
                 case '|':
                 case 'P':
                 case ':':
                 case '?':
                     content.getChildren().set(3, (Node) new ImageView(new Image("file:LLRL.gif", 485,346,true,true)));
                    break;
                     
                    
                default:
                    content.getChildren().set(3, (Node) new ImageView(new Image("file:RightThumb.gif", 485,346,true,true)));   
         }
    }
}