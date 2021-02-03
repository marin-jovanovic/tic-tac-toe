import java.io.*;
import java.util.EnumSet;

public enum Constant {
    LOCATION_X(1,"LOCATION_X", 20),
    LOCATION_Y(2, "LOCATION_Y", 30),
    NUM_OF_WINS(3, "NUM_OF_WINS", 0),
    USERNAME(4, "USERNAME", "marin"),
    IS_ACTIVE(5, "IS_ACTIVE", true);

    private static String CONSTANTS_PATH = "constants.txt";

    int row;
    String id;
    Object value;

    Constant(int row, String id, Object value) {
        this.row = row;
        this.id = id;
        this.value = value;
    }

    public Object getValue() {
        if (value instanceof Integer) {
            System.out.println("int");
            return Integer.parseInt(String.valueOf(value));
        } else if (value instanceof Double) {
            System.out.println("double");
            return Double.parseDouble(String.valueOf(value));
        } else if (value instanceof Boolean) {
            System.out.println("boolean");
            return Boolean.parseBoolean(String.valueOf(value));
        } else if (value instanceof String){
            System.out.println("string");
            return value;
        } else {
            System.out.println("error");
            System.exit(-1);
        }

        return value;
    }

    @Override
    public String toString() {
        return "Constant{" +
                "row=" + row +
                ", id='" + id + '\'' +
                ", value=" + value +
                '}';
    }

    public void setValue(Object value) {
        if (this.value.getClass().equals(value.getClass())) {
            System.out.println("same class");
            this.value = value;
        } else {
            System.out.println("objects not of same type");
        }
    }

    public static void main(String[] args) {

        initializeConstants();
        System.out.println();

        printAll();

        USERNAME.setValue("darko");

        updateConstants();

    }

    /**
     * reads from constants.txt
     * sets constants values to values from constants instead of defined in this
     *
     * use this at the start of program to get latest constants
     */
    private static void initializeConstants() {

        try(FileReader fr = new FileReader(CONSTANTS_PATH);
            BufferedReader bw = new BufferedReader(fr)) {

            String line;

            for (Constant constant : EnumSet.allOf(Constant.class)) {
                if ((line = bw.readLine()) == null) {
                    System.out.println("error");
                    break;
                }

                System.out.println(constant);

                String[] raw_data = line.split(" ");
                String id = raw_data[0];
                Object value = raw_data[1];

                if (value.getClass().equals(constant.value.getClass())) {
                    constant.value = value;
                } else {
                    System.out.println("something wrong in constants.txt");
                    System.exit(-1);
                }


                System.out.println(constant);
                System.out.println();

            }

        } catch (IOException e) {
            e.printStackTrace();
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

        LOCATION_X.setValue("abrakadabra");
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
        for (Constant constant : EnumSet.allOf(Constant.class)) {
            System.out.println(constant);
            System.out.println(constant.getValue());
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }


}
