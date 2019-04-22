import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    private static LinkedList<score> scores = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        boolean onOff = true;

            //Input scanner.
            Scanner in = new Scanner(System.in);

            //Start program.
            while (onOff) {
                menu();
                String command = in.nextLine();
                switch (command) {
                    case "quiz":
                        stQuiz();
                        break;
                    case "score":
                        displayScore();
                        break;
                    case "clear":
                        clear();
                        break;
                    case "quit":
                        onOff = false;
                        break;
                }
        }

    } //End of Class


    //8*** Outside Methods***8//

    public static void menu(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("////////////////////////////////////////////////////");
        System.out.println("/////////////// --- Quiz Menu --- //////////////////");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("/////////////// --- Commands --- ///////////////////");
        System.out.println("////////                                    ////////");
        System.out.println("////////   Syntax:      Description:        ////////");
        System.out.println("////////                                    ////////");
        System.out.println("////////    quiz        Take a quiz.        ////////");
        System.out.println("////////    score       View scores.        ////////");
        System.out.println("////////	quit        Quit.               ////////");
        System.out.println("////////                                    ////////");
        System.out.println("////////////////////////////////////////////////////\n");
        System.out.println("~~~ Please enter a command from the menu above. ~~~");
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public static void stQuiz() throws IOException {

        //Clear previous console log.
        clear();

        //Scanner
        Scanner in = new Scanner(System.in);

        //Declare file names.

        //Math
        String math1 = "math1", science1 = "science1", history1 = "history1";
        String math2 = "math2", science2 = "science2", history2 = "history2";
        String math3 = "math3", science3 = "science3", history3 = "history3";

        //Declare file path for each quiz's key.
        String math1Key = "./src/math1Key";
        String math2Key = "./src/math2Key";
        String math3Key = "./src/math3Key";
        String history1Key = "./src/history1Key";
        String history2Key = "./src/history2Key";
        String history3Key = "./src/history3Key";
        String science1Key = "./src/science1Key";
        String science2Key = "./src/science2Key";
        String science3Key = "./src/science3Key";

        //Declare file path for FileReader to find later.
        String math1Path = "./src/math1";
        String math2Path = "./src/math2";
        String math3Path = "./src/math3";
        String history1Path = "./src/history1";
        String history2Path = "./src/history2";
        String history3Path = "./src/history3";
        String science1Path = "./src/science1";
        String science2Path = "./src/science2";
        String science3Path = "./src/science3";

        //Queue that holds user's answers.
        Queue<Character> answers = new LinkedList<Character>();

        //Array that holds the answer key.
        char[] key = new char[5];
        //Array that will hold the questions.
        char[] questions = new char[10000];

        //Subject var
        String sub;
        //Difficulty var
        int diff;

        //Prompt user to select a subject.
        subPrompt();
        sub = in.nextLine().toLowerCase();
        while (subChecker(sub)) {
            System.out.println("Please enter a valid subject.");
            subPrompt();
            sub = in.nextLine().toLowerCase();
        }

        //Promt user to select difficulty.
        diffPrompt();
        diff = in.nextInt();
        while (diff < 0 || diff > 3) {
            System.out.println("Please enter a valid difficulty.");
            diffPrompt();
            diff = in.nextInt();
        }

        //Convert difficulty level from integer to a string.
        String diffS = String.valueOf(diff);


        //Concatenate subject and difficulty.
        String selection = sub + diffS;

        //Route holder variable.
        String route = "";

        //Pass selection to FileReader.
        switch (selection) {
            case "math1":
                selection = math1Path;
                route = math1Key;
                break;
            case "math2":
                selection = math2Path;
                route = math2Key;
                break;
            case "math3":
                selection = math3Path;
                route = math3Key;
                break;
            case "history1":
                selection = history1Path;
                route = history1Key;
                break;
            case "history2":
                selection = history2Path;
                route = history2Key;
                break;
            case "history3":
                selection = history3Path;
                route = history3Key;
                break;
            case "science1":
                selection = science1Path;
                route = science1Key;
                break;
            case "science2":
                selection = science2Path;
                route = science2Key;
                break;
            case "science3":
                selection = science3Path;
                route = science3Key;
                break;
        }
        FileReader a = new FileReader(selection);
        //Push file contents into questions array to be used to display.
        a.read(questions);

        FileReader b = new FileReader(route);
        //Push file contents into answerKey array to be compared to user input later on.
        b.read(key);

        //Iterates thru newly questions array and displays each char, at each index.
        for (int i = 0; i < questions.length; i++) {
            //If an index holds "blank" character, break.
            //Else, print out to be displayed as question.
            if (questions[i] == '\u0000') {
                break;
            } else {
                System.out.print(questions[i]);
            }
        }

        String inAnswers;
        //Prompt user to enter answer after quiz is displayed.
        ansPrompt();
        in.nextLine();
        inAnswers = in.nextLine();

        //If user's answers does not equal 5 answers, prompt user to re-enter answers.
        while (inAnswers.length() != 5) {
            ansPrompt();
            inAnswers = in.nextLine();
        }

        //Show user what answers they entered.
        System.out.println("You have inputted [" + inAnswers + "] as your answers.");

        //Ask if they would like to change answers before getting graded.
        System.out.println("Would you like to change your answers before it gets automatically graded? (Y/N) ");
        String y1 = in.next();
        if (y1.equalsIgnoreCase("y")) {
            ansPrompt();
            inAnswers = in.next();
            while (inAnswers.length() != 5) {
                ansPrompt();
                inAnswers = in.nextLine();
            }
        }

        System.out.println("We will now grade your quiz.");
        char[] qPushArr = inAnswers.toCharArray();
        //Push users 5 length string as separate characters to queue.
        for (int i = 0; i < inAnswers.length(); i++) {
            answers.add(qPushArr[i]);
        }


        //Compare queue to answer key and return grade.
        double gradeVal = grade(key, answers);
        System.out.println("Your grade was: " + gradeVal + ".");

        scores.add(new score(gradeVal));

        clear();
    }


    //Method to prompt user to select a subject.
    public static void subPrompt() {
        System.out.println("Please select a subject. (Math/Science/History)");
    }

    //Method to prompt user to select a difficulty.
    public static void diffPrompt(){
        System.out.println("Please select a difficulty. (1-3)");
    }

    //Method to prompt user to answer.
    public static void ansPrompt(){
        System.out.println("Please enter your answers all in one line. (Example: ACCDE or accde) ");
    }

    //Throws error.
    public static void chPromptError(){
        System.out.println("It appears that you have entered an invalid answer format.");
    }

    //Grades quizzes.
    public static double grade(char[] a, Queue<Character> b){
        double right = 0;
        double wrong = 0;
        for(int i = 0; i < a.length; i++) {
            if(Character.toLowerCase(a[i]) == b.remove()){
                right++;
            } else {
                wrong++;
            }
        }

        return right/5 * 100;
    }

    //Clears console log
    public static void clear() {
        for(int i = 0; i < 75; i++)
            System.out.print("\n");
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

    //Displays scores.
    public static String displayScore() {
        if (scores.size() == 0) {
//            DirectoryScanner scanner = new DirectoryScanner();
//            scanner.setIncludes(new String[]{"**/*.java"});
//            scanner.setBasedir("C:/Temp");
//            scanner.setCaseSensitive(false);
//            scanner.scan();
//            String[] files = scanner.getIncludedFiles();
        }

        return "";
    }

}
