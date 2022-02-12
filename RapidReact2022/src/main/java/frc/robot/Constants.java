package frc.robot;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

public final class Constants {
    // Controller Ports
    public final static int kDriverControllerPort = 0;
    public final static int kManipulatorControllerPort = 1;

    // CAN channels
    public final static int kMotorChannel = 0;    
    public static final int kShooterMotorPort = 6;

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
    public static final int kIntakeChannel = 5; //tbd
}
