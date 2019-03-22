/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.MecanumDriveCommand;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * Add your docs here.
 */
public class DriveTrainSubsystem extends Subsystem {
  double throttle = 0.95;
  public boolean isHigh = true;
  public static Victor frontL = new Victor(RobotMap.FL);
  public static Victor frontR = new Victor(RobotMap.FR);
  public static Victor rearL = new Victor(RobotMap.RL);
  public static Victor rearR = new Victor(RobotMap.RR);
  
  // TODO: Check before comp
  public static MecanumDrive MD = new MecanumDrive(frontL, rearL, frontR, rearR);
  

  public void DriveMecanumGeneric( double y, double x, double z) {
    	MD.driveCartesian(y, x, z);
  }

  public void setThrottle(double newThrottle) {
    throttle = newThrottle;
  }

  public Double getThrottle() {
    return throttle;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
   setDefaultCommand(new MecanumDriveCommand());
  }
}
