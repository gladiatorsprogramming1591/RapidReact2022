/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ShooterCommands;

import frc.robot.Constants;
import frc.robot.JoystickButtonConstants;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * An example command that uses an example subsystem.
 */
public class ShooterOn extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSubsystem m_subsystem;
  private JoystickButton m_Button;
  private double m_speed = Constants.kLowGoalSpeed;
  public ShooterOn(ShooterSubsystem shooterSubsystem){
    this(shooterSubsystem, Constants.kLowGoalSpeed);
  }
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterOn(ShooterSubsystem shooterSubsystem, double speed) {
    m_subsystem = shooterSubsystem;
    m_speed = speed;
    // m_Button = joystickButton;_________

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooterSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("ShooterSubsystem Calling ShooterOn with speed: " + m_speed);
    SmartDashboard.putNumber("Shoot Req Vel", m_speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // m_speed = SmartDashboard.getNumber("Shoot Req Vel", m_speed);
    m_subsystem.setShooterSpeed(m_speed);
    SmartDashboard.putNumber("Shoot Req Vel", m_speed);
    // System.out.println("shooter speed: " + m_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}   //Idea: Press to  stop, release to  start (now redundant)
