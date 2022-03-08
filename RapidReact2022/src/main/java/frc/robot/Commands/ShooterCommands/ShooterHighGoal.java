package frc.robot.commands.ShooterCommands;

import frc.robot.Constants;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ShooterHighGoal extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final ShooterSubsystem m_subsystem;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterHighGoal(ShooterSubsystem shooterSubsystem) {
    m_subsystem = shooterSubsystem;

    addRequirements(shooterSubsystem);
  }

  @Override
  public void initialize() {
    System.out.println("ShooterSubsystem Calling ShooterHighGoal");
    SmartDashboard.putString("SHOOTER MODE", "High");
  }

  @Override
  public void execute() {
    m_subsystem.setShooterSpeed(Constants.kHighGoalSpeed);
  }

  @Override
  public void end(boolean interrupted) {    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
