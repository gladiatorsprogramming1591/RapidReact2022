package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

public final class Constants {
    // Controller Ports
    public final static int kDriverControllerPort = 0;
    public final static int kManipulatorControllerPort = 1;

    // CAN channels
    public final static int kMotorChannel = 0;    
    public final static int kCbotFrontLeftMotorCANID = 1;
    public final static int kCbotRearLeftMotorCANID = 2;
    public final static int kCbotFrontRightMotorCANID = 3;
    public final static int kCbotRearRightMotorCANID = 4;
    public static final int kShooterMotorPort = 6;
    public static final int kIntakeChannel = 5; //tbd
    
        // PIDDriveEncoder
    public final static double kPIDDriveRotP = 0.5;
    public final static double kPIDDriveRotI = 0;
    public final static double kPIDDriveRotD = 0.1;
    public final static double kPIDDriveP = 0.03;
    public final static double kPIDDriveI = 0;
    public final static double kPIDDriveD = 0;
    public final static double kPIDDrivePosTol = 0.5;
    public final static double kPIDDriveVelTol = 10;
    public final static double kPIDAnglePosTol = 0.15;
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
    public final static double kSlowDriveScalar = 0.5;  // with squared inputs, sets max speed to 64%
    public final static double kFastDriveScalar = 0.4;  // with squared inputs, sets max speed to 25%
    public final static double kSlowTurnScalar = 0.5;
    public final static double kFastTurnScalar = 0.8;
    public final static double kDriveRampRate = 0.1;
    public final static double kTargetHeight = 9.0;
    
    //Encoder 
    public final static int kEncoderResolution = 42;
    
    public final static double kShooterMotorSpeed = -0.3;
    public final static double kShooterGearRatio = 7/6;
    public final static double kNeoMaxSpeed = 5676;
    public final static double kShooterMotorSpeedRPM = kShooterMotorSpeed*kNeoMaxSpeed*kShooterGearRatio;
    public static final PneumaticsModuleType kPCM_CANID = null;
    public static final int kArmSolenoidForwardChannel = 0; //tbd
    public static final int kArmSolenoidReverseChannel = 1; //tbd
    public static final double kIntakeForwardSpeed = .5;
    public static final double kIntakeReverseSpeed = -.5;
    public static final double kIntakeRampRate = 0.1;
    public static final double kMinRotations = 0.2;


}
