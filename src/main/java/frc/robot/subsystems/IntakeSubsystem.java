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

/**
 * Add your docs here.
 */
public class IntakeSubsystem extends Subsystem {
  public TalonSRX intake = new TalonSRX(RobotMap.intakeTalon); 

  StringBuilder sb = new StringBuilder();
  int _loops = 0;

  // Constants
  public static final int kSlotIdx = 0;
  public static final int kPIDLoopIdx = 0;
  public static final int kTimeoutMs = 30;
  public static boolean kSensorPhase = true;
  public static boolean kMotorInvert = false;
  
  // Gains
  public final double kP = 1.2;
  public final double kI = 0.0;
  public final double kD = 8.0;
  public final double kF = 0.0;
  public final int kIzone = 0;
  public final double kPeakOutput = 0.8;

  //double kTargetPos;
  int absolutePosition;

  public IntakeSubsystem() {

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
 	public double moveToTarget(double target) {
		intake.set(ControlMode.Position, target);
		_loops++;
		return target;
	}
	
	public void printer(double target) {
		/* Prepare line to print */
		sb.append("\nloop out:");
		/* Get motor output and cast to int 
		to remove decimal places */
		double motorOutput = intake.getMotorOutputPercent();
		sb.append((int) (motorOutput * 100));
		sb.append("%");	// Percent

		sb.append("\nloop pos:");
		sb.append(intake.getSelectedSensorPosition(0));
		sb.append("u"); 	// Native units
		
		sb.append("\nloop err:");
		sb.append(getError(intake));
		sb.append("u");	// Native Units

		sb.append("\nloop trg:");
		sb.append(target);
		sb.append("u");	/// Native Units

		/**
		 * Print every ten loops, printing too much too fast is generally bad
		 * for performance.
		 */
		if (++_loops >= 20) {
			System.out.println(sb.toString());
			_loops = 0;
		}

		/* Reset built string for next loop */
		sb.setLength(0);
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
