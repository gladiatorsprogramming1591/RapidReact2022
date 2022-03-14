package frc.robot;

import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.XButtonTest;
import frc.robot.commands.AutoCommands.PickupShootHighGoal;
import frc.robot.commands.AutoCommands.ShootOneBallAuto;
import frc.robot.commands.AutoCommands.ShootAutoLowGoal;
import frc.robot.commands.AutoCommands.ShootAutoSpit;
import frc.robot.commands.AutoCommands.WallAuto;
import frc.robot.commands.DriveTrainCommands.FastDrive;
import frc.robot.commands.DriveTrainCommands.PIDDriveToTargetVision;
import frc.robot.commands.DriveTrainCommands.PushDrive;
import frc.robot.commands.DriveTrainCommands.SlowDrive;
import frc.robot.commands.HopperCommands.HopperAdvance;
import frc.robot.commands.HopperCommands.HopperOff;
import frc.robot.commands.HopperCommands.HopperOn;
// import frc.robot.commands.HopperCommands.HopperOn;
import frc.robot.commands.HopperCommands.HopperReverse;
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
  public final static boolean XboxController = false;
  public final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  public final IntakeSubsystem m_IntakeSubsystem = new IntakeSubsystem();
  public final HopperSubsystem m_hopperSubsystem = new HopperSubsystem();
  public Joystick testStick = new Joystick(Constants.kManipulatorControllerPort);
  public final static Joystick m_driverStick = new Joystick(Constants.kDriverControllerPort);
  public final static Joystick m_XboxDriverStick = new Joystick(Constants.kXboxControllerPort);
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
    new JoystickButton(m_driverStick, JoystickButtonConstants.kR3)
      .toggleWhenPressed(new PushDrive(m_driveTrain));
      

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
    .whenPressed(new HopperReverse(m_hopperSubsystem, Constants.kHopperReverseDist));



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


    if (isCBot) {
      
      //Climb
      new JoystickButton(testStick, JoystickButtonConstants.kA) //Only needs climbWithStick and Nudge L and R
      .whenPressed(new ClimbWithStick(testStick, m_climb));
      new JoystickButton(testStick, JoystickButtonConstants.kX)
      .whenPressed(new ClimbNudgeLeftDown(m_climb)
      .withTimeout(Constants.kNudgeTime));
      new JoystickButton(testStick, JoystickButtonConstants.kB)
      .whenPressed(new ClimbNudgeRightDown(m_climb)
      .withTimeout(Constants.kNudgeTime));
      new JoystickButton(testStick, JoystickButtonConstants.kY)
      .whenPressed(new ZeroClimbPosition(m_climb));

      // new POVButton(testStick, JoystickButtonConstants.kPOV_UP)
      // .whenPressed(new ClimbToPosition(m_climb, Constants.kClimbTopPos));
      // new POVButton(testStick, JoystickButtonConstants.kPOV_RIGHT)
      // .whenPressed(new ClimbToPosition(m_climb, Constants.kClimbTwoInches));
      // new POVButton(testStick, JoystickButtonConstants.kPOV_DOWN)
      // .whenPressed(new ClimbToPosition(m_climb, 0));
      // new JoystickButton(testStick, JoystickButtonConstants.kStart)
      // .whenPressed(new ClimbToPosition(m_climb, Constants.kClimbTest));

      //Latches
      // new JoystickButton(testStick, JoystickButtonConstants.kL3)
      // .whenPressed(new ServoForward(m_latchServos));
      // new JoystickButton(testStick, JoystickButtonConstants.kR3)
      // .whenPressed(new ServoBackward(m_latchServos));
    }



    // if (XboxController) {
    //   new JoystickButton(m_XboxDriverStick, XboxButtonConstants.kL3)
    //   .toggleWhenPressed(new SlowDrive(m_driveTrain, m_XboxDriverStick));
    // new JoystickButton(m_XboxDriverStick, XboxButtonConstants.kR3)
    //   .toggleWhenPressed(new PushDrive(m_driveTrain));

    //   new JoystickButton(m_XboxDriverStick, XboxButtonConstants.kL2) //Trigger
    //   .whenPressed((Command) new IntakeOn(m_IntakeSubsystem)); 
    //   new JoystickButton(m_XboxDriverStick, XboxButtonConstants.kL1)
    //   .whenPressed((Command) new IntakeReverse(m_IntakeSubsystem));
    //   new JoystickButton(m_XboxDriverStick, XboxButtonConstants.kShare)  
    //   .whenPressed((Command) new IntakeOff(m_IntakeSubsystem));

    //   new JoystickButton(m_XboxDriverStick, XboxButtonConstants.kY)
    //   .whenPressed(new PIDDriveToTargetVision(m_driveTrain)); 

    //   new POVButton(m_XboxDriverStick, XboxButtonConstants.kPOV_UP) ____________________//Not finished
    //   .whenPressed(new HopperOn(m_hopperSubsystem, Constants.kHopperAdvanceDist));
    //   new POVButton(m_XboxDriverStick, XboxButtonConstants.kPOV_LEFT)
    //   .whenPressed(new HopperOff(m_hopperSubsystem));
    //   new POVButton(m_XboxDriverStick, XboxButtonConstants.kPOV_DOWN)
    //   .whenPressed(new HopperReverse(m_hopperSubsystem, Constants.kHopperReverseDist));
    // }
  }









  public Command getHighGoalAutonomousCommand() {
    return new PickupShootHighGoal(m_shooterSubsystem, m_hopperSubsystem, m_driveTrain, m_IntakeSubsystem);
  }

  public Command getLowGoalAutonomousCommand() {
    return new ShootOneBallAuto(m_shooterSubsystem, m_hopperSubsystem, m_driveTrain, m_IntakeSubsystem);
  }
  //Why equal to OneBall if Low Goal??????????????????????????????????????????????????????????????????????

  public Command getWallAutonomousCommand() {
    return new WallAuto(m_shooterSubsystem, m_hopperSubsystem, m_driveTrain, m_IntakeSubsystem);
  }

  public Command getOneBallAutoCommand() {
    return new ShootOneBallAuto(m_shooterSubsystem, m_hopperSubsystem, m_driveTrain, m_IntakeSubsystem);
  }
  
  public Command getSpitBallAutoCommand() {
    return new ShootAutoSpit(m_shooterSubsystem, m_hopperSubsystem, m_IntakeSubsystem);
  }
}