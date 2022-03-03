package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Climb extends SubsystemBase {

  private static CANSparkMax m_LeftMotor;
  private static CANSparkMax m_RightMotor;
  private static RelativeEncoder m_leftEncoder;
  private static RelativeEncoder m_rightEncoder;
  private MotorControllerGroup m_MCG; //"Motor Controller Group"
  private double zeroPos = 0; 
  private static Servo m_Servo;

  public Climb() {
      m_MCG = new MotorControllerGroup(
          m_LeftMotor = new CANSparkMax(Constants.kLeftClimberChannel, MotorType.kBrushless),
          m_RightMotor = new CANSparkMax(Constants.kRightClimberChannel, MotorType.kBrushless)
        );
      m_Servo = new Servo(Constants.kServoChannel);

    // Don't use open loop ramp rate with PID
    // Also, climb start/stop should be OK without a ramp
    // Finally, we want the climb to run in brake mode always to stop as quickly as possible
    // m_LeftMotor.setOpenLoopRampRate(Constants.kDriveRampRate);  //kDriveRampRate may be ok
    // m_RightMotor.setOpenLoopRampRate(Constants.kDriveRampRate);
    setBrakeMode();

    m_RightMotor.setInverted(true);
    
    m_leftEncoder = m_LeftMotor.getEncoder();
    m_rightEncoder = m_RightMotor.getEncoder();
  }
  
  public void setBrakeMode() {
    System.out.println("Calling Climb.setBrakeMode");
    m_LeftMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    m_RightMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
  }

  public void setCoastMode() {
    System.out.println("Calling Climb.setCoastMode");
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

  public void setZeroPos(double position){
    zeroPos = position; 
  } 

  public double getZeroPos(){
    return zeroPos; 
  }


  public void periodic() {
    // This method will be called once per scheduler run
    // This should be used for diagnostics and not used to run motors since this is used
    // in all modes, not just teleop
    SmartDashboard.putNumber("Left Climb Enc", m_leftEncoder.getPosition());//NotShowingOnDashboard??_____________________
    SmartDashboard.putNumber("Right Climb Enc", m_rightEncoder.getPosition());
    SmartDashboard.putNumber("Left Climb Vel", m_leftEncoder.getVelocity());
    SmartDashboard.putNumber("Right Climb Vel", m_rightEncoder.getVelocity());
  }

  public void setClimbSpeed(double speed) {
    m_MCG.set(speed);
  }

  public void setLeftClimbSpeed(double speed){
    m_LeftMotor.set(speed);
  }

  public void setRightClimbSpeed(double speed){
    m_RightMotor.set(speed);
  }

  public void toggleIdleMode() {
    if(m_LeftMotor.getIdleMode() == IdleMode.kBrake) {
      setCoastMode();
    } else {
      setBrakeMode();
    }
  }

  public void setServoForward(){
    m_Servo.setAngle(Constants.kServoForwardAngle);
  }

  public void setServoBackward(){
    m_Servo.setAngle(Constants.kServoBackwardAngle);
  }
}
