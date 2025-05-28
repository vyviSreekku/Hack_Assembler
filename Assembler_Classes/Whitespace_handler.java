/*This is a class which will remove all whitespaces from the data of the file and return a arraylist with that data */

package Assembler_Classes;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Whitespace_handler {

    public ArrayList<String> remove_whitespaces(String inputFileName)
    {
        ArrayList<String> Data=new ArrayList<String>();

        try {
            BufferedReader reader=new BufferedReader(new FileReader(inputFileName));//A reader object

            String line;

            while((line=reader.readLine())!=null)//Checks whether each line is null or not
            {
                line=line.replaceAll(" ", "");   // Replaces all spaces with no space
                line=line.replaceAll("\t", "");   //Replaces all tab space if any;


                if(line.isEmpty())                  // If line becomes null after these operation we skip it
                {
                    continue;
                }

                else
                {
                    if(line.startsWith("/"))  //else we check if line starts with '/',if so we skip it
                    {
                        continue;
                    }
                }

                if( line.contains("/") )  // check if line contains '/',if so we break until that
                {
                    line = line.substring(0,line.indexOf("/"));
                }

                Data.add(line);  //after all operations and conditions are met,it is added to arraylist
            }
            reader.close();
        }


        catch(Exception e)
        {
            System.out.println(e);
        }


        return Data; // arraylist is returned
    }
}