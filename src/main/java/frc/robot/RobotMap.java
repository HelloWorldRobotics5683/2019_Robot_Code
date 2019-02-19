/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
// Test for github
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static final int FL = 0;
	public static final int FR = 1;
	public static final int RR = 2;
	public static final int RL = 3;

	public static final int Ult1 = 0;
	public static final int Ult2 = 1;

	public static final int intakeTalon = 2;
	public static final int elevatorTalon = 1;

	public static final double kHighThrottle = 1.0;
	public static final double kLowThrottle = 0.25;

	public static final double l1Ratio = 1740 / 17.25;

	public static final double kLevel1 = 3200;
	public static final int kLevel2 = 12605;
	public static final int kLevel3 = 22230;
}
