/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.commands.DriveTrainCommands.SlowDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;

public abstract class DriveTrain extends SubsystemBase {
  // * Member fields

  private final DifferentialDrive m_differentialDrive;

  private final Joystick m_driverJoystick;

  public DriveTrain(DifferentialDrive differentialDrive, Joystick driverJoystick) {
    m_differentialDrive = differentialDrive;
    m_driverJoystick = driverJoystick;
    setDefaultCommand(new SlowDrive(this));
  }

  public void drive(double xSpeed, double zRotation, boolean squareInputs) {
    m_differentialDrive.arcadeDrive(xSpeed, zRotation, squareInputs);
  }

  public double getAxisForward() {
      return -m_driverJoystick.getY();
  }

  public double getAxisTurning() {
      return m_driverJoystick.getZ();
  }

  public abstract void setBrakeMode();

  public abstract void setCoastMode();
}
