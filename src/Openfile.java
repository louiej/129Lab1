/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author LJR
 */
public class Openfile{

    //Declare Variable
    JFileChooser fileChooser = new JFileChooser();
    StringBuilder sb = new StringBuilder();
    FileFilter filter = new FileNameExtensionFilter("Input File","in");
    
    private java.io.File file;

        public void PickMe() throws Exception{
               
            fileChooser.setFileFilter(filter);
            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ){
                
                
                //get the file
                file = fileChooser.getSelectedFile();
                
                //create scanner
                Scanner input = new Scanner(file);
                
                //read text from file
                while(input.hasNext()){
                    sb.append(input.nextLine());
                    sb.append("\n");
                }
                
                input.close();
             }
                else{
                    sb.append("No file was selected.");
                }
        }

        public String getInputString() {
            return sb.toString();
        }
}