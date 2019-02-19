/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorCommand extends Command {
  private double target;

  public ElevatorCommand(double target) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.elevSys);
    this.target = target;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double position = Robot.elevSys.moveToTarget(this.target);
    Robot.elevSys.printer(position);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.elevSys.getError() <= 20 && Robot.elevSys.getError() > 0;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("Target reached");
    System.out.println("Error after motion magic: " + Robot.elevSys.getError() + "u");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
