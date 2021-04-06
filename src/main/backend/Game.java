package source.backend;

import java.util.Scanner;


public class Game {
	/**x
	 * -x- and -o- are markers
	 * 
	 * template for TTT
	 * [fff]
	 * [fff]
	 * [fff]
	 * 
	 * [012]
	 * [345]
	 * [678]
	 */

	private static source.backend.Board board;
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		play();
	}


	public static void play() {
		//		System.out.println(SettingsAndOptions.isGUIActive());
		if(!source.backend.SettingsAndOptions.isGUIActive()) {
			SettingUpMarkers();
			SettingUpFirstPlayer();
		}
		try(Scanner sc = new Scanner(System.in)) {
			do {
				playGame();
				System.out.println("do you want to play another? [y, n]");
			} while (sc.nextLine().equals("y"));
		} catch (Exception e) {
		}
	}

	public static void playGame() {
		board = new source.backend.Board();
		int turnCounter = 0;
		if(source.backend.SettingsAndOptions.getFirstPlayer() == source.backend.Owner.USER) {
			board.printBoardAndManual();
			int playerWantsToPlay = PlayerInput();
			board.getBoardTile(playerWantsToPlay).setOwner(Owner.USER);
			turnCounter++;
		}

		while(true) {
			board.printBoardAndManual();
			System.out.println();
			board.getBoardTile(ArtificialIntelligenceForComputer.computersTurn(board)).setOwner(Owner.COMPUTER);

			turnCounter++;
			board.printBoardAndManual();

			if(turnCounter >= 5 && board.isWinner(Owner.COMPUTER)) {
				System.out.println("Defeat");
				break;
			}
			if(board.howManyMovesAreLeft() == -1) {
				System.out.println("no winners");
				break;
			}



			int playerWantsToPlay = PlayerInput();

			board.getBoardTile(playerWantsToPlay).setOwner(Owner.USER);

			turnCounter++;
			board.printBoardAndManual();

			if(turnCounter >= 5 && board.isWinner(Owner.USER)) {
				System.out.println("Victory");
				break;
			}
			if(board.howManyMovesAreLeft() == -1) {
				System.out.println("no luzers");
				break;
			}

		}
	}

	private static void    SettingUpFirstPlayer() {
		String scanString;
		while(true) {
			System.out.println("Do you want to play first? [y/n]");
			scanString = scan.nextLine();
			if(scanString.length() == 1) {
				if (scanString.equals("y")) {

					SettingsAndOptions.setFirstPlayer(Owner.USER);
					return;
				}
				else if(scanString.equals("n")) {
					SettingsAndOptions.setFirstPlayer(Owner.COMPUTER);
					return;
				}
			}
			else {
				System.out.println("invalid input");
			}		
		}
	}
	private static void    SettingUpMarkers() {
		String scanString;
		while(true) {
			System.out.println("Do you want to be 'x' or 'o'? [x,o]");
			scanString = scan.nextLine();

			if(scanString.length() == 1) {
				if (scanString.equals("x")) {
					SettingsAndOptions.setMarkers(Marker.x);
					break;
					//return;
				}
				else if (scanString.equals("o")) {
					SettingsAndOptions.setMarkers(Marker.o);
					break;
					//return;
				}
			}	
			else {
				System.out.println("invalid input");
			}
		}
	}



	private static int     PlayerInput() {	
		System.out.println("playerinput");
		String scanString;
		if(SettingsAndOptions.isGUIActive()) {
			while(true) {
//				System.out.println(MainFrame.getPostion());
//				System.out.println(board.isTileAvailable(MainFrame.getPostion()));
//				System.out.println(MainFrame.getPostion());
				if(board.isTileAvailable(MainFrame.getPostion())) {
					System.out.println("nesto");
					System.out.println(MainFrame.getPostion());
					return MainFrame.getPostion();
				} else {
//					System.out.println("vrtim");
				}
			}


		} else {
			while(true) {
				System.out.println("Where do you want to place X? [0,1,2,3,4,5,6,7,8,9]");
				scanString = scan.nextLine();

				if(scanString.length() == 1
						&&  (scanString.equals("0")
								|| scanString.equals("1")
								|| scanString.equals("2")
								|| scanString.equals("3")
								|| scanString.equals("4")
								|| scanString.equals("5")
								|| scanString.equals("6")
								|| scanString.equals("7")
								|| scanString.equals("8"))) {
					if(board.isTileAvailable(Integer.parseInt(scanString))) {
						return Integer.parseInt(scanString);
					}
					else {
						System.out.println("Tile not Available");
					}

				}

				else {
					System.out.println("invalid input");
				}

			}
		}


	}


}
