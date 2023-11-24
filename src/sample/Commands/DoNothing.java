package sample.Commands;

public class DoNothing extends AbstractTankCommand {

	@Override
	public String toString() {
		return "doNothing();\n";
	}
	
	@Override
	public AbstractTankCommand clone() {
		DoNothing newDoNothing = new DoNothing();
		return newDoNothing;
	}

}
