package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase{
    CANSparkMax m_shooterMotor;
    CANEncoder m_encoder;
    private double m_maxSpeed;
    private double m_minSpeed;
    double m_targetVelocity;

    public ShooterSubsystem(){
        m_maxSpeed = 1;
        m_minSpeed = -1;

    }

    public double getShooterVelocity() {
        return m_encoder.getVelocity();
      }

      public void shooterOff() {
        System.out.println("Turning shooter off");
        m_shooterMotor.set(0);
      }
}

