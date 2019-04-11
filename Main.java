import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        //Input scanner.
        Scanner in = new Scanner(System.in);

        //Declare file names.
        String math1 = "math1";
        String math2 = "math2";
        String math3 = "math3";

        String science1 = "science1";
        String science2 = "science2";
        String science3 = "science3";

        String history1 = "history1";
        String history2 = "history2";
        String history3 = "history3";

        //Declare file path for FileReader to find later.
        String math1Route = "/Users/ervinenriquez/Documents/IntelliJ Projects/Data Structures Labs/Group Project/src/math1";
        String math2Route = "/Users/ervinenriquez/Documents/IntelliJ Projects/Data Structures Labs/Group Project/src/math2";
        String math3Route = "/Users/ervinenriquez/Documents/IntelliJ Projects/Data Structures Labs/Group Project/src/math3";
        String history1Route = "/Users/ervinenriquez/Documents/IntelliJ Projects/Data Structures Labs/Group Project/src/history1";
        String history2Route = "/Users/ervinenriquez/Documents/IntelliJ Projects/Data Structures Labs/Group Project/src/history2";
        String history3Route = "/Users/ervinenriquez/Documents/IntelliJ Projects/Data Structures Labs/Group Project/src/history3";
        String science1Route = "/Users/ervinenriquez/Documents/IntelliJ Projects/Data Structures Labs/Group Project/src/science1";
        String science2Route = "/Users/ervinenriquez/Documents/IntelliJ Projects/Data Structures Labs/Group Project/src/science2";
        String science3Route = "/Users/ervinenriquez/Documents/IntelliJ Projects/Data Structures Labs/Group Project/src/science3";

        //Array that holds user's answers.
        char[] answers = new char[5];
        //ArrayList that will hold the questions
        ArrayList<Character> questions = new ArrayList();

        //Subject var
        String sub;
        //Difficulty var
        int diff;

        //Prompt user to select a subject.
        subPrompt();
        sub = in.nextLine().toLowerCase();
        while (subChecker(sub)) {
            subPrompt();
            sub = in.nextLine().toLowerCase();
        }

        //Promt user to select difficulty.
        diffPrompt();
        diff = in.nextInt();
        while (diff < 0 || diff > 3) {
            diffPrompt();
            diff = in.nextInt();
        }

        //Convert difficulty level from integer to a string.
        String diffS = String.valueOf(diff);


        //Concatenate subject and difficulty.
        String selection = sub + diffS;

        //Pass selection to FileReader.
        switch(selection) {
            case "math1": selection = math1Route;
            break;
            case "math2": selection = math2Route;
            break;
            case "math3": selection = math3Route;
            break;
        }

        FileReader a = new FileReader(selection);

        char[] poo = new char[1000];
        a.read(poo);

        for (int i = 0; i < poo.length; i++) {
            int counter = 0;
            if(poo[i] == ' ') {
                System.out.print(poo[i]);
                counter++;
                if (counter > 1) {
                    break;
                }
            } else {
                System.out.print(poo[i]);
            }
        }







    }

    //Method to prompt user to select a subject.
    public static void subPrompt() {
        System.out.println("Please select a subject. (Math/Science/History)");
    }

    //Method to prompt user to select a difficulty.
    public static void diffPrompt(){
        System.out.println("Please select a difficulty. (1-3)");
    }

    //Checks if user inputted a valid value.
    public static boolean subChecker(String in) {
        boolean tf = true;
        String math = "math";
        String history = "history";
        String science = "science";

        if (in.equalsIgnoreCase(math)) {
            tf = false;
        } else if (in.equalsIgnoreCase(history)) {
            tf = false;
        } else if (in.equalsIgnoreCase(science)) {
            tf = false;
        }

        return tf;
    }
}
