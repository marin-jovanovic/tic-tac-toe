import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLocation(500, 500);
        setSize(200, 200);

        //        setLocation((Integer) Constant.LOCATION_X.getValue(), (Integer) Constant.LOCATION_Y.getValue());
        //        setSize((Integer) Constant.WIDTH.getValue(), (Integer) Constant.HEIGHT.getValue());
        setLayout(new FlowLayout());

        JTextArea textArea = new JTextArea();
        add(textArea);

        JButton button = new JButton();
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("stisnitu " + textArea.getText());

                writeFile(textArea.getText());



            }
        });



    }

    public void writeFile(String value){

//        File directory = new File("test_dir");
//        if (! directory.exists()){
//            directory.mkdir();
//            System.out.println("dir made");
//            // If you require it to make the entire directory path including parents,
//            // use directory.mkdirs(); here instead.
//        }

        try(FileWriter fw = new FileWriter("config.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
