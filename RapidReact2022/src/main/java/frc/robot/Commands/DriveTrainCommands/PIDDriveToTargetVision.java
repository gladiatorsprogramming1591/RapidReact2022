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
public class PIDDriveToTargetVision extends CommandBase{
    private final DriveTrainC m_driveTrainC;
    private PIDController anglePID = new PIDController(Constants.kVisionPIDDriveRotP, Constants.kVisionPIDDriveRotI, Constants.kVisionPIDDriveRotD);
    private PIDController drivePID = new PIDController(Constants.kVisionPIDDriveP, Constants.kVisionPIDDriveI, Constants.kVisionPIDDriveD);
    private double m_angleSetpoint = 0;
    private double m_driveSetpoint = Constants.kTargetHeight;
    private int invalidTargetLoopCount = 0; 

    public PIDDriveToTargetVision(DriveTrainC driveTrainC) {
        addRequirements(driveTrainC);
        m_driveTrainC = driveTrainC;

        drivePID.setTolerance(Constants.kVisionPIDDrivePosTol, Constants.kVisionPIDDriveVelTol);
        anglePID.setTolerance(Constants.kVisionPIDAnglePosTol, Constants.kPIDAngleVelTol);
    }

    @Override
    public void initialize() {
        m_driveTrainC.setBrakeMode();
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
        
            double rot = anglePID.calculate(-m_driveTrainC.calculateHorizontalError()/10, m_angleSetpoint);
            SmartDashboard.putNumber("PIDDriveToTarget Rotations: ", rot);
            System.out.print("Rot: " + rot + "  RotPosErr: " + m_driveTrainC.calculateHorizontalError()/10);
            System.out.print("RotVelErr: " + anglePID.getVelocityError());
            double drive = drivePID.calculate(m_driveTrainC.calculateVerticalError()/10, m_driveSetpoint/10);
            SmartDashboard.putNumber("PIDDriveToTarget Drive: ", drive);

            System.out.print("  Drive: " + drive + "  DrivePosErr: " + (m_driveSetpoint/10.0 - m_driveTrainC.calculateVerticalError()/10));
            System.out.print("DriveVelErr: " + drivePID.getVelocityError());

            if (rot > 0.5){
                rot = 0.5; 
            } else if (rot < -0.5) {
                rot = -0.5;
            } 

            if (drive > 0.5){
                drive = 0.5; 
            } else if (drive < -0.5) {
                drive = -0.5;
            }
            
            m_driveTrainC.drive(drive, rot, false);
        }
    } 

    @Override
    public boolean isFinished() {
        if (invalidTargetLoopCount >= 100){
            System.out.println("Target lost! Stopping..."); 
            return(true); 
        }
        return anglePID.atSetpoint() && drivePID.atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        m_driveTrainC.drive(0, 0, Constants.kFastSquaredInputs);
        System.out.println("PIDDriveToTarget Ending...");
        // m_driveTrainC.setCoastMode();
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
