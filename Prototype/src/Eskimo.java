
public class Eskimo extends Pawn{
	
	public Eskimo() {super(); bodyTemp++;}
	
	public Eskimo(String name) {super(name); bodyTemp++;}

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
}
