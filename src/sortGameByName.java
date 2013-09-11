import java.util.Comparator;

public class sortGameByName implements Comparator<Game>{
	
	//  Basic use of comparator to sort game list alphabetically
	
	public int compare(Game emp1, Game emp2) {
		return emp1.getName().compareTo(emp2.getName());
	}
}