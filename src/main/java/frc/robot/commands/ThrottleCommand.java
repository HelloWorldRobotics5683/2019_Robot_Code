/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ThrottleCommand extends Command {
  public ThrottleCommand() {
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
    if (Robot.dt.isHigh) {
      System.out.println("Setting low speed.");
      SmartDashboard.putNumber("Throttle", RobotMap.kLowThrottle);
      Robot.dt.setThrottle(RobotMap.kLowThrottle);
    } else {
      System.out.println("Setting high speed.");
      SmartDashboard.putNumber("Throttle", RobotMap.kHighThrottle);
      Robot.dt.setThrottle(RobotMap.kHighThrottle);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
