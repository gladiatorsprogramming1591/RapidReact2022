package frc.robot.commands.DriveTrainCommands.PIDDrive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainC;

public class PIDTurnToDegrees extends CommandBase{
    private double m_setpoint;
    private final DriveTrainC m_DriveTrainC;
    private final double m_targetHeading;
    private final boolean m_isAbsolute;
    private PIDController anglePID = new PIDController(Constants.kRotationP, Constants.kRotationI, Constants.kRotationD);


    public PIDTurnToDegrees(DriveTrainC driveTrainC, double targetHeading, boolean isAbsolute) {
        addRequirements(driveTrainC);

        m_DriveTrainC = driveTrainC;
        m_targetHeading = targetHeading;    
        m_isAbsolute = isAbsolute;

        // Use brake mode for auto driving
        m_DriveTrainC.setBrakeMode();
    }

    /**
     * Get the setpoint for the PIDCommand constructor. This method is static so that it can be used in the constructor.
     * @param isAbsolute from PIDTurnToDegrees constructor
     * @param targetHeading from PIDTurnToDegrees constructor
     * @param driveTrainC from PIDTurnToDegrees constructor
     * @return the setpoint. should only be called once
     */
    private void setSetpoint(boolean isAbsolute, double targetHeading, DriveTrainC driveTrainC) {
        double setpoint;
        if(isAbsolute) {
            setpoint = targetHeading;
        } else {
            setpoint = driveTrainC.getHeading() + targetHeading;
            // if (setpoint >= 360) {
            //     setpoint -= 360;
            // } else if (setpoint <= -360) {
            //     setpoint -= 360;
            // }
        }
        m_setpoint = setpoint;
        System.out.println("PIDTurnToDegrees setPoint = " + m_setpoint);
    }

    @Override
    public void initialize() {
        super.initialize();
        setSetpoint(m_isAbsolute, m_targetHeading, m_DriveTrainC);
        anglePID.setTolerance(Constants.kAutoRotationError, Constants.kAutoRotationVelocityError);
    }

    @Override
    public void execute() {
        double rot = -anglePID.calculate(m_DriveTrainC.getHeading(), m_setpoint);
        System.out.println("Angle Error = " + (m_setpoint - m_DriveTrainC.getHeading()) + ", Rot = " + rot + "RotVel = " + anglePID.getVelocityError());
        SmartDashboard.putNumber("SetPointDifference", m_setpoint - m_DriveTrainC.getHeading());
        SmartDashboard.putNumber("SetPoint", m_setpoint);

        // Limit rotation speed
        final double MAX_SPEED = 0.5;

        if (rot > MAX_SPEED) {
            rot = MAX_SPEED;
        } else if (rot < -MAX_SPEED) {
            rot = -MAX_SPEED;
        }

        m_DriveTrainC.drive(0, rot, false);
    }

    @Override
    public boolean isFinished() {
        return anglePID.atSetpoint();
    }
}
