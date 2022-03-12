package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.HopperCommands.HopperOn;
import frc.robot.commands.IntakeCommands.IntakeOn;
import frc.robot.commands.ShooterCommands.ShooterLowGoal;
import frc.robot.commands.ShooterCommands.ShooterOn;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootAutoLowGoal extends SequentialCommandGroup{

    public ShootAutoLowGoal(ShooterSubsystem shooter, HopperSubsystem hopper, IntakeSubsystem intake) {
        addCommands(
            // new IntakeOn(intake), // done in higher level command
            new HopperOn(hopper, Constants.kAutoHopperDist),
            new ShooterLowGoal(shooter).withTimeout(1.0),
            new HopperOn(hopper, Constants.kAutoHopperDist2ndBall)
        );
    }
}