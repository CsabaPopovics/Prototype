import java.util.ArrayList;
import java.util.Scanner;

public class Researcher extends Pawn{
	
	public Researcher() {super();}
	
	public Researcher(String name) {super(name);}

	public Researcher(Pawn p){
        name=p.name;
        field=p.field;
        inventory = p.inventory;
        bodyTemp = p.bodyTemp;
        workUnit = p.workUnit;
        finished = p.finished;
    }

    public static Researcher parse(Scanner scanner) {
	    if(scanner.next().equals("type") && scanner.next().equals("researcher")){
	        return new Researcher(Pawn.parse(scanner));
        }
	    else return null;
    }

    @Override
    public void inspect(Direction d) {
        Field destination = field.getNeighbour(d);
        if(destination != null) {
        	System.out.println("Limit of the inspected field: " + destination.getLimit());
        	System.out.println("Current load of the inspected field: " + destination.getLoad());
        	workUnit--;
        }
    }

    @Override
    public String toString() {
        String res="type researcher%n"+super.toString();
        return res;
    }
}
