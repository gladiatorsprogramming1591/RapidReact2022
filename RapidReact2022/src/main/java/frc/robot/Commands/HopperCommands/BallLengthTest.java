package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.subsystems.HopperSubsystem;

public class BallLengthTest extends CommandBase {

    private Joystick m_stick;
    private HopperSubsystem m_hopperSubsystem;
    private JoystickButton m_button;
    private double initPosition;

    public BallLengthTest(Joystick stick, HopperSubsystem hopperSubsystem, JoystickButton joystickButton){
        m_stick = stick;
        m_hopperSubsystem = hopperSubsystem;
        m_button = joystickButton;

        addRequirements(m_hopperSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("BallLengthTest init Pos " + m_hopperSubsystem.getHopperEncPos());
        initPosition = m_hopperSubsystem.getHopperEncPos();
    }

    @Override
    public void execute() {
        double y = -m_stick.getY();
        if (y > 0) {
            y *= .2;
        } else {
            if (y < 0){
                y = -.2;
            }
        }
        m_hopperSubsystem.hopperOn(y);
        SmartDashboard.putNumber("EncHop", m_hopperSubsystem.getHopperEncPos());
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("BallLengthTest end: EncPos: " + m_hopperSubsystem.getHopperEncPos());
        if (initPosition > 0){
        System.out.println("BallLengthTest Change: EncPos: " + (initPosition-m_hopperSubsystem.getHopperEncPos()));
        } else {
            if (initPosition < 0) {
                System.out.println("BallLengthTest Change: EncPos: " + (initPosition+m_hopperSubsystem.getHopperEncPos()));
            } else {
                System.out.println("BallLengthTest Change: None");
                }
            }
        }

    @Override
    public boolean isFinished() {
        return false;
    }
}//return false may be issue
