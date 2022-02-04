package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase{
    CANSparkMax m_shooterMotor;
    RelativeEncoder m_encoder;
    private double m_maxSpeed;
    private double m_minSpeed;
    double m_targetVelocity;

    public ShooterSubsystem(){
      m_maxSpeed = Constants.kNeoMaxSpeed;
      m_minSpeed = -Constants.kNeoMaxSpeed;

    }

    public double getShooterVelocity() {
        return m_encoder.getVelocity();
      }

      public void setShooterSpeed(double speed) {
        if (speed < m_minSpeed || speed > m_maxSpeed) {
          System.out.println("Invalid speed set for shooter, " + speed);
          m_shooterMotor.setVoltage(0);
        } else {
          // m_shooterMotor.getPIDController().setFF(speed * 1 + 0);
          m_targetVelocity = speed;
          System.out.println("Setting shooter speed to " + speed);
          m_shooterMotor.getPIDController().setReference(speed, ControlType.kVelocity);
          // m_shooterMotor.set(speed);
        }
      }
    
      public boolean isShooterAtSpeed() {
          return ((m_encoder.getVelocity() >= (m_targetVelocity * 1) - 25)
            && (m_encoder.getVelocity() <= (m_targetVelocity * 1) + 25));
        } 

        public void shooterOn() {
          //Turns on the shooter motor
          System.out.println("Turning shooter on");
          
            setShooterSpeed(Constants.kShooterMotorSpeedRPM);
      
        }

      public void shooterOff() {
        System.out.println("Turning shooter off");
        m_shooterMotor.set(0);
      }

}

