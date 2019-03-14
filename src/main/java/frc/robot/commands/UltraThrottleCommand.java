/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class UltraThrottleCommand extends Command {
  public UltraThrottleCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.ultraSys);
    requires(Robot.dt);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double avgDist = (Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra1) + 
    Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra2)) / 2;
    if(avgDist < 6.0) {
      Robot.dt.setThrottle(0.1);
    } else if (Robot.dt.isHigh) {
      Robot.dt.setThrottle(0.95);
    } else {
      Robot.dt.setThrottle(0.3);
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
