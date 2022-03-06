package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.HopperSubsystem;

public class HopperOn extends CommandBase {

   HopperSubsystem m_hopperSubsystem;
   private double m_initialPosition;

   public HopperOn(HopperSubsystem hopperSubsystem) {
      m_hopperSubsystem = hopperSubsystem;

      addRequirements(m_hopperSubsystem);
   }
    
   @Override
   public void initialize() {
      m_initialPosition = m_hopperSubsystem.getHopperEncPos();
      System.out.println("HopperAdvance initial position " + m_initialPosition);
   }

   @Override
   public void execute() {
      m_hopperSubsystem.hopperOn();
   }
     
   // Called once the command ends or is interrupted.
   public void end(boolean interrupted) {
      m_hopperSubsystem.hopperOff();
   }

   // Returns true when the command should end.
   @Override
   public boolean isFinished(){
      return m_hopperSubsystem.getHopperEncPos()-m_initialPosition > Constants.kHopperAdvanceDist;
   }
   
}

