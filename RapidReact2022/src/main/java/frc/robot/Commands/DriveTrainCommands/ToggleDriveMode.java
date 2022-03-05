/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveTrainCommands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ToggleDriveMode extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain m_driveTrain;
  private double m_AxisForward;
  private double m_AxisTurning;
  private boolean m_SquaredInput;
  // private static int DriveMode = 1; //0=Slow, 1=Fast
  // private Joystick m_button;

  /**
   * Creates a new ExampleCommand.
   
   * @param subsystem The subsystem used by this command.
   */
  public ToggleDriveMode(DriveTrain driveTrain) {
    m_driveTrain = driveTrain;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("Fast Drive", false);
  }
  

  @Override
  public void execute() {
    m_AxisForward = m_driveTrain.getAxisForward() * Constants.kSlowDriveScalar;
    m_AxisTurning = m_driveTrain.getAxisTurning() * Constants.kSlowTurnScalar;
    m_SquaredInput = Constants.kFastSquaredInputs;
    
    m_driveTrain.drive(m_AxisForward, m_AxisTurning,m_SquaredInput);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("Fast Drive", true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

//Get button status, if pressed return true___________________________________________________