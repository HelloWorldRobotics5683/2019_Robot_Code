/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.command_groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;

public class OuttakeGroup extends CommandGroup {
  double ticks;

  public OuttakeGroup(/*double ticks*/) {
    System.out.println("OT");
    // this.ticks = ticks;
    //Raise elevator to target
    // addSequential(new ElevatorCommand(ticks));
    // Move forward for y amount (can be overridden)
    addSequential(new AutoDriveCommand(0., .1, 0.));
    //Rotate intake to 0 to release hatch
    addSequential(new ResetIntakeCommand());
    //Back away slowly (can be overridden)
    addSequential(new AutoDriveCommand(0., -.1, 0.));
    // addParallel(new ElevatorCommand(ticks));
    // addSequential(new IntakeCommand());
  }
}
