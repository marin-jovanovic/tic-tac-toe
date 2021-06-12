package com.tic_tac_toe;

import com.tic_tac_toe.gui.TestingGui;

import javax.swing.*;
import java.util.Objects;

public class Utils {
	public static ImageIcon getImageIcon(String val) {
		return new ImageIcon(Objects.requireNonNull(TestingGui.class.getResource("/images/" + val + ".png")));
	}

	public static void setImageOrAltText(Object object, String path, String altText) {
//		try {
//			object
//
//		}
	}

}
