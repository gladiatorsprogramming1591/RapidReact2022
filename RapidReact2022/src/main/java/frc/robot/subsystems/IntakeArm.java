package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class IntakeArm extends SubsystemBase {

    private DoubleSolenoid m_piston;

    public IntakeArm() {
       
        m_piston = new DoubleSolenoid(Constants.kPCM_CANID, Constants.kArmSolenoidForwardChannel,
            Constants.kArmSolenoidReverseChannel);
    }

    public boolean armLowered = false;
    public void lowerArm() {
        SmartDashboard.putString("IntakeArm", "Forward (down)");
        System.out.println("Setting IntakeArm Forward (down)");
        m_piston.set(DoubleSolenoid.Value.kForward);
        armLowered = true;
    }
    public void raiseArm() {
        SmartDashboard.putString("IntakeArm", "Reverse (up)");
        System.out.println("Setting IntakeArm Reverse (up)");
        m_piston.set(DoubleSolenoid.Value.kReverse);
        armLowered = false;
    }
   
}