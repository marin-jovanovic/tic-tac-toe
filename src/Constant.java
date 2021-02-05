import java.io.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedHashMap;

/**
 * errors that will not be handled
 * swapped line[x] with line[y]
 *
 */
public enum Constant {
    LOCATION_X(1, 20),
    LOCATION_Y(2, 30),
    NUM_OF_WINS(3, 0),
    USERNAME(4, "marin"),
    IS_ACTIVE(5, true);

    private static class Tool {
        public static int numOfConstants = 0;
    }

    /**
     * destination folder
     */
//    private static String CONSTANTS_PATH = "constants.txt";
    private static String CONSTANTS_PATH = "2.txt";

    int row;
    String id;
    Object value;

    Constant(int row, Object value) {
        this.row = row;
        this.id = this.name();
        this.value = value;

        Tool.numOfConstants++;
    }

    public Object getValue() {
        System.out.println("*** " + (new Throwable().getStackTrace())[0].getMethodName() + " ***");

        if (value instanceof Integer) {
            return Integer.parseInt(String.valueOf(value));
        } else if (value instanceof Double) {
            return Double.parseDouble(String.valueOf(value));
        } else if (value instanceof Boolean) {
            return Boolean.parseBoolean(String.valueOf(value));
        } else if (value instanceof String){
            return value;
        } else {
            System.out.println("error; getValue");
            System.exit(-1);
        }

        return value;
    }

    @Override
    public String toString() {
        String type;

        if (value instanceof Integer) {
            type = "Integer";
        } else if (value instanceof Double) {
            type = "Double";
        } else if (value instanceof Boolean) {
            type = "Boolean";
        } else if (value instanceof String){
            type = "String";
        } else {
            type = "error";
            System.exit(-1);
        }

        return "Constant{" +
                "row= " + row +
                ", id= " + id +
                ", value= " + value +
                ", type= " + type +
                '}';
    }

    public void setValue(Object value) {
        System.out.println("*** " + (new Throwable().getStackTrace())[0].getMethodName() + " ***");

        if (this.value.getClass().equals(value.getClass())) {
            System.out.println("same class");
            this.value = value;
        } else {
            System.out.println("objects not of same type");
        }
    }

    public static void main(String[] args) {

//        System.out.println(IS_ACTIVE.name());
//
//        System.out.println(Tool.numOfConstants);
        initializeConstants();
        printAll();

        USERNAME.setValue("test_un");

        updateConstants();
    }

    /**
     * reads from constants.txt
     * sets constants values to values from constants instead of defined in this
     *
     * use this at the start of program to get latest constants
     *
     * handling errors:
     *  if constants.txt is modified there is rollback mechanism
     *  returns list of constants that could not be initialized from constants.txt
     *
     * if file is empty:
     *  use constants defined in this file
     *
     * if file contains modified line (two words, first word is not constant):
     *  this constant uses preassigned value from this file
     *
     * if file contains modified line (two words, second word wrong type):
     *  this constant uses preassigned value from this file
     *
     * if file contains modified line (two words, first word is not constant, second word wrong type):
     *  this constant uses preassigned value from this file
     *
     * if file contains modified line (no words (empty line), one word, multiple words):
     *  this constant uses preassigned value from this file
     */
    private static LinkedHashMap<Integer, String> initializeConstants() {
        System.out.println("*** " + (new Throwable().getStackTrace())[0].getMethodName() + " ***");

        Constant[] backup_states = new Constant[Tool.numOfConstants];
        int i = 0;

        for (Constant constant : EnumSet.allOf(Constant.class)) {
            backup_states[i++] = constant;
        }
        LinkedHashMap<Integer, String> error_log = new LinkedHashMap<>();

        try(FileReader fr = new FileReader(CONSTANTS_PATH);
            BufferedReader bw = new BufferedReader(fr)) {

            String line;

//            which token is being processed
            int index = 0;
            for (Constant constant : EnumSet.allOf(Constant.class)) {
                System.out.println(constant);
/*

                  constants.txt contains less lines than sum of constants is

                  breaks assignment
                  constants use predefined values
                 */

                if ((line = bw.readLine()) == null) {
                    error_log.put(index, "not enough lines from this line");
                    break;
                }

                index++;


                if ((line.split(" ")).length != 2) {

                    if (line.equals("")) {
                        error_log.put(index, "empty line");
                    } else {
                        if ((line.split(" "))[0].equals(constant.id)) {

                            if (line.split(" ").length > 2) {

                                handleValue(error_log, line, index, constant, "too much tokens");

                            } else {
                                error_log.put(index, "not enough tokens");
                            }

                        } else {
                            error_log.put(index, "id mismatch");
                        }
                    }

                } else {

                    handleValue(error_log, line, index, constant, "");

                }

                System.out.println(constant);
                System.out.println();

            }

        } catch (IOException e) {
            System.out.println("IOException");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception");
            System.out.println(e.getMessage());
            printAll();

            i = 0;
            for (Constant constant : EnumSet.allOf(Constant.class)) {
                constant = backup_states[i++];
            }

            for (Constant constant : EnumSet.allOf(Constant.class)) {
                System.out.println(constant);
            }

        }

        error_log.forEach((key, value) -> System.out.println(key + ":" + value));

        return error_log;
    }


    /**
     * Assigns new value to constants if that can be done.
     * If that can not be done; appends new error to log and does not change value (it uses predefined value).
     *
     * @param error_log (k, v) -> (index, error message)
     * @param line line in .txt
     * @param index index of constant
     * @param constant current constant that we try to assign new value
     * @param errorMessagePrefix if line in .txt contains more than 2 tokens append message, else ""
     */
    private static void handleValue(LinkedHashMap<Integer, String> error_log, String line,
                                    int index, Constant constant, String errorMessagePrefix) {

        Object value = (line.split(" "))[1];

        if (constant.value instanceof Integer) {
            if (value.toString().matches("[1-9][0-9]*")) {
                constant.value = Integer.parseInt(String.valueOf(value));

                if (! errorMessagePrefix.equals("")) {
                    error_log.put(index, errorMessagePrefix);
                }

            } else {
                error_log.put(index, errorMessagePrefix + (errorMessagePrefix.equals("") ? "" : ", ") +
                        "error with integer " + value);
            }
        } else if (constant.value instanceof Double) {
            if (value.toString().matches("[0-9]+\\.[0-9]+")) {
                constant.value = Double.parseDouble(String.valueOf(value));

                if (! errorMessagePrefix.equals("")) {
                    error_log.put(index, errorMessagePrefix);
                }

            } else {
                error_log.put(index, errorMessagePrefix + (errorMessagePrefix.equals("") ? "" : ", ") +
                        "error with double " + value);
            }
        } else if (constant.value instanceof Boolean) {

            if (value.toString().equals("true") || value.toString().equals("false")) {
                constant.value = Boolean.parseBoolean(String.valueOf(value));

                if (! errorMessagePrefix.equals("")) {
                    error_log.put(index, errorMessagePrefix);
                }

            } else {
                error_log.put(index, errorMessagePrefix + (errorMessagePrefix.equals("") ? "" : ", ") +
                        "error with boolean " + value);
            }

        } else if (constant.value instanceof String) {
            constant.value = value;

            if (! errorMessagePrefix.equals("")) {
                error_log.put(index, errorMessagePrefix);
            }

        } else {
            error_log.put(index, errorMessagePrefix + (errorMessagePrefix.equals("") ? "" : ", ") +
                    "error while paring value, constant.value error, " + value);
        }
    }

    /**
     * writes to constants.txt
     * writes constant values to constants.txt
     *
     * use this at the end of program to save new settings
     */
    private static void updateConstants() {

        try(FileWriter fw = new FileWriter(CONSTANTS_PATH, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {

            for(Constant constant : EnumSet.allOf(Constant.class)) {
                out.println(constant.id + " " + constant.value);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void testChangeValue() {
        LOCATION_X.setValue(120);
        System.out.println(LOCATION_X);
        System.out.println();

        LOCATION_X.setValue("this should fail");
        System.out.println(LOCATION_X);
        System.out.println();

        LOCATION_X.setValue(20);
        System.out.println(LOCATION_X);
        System.out.println();
    }

    /**
     * prints all constants
     */
    private static void printAll() {
        System.out.println("*** " + (new Throwable().getStackTrace())[0].getMethodName() + " ***");

        for (Constant constant : EnumSet.allOf(Constant.class)) {
            System.out.println(constant);
        }

        System.out.println();
    }


}
