package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class ToggleIdleMode extends CommandBase {

    private Climb m_climb;

    public ToggleIdleMode(Climb climb) {
        m_climb = climb;
    }
    
    @Override
    public void initialize() {
        System.out.println("ToggleIdleMode run once");
        m_climb.toggleIdleMode();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
