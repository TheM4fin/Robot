package sample.Commands;

import java.util.Random;

public class TurnGunRight extends AbstractTankCommand {

	public TurnGunRight() {
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
		return "turnGunRight(" + this.commandParameter + ");\n";
	}
	
	@Override
	public AbstractTankCommand clone() {
		TurnGunRight newTurnGunRight = new TurnGunRight();
		newTurnGunRight.setCommandParameter(this.getCommandParameter());
		return newTurnGunRight;
	}


}
