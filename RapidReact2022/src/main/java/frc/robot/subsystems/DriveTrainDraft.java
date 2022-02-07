package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.FastDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrainDraft extends SubsystemBase {

  private final DifferentialDrive m_differentialDrive;

  public DriveTrainDraft(DifferentialDrive m_differentialDrive, Joystick m_driverJoystick) {
    this.m_differentialDrive = m_differentialDrive;
    this.m_driverJoystick = m_driverJoystick;
    extracted();
}


private void extracted() {
    setDefaultCommand(new FastDrive(this));
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
    
      public void setBrakeMode() {
        System.out.println("Override me!!!");
      }
    
      public void setCoastMode() {
        System.out.println("Override me!!!");
      }
    
      public double getLeftEncPos() {
        return 0;  // Override me!!!
      }
    
      public double getRightEncPos() {
        return 0;  // Override me!!!

  }

}