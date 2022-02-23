package frc.robot.commands.DriveTrainCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    private int invalidTargetLoopCount = 0; 

    public PIDDriveToTarget(DriveTrainC driveTrainC) {
        addRequirements(driveTrainC);
        m_driveTrainC = driveTrainC;

        drivePID.setTolerance(Constants.kPIDDrivePosTol, Constants.kPIDDriveVelTol);
        anglePID.setTolerance(Constants.kPIDAnglePosTol, Constants.kPIDAngleVelTol);
    }

    @Override
    public void initialize() {
        System.out.println("--------------- PIDDriveToTarget ----------------");
        if (m_driveTrainC.validTarget() != true){
            System.out.println("PIDDriveToTarget: no valid target"); 
            invalidTargetLoopCount = 100; //force isFinished to be true
        } 

        
    }

    @Override
    public void execute() {
        if (m_driveTrainC.validTarget() != true){
            invalidTargetLoopCount++;  
        } else {
            invalidTargetLoopCount = 0; 
        
            double rot = anglePID.calculate(m_driveTrainC.calculateHorizontalError(), 0);
            SmartDashboard.putNumber("PIDDriveToTarget Rotations: ", rot);
            double drive = drivePID.calculate(m_driveTrainC.calculateVerticalError(), Constants.kTargetHeight);
            SmartDashboard.putNumber("PIDDriveToTarget Drive: ", drive);

            m_driveTrainC.drive(0, rot, Constants.kFastSquaredInputs); // testing with forward speed of 0 for now  
        }
    } 

    @Override
    public boolean isFinished() {
        if (invalidTargetLoopCount >= 100){
            System.out.println("Target lost! Stopping..."); 
            return(true); 
        }
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
