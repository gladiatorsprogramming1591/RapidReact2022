package frc.robot;

import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.XButtonTest;
import frc.robot.commands.DriveTrainCommands.FastDrive;
import frc.robot.commands.DriveTrainCommands.PIDDriveToTarget;
import frc.robot.commands.DriveTrainCommands.SlowDrive;
import frc.robot.commands.HopperCommands.HopperAdvance;
import frc.robot.commands.HopperCommands.HopperOff;
import frc.robot.commands.HopperCommands.HopperOn;
import frc.robot.commands.HopperCommands.HopperReverse;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.commands.IntakeCommands.IntakeOff;
import frc.robot.commands.IntakeCommands.IntakeOn;
import frc.robot.commands.IntakeCommands.IntakeReverse;
import frc.robot.commands.ShooterCommands.ShooterOff;
import frc.robot.commands.ShooterCommands.ShooterOn;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.Climb;
import frc.robot.commands.ClimbCommands.*;

public class RobotContainer {
    private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
    private final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();
    private HopperSubsystem m_hopperSubsystem = new HopperSubsystem();
    private Joystick testStick = new Joystick(Constants.kManipulatorControllerPort);
    public final static Joystick m_driverStick = new Joystick(Constants.kDriverControllerPort);
    public final static DriveTrainC m_driveTrain = new DriveTrainC(m_driverStick);
    public final static Climb m_climb = new Climb();


    RobotContainer() { 
            System.out.println("RobotContainer c'tor");
        SmartDashboard.putString("Hello World", "I am Robot");
        //new JoystickButton(m_driverStick, JoystickButtonConstants.kX)
        //.whileHeld(new XButtonTest()); 


        // DRIVE STICK

            // ---DRIVE TRAIN--- 
     new JoystickButton(m_driverStick, JoystickButtonConstants.kA)
       .whenPressed(new SlowDrive(m_driveTrain));
     new JoystickButton(m_driverStick, JoystickButtonConstants.kX)
       .whenPressed(new FastDrive(m_driveTrain));

       //Intake
        new JoystickButton(m_driverStick, JoystickButtonConstants.kL2)
        .whenPressed((Command) new IntakeOn(m_IntakeSubsystem)); 
        new JoystickButton(m_driverStick, JoystickButtonConstants.kL1)
        .whenPressed((Command) new IntakeReverse(m_IntakeSubsystem));
        new POVButton(m_driverStick, JoystickButtonConstants.kPOV_LEFT)  //Up (0=Up, 90=Right, 180=Down, 270=Left)
        .whenPressed((Command) new IntakeOff(m_IntakeSubsystem));

        //Shooter
        new JoystickButton(m_driverStick, JoystickButtonConstants.kR2)
      .whenPressed(new ShooterOn(m_shooterSubsystem));
      new JoystickButton(m_driverStick, JoystickButtonConstants.kR1)
      .whenPressed(new ShooterOff(m_shooterSubsystem));

        //PIDDriveToTarget
        new JoystickButton(m_driverStick, JoystickButtonConstants.kY)
        .whenPressed(new PIDDriveToTarget(m_driveTrain)); 


      // TEST STICK

      //Hopper
      new JoystickButton(testStick, JoystickButtonConstants.kR1)//Gio:UpOnD-Pad
      .whenPressed(new HopperAdvance(m_hopperSubsystem)
      .withTimeout(1.0));
      new JoystickButton(testStick, JoystickButtonConstants.kY)//Gio:kBforEasyCancel
      .whenPressed(new HopperOff(m_hopperSubsystem));
      new JoystickButton(testStick, JoystickButtonConstants.kR2)//Gio:DownOnD-Pad
      .whenPressed(new HopperReverse(m_hopperSubsystem));

      //Climb
      new JoystickButton(testStick, JoystickButtonConstants.kA)
      .whenPressed(new ClimbWithStick(testStick, m_climb));
      new JoystickButton(testStick, JoystickButtonConstants.kB)//NotInterupting_______________________________
      .whenPressed(new ServoForward(m_climb));
      new POVButton(testStick, JoystickButtonConstants.kPOV_UP)
      .whenPressed(new ClimbToPosition(m_climb, Constants.kClimbTopPos));
      new POVButton(testStick, JoystickButtonConstants.kPOV_RIGHT)
      .whenPressed(new ClimbToPosition(m_climb, Constants.kClimbTwoInches));
      new POVButton(testStick, JoystickButtonConstants.kPOV_DOWN)
      .whenPressed(new ClimbToPosition(m_climb, 0));
      new POVButton(testStick, JoystickButtonConstants.kStart)
      .whenPressed(new ClimbToPosition(m_climb, Constants.kClimbTest)); 
      new JoystickButton(testStick, JoystickButtonConstants.kX)
      .whenPressed(new ClimbNudgeLeftDown(m_climb)
      .withTimeout(Constants.kNudgeTime));
      new JoystickButton(testStick, JoystickButtonConstants.kB)
      .whenPressed(new ClimbNudgeRightDown(m_climb)
      .withTimeout(Constants.kNudgeTime));
    }
}
