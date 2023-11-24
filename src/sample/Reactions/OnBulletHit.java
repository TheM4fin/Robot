package sample.Reactions;

import sample.Commands.AbstractTankCommand;

public class OnBulletHit extends AbstractReactions {

	
	@Override
	public String toString() {
		String result = "";
		result += "\npublic void onBulletHit(BulletHitEvent event){\n";
		for (int i = 0; i < this.commandList.size(); i++) {
			result += commandList.get(i);
		}
		result += "}\n";
		return result;
	}
	
	@Override
	public AbstractReactions clone() {
		OnBulletHit newOnBulletHit = new OnBulletHit();
		for(AbstractTankCommand command : this.getCommandList()) {
			newOnBulletHit.addCommand(command.clone());
		}
		return newOnBulletHit;
	}
}
