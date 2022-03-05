package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.LatchServos;

public class ServoBackward extends CommandBase {

    private LatchServos m_latchServos;
    private double leftBkwdAng = Constants.kLeftServoBackwardAngle;
    private double rightBkwdAng = Constants.kRightServoBackwardAngle;

    public ServoBackward(LatchServos latchServos) {
        m_latchServos = latchServos;

        addRequirements(m_latchServos);
    }
    
    @Override
    public void initialize() {
        System.out.println("ServoBackward running");
        m_latchServos.setRightServoBackward(rightBkwdAng);
        m_latchServos.setLeftServoBackward(leftBkwdAng);

        SmartDashboard.putNumber("LServoBkwd", leftBkwdAng);
        SmartDashboard.putNumber("RServoBkwd", rightBkwdAng);
    }

    @Override
    public void execute() {
        leftBkwdAng = SmartDashboard.getNumber("LServoBkwd", leftBkwdAng);
        rightBkwdAng = SmartDashboard.getNumber("RServoBkwd", rightBkwdAng);
        m_latchServos.setRightServoBackward(rightBkwdAng);
        m_latchServos.setLeftServoBackward(leftBkwdAng);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
