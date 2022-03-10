package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class ZeroClimbPosition extends CommandBase {
    Climb m_climb; 

    public ZeroClimbPosition(Climb climb){
        m_climb = climb; 
        addRequirements(m_climb);
    }

    @Override 
    public void initialize(){
        m_climb.setZeroPos(m_climb.getLeftEncPos()); 
    }

    @Override
    public boolean isFinished(){
        return true; 
    } 
    
}
