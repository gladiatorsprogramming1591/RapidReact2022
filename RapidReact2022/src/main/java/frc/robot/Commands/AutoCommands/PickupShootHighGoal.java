package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveTrainCommands.PIDDriveToTargetVision;
import frc.robot.commands.DriveTrainCommands.PIDDrive.PIDDriveInches;
import frc.robot.commands.DriveTrainCommands.PIDDrive.PIDTurnToDegrees;
import frc.robot.commands.IntakeCommands.IntakeOn;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class PickupShootHighGoal extends SequentialCommandGroup {
    
    public PickupShootHighGoal(ShooterSubsystem shooter, HopperSubsystem hopper, DriveTrainC driveTrainC, IntakeSubsystem intake) {
        addCommands(
            new IntakeOn(intake),
            new PIDDriveInches(driveTrainC, 50), 
            new PIDTurnToDegrees(driveTrainC, 180, false),
            // new PIDDriveToTargetVision(driveTrainC),
            new ShootAutoHighGoal(shooter, hopper, intake)
        );
    }
}
