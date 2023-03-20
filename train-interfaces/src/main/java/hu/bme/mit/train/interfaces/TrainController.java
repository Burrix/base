package hu.bme.mit.train.interfaces;

public interface TrainController {

	void followSpeed();

	int getReferenceSpeed();

	void setSpeedLimit(int speedLimit);

	void setJoystickPosition(int joystickPosition);

	public int getSpeedForGivenTime(String time);

	public int getJoystickForGivenTime(String time);
}
