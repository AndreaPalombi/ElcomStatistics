import java.util.Scanner;

public class FileCounter { /*this class reads the file as input and count the characters for each line.
    It takes the lines and breaking it down checking the lines number with the characters and the words,
    assuming that words are broken up by spaces or end of line characters */

    //initialise variables

    int numLines = 0;
    int numWords = 0;
    int numChars = 0;


    public FileCounter(Scanner file) throws Exception{

        Scanner tempFile = file;

        while(tempFile.hasNextLine()){
            String currentLine = tempFile.nextLine();
            int size = currentLine.length();
            boolean foundDiv = true; // No words on this line
            boolean foundChar; //No char found yet

            for(int i=0; i < size; i++){
                if(currentLine.charAt(i)==' '){
                    //break found
                    foundDiv = true;
                    foundChar = false; //no char found since last break
                } else{
                    //char found
                    foundChar = true;
                    numChars++;
                }
                if(foundChar && foundDiv){
                    numWords++;
                    foundDiv = false; //no break found since last char.
                }
            }
            numLines++;
        }tempFile.close();
        //All data has been found.


        //Print output on console
        System.out.println("Number of words: " + numWords);
        System.out.println("Number of Lines: " + numLines);
        System.out.printf("Num chars: %.2f", (float)numChars / numWords);
    }
}
