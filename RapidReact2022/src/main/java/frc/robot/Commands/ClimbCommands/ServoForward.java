package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class ServoForward extends CommandBase {

    private Climb m_climb;

    public ServoForward(Climb climb) {
        m_climb = climb;

        // addRequirements(m_climb);
    }
    
    @Override
    public void initialize() {
        System.out.println("setServoForward run once");
        m_climb.setServoForward();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
