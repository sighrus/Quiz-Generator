import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    private static LinkedList<score> scores = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        scores = searchScores();

        boolean onOff = true;
        boolean onOff2 = true;

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
                    onOff2 = true;
                    while (onOff2) {
                        displayScore();
                        String command1 = in.nextLine();
                        switch(command1) {
                            case "menu":
                                clear();
                                onOff2=false;
                                break;
                        }
                    }
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

    //Displays menu options
    public static void menu() {
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

    //Starts the quiz
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
        //Name var
        String name;

        //Prompt user to input name.
        System.out.println("What is your name?");
        name = in.nextLine();

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


        System.out.println("\n~~~~~~~~~~ Quiz Questions ~~~~~~~~~~");
        System.out.println("(You may have to scroll up and down)\n");
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
        System.out.println("\n~~~~~~~~~~~~ End of Quiz ~~~~~~~~~~~\n");


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

        //Converts answers to char array.
        char[] qPushArr = inAnswers.toCharArray();

        //Push users answer (char array) as separate characters to queue.
        for (int i = 0; i < inAnswers.length(); i++) {
            answers.add(qPushArr[i]);
        }

        //Compare queue to answer key and return grade.
        double gradeVal = grade(key, answers);
        System.out.println("Your grade was: " + gradeVal + ".");

        //Add their score to scores list.
        score scoreHolder = new score(gradeVal, name, scores.size());
        scores.add(scoreHolder);

        //Serialize the object
        serializeThis(scoreHolder);

        clear();
    }

    //Method to prompt user to select a subject.
    public static void subPrompt() {
        System.out.println("Please select a subject. (Math/Science/History)");
    }

    //Method to prompt user to select a difficulty.
    public static void diffPrompt() {
        System.out.println("Please select a difficulty. (1-3)");
    }

    //Method to prompt user to answer.
    public static void ansPrompt() {
        System.out.println("Please enter your answers all in one line. (Example: ACCDE or accde) ");
    }

    //Throws error.
    public static void chPromptError() {
        System.out.println("It appears that you have entered an invalid answer format.");
    }

    //Grades quizzes.
    public static double grade(char[] a, Queue<Character> b) {
        double right = 0;
        double wrong = 0;
        for (int i = 0; i < a.length; i++) {
            if (Character.toLowerCase(a[i]) == b.remove()) {
                right++;
            } else {
                wrong++;
            }
        }

        return right / 5 * 100;
    }

    //Clears console log
    public static void clear() {
        for (int i = 0; i < 75; i++)
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

    //Searches for objects to deserialize if any and returns a 'score' LinkedList of all the objects it found.
    public static LinkedList<score> searchScores() {

        //List to store deserialized objects.
        LinkedList<score> scoreList = new LinkedList<>();

        //Searches for score objects and if it finds any, add it to files[] to be serialized.
        File dir = new File(".");
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.matches("score[0-9]{1,9}");
            }
        });

        for (File xmlfile : files) {
            //Deserialize object and add it to scoreList.
            score test = deserializeThis(xmlfile.getName());
            scoreList.add(test);
        }


        return scoreList;
    }

    //Serializes objects so we can save scores from other times the program has ran.
    public static void serializeThis(score in) {
        String filename = "score" + in.getId();

        try {
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(in);

            out.close();
            file.close();

            System.out.println("\nSuccessfully serialized the object. Check your SRC folder.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //De-serializes objects so we can read scores from other times the program has ran.
    public static score deserializeThis(String filename) {
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            Object x = in.readObject();
            score e = (score) x;

            in.close();
            file.close();

            return e;
        } catch (Exception error) {
            error.printStackTrace();
            System.out.println("Error could not deserialize.");
        }
        return null;
    }

    //Displays scores.
    public static void displayScore() {

        //Sort scores in descending order, putting highest grades in the beginning indexes.
        Collections.sort(scores);

        clear();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("////////////////////////////////////////////////////");
        System.out.println("////////////////// --- Scores --- //////////////////");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("////////////////////////////////////////////////////");
        System.out.println("////////                                    ////////");
        System.out.println("////////    Name:         Score:            ////////");
        System.out.println("////////                                    ////////");

        //Prints out the name and grade of each person, while formatting it correctly.
        //Most of the code here is for formatting purposes only.
        for (int i = 0; i < scores.size(); i++) {

            //Length of name
            int nameC = scores.get(i).getName().length();
            String spacer = "                                                         ";
            String spacer2 = "                                                        ";


            if (scores.get(i) != null) {
                if (scores.get(i).getGrade() == 100.0) {
                    spacer2 = spacer2.substring(0, 13);
                    if (nameC > 5) {
                        int n = nameC - 5;

                        spacer = spacer.substring(0, 9 - n);
                        System.out.println("////////    " + scores.get(i).getName() + "" + spacer + "" + scores.get(i).getGrade() + "" + spacer2 + "////////");

                    } else if (nameC < 5) {
                        int n = 5 - nameC;
                        spacer = spacer.substring(0, 9 + n);
                        System.out.println("////////    " + scores.get(i).getName() + "" + spacer + "" + scores.get(i).getGrade() + "" + spacer2 + "////////");

                    } else {
                        System.out.println("////////    " + scores.get(i).getName() + "         " + scores.get(i).getGrade() + "" + spacer2 + "////////");
                    }
                } else if (scores.get(i).getGrade() <= 90.0 && scores.get(i).getGrade() >= 10.0) {
                    if (nameC > 5) {
                        int n = nameC - 5;

                        spacer = spacer.substring(0, 9 - n);
                        System.out.println("////////    " + scores.get(i).getName() + "" + spacer + "" + scores.get(i).getGrade() + "              ////////");

                    } else if (nameC < 5) {
                        int n = 5 - nameC;
                        spacer = spacer.substring(0, 9 + n);
                        System.out.println("////////    " + scores.get(i).getName() + "" + spacer + "" + scores.get(i).getGrade() + "              ////////");

                    } else {
                        System.out.println("////////    " + scores.get(i).getName() + "         " + scores.get(i).getGrade() + "              ////////");
                    }
                } else if (scores.get(i).getGrade() < 10.0 && scores.get(i).getGrade() > 0.0) {
                    if (nameC > 5) {
                        int n = nameC - 5;

                        spacer = spacer.substring(0, 9 - n);
                        System.out.println("////////    " + scores.get(i).getName() + "" + spacer + "" + scores.get(i).getGrade() + "              ////////");

                    } else if (nameC < 5) {
                        int n = 5 - nameC;
                        spacer = spacer.substring(0, 9 + n);
                        System.out.println("////////    " + scores.get(i).getName() + "" + spacer + "" + scores.get(i).getGrade() + "              ////////");

                    } else {

                        System.out.println("////////    " + scores.get(i).getName() + "         " + scores.get(i).getGrade() + "              ////////");
                    }
                } else {
                    spacer2 = spacer2.substring(0, 15);

                    if (nameC > 5) {
                        int n = nameC - 5;

                        spacer = spacer.substring(0, 9 - n);
                        System.out.println("////////    " + scores.get(i).getName() + "" + spacer + "" + scores.get(i).getGrade() + "" + spacer2 + "////////");

                    } else if (nameC < 5) {
                        int n = 5 - nameC;
                        spacer = spacer.substring(0, 9 + n);
                        System.out.println("////////    " + scores.get(i).getName() + "" + spacer + "" + scores.get(i).getGrade() + "" + spacer2 + "////////");
                    } else {
                        System.out.println("////////    " + scores.get(i).getName() + "         " + scores.get(i).getGrade() + "" + spacer2 + "////////");
                    }
                }
            }
        }
        System.out.println("////////                                    ////////");
        System.out.println("////////   Type 'menu' to return to menu    ////////");
        System.out.println("////////////////////////////////////////////////////");
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }
}
