package com.tic_tac_toe.gui;

import com.tic_tac_toe.Utils;
import com.tic_tac_toe.gamedrivers.board.Game;
import com.tic_tac_toe.gamedrivers.point.Point;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TileButton extends JButton implements PropertyChangeListener {
	private final PropertyChangeSupport support;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("catched property change in tile button");

		System.out.println(evt.getNewValue());

//		this.setEnabled(false);
//		this.setIcon(null);
	}

	public void addListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	public void removeListener(PropertyChangeListener p) {
		support.removePropertyChangeListener(p);
	}

//	todo
	class playerVsComputerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}

	private Game game;
	private Point point;

	public TileButton(Game game, Point point) {
		support = new PropertyChangeSupport(this);


		this.game = game;
		this.point = point;

		this.setText(point.getX() + " " + point.getY());

//		this.addActionListener(event -> {
//			this.setEnabled(false);
////			todo load pre-made images (create class then load)
//
//			try {
//				this.setDisabledIcon(Utils.getImageIcon("o"));
//				this.setIcon(Utils.getImageIcon("o"));
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//				this.setText("o");
//			}
//
////			todo cleanup
//			this.setText(null);
//
//			////			other player move
//			support.firePropertyChange("", null, "game won check pls");
////
//////					if (g.get().isGameWon(new Point(finalX, finalY), TileOwner.USER_1)) {
//////						System.out.println("game won");
//////						jTextField.setText("VICTORY");
//////						for (int i = 0; i < g.get().getYAxisLength(); i++) {
//////							for (int j = 0; j < g.get().getXAxisLength(); j++) {
//////
//////								buttons[i][j].setEnabled(false);
////////								buttons[i][j].setIcon(null);
//////
//////							}
//////						}
//////
//////					}
//		});
	}


//	public TileButton() {
//		support = new PropertyChangeSupport();
//
//		this.addActionListener(event -> {
//			this.setEnabled(false);
////			todo load pre-made images (create class then load)
//
//			try {
//				this.setDisabledIcon(Utils.getImageIcon("o"));
//				this.setIcon(Utils.getImageIcon("o"));
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//				this.setText("o");
//			}
//
////			todo cleanup
//			this.setText(null);
//
//
//
//
//
////			other player move
//
////					if (g.get().isGameWon(new Point(finalX, finalY), TileOwner.USER_1)) {
////						System.out.println("game won");
////						jTextField.setText("VICTORY");
////						for (int i = 0; i < g.get().getYAxisLength(); i++) {
////							for (int j = 0; j < g.get().getXAxisLength(); j++) {
////
////								buttons[i][j].setEnabled(false);
//////								buttons[i][j].setIcon(null);
////
////							}
////						}
////
////					}
////
////					System.out.println();
////
////					Point computerMove = g.get().computerMove();
////
////					g.get().setTile(new Point(computerMove.getX(), computerMove.getY()), TileOwner.COMPUTER);
////					buttons[computerMove.getY()][computerMove.getX()].setEnabled(false);
////					buttons[computerMove.getY()][computerMove.getX()].setIcon(getImageIcon("x"));
////					buttons[computerMove.getY()][computerMove.getX()].setDisabledIcon(getImageIcon("x"));
//		});
//
//	}



////	todo cleanup
//	public TileButton(String label) {
//		this();
//
//		this.setText(label);
//	}

	public void restart() {
		this.setEnabled(true);
		this.setIcon(null);
	}



}
