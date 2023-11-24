package sample.Reactions;

import sample.Commands.AbstractTankCommand;

public class OnHitWall extends AbstractReactions {

	@Override
	public String toString() {
		String result = "";
		result += "\npublic void onHitWall(HitWallEvent e) {\n";
		for (int i = 0; i < this.commandList.size(); i++) {
			result += commandList.get(i);
		}
		result += "}\n";
		return result;
	}
	
	@Override
	public AbstractReactions clone() {
		OnHitWall newOnHitWall = new OnHitWall();
		for(AbstractTankCommand command : this.getCommandList()) {
			newOnHitWall.addCommand(command.clone());
		}
		return newOnHitWall;
	}
}
