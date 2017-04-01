package commands;

abstract public class PlayerCommand {
	@Override
	abstract public String toString();
	abstract public String serialize();
	abstract public String getEditLabel();
	abstract public String getCurrentValue();
	abstract public void setCurrentValue(String newValue);
}
