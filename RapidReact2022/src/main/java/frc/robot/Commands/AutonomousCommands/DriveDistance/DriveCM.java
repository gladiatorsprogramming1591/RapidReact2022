package frc.robot.commands.AutonomousCommands.DriveDistance;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainC;

/**
 * Command the robot to drive forward or backwards a certain amount of centimeters (in a straight line).
 */
public class DriveCM extends DriveEncoder{
    /**
     * Construct a DriveEncoder with centimeters translated into rotations.
     * 
     * @param driveTrain The driveTrain subsystem that DriveEncoder will use.
     * @param cm The distance in centimeters the robot should travel (use a negative value to go backwards).
     */
    public DriveCM(DriveTrainC driveTrain, double cm) {
        super(driveTrain, cm / Constants.kWheelCircumferenceCM);
    }
}
