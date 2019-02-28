/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.command_groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.RobotMap;
import frc.robot.Robot;
import frc.robot.commands.*;

public class IntakeGroup extends CommandGroup {

  public IntakeGroup() {
    // Rotate intake to 0
    addParallel(new ResetIntakeCommand());
    // Move elevator to 1st level, will stay if already at 1
    addSequential(new ElevatorCommand(RobotMap.kLevel1));
    // Drive forward until between 11 and 12 inches away from loading zone (can be overridden)
    addSequential(new AutoDriveCommand(0., .1, 0.));
    if((Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra1) < 11.2 &&
      Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra1) > 11) &&
      (Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra2) < 11.2 &&
      Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra2) > 11)) {
         new StopMovingCommand();
    }
    // Rotate intake 0.5 rotations to grab intake
    addSequential(new IntakeCommand());
    // Back away from loading zone (Can be overridden)
    addSequential(new AutoDriveCommand(0., -.1, 0.));
  }
}
