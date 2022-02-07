package frc.robot;

public final class Constants {
    // Controller Ports
    public final static int kDriverControllerPort = 0;
    public final static int kManipulatorControllerPort = 1;

    // CAN channels
    public final static int kMotorChannel = 0; 
    public final static int kCbotFrontLeftMotorCANID = 1;
    public final static int kCbotRearLeftMotorCANID = 2;
    public final static int kCbotRearRightMotorCANID = 3;
    public final static int kCbotFrontRightMotorCANID = 4;
    
        // PIDDriveEncoder
    public final static double kPIDDriveRotP = 0.015;
    public final static double kPIDDriveRotI = 0;
    public final static double kPIDDriveRotD = 0;
    public final static double kPIDDriveP = 0.03;
    public final static double kPIDDriveI = 0;
    public final static double kPIDDriveD = 0;
    public final static double kPIDDrivePosTol = 0.5;
    public final static double kPIDDriveVelTol = 10;
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
    public final static double kSlowDriveScalar = 0.6;  // with squared inputs, sets max speed to 36%
    public final static double kFastDriveScalar = 1.0;  // with squared inputs, sets max speed to 100%
    public final static double kDriveRampRate = 0.1;
    
    //Encoder 
    public final static int kEncoderResolution = 42;
    
}
