package frc.robot.triggers;

// Import buttons and generic HID (generic joystick)
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class SingleButton extends Trigger {
    // Construct variables for the buttons and HID
    private final GenericHID m_joy;
    private final int m_button1;
    private final int m_button2;

    public SingleButton(GenericHID joy, int button1, int button2) {
        // sets the variables that were passed into the constructor to be equal to the variables in the class
        this.m_joy = joy;
        this.m_button1 = button1;
        this.m_button2 = button2;
    }

    // returns true if only the first button is pressed, false otherwise
    @Override
    public boolean get() {
        return m_joy.getRawButton(m_button1) && !m_joy.getRawButton(m_button2);
    }
}