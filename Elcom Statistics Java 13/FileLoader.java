import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

//This class FileLoader contains the software GUI the user interacts with.

public class FileLoader extends JFrame implements ActionListener{

    JButton selectFileButton = new JButton("Select File");
    JButton countButton = new JButton("Count");
    File file;


    FileLoader() { //constructor which call the main Frame to load the file and execute the count

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,20,35));

        JLabel title = new JLabel("Elcom statistics".toUpperCase());

        selectFileButton.addActionListener(this); //create a button to open a selection window (launch the FileChooser)
        selectFileButton.setLayout(new FlowLayout(FlowLayout.CENTER));

        countButton.addActionListener(this);//create a button to execute the count
        countButton.setLayout(new FlowLayout(FlowLayout.CENTER));
        countButton.setEnabled(false); //this button will be enabled only if a file has been loaded


        this.add(title);
        this.add(selectFileButton);
        this.add(countButton);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(250,250));
        this.setResizable(false);
        this.setVisible(true);
    }


    public void counterMessage(FileCounter fileCounter){ //this method opens a window which displays the output
        JDialog d = new JDialog(this, "Count",true);
        d.setLayout(new FlowLayout(FlowLayout.CENTER,40,20));
        JButton confirm = new JButton("OK");
        d.add(new JLabel("Number of words: ".toUpperCase() + fileCounter.numWords));
        d.add(new JLabel("Number of lines: ".toUpperCase() + fileCounter.numLines));
        d.add(new JLabel("Mean word length: ".toUpperCase() +
                String.format("%.2f",(float)fileCounter.numChars / fileCounter.numWords)));
        d.add(confirm);

        confirm.addActionListener(e -> d.setVisible(false));

        d.setSize(250,250);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }


    public void actionPerformed(ActionEvent e){

        if(e.getSource() == selectFileButton){ //if "Select" button is pressed
            JFileChooser fileChooser = new JFileChooser();

            int response = fileChooser.showOpenDialog(null); //select the file to open

            if(response == JFileChooser.APPROVE_OPTION){
                file = new File(fileChooser.getSelectedFile().getAbsolutePath()); //get the file path for selection
                confirmMessage();
                countButton.setEnabled(true);
            }
        }

        if(e.getSource() == countButton){ //if "Count" button is pressed

            try {
                FileCounter fileCounter = new FileCounter(new Scanner(file)); //FileCounter object instance
                counterMessage(fileCounter);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }


    public void confirmMessage(){//this method opens a window to confirm to the user that a file has been loaded
        JDialog d = new JDialog(this, "Loader",true);
        d.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        JButton confirm = new JButton("OK");
        d.add(new JLabel("File loaded"));
        d.add(confirm);

        confirm.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                d.setVisible(false);
            }
        });

        d.setSize(150,150);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }

}
