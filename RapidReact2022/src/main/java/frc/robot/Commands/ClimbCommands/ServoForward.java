package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.LatchServos;

public class ServoForward extends CommandBase {

    private LatchServos m_latchServos;
    private double leftFwdAng = Constants.kLeftServoForwardAngle;
    private double rightFwdAng = Constants.kRightServoForwardAngle;

    public ServoForward(LatchServos latchServos) {
        m_latchServos = latchServos;

        addRequirements(m_latchServos);
    }
    
    @Override
    public void initialize() {
        System.out.println("setServoForward running");
        m_latchServos.setRightServoForward(rightFwdAng);
        m_latchServos.setLeftServoForward(leftFwdAng);

        SmartDashboard.putNumber("LServoFwd", leftFwdAng);
        SmartDashboard.putNumber("RServoFwd", rightFwdAng);
    }

    @Override
    public void execute() {
        leftFwdAng = SmartDashboard.getNumber("LServoFwd", leftFwdAng);
        rightFwdAng = SmartDashboard.getNumber("RServoFwd", rightFwdAng);
        m_latchServos.setRightServoBackward(rightFwdAng);
        m_latchServos.setLeftServoBackward(leftFwdAng);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
