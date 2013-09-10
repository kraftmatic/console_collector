import java.io.Serializable;


public class Console implements Serializable{
	
		private static final long serialVersionUID = -8466834904326309643L;
	
		private String name;
		private String maker;
		private int year;
		
		
		Console(String thisName, String thisMaker){
			name = thisName;
			maker = thisMaker;
		}
		
		public void setName(String thisName){
			name = thisName;
		}
		
		public String getName(){
			return name;
		}
		
		public void setMaker(){	
		}
		
		public String getMaker(){
			return maker;
		}

		public void setYear(){
		}

		public int getYear(){
			return year;
		}
		
		public void setNotes(){
		}
		
		public void getNotes(){
		}
	
		
}