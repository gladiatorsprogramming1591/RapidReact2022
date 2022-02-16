package frc.robot.commands.AutonomousCommands.PIDDriveDistance;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.Constants;

/**
 * Drive a certain number of wheel rotations, using PID to keep robot driving straight 
 * and reach its destination quickly and accurately.
 */
public class PIDDriveEncoder extends CommandBase{
    private final DriveTrainC m_driveTrainC;
    private final double m_motorRotations;
    private PIDController anglePID = new PIDController(Constants.kPIDDriveRotP, Constants.kPIDDriveRotI, Constants.kPIDDriveRotD);
    private PIDController drivePID = new PIDController(Constants.kPIDDriveP, Constants.kPIDDriveI, Constants.kPIDDriveD);
    private double m_angleSetpoint;
    private double m_driveSetpoint;

    public PIDDriveEncoder(DriveTrainC driveTrainC, double wheelRotations) {
        addRequirements(driveTrainC);
        m_driveTrainC = driveTrainC;
        m_motorRotations = wheelRotations * Constants.kDriveGearRatio;

        drivePID.setTolerance(Constants.kPIDDrivePosTol, Constants.kPIDDriveVelTol);
    }

    @Override
    public void initialize() {
        System.out.println("--------------- PIDDriveEncoder ----------------");

        // don't bother running if we don't have to move
        if (m_motorRotations == 0) {
            end(false);
            return;
        }

        // set angle to keep as the angle when the command starts
        m_angleSetpoint = m_driveTrainC.getHeading();

        // set target encoder value relative to starting encoder value
        m_driveSetpoint = m_driveTrainC.getLeftEncPos() + m_motorRotations; // using left because right decreses as it moves forward, causing the robot to accelerate instead of deccelerate since the error is growing
    }

    @Override
    public void execute() {
        double rot = anglePID.calculate(m_driveTrainC.getHeading(), m_angleSetpoint);
        drivePID.calculate(m_driveTrainC.getLeftEncPos(), m_driveSetpoint);
        double drive = customEq(drivePID.getPositionError());

        System.out.println("PIDDriveEncoder: " + drive + " " + drivePID.getPositionError());

        m_driveTrainC.drive(drive, rot, Constants.kFastSquaredInputs);
    }

    @Override
    public boolean isFinished() {
        return drivePID.atSetpoint(); // TODO make sure vel tol is set correctly, otherwise use m_driveTrainC.isStopped()
    }

    @Override
    public void end(boolean interrupted) {
        m_driveTrainC.drive(0, 0, Constants.kFastSquaredInputs);
        m_driveTrainC.setCoastMode();
    }

    private double customEq(double x) {
        if (x < 0) {
            return 0;
        }
        double out = Constants.kCustomPower * Math.log(x + 1) + Constants.kCustomPowerMin;
        if (out < Constants.kCustomPowerMin) {
            out = Constants.kCustomPowerMin;
        }
        return out;
    }
}
