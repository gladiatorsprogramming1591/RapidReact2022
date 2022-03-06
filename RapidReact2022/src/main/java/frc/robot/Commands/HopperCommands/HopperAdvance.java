//NOT USED
package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.HopperSubsystem;

public class HopperAdvance extends CommandBase {

   HopperSubsystem m_hopperSubsystem;
   private double m_initialPosition;

   public HopperAdvance(HopperSubsystem hopperSubsystem) {
      m_hopperSubsystem = hopperSubsystem;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(m_hopperSubsystem);
   }
    
   // Called when the command is initially scheduled.
   @Override
   public void initialize() {
      m_initialPosition = m_hopperSubsystem.getHopperEncPos();
      System.out.println("HopperAdvance initial position " + m_initialPosition);
      m_hopperSubsystem.hopperOn();
   }

   // Called every time the scheduler runs while the command is scheduled.
   @Override
   public void execute() {
   }
     
   // Called once the command ends or is interrupted.
   public void end(boolean interrupted) {
      double endPosition = m_hopperSubsystem.getHopperEncPos();
      System.out.println("HopperAdvance end position " + endPosition);
      System.out.println("HopperAdvance change in position " + (endPosition - m_initialPosition));
      m_hopperSubsystem.hopperOff();
   }

   // Returns true when the command should end.
   @Override
   public boolean isFinished(){
      return false;
   }
     
}

