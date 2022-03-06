package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.ShooterCommands.ShooterOn;

public class ShooterSubsystem extends SubsystemBase{
    CANSparkMax m_shooterMotor;
    RelativeEncoder m_encoder;
    double m_targetVelocity;

    public ShooterSubsystem(){
      if(RobotContainer.isCBot){
        m_shooterMotor = new CANSparkMax(Constants.kShooterChannel, MotorType.kBrushless);
      } else {
        m_shooterMotor = new CANSparkMax(Constants.kPBotShooterChannel, MotorType.kBrushless);
      }
      m_shooterMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);

      m_shooterMotor.enableVoltageCompensation(12);

      // setDefaultCommand(new ShooterOn(this));
      m_encoder = m_shooterMotor.getEncoder();
    }

    public double getShooterVelocity() {
        return m_encoder.getVelocity();
      }

      public void setShooterSpeed(double speed) {
        m_shooterMotor.set(speed);
        
      }
    
      public boolean isShooterAtSpeed() {
          return ((m_encoder.getVelocity() >= (m_targetVelocity * 1) - 25)
            && (m_encoder.getVelocity() <= (m_targetVelocity * 1) + 25));
        } 
        
        @Override
        public void periodic() {
          // This method will be called once per scheduler run
          SmartDashboard.putString("Shooter Vel", "" + Math.round(m_encoder.getVelocity()));
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

