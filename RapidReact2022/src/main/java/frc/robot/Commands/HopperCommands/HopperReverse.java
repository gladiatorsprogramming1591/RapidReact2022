package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.HopperSubsystem;

public class HopperReverse extends CommandBase {

   HopperSubsystem m_hopperSubsystem;
   private double m_initialPosition;

 	public HopperReverse(HopperSubsystem hopperSubsystem) {
      m_hopperSubsystem = hopperSubsystem;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(m_hopperSubsystem);
   }
    
   // Called when the command is initially scheduled.
   @Override
   public void initialize() {
      m_initialPosition = m_hopperSubsystem.getHopperEncPos();
      System.out.println("HopperReverse initial position " + m_initialPosition);
   }

   // Called every time the scheduler runs while the command is scheduled.
   @Override
   public void execute() {
      m_hopperSubsystem.hopperReverse();
   }
    
   // Called once the command ends or is interrupted.
   public void end(boolean interrupted) {
      m_hopperSubsystem.hopperOff();
      System.out.println("HopperReverse end position " + m_hopperSubsystem.getHopperEncPos());
      System.out.println("HopperReverse change position " + (m_hopperSubsystem.getHopperEncPos()-m_initialPosition));
   }

   // Returns true when the command should end.
   @Override
   public boolean isFinished(){
      return m_hopperSubsystem.getHopperEncPos()-m_initialPosition < Constants.kHopperReverseDist;
   }

}

