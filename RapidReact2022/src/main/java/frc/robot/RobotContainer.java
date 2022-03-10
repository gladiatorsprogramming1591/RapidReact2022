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
import frc.robot.commands.AutoCommands.PickupShootLowGoal;
import frc.robot.commands.DriveTrainCommands.FastDrive;
import frc.robot.commands.DriveTrainCommands.PIDDriveToTargetVision;
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
      new JoystickButton(testStick, JoystickButtonConstants.kL3)
      .whenPressed(new ServoForward(m_latchServos));
      new JoystickButton(testStick, JoystickButtonConstants.kR3)
      .whenPressed(new ServoBackward(m_latchServos));
    }
  }

  public Command getDefaultAutonomousCommand() {
    return new PickupShootHighGoal(m_shooterSubsystem, m_hopperSubsystem, m_driveTrain, m_IntakeSubsystem);
  }

  public Command getCustomAutonomousCommand() {
    return new PickupShootLowGoal(m_shooterSubsystem, m_hopperSubsystem, m_driveTrain, m_IntakeSubsystem);
  }}
//Notes to self: figure out how to simulate motors

/*
-SHOOT ONE OPTION 0 --In Progress
-REGERGITATE ALL OPTION -- LEFT POV (POSSIBLY TESTSTICK)
-FULL CHUCK OPTION
-"NUDGE" OPTION FOR INTAKE
-ORGANIZE SHUFFLEBOARD
-ADD SHOOTING MODES TO S.B. (POS BOOLEANS?)

BUTTON MAPPING:
  MANIPULATOR STICK:
    SHOOTER
      (for sure)
      -R1- HIGH GOAL SPEED
      -R2- LOW GOAL SPEED
      (may change)
      -L1- BLEH SPEED
      -L2- STOP SHOOTER

(Need to figure out mapping for "regergitate all" to fit along with climb buttons)

RESEARCH:
-Look into Thunderclap's drive ecoder; possible copy and paste
-Look into DeepSpace's camera server group to reference for including Microsoft Camera



NOTES:: -When ball is at the top of the hopper between the "stopper" and hopper belts,
hopper does not advance (too little power?). (REMAINS AN ISSUE)
-Justin brought up that belts of intake seem to skip when  when manually turned ontop of ball,
have not tested if the same ran by the motor.

PENDING TEST:
*/









/*
COMPLETED:
3/3/22
-UP ON D-PAD --ADVANCE HOPPER
-DOWN D-PAD REVERSE HOPPER (JUST HOPPER)
-ALL CLIMB POSTITIONS TO TESTSTICK
-kA: ENABLE SLOW     kA:ENABLE FAST
-FIX TURNIG LEFT TOO FAST (SENSITIVE)*** --Bad Joystick

3/4/22
-Program new motor controllers (CAN ID 10 AND 5)
-Intake motor controller current limit: set to 50 --set all to 50
-Programmed 2nd intake motor
-Found 25ft Ethernet Cable
-FIX SLOW MODE (and simplified) --Used .toggleWhenPressed

3/5/22
-PROGRAM SERVOS CONTROLLING CLIMB HOOKS: PWM 9 LEFT, PWM 8 RIGHT
-MINIMUM SPEED SHOOTER OPTION
*/