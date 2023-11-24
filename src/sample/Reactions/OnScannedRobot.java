package sample.Reactions;

import sample.Commands.AbstractTankCommand;

public class OnScannedRobot extends AbstractReactions {

	@Override
	public String toString() {
		String result = "";
		result += "\npublic void onScannedRobot(ScannedRobotEvent e) {\n";
		for (int i = 0; i < this.commandList.size(); i++) {
			result += commandList.get(i);
		}
		result += "}\n";
		return result;
	}
	
	@Override
	public AbstractReactions clone() {
		OnScannedRobot newOnScannedRobot = new OnScannedRobot();
		for(AbstractTankCommand command : this.getCommandList()) {
			newOnScannedRobot.addCommand(command.clone());
		}
		return newOnScannedRobot;
	}
}
