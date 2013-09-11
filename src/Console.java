import java.io.Serializable;


public class Console implements Serializable{
	
		private static final long serialVersionUID = -8466834904326309643L;
		
		//  Console object contains name and maker information.  To-do:  Add date released, number of players, notes
	
		private String name;
		private String maker;
		
		// Constructor just contains the console name and maker for now.
		
		Console(String thisName, String thisMaker){
			name = thisName;
			maker = thisMaker;
		}
		
		//  Setters and getters
		
		public void setName(String thisName){
			name = thisName;
		}
		
		public String getName(){
			return name;
		}
		
		public void setMaker(String thisMaker){
			maker = thisMaker;
		}
		
		public String getMaker(){
			return maker;
		}

		
}