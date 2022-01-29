package Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsytem extends SubsystemBase{
  CANSparkMax m_shooterMotor;
  RelativeEncoder m_encoder;

  public ShooterSubsystem() {
    m_shooterMotor = new CANSparkMax(Constants.kShooterMotorPort, MotorType.kBrushless);
    m_shooterMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    // These PIDF values were taken from GRR, need to tweak
    /* m_shooterMotor.getPIDController().setP(0.00075);
    m_shooterMotor.getPIDController().setI(0.0);
    m_shooterMotor.getPIDController().setD(2.0);
    m_shooterMotor.getPIDController().setFF(0.00019); */

    m_encoder = m_shooterMotor.getEncoder();
    m_shooterMotor.setOpenLoopRampRate(Constants.kShooterRampRate);

    if (m_rpmOrDutyCycle == DutyCycleOrRPM.RPM) {
      m_maxSpeed = Constants.kNeoMaxSpeed;
      m_minSpeed = -Constants.kNeoMaxSpeed;
    }
    else {
      m_maxSpeed = 1;
      m_minSpeed = -1;
    }
  }
}

