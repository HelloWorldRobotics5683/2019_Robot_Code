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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class IntakeSubsystem extends Subsystem {
  public TalonSRX intake = new TalonSRX(RobotMap.intakeTalon); 

  int _loops = 0;

  // Constants
  public static final int kSlotIdx = 0;
  public static final int kPIDLoopIdx = 0;
  public static final int kTimeoutMs = 30;
  public static boolean kSensorPhase = false;
  public static boolean kMotorInvert = false;
  
  // Gains
  public final double kP = 2;
  public final double kI = 0.0;
  public final double kD = 8.0;
  public final double kF = 0.0;
  public final int kIzone = 0;
  public final double kPeakOutput = 0.8;

  //double kTargetPos;
  public boolean isAtZero = true;

  public IntakeSubsystem() {
	intake.setSelectedSensorPosition(2048);
    intake.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
	intake.setSensorPhase(kSensorPhase); 
	intake.setInverted(kMotorInvert);
	intake.setNeutralMode(NeutralMode.Brake);
	intake.configNominalOutputForward(0, kTimeoutMs);
	intake.configNominalOutputReverse(0, kTimeoutMs);
	intake.configPeakOutputForward(kPeakOutput, kTimeoutMs);
	intake.configPeakOutputReverse(-kPeakOutput, kTimeoutMs);
	intake.configAllowableClosedloopError(kPIDLoopIdx, 10, kTimeoutMs);
	intake.config_kF(kPIDLoopIdx, kF, kTimeoutMs);
	intake.config_kP(kPIDLoopIdx, kP, kTimeoutMs);
	intake.config_kI(kPIDLoopIdx, kI, kTimeoutMs);
	intake.config_kD(kPIDLoopIdx, kD, kTimeoutMs);

	// System.out.println("Setting absolute position...");	
    // absolutePosition = intake.getSensorCollection().getPulseWidthPosition();
    // setPos(intake, absolutePosition);
  
}
 	public double moveToTarget(double ticks) {
		double target = ticks;
		intake.set(ControlMode.Position, target);
		_loops++;
		isAtZero = !isAtZero;
		return target;
	}
	
	public void printer(double target) {
		/* Prepare line to print */
		if (_loops >= 1000) {
			/* Get motor output and cast to int 
			to remove decimal places */
			double motorOutput = intake.getMotorOutputPercent();
			System.out.println("loop out: " + (int) (motorOutput * 100) + "%");	// Percent
			System.out.println("loop pos: " + getPos() + "u");
			System.out.println("loop err: " + getError() + "u");	// Native Units
			System.out.println("loop trg: " + target + "u");	/// Native Units
			_loops = 0;
		}
	}

	public int getError() {
		return intake.getClosedLoopError(0);
	}

	public void setPos(int sensorPos) {
		intake.setSelectedSensorPosition(sensorPos, kPIDLoopIdx, kTimeoutMs);
	}

	public int getPos() {
		return intake.getSelectedSensorPosition(kPIDLoopIdx);
	}

	public void IntakeDash() {
		if (intake.getSelectedSensorPosition() == 0) {
			SmartDashboard.putString("Intake Position", "Down");
		} else if (intake.getSelectedSensorPosition() == 2048) {
			SmartDashboard.putString("Intake Position", "Up");
		} 
	}


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
  }
}
