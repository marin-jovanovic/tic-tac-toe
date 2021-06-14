package com.tictactoe;

import javax.swing.*;
import java.util.Objects;

public class Utils {
    public static ImageIcon getImageIcon(String val) {
        return new ImageIcon(Objects.requireNonNull(Main.class.getResource("/images/" + val + ".png")));
    }

    public static void setImageOrAltText(Object object, String path, String altText) {
//		try {
//			object
//
//		}
    }

}
