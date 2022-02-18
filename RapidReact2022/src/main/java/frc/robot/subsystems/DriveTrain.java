/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.DriveTrainCommands.FastDrive;

public abstract class DriveTrain extends SubsystemBase {
  // * Member fields

  private final DifferentialDrive m_differentialDrive;

  private final Joystick m_driverJoystick;

  private double m_AxisForward = 0;
  private double m_AxisTurning = 0;

      NetworkTable table;
      NetworkTableEntry tx;
      NetworkTableEntry ty;
      NetworkTableEntry ta;
      NetworkTableEntry tv;

  public DriveTrain(DifferentialDrive differentialDrive, Joystick driverJoystick) {
    m_differentialDrive = differentialDrive;
    m_driverJoystick = driverJoystick;
    setDefaultCommand(new FastDrive(this));
    table = NetworkTableInstance.getDefault().getTable("limelight");
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

    static void Limelight(){

 
    }
    
    public double calculateHorizontalError() {

      tx = table.getEntry("tx");

      return tx.getDouble(0.0);
    }

    public double calculateVerticalError() {

      ty = table.getEntry("ty");

      return ty.getDouble(0.0); // - Constants.kTargetHeight;
    }

 @Override
 public void periodic() {
  //  drive(m_AxisForward, m_AxisTurning, Constants.kSlowSquaredInputs);

  tx = table.getEntry("tx");
  ty = table.getEntry("ty");
  ta = table.getEntry("ta");
  tv = table.getEntry("tv");

  double x = tx.getDouble(0.0);
  double y = ty.getDouble(0.0);
  double area = ta.getDouble(0.0);
  int v = (int) tv.getNumber(0.0);

  SmartDashboard.putNumber("LimelightX", x);
  SmartDashboard.putNumber("LimelightY", y);
  SmartDashboard.putNumber("LimelightArea", area);
  SmartDashboard.putNumber("LimelightV", v);
 }


   // Called every time the scheduler runs while the command is scheduled.
 public void setSlowDrive() {
   m_AxisForward = getAxisForward() * Constants.kSlowDriveScalar;
   m_AxisTurning = getAxisTurning() * Constants.kSlowDriveScalar;
 }
 
 public void setFastDrive() {
   m_AxisForward = getAxisForward() * Constants.kFastDriveScalar;
   m_AxisTurning = getAxisTurning() * Constants.kFastDriveScalar;
 }
}