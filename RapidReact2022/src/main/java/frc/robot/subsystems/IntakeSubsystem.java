package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


/**
 * The elevator subsystem
 */
public class IntakeSubsystem extends SubsystemBase {

    private final CANSparkMax m_intakeLeftMotor;
    private final CANSparkMax m_intakeRightMotor;
    private MotorControllerGroup m_MCG;

    public IntakeSubsystem() {
        if (RobotContainer.isCBot){
            m_MCG = new MotorControllerGroup(
                m_intakeLeftMotor = new CANSparkMax(Constants.kIntakeLeftChannel, MotorType.kBrushless),
                m_intakeRightMotor = new CANSparkMax(Constants.kIntakeRightChannel, MotorType.kBrushless)
            );
            m_intakeRightMotor.setInverted(true);
            m_intakeRightMotor.setOpenLoopRampRate(Constants.kIntakeRampRate);
        } else{
            m_intakeLeftMotor = new CANSparkMax(Constants.kIntakeLeftChannel, MotorType.kBrushless);
            m_intakeRightMotor = null;
        }

        m_intakeLeftMotor.setOpenLoopRampRate(Constants.kIntakeRampRate);
        m_intakeRightMotor.enableVoltageCompensation(12);
        m_intakeLeftMotor.enableVoltageCompensation(12);
    }
    
    public void intakeOn() {
        if (RobotContainer.isCBot){
            m_MCG.set(Constants.kIntakeForwardSpeed);
        } else {
            m_intakeLeftMotor.set(Constants.kIntakeForwardSpeed);
        }
    }

    public void intakeReverse() {
        if (RobotContainer.isCBot){
            m_MCG.set(Constants.kIntakeReverseSpeed);
        } else {
            m_intakeLeftMotor.set(Constants.kIntakeReverseSpeed);
        }
    }

    public void intakeOff() {
        if (RobotContainer.isCBot){
        m_MCG.set(0);
        } else {
            m_intakeLeftMotor.set(0);
        }
    }

}