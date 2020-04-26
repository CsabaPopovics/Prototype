import java.util.Scanner;

public class Eskimo extends Pawn{
	
	public Eskimo() {super(); bodyTemp++;}
	
	public Eskimo(String name) {super(name); bodyTemp++;}

    public Eskimo(Pawn p){
        name=p.name;
        field=p.field;
        inventory = p.inventory;
        bodyTemp = p.bodyTemp;
        workUnit = p.workUnit;
        finished = p.finished;
    }

    @Override
    public void buildIgloo() {
       if(field.setIgloo())
    	   workUnit--;
    }

    @Override
    public String toString() {
        String res="type eskimo%n"+super.toString();
        return res;
    }

    public static Eskimo parse(Scanner scanner) {
        if(scanner.next().equals("type") && scanner.next().equals("eskimo")){
            return new Eskimo(Pawn.parse(scanner));
        }
        else return null;
    }
}
