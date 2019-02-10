/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;


public class ElevatorSubsystem extends Subsystem {
 public TalonSRX elevator = new TalonSRX(RobotMap.elevatorTalon); 

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

  public ElevatorSubsystem() {
		
    elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
		elevator.setSensorPhase(kSensorPhase); 
		elevator.setInverted(kMotorInvert);
		elevator.setNeutralMode(NeutralMode.Brake);
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
		
		// Not sure if necessary?
    // int absolutePosition = elevator.getSensorCollection().getPulseWidthPosition();
    // elevator.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);
  }

  public double moveToTarget(double rotations) {
    /* Get gamepad axis - forward stick is positive */
		// double leftYstick = -1.0 * Robot.m_oi.DriveY();
		 double target = rotations * 4096;
		/* Motion Magic */ 
		elevator.set(ControlMode.MotionMagic, target);
		_loops++;
		return target;
  }

	public void printer(double target) {
		/* Prepare line to print */
		/* Get current Talon SRX motor output */
		double motorOutput = elevator.getMotorOutputPercent();
		sb.append("\nOut:");
		sb.append(motorOutput);
		sb.append("\nVel:");
		sb.append(elevator.getSelectedSensorVelocity(kPIDLoopIdx));
		sb.append("\nerr:");
		sb.append("u");
		sb.append(getError(elevator));
		sb.append("\ttrg:");
		sb.append(target);
		sb.append("u");

		if(_loops >= 20) {
			System.out.println(sb.toString());
			_loops = 0;
		}
	}

  public int getError(TalonSRX t) {
		return t.getClosedLoopError(0);
	}

	public void setPos(TalonSRX t, int sensorPos) {
		t.setSelectedSensorPosition(sensorPos, kPIDLoopIdx, kTimeoutMs);
	}

	public int getPos(TalonSRX t) {
		return t.getSelectedSensorPosition(kPIDLoopIdx);
	}

	public double reset() {
		moveToTarget(0.);
		_loops++;
		return 0.;
	}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
  }
}
