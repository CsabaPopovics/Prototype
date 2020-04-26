
public class Researcher extends Pawn{
	
	public Researcher() {super();}
	
	public Researcher(String name) {super(name);}

    @Override
    public void inspect(Direction d) {
        Field destination = field.getNeighbour(d);
        if(destination != null) {
        	System.out.println("Limit of the inspected field: " + destination.getLimit());
        	System.out.println("Current load of the inspected field: " + destination.getLoad());
        	workUnit--;
        }
    }
}
