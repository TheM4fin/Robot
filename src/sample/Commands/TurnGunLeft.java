package sample.Commands;

import java.util.Random;

public class TurnGunLeft extends AbstractTankCommand {

	
	public TurnGunLeft() {
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
		return "turnGunLeft(" + this.commandParameter + ");\n";
	}
	
	@Override
	public AbstractTankCommand clone() {
		TurnGunLeft newTurnGunLeft = new TurnGunLeft();
		newTurnGunLeft.setCommandParameter(this.getCommandParameter());
		return newTurnGunLeft;
	}


}
