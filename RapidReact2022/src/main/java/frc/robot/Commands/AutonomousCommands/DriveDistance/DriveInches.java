package frc.robot.commands.AutonomousCommands.DriveDistance;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainC;

/**
 * Command the robot to drive forward or backwards a certain amount of inches (in a straight line).
 */
public class DriveInches extends DriveEncoder{
    /**
     * Construct a DriveEncoder with inches translated into rotations.
     * 
     * @param driveTrain The driveTrain subsystem that DriveEncoder will use.
     * @param inches The disance in inches the robot should travel (use a negative value to go backwards).
     */
    public DriveInches(DriveTrainC driveTrain, double inches) {
        super(driveTrain, inches / Constants.kWheelCircumferenceInches);
    }
}
