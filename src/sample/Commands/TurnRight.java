package sample.Commands;

import java.util.Random;

public class TurnRight extends AbstractTankCommand {

	public TurnRight() {
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
		return "turnRight(" + this.commandParameter + ");\n";
	}

	@Override
	public AbstractTankCommand clone() {
		TurnRight newTurnRight = new TurnRight();
		newTurnRight.setCommandParameter(this.getCommandParameter());
		return newTurnRight;
	}

}
