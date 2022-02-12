package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

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

    private final CANSparkMax m_intakeMotor;

    public IntakeSubsystem() {
        m_intakeMotor = new CANSparkMax(Constants.kIntakeChannel, MotorType.kBrushless);

        m_intakeMotor.setOpenLoopRampRate(Constants.kIntakeRampRate);
    }
    public void intakeOn() {
        m_intakeMotor.set(Constants.kIntakeForwardSpeed);
    }

    public void intakeReverse() {
        m_intakeMotor.set(Constants.kIntakeReverseSpeed);
    }

    public void intakeOff() {
        m_intakeMotor.set(0);
    }

}