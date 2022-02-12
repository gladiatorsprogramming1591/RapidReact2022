package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class MotorTest extends CommandBase {

    private int loopcount = 0;
    private WPI_TalonSRX motor;
    private Joystick m_stick;

    public MotorTest(Joystick stick){
        m_stick = stick;
    }

    @Override
    public void initialize() {
        System.out.println("MotorTest init");
        motor = new WPI_TalonSRX(Constants.kMotorChannel);
        // motor.configOpenloopRamp(1.0);
        SmartDashboard.putData(this);
    }

    @Override
    public void execute() {
        if ((++loopcount % 250) == 0) {
            System.out.println("MotorTest exec");
        }
        double y = m_stick.getY();
        motor.set(y);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("MotorTest end");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
