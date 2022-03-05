package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Servo;

public class LatchServos extends SubsystemBase {

  private static Servo m_LeftServo;
  private static Servo m_RightServo;

  public LatchServos() {

      m_LeftServo = new Servo(Constants.kLeftServoChannel);
      m_RightServo = new Servo(Constants.kRightServoChannel);
  }

  public void periodic() {
    // This method will be called once per scheduler run
    // This should be used for diagnostics and not used to run motors since this is used
    // in all modes, not just teleop
  }

  public void setRightServoForward(double speed){
    m_RightServo.setAngle(speed);
  }

  public void setLeftServoForward(double speed){
    m_LeftServo.setAngle(speed);
  }


  public void setRightServoBackward(double speed){
    m_RightServo.setAngle(speed);
  }
  
  public void setLeftServoBackward(double speed){
    m_LeftServo.setAngle(speed);
  }
}
