import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import Assembler_Classes.*;


public class Assembler {
    private static void assemblyCodeGenerator(String inputFilename, String outputFilename)//Method to generate assembly code
    {
        Whitespace_handler WH = new Whitespace_handler();
        Symbol_Variables_handler SVH = new Symbol_Variables_handler();
        A_c_instruction_handler ACIH =new A_c_instruction_handler();

        ArrayList<String> noWhiteSpace = new ArrayList<>();
        ArrayList<String> symbolTable = new ArrayList<>();
        ArrayList<String> assemblyCode = new ArrayList<>();

        noWhiteSpace = WH.remove_whitespaces(inputFilename);//remove whitespaces and store in an arraylist
        symbolTable = SVH.SymbolTable(noWhiteSpace);// makes a syboltable from the array of nowhitespaces
        assemblyCode = ACIH.acInstructionHandler(noWhiteSpace, symbolTable);// generate assembly code from an array

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename));
            
            for (String line : assemblyCode) {
                writer.write(line+"\n");
            }

            writer.close();

        } 
        
        catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        assemblyCodeGenerator("D:\\EOC_ASSEMBLER\\assembly.asm", "D:\\EOC_ASSEMBLER\\assembly.hack");
    }
}
