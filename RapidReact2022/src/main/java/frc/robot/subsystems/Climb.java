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

public class Climb {

  private static CANSparkMax m_LeftMotor;
  private static CANSparkMax m_RightMotor;
  private static RelativeEncoder m_leftEncoder;
  private static RelativeEncoder m_rightEncoder;
  private MotorControllerGroup m_MCG; //"Motor Controller Group"
  private Joystick m_stick;

  public Climb(Joystick stick) {
      m_MCG = new MotorControllerGroup(
          m_LeftMotor = new CANSparkMax(Constants.kLeftClimberChannel, MotorType.kBrushless),
          m_RightMotor = new CANSparkMax(Constants.kRightClimberChannel, MotorType.kBrushless)
        );
      m_stick = stick;

    m_LeftMotor.setOpenLoopRampRate(Constants.kDriveRampRate);
    m_RightMotor.setOpenLoopRampRate(Constants.kDriveRampRate);

    m_RightMotor.setInverted(true);
    
    m_leftEncoder = m_LeftMotor.getEncoder();
    m_rightEncoder = m_RightMotor.getEncoder();
  }
  
  public void setBrakeMode() {
    m_LeftMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_RightMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
  }

  public void setCoastMode() {
    System.out.println("Calling DriveTrainC.setCoastMode");
    m_LeftMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    m_RightMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
  }

  public double getLeftEncPos() {
    return m_leftEncoder.getPosition();  
  }

  public double getRightEncPos() {
    return m_rightEncoder.getPosition();  
  }

  public boolean isStopped() {
    if (m_rightEncoder.getVelocity() == 0 && m_leftEncoder.getVelocity() == 0) {
      return true;
    } else {
      return false;
    }
  }

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
