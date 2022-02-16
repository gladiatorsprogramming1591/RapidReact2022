/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutonomousCommands.DriveDistance;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.AutonomousCommands.AutoMovementCommand;
import frc.robot.subsystems.DriveTrainC;

/**
 * Drive a certain distance based on an encoder value, autonomously.
 */
public class DriveEncoder extends AutoMovementCommand {
  private final DriveTrainC m_DriveTrain;
  private final double m_motorRotations;
  private double m_startEncoderValue;
  private double m_driveSpeed;
  private double m_targetAngle;
  private PIDController anglePID = new PIDController(Constants.kP_DriveStraight, Constants.kI_DriveStraight, Constants.kD_DriveStraight);

  /**
   * Creates a new DriveEncoder command.
   *
   * @param subsystem The subsystem used by this command.
   * @param wheelRotations The distance in rotations the robot should travel.
   */
  public DriveEncoder(DriveTrainC subsystem, double wheelRotations) {
    super(subsystem);
    m_DriveTrain = subsystem;
    if (wheelRotations > 0) {
      m_motorRotations = (wheelRotations - Constants.kStopDistance) * Constants.kDriveGearRatio;
    } else {
      m_motorRotations = (wheelRotations + Constants.kStopDistance) * Constants.kDriveGearRatio;
    }
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("------------ DriveEncoder ---------------");
    m_startEncoderValue = m_DriveTrain.getRightEncPos(); // using right arbitrarily at the momement, TODO check both or avg if more accurate
    m_DriveTrain.setBrakeMode();
    if (m_motorRotations == 0) {
      m_driveSpeed = 0;
      end(false);
    } else {
      if (m_motorRotations > 0) { // needs to move forward
        m_driveSpeed = Constants.kAutoDriveSpeed;
      } else { // needs to move backwards
        m_driveSpeed = -1 * Constants.kAutoDriveSpeed;
      }
      // Read the initial heading for tracking to that heading so we drive straight
      anglePID.setSetpoint(m_DriveTrain.getHeading());
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_startedStopping) {
      return;
    }
    // Update the rotation angle if we are starting to veer from our target angle
    double zRotation = anglePID.calculate(m_DriveTrain.getHeading());
    m_DriveTrain.drive(m_driveSpeed, zRotation, Constants.kFastSquaredInputs);
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_DriveTrain.setCoastMode();
  }

  @Override
  public boolean readyToStop() {
    if (Math.abs(m_motorRotations) <= Math.abs(m_DriveTrain.getRightEncPos() - m_startEncoderValue)) { // when the robot has travelled the necessary distance
      return true;
    } else {
      return false;
    }
  }
}
