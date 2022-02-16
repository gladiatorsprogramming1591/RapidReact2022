package frc.robot.commands.AutonomousCommands.PIDDriveDistance;

import frc.robot.subsystems.DriveTrainC;
import frc.robot.Constants;

/**
 * Creates a PIDDriveEncoder based on the inches value passed to it
 */
public class PIDDriveInches extends PIDDriveEncoder{
    /**
     * Constructs a PIDDriveEncoder by translating inches into wheel rotations
     * 
     * @param driveTrainC the DriveTrainC subsystem to be used
     * @param inches how many inches the robot should drive (negative means backwards)
     */
    public PIDDriveInches(DriveTrainC driveTrainC, double inches) {
        super(driveTrainC, inches / Constants.kWheelCircumferenceInches);
    }
}
