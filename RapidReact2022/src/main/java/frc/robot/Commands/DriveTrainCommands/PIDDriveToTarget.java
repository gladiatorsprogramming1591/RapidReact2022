package frc.robot.commands.DriveTrainCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.Constants;

/**
 * Drive a certain number of wheel rotations, using PID to keep robot driving straight 
 * and reach its destination quickly and accurately.
 */
public class PIDDriveToTarget extends CommandBase{
    private final DriveTrainC m_driveTrainC;
    private PIDController anglePID = new PIDController(Constants.kPIDDriveRotP, Constants.kPIDDriveRotI, Constants.kPIDDriveRotD);
    private PIDController drivePID = new PIDController(Constants.kPIDDriveP, Constants.kPIDDriveI, Constants.kPIDDriveD);
    private double m_angleSetpoint;
    private double m_driveSetpoint;

    public PIDDriveToTarget(DriveTrainC driveTrainC) {
        addRequirements(driveTrainC);
        m_driveTrainC = driveTrainC;

        drivePID.setTolerance(Constants.kPIDDrivePosTol, Constants.kPIDDriveVelTol);
    }

    @Override
    public void initialize() {
        System.out.println("--------------- PIDDriveToTarget ----------------");

        
    }

    @Override
    public void execute() {
        double rot = anglePID.calculate(m_driveTrainC.calculateHorizontalError(), 0);
        System.out.println("PIDDriveToTarget Rotations: " + rot);
        double drive = drivePID.calculate(m_driveTrainC.calculateVerticalError(), Constants.kTargetHeight);
        System.out.println("PIDDriveToTarget Drive: " + drive);

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
