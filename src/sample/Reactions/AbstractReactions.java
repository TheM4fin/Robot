package sample.Reactions;

import java.util.ArrayList;
import java.util.Random;

import sample.Commands.*;

public abstract class AbstractReactions {

	public ArrayList<AbstractTankCommand> commandList = new ArrayList<AbstractTankCommand>();

	public ArrayList<AbstractTankCommand> getCommandList() {
		return commandList;
	}

	public void setCommandList(ArrayList<AbstractTankCommand> commandList) {
		this.commandList = commandList;
	}

	public void addCommand(AbstractTankCommand addedCommand) {
		this.commandList.add(addedCommand);
	}

	public void MutateReaction(double mutationProbability) {
		Random randomN = new Random();
		for (int i = 0; i < commandList.size(); i++) {
			double chanceNum = randomN.nextDouble();
			if (chanceNum <= mutationProbability) {
				int selectTargetCommand = randomN.nextInt(8);
				switch (selectTargetCommand) {
				case 0: // ahead
					Ahead mutatedAhead = new Ahead();
					mutatedAhead.MutateCommand();
					commandList.set(i, mutatedAhead);
					break;
				case 1: // back
					Back mutatedBack = new Back();
					mutatedBack.MutateCommand();
					commandList.set(i, mutatedBack);
					break;
				case 2: // doNothing
					DoNothing mutatedDoNothing = new DoNothing();
					commandList.set(i, mutatedDoNothing);
					break;
				case 3: // fire
					Fire mutatedFire = new Fire();
					mutatedFire.MutateCommand();
					commandList.set(i, mutatedFire);
					break;
				case 4: // turnGunLeft
					TurnGunLeft mutatedTurnGunLeft = new TurnGunLeft();
					mutatedTurnGunLeft.MutateCommand();
					commandList.set(i, mutatedTurnGunLeft);
					break;
				case 5: // turnGunRight
					TurnGunRight mutatedTurnGunRight = new TurnGunRight();
					mutatedTurnGunRight.MutateCommand();
					commandList.set(i, mutatedTurnGunRight);
					break;
				case 6: // turnLeft
					TurnLeft mutatedTurnLeft = new TurnLeft();
					mutatedTurnLeft.MutateCommand();
					commandList.set(i, mutatedTurnLeft);
					break;
				case 7: // turnRight
					TurnRight mutatedTurnRight = new TurnRight();
					mutatedTurnRight.MutateCommand();
					commandList.set(i, mutatedTurnRight);
					break;
				}
			}
		}
	}
	
	public AbstractReactions clone() {
		return this;
	}
	
	
	
	
}
