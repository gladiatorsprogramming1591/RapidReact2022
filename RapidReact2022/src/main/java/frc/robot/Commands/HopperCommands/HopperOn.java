package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.HopperSubsystem;

public class HopperOn extends CommandBase {

   HopperSubsystem m_hopperSubsystem;
   private double m_initialPosition;
   private double m_distance;

   public HopperOn(HopperSubsystem hopperSubsystem, double distance) {
      m_hopperSubsystem = hopperSubsystem;
      m_distance = distance;

      addRequirements(m_hopperSubsystem);
   }
    
   @Override
   public void initialize() {
      m_initialPosition = m_hopperSubsystem.getHopperEncPos();
      System.out.println("HopperOn initial position " + m_initialPosition);
   }

   @Override
   public void execute() {
      m_hopperSubsystem.hopperOn(Constants.kHopperForwardSpeed);
   }
     
   // Called once the command ends or is interrupted.
   public void end(boolean interrupted) {
      m_hopperSubsystem.hopperOff();
      System.out.println("HopperOn end position " + m_hopperSubsystem.getHopperEncPos());
      System.out.println("HopperOn change position " + (m_hopperSubsystem.getHopperEncPos()-m_initialPosition));
   }

   // Returns true when the command should end.
   @Override
   public boolean isFinished(){
      return m_hopperSubsystem.getHopperEncPos()-m_initialPosition > m_distance;
   }
   
}

