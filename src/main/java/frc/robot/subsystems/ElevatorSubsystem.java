/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import frc.robot.Robot;


public class ElevatorSubsystem extends Subsystem {

  TalonSRX elevator = new TalonSRX(2); 

  StringBuilder sb = new StringBuilder();
  int _loops = 0;
  
  // Constants
	public static final int kSlotIdx = 0;
	public static final int kPIDLoopIdx = 0;
	public static final int kTimeoutMs = 15;
	public static boolean kSensorPhase = true;
  public static boolean kMotorInvert = false;
  
  // Gains
  public final double kP = 0.2;
  public final double kI = 0.0;
  public final double kD = 0.0;
  public final double kF = 0.2;
  public final int kIzone = 0;
  public final double kPeakOutput = 1.0;

  double targetPos;

  public ElevatorSubsystem() {
		
    elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
		elevator.setSensorPhase(kSensorPhase); 
		elevator.setInverted(kMotorInvert);
		elevator.configNominalOutputForward(0, kTimeoutMs);
		elevator.configNominalOutputReverse(0, kTimeoutMs);
		elevator.configPeakOutputForward(1, kTimeoutMs);
		elevator.configPeakOutputReverse(-1, kTimeoutMs);
		elevator.configAllowableClosedloopError(kPIDLoopIdx, 0, kTimeoutMs);
		elevator.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
		elevator.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
		elevator.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
    elevator.config_kD(kPIDLoopIdx, kD, kTimeoutMs);
    
    elevator.configMotionCruiseVelocity(15000, kTimeoutMs);
		elevator.configMotionAcceleration(6000, kTimeoutMs);
		elevator.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
    
    int absolutePosition = elevator.getSensorCollection().getPulseWidthPosition();
    elevator.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);
  }

  public void loop() {
    /* Get gamepad axis - forward stick is positive */
		double leftYstick = -1.0 * Robot.m_oi.DriveY();

		/* Get current Talon SRX motor output */
		double motorOutput = elevator.getMotorOutputPercent();

		/* Prepare line to print */
		sb.append("\tOut%:");
		sb.append(motorOutput);
		sb.append("\tVel:");
		sb.append(elevator.getSelectedSensorVelocity(kPIDLoopIdx));

		/* Motion Magic */ 
			
		/*4096 ticks/rev * 10 Rotations in either direction */
		double targetPos = leftYstick * 4096 * 10.0;
		elevator.set(ControlMode.MotionMagic, targetPos);

		/* Append more signals to print when in speed mode */
		sb.append("\terr:");
		sb.append(elevator.getClosedLoopError(kPIDLoopIdx));
		sb.append("\ttrg:");
		sb.append(targetPos);
  }

  public int getError() {
		return elevator.getClosedLoopError();
	}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
