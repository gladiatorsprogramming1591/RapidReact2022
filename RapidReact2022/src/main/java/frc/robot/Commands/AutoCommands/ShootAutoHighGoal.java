package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.HopperCommands.HopperOn;
import frc.robot.commands.IntakeCommands.IntakeOn;
import frc.robot.commands.ShooterCommands.ShooterOn;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootAutoHighGoal extends SequentialCommandGroup{

    public ShootAutoHighGoal(ShooterSubsystem shooter, HopperSubsystem hopper, IntakeSubsystem intake) {
        addCommands(
            new IntakeOn(intake),
            new ShooterOn(shooter, Constants.kHighGoalSpeed).withTimeout(0.5),
            new HopperOn(hopper, Constants.kAutoHopperDist)
        );
    }
}