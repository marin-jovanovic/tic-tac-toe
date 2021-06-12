package com.tic_tac_toe.gui;

import com.tic_tac_toe.Utils;

import javax.swing.*;

public class TileButton extends JButton {

	public TileButton() {


//		this.addActionListener();
//		this.addActionListener(event -> {
//			this.setEnabled(false);
////			todo load pre-made images (create class then load)
//
//			try {
//				this.setDisabledIcon(Utils.getImageIcon("o"));
//				this.setIcon(Utils.getImageIcon("o"));
//			} catch (Exception e) {
//				System.out.println(e);
//				this.setText("o");
//			}
//
////			todo cleanup
//			this.setText(null);
//
//		});
	}



//	todo cleanup
	public TileButton(String label) {
		this();

		this.setText(label);
	}

	public void restart() {
		this.setEnabled(true);
		this.setIcon(null);
	}


}
