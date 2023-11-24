package sample.Commands;

import java.util.Random;

public class TurnLeft extends AbstractTankCommand {

	public TurnLeft() {
		super();
		MutateCommand();
	}
	
	@Override
	public void MutateCommand() {
		Random ranNum = new Random();
		this.commandParameter = ranNum.nextInt(180) + 1;
	}
	
	@Override
	public String toString() {
		return "turnLeft(" + this.commandParameter + ");\n";
	}

	@Override
	public AbstractTankCommand clone() {
		TurnLeft newTurnLeft = new TurnLeft();
		newTurnLeft.setCommandParameter(this.getCommandParameter());
		return newTurnLeft;
	}


}
