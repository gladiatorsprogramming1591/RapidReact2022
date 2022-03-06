package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.PIDDrive.PIDDriveInches;
import frc.robot.commands.PIDDrive.PIDTurnToDegrees;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class MainAutoCommand extends SequentialCommandGroup {
    
    public MainAutoCommand(ShooterSubsystem shooter, HopperSubsystem hopper, DriveTrainC driveTrainC) {
        addCommands(
            // new ShootAutoLowGoal(shooter, hopper),
            new PIDDriveInches(driveTrainC, -60)
            // , new PIDTurnToDegrees(driveTrainC, -90, false)
        );
    }
}
