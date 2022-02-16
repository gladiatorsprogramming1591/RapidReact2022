package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainC;

/**
 * An abstract class which provides functionality for all autonomous commands which move the robot (rotation or translation)
 * 
 * How to extend this class in an actual movement command:
 * 
 * public void execute() {
 *  if (reachedDestination) {
 *    stop();
 *  } else {
 *    // move to destination
 *  }
 * }
 */
public abstract class AutoMovementCommand extends CommandBase {
  protected boolean m_startedStopping = false;

  private DriveTrainC m_DriveTrain;

  public AutoMovementCommand(DriveTrainC driveTrain) {
    m_DriveTrain = driveTrain;
  }

  /**
   * Should contain logic for detecting when the robot has reached its destination and should be stopping.
   * 
   * @return True when the robot should be stopping.
   * @return False when the robot is not ready to stop yet.
   */
  protected abstract boolean readyToStop();

  @Override
  /**
   * DO NOT IMPLEMENT THIS METHOD IN CHILD CLASS
   */
  public boolean isFinished() {
    if (readyToStop()) {
      stop();
    }
    if (m_startedStopping && m_DriveTrain.isStopped()) {
      return true;
    } else {
      return false;
    }
  }

  private void stop() {
    m_DriveTrain.setBrakeMode();
    m_DriveTrain.drive(0, 0, Constants.kFastSquaredInputs);
    m_startedStopping = true;
  }
}
