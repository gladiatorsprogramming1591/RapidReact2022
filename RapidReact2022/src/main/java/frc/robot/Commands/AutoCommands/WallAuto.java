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
            new ShootAutoLowGoal(shooter, hopper, intake),
            new PIDDriveInches(driveTrainC, -18), //-24 from the wall
            new IntakeOn(intake),
            new PIDTurnToDegrees(driveTrainC, -156.5, false),
            new PIDDriveInches(driveTrainC, 61), //56 from bot to ball
            //Reverse Steps
            new PIDDriveInches(driveTrainC, -61),
            new PIDTurnToDegrees(driveTrainC, 156.5, false),
            new PIDDriveInches(driveTrainC, 18),
            new ShootAutoLowGoal(shooter, hopper, intake)
        );
    }
}
