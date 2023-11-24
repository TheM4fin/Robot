package sample.Commands;

import java.util.Random;

public abstract class AbstractTankCommand {

	public int commandParameter;

	public int getCommandParameter() {
		return commandParameter;
	}

	public void setCommandParameter(int commandParameter) {
		this.commandParameter = commandParameter;
	}

	public void MutateCommand() {
	}

	public void MutateCommand(double mutationProbability) {
		Random randomN = new Random();
		double chanceNum = randomN.nextDouble();
		if (chanceNum <= mutationProbability) {
			this.MutateCommand();
		}
				
	}
	
	public AbstractTankCommand clone() {
		return this;
	};

}
