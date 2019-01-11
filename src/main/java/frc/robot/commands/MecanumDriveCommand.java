/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MecanumDriveCommand extends Command {
  public MecanumDriveCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.dt);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.dt.DriveMecanumGeneric(
      -Robot.m_oi.DriveX() * throttle(),
      Robot.m_oi.DriveY() * throttle(),
      -Robot.m_oi.DriveTwist() * throttle()
    );
  }
  
  protected Double throttle() {
    Double throttle = (-Robot.m_oi.DriveThrottle() + 1)/2 * 0.9;
    return throttle;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
