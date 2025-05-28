/* Deals with a_instruction and c_instruction from an arrayList of symboltable and nowhitespaces 
and from filename and symbol table using method overloading*/

package Assembler_Classes;

import java.util.ArrayList;
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.FileReader;

public class A_c_instruction_handler {
    private static ArrayList<String> Symboltable;

    /*Deals with an a_instrucution */
    private String a_Inst(String instruction)
    {
        if(instruction.matches("\\d+"))  //checks if it is a digit
        {
            int n = Integer.valueOf(instruction);
            return Integer.toBinaryString(n); //binary value is returned
        }

        else
        {

            for (String x : Symboltable)  //checks in sybol table 
            {
                String symbol = x.substring(0, x.indexOf("-"));

                if(symbol.equals(instruction))
                {

                    int n = Integer.valueOf(x.substring(x.indexOf("-")+1));
                    return Integer.toBinaryString(n); //corresponding value is returned

                }
            }

        }

        return "fail"; //To stop return error and for debugging
    }
    

    /* Deals with c_instruction */
    private String c_Inst(String instruction)
    {
        String dest="null",comp,jump="null"; //dest and jump is by default null

        //divide into dest,comp,jump
        if(instruction.contains("="))
        {
            if(instruction.contains(";"))
            {
                jump = instruction.substring( instruction.indexOf(";")+1 );
                comp = instruction.substring( instruction.indexOf("=")+1, instruction.indexOf(";"));
                dest = instruction.substring( 0,instruction.indexOf("="));
            }

            else
            {
                comp = instruction.substring(instruction.indexOf("=")+1);
                dest = instruction.substring(0,instruction.indexOf("="));
            }
        }
        else if(instruction.contains(";"))
        {
            comp = instruction.substring(0,instruction.indexOf(";"));
            jump = instruction.substring(instruction.indexOf(";")+1);
        }
        else
        {
            comp = instruction;
        }
        return "111" + compTable(comp) + destTable(dest) + jumpTable(jump);
        
    }

    /* Predefined table and method to retrurn value from key */
    private String destTable(String dest)
    {
        HashMap<String,String> d=new HashMap<>();

        d.put("null", "000");d.put("M", "001");d.put("D", "010");d.put("MD", "011");d.put("A", "100");d.put("AM", "101");
        d.put("AD", "110"); d.put("AMD", "111");

        return d.get(dest);
    }


    private String compTable(String comp)
    {
        
        HashMap<String,String> compTable_a0=new HashMap<>();

        compTable_a0.put("0", "101010"); compTable_a0.put("1" , "111111");compTable_a0.put("-1" , "111010");compTable_a0.put("D", "001100");compTable_a0.put("A", "110000");compTable_a0.put("!D", "001101");
        compTable_a0.put("!A", "110001");compTable_a0.put("-D", "001111");compTable_a0.put("-A", "110011");compTable_a0.put("D+1", "011111");compTable_a0.put("A+1","110111");compTable_a0.put("D-1","001110");
        compTable_a0.put("A-1","110010");compTable_a0.put("D+A","000010");compTable_a0.put("D-A","010011");compTable_a0.put("A-D","000111");compTable_a0.put("D&A","000000");compTable_a0.put("D|A","010101");
        
        HashMap<String,String> compTable_a1=new HashMap<>();

        compTable_a1.put("M", "110000"); compTable_a1.put("!M", "110001");compTable_a1.put("-M", "110011");compTable_a1.put("M+1", "110111");compTable_a1.put("M-1", "110010");compTable_a1.put("D+M", "000010");
        compTable_a1.put("D-M", "010011");compTable_a1.put("M-D", "000111");compTable_a1.put("D&M", "000000");compTable_a1.put("D|M", "010101");

        if(compTable_a0.containsKey(comp))
        {
            return "0" + compTable_a0.get(comp);
        }
        else
        {
            return "1" + compTable_a1.get(comp);
        }
    }

    private String jumpTable(String jump)
    {
        HashMap<String,String> j=new HashMap<>();

        j.put("null", "000");j.put("JGT", "001");j.put("JEQ", "010");j.put("JGE", "011");j.put("JLT", "100");j.put("JNE", "101");
        j.put("JLE", "110"); j.put("JMP", "111");

        return j.get(jump);
    }


    /*Deals with  a and c instruction from arraylists */
    public ArrayList<String> acInstructionHandler(ArrayList<String> Nowhitespaces, ArrayList<String> Symboltable)
    {
        A_c_instruction_handler.Symboltable=Symboltable;

        String instruction;

        ArrayList<String> Assemblycode=new ArrayList<>();

            for (String line : Nowhitespaces) {
                
                if(line.startsWith("("))// Since it is a label we ignore
                {
                    continue;
                }


                if(line.startsWith("@"))
                {
                    instruction = a_Inst(line.substring(1));

                    if(instruction.length()<15)
                    {
                        instruction = "0".repeat(15-instruction.length()) + instruction; //To have 15 digits in binary value
                    }
                    
                    instruction = "0" + instruction;

                }

                else
                {
                    instruction = c_Inst(line);
                }
                Assemblycode.add(instruction);
            }

        return Assemblycode;
    }


    /* Deals with a and c instruction from file name and symbol table */
    public ArrayList<String> acInstructionHandler(String Filename, ArrayList<String> Symboltable)
    {
        A_c_instruction_handler.Symboltable=Symboltable;
        String instruction;
        ArrayList<String> Assemblycode = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(Filename));
            String line;
            while((line = reader.readLine())!=null)
            {
                if(line.startsWith("("))
                {
                    continue;
                }


                if(line.startsWith("@"))
                {
                    instruction = a_Inst(line.substring(1));
                    if(instruction.length()<15)
                    {
                        instruction = "0".repeat(15-instruction.length()) + instruction;
                    }
                    instruction = "0" + instruction;
                }

                else
                {
                    instruction = c_Inst(line);
                }


                Assemblycode.add(instruction);
            }
            reader.close();

        } 
        catch (Exception e) {
            System.out.println(e);
        }
        return Assemblycode;
    }

    
}
