package sample.Reactions;

import sample.Commands.AbstractTankCommand;

public class OnHitRobot extends AbstractReactions {

	@Override
	public String toString() {
		String result = "";
		result += "\npublic void onHitRobot(HitRobotEvent e) {\n";
		for (int i = 0; i < this.commandList.size(); i++) {
			result += commandList.get(i);
		}
		result += "}\n";
		return result;
	}
	
	@Override
	public AbstractReactions clone() {
		OnHitRobot newOnHitRobot = new OnHitRobot();
		for(AbstractTankCommand command : this.getCommandList()) {
			newOnHitRobot.addCommand(command.clone());
		}
		return newOnHitRobot;
	}
}
