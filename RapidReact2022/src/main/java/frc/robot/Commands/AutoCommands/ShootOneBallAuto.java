package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveTrainCommands.PIDDrive.PIDDriveInches;
import frc.robot.commands.ShooterCommands.ShooterLowGoal;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootOneBallAuto extends SequentialCommandGroup {
    
    public ShootOneBallAuto(ShooterSubsystem shooter, HopperSubsystem hopper, DriveTrainC driveTrainC, IntakeSubsystem intake) {
        addCommands(
            new ShooterLowGoal(shooter).withTimeout(0.8), // turn on shooter, allow drivetrain to stop
            new ShootAutoLowGoal(shooter, hopper, intake),
            new ShooterLowGoal(shooter).withTimeout(7.0),
            new PIDDriveInches(driveTrainC, -70)
        );
    }
}
