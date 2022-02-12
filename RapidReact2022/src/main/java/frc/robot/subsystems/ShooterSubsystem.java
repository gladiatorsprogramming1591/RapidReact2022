package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase{
    CANSparkMax m_shooterMotor;
    RelativeEncoder m_encoder;
    private double m_maxSpeed;
    private double m_minSpeed;
    double m_targetVelocity;

    public ShooterSubsystem(){
      m_shooterMotor = new CANSparkMax(Constants.kShooterMotorPort, MotorType.kBrushless);
      m_shooterMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
      m_maxSpeed = Constants.kNeoMaxSpeed;
      m_minSpeed = -Constants.kNeoMaxSpeed;

      m_encoder = m_shooterMotor.getEncoder();
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
          System.out.println("Setting shooter speed to " + Constants.kShooterMotorSpeed);
          // System.out.println("Setting shooter speed to " + speed);
          // m_shooterMotor.getPIDController().setReference(speed, ControlType.kVelocity);
          m_shooterMotor.set(Constants.kShooterMotorSpeed);
        }
      }
    
      public boolean isShooterAtSpeed() {
          return ((m_encoder.getVelocity() >= (m_targetVelocity * 1) - 25)
            && (m_encoder.getVelocity() <= (m_targetVelocity * 1) + 25));
        } 
        
        @Override
        public void periodic() {
          // This method will be called once per scheduler run
          SmartDashboard.putString("Shooter Speed", "" + Math.round(m_encoder.getVelocity()));
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

