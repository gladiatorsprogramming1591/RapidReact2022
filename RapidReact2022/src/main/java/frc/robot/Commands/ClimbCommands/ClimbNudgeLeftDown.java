package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climb;

public class ClimbNudgeLeftDown extends CommandBase {
    Climb m_climb; 
     

    public ClimbNudgeLeftDown(Climb climb){
        m_climb = climb;
        addRequirements(m_climb);
    }

    @Override 
    public void initialize(){
        System.out.println("ClimbNudgeLeftDown init LeftEncPos: " + m_climb.getLeftEncPos() + " RightEncPos" + m_climb.getRightEncPos()); 
        m_climb.setBrakeMode(); // force to always run this command in brake mode
        m_climb.setLeftClimbSpeed(Constants.kClimberNudgeSpeed);
    }

    @Override
    public boolean isFinished(){
        return false; 

    } 

    @Override 
    public void end(boolean interrupted){
        m_climb.setClimbSpeed(0);
        System.out.println("ClimbNudgeLeftDown end LeftEncPos: " + m_climb.getLeftEncPos() + " RightEncPos" + m_climb.getRightEncPos()); 
    }

    
}
