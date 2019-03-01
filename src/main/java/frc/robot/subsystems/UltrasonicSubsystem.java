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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class UltrasonicSubsystem extends Subsystem {

  public AnalogInput Ultra1 = new AnalogInput(RobotMap.Ult1);
  public AnalogInput Ultra2 = new AnalogInput(RobotMap.Ult2);

  public double UltraConversion(AnalogInput ultra) {
    double ultra_voltage = ultra.getVoltage();
		double ultraInches = ((ultra_voltage/9.766) * 1000);

		return ultraInches;
  }

  public void pushUltraData() {
    SmartDashboard.putNumber("Ultra1", UltraConversion(Ultra1));
    SmartDashboard.putNumber("Ultra2", UltraConversion(Ultra2));
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new NavxCommand());
  }
}
