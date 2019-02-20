/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.NavxCommand;
import edu.wpi.first.wpilibj.AnalogInput;

public class UltrasonicSubsystem extends Subsystem {

  AnalogInput Ultra1 = new AnalogInput(RobotMap.Ult1);
  AnalogInput Ultra2 = new AnalogInput(RobotMap.Ult2);

  public double UltraConversion(AnalogInput ultra) {
    double ultra_voltage = ultra.getVoltage();
		double ultraInches = ((ultra_voltage/9.766) * 1000);

		return ultraInches;
	}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new NavxCommand());
  }
}
