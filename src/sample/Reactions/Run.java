package sample.Reactions;

import sample.Commands.AbstractTankCommand;

public class Run extends AbstractReactions {

	@Override
	public String toString() {
		String result = "";
		result += "\npublic void run() {\n";
		result += "while(true){\n";
		for (int i = 0; i < this.commandList.size(); i++) {
			result += commandList.get(i);
		}
		result += "}\n";
		result += "}\n";
		return result;
	}
	
	@Override
	public AbstractReactions clone() {
		Run newRun = new Run();
		for(AbstractTankCommand command : this.getCommandList()) {
			newRun.addCommand(command.clone());
		}
		return newRun;
	}

}
