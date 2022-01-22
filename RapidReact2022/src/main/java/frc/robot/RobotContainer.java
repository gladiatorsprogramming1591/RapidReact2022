package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Commands.*;

public class RobotContainer {
    public final static Joystick m_driverStick = new Joystick(Constants.kDriverControllerPort);

    RobotContainer() {
            System.out.println("RobotContainer c'tor");
        SmartDashboard.putString("Hello World", "I am Robot");
        new JoystickButton(m_driverStick, JoystickButtonConstants.kX)
        .whileHeld(new XButtonTest()); 
        new JoystickButton(m_driverStick, JoystickButtonConstants.kB)
        .whenPressed(new MotorTest(m_driverStick)); 
    }
}
