import java.io.Serializable;


public class Game implements Serializable{
	
	private static final long serialVersionUID = 3442461123988281314L;
	
	private String console;
	private String name;
	
	Game(String thisName, String thisConsole){
		
		//  Right now this object just contains the game name and associated console.  To-do:  developer, publisher,
		//  release date, notes.
		
		name = thisName;
		console = thisConsole;
		
	}
	
	//  Setters and getters
	
	public void setConsole(String thisConsole){
		console = thisConsole;
	}
	
	public String getConsole(){
		return console;
	}
	public void setName(String thisName){
		name = thisName;
	}
	
	public String getName(){
		return name;
	}
}
