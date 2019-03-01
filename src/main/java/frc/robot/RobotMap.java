/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Port numbers for the driving motors
	public static final int FL = 0;
	public static final int FR = 1;
	public static final int RR = 2;
	public static final int RL = 3;

	// Port numbers for the ultrasonic sensors
	public static final int Ult1 = 0;
	public static final int Ult2 = 1;

	// Port numbers for the talons
	public static final int intakeTalon = 2;
	public static final int elevatorTalon = 1;

	// Port number for the limit switch
	public static final int limitSwitch = 1;

	// Constants for the throttle speeds
	public static final double kHighThrottle = 1.0;
	public static final double kLowThrottle = 0.3;

	// Constant of encoder ticks to inches for 1st elevator level
	// public static final double l1Ratio = 1740 / 17.25;

	// Constants in encoder ticks for the elevator levels
	// TODO: Get new constants for the rocket levels
	public static final double kLevel1 = 3324;
	public static final int kLevel2 = 14050;
	public static final int kLevel3 = 22170;
}
