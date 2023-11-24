package sample.Commands;

import java.util.Random;

public class Ahead extends AbstractTankCommand {

	public Ahead() {
		super();
		MutateCommand();
	}

	@Override
	public void MutateCommand() {
		Random ranNum = new Random();
		this.commandParameter = ranNum.nextInt(800) + 1;
	}

	@Override
	public String toString() {
		return "ahead(" + this.commandParameter + ");\n";
	}
	
	@Override
	public AbstractTankCommand clone() {
		Ahead newAhead = new Ahead();
		newAhead.setCommandParameter(this.getCommandParameter());
		return newAhead;
	}

}
