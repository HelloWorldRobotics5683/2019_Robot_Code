/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.NavxCommand;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class NavxSubsystem extends Subsystem {
  public static AHRS ahrs = new AHRS(SPI.Port.kMXP);

  public void pushData() {
    SmartDashboard.putNumber("IMU_Yaw", ahrs.getYaw());

    SmartDashboard.putNumber("Ultrasonic", Robot.ultraSys.UltraConversion());

    SmartDashboard.putNumber("Displacement_X", ahrs.getDisplacementX());
    SmartDashboard.putNumber("Displacement_Y", ahrs.getDisplacementY());
    SmartDashboard.putNumber("Displacement_Z", ahrs.getDisplacementZ());
  }

  public void reset() {
    ahrs.reset();
    ahrs.resetDisplacement();
  }

  public Boolean isChanging () {
    return ahrs.isMoving() || ahrs.isRotating();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new NavxCommand());
  }
}
