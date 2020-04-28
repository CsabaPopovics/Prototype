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
        starterFieldName=p.starterFieldName; //Csak parserhez kell, később nem érdekes
        starterIsActive=p.starterIsActive; //Csak parserhez kell
        game=p.game;
    }

    public static Researcher parse(Scanner scanner, String name) {
	    {
	        return new Researcher(Pawn.parse(scanner, name));
        }

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
        String res=String.format("type researcher%n")+super.toString();
        return res;
    }
}
