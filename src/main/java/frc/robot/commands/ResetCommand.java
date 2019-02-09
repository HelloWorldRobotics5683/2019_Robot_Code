/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Robot;

public class ResetCommand extends Command {
  TalonSRX intake = Robot.intakeSys.getIntake();

  public ResetCommand() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.intakeSys);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.intakeSys.reset();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.intakeSys.getPos(intake) == 0;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("Has Reset");
    System.out.println("Rel pos after reset: " + Robot.intakeSys.getPos(intake) + "u");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
