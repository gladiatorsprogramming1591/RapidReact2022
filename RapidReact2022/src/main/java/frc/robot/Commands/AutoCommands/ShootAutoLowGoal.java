package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.HopperCommands.HopperAdvance;
import frc.robot.commands.ShooterCommands.ShooterOn;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootAutoLowGoal extends SequentialCommandGroup{

    public ShootAutoLowGoal(ShooterSubsystem shooter, HopperSubsystem hopper) {
        addCommands(
            new ShooterOn(shooter, Constants.kLowGoalSpeed),
            new HopperAdvance(hopper).withTimeout(0.3)
        );
    }
}