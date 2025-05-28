/*This is a class which will make a symbolTable from the data from both arraylist and file using method overloading
 and return a arraylist with that data */

package Assembler_Classes;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class Symbol_Variables_handler {

    /* Creates symbol table from arraylist of data with no_whitespaces*/
    
    public ArrayList<String> SymbolTable(ArrayList<String> No_whitespaces)
    {
        //Predefined ArrayList
        ArrayList<String> Symboltable=new ArrayList<String>(List.of("R0-0","R1-1","R2-2","R3-3","R4-4","R5-5","R6-6","R7-7","R8-8","R9-9",
        "R10-10","R11-11","R12-12","R13-13","R14-14","R15-15","SCREEN-16384","KBD-24576","SP-0","LCL-1","ARG-2","THIS-3","THAT-4"));

            int i=0;

            for (String line: No_whitespaces) 
            {
                
                if(line.startsWith("("))    //checks if it is a label
                    {
                        String label = line.substring(1, line.indexOf( ")" ));  //takes the value from index 1 to index before ')'
                        Symboltable.add(label + "-" + String.valueOf(i));
                        continue;//To ignore line no. if it is a label
                    }  
                    i++;
            }

            int n=16; //starting value is 16
            
            for (String line : No_whitespaces) {
                
                boolean a = true;
                if(line.startsWith("@"))
                    {
                        String variable = line.substring(1, line.length());
                        int temp = variable.compareTo("\\d");
                        if(temp == 0)
                        {
                            continue;
                        }


                        for (String x : Symboltable) {
                            x=x.substring(0, x.indexOf("-"));

                            if(variable.equals(x))
                            {
                                a = false;
                                break;
                            }
                        }


                        if(a)
                        {
                            
                            Symboltable.add(variable + "-" + String.valueOf(n));
                            n++;
                        }

                }
        }
        return Symboltable;
    }

/*Creates symbol table from file with no_whitespaces and same logic is applied*/
    public ArrayList<String> SymbolTable(String inputFileName)
    {
        //prededined symbol table
        ArrayList<String> Symboltable = new ArrayList<String>(List.of("R0-0","R1-1","R2-2","R3-3","R4-4","R5-5","R6-6","R7-7","R8-8","R9-9",
        "R10-10","R11-11","R12-12","R13-13","R14-14","R15-15","SCREEN-16384","KBD-24576","SP-0","LCL-1","ARG-2","THIS-3","THAT-4"));


        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFileName)); //A reader object

            String line;
            int i = 0;

            reader.mark(8000); // Set mark at first line for 8000characters

            while( (line = reader.readLine()) != null)//Checks whether each line is null or not
            {
                if(line.startsWith("("))
                    {
                        String label = line.substring(1, line.indexOf(")"));
                        Symboltable.add(label + "-" + String.valueOf(i));
                        continue;

                    }  
                    i++;
            }
            reader.reset();//reader goes back to first line

            int n = 16;
            while( (line = reader.readLine()) != null)//Checks whether each line is null or not
            {
                boolean a = true;
                if(line.startsWith("@"))
                    {
                        String variable = line.substring(1, line.length());
                        int temp = variable.compareTo("\\d");
                        if(temp == 0)
                        {
                            continue;
                        }


                        for (String x : Symboltable) {
                            x=x.substring(0, x.indexOf("-"));

                            if(variable.equals(x))
                            {
                                a = false;
                                break;
                            }
                        }


                        if(a)
                        {
                            
                            Symboltable.add(variable + "-" + String.valueOf(n));
                            n++;
                        }

                    }  
            }
            reader.close();

        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return Symboltable;
    }
}