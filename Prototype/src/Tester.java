import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Tester {
    private File testFolder;
    public Tester(){
        String currentDirectory = System.getProperty("user.dir");
        testFolder= new File(currentDirectory, "test");
    }

    public void runAll(){
        File[] testCaseFolders=testFolder.listFiles();
        ArrayList<File> cases=new ArrayList<File>(Arrays.asList(testCaseFolders));
        Collections.sort(cases);

        for(File f:cases){
            if(f.isDirectory()){
                runInput(f);
                compareOutput(f);
            }
        }
    }

    private void compareOutput(File caseFolder) {
        File expected=new File(caseFolder, "expected.txt");
        File output=new File(caseFolder, "output.txt");
        if(expected.exists() && output.exists()){
            try {
                Scanner expReader=new Scanner(expected);
                Scanner outReader=new Scanner(output);
                int row=1;
                boolean error=false;
                while(expReader.hasNextLine() && outReader.hasNextLine() && !error){
                    if(expReader.nextLine().equals(outReader.nextLine())){}
                    else{
                        error=true;
                        System.out.println(caseFolder.getName()+": Error at line "+row+" of output");
                    }
                    ++row;
                }
                if(!expReader.hasNextLine() && !outReader.hasNextLine() && !error){
                    System.out.println(caseFolder.getName()+" OK");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    private void runInput(File caseFolder) {
        File inputFile=new File(caseFolder, "input.txt");
        File outputFile=new File(caseFolder, "output.txt");
        InputStream stdin=System.in;
        PrintStream stdout=System.out;

        try {

            System.setIn(new FileInputStream(inputFile));
            System.setOut(new PrintStream(outputFile));
            App.processInput();
            System.out.flush();
            System.setIn(stdin);
            System.setOut(stdout);

        } catch (FileNotFoundException e) {
            System.setIn(stdin);
            System.setOut(stdout);
            e.printStackTrace();
        }

    }


}
