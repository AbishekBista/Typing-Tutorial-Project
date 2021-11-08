import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.geometry.*;
import java.io.*;
import java.util.*;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.*;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.paint.*;
import javafx.scene.chart.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TypingGame extends Application {
    ProfileAccess pA=new ProfileAccess();
//the keys missed history data
    ObservableList<TypingHistoryOL> historyData= FXCollections.observableArrayList();
//to store the letters that are troublesome to the user
    TableView<TypingHistoryOL> typeH=new TableView<>(); 
// the pie chart accuracy data
    ObservableList<PieChart.Data> accuracyChartData=FXCollections.observableArrayList();
    PieChart accuracyChart = new PieChart(accuracyChartData);
    Label wpmrecord=new Label();
    Label wpmlabel=new Label();
    int mode = 0;
    File filename = new File("C:/Users/Test/Documents/NetBeansProjects/TypingGame/src/demo.txt");
    int done = 0;
    String profile="default";
    private boolean profileChange=false;
    boolean specialCharsChoice=false;
    static Stage primaryStage;
    TextGraber getwords = null; 
  
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage ppp) throws Exception {
//Interface design
        primaryStage = ppp;
        BorderPane menuLay = new BorderPane(); // layout design
        Scene menu = new Scene(menuLay, 800, 600);
        menu.getStylesheets().add("style.css");
        menuLay.setId("pane");
        menuLay.setStyle("-fx-background-color: lightgreen");
//Top of the menu
        Label mainTop = new Label("TypingTutor");
        mainTop.setStyle("-fx-background-color: lightgreen");
        mainTop.setFont(new Font("Ubuntu", 30));
        mainTop.setTextFill(Color.DARKBLUE);
        mainTop.setPadding(new Insets(15, 12, 15, 350));
        HBox title = new HBox();
        title.setStyle("-fx-background-color: lightgreen");
        title.setAlignment(Pos.CENTER);
        title.getChildren().addAll(mainTop);
        menuLay.setTop(title);
        
//This creates the profile selector
        final ComboBox<String> profileBox=new ComboBox<>();
        StackPane cb=new StackPane();
        cb.setStyle("-fx-background-color:lightgreen");
        cb.getChildren().add(profileBox);
        cb.setAlignment(Pos.BOTTOM_RIGHT);
        title.getChildren().add(cb);
        menuLay.setAlignment(title, Pos.CENTER);
        HBox.setHgrow(cb, Priority.ALWAYS);
        HBox.setHgrow(mainTop, Priority.ALWAYS);
        
//Main Page
        Button startGameBut = new Button("Start Test");
        startGameBut.setPrefSize(150,30);        
        startGameBut.setOnAction(e-> {
            primaryStage.hide();
            if(mode==0 || mode==1)
            {   
                BeginGame game=new BeginGame(mode,filename, specialCharsChoice, profile);
                System.out.println("The mode is "+mode+" the filename is "+ filename+ " the specialCharactersChoice is "+specialCharsChoice+" and the profile is "+profile);
            }
            if(mode==2)
            {
                BeginGameWhole game1=new BeginGameWhole(filename, specialCharsChoice, profile);
            }
            
        });

// Lessons
//Page Index
        BorderPane lessonBox=new BorderPane();
        HBox index=new HBox(10);
        Button page1=new Button("1");
        Button page2=new Button("2");
        Button page3=new Button("3");
        Button page4=new Button("4");
        Button page5=new Button("5");
        Button page6=new Button("6");
        Button page7=new Button("7");
        Button page8=new Button("8");
        Button page9=new Button("9");
        Button page10=new Button("10");
        Button page11=new Button("11");
        Button page12=new Button("12");
        
//Hover-over event
        page1.setOnMouseEntered(e->{
            page1.setUnderline(true);
        });
        page1.setOnMouseExited(e->{
            page1.setUnderline(false);
        });
        page2.setOnMouseEntered(e->{
            page2.setUnderline(true);
        });
        page2.setOnMouseExited(e->{
            page2.setUnderline(false);
        });
        page3.setOnMouseEntered(e->{
            page3.setUnderline(true);
        });
        page3.setOnMouseExited(e->{
            page3.setUnderline(false);
        });
        page4.setOnMouseEntered(e->{
            page4.setUnderline(true);
        });
        page4.setOnMouseExited(e->{
            page4.setUnderline(false);
        });        
        page5.setOnMouseEntered(e->{
            page5.setUnderline(true);
        });
        page5.setOnMouseExited(e->{
            page5.setUnderline(false);
        });
        page6.setOnMouseEntered(e->{
            page6.setUnderline(true);
        });
        page6.setOnMouseExited(e->{
            page6.setUnderline(false);
        });
        page7.setOnMouseEntered(e->{
            page7.setUnderline(true);
        });
        page7.setOnMouseExited(e->{
            page7.setUnderline(false);
        });
        page8.setOnMouseEntered(e->{
            page8.setUnderline(true);
        });
        page8.setOnMouseExited(e->{
            page8.setUnderline(false);
        });
        page9.setOnMouseEntered(e->{
            page9.setUnderline(true);
        });
        page9.setOnMouseExited(e->{
            page9.setUnderline(false);
        });
        page10.setOnMouseEntered(e->{
            page10.setUnderline(true);
        });
        page10.setOnMouseExited(e->{
            page10.setUnderline(false);
        });   
        page11.setOnMouseEntered(e->{
            page11.setUnderline(true);
        });
        page11.setOnMouseExited(e->{
            page11.setUnderline(false);
        });
        page12.setOnMouseEntered(e->{
            page12.setUnderline(true);
        });
        page12.setOnMouseExited(e->{
            page12.setUnderline(false);
        });
        
// Styling the index buttons
        page1.setStyle("-fx-background-color: transparent; -fx-text-fill: darkblue;-fx-font-size: 20");
        page2.setStyle("-fx-background-color: transparent; -fx-text-fill: darkblue;-fx-font-size: 20");
        page3.setStyle("-fx-background-color: transparent; -fx-text-fill: darkblue;-fx-font-size: 20");
        page4.setStyle("-fx-background-color: transparent; -fx-text-fill: darkblue;-fx-font-size: 20");
        page5.setStyle("-fx-background-color: transparent; -fx-text-fill: darkblue;-fx-font-size: 20");
        page6.setStyle("-fx-background-color: transparent; -fx-text-fill: darkblue;-fx-font-size: 20");
        page7.setStyle("-fx-background-color: transparent; -fx-text-fill: darkblue;-fx-text-fill: darkblue;-fx-font-size: 20");
        page8.setStyle("-fx-background-color: transparent; -fx-text-fill: darkblue;-fx-font-size: 20");
        page9.setStyle("-fx-background-color: transparent; -fx-text-fill: darkblue;-fx-font-size: 20");
        page10.setStyle("-fx-background-color: transparent; -fx-text-fill: darkblue;-fx-font-size: 20");
        page11.setStyle("-fx-background-color: transparent; -fx-text-fill: darkblue;-fx-font-size: 20");
        page12.setStyle("-fx-background-color: transparent; -fx-text-fill: darkblue;-fx-font-size: 20");
        
//adding the buttons to the index HBox
        index.getChildren().addAll(page1, page2, page3, page4, page5, page6, page7, page8, page9, page10, page11, page12);
        index.setPadding(new Insets(40, 0, 0, 25));
        lessonBox.setTop(index);
        VBox lessonVBox1=new VBox(15);
        lessonVBox1.setPadding(new Insets(20, 60, 0,0));
        VBox lessonVBox2=new VBox(15);
        lessonVBox2.setPadding(new Insets(20, 60, 0,0));
        VBox lessonVBox3=new VBox(15);
        lessonVBox3.setPadding(new Insets(20, 60, 0,0));
        VBox lessonVBox4=new VBox(15);
        lessonVBox4.setPadding(new Insets(20, 60, 0,0));
        VBox lessonVBox5=new VBox(15);
        lessonVBox5.setPadding(new Insets(20, 60, 0,0));
        VBox lessonVBox6=new VBox(15);
        lessonVBox6.setPadding(new Insets(20, 60, 0,0));
        VBox lessonVBox7=new VBox(15);
        lessonVBox7.setPadding(new Insets(20, 60, 0,0));
        VBox lessonVBox8=new VBox(15);
        lessonVBox8.setPadding(new Insets(20, 60, 0,0));
        VBox lessonVBox9=new VBox(15);
        lessonVBox9.setPadding(new Insets(20, 60, 0,0));
        VBox lessonVBox10=new VBox(15);
        lessonVBox10.setPadding(new Insets(20, 60,0,0));
        VBox lessonVBox11=new VBox(15);
        lessonVBox11.setPadding(new Insets(20, 60, 0,0));
        VBox lessonVBox12=new VBox(15);
        lessonVBox12.setPadding(new Insets(20, 60, 0,0));
        
        Button lesson1=new Button("Lesson 1");
        lesson1.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson1.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson2=new Button("Lesson 2");
        lesson2.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson2.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson3=new Button("Lesson 3");
        lesson3.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson3.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson4=new Button("Lesson 4");
        lesson4.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson4.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson5=new Button("Lesson 5");
        lesson5.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson5.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson6=new Button("Lesson 6");
        lesson6.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson6.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson7=new Button("Lesson 7");
        lesson7.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson7.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson8=new Button("Lesson 8");
        lesson8.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson8.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson9=new Button("Lesson 9");
        lesson9.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson9.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson10=new Button("Lesson 10");
        lesson10.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson10.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson11=new Button("Lesson 11");
        lesson11.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson11.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson12=new Button("Lesson 12");
        lesson12.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson12.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson13=new Button("Lesson 13");
        lesson13.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson13.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson14=new Button("Lesson 14");
        lesson14.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson14.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson15=new Button("Lesson 15");
        lesson15.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson15.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson16=new Button("Lesson 16");
        lesson16.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson16.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson17=new Button("Lesson 17");
        lesson17.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson17.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson18=new Button("Lesson 18");
        lesson18.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson18.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson19=new Button("Lesson 19");
        lesson19.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson19.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson20=new Button("Lesson 20");
        lesson20.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson20.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson21=new Button("Lesson 21");
        lesson21.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson21.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson22=new Button("Lesson 22");
        lesson22.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson22.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson23=new Button("Lesson 23");
        lesson23.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson23.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson24=new Button("Lesson 24");
        lesson24.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson24.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson25=new Button("Lesson 25");
        lesson25.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson25.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson26=new Button("Lesson 26");
        lesson26.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson26.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson27=new Button("Lesson 27");
        lesson27.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson27.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson28=new Button("Lesson 28");
        lesson28.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson28.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson29=new Button("Lesson 29");
        lesson29.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson29.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson30=new Button("Lesson 30");
        lesson30.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson30.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson31=new Button("Lesson 31");
        lesson31.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson31.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson32=new Button("Lesson 32");
        lesson32.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson32.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson33=new Button("Lesson 33");
        lesson33.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson33.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson34=new Button("Lesson 34");
        lesson34.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson34.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson35=new Button("Lesson 35");
        lesson35.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson35.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson36=new Button("Lesson 36");
        lesson36.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson36.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson37=new Button("Lesson 37");
        lesson37.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson37.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson38=new Button("Lesson 38");
        lesson38.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson38.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson39=new Button("Lesson 39");
        lesson39.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson39.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson40=new Button("Lesson 40");
        lesson40.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson40.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson41=new Button("Lesson 41");
        lesson41.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson41.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson42=new Button("Lesson 42");
        lesson42.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson42.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson43=new Button("Lesson 43");
        lesson43.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson43.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson44=new Button("Lesson 44");
        lesson44.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson44.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson45=new Button("Lesson 45");
        lesson45.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson45.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson46=new Button("Lesson 46");
        lesson46.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson46.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson47=new Button("Lesson 47");
        lesson47.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson47.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson48=new Button("Lesson 48");
        lesson48.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson48.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson49=new Button("Lesson 49");
        lesson49.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson49.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson50=new Button("Lesson 50");
        lesson50.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson50.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson51=new Button("Lesson 51");
        lesson51.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson51.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson52=new Button("Lesson 52");
        lesson52.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson52.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson53=new Button("Lesson 53");
        lesson53.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson53.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson54=new Button("Lesson 54");
        lesson54.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson54.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson55=new Button("Lesson 55");
        lesson55.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson55.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson56=new Button("Lesson 56");
        lesson56.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson56.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson57=new Button("Lesson 57");
        lesson57.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson57.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson58=new Button("Lesson 58");
        lesson58.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson58.txt");
            BeginGame game=new BeginGame(1,filename, specialCharsChoice, profile);
        });
        Button lesson59=new Button("Lesson 59");
        lesson59.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson59.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson60=new Button("Lesson 60");
        lesson60.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson60.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        Button lesson61=new Button("Lesson 61");
        lesson61.setOnAction(e->{
            primaryStage.hide();
            filename=new File("lessons/lesson61.txt");
            BeginGameWhole game=new BeginGameWhole(filename, specialCharsChoice, profile);
        });
        
// trying to assing images to the background
//making the button width even in the VBox
        lesson1.setPrefWidth(69);
        lesson2.setPrefWidth(69);
        lesson3.setPrefWidth(69);
        lesson4.setPrefWidth(69);
        lesson5.setPrefWidth(69);
        lesson6.setPrefWidth(69);
        lesson7.setPrefWidth(69);
        lesson8.setPrefWidth(69);
        lesson9.setPrefWidth(69); 
       
        Label desc1=new Label("The Home Keys");
        desc1.setStyle("-fx-font-size: 20");
        Label desc2=new Label("Keys E and I");
        desc2.setStyle("-fx-font-size: 20");
        Label desc3=new Label("Keys R and U");
        desc3.setStyle("-fx-font-size: 20");
        Label desc4=new Label("Keys T and O");
        desc4.setStyle("-fx-font-size: 20");
        Label desc5=new Label("Capital letters and period");
        desc5.setStyle("-fx-font-size: 20");
        Label desc6=new Label("Keys C and Comma");
        desc6.setStyle("-fx-font-size: 20");
        Label desc7=new Label("Keys G, H and apostrophe");
        desc7.setStyle("-fx-font-size: 20");
        Label desc8=new Label("Keys V, N and question mark");
        desc8.setStyle("-fx-font-size: 20");
        Label desc9=new Label("Keys W and M");
        desc9.setStyle("-fx-font-size: 20");
        Label desc10=new Label("Keys Q and P");
        desc10.setStyle("-fx-font-size: 20");
        Label desc11=new Label("Keys B and Y");
        desc11.setStyle("-fx-font-size: 20");
        Label desc12=new Label("Keys Z and X");
        desc12.setStyle("-fx-font-size: 20");
        
//adding the lesson buttons to respective VBoxes       
        lessonVBox1.getChildren().addAll(desc1,lesson1,lesson2,lesson3,lesson4);
        lessonVBox2.getChildren().addAll(desc2, lesson5,lesson6,lesson7,lesson8);
        lessonVBox3.getChildren().addAll(desc3, lesson9,lesson10,lesson11,lesson12,lesson13);
        lessonVBox4.getChildren().addAll(desc4,lesson14,lesson15,lesson16,lesson17,lesson18);
        lessonVBox5.getChildren().addAll(desc5,lesson19,lesson20,lesson21,lesson22,lesson23,lesson24);
        lessonVBox6.getChildren().addAll(desc6,lesson25,lesson26,lesson27,lesson28,lesson29);
        lessonVBox7.getChildren().addAll(desc7,lesson30,lesson31,lesson32,lesson33,lesson34,lesson35);
        lessonVBox8.getChildren().addAll(desc8,lesson36,lesson37,lesson38,lesson39,lesson40,lesson41);
        lessonVBox9.getChildren().addAll(desc9,lesson42,lesson43,lesson44,lesson45,lesson46);
        lessonVBox10.getChildren().addAll(desc10,lesson47,lesson48,lesson49,lesson50,lesson51);
        lessonVBox11.getChildren().addAll(desc11,lesson52,lesson53,lesson54,lesson55,lesson56);
        lessonVBox12.getChildren().addAll(desc12,lesson57,lesson58,lesson59,lesson60,lesson61);
        
        lessonVBox1.setAlignment(Pos.TOP_CENTER);
        lessonVBox2.setAlignment(Pos.TOP_CENTER);
        lessonVBox3.setAlignment(Pos.TOP_CENTER);
        lessonVBox4.setAlignment(Pos.TOP_CENTER);
        lessonVBox5.setAlignment(Pos.TOP_CENTER);
        lessonVBox6.setAlignment(Pos.TOP_CENTER);
        lessonVBox7.setAlignment(Pos.TOP_CENTER);
        lessonVBox8.setAlignment(Pos.TOP_CENTER);
        lessonVBox9.setAlignment(Pos.TOP_CENTER);
        lessonVBox10.setAlignment(Pos.TOP_CENTER);
        lessonVBox11.setAlignment(Pos.TOP_CENTER);
        lessonVBox12.setAlignment(Pos.TOP_CENTER);
        
//starting with page 1 in lesson        
        lessonBox.setCenter(lessonVBox1);
        lessonBox.setStyle("-fx-border-style: solid; -fx-border-color: green");
//Test & Settings
        VBox testBox = new VBox(10);
        testBox.setStyle("-fx-background-color: lightgreen; -fx-border-color: green; -fx-border-style:solid");
        //setting-filelocation
        HBox fileLocationHBox=new HBox(5);
        Label setL1 = new Label("Enter Text File Location");
        setL1.setStyle("-fx-font-size: 20");
        TextField enterFileLocation = new TextField();
        enterFileLocation.setPrefHeight(30);
        enterFileLocation.setPrefWidth(300);
        enterFileLocation.setMaxWidth(400);
        enterFileLocation.setPromptText("ex. C:/Users/Johnny/Documents/words.txt");
        enterFileLocation.setOnAction(e-> {
            String a = new String(enterFileLocation.getText());
            File b = new File(a);
            File c = new File(a + ".txt");
            System.out.println(a + "\n b is " + b.exists() + "\n c is " + c.exists());
            if (b.exists() == false && c.exists() == false)
            {
                startGameBut.setDisable(true);
                startGameBut.setText("Invalid File Location");
            } 
            else
            {
                startGameBut.setDisable(false);
                startGameBut.setPrefSize(150,30);
                startGameBut.setStyle("-fx-font-size: 13");
                startGameBut.setText("Start Test");
                
                if (b.exists() == true) {
                    filename = b;
                } else {
                    filename = c;
                }
                if(profileChange)
                {
                    pA.setData("File",filename.toString());
                }
            }
        }); 
        Button searchFileButton=new Button("Search");
        searchFileButton.setStyle("-fx-font-size: 15");
        searchFileButton.setPrefHeight(30);
        searchFileButton.setPrefWidth(100);
        searchFileButton.setOnAction(e->
        {
            FileChooser fileChooser=new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),new ExtensionFilter("All Files", "*.*"));
            File selectedFile=fileChooser.showOpenDialog(primaryStage);
            if(selectedFile!=null)
            {
                if(profileChange)
                {
                    filename = selectedFile;
                    pA.setData("File", filename.toString());
                    startGameBut.setText("Start Game");
                    startGameBut.setDisable(false);
                }
                enterFileLocation.setText(selectedFile.toString());
                pA.setData("File", filename.toString());
            }   
        });
        fileLocationHBox.getChildren().addAll(setL1,enterFileLocation,searchFileButton);
        
            String aPH = enterFileLocation.getText();
            File bPH = new File(aPH);
            File cPH = new File(aPH + ".txt");
            System.out.println(aPH + "\n" + bPH.exists() + "\n" + cPH.exists());
            if (bPH.exists() == false && cPH.exists() == false)
            {
                startGameBut.setDisable(true);
                startGameBut.setText("Invalid File Location");
            } 
            else
            {
                startGameBut.setDisable(false);
                startGameBut.setPrefSize(150,30);
                startGameBut.setStyle("-fx-font-size: 13");
                startGameBut.setText("Start Test");
                if (bPH.exists() == true) {
                    filename = bPH;
                } else {
                    filename = cPH;
                }
            }

//settings-mode
        final ToggleGroup modeButList = new ToggleGroup();
        RadioButton mode0 = new RadioButton("Word by Word");
        mode0.setStyle("-fx-font-size: 15");
        mode0.setToggleGroup(modeButList);
        mode0.setOnAction(e -> {
            mode = 0;
            if(profileChange)
                {
                    pA.setData("Mode","0");
                }
        });
        RadioButton mode1 = new RadioButton("Line by Line");
        mode1.setStyle("-fx-font-size: 15");
        mode1.setToggleGroup(modeButList);
        mode1.setOnAction(e ->{
            mode = 1;
            if(profileChange)
                {
                    pA.setData("Mode","1");
                }
                });
        RadioButton mode2 = new RadioButton("Whole File");
        mode2.setStyle("-fx-font-size:15");
        mode2.setToggleGroup(modeButList);
        mode2.setOnAction(e -> {
            mode = 2;
            if(profileChange)
                {
                    pA.setData("Mode","2");
                }
                });
        mode0.setSelected(true);
        VBox butListMode = new VBox(5);
        Label displaySettingsLabel=new Label("Display Settings");
        displaySettingsLabel.setStyle("-fx-font-size: 20");
        butListMode.getChildren().addAll(displaySettingsLabel,mode0, mode1, mode2);
        butListMode.setSpacing(2);        
        testBox.getChildren().addAll(fileLocationHBox, butListMode);
        testBox.setPadding(new Insets(20, 0, 0, 0));
        
//settings-allow special characters
        Label specialChars=new Label("Would you like to allow special characters such as ¿");
        specialChars.setStyle("-fx-font-size: 20");
        final ToggleGroup specialCharsTG = new ToggleGroup();
        RadioButton noSpecial = new RadioButton("Yes");
        noSpecial.setStyle("-fx-font-size: 15");
        noSpecial.setToggleGroup(specialCharsTG);
        noSpecial.setSelected(true);
        noSpecial.setOnAction(e-> {
                specialCharsChoice=false;
                if(profileChange)
                {
                    pA.setData("SpecChars","false");
                }
                        });
        
        RadioButton yesSpecial= new RadioButton("No");
        yesSpecial.setStyle("-fx-font-size: 15");
        yesSpecial.setToggleGroup(specialCharsTG);
        yesSpecial.setOnAction(e-> {
            specialCharsChoice=true;
            if(profileChange)
                {
                    pA.setData("SpecChars","true");
                }
        });
        VBox specialCharsVBox=new VBox(5);
        specialCharsVBox.getChildren().addAll(specialChars, noSpecial, yesSpecial);
        testBox.getChildren().add(specialCharsVBox);
        testBox.getChildren().add(startGameBut);
        
//Delete button
        Button deleteProfileButton=new Button("Delete Profile");
        deleteProfileButton.setTextFill(Color.RED);
        deleteProfileButton.setPrefWidth(150);
        deleteProfileButton.setPrefHeight(30);
        deleteProfileButton.setStyle("-fx-font-size:15");
        deleteProfileButton.setOnAction(e->
        {
            if(!profile.equals("default"))
            {
            Alert alert=new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Delete Profile");
            alert.setHeaderText("Are you sure you want to delete this profile");
            alert.setContentText("If you delete your profile, your previous history will be unrecoverable unless you made a backup");
            
            Optional<ButtonType>result=alert.showAndWait();
            if(result.get()==ButtonType.OK) //result stores ok or cancel
            {
                pA.deleteProfile();
                profileBox.getItems().remove(profile);
                profile="default";
                profileBox.setValue("default");
                update();
            }
            else
            {
                //  user chose CANCEL or closed the dialog
            }
            }
        });
        testBox.getChildren().addAll(deleteProfileButton);
        testBox.setAlignment(Pos.CENTER);
        
//History
        VBox historyPage = new VBox(2);
        historyPage.setStyle("-fx-background-color: lightgreen; -fx-border-style: solid; -fx-border-color: green");
        HBox historyPageButtons=new HBox(5);
        historyPageButtons.setPadding(new Insets(20,0,0,0));
        Button keysMissedPageButton=new Button("Typing History");
        keysMissedPageButton.setOnAction(e->{
            
            historyPage.getChildren().removeAll(historyPage.getChildren());
            historyPage.getChildren().addAll(historyPageButtons, typeH);    
        });
        
// On click of Accuracy Button
        Button accuracyPageButton=new Button("Accuracy");
        accuracyPageButton.setOnAction(e->{
           historyPage.getChildren().removeAll(historyPage.getChildren());
           historyPage.getChildren().addAll(historyPageButtons, accuracyChart);
           
        });
  
//WPM stuff
HBox WPMBox=new HBox();
WPMBox.setAlignment(Pos.CENTER);
VBox wpmdata=new VBox();
wpmdata.getChildren().add(wpmrecord);
wpmdata.setStyle("-fx-font-size: 260");
wpmdata.setPadding(new Insets(0,0,0,150));
wpmlabel=new Label("Words Per Minute");
wpmlabel.setStyle("-fx-font-size: 30");
WPMBox.getChildren().addAll(wpmdata,wpmlabel);
// On click of WPM Button
        Button wpm=new Button("Word per Minute");
        wpm.setOnAction(e->{
            historyPage.getChildren().removeAll(historyPage.getChildren());
            historyPage.getChildren().addAll(historyPageButtons, WPMBox); 
           
        });
        
//table history stuff     
        historyPageButtons.getChildren().addAll(keysMissedPageButton, accuracyPageButton, wpm);
        historyPage.getChildren().addAll(historyPageButtons, typeH);
        
//column for number
        typeH.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<TypingHistoryOL, Integer> rankOnMissed = new TableColumn<>("#");
        rankOnMissed.setCellValueFactory(new PropertyValueFactory<>("number"));
         
//column for letters
        TableColumn<TypingHistoryOL, String> letteronMissed=new TableColumn<>("Letters Missed");
        letteronMissed.setPrefWidth(100);
        letteronMissed.setCellValueFactory(new PropertyValueFactory<>("letter"));
               
//column for missed times
        TableColumn<TypingHistoryOL, Integer> timesletMissed=new TableColumn<>("Times Missed");
        timesletMissed.setPrefWidth(100);
        timesletMissed.setCellValueFactory(new PropertyValueFactory<>("missed"));
        typeH.setItems(historyData);
        typeH.getColumns().add(rankOnMissed);
        typeH.getColumns().add(letteronMissed);
        typeH.getColumns().add(timesletMissed);

//accuracy pie stuff
        accuracyChart.setTitle("Typing Accuracy");
        
//Left Pane
        VBox navigation = new VBox(10);
        navigation.setStyle("-fx-background-color: palegreen; -fx-border-style: solid; -fx-border-color: green");
        navigation.setPadding(new Insets(10, 10, 10, 10));
        menuLay.setLeft(navigation);

//Tutorial Box
        BorderPane tutorialBox = new BorderPane();
      
        VBox tBox1=new VBox(new Label("Position your hands on the home row as highlighted"), new ImageView(new Image("file:fingers.gif", 485,346,true,true)));
        tBox1.setStyle("-fx-font-size: 20");
        tBox1.setAlignment(Pos.CENTER);
        
        VBox tBox2=new VBox(new Label("Press the keys E and I with your middle fingers"), new ImageView(new Image("file:MiddleFinger.gif", 485,346,true,true)));
        tBox2.setStyle("-fx-font-size: 20");
        tBox2.setAlignment(Pos.CENTER);
        
        VBox tBox3=new VBox(new Label("Press the keys R and U with your index fingers"), new ImageView(new Image("file:IndexFinger.gif", 485,346,true,true)));
        tBox3.setStyle("-fx-font-size: 20");
        tBox3.setAlignment(Pos.CENTER);
        
        VBox tBox4=new VBox(new Label("Press the key T with your left index finger"), new ImageView(new Image("file:LeftIndexFinger.gif", 485,346,true,true)));
        tBox4.setStyle("-fx-font-size: 20");
        tBox4.setAlignment(Pos.CENTER);
        
        VBox tBox5=new VBox(new Label("Press the key O with your right ring finger"), new ImageView(new Image("file:RightRingFinger.gif", 485,346,true,true)));
        tBox5.setStyle("-fx-font-size: 20");
        tBox5.setAlignment(Pos.CENTER);
        
        VBox tBox6=new VBox(new Label("Press and hold the shift keys with your little fingers to type capital letters"), new ImageView(new Image("file:LittleFinger.gif", 485,346,true,true)));
        tBox6.setStyle("-fx-font-size: 20");
        tBox6.setAlignment(Pos.CENTER);
        
        VBox tBox7=new VBox(new Label("Press the period key (.) with your right ring finger"), new ImageView(new Image("file:RightRingFinger.gif", 485,346,true,true)));
        tBox7.setStyle("-fx-font-size: 20");
        tBox7.setAlignment(Pos.CENTER);
        
        VBox tBox8=new VBox(new Label("Press the keys C and comma (,) with your middle fingers"), new ImageView(new Image("file:MiddleFinger.gif", 485,346,true,true)));
        tBox8.setStyle("-fx-font-size: 20");
        tBox8.setAlignment(Pos.CENTER);
        
        VBox tBox9=new VBox(new Label("Press the keys G and H with your index fingers"), new ImageView(new Image("file:IndexFinger.gif", 485,346,true,true)));
        tBox9.setStyle("-fx-font-size: 20");
        tBox9.setAlignment(Pos.CENTER);
        
        VBox tBox10=new VBox(new Label("Press the apostrophe key (') with your right little finger"), new ImageView(new Image("file:RightLittleFinger.gif", 485,346,true,true)));
        tBox10.setStyle("-fx-font-size: 20");
        tBox10.setAlignment(Pos.CENTER);
        
        VBox tBox11=new VBox(new Label("Press the keys V and N with your index fingers"), new ImageView(new Image("file:IndexFinger.gif", 485,346,true,true)));
        tBox11.setStyle("-fx-font-size: 20");
        tBox11.setAlignment(Pos.CENTER);
        
        VBox tBox12=new VBox(new Label("Press the key W with your left ring finger"), new ImageView(new Image("file:LeftRingFinger.gif", 485,346,true,true)));
        tBox12.setStyle("-fx-font-size: 20");
        tBox12.setAlignment(Pos.CENTER);
        
        VBox tBox13=new VBox(new Label("Press the key M with your right index finger"), new ImageView(new Image("file:RightIndexFinger.gif", 485,346,true,true)));
        tBox13.setStyle("-fx-font-size: 20");
        tBox13.setAlignment(Pos.CENTER);
        
        VBox tBox14=new VBox(new Label("Press the key Q and P with your little fingers"), new ImageView(new Image("file:LittleFinger.gif", 485,346,true,true)));
        tBox14.setStyle("-fx-font-size: 20");
        tBox14.setAlignment(Pos.CENTER);
        
        VBox tBox15=new VBox(new Label("Press the key B with your left index finger"), new ImageView(new Image("file:LeftIndexFinger.gif", 485,346,true,true)));
        tBox15.setStyle("-fx-font-size: 20");
        tBox15.setAlignment(Pos.CENTER);
        
        VBox tBox16=new VBox(new Label("Press the key Y with your right index finger"), new ImageView(new Image("file:RightIndexFinger.gif", 485,346,true,true)));
        tBox16.setStyle("-fx-font-size: 20");
        tBox16.setAlignment(Pos.CENTER);
        
        VBox tBox17=new VBox(new Label("Press the key Z with your left little finger"), new ImageView(new Image("file:LeftLittleFinger.gif", 485,346,true,true)));
        tBox17.setStyle("-fx-font-size: 20");
        tBox17.setAlignment(Pos.CENTER);
        
        VBox tBox18=new VBox(new Label("Press the key X with your left ring finger"), new ImageView(new Image("file:LeftRingFinger.gif", 485,346,true,true)));
        tBox18.setStyle("-fx-font-size: 20");
        tBox18.setAlignment(Pos.CENTER);
        
        tutorialBox.setCenter(tBox1);
        tutorialBox.setStyle("-fx-border-style: solid; -fx-border-color: green");
        HBox tutorialIndex=new HBox(10);
        Button tpage1=new Button("1");
        Button tpage2=new Button("2");
        Button tpage3=new Button("3");
        Button tpage4=new Button("4");
        Button tpage5=new Button("5");
        Button tpage6=new Button("6");
        Button tpage7=new Button("7");
        Button tpage8=new Button("8");
        Button tpage9=new Button("9");
        Button tpage10=new Button("10");
        Button tpage11=new Button("11");
        Button tpage12=new Button("12");
        Button tpage13=new Button("13");
        Button tpage14=new Button("14");
        Button tpage15=new Button("15");
        Button tpage16=new Button("16");
        Button tpage17=new Button("17");
        Button tpage18=new Button("18");
        
        tutorialIndex.getChildren().addAll(tpage1, tpage2, tpage3, tpage4, tpage5, tpage6, tpage7, tpage8, tpage9, tpage10, tpage11, tpage12, tpage13, tpage14, tpage15,tpage16,tpage17,tpage18);
        tutorialIndex.setPadding(new Insets(40, 0, 0, 20));
        tutorialBox.setTop(tutorialIndex);
//Hover-over event
        tpage1.setOnMouseEntered(e->{
            tpage1.setUnderline(true);
      
        });
        tpage1.setOnMouseExited(e->{
            tpage1.setUnderline(false);
        });
        
        tpage2.setOnMouseEntered(e->{
            tpage2.setUnderline(true);
        });
        tpage2.setOnMouseExited(e->{
            tpage2.setUnderline(false);
        });
        
        tpage3.setOnMouseEntered(e->{
            tpage3.setUnderline(true);
        });
        tpage3.setOnMouseExited(e->{
            tpage3.setUnderline(false);
        });
        
        tpage4.setOnMouseEntered(e->{
            tpage4.setUnderline(true);
        });
        tpage4.setOnMouseExited(e->{
            tpage4.setUnderline(false);
        });
        
        tpage5.setOnMouseEntered(e->{
            tpage5.setUnderline(true);
        });
        tpage5.setOnMouseExited(e->{
            tpage5.setUnderline(false);
        });
        
        tpage6.setOnMouseEntered(e->{
            tpage6.setUnderline(true);
        });
        tpage6.setOnMouseExited(e->{
            tpage6.setUnderline(false);
        });
        
        tpage7.setOnMouseEntered(e->{
            tpage7.setUnderline(true);
        });
        tpage7.setOnMouseExited(e->{
            tpage7.setUnderline(false);
        });
        
        tpage8.setOnMouseEntered(e->{
            tpage8.setUnderline(true);
        });
        tpage8.setOnMouseExited(e->{
            tpage8.setUnderline(false);
        });
        
        tpage9.setOnMouseEntered(e->{
            tpage9.setUnderline(true);
        });
        tpage9.setOnMouseExited(e->{
            tpage9.setUnderline(false);
        });
        
        tpage10.setOnMouseEntered(e->{
            tpage10.setUnderline(true);
        });
        tpage10.setOnMouseExited(e->{
            tpage10.setUnderline(false);
        });
        
        tpage11.setOnMouseEntered(e->{
            tpage11.setUnderline(true);
        });
        tpage11.setOnMouseExited(e->{
            tpage11.setUnderline(false);
        });
        
        tpage12.setOnMouseEntered(e->{
            tpage12.setUnderline(true);
        });
        tpage12.setOnMouseExited(e->{
            tpage12.setUnderline(false);
        });
        
        tpage13.setOnMouseEntered(e->{
            tpage13.setUnderline(true);
        });
        tpage13.setOnMouseExited(e->{
            tpage13.setUnderline(false);
        });
        
        tpage14.setOnMouseEntered(e->{
            tpage14.setUnderline(true);
        });
        tpage14.setOnMouseExited(e->{
            tpage14.setUnderline(false);
        });
        
        tpage15.setOnMouseEntered(e->{
            tpage15.setUnderline(true);
        });
        tpage15.setOnMouseExited(e->{
            tpage15.setUnderline(false);
        });
        
        tpage16.setOnMouseEntered(e->{
            tpage16.setUnderline(true);
        });
        tpage16.setOnMouseExited(e->{
            tpage16.setUnderline(false);
        });
        
        tpage17.setOnMouseEntered(e->{
            tpage17.setUnderline(true);
        });
        tpage17.setOnMouseExited(e->{
            tpage17.setUnderline(false);
        });
        
        tpage18.setOnMouseEntered(e->{
            tpage18.setUnderline(true);
        });
        tpage18.setOnMouseExited(e->{
            tpage18.setUnderline(false);
        });
        
// Styling the index buttons
        tpage1.setStyle("-fx-font-size: 12");
        tpage2.setStyle("-fx-font-size: 12");
        tpage3.setStyle("-fx-font-size: 12");
        tpage4.setStyle("-fx-font-size: 12");
        tpage5.setStyle("-fx-font-size: 12");
        tpage6.setStyle("-fx-font-size: 12");
        tpage7.setStyle("-fx-font-size: 12");
        tpage8.setStyle("-fx-font-size: 12");
        tpage9.setStyle("-fx-font-size: 12");
        tpage10.setStyle("-fx-font-size: 12");
        tpage11.setStyle("-fx-font-size: 12");
        tpage12.setStyle("-fx-font-size: 12");
        tpage13.setStyle("-fx-font-size: 12");
        tpage14.setStyle("-fx-font-size: 12");
        tpage15.setStyle("-fx-font-size: 12");
        tpage16.setStyle("-fx-font-size: 12");
        tpage17.setStyle("-fx-font-size: 12");
        tpage18.setStyle("-fx-font-size: 12");

        tpage1.setOnAction(e-> {
            tutorialBox.setCenter(tBox1);
        });
        
        tpage2.setOnAction(e->{
            tutorialBox.setCenter(tBox2);
        });

        tpage3.setOnAction(e -> {
            tutorialBox.setCenter(tBox3);
        });
        tpage4.setOnAction(e -> {
            tutorialBox.setCenter(tBox4);
        });
        tpage5.setOnAction(e -> {
            tutorialBox.setCenter(tBox5);
        });
        tpage6.setOnAction(e -> {
            tutorialBox.setCenter(tBox6);
        });
        tpage7.setOnAction(e -> {
            tutorialBox.setCenter(tBox7);
        });
        tpage8.setOnAction(e -> {
            tutorialBox.setCenter(tBox8);
        });
        tpage9.setOnAction(e -> {
            tutorialBox.setCenter(tBox9);
        });
        tpage10.setOnAction(e -> {
            tutorialBox.setCenter(tBox10);
        });
        tpage11.setOnAction(e -> {
            tutorialBox.setCenter(tBox11);
        });
        tpage12.setOnAction(e -> {
            tutorialBox.setCenter(tBox12);
        });
        tpage13.setOnAction(e -> {
            tutorialBox.setCenter(tBox13);
        });
        tpage14.setOnAction(e -> {
            tutorialBox.setCenter(tBox14);
        });
        tpage15.setOnAction(e -> {
            tutorialBox.setCenter(tBox15);
        });
        tpage16.setOnAction(e -> {
            tutorialBox.setCenter(tBox16);
        });
        tpage17.setOnAction(e -> {
            tutorialBox.setCenter(tBox17);
        });
        tpage18.setOnAction(e -> {
            tutorialBox.setCenter(tBox18);
        });
       
//Buttons
//MainPageBut
        Button lessonBut = new Button("Lessons");
        lessonBut.setStyle("-fx-background-color: #3d4cad; -fx-borde-style: solid; -fx-text-fill: white; -fx-border-radius: 50px");
        lessonBut.setPrefSize(100, 20);
        lessonBut.setOnAction(e -> {
        menuLay.setCenter(lessonBox);
        });
        navigation.getChildren().add(lessonBut);
        
// Lesson and Index Selection
        page1.setOnAction(e->{
          lessonBox.setCenter(lessonVBox1);
        });
        page2.setOnAction(e->{
           lessonBox.setCenter(lessonVBox2);
        });
        page3.setOnAction(e->{
          lessonBox.setCenter(lessonVBox3);
        });
        page4.setOnAction(e->{
           lessonBox.setCenter(lessonVBox4);
        });
        page5.setOnAction(e->{
           lessonBox.setCenter(lessonVBox5);
        });
        page6.setOnAction(e->{
           lessonBox.setCenter(lessonVBox6);
        });
        page7.setOnAction(e->{
           lessonBox.setCenter(lessonVBox7); 
        });
        page8.setOnAction(e->{
           lessonBox.setCenter(lessonVBox8);
        });
        page9.setOnAction(e->{
           lessonBox.setCenter(lessonVBox9); 
        });
        page10.setOnAction(e->{
           lessonBox.setCenter(lessonVBox10); 
        });
        page11.setOnAction(e->{
           lessonBox.setCenter(lessonVBox11);
        });
        page12.setOnAction(e->{
           lessonBox.setCenter(lessonVBox12); 
        });

//Test Button
        Button testBut = new Button("Test");
        testBut.setStyle("-fx-background-color: #3d4cad; -fx-borde-style: dashed; -fx-text-fill: white; -fx-border-radius: 50px");
        testBut.setPrefSize(100, 20);
        testBut.setOnAction(e -> {
            menuLay.setCenter(testBox);
            String a = enterFileLocation.getText();
            File b = new File(a);
            File c = new File(a + ".txt"); 
            if (b.exists() == false && c.exists() == false)
            {
                startGameBut.setDisable(true);
                startGameBut.setText("Invalid File Location");
            } 
            else
            {
                startGameBut.setDisable(false);
                startGameBut.setPrefSize(150, 30);
                startGameBut.setStyle("-fx-font-size: 13");
                startGameBut.setText("Start Test");
                if (b.exists() == true) {
                    filename = b;
                } 
                else
                {
                    filename = c;
                }
                if(profileChange)
                {
                    pA.setData("File",filename.toString());
                }
            }            
        });
        navigation.getChildren().add(testBut);

//HistoryBut
        Button historyBut = new Button("History");
        historyBut.setStyle("-fx-background-color: #3d4cad; -fx-borde-style: dashed; -fx-text-fill: white; -fx-border-radius: 50px");
        historyBut.setPrefSize(100, 20);
        historyBut.setOnAction(e -> {
//updateHistory()           
            menuLay.setCenter(historyPage);
            if(!profile.equals("default")){
                historyData.clear();
                update();
            } 
        });
        navigation.getChildren().add(historyBut);
        
        Button tutorialBut = new Button("Tutorial");
        tutorialBut.setStyle("-fx-background-color: #3d4cad; -fx-borde-style: solid; -fx-text-fill: white; -fx-border-radius: 50px");
        tutorialBut.setPrefSize(100, 20);
        tutorialBut.setOnAction(e -> {
        menuLay.setCenter(tutorialBox);
        });
        navigation.getChildren().add(tutorialBut);

// Exit button
        Button exitButton=new Button("Exit");
        exitButton.setStyle("-fx-background-color: #3d4cad; -fx-borde-style: dashed; -fx-text-fill: white; -fx-border-radius: 50px");
        exitButton.setPrefSize(100,20);
        exitButton.setOnAction(e->{
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            String s = "Do you want to exit?";
            alert.setContentText(s);
            Optional<ButtonType> result=  alert.showAndWait();
            if((result.isPresent()) && (result.get()== ButtonType.OK))
                    {
                        System.exit(0);
                    }
            
        });
        navigation.getChildren().add(exitButton);
        
//Bottom Credits
        VBox credits = new VBox();
        credits.setStyle("-fx-background-color: lightgreen");
        Label credits0 = new Label("Credits" + "\n");
        Label credits1 = new Label("Created by: Abishek Bista" + "\n");
        Label credits2 = new Label("All rights reserved.");
        Label credits3 = new Label("Version: 1.0");
        credits0.setTextFill(Color.BLACK);
        credits1.setTextFill(Color.BLACK);
        credits2.setTextFill(Color.BLACK);
        credits3.setTextFill(Color.BLACK);
        credits.getChildren().addAll(credits0, credits1, credits2, credits3);
        credits0.setAlignment(Pos.CENTER);
        credits1.setAlignment(Pos.CENTER);
        credits2.setAlignment(Pos.CENTER);
        credits3.setAlignment(Pos.CENTER);
        credits.setAlignment(Pos.CENTER);
        menuLay.setBottom(credits);
        
// Updating the list of current users
       ArrayList<String> profilesList=pA.getProfiles();
       for(int i=0;i<profilesList.size();i++)
             {
                 profileBox.getItems().add(profilesList.get(i));
             }
           
//Profile Access Stuff
        profileBox.getItems().addAll("Make Profile");
        profileBox.setPromptText("Select Profile");
        profileBox.setEditable(false);
        pA.getProfiles();
        
//Creating new profile
    profileBox.valueProperty().addListener(new ChangeListener<String>(){
        @Override
        public void changed(ObservableValue ov, String t, String t1)
        {
            if(t1.equals("Make Profile")) //if user wants to create new profile
            {
                TextInputDialog dialog=new TextInputDialog();
                dialog.setTitle("Create Account");
                dialog.setHeaderText("Create an account");
                dialog.setContentText("Please enter your name");
                primaryStage.hide();
                startGameBut.setVisible(false);
                wpmrecord.setVisible(false);
                wpmlabel.setVisible(false);
             //Traditional way to get the response value
                Optional<String> result=dialog.showAndWait();
                if(result.isPresent() && !profileBox.getItems().toString().contains(result.get().toString())) //if result has a name and the name is unique
                {
                    pA.addProfile(result.get().toString());
                    profileBox.getItems().remove("Make Profile");
                    profileBox.getItems().addAll(result.get().toString(),"Make Profile");
                    pA.setProfile(result.get().toString());
                    profile=result.get().toString();
                    profileChange=true;
                    profileBox.setValue(result.get().toString());
                }
                primaryStage.show(); 
            }       
            else
            {
                if(!t1.equals("default"))
                {
                    profileChange=true;
                    profile=t1;
                    accuracyChart.setVisible(true);
                    wpmrecord.setVisible(true);
                    wpmlabel.setVisible(true);
                    startGameBut.setVisible(true);
                }
                else
                {
                    profile="default";
                    profileChange=false;
                    accuracyChart.setVisible(false);
                    wpmrecord.setVisible(false);
                    wpmlabel.setVisible(false);
                    
                }
                pA.setProfile(t1);
                Object[] data=pA.getSettings(); //fetch display mode 
                if((Integer)data[0]>=0 && (Integer)data[0]<3)
                {
                    mode=(Integer)data[0];
                    switch((Integer)data[0])
                    {
                        case 0:
                            mode0.setSelected(true);
                            break;
                        case 1:
                            mode1.setSelected(true);
                            break;
                        case 2:
                            mode2.setSelected(true);
                            break;    
                    }
                }
                else
                {
                    System.out.println("Invalid mode data");
                }
                enterFileLocation.setText((String)data[1]);
                String a = enterFileLocation.getText();
                File b = new File(a);
                File c = new File(a + ".txt");
                System.out.println(a + "\n" + b.exists() + "\n" + c.exists());
                if (b.exists() == false && c.exists() == false)
                {
                    startGameBut.setDisable(true);
                    startGameBut.setText("Invalid File Location");
                } 
                else  
                {
                    startGameBut.setDisable(false);
                    startGameBut.setText("Start Test");
                    if (b.exists() == true) {
                        filename = b;
                    } else {
                        filename = c;
                    }
                }
                if((Boolean)data[2])
                {
                    yesSpecial.setSelected(true);
                    specialCharsChoice=true;
                }
                else
                {
                    noSpecial.setSelected(true);
                    specialCharsChoice=false;
                }

                historyData.clear();
                if(!profile.equals("default"))
                {
                  update();
                }
            }
        }
    });
        primaryStage.setScene(menu);
        primaryStage.show();
    }
    
//This is the observable list for the history data
    public void update()
    {
        // This is for updating the TypingHistory Observable List
        historyData.clear();
        TypingHistory th=pA.getHistoryData();
        ArrayList<String> letters=th.getLetters();
        ArrayList<Integer> numbers=th.getMissed();
        for(int i=0;i<letters.size();i++)
        {
            historyData.add(new TypingHistoryOL((i+1), letters.get(i), numbers.get(i)));
        }
        int[] accurac= pA.getAccuracy();        
        double[] accuracy={accurac[0], accurac[1]};
       
//This is for the updating of the piechart
        accuracyChartData.clear();
        double cPercent;
        double mPercent;
        try
        {
            cPercent=(double)(accuracy[1]/accuracy[0])*100;
            mPercent=(double)((accuracy[0]-accuracy[1])/accuracy[0])*100;
        }
        catch(Exception e)
        {
            cPercent=0;
            mPercent=0;
        }
        accuracyChartData.add(new PieChart.Data("Correct-"+(int)(cPercent+.5)+"%", cPercent));
        accuracyChartData.add(new PieChart.Data("Missed-"+(int)(mPercent+.5)+"%", mPercent));
        System.out.println("The typing speed is:"+ pA.getWPM());
        
// Updating of WPM
        wpmrecord.setText(""+ pA.getWPM()); 
    }
}