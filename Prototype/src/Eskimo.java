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
        starterFieldName=p.starterFieldName; //Csak parserhez kell, később nem érdekes
        starterIsActive=p.starterIsActive; //Csak parserhez kell
        game=p.game;
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

    public static Eskimo parse(Scanner scanner, String name) {

            return new Eskimo(Pawn.parse(scanner, name));


    }
}
