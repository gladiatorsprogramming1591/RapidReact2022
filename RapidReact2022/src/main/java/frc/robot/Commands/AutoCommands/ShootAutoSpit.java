package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.HopperCommands.AutoHopperOn;
import frc.robot.commands.HopperCommands.HopperOn;
import frc.robot.commands.IntakeCommands.IntakeOn;
import frc.robot.commands.ShooterCommands.ShooterHighGoal;
import frc.robot.commands.ShooterCommands.ShooterOn;
import frc.robot.commands.ShooterCommands.ShooterSpit;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootAutoSpit extends SequentialCommandGroup{

    public ShootAutoSpit(ShooterSubsystem shooter, HopperSubsystem hopper, IntakeSubsystem intake) {
        addCommands(
            // new IntakeOn(intake), // done in higher level command
            new ShooterSpit(shooter).withTimeout(1.7), 
            new AutoHopperOn(hopper, Constants.kAutoHopperDist),
            new ShooterSpit(shooter).withTimeout(2.0),
            new AutoHopperOn(hopper, Constants.kAutoHopperDist2ndBall)
            );
    }
}