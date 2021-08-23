import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class demonstrates a Simple Card Game with the GUI Interface and its implementation
 * 
 * @ Saharsh
 */
public class SimpleCardGame {
	//Card Images labels initiation
	JLabel label_Image1 = new JLabel();
	JLabel label_Image2 = new JLabel();
	JLabel label_Image3 = new JLabel();
	JLabel label_Image4 = new JLabel();
	JLabel label_Image5 = new JLabel();
	JLabel label_Image6 = new JLabel();
	
	//Button initation 
	JButton btn_rpcard1 = new JButton("Replace Card 1");
	JButton btn_rpcard2 = new JButton("Replace Card 2");
	JButton btn_rpcard3 = new JButton("Replace Card 3");
	JButton btn_start = new JButton("Start");
	JButton btn_result= new JButton("Result");
	
	//Labels for money, bet and textfield to input the bet
	JLabel label_bet = new JLabel();
	JLabel label_money = new JLabel();
	JLabel label_info = new JLabel();
	JTextField txt_inputbet = new JTextField(10);
	
	ImageIcon deck[] = new ImageIcon[53]; //playing cards
	int rpcount;
	
	int Totalmun = 100; //variable to store the money
	
	ImageIcon Img4;
	ImageIcon Img5;
	ImageIcon Img6;
	
	/**
	 * This method called main starts the MySimpleGame class
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleCardGame t= new SimpleCardGame();
		t.go();
	}
	
	/**
	 * the go() method defines the GUI and implemetation variables and provides an outline run of the Game
	 */
	public void go() {
		JFrame frame = new JFrame("A Simple Card Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btn_start.setEnabled(true);
		btn_result.setEnabled(false);
		btn_rpcard1.setEnabled(false);
		btn_rpcard2.setEnabled(false);
		btn_rpcard3.setEnabled(false);
		
		ImageIcon Image1 = new ImageIcon("card_back.gif");
		ImageIcon Image2 = new ImageIcon("card_back.gif");
		ImageIcon Image3 = new ImageIcon("card_back.gif");
		ImageIcon Image4 = new ImageIcon("card_back.gif");
		ImageIcon Image5 = new ImageIcon("card_back.gif");
		ImageIcon Image6 = new ImageIcon("card_back.gif");
		
		label_Image1.setIcon(Image1);
		label_Image2.setIcon(Image2);
		label_Image3.setIcon(Image3);
		label_Image4.setIcon(Image4);
		label_Image5.setIcon(Image5);
		label_Image6.setIcon(Image6);
		
		label_bet.setText("Bet: $");
		label_money.setText("Please place your bet! ");
		label_info.setText("Amount of money you have: $" + Totalmun);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menuC = new JMenu("Control");
		JMenu menuH = new JMenu("Help");
		JMenuItem menuItemC = new JMenuItem("Exit");
		JMenuItem menuItemH = new JMenuItem("Instructions");
		menuC.add(menuItemC);
		menuH.add(menuItemH);
		menuBar.add(menuC);
		menuBar.add(menuH);
		frame.setJMenuBar(menuBar);
		
		menuItemC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		
		menuItemH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String Message = "J, Q, K are regarded as special cards.\n"
						+ "Rule 1: The one with more special cards wins.\n"
						+ "Rule 2: If both have the same number of special cards, add the face values of the other\n"
						+ "card(s) and take the remainder after dividing the sum by 10. The one with a bigger\n"
						+ "remainder wins. (Note: Ace = 1).\n"
						+ "Rule 3: The dealer wins if both rule 1 and rule 2 cannot distinguish the winner.";
				JOptionPane.showMessageDialog(new Frame(), Message, "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			
		});
		
		JPanel MainPanel = new JPanel();
		JPanel DealerPanel = new JPanel();
		JPanel PlayerPanel = new JPanel();
		JPanel RpCardBtnPanel = new JPanel();
		RpCardBtnPanel.add(btn_rpcard1);
		RpCardBtnPanel.add(btn_rpcard2);
		RpCardBtnPanel.add(btn_rpcard3);
		
		JPanel ButtonPanel = new JPanel();
		btn_start.setBackground(Color.YELLOW);
		btn_result.setBackground(Color.YELLOW);
		ButtonPanel.add(label_bet);
		ButtonPanel.add(txt_inputbet);
		
		btn_start.addActionListener(new StartButton());
		btn_rpcard1.addActionListener(new ReplaceCard1());
		btn_rpcard2.addActionListener(new ReplaceCard2());
		btn_rpcard3.addActionListener(new ReplaceCard3());
		btn_result.addActionListener(new Result());
		
		ButtonPanel.add(btn_start);
		ButtonPanel.add(btn_result);
		
		JPanel InfoPanel = new JPanel();
		InfoPanel.add(label_money);
		InfoPanel.add(label_info);
		
		DealerPanel.add(label_Image1);
		DealerPanel.add(label_Image2);
		DealerPanel.add(label_Image3);
		
		PlayerPanel.add(label_Image4);
		PlayerPanel.add(label_Image5);
		PlayerPanel.add(label_Image6);
		
		MainPanel.setLayout(new GridLayout(5,1));
		MainPanel.add(DealerPanel);
		MainPanel.add(PlayerPanel);
		MainPanel.add(RpCardBtnPanel);
		MainPanel.add(ButtonPanel);
		MainPanel.add(InfoPanel);
		DealerPanel.setBackground(Color.MAGENTA);
		PlayerPanel.setBackground(Color.MAGENTA);
		RpCardBtnPanel.setBackground(Color.MAGENTA);
		
		frame.getContentPane().add(MainPanel);
		frame.setSize(400,700);
		frame.setVisible(true);
		
	}
	
	/**
	 * The StartButton inner class provides the implementation when the Start Button is pressed
	 * 
	 * @author Saharsh
	 *
	 */
	class StartButton implements ActionListener{
		/**
		 * The actionPerformed method describes all the actions after clicking of the StartButton
		 * 
		 * @param event
		 */
		public void actionPerformed(ActionEvent event) { 
			double d = Double.parseDouble(txt_inputbet.getText());
			int va  =(int) d;
			if (Totalmun-va<0) {
				String warn = ("WARNING: You only have $" + Totalmun);
				JOptionPane.showMessageDialog(new JFrame(), warn, "Message", JOptionPane.WARNING_MESSAGE);
				label_money.setText("Please place your bet! ");
			}
			else {
				if (!(txt_inputbet.getText().isEmpty()) && va == d && va >0){
					for (int i =1; i< 53; i++) {
						if (i<10) {
							deck[i] = new ImageIcon("card_1"+i+".gif");
						}
						else if (i>=10 && i<19) {
							deck[i] = new ImageIcon("card_2"+(i-9)+".gif");
						}
						else if (i>=19 && i<28) {
							deck[i] = new ImageIcon("card_3"+(i-18)+".gif");
						}
						else if (i>=28 && i<37) {
							deck[i] = new ImageIcon("card_4"+(i-27)+".gif");
						}
						else if (i>=37 && i<41) {
							deck[i] = new ImageIcon("card_1"+(i-27)+".gif");
						}
						else if (i>=41 && i<45) {
							deck[i] = new ImageIcon("card_2"+(i-31)+".gif");
						}
						else if (i>=45 && i<49) {
							deck[i] = new ImageIcon("card_3"+(i-35)+".gif");
						}
						else {
							deck[i] = new ImageIcon("card_4"+(i-39)+".gif");
						}
					}
					for (int i =1; i<53; i++) {
						int ran = (int) (Math.random()*52) +1;
						ImageIcon tem = deck[i];
						deck[i] = deck[ran];
						deck[ran] = tem;
					}
					
					label_Image4.setIcon(deck[1]);
					label_Image5.setIcon(deck[2]);
					label_Image6.setIcon(deck[3]);
					
					Img4 = deck[4];
					Img5 = deck[5];
					Img6 = deck[6];
					
					label_money.setText("Your current bet is: $" + txt_inputbet.getText());
					
					btn_start.setEnabled(false);
					btn_result.setEnabled(true);
					btn_rpcard1.setEnabled(true);
					btn_rpcard2.setEnabled(true);
					btn_rpcard3.setEnabled(true);
				}
				else {
					String s = "WARNING: The bet you place must be a positive integer";
					JOptionPane.showMessageDialog(new Frame(), s, "Message", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	/**
	 * This inner class is the implemetation of the action performed when the Replace Card 1 button is used
	 * 
	 * @author Saharsh
	 */
	class ReplaceCard1 implements ActionListener{
		/**
		 * This method inside replace card 1 implements the actions taken when replace card 1 button is pressed. It will replace the first card of the user with another card from the deck
		 * 
		 * @param e
		 */
		public void actionPerformed(ActionEvent e) {
			if (!(btn_rpcard2.isEnabled() == false && btn_rpcard3.isEnabled() == false)){
				label_Image4.setIcon(deck[7]);
				btn_rpcard1.setEnabled(false);
				rpcount++;
			}
			if (rpcount == 2) {
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
				btn_rpcard1.setEnabled(false);
			}
		}
	}
	
	/**
	 * This inner class performs the action when Replace Card 2 button is pressed
	 * 
	 * @author Saharsh
	 *
	 */
	class ReplaceCard2 implements ActionListener{
		/**
		 * This method inside replace card 2 implements the actions taken when replace card 2 button is pressed. It will replace the second card of the user with another card from the deck
		 * 
		 * @param e
		 */
		public void actionPerformed(ActionEvent e) {
			if (!(btn_rpcard3.isEnabled() == false && btn_rpcard1.isEnabled() == false)){
				label_Image5.setIcon(deck[8]);
				btn_rpcard2.setEnabled(false);
				rpcount++;
			}
			if (rpcount == 2) {
				btn_rpcard1.setEnabled(false);
				btn_rpcard3.setEnabled(false);
				btn_rpcard2.setEnabled(false);
			}
		}
	}
	/**
	 * This inner class performs the action when Replace Card 3 button is pressed
	 * 
	 * @author Saharsh
	 *
	 */
	class ReplaceCard3 implements ActionListener{
		/**
		 * This method inside replace card 3 implements the actions taken when replace card 3 button is pressed. It will replace the third card of the user with another card from the deck
		 * 
		 * @param e
		 */
		public void actionPerformed(ActionEvent e) {
			if (!(btn_rpcard1.isEnabled() == false && btn_rpcard2.isEnabled() == false)){
				label_Image6.setIcon(deck[9]);
				btn_rpcard3.setEnabled(false);
				rpcount++;
			}
			if (rpcount == 2) {
				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
			}
		}
	}
	/**
	 * This class performs the actions when replace button is pressed
	 * @author Saharsh
	 *
	 */
	class Result implements ActionListener{
		/**
		 * This method implements when the result button is pressed. It will compare the cards of the player and dealer
		 * 
		 * @param e
		 */
		public void actionPerformed(ActionEvent e) {
			int dealspecial = 0;
			int playspecial = 0;
			int val1 = 0;
			int val2= 0;
			
			label_Image1.setIcon(Img4);
			label_Image2.setIcon(Img5);
			label_Image3.setIcon(Img6);
			
			for (int i =1; i<53; i++) {
				for (int k = 1; k<5;k++) {
					for (int j = 11; j<14;j++) {
						if ((deck[i]).toString().equals((new ImageIcon("card_"+k+j+".gif")).toString())){
							if ((label_Image4.getIcon()).toString().equals((deck[i]).toString())) {
								playspecial++;
							}
							else if ((label_Image5.getIcon()).toString().equals((deck[i]).toString())) {
								playspecial++;
							}
							else if ((label_Image6.getIcon()).toString().equals((deck[i]).toString())) {
								playspecial++;
							}
							else if ((label_Image1.getIcon()).toString().equals((deck[i]).toString())) {
								dealspecial++;
							}
							else if ((label_Image2.getIcon()).toString().equals((deck[i]).toString())) {
								dealspecial++;
							}
							else if ((label_Image3.getIcon()).toString().equals((deck[i]).toString())) {
								dealspecial++;
							}
						}
					}
				}
			}
			
			if (dealspecial > playspecial) {
				String dealwin = "Sorry! The Dealer wins this round!";
				JOptionPane.showMessageDialog(new Frame(), dealwin, "Message", JOptionPane.INFORMATION_MESSAGE);
				Totalmun -= (Integer.valueOf(txt_inputbet.getText())); 
				label_info.setText("Amount of money you have: $" + Totalmun);
			}
			else if (dealspecial < playspecial) {
				String playwin = "Congratulations! You win this round!";
				JOptionPane.showMessageDialog(new Frame(), playwin, "Message", JOptionPane.INFORMATION_MESSAGE);
				Totalmun += (Integer.valueOf(txt_inputbet.getText())); 
				label_info.setText("Amount of money you have: $" + Totalmun);
			}
			else {
				for (int i =1; i<53; i++) {
					for (int k = 1; k<5;k++) {
						for (int j = 11; j<14;j++) {
							if (!(deck[i]).toString().equals((new ImageIcon("card_"+k+j+".gif")).toString())) {
								if ((label_Image4.getIcon()).toString().equals((deck[i]).toString())) {
									String xyz = (deck[i]).toString();
									if (xyz.charAt(7)== '0') {
										val2 += 10;
									}
									else {
										val2 += (int)(xyz.charAt(6)-48);
									}
								}
								else if ((label_Image5.getIcon()).toString().equals((deck[i]).toString())) {
									String xyz = (deck[i]).toString();
									if (xyz.charAt(7)== '0') {
										val2 += 10;
									}
									else {
										val2 += (int)(xyz.charAt(6)-48);
									}
								}
								else if ((label_Image6.getIcon()).toString().equals((deck[i]).toString())) {
									String xyz = (deck[i]).toString();
									if (xyz.charAt(7)== '0') {
										val2 += 10;
									}
									else {
										val2 += (int)(xyz.charAt(6)-48);
									}
								}
								else if ((label_Image1.getIcon()).toString().equals((deck[i]).toString())) {
									String xyz = (deck[i]).toString();
									if (xyz.charAt(7)== '0') {
										val1 += 10;
									}
									else {
										val1 += (int)(xyz.charAt(6)-48);
									}
								}
								else if ((label_Image2.getIcon()).toString().equals((deck[i]).toString())) {
									String xyz = (deck[i]).toString();
									if (xyz.charAt(7)== '0') {
										val1 += 10;
									}
									else {
										val1 += (int)(xyz.charAt(6)-48);
									}
								}
								else if ((label_Image3.getIcon()).toString().equals((deck[i]).toString())) {
									String xyz = (deck[i]).toString();
									if (xyz.charAt(7) == '0') {
										val1 += 10;
									}
									else {
										val1 += (int)(xyz.charAt(6)-48);
									}
								}
							}
						}
					}
				}
				if (val1 %10>=val2 %10) {
					String dealwin = "Sorry! The Dealer wins this round!";
					JOptionPane.showMessageDialog(new Frame(), dealwin, "Message", JOptionPane.INFORMATION_MESSAGE);
					if (Totalmun==0) {
						String mes = "Game Over!\n" + "You have no more money!\n" + "Please start a new game!\n";
						JOptionPane.showMessageDialog(new Frame(), mes, "Message", JOptionPane.INFORMATION_MESSAGE);
						label_info.setText("Please start a new game!");
						label_money.setText("You have no more money! ");
						btn_start.setEnabled(false);
						btn_result.setEnabled(false);
						btn_rpcard1.setEnabled(false);
						btn_rpcard2.setEnabled(false);
						btn_rpcard3.setEnabled(false);
					}
					else {
						Totalmun -= (Integer.valueOf(txt_inputbet.getText())); 
						label_info.setText("Amount of money you have: $" + Totalmun);
					}
				}
				else {
					String playwin = "Congratulations! You win this round!";
					JOptionPane.showMessageDialog(new Frame(), playwin, "Message", JOptionPane.INFORMATION_MESSAGE);
					Totalmun += (Integer.valueOf(txt_inputbet.getText())); 
					label_info.setText("Amount of money you have: $" + Totalmun);
				}
			}
			if (Totalmun==0) {
				String mes = "Game Over!\n" + "You have no more money!\n" + "Please start a new game!\n";
				JOptionPane.showMessageDialog(new Frame(), mes, "Message", JOptionPane.INFORMATION_MESSAGE);
				label_info.setText("Please start a new game!");
				label_money.setText("You have no more money! ");
				btn_start.setEnabled(false);
				btn_result.setEnabled(false);
				btn_rpcard1.setEnabled(false);
				btn_rpcard2.setEnabled(false);
				btn_rpcard3.setEnabled(false);
			}
			else {
			btn_start.setEnabled(true);
			btn_result.setEnabled(false);
			btn_rpcard1.setEnabled(false);
			btn_rpcard2.setEnabled(false);
			btn_rpcard3.setEnabled(false);
			
			
			label_Image1.setIcon(new ImageIcon("card_back.gif"));
			label_Image2.setIcon(new ImageIcon("card_back.gif"));
			label_Image3.setIcon(new ImageIcon("card_back.gif"));
			label_Image4.setIcon(new ImageIcon("card_back.gif"));
			label_Image5.setIcon(new ImageIcon("card_back.gif"));
			label_Image6.setIcon(new ImageIcon("card_back.gif"));
			
			label_money.setText("Please place your bet! ");
			}
		}
	}
}
