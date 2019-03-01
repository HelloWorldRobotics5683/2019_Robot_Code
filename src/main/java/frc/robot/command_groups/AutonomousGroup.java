/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.command_groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;

public class AutonomousGroup extends CommandGroup {

  public AutonomousGroup() {
    addParallel(new MecanumDriveCommand());
    addSequential(new IntakeInit());
    addSequential(new ResetElevatorCommand());
  }
}
