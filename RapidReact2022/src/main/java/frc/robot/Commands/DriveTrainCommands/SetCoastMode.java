package frc.robot.commands.DriveTrainCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

/**
 * Activate coast mode for the passed DriveTrain.
 */
public class SetCoastMode extends CommandBase{
    private final DriveTrain m_DriveTrain;
    
    /**
     * Activate coast mode, which allows motors to spin freely when set to 0.
     * 
     * @param drivetrain The DriveTrain to activate coast mode on.
     */
    public SetCoastMode(DriveTrain drivetrain) {
        m_DriveTrain = drivetrain;
    }

    @Override
    public void initialize() {
        m_DriveTrain.setCoastMode();
    }

    @Override
    public boolean isFinished() {
        return true; // don't loop
    }

}
