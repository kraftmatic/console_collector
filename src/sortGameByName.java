import java.util.Comparator;

public class sortGameByName implements Comparator<Game>{
	
	public int compare(Game emp1, Game emp2) {
		return emp1.getName().compareTo(emp2.getName());
	}
}