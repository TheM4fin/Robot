package sample.Commands;

import java.util.Random;

public class Fire extends AbstractTankCommand {

	public Fire() {
		super();
		MutateCommand();
	}
	
	@Override
	public void MutateCommand() {
		Random ranNum = new Random();
		this.commandParameter = ranNum.nextInt(3) + 1;
	}
	@Override
	public String toString() {
		return "fire(" + this.commandParameter + ");\n";
	}
	
	@Override
	public AbstractTankCommand clone() {
		Fire newFire = new Fire();
		newFire.setCommandParameter(this.getCommandParameter());
		return newFire;
	}


}
