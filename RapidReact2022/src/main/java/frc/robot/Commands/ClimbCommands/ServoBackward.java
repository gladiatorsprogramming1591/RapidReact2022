package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class ServoBackward extends CommandBase {

    private Climb m_climb;

    public ServoBackward(Climb climb) {
        m_climb = climb;

        // addRequirements(m_climb);
    }
    
    @Override
    public void initialize() {
        System.out.println("ServoBackward run once");
        m_climb.setServoBackward();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
