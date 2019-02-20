/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

// Imports for xbox controller and buttons
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
// Import the button group classes for use in OI
import frc.robot.triggers.DoubleButton;
import frc.robot.triggers.SingleButton;
// Import all commands/command groups to bind to buttons
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

  // Constructing new xbox controller, buttons, and button groups
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
  SingleButton level1;
  SingleButton level2;
  SingleButton level3;
  DoubleButton goHome;
  private int num_of_sticks = 2;

  public OI() {
    // Initializing buttons and button groups
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
   	 // kate **** level1 = new SingleButton(xb, 1, 6); // A and !RB	  
    outtakeL2 = new DoubleButton(xb, 6, 2); // RB and B
    outtakeL3 = new DoubleButton(xb, 6, 4); // RB and Y
    level1 = new SingleButton(xb, 1, 6);
    level2 = new SingleButton(xb, 2, 6);
    level3 = new SingleButton(xb, 4, 6);
    goHome = new DoubleButton(xb, 6, 3); // RB and X

    intakeReset.whenActive(new ResetIntakeCommand());
    X.whileHeld(new ElevatorCommand(0.)); // reset to very bottom
    // Y.whenPressed(new ElevatorCommand(RobotMap.kLevel3)); // Move elevator to 3rd level
    // B.whenPressed(new ElevatorCommand(RobotMap.kLevel2)); // Move elevator to 2nd level
    // A.whenPressed(new ElevatorCommand(RobotMap.kLevel1)); // Move elevator to 1st level
    level1.whenActive(new ElevatorCommand(RobotMap.kLevel1));
    level2.whenActive(new ElevatorCommand(RobotMap.kLevel2));
    level3.whenActive(new ElevatorCommand(RobotMap.kLevel3));
    LB.whileHeld(new ElevatorManualCommand()); // Manually control elevator with right stick
    Start.whenPressed(new IntakeCommand()); // Rotate the intake mechanism 0.5 rotations
    Back.whenPressed(new StopMovingCommand()); // Stops the drivetrain motors
    LS.whenPressed(new ThrottleCommand()); /* Toggles between high and low speeds,
                                              determined by kHighThrottle and kLowThrottle*/
    outtakeL1.whenActive(new OuttakeGroup()); /* Drives the robot forward to put hatch panel on loading zone,
                                         resets the intake to release the hatch, and then backs away */
  }
  
  // Method for testing if button with number bNum has been pressed, returns true if button is pressed
  public Boolean getButton(int bNum) {
    return xb.getRawButton(bNum);
  }

  // Returns the number of joysticks on the controller (2)
  public int getNumSticks() {
    return num_of_sticks;
  }

  /* Takes the inputs of the two triggers to turn the robot, or does nothing when neither is pressed. 
  The left trigger is inverted so that the robot turns counter-clockwise. The right trigger turns the
  robot clockwise*/
  public double DriveTwist() {
    if (LTriggerPressed()) {
      return -1.0 * xb.getTriggerAxis(Hand.kLeft);
    } else if(RTriggerPressed()) {
      return xb.getTriggerAxis(Hand.kRight);
    }
    return 0.0;
  }

  // returns a double for the left joystick's y-axis. Positive = up, negative = down
  public Double DriveY() {
    return xb.getY(Hand.kLeft);
  }

  // returns a double for the left joystick's x-axis. Positive = right, negative = left
  public Double DriveX() {
    return xb.getX(Hand.kLeft);
  }

  // returns a double for the right joystick's y-axis. Positive = up, negative = down
  public Double ElevY() {
    return xb.getY(Hand.kRight);
  }

  // returns a double for the right joystick's x-axis. Positive = right, negative = left
  public Double ElevX() {
    return xb.getX(Hand.kRight);
  }

  // returns true if the left trigger is being pressed, false otherwise
  public boolean LTriggerPressed() {
		if (xb.getTriggerAxis(Hand.kLeft) > 0) {
			return true;
		}
		return false;
	}

  // returns true if the right trigger is being pressed, false otherwise
	public boolean RTriggerPressed() {
		if (xb.getTriggerAxis(Hand.kRight) > 0) {
			return true;
		}
		return false;
  }
}
