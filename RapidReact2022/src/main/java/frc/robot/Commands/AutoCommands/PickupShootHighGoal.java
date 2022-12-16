package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.DriveTrainCommands.PIDDriveToTargetVision;
import frc.robot.commands.DriveTrainCommands.PIDDrive.PIDDriveInches;
import frc.robot.commands.DriveTrainCommands.PIDDrive.PIDTurnToDegrees;
import frc.robot.commands.HopperCommands.HopperReverse;
import frc.robot.commands.IntakeCommands.IntakeOff;
import frc.robot.commands.IntakeCommands.IntakeOn;
import frc.robot.commands.ShooterCommands.ShooterHighGoal;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class PickupShootHighGoal extends SequentialCommandGroup {
    
    public PickupShootHighGoal(ShooterSubsystem shooter, HopperSubsystem hopper, DriveTrainC driveTrainC, IntakeSubsystem intake) {
        addCommands(
            new DriveFastAuto(driveTrainC, -0.3).withTimeout(0.1),  //Jerk to bring down intake
            new IntakeOn(intake),
            new ShooterHighGoal(shooter).withTimeout(0.1), 
            new PIDDriveInches(driveTrainC, 93),
            new PIDTurnToDegrees(driveTrainC, 180, false), //-170 if right tarmac, 180 if left
            new ShooterHighGoal(shooter).withTimeout(0.5),  //Gives time to turn 180 (Reduced from 1.0)
            new PIDDriveToTargetVision(driveTrainC),
             new IntakeOff(intake),
            new ShooterHighGoal(shooter).withTimeout(0.75), //Gives time to adjust turn/distance (Reduced from 1.0)
            new HopperReverse(hopper, Constants.kHopperReverseNudgeDist),
             new IntakeOn(intake),
            new ShootAutoHighGoal(shooter, hopper, intake)
        );
    }
}
