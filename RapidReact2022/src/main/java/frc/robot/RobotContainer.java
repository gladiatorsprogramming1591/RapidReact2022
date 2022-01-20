package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Commands.XButtonTest;

public class RobotContainer {
    public final static Joystick m_driverStick = new Joystick(Constants.kDriverControllerPort);

    RobotContainer() {
        new JoystickButton(m_driverStick, JoystickButtonConstants.kX)
        .whileHeld(new XButtonTest());  
    }
}
