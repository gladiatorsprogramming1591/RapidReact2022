package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climb;

public class ClimbWithStick extends CommandBase {

    private Joystick m_stick;
    private Climb m_climb;

    public ClimbWithStick(Joystick stick, Climb climb){
        m_stick = stick;
        m_climb = climb;
    }

    @Override
    public void initialize() {
        System.out.println("ClimbWithStick init");
    }

    @Override
    public void execute() {
        double y = -m_stick.getY();
        if (y > Constants.kClimberUpwardSpeed) {
            y = Constants.kClimberUpwardSpeed;
        } else {
            if (y < Constants.kClimberDownwardSpeed){
                y = Constants.kClimberDownwardSpeed;
            }
        }
        m_climb.setClimbSpeed(y);
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("ClimbWithStick end");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
