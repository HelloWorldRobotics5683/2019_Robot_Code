/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class testCommand extends Command {
  int ticks = 0;
  public testCommand( int ticks) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.intakeSys);
    this.ticks=ticks;
  }
 
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.intakeSys.intake.setSelectedSensorPosition(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.intakeSys.moveToTarget(ticks);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
     System.out.println("Intake finished: " + Robot.intakeSys.intake.getSelectedSensorPosition());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
