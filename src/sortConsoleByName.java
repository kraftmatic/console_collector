import java.util.Comparator;

public class sortConsoleByName implements Comparator<Console>{
	
	public int compare(Console emp1, Console emp2) {
		return emp1.getName().compareTo(emp2.getName());
	}
}