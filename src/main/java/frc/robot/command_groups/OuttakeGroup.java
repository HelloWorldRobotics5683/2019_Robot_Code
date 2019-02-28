/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.command_groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.*;

public class OuttakeGroup extends CommandGroup {
  double ticks;

  public OuttakeGroup(double ticks) {
    System.out.println("OT");
    this.ticks = ticks;
    // Move forward ultil both encoders are around 10 inches away from loading zone, then stop
    addSequential(new AutoDriveCommand(0., .1, 0.));
    if((Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra1) < 10.2 &&
      Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra1) > 10) &&
      (Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra2) < 10.2 &&
      Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra2) > 10)) {
         new StopMovingCommand();
    }
     // Raise elevator to target
     addSequential(new ElevatorCommand(ticks));
     // Move forward the rest of the way to the loading zone, then stop
     addSequential(new AutoDriveCommand(0., .1, 0.));
     if((Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra1) < 11.2 &&
       Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra1) > 11) &&
       (Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra2) < 11.2 &&
       Robot.ultraSys.UltraConversion(Robot.ultraSys.Ultra2) > 11)) {
          new StopMovingCommand();
     }
    //Rotate intake to 0 to release hatch
    addSequential(new ResetIntakeCommand());
    //Back away slowly (can be overridden)
    addSequential(new AutoDriveCommand(0., -.1, 0.));
  }
}
