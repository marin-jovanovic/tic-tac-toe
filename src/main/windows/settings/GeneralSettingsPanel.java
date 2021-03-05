package main.windows.settings;

import main.resourceManagers.constants.Constant;
import main.windows.settings.elements.textField.TextFieldElement;

import javax.swing.*;
import java.awt.*;

public class GeneralSettingsPanel extends JPanel {

    public GeneralSettingsPanel() {

        setLayout(new GridLayout(0, 1));

        add(new TextFieldElement(Constant.NUMBER_OF_ROWS));
        add(new TextFieldElement(Constant.NUMBER_OF_COLUMNS));
        add(new TextFieldElement(Constant.HOW_MANY_IN_SEQUENCE_TO_WIN));


//TODO
//        add(new RestartCurrentButton(Constant.NUMBER_OF_ROWS, Constant.NUMBER_OF_COLUMNS, Constant.NUMBER_OF_MINES));

        add(new TogglePanel("is sound active", Constant.IS_SOUND_ACTIVE));


    }

    private static class TogglePanel extends JPanel {

        public TogglePanel(String label, Constant constant) {

            setLayout(new FlowLayout());

            JLabel jLabel = new JLabel(label);
            add(jLabel);


            String currentValue;

            JToggleButton button;

            if ((boolean) constant.getValue()) {

                currentValue = "ON";
                button = new JToggleButton(currentValue);

                button.addActionListener(e -> {
                    if (button.isSelected()) {
                        button.setText("OFF");

                        constant.setValue(false);

                    } else {
                        button.setText("ON");

                        constant.setValue(true);
                    }
                });

            } else {
                currentValue = "OFF";
                button = new JToggleButton(currentValue);

                button.addActionListener(e -> {
                    if (button.isSelected()) {
                        button.setText("ON");

                        constant.setValue(true);
                    } else {
                        button.setText("OFF");
                        constant.setValue(false);
                    }
                });

            }

            add(button);

        }

    }


}