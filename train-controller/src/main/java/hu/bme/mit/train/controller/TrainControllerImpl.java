package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

import java.text.SimpleDateFormat;

import com.google.common.collect.*;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 0;
	public static Table<String, Integer, Integer> Tachograf = TreeBasedTable.create();

	@Override
	public void followSpeed() {
		String date = new SimpleDateFormat("yyyy.MM.dd.HH.mm").format(new java.util.Date());
		Tachograf.put(date, 1, referenceSpeed);
		Tachograf.put(date, 2, step);
		if (referenceSpeed > 20) {
			referenceSpeed = 0;
		}

		if (referenceSpeed < 0) {
			referenceSpeed = 0;
		} else {
			if (referenceSpeed + step > 0) {
				referenceSpeed += step;
			} else {
				referenceSpeed = 0;
			}
		}

		enforceSpeedLimit();
	}

	public boolean isEmpty() {
		return Tachograf.isEmpty();
	}

	public int getSpeedForGivenTime(String time) {
		return Tachograf.get(time, 1);
	}

	public int getJoystickForGivenTime(String time) {
		return Tachograf.get(time, 2);
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();

	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;
	}

}
