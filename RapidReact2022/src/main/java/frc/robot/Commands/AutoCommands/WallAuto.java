package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveTrainCommands.PIDDriveToTargetVision;
import frc.robot.commands.DriveTrainCommands.PIDDrive.PIDDriveInches;
import frc.robot.commands.DriveTrainCommands.PIDDrive.PIDTurnToDegrees;
import frc.robot.commands.IntakeCommands.IntakeOn;
import frc.robot.commands.ShooterCommands.ShooterHighGoal;
import frc.robot.commands.ShooterCommands.ShooterLowGoal;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class WallAuto extends SequentialCommandGroup {
    
    public WallAuto(ShooterSubsystem shooter, HopperSubsystem hopper, DriveTrainC driveTrainC, IntakeSubsystem intake) {
        addCommands(
            new ShooterLowGoal(shooter).withTimeout(1.0), // turn on shooter, wait for speed
            new PIDDriveInches(driveTrainC, -50),
            new PIDTurnToDegrees(driveTrainC, 180, false),
            // new IntakeOn(intake),
            new PIDDriveInches(driveTrainC, 50)
            // new PIDTurnToDegrees(driveTrainC, 180, false),
            // new ShooterLowGoal(shooter).withTimeout(0.8), // turn on shooter, allow drivetrain to stop
            // new PIDDriveInches(driveTrainC, 105),  // Guess at forward distance
            // new ShootAutoLowGoal(shooter, hopper, intake),
            // new ShooterHighGoal(shooter).withTimeout(1.0), // Allow balls to exit shooter
            // new PIDDriveInches(driveTrainC, -50),
            // new ShooterHighGoal(shooter).withTimeout(0.5), // Allow drivetrain to stop
            // new PIDTurnToDegrees(driveTrainC, -90, false) 
        );
    }
}
