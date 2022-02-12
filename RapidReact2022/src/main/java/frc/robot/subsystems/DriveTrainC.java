// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.kauailabs.navx.frc.AHRS;

public class DriveTrainC extends DriveTrain {

  private static CANSparkMax m_frontLeftMotor;
  private static CANSparkMax m_frontRightMotor;
  private static CANSparkMax m_rearLeftMotor;
  private static CANSparkMax m_rearRightMotor;
  private static RelativeEncoder m_leftEncoder;
  private static RelativeEncoder m_rightEncoder;

  private AHRS m_NavX;

  public DriveTrainC(Joystick m_driverJoystick) {
    super(
      new DifferentialDrive(
        new MotorControllerGroup(
          m_frontLeftMotor = new CANSparkMax(Constants.kCbotFrontLeftMotorCANID, MotorType.kBrushless),
          m_rearLeftMotor = new CANSparkMax(Constants.kCbotRearLeftMotorCANID, MotorType.kBrushless)
        ), 
        new MotorControllerGroup(
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

    // In 2021, DifferentialDrive inverted the right side motor controllers. 
    // Starting in 2022, this needs to be done explicitly.
    m_frontRightMotor.setInverted(true);
    m_rearRightMotor.setInverted(true);
    
    m_leftEncoder = m_frontLeftMotor.getEncoder();
    m_rightEncoder = m_frontRightMotor.getEncoder();

    m_NavX = new AHRS(SPI.Port.kMXP);

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

  public double getLeftEncPos() {
    return m_leftEncoder.getPosition();  
  }

  public double getRightEncPos() {
    return m_rightEncoder.getPosition();  
  }

  public double getHeading() {
    return m_NavX.getAngle() + 180;
  }

  public void calibrateNavX() {
    m_NavX.reset();
  }

  public boolean isStopped() {
    if (m_rightEncoder.getVelocity() == 0 && m_leftEncoder.getVelocity() == 0) {
      return true;
    } else {
      return false;
    }
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
    SmartDashboard.putNumber("Heading", getHeading());
    SmartDashboard.putBoolean("Is NavX Calibrating?", m_NavX.isCalibrating());
  }
}
