/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.triggers.DoubleButton;
import frc.robot.commands.*;
import frc.robot.command_groups.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  public static XboxController xb = new XboxController(0);

  Button A;
  Button B;
  Button X;
  Button Y;
  Button LB;
  Button RB;
  Button Back;
  Button Start;
  Button LS; // Click left stick
  Button RS; // Click right stick
  Button b11;
  Button b12;
  DoubleButton intakeReset;
  DoubleButton outtakeL1;
  DoubleButton outtakeL2;
  DoubleButton outtakeL3;
  DoubleButton goHome;
  private int num_of_sticks = 2;

  public OI() {
    A = new JoystickButton(xb, 1);
	  B = new JoystickButton(xb, 2);
	  X = new JoystickButton(xb, 3);
	  Y = new JoystickButton(xb, 4);
	  LB = new JoystickButton(xb, 5);
	  RB = new JoystickButton(xb, 6);
	  Back = new JoystickButton(xb, 7);
	  Start = new JoystickButton(xb, 8);
	  LS = new JoystickButton(xb, 9);
	  RS = new JoystickButton(xb, 10);
	  b11 = new JoystickButton(xb, 11);
    b12 = new JoystickButton(xb, 12);
    intakeReset = new DoubleButton(xb, 5, 6); // LB and RB
    outtakeL1 = new DoubleButton(xb, 6, 1); // RB and A
    outtakeL2 = new DoubleButton(xb, 6, 2); // RB and B
    outtakeL3 = new DoubleButton(xb, 6, 4); // RB and Y
    goHome = new DoubleButton(xb, 6, 3); // RB and X

    X.whileHeld(new ElevatorCommand(0.)); // reset to very bottom
    Y.whenPressed(new ElevatorCommand(RobotMap.kLevel3));
    B.whenPressed(new ElevatorCommand(RobotMap.kLevel2));
    A.whileHeld(new ElevatorCommand(RobotMap.kLevel1));
    LB.whileHeld(new ElevatorManualCommand());
    Start.whenPressed(new IntakeCommand());
    Back.whenPressed(new StopMovingCommand());
    LS.whenPressed(new ThrottleCommand());
    intakeReset.whenActive(new ResetIntakeCommand());
    RB.whenPressed(new OuttakeGroup());
    
  }
  
  public Boolean getButton(int bNum) {
    return xb.getRawButton(bNum);
  }

  public int getNumSticks() {
    return num_of_sticks;
  }

  public double DriveTwist() {
    if (LTriggerPressed()) {
      return -1.0 * xb.getTriggerAxis(Hand.kLeft);
    } else if(RTriggerPressed()) {
      return xb.getTriggerAxis(Hand.kRight);
    }
    return 0.0;
  }

  public Double DriveY() {
    return xb.getY(Hand.kLeft);
  }

  public Double DriveX() {
    return xb.getX(Hand.kLeft);
  }

  public Double ElevY() {
    return xb.getY(Hand.kRight);
  }

  public Double ElevX() {
    return xb.getX(Hand.kRight);
  }

  public boolean LTriggerPressed() {
		if (xb.getTriggerAxis(Hand.kLeft) > 0) {
			return true;
		}
		return false;
	}

	public boolean RTriggerPressed() {
		if (xb.getTriggerAxis(Hand.kRight) > 0) {
			return true;
		}
		return false;
  }
}
