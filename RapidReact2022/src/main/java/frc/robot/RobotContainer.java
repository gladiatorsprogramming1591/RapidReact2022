package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.*;
import frc.robot.commands.DriveTrainCommands.FastDrive;
import frc.robot.commands.DriveTrainCommands.SlowDrive;
import frc.robot.subsystems.DriveTrainC;

public class RobotContainer {
    public final static Joystick m_driverStick = new Joystick(Constants.kDriverControllerPort);
    public final static DriveTrainC m_driveTrain = new DriveTrainC(m_driverStick);

    RobotContainer() {
            System.out.println("RobotContainer c'tor");
        SmartDashboard.putString("Hello World", "I am Robot");
        new JoystickButton(m_driverStick, JoystickButtonConstants.kX)
        .whileHeld(new XButtonTest()); 
        new JoystickButton(m_driverStick, JoystickButtonConstants.kB)
        .whenPressed(new MotorTest(m_driverStick)); 

            // ---DRIVE TRAIN--- 
     new JoystickButton(m_driverStick, JoystickButtonConstants.kR1)
       .whenPressed(new SlowDrive(m_driveTrain));
     new JoystickButton(m_driverStick, JoystickButtonConstants.kR2)
       .whenPressed(new FastDrive(m_driveTrain));
    }
}
