/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.command_groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.testCommand;

public class IntakeInitGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public IntakeInitGroup() {
    addSequential(new testCommand(3072));
    addSequential(new testCommand(2048));
  }
}
