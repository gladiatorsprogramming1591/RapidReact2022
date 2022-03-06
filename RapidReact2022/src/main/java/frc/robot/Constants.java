package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.subsystems.LatchServos;

public final class Constants {
    // Controller Ports
    public final static int kDriverControllerPort = 0;
    public final static int kManipulatorControllerPort = 1;

    // CAN channels
    public final static int kMotorChannel = 0;    //Not used, for initial testing
    public final static int kCbotFrontLeftMotorCANID = 1;
    public final static int kCbotRearLeftMotorCANID = 2;
    public final static int kCbotFrontRightMotorCANID = 3;
    public final static int kCbotRearRightMotorCANID = 4;
    public final static int kIntakeRightChannel = 10;
    public static final int kShooterChannel = 5; 
    public static final int kIntakeLeftChannel = 7; //works on old robot although labels are swapped
    public static final int kHopperChannel = 6;
    public static final int kLeftClimberChannel = 8;
    public static final int kRightClimberChannel = 9;
    public static final int kPBotHopperChannel = 5;
    public static final int kPBotShooterChannel = 6;

    // pwm channels 
    public static final int kLeftServoChannel = 8;
    public static final int kRightServoChannel = 9;

    // PIDTurnToDegrees
    public final static double kRotationP = 0.011;
    public final static double kRotationI = 0;
    public final static double kRotationD = 0;
    public final static double kAutoRotationError = 2.0; // stop rotating if bot is x degrees away from target
    public final static double kStaticPowerRequirement = 0.35;
    public final static double kAutoRotationVelocityError = 150.0;
    
        // PIDDriveEncoder
    public final static double kPIDDriveRotP = 0.4;
    public final static double kPIDDriveRotI = 0.09;
    public final static double kPIDDriveRotD = 0.1;
    public final static double kPIDDriveP = 0.5; //may need to tweak
    public final static double kPIDDriveI = 0.09;
    public final static double kPIDDriveD = 0.1;
    public final static double kPIDDrivePosTol = 0.1;
    public final static double kPIDDriveVelTol = 1.0;
    public final static double kPIDAnglePosTol = 0.1;
    public final static double kPIDAngleVelTol = 1.0;
    public final static double kCustomPower = 0.17;
    public final static double kCustomPowerMin = 0.08;

     // Robot measurements
     public final static double kWheelDiameterInches = 4;
     public final static double kWheelDiameterCM = kWheelDiameterInches * 2.54; // derived from above
     public final static double kWheelCircumferenceInches = kWheelDiameterInches * Math.PI; // derived from above
     public final static double kWheelCircumferenceCM = kWheelDiameterCM * Math.PI; // derived from above
     public final static double kDriveGearRatio = 11.1; // measured as 11.34851783, 10.5 on first day

         //Drive train constants
    public final static boolean kFastSquaredInputs = true;
    public final static boolean kSlowSquaredInputs = true;
    public final static double kSlowDriveScalar = 0.4;  // with squared inputs, sets max speed to 16%
    public final static double kFastDriveScalar = 0.8;  // with squared inputs, sets max speed to 64%
    public final static double kSlowTurnScalar = 0.4;
    public final static double kFastTurnScalar = 0.7;
    public final static double kDriveRampRate = 0.1;
    public final static double kTargetHeight = -1.216;
    
    //Encoder 
    public final static int kEncoderResolution = 42;
    
    public final static double kHighGoalSpeed = -0.6;
    public final static double kLowGoalSpeed = -0.35; //Low goal at start 35% per testing
    public final static double kBlehSpeed = -0.2;
    public final static double kShooterGearRatio = 7/6;
    public final static double kNeoMaxSpeed = 5676;
    public final static double kShooterMotorSpeedRPM = kHighGoalSpeed*kNeoMaxSpeed*kShooterGearRatio;
    public static final PneumaticsModuleType kPCM_CANID = null;
    public static final int kArmSolenoidForwardChannel = 0; //tbd
    public static final int kArmSolenoidReverseChannel = 1; //tbd
    public static final double kIntakeForwardSpeed = 0.5;
    public static final double kIntakeReverseSpeed = -0.4;
    public static final double kIntakeRampRate = 0.1;
    public static final double kMinRotations = 0.2;
    public static final double kHopperRampRate = 0.1;
    public static final double kHopperForwardSpeed = 0.4;
    public static final double kHopperReverseSpeed = -0.4;
    public static final double kHopperReverseDist = -2;

    //Climb
    public static final double kClimberUpwardDist = 10; 
    public static final double kClimberDownwardDist = -10; 
    public static final double kClimberUpwardSpeed = 0.8;
    public static final double kClimberDownwardSpeed = -0.4;
    public static final double kClimbStopDist = 2;
    public static final double kClimbTopPos = 141.2; 
    public static final double kClimbTwoInches = 11.5; 
    public static final double kClimbTest = 40;
    public static final double kClimberNudgeSpeed = -0.2;
    public static final double kNudgeTime = 0.2;

    //Climb Latch Servos
    public static final double kRightServoForwardAngle = 95;
    public static final double kRightServoBackwardAngle = 115.7;
    public static final double kLeftServoForwardAngle = 100;
    public static final double kLeftServoBackwardAngle = 69.3;
}
