 
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.swing.*;



public class consoleArray extends JFrame{


	private static final long serialVersionUID = 5797645173365007976L;
	private ArrayList<Console> consoleArray = new ArrayList<Console>();
	private ArrayList<Game> gameArray = new ArrayList<Game>();

	JFrame outputFrame = new JFrame("Current Console List");
	JTextArea outputText = new JTextArea(8,40);

	
	public void cGui(){
		
		JFrame mainFrame = new JFrame("Console Sorter");
		JPanel bottomPanel = new JPanel();
		JPanel topPanel = new JPanel();
		
		mainFrame.setSize(290,140);
		mainFrame.setLocation(20,20);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BorderLayout flo = new BorderLayout();
		GridLayout floBottom = new GridLayout(1,2);
		GridLayout floTop = new GridLayout(2,2);
		mainFrame.setLayout(flo);
		bottomPanel.setLayout(floBottom);
		topPanel.setLayout(floTop);
		
	    ActionListener addButton = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	addConsolePrompt();
	        }
	      };
	      
	      ActionListener loadButton = new ActionListener() {
		        public void actionPerformed(ActionEvent actionEvent) {
		        	restore();
		        	JOptionPane.showMessageDialog(outputFrame, "Save file has been successfully loaded.");
		        }
		      };
		      
			    ActionListener saveButton = new ActionListener() {
			        public void actionPerformed(ActionEvent actionEvent) {
			        	save();
			        	JOptionPane.showMessageDialog(outputFrame, "Save file has been successfully saved.");
			        }
			      };
			    
			    ActionListener removeConsoleButton = new ActionListener() {
			        public void actionPerformed(ActionEvent actionEvent) {
			        	removeConsole();
			        }
			      };
				    ActionListener sortButton = new ActionListener() {
				        public void actionPerformed(ActionEvent actionEvent) {
				        	sortConsole();
				        }
				      };
					    ActionListener addGameButton = new ActionListener() {
					        public void actionPerformed(ActionEvent actionEvent) {
					        	addGamePrompt();
					        }
					      };
						    ActionListener removeGameButton = new ActionListener() {
						        public void actionPerformed(ActionEvent actionEvent) {
						        	removeGame();
						        }
						      };
			      
			      
		JButton addGame = new JButton("Add Game");
		JButton addConsole = new JButton("Add Console");
		JButton save = new JButton("Save");
		JButton	load = new JButton("Load");
		JButton	sort = new JButton("Sort");
		JButton	removeConsole = new JButton("Remove Console");
		JButton removeGame = new JButton("Remove Game");
		
		addConsole.addActionListener(addButton);
		addGame.addActionListener(addGameButton);
		load.addActionListener(loadButton);
		save.addActionListener(saveButton);
		removeConsole.addActionListener(removeConsoleButton);
		removeGame.addActionListener(removeGameButton);
		sort.addActionListener(sortButton);
		
		mainFrame.add(sort, BorderLayout.CENTER);
		mainFrame.add(bottomPanel, BorderLayout.SOUTH);
		mainFrame.add(topPanel, BorderLayout.NORTH);
		topPanel.add(addGame);
		topPanel.add(addConsole);
		topPanel.add(removeGame);
		topPanel.add(removeConsole);
		bottomPanel.add(save);
		bottomPanel.add(load);
		
		

		mainFrame.setVisible(true);
	}
	
	public void addConsolePrompt(){
		
		final JFrame addConsole = new JFrame("Add New Console");
		addConsole.setSize(300, 80);
		addConsole.setLocation(20,180);
		final JTextField consoleName = new JTextField("Enter Console Name            ");
		JButton submit = new JButton("Submit");

	    ActionListener submitClick = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	Console tempConsole = new Console("initialize", "initialize");
	        	tempConsole.setName(consoleName.getText());
	        	addConsole(tempConsole);
	        	addConsole.dispose();
	        }
	    };
		
		FlowLayout flo = new FlowLayout();
		addConsole.setLayout(flo);
		
		addConsole.add(submit);
		addConsole.add(consoleName);
		submit.addActionListener(submitClick);
		
		addConsole.setVisible(true);
		

		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addGamePrompt(){
		
		final JFrame addGame = new JFrame("Add New Game");
		addGame.setSize(300, 120);
		addGame.setLocation(20,180);
		final JTextField gameName = new JTextField("          Enter Game Name          ");
		JButton submit = new JButton("Submit");

		String[] consoleNames = new String[consoleArray.size()];
		int i = 0;
		for(Console temp : consoleArray){
			consoleNames[i] = temp.getName();
			i++;
		}
		final JComboBox gameBox = new JComboBox(consoleNames);
		
		
	    ActionListener submitClick = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	
	            String selectedConsole =  (String) gameBox.getSelectedItem();
	            
	        	Game tempGame = new Game("initialize","initialize");
	        	tempGame.setName(gameName.getText());
	        	tempGame.setConsole(selectedConsole);
	        	addGame(tempGame);
	        	addGame.dispose();
	        }
	    };
		
		FlowLayout flo = new FlowLayout();
		addGame.setLayout(flo);
		
		addGame.add(gameBox);
		addGame.add(submit);
		addGame.add(gameName);
		submit.addActionListener(submitClick);
		
		addGame.setVisible(true);
			
	}
	
	public void addConsole(Console thisOne){
		
		consoleArray.add(thisOne);
		list();
	}

	public void addGame(Game thisOne){
		
		gameArray.add(thisOne);
		list();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeConsole(){
		final JFrame removeFrame = new JFrame("Remove a Console");
		removeFrame.setSize(300, 80);
		removeFrame.setLocation(20,180);
		BorderLayout removeFlo = new BorderLayout();
		
		// Populating the combo box with a string of all the current console names.
		String[] consoleNames = new String[consoleArray.size()];
		int i = 0;
		for(Console temp : consoleArray){
			consoleNames[i] = temp.getName();
			i++;
		}
		final JComboBox consoleBox = new JComboBox(consoleNames);
		JLabel removeLabel = new JLabel("  Select console to remove:  ");
		JButton removeButton = new JButton("Remove");
		
		// Removing the specific selected object when the button is clicked
	      ActionListener removeSubmit = new ActionListener() {
		        public void actionPerformed(ActionEvent actionEvent) {
		        	
		            String selectedConsole =  (String) consoleBox.getSelectedItem();
		        	
		    		for (Iterator<Console> iterator = consoleArray.iterator(); iterator.hasNext(); ) {
		  			  Console px = iterator.next();
		  			  if(px.getName().equals(selectedConsole)){
		  			    iterator.remove();
		  			  }
		  			}
		    		
		    		list();
		    		removeFrame.dispose();
		        	
		        }
		      };
		      
		removeFrame.setLayout(removeFlo);
		removeButton.addActionListener(removeSubmit);
		removeFrame.add(consoleBox, BorderLayout.CENTER);
		removeFrame.add(removeLabel, BorderLayout.WEST);
		removeFrame.add(removeButton, BorderLayout.EAST);
		removeFrame.setSize(400,70);
		removeFrame.setVisible(true);
		
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void removeGame(){
		final JFrame removeFrame = new JFrame("Remove a Game");
		removeFrame.setSize(300, 80);
		removeFrame.setLocation(20,180);
		BorderLayout removeFlo = new BorderLayout();
		
		// Populating the combo box with a string of all the current console names.
		String[] gameNames = new String[gameArray.size()];
		int i = 0;
		for(Game temp : gameArray){
			gameNames[i] = temp.getName();
			i++;
		}
		final JComboBox gameBox = new JComboBox(gameNames);
		JLabel removeLabel = new JLabel("  Select game to remove:  ");
		JButton removeButton = new JButton("Remove");
		
		// Removing the specific selected object when the button is clicked
	      ActionListener removeSubmit = new ActionListener() {
		        public void actionPerformed(ActionEvent actionEvent) {
		        	
		            String selectedGame =  (String) gameBox.getSelectedItem();
		        	
		    		for (Iterator<Game> iterator = gameArray.iterator(); iterator.hasNext(); ) {
		  			  Game px = iterator.next();
		  			  if(px.getName().equals(selectedGame)){
		  			    iterator.remove();
		  			  }
		  			}
		    		
		    		list();
		    		removeFrame.dispose();
		        	
		        }
		      };
		      
		removeFrame.setLayout(removeFlo);
		removeButton.addActionListener(removeSubmit);
		removeFrame.add(gameBox, BorderLayout.CENTER);
		removeFrame.add(removeLabel, BorderLayout.WEST);
		removeFrame.add(removeButton, BorderLayout.EAST);
		removeFrame.setSize(400,70);
		removeFrame.setVisible(true);
		
	}
	
	public void sortConsole(){
		Collections.sort(consoleArray, new sortConsoleByName());
		Collections.sort(gameArray, new sortGameByName());
		list();
	}
	
	public void list(){
		
		outputFrame.setSize(300, 300);
		outputFrame.setLocation(450, 20);
		//outputText.setTabSize(12);
		outputText.setText("");
		outputFrame.add(outputText);
		outputFrame.setVisible(true);
		
		for(Console temp : consoleArray){
			outputText.append(temp.getName() + System.lineSeparator());
			
			for(Game tempGame : gameArray){
				if(temp.getName().equals(tempGame.getConsole())){
				outputText.append("       " + tempGame.getName() + System.lineSeparator());
			
				}
			}
		}
	}
	
	public void save(){
		try{  

			FileOutputStream saveFile = new FileOutputStream("SaveConsoles.sav");
			ObjectOutputStream save = new ObjectOutputStream(saveFile);

			save.writeObject(consoleArray);

			saveFile.close();
			save.close();
		}
		catch (FileNotFoundException exc){			
			exc.printStackTrace();
		}
		catch (IOException exc){
			exc.printStackTrace();
		}
		
		try{  

			FileOutputStream saveGameFile = new FileOutputStream("SaveGames.sav");
			ObjectOutputStream saveTwo = new ObjectOutputStream(saveGameFile);

			saveTwo.writeObject(gameArray);

			saveGameFile.close();
			saveTwo.close();
		}
		catch (FileNotFoundException exc){			
			exc.printStackTrace();
		}
		catch (IOException exc){
			exc.printStackTrace();
		}
		
	}

	@SuppressWarnings({ "resource", "unchecked" })
	public void restore(){
		
		try{	
			FileInputStream fis = new FileInputStream("SaveConsoles.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
//			ArrayList<Console> consoleArray = (ArrayList<Console>) obj;
			consoleArray = (ArrayList<Console>) obj;
		}
		catch (FileNotFoundException exc){			
			exc.printStackTrace();
		}
		catch (IOException exc){
			exc.printStackTrace();
		}
		catch (ClassNotFoundException exc){
			exc.printStackTrace();
		}
		
		try{	
			FileInputStream fisTwo = new FileInputStream("SaveGames.sav");
			ObjectInputStream oisTwo = new ObjectInputStream(fisTwo);
			Object obj = oisTwo.readObject();
//			ArrayList<Console> consoleArray = (ArrayList<Console>) obj;
			gameArray = (ArrayList<Game>) obj;
		}
		catch (FileNotFoundException exc){			
			exc.printStackTrace();
		}
		catch (IOException exc){
			exc.printStackTrace();
		}
		catch (ClassNotFoundException exc){
			exc.printStackTrace();
		}
		
		list();
		
	}

}
