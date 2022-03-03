package frc.robot.commands.ClimbCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Climb;

public class ClimbToPosition extends CommandBase {
    Climb m_climb; 
    double m_position; 
    static final int UP = 1; 
    static final int DOWN = 2; 
    int m_direction;

    public ClimbToPosition(Climb climb, double position){
        m_position = position; 
        m_climb = climb; 
        addRequirements(m_climb);
    }

    @Override 
    public void initialize(){
        m_climb.setBrakeMode(); // force to always run this command in brake mode
        m_position += m_climb.getZeroPos(); // change from relative to absolute position
        double leftStart = m_climb.getLeftEncPos(); 
        System.out.println("ClimbToPosition init LeftEncPos: " + leftStart + " RightEncPos" + m_climb.getRightEncPos()); 
        if (Math.abs(m_position - leftStart) > 0.2){
            if (m_position < leftStart) { //go down
                m_climb.setClimbSpeed(Constants.kClimberDownwardSpeed);
                m_direction = DOWN; 
            } else {
                m_climb.setClimbSpeed(Constants.kClimberUpwardSpeed);
                m_direction = UP; 
            } 
        }
    }

    @Override
    public boolean isFinished(){
        double leftPose = m_climb.getLeftEncPos(); 
        if (leftPose - m_position - Constants.kClimbStopDist > 0 && m_direction == UP){
            return true; 
        } else {
            if (leftPose - m_position - Constants.kClimbStopDist < 0 && m_direction == DOWN){
                return true; 
            }
        }
        return false; 

    } 

    @Override 
    public void end(boolean interrupted){
        m_climb.setClimbSpeed(0);
        System.out.println("ClimbToPosition end LeftEncPos: " + m_climb.getLeftEncPos() + " RightEncPos" + m_climb.getRightEncPos()); 
    }

    
}
