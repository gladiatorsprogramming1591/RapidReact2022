package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveTrainCommands.FastDrive;
import frc.robot.commands.DriveTrainCommands.SlowDrive;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.commands.IntakeCommands.IntakeOn;
import frc.robot.commands.ShooterCommands.ShooterOff;
import frc.robot.commands.ShooterCommands.ShooterOn;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class RobotContainer {
    private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
    private final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();
    public final static Joystick m_driverStick = new Joystick(Constants.kDriverControllerPort);
    public final static DriveTrainC m_driveTrain = new DriveTrainC(m_driverStick);


    RobotContainer() { 
            System.out.println("RobotContainer c'tor");
        SmartDashboard.putString("Hello World", "I am Robot");
        new JoystickButton(m_driverStick, JoystickButtonConstants.kX)
        .whileHeld(new XButtonTest()); 

            // ---DRIVE TRAIN--- 
     new JoystickButton(m_driverStick, JoystickButtonConstants.kR1)
       .whenPressed(new SlowDrive(m_driveTrain));
     new JoystickButton(m_driverStick, JoystickButtonConstants.kR2)
       .whenPressed(new FastDrive(m_driveTrain));
        new JoystickButton(m_driverStick, JoystickButtonConstants.kX)
        .whenPressed((Command) new IntakeOn(m_IntakeSubsystem)); 

        //Shooter
        new JoystickButton(m_driverStick, JoystickButtonConstants.kL1)
      .whenPressed(new ShooterOn(m_shooterSubsystem));
      new JoystickButton(m_driverStick, JoystickButtonConstants.kL2)
      .whenPressed(new ShooterOff(m_shooterSubsystem));
    }
}
