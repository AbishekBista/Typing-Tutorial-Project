import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.*;
import javafx.scene.paint.*;


class BeginGameWhole
{
    int mode;
    int LineNumberCount = 1;
    int TotalNumberOfLines = 0;
    boolean changeAc=true; //this determines if the person has already gotten the letter wrong
    Font wordFont=new Font("Verdana", 25);
    File location;
    TextGraber getwords = null; 
    int on=0;
    Date day =new Date();
    Date eDay;
    long startType=day.getTime();
    Stage gameStage=new Stage();
    ScrollPane contenta = new ScrollPane();
    VBox content = new VBox();
    Scene gameScene;
    String profile;
    boolean changeable = false; //this determines if the profile stats can be changed or if it is the default profile which shouldn't be changed
    ProfileAccess pA=new ProfileAccess();
    ArrayList<String> currentString;
    Label lineNumber = new Label();
    BeginGameWhole(File filelocation, boolean special, String name)
            {                
                gameStage.setResizable(false);
                profile=name;
                pA.setProfile(profile);
                location = filelocation;
                getwords = new TextGraber(location, special);
                contenta.setHbarPolicy(ScrollBarPolicy.NEVER);
                contenta.setVbarPolicy(ScrollBarPolicy.NEVER);
                contenta.setStyle("-fx-background-color: palegreen; -fx-border-style: solid");
                content.setAlignment(Pos. TOP_CENTER);
                content.setStyle("-fx-background-color: lightgreen");
                Label titleName=new Label("TypingTutor");
                titleName.setFont(new Font("Ubuntu", 30));
                titleName.setTextFill(Color.DARKBLUE);
                if(!profile.equals("default")) //if the selected profile is other than default, the stats change upon switching the profile
                {
                    changeable=true;
                }
                content.getChildren().add(titleName);       
                content.getChildren().add(contenta);
                gameScene=new Scene(content,1500, 650);
                gameStage.setScene(gameScene);
                gameStage.setResizable(false);
                
                // This adds the skip button for any broken letters that the program didn't automatically remove
                Button skipLet=new Button("Skip Letter");
                skipLet.setStyle("-fx-background-color: #3d4cad; -fx-borde-style: solid; -fx-text-fill: white; -fx-border-radius: 50px;");
                skipLet.setPrefSize(121,20);
                skipLet.setOnAction(e->{
                    on++;
                    drawWord(true);
                    if(on>=currentString.get(0).length())
                    {
                        currentString.remove(0);
                        changeAc=true;
                        on=0;
                    }
                });
                
                Button exit = new Button("Exit");
                exit.setStyle("-fx-background-color: #3d4cad; -fx-borde-style: solid; -fx-text-fill: white; -fx-border-radius: 50px;");
                exit.setPrefSize(121,20);
                exit.setOnAction(e->{
                    gameStage.hide();
                    TypingGame.primaryStage.show();
                });
                try (BufferedReader reader = new BufferedReader(new FileReader(filelocation))) {
                    while(reader.readLine() != null)
                    TotalNumberOfLines++;
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(BeginGameWhole.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(BeginGameWhole.class.getName()).log(Level.SEVERE, null, ex);
                }
                exit.setFocusTraversable(false);
                skipLet.setFocusTraversable(false);
                VBox skip = new VBox(5);
                skip.setPadding(new Insets(30,10,0,0));
                lineNumber.setFont(new Font("Ubuntu", 15));
                lineNumber.setText("Line Number: "+LineNumberCount+"/"+TotalNumberOfLines);
                skip.getChildren().addAll(lineNumber, skipLet, exit);
                skip.setAlignment(Pos.BOTTOM_RIGHT);
                content.getChildren().add(skip);
                VBox.setVgrow(skip, Priority.ALWAYS);
                ImageView iv=new ImageView(new Image("file:fingers.gif", 485,346,true,true));
                content.getChildren().add(iv);
                
        
                
               
        //keypressed detection
            gameScene.setOnKeyTyped(
                new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent e) {   
                        String code = e.getCharacter().toString();
                        checkLet(code); 
                        if(currentString==null)
                        { 
                        }
                     }
                });
        gameStage.show();
        getText();
        setKeys();
    }
    
    public void checkLet(String let) 
    {
        try{
        char w = let.charAt(0);
        System.out.println("This is the letter that I am typing: "+ let.charAt(0));
        System.out.println("This is the letter to be pressed now: "+currentString.get(0).charAt(on));
        if (let.charAt(0) != (currentString.get(0).charAt(on))) {
            drawWord(false);
            if(!profile.equals("default"))
            {
                pA.setOtherData(currentString.get(0).charAt(on)+""); 
                if(changeAc && changeable)
                {
                   changeAc=false;
                   pA.letterOccurred(false);
                }
            }
        }
        else {
            on++;
            if(changeAc && changeable)
                {
                   pA.letterOccurred(true);
                }
            drawWord(true);
            changeAc=true;
            if (on >= currentString.get(0).length()) {
               on=0;
               currentString.remove(0);
               drawWord(true);
               LineNumberCount++;
               lineNumber.setFont(new Font("Ubuntu", 15));
               lineNumber.setText("Line Number: "+LineNumberCount+"/"+TotalNumberOfLines);
            }
        } 
        if(currentString.isEmpty())
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
        }setKeys();
        }catch(Exception e){
        }
    }
     public void drawWord(boolean correct) { 
            int p = 0;
            FlowPane words1=new FlowPane();
            words1.setStyle("-fx-background-color: palegreen");
            for(int i=0;i<currentString.size() && i< 5;i++)
            {
            String word = currentString.get(i);
            FlowPane h=new FlowPane();
            h.setStyle("-fx-background-color: palegreen");
            h.setPrefWrapLength(contenta.getWidth());
            for (String s : word.split("")) {
                Label l = new Label(s);
               if(i==0)
               {
                if (p < on) {
                    l.setTextFill(Color.GREEN);
                }
                if (p == on) {
                    if (correct) {
                        if(l.getText().equals(" "))
                        {
                            l.setUnderline(true);
                        }
                        l.setTextFill(Color.BLUE);
                    } else {
                        if(l.getText().equals(" "))
                        {
                            l.setUnderline(true);
                        }
                        l.setTextFill(Color.RED);
                    }
                }
                if (p > on) {
                    l.setTextFill(Color.DARKGRAY);
                }
               }
               else
               {
                   l.setTextFill(Color.DARKGRAY);
               }
                l.setAlignment(Pos.CENTER);
                l.setFont(wordFont);
                h.getChildren().add(l);
                p++;
            }
            words1.getChildren().add(h);
            }
            contenta.setContent(words1);
            
        }

    public void getText()
    {
        currentString=getwords.getLabel3();
        drawWord(true);
    }
    
    private void setKeys()
    {
        char x= currentString.get(0).charAt(on);
        switch(x){
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