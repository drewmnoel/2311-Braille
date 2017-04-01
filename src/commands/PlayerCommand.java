package commands;

abstract public class PlayerCommand {
	@Override
	abstract public String toString();
	abstract public String serialize();
}
