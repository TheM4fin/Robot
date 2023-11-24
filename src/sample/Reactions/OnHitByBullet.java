package sample.Reactions;

import sample.Commands.AbstractTankCommand;

public class OnHitByBullet extends AbstractReactions {

	@Override
	public String toString() {
		String result = "";
		result += "\npublic void onHitByBullet(HitByBulletEvent e) {\n";
		for (int i = 0; i < this.commandList.size(); i++) {
			result += commandList.get(i);
		}
		result += "}\n";
		return result;
	}
	
	@Override
	public AbstractReactions clone() {
		OnHitByBullet newOnHitByBullet = new OnHitByBullet();
		for(AbstractTankCommand command : this.getCommandList()) {
			newOnHitByBullet.addCommand(command.clone());
		}
		return newOnHitByBullet;
	}
}
