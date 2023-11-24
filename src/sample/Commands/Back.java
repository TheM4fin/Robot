package sample.Commands;

import java.util.Random;

public class Back extends AbstractTankCommand {

	public Back() {
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
		return "back(" + this.commandParameter + ");\n";
	}
	
	@Override
	public AbstractTankCommand clone() {
		Back newBack = new Back();
		newBack.setCommandParameter(this.getCommandParameter());
		return newBack;
	}
	

}
