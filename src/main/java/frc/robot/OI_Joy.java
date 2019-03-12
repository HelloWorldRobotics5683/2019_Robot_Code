/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.triggers.*;
// Import all commands/command groups to bind to buttons
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI_Joy {
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

  // Constructing new joyox controller, buttons, and button groups
  public static Joystick joy = new Joystick(0);

  JoystickButton b1;
  JoystickButton b2;
  JoystickButton b3;
  JoystickButton b4;
  JoystickButton b5;
  JoystickButton b6;
  JoystickButton b7;
  JoystickButton b8;
  JoystickButton b9;
  JoystickButton b10;
  JoystickButton b11;
  JoystickButton b12;
//   DoubleButton intakeReset;
//   DoubleButton outtakeL1;
//   DoubleButton outtakeL2;
//   DoubleButton outtakeL3;
//   SingleButton level1;
//   SingleButton level2;
//   SingleButton level3;
//   DoubleButton goHome;
    DoubleButton manual;

  public OI_Joy() {
    // Initializing buttons and button groups
    b1 = new JoystickButton(joy, 1);
	b2 = new JoystickButton(joy, 2);
	b3 = new JoystickButton(joy, 3);
	b4 = new JoystickButton(joy, 4);
	b5 = new JoystickButton(joy, 5);
	b6 = new JoystickButton(joy, 6);
	b7 = new JoystickButton(joy, 7);
	b8 = new JoystickButton(joy, 8);
	b9 = new JoystickButton(joy, 9);
	b10 = new JoystickButton(joy, 10);
	b11 = new JoystickButton(joy, 11);
    b12 = new JoystickButton(joy, 12);
    // intakeReset = new DoubleButton(joy, 5, 6); // LB and RB
    // outtakeL1 = new DoubleButton(joy, 6, 1); // RB and A
    // outtakeL2 = new DoubleButton(joy, 6, 2); // RB and B
    // outtakeL3 = new DoubleButton(joy, 6, 4); // RB and Y
    // level1 = new SingleButton(joy, 1, 6);
    // level2 = new SingleButton(joy, 2, 6);
    // level3 = new SingleButton(joy, 4, 6);
    // goHome = new DoubleButton(joy, 6, 3); // RB and X
    manual = new DoubleButton(joy, 1, 2);

    b6.whenActive(new ResetIntakeCommand());
    b12.whileHeld(new ElevatorCommand(0.)); // reset to very bottom
    // Y.whenPressed(new ElevatorCommand(RobotMap.kLevel3)); // Move elevator to 3rd level
    // B.whenPressed(new ElevatorCommand(RobotMap.kLevel2)); // Move elevator to 2nd level
    // A.whenPressed(new ElevatorCommand(RobotMap.kLevel1)); // Move elevator to 1st level
    b11.whenActive(new ElevatorCommand(RobotMap.kLevel1));
    b9.whenActive(new ElevatorCommand(RobotMap.kLevel2));
    b7.whenActive(new ElevatorCommand(RobotMap.kLevel3));
    manual.whileActive(new ElevatorManualCommand()); // Manually control elevator with right stick
    b2.whenPressed(new IntakeCommand()); // Rotate the intake mechanism 0.5 rotations
    b5.whenPressed(new StopMovingCommand()); // Stops the drivetrain motors
    // Start.whenPressed(new IntakeCommand(3072));
    // Back.whenPressed(new IntakeCommand(2048));
    b1.whenPressed(new ThrottleCommand()); /* Toggles between high and low speeds,
                                              determined by kHighThrottle and kLowThrottle*/
    // outtakeL1.whenActive(new OuttakeGroup(RobotMap.kLevel1)); /* Drives the robot forward to put hatch panel on loading zone,
    //                                                           resets the intake to release the hatch, and then backs away */
    // outtakeL2.whenActive(new OuttakeGroup(RobotMap.kLevel2));
    // outtakeL3.whenActive(new OuttakeGroup(RobotMap.kLevel3));
    // RS.whenPressed(new IntakeGroup());
  }
  
  // Method for testing if button with number bNum has been pressed, returns true if button is pressed
  public Boolean getButton(int bNum) {
    return joy.getRawButton(bNum);
  }

  /* Takes the inputs of the two triggers to turn the robot, or does nothing when neither is pressed. 
  The left trigger is inverted so that the robot turns counter-clockwise. The right trigger turns the
  robot clockwise*/
  public double DriveTwist() {
    return joy.getZ();
  }

  // returns a double for the left joystick's y-axis. Positive = up, negative = down
  public Double DriveY() {
    return joy.getY();
  }

  // returns a double for the left joystick's x-axis. Positive = right, negative = left
  public Double DriveX() {
    return joy.getX();
  }
}