package frc.robot.commands.DriveTrainCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainC;

public class CalibrateNavX extends CommandBase{
    DriveTrainC m_DriveTrainC;

    public CalibrateNavX(DriveTrainC driveTrainC) {
        m_DriveTrainC = driveTrainC;
    }

    @Override
    public void initialize() {
        m_DriveTrainC.calibrateNavX();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
