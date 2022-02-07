package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class DriveTrainC extends DriveTrain {

  static CANSparkMax m_frontLeftMotor;
  static CANSparkMax m_frontRightMotor;
  static CANSparkMax m_rearLeftMotor;
  static CANSparkMax m_rearRightMotor;
  static CANEncoder m_leftEncoder;
  static CANEncoder m_rightEncoder;

  public DriveTrainC(Joystick m_driverJoystick) {
    super(
      new DifferentialDrive(
        new SpeedControllerGroup(
          m_frontLeftMotor = new CANSparkMax(Constants.kCbotFrontLeftMotorCANID, MotorType.kBrushless),
          m_rearLeftMotor = new CANSparkMax(Constants.kCbotRearLeftMotorCANID, MotorType.kBrushless)
        ), 
        new SpeedControllerGroup(
          m_frontRightMotor = new CANSparkMax(Constants.kCbotFrontRightMotorCANID, MotorType.kBrushless), 
          m_rearRightMotor = new CANSparkMax(Constants.kCbotRearRightMotorCANID, MotorType.kBrushless)
        )
      ),
      m_driverJoystick
    );
    
    m_frontLeftMotor.setOpenLoopRampRate(Constants.kDriveRampRate);
    m_rearLeftMotor.setOpenLoopRampRate(Constants.kDriveRampRate);
    m_frontRightMotor.setOpenLoopRampRate(Constants.kDriveRampRate);
    m_rearRightMotor.setOpenLoopRampRate(Constants.kDriveRampRate);

    m_leftEncoder = m_frontLeftMotor.getEncoder();
    m_rightEncoder = m_frontRightMotor.getEncoder();
  }

  @Override
  public void setBrakeMode() {
    m_frontLeftMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_frontRightMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_rearLeftMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_rearRightMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
  }

  @Override
  public void setCoastMode() {
    System.out.println("Calling DriveTrainP.setCoastMode");
    m_frontLeftMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    m_frontRightMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    m_rearLeftMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    m_rearRightMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
  }

  @Override
  public double getLeftEncPos() {
    return m_leftEncoder.getPosition();  
  }

  @Override
  public double getRightEncPos() {
    return m_rightEncoder.getPosition();  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // This should be used for diagnostics and not used to run motors since this is used
    // in all modes, not just teleop
    SmartDashboard.putNumber("Left Drive Enc", m_leftEncoder.getPosition());
    SmartDashboard.putNumber("Right Drive Enc", m_rightEncoder.getPosition());
    SmartDashboard.putNumber("Left Drive Vel", m_leftEncoder.getVelocity());
    SmartDashboard.putNumber("Right Drive Vel", m_rightEncoder.getVelocity());
  }
}