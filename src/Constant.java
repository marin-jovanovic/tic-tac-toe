import java.io.*;
import java.util.EnumSet;

public enum Constant {
    LOCATION_X(1,"LOCATION_X", 20),
    LOCATION_Y(2, "LOCATION_Y", 30),
    NUM_OF_WINS(3, "NUM_OF_WINS", 0),
    USERNAME(4, "USERNAME", "marin"),
    IS_ACTIVE(5, "IS_ACTIVE", true);

    private static class Tool {
        public static int numOfConstants = 0;
    }

    /**
     * destination folder
     */
    private static String CONSTANTS_PATH = "constants.txt";

    int row;
    String id;
    Object value;

    Constant(int row, String id, Object value) {
        this.row = row;
        this.id = id;
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

        initializeConstants();

        printAll();

        if (USERNAME.getValue().equals("a")) {
            USERNAME.setValue("b");
        } else {
            USERNAME.setValue("a");
        }

        testChangeValue();

        updateConstants();

    }

    /**
     * reads from constants.txt
     * sets constants values to values from constants instead of defined in this
     *
     * use this at the start of program to get latest constants
     */
    private static void initializeConstants() {
        System.out.println("*** " + (new Throwable().getStackTrace())[0].getMethodName() + " ***");

        Constant[] backup_states = new Constant[Tool.numOfConstants];
        int i = 0;

        for (Constant constant : EnumSet.allOf(Constant.class)) {
            backup_states[i++] = constant;
        }

        try(FileReader fr = new FileReader(CONSTANTS_PATH);
            BufferedReader bw = new BufferedReader(fr)) {

            String line;

            for (Constant constant : EnumSet.allOf(Constant.class)) {
                if ((line = bw.readLine()) == null) {
                    System.out.println("error");
                    break;
                }

                System.out.println(constant);

                Object value = (line.split(" "))[1];

                if (constant.value instanceof Integer) {
                    if (value.toString().matches("[1-9][0-9]*")) {
                        constant.value = Integer.parseInt(String.valueOf(value));
                    } else {
                        System.out.println("error with integer");
                    }
                } else if (constant.value instanceof Double) {
                    if (value.toString().matches("[0-9]+\\.[0-9]+")) {
                        constant.value = Double.parseDouble(String.valueOf(value));
                    } else {
                        System.out.println("error with double");
                    }
                } else if (constant.value instanceof Boolean) {
                    if (value.toString().equals("true") || value.toString().equals("false")) {
                        constant.value = Boolean.parseBoolean(String.valueOf(value));
                    } else {
                        System.out.println("error with boolean");
                    }
                } else if (constant.value instanceof String) {
                    constant.value = value;
                } else {
                    System.out.println("error while paring value");
                }

                System.out.println(constant);
                System.out.println();

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            printAll();

            i = 0;
            for (Constant constant : EnumSet.allOf(Constant.class)) {
                constant = backup_states[i++];
            }

            for (Constant constant : EnumSet.allOf(Constant.class)) {
                System.out.println(constant);
            }

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
