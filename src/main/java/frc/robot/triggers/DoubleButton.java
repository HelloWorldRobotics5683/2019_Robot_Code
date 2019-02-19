package frc.robot.triggers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class DoubleButton extends Trigger {
    private final GenericHID m_joy;
    private final int m_button1;
    private final int m_button2;

    public DoubleButton(GenericHID joy, int button1, int button2) {
        this.m_joy = joy;
        this.m_button1 = button1;
        this.m_button2 = button2;
    }

    @Override
    public boolean get() {
        return m_joy.getRawButton(m_button1) && m_joy.getRawButton(m_button2);
    }
}