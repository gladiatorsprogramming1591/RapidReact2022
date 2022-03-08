package frc.robot;

import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.XButtonTest;
import frc.robot.commands.AutoCommands.MainAutoCommand;
import frc.robot.commands.DriveTrainCommands.FastDrive;
import frc.robot.commands.DriveTrainCommands.PIDDriveToTargetVision;
import frc.robot.commands.DriveTrainCommands.SlowDrive;
import frc.robot.commands.HopperCommands.HopperAdvance;
import frc.robot.commands.HopperCommands.HopperOff;
import frc.robot.commands.HopperCommands.HopperOn;
// import frc.robot.commands.HopperCommands.HopperOn;
import frc.robot.commands.HopperCommands.HopperReverse;
import frc.robot.commands.HopperCommands.Regurgitate;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.commands.IntakeCommands.IntakeOff;
import frc.robot.commands.IntakeCommands.IntakeOn;
import frc.robot.commands.IntakeCommands.IntakeReverse;
import frc.robot.commands.ShooterCommands.ShooterHighGoal;
import frc.robot.commands.ShooterCommands.ShooterLowGoal;
import frc.robot.commands.ShooterCommands.ShooterOff;
import frc.robot.commands.ShooterCommands.ShooterSpit;
// import frc.robot.commands.ShooterCommands.ShooterOn;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.LatchServos;
import frc.robot.commands.ClimbCommands.*;

public class RobotContainer {
  public final static boolean isCBot = true;
  public final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  public final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();
  public final HopperSubsystem m_hopperSubsystem = new HopperSubsystem();
  public Joystick testStick = new Joystick(Constants.kManipulatorControllerPort);
  public final static Joystick m_driverStick = new Joystick(Constants.kDriverControllerPort);
  public final DriveTrainC m_driveTrain = new DriveTrainC(m_driverStick);
  public static Climb m_climb;
  public static LatchServos m_latchServos;

  RobotContainer() { 
    System.out.println("RobotContainer c'tor");
    SmartDashboard.putString("Hello World", "I am Robot");
    //new JoystickButton(m_driverStick, JoystickButtonConstants.kX)
    //.whileHeld(new XButtonTest()); 

    m_latchServos = new LatchServos();
    if (isCBot) {
      m_climb = new Climb();
    }
  

    // DRIVE STICK

    // ---DRIVE TRAIN--- 
    new JoystickButton(m_driverStick, JoystickButtonConstants.kL3)
      .toggleWhenPressed(new SlowDrive(m_driveTrain, m_driverStick));
      

      //Intake
      new JoystickButton(m_driverStick, JoystickButtonConstants.kL2)
      .whenPressed((Command) new IntakeOn(m_IntakeSubsystem)); 
      new JoystickButton(m_driverStick, JoystickButtonConstants.kL1)
      .whenPressed((Command) new IntakeReverse(m_IntakeSubsystem));
      new JoystickButton(m_driverStick, JoystickButtonConstants.kBack)  
      .whenPressed((Command) new IntakeOff(m_IntakeSubsystem));

      //PIDDriveToTarget
      new JoystickButton(m_driverStick, JoystickButtonConstants.kY)
      .whenPressed(new PIDDriveToTargetVision(m_driveTrain)); 

      //Hopper
    new POVButton(m_driverStick, JoystickButtonConstants.kPOV_UP)
    .whenPressed(new HopperOn(m_hopperSubsystem, Constants.kHopperAdvanceDist));
    new POVButton(m_driverStick, JoystickButtonConstants.kPOV_LEFT)//Gio: kB for Easy Cancel
    .whenPressed(new HopperOff(m_hopperSubsystem));
    new POVButton(m_driverStick, JoystickButtonConstants.kPOV_DOWN)
    .whenPressed(new HopperReverse(m_hopperSubsystem));



      // TEST STICK

     //Shooter (add 4 new buttons for forward, reverse, stop, and spit)

     new JoystickButton(testStick, JoystickButtonConstants.kR2)
     .whenPressed(new ShooterLowGoal(m_shooterSubsystem));
     new JoystickButton(testStick, JoystickButtonConstants.kR1)
     .whenPressed(new ShooterHighGoal(m_shooterSubsystem));
     new JoystickButton(testStick, JoystickButtonConstants.kL2)
     .whenPressed(new ShooterOff(m_shooterSubsystem));
     new JoystickButton(testStick, JoystickButtonConstants.kL1)
     .whenPressed(new ShooterSpit(m_shooterSubsystem));

    //  new MultiToggle(new JoystickButton(testStick, JoystickButtonConstants.kR2), 1)
    //  .whileHeld(new ShooterOn(m_shooterSubsystem,  Constants.kLowGoalSpeed));
    //  new MultiToggle(new JoystickButton(testStick, JoystickButtonConstants.kR2), 2)
    //  .whileHeld(new ShooterOn(m_shooterSubsystem,  Constants.kHighGoalSpeed));
    //  new MultiToggle(new JoystickButton(testStick, JoystickButtonConstants.kR2), 3)
    //  .whileHeld(new ShooterOn(m_shooterSubsystem,  Constants.kBlehSpeed));
    //  new MultiToggle(new JoystickButton(testStick, JoystickButtonConstants.kR2), 0)
    //  .whileHeld(new ShooterOff(m_shooterSubsystem));

    //Regurgitate
    new JoystickButton(testStick, JoystickButtonConstants.kPOV_DOWN)
    .whenPressed(new Regurgitate(m_hopperSubsystem, m_IntakeSubsystem));


    if (isCBot) {
      
      //Climb
      new JoystickButton(testStick, JoystickButtonConstants.kA) //Only needs climbWithStick and Nudge L and R
      .whenPressed(new ClimbWithStick(testStick, m_climb));         //--------ASK ETHAN IF NUDGES ARE INVERTED-----------
      new JoystickButton(testStick, JoystickButtonConstants.kX)
      .whenPressed(new ClimbNudgeLeftDown(m_climb)
      .withTimeout(Constants.kNudgeTime));
      new JoystickButton(testStick, JoystickButtonConstants.kB)
      .whenPressed(new ClimbNudgeRightDown(m_climb)
      .withTimeout(Constants.kNudgeTime));

      // new POVButton(testStick, JoystickButtonConstants.kPOV_UP)
      // .whenPressed(new ClimbToPosition(m_climb, Constants.kClimbTopPos));
      // new POVButton(testStick, JoystickButtonConstants.kPOV_RIGHT)
      // .whenPressed(new ClimbToPosition(m_climb, Constants.kClimbTwoInches));
      // new POVButton(testStick, JoystickButtonConstants.kPOV_DOWN)
      // .whenPressed(new ClimbToPosition(m_climb, 0));
      // new JoystickButton(testStick, JoystickButtonConstants.kStart)
      // .whenPressed(new ClimbToPosition(m_climb, Constants.kClimbTest));

      //Latches
      new JoystickButton(testStick, JoystickButtonConstants.kL3)
      .whenPressed(new ServoForward(m_latchServos));
      new JoystickButton(testStick, JoystickButtonConstants.kR3)
      .whenPressed(new ServoBackward(m_latchServos));
    }
  }

  public Command getAutonomousCommand() {
    return new MainAutoCommand(m_shooterSubsystem, m_hopperSubsystem, m_driveTrain, m_IntakeSubsystem);
  }
}