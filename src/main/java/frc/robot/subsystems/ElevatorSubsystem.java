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
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;


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
  public final double kP = 1.0;
  public final double kI = 0.0125;
  public final double kD = 12;
  public final double kF = 0.1364;
  public final int kIzone = 75;
  public final double kPeakOutput = 0.001;

  public ElevatorSubsystem() {
		elevator.setSelectedSensorPosition(0);
		elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
		elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);
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
    
    	elevator.configMotionCruiseVelocity(600, kTimeoutMs);
		elevator.configMotionAcceleration(1000, kTimeoutMs);
		elevator.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
		
		// Not sure if necessary?
    // int absolutePosition = elevator.getSensorCollection().getPulseWidthPosition();
    // elevator.setSelectedSensorPosition(absolutePosition, kPIDLoopIdx, kTimeoutMs);
  }

  public double moveToTarget(double ticks) {
		 double target = ticks;
		/* Motion Magic */ 
		elevator.set(ControlMode.MotionMagic, target);
		_loops++;
		
		return target;
  }

	public void printer(double target) {
		/* Prepare line to print */
		/* Get current Talon SRX motor output */
		if(_loops >= 1000) {
			double motorOutput = elevator.getMotorOutputPercent();
			System.out.println("Out: " + (int) (motorOutput * 100) + "%");
			System.out.println("Vel: " + elevator.getSelectedSensorVelocity(kPIDLoopIdx));
			System.out.println("err: " + getError() + "u");
			System.out.println("trg: " + target + "u");
			// sb.append("\npos:");
			// sb.append(getPos());
			// sb.append("u");
			_loops = 0;
		}
	}

  public int getError() {
		return elevator.getClosedLoopError(0);
	}

	public void setPos(int sensorPos) {
		elevator.setSelectedSensorPosition(sensorPos, kPIDLoopIdx, kTimeoutMs);
	}

	public int getPos() {
		return elevator.getSelectedSensorPosition(0);
	}

	public void manualControl(double stickVal) {
		elevator.set(ControlMode.PercentOutput, stickVal);
	}

  @Override
  public void initDefaultCommand() {
	// Set the default command for a subsystem here.
  }
}
