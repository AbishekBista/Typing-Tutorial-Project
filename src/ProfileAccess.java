import java.io.*;
import java.util.Scanner;
import org.w3c.dom.*;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ProfileAccess
{ 
   private final File file=new File("profile/ProfileData.xml");
   String activeProf= "default";  
   public ProfileAccess()
   {
       if(!file.exists())
       {
           try
               {
                   DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
                   DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
                   Document doc=dBuilder.newDocument();
                   //root element
                   Element rootElement=doc.createElement("Profiles");
                   doc.appendChild(rootElement);
                   
                   //Creates the default profile
                   Element defaultProf=doc.createElement("default");
                   rootElement.appendChild(defaultProf);
                   
                   // Creating the settings element
                   Element settings=doc.createElement("Settings");
                   defaultProf.appendChild(settings);

                    //Assigns all the default setting values                 
                    Element defMode = doc.createElement("Mode");
                    defMode.appendChild(doc.createTextNode("0"));
                    settings.appendChild(defMode);

                    //default file
                    Element defFile = doc.createElement("File");
                    defFile.appendChild(doc.createTextNode(""));
                    settings.appendChild(defFile);

                    //default special
                    Element defSpecChar = doc.createElement("SpecChars");
                    defSpecChar.appendChild(doc.createTextNode("false"));
                    settings.appendChild(defSpecChar);
                  
                   //player data
                   Element defData=doc.createElement("Data");
                   defaultProf.appendChild(defData);
                  
                   //subsets of player data
                   //individual files history
                   Element defIFH=doc.createElement("File History");
                   defData.appendChild(defIFH);
                   
                   // keys missed history
                   Element defMissed=doc.createElement("Missed");
                   defData.appendChild(defMissed);
                   
                   // typing accuracy
                   Element defAccuracy=doc.createElement("Accuracy");   
                   defData.appendChild(defAccuracy);
                   
                   //typing subs
                   // Total letters
                   Element defAccuracyTotal=doc.createElement("Total");
                   defAccuracyTotal.appendChild(doc.createTextNode("0"));
                   defAccuracy.appendChild(defAccuracyTotal);
                   
                   // Correct letters
                   Element defAccuracyCorrect=doc.createElement("Correct");   
                   defAccuracyCorrect.appendChild(doc.createTextNode("0"));
                   defAccuracy.appendChild(defAccuracyCorrect);
                   
                   //Creating words per minute element
                   Element wordsPerMinute=doc.createElement("WPM");
                   defData.appendChild(wordsPerMinute);
                   
                   //write the content into xml file
                   TransformerFactory transformerFactory=TransformerFactory.newInstance();
                   Transformer transformer=transformerFactory.newTransformer();
                   DOMSource source=new DOMSource(doc);
                   StreamResult result=new StreamResult(file);
                   transformer.transform(source,result);
                   
                   //Output to console for testing
                   StreamResult consoleResult=new StreamResult(System.out);
                   transformer.transform(source,consoleResult);
               }
           catch(Exception e)
           {
               e.printStackTrace();           
               }
           }
       }
   
   public void addProfile(String name)
   {
       String help=new String(name).trim().replaceAll(" ", "");
       System.out.println("I was called.");
       try
       {
       DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
       DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
       Document doc=docBuilder.parse(file);
       Node profilesList=doc.getFirstChild();
       Element newProfile= doc.createElement(help);
       profilesList.appendChild(newProfile);
       
       // Creating the settings element
       Element settings=doc.createElement("Settings");
       newProfile.appendChild(settings);
                 
        //Assigns all the default setting values                 
        Element defMode = doc.createElement("Mode");
        defMode.appendChild(doc.createTextNode("0"));
        settings.appendChild(defMode);
       
        //default file
        Element defFile = doc.createElement("File");
        defFile.appendChild(doc.createTextNode(""));
        settings.appendChild(defFile);
                   
        //default special
        Element defSpecChar = doc.createElement("SpecChars");
        defSpecChar.appendChild(doc.createTextNode("false"));
        settings.appendChild(defSpecChar);
        
        //player data
        Element defData = doc.createElement("Data");
        defFile.appendChild(doc.createTextNode(""));
        newProfile.appendChild(defData);
        
        // keys missed history
           Element defMissed=doc.createElement("Missed");
           defData.appendChild(defMissed);

           // typing accuracy
           Element defAccuracy=doc.createElement("Accuracy");   
           defData.appendChild(defAccuracy);

           //typing subs
           Element defAccuracyTotal=doc.createElement("Total");
           defAccuracyTotal.appendChild(doc.createTextNode("0"));
           defAccuracy.appendChild(defAccuracyTotal);

           Element defAccuracyCorrect=doc.createElement("Correct");  
           defAccuracyCorrect.appendChild(doc.createTextNode("0"));
           defAccuracy.appendChild(defAccuracyCorrect);
           
           //words per minute
           Element wordsPerMinute=doc.createElement("WPM");
           wordsPerMinute.appendChild(doc.createTextNode("0"));
           defData.appendChild(wordsPerMinute);
        
       // Writing the xml data 
       TransformerFactory transformerFactory=TransformerFactory.newInstance();
       Transformer transformer=transformerFactory.newTransformer();
       DOMSource source=new DOMSource(doc);
       StreamResult result=new StreamResult(file);
       transformer.transform(source,result);
   }
   catch(Exception e){
       System.out.println("I failed.");
       e.printStackTrace();
   }
   }
   
   public void setData(String toChange, String toWhat)
   {
      try
      {
        DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
        Document doc=docBuilder.parse(file);
        Element profileSelect=(Element) doc.getElementsByTagName(activeProf).item(0);
        Element settings=(Element)profileSelect.getElementsByTagName("Settings").item(0);
        settings.getElementsByTagName(toChange).item(0).setTextContent(toWhat);     
        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        DOMSource source=new DOMSource(doc);
        StreamResult result=new StreamResult(file);
        transformer.transform(source,result);
        } 
      catch(Exception e)
      {
          System.out.println("Problem updating xml data.");
      }
   }
  
   public void setOtherData(String key)
   {
       try
      {
        DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
        Document doc=docBuilder.parse(file);
        Node profileSelect= doc.getElementsByTagName(activeProf).item(0);
        Element profile=(Element)profileSelect;
        Element subdata=(Element)profile.getElementsByTagName("Data").item(0);
        Element data= (Element)subdata.getElementsByTagName("Missed").item(0);
        if(data.getElementsByTagName("_"+key.codePointAt(0)).item(0)==null)
        {
            Element newEl = doc.createElement("_"+key.codePointAt(0));
            data.appendChild(newEl);
            newEl.appendChild(doc.createTextNode("1"));
        }
        else
        {
            Node oldDataE=(Element)data.getElementsByTagName("_"+key.codePointAt(0)).item(0);
            String oldDataS=oldDataE.getTextContent();
            int oldDataN=Integer.parseInt(oldDataS);
            oldDataN++;
            oldDataE.setTextContent(oldDataN+"");
        }
        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        DOMSource source=new DOMSource(doc);
        StreamResult result=new StreamResult(file);
        transformer.transform(source,result);
        }
      catch(Exception e)
      {
          e.getStackTrace();
          System.out.println("Problem updating xml data");
      }
   }
   
   public TypingHistory getHistoryData()
   {
       TypingHistory tH=new TypingHistory();
       try
       {
        DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
        Document doc=docBuilder.parse(file);
        Node profileSelect= doc.getElementsByTagName(activeProf).item(0);
        Element profile=(Element)profileSelect;
        Element subdata=(Element)profile.getElementsByTagName("Data").item(0);
        Element data= (Element)subdata.getElementsByTagName("Missed").item(0);
        NodeList missedLets=data.getChildNodes();
        for(int i=0;i<=missedLets.getLength()-1;i++)
        {
            Node currentLet=missedLets.item(i);
            String nodeName = currentLet.getNodeName();
            nodeName=nodeName.substring(1);
            int unicodeNumb=Integer.parseInt(nodeName);
            String name= new String(Character.toChars(unicodeNumb));
            if(unicodeNumb==32)
            {
                name="Space";
            }
            
            if(unicodeNumb==9)
            {
                name="Tab";
            }   
            int number=Integer.parseInt(currentLet.getTextContent());
            tH.addData(name, number);
        }
       }catch(Exception e)
       {
           System.out.println("Problem getting xml data");
           e.printStackTrace();
       }
    return tH;  
   }
  
   public void setProfile(String profile)
   {
       activeProf=profile;
   }
   
   public int[] getAccuracy()
   {
       int[] accuracy=new int [2];
       //[0]=total keys
       //[1]=correct keys
        try
       {
        DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
        Document doc=docBuilder.parse(file);
        Node profileSelect= doc.getElementsByTagName(activeProf).item(0);
        Element profile=(Element)profileSelect;
        Element subdata=(Element)profile.getElementsByTagName("Data").item(0);
        Element data=(Element)subdata.getElementsByTagName("Missed").item(0);
        Element accuracyE=(Element)subdata.getElementsByTagName("Accuracy").item(0);
        accuracy[0]=Integer.parseInt(accuracyE.getElementsByTagName("Total").item(0).getTextContent());
        accuracy[1]=Integer.parseInt(accuracyE.getElementsByTagName("Correct").item(0).getTextContent());
       }catch(Exception e)
       {
           e.printStackTrace();
       }
       return accuracy;
   }
   
   public void setWPM(int wpm)
   {
       try
       {
           DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
           DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
           Document doc=docBuilder.parse(file);
           Node profileSelect=doc.getElementsByTagName(activeProf).item(0);
           Element profile=(Element)profileSelect;
           Element subdata=(Element)profile.getElementsByTagName("Data").item(0);
           subdata.getElementsByTagName("WPM").item(0).setTextContent(""+wpm);
           TransformerFactory transformerFactory=TransformerFactory.newInstance();
           Transformer transformer=transformerFactory.newTransformer();
           DOMSource source=new DOMSource(doc);
           StreamResult result=new StreamResult(file);
           transformer.transform(source,result);    
       }catch(Exception e)
       {
           System.out.println("Problem in WPM updater.");
           e.printStackTrace();
       }   
   }
   
   public int getWPM()
   {
       int speed=0;
        try
       {
        DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
        Document doc=docBuilder.parse(file);
        Node profileSelect= doc.getElementsByTagName(activeProf).item(0);
        Element profile=(Element)profileSelect;
        Element subdata=(Element)profile.getElementsByTagName("Data").item(0);  
        speed=Integer.parseInt(subdata.getElementsByTagName("WPM").item(0).getTextContent());
       }catch(Exception e)
       {
           e.printStackTrace();
       }
       return speed;
   }
   
   public void letterOccurred(boolean correct)
   {
       int a;
       int ab;
       try
       {
        DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
        Document doc=docBuilder.parse(file);
        Node profileSelect= doc.getElementsByTagName(activeProf).item(0);
        Element profile=(Element)profileSelect;
        Element subdata=(Element)profile.getElementsByTagName("Data").item(0);
        Element data=(Element)subdata.getElementsByTagName("Missed").item(0);
        Element accuracyE=(Element)subdata.getElementsByTagName("Accuracy").item(0);
        a=Integer.parseInt(accuracyE.getElementsByTagName("Total").item(0).getTextContent());
        ab=Integer.parseInt(accuracyE.getElementsByTagName("Correct").item(0).getTextContent());
        a++;
        ab++;
        accuracyE.getElementsByTagName("Total").item(0).setTextContent(String.valueOf(a));
        if(correct)
        {
            accuracyE.getElementsByTagName("Correct").item(0).setTextContent(String.valueOf(ab));
        }
        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        DOMSource source=new DOMSource(doc);
        StreamResult result=new StreamResult(file);
        transformer.transform(source,result);
       }catch(Exception e)
       {
           System.out.println("Problem in accuracy updater");
           e.printStackTrace();
       }
   }

   // this will be specific settings data
   public Object[] getSettings()
   {
       Object[] data=new Object[4];
       try
       {
       DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
       Document doc=dBuilder.parse(file);
       doc.getDocumentElement().normalize();
       Element profileSelect=(Element) doc.getElementsByTagName(activeProf).item(0);
       Element settings=(Element)profileSelect.getElementsByTagName("Settings").item(0);;
       data[0]=Integer.parseInt(settings.getElementsByTagName("Mode").item(0).getTextContent());
       data[1]=(String)settings.getElementsByTagName("File").item(0).getTextContent();
       data[2]=new Boolean(settings.getElementsByTagName("SpecChars").item(0).getTextContent());       
       }catch(Exception e)
       {System.out.println("Failed to access data");
       }
       return data;
   }
   
   public boolean areProfiles()
   {
       boolean profilesPresent=false;
       return profilesPresent;
   }
   
   public ArrayList<String> getProfiles()
   {
       ArrayList<String> profiles=new ArrayList<String>();
       try
       {    
       DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
       Document doc=dBuilder.parse(file);
       doc.getDocumentElement().normalize();
       Node profilesNode=doc.getFirstChild();
       NodeList profilesList=profilesNode.getChildNodes();
       for(int i=0;i<profilesList.getLength();i++)
       {
           //System.out.println(profilesList.item(i).getNodeName().toString()); testing Node Name in Profile Selection
           profiles.add(profilesList.item(i).getNodeName().toString());
       }
       }catch(Exception ex)
       {
           Alert alert=new Alert(AlertType.ERROR);
           alert.setTitle("Error");
           alert.setHeaderText("Problem accessing previous profile data");
           alert.setContentText("The data stored at "+ file+" has become unreadable.Please either delete the file or attempt to recover your data by copying all the remaining data under your porfile under your name then delete it.");
           alert.showAndWait();
           System.exit(0);
       }
       return profiles;
   }
   
   // Function to delete profile
   public void deleteProfile()
   {
       try
       {
        DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
        Document doc=docBuilder.parse(file);
        Node profileSelect= doc.getFirstChild();
        Element profileSelectE=(Element)profileSelect;
        Node profile=profileSelectE.getElementsByTagName(activeProf).item(0);
        profileSelect.removeChild(profile);
        TransformerFactory transformerFactory=TransformerFactory.newInstance();
        Transformer transformer=transformerFactory.newTransformer();
        DOMSource source=new DOMSource(doc);
        StreamResult result=new StreamResult(file);
        transformer.transform(source,result);
       }catch(Exception e)
       {
           e.printStackTrace();
       }
   }
}