/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class RumbleCommand extends Command {
  Double diff = Math.abs(Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra1) 
  - Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra2));

  public RumbleCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.ultraSys);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   // if (diff < 0.2) {
      Robot.m_oi.xb.setRumble(RumbleType.kLeftRumble, 1.);
      Timer.delay(5);
      Robot.m_oi.xb.setRumble(RumbleType.kRightRumble, 1.);
      Timer.delay(5);
    // }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_oi.xb.setRumble(RumbleType.kLeftRumble, 0.);
    Robot.m_oi.xb.setRumble(RumbleType.kRightRumble, 0.);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
