package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class Regurgitate extends CommandBase {

   HopperSubsystem m_hopperSubsystem;
   IntakeSubsystem m_intakeSubsystem;
   private double m_initialPosition;

 	public Regurgitate(HopperSubsystem hopperSubsystem, IntakeSubsystem intakeSubsystem) {
      m_hopperSubsystem = hopperSubsystem;

      m_intakeSubsystem = intakeSubsystem;

      addRequirements(m_hopperSubsystem);
      addRequirements(m_intakeSubsystem);
   }
    
   @Override
   public void initialize() {
      m_initialPosition = m_hopperSubsystem.getHopperEncPos();
      System.out.println("Regurgitate I.P. " + m_initialPosition);
   }

   @Override
   public void execute() {
      m_hopperSubsystem.hopperReverse(Constants.kHopperReverseSpeed);
      m_intakeSubsystem.intakeReverse();
   }
    
   public void end(boolean interrupted) {
      m_hopperSubsystem.hopperOff();
      m_intakeSubsystem.intakeOn();
      System.out.println("HopperRegurgitate end position " + m_hopperSubsystem.getHopperEncPos());
      System.out.println("HopperRegurgitate change position " + (m_hopperSubsystem.getHopperEncPos()-m_initialPosition));
   }

   @Override
   public boolean isFinished(){
      return m_hopperSubsystem.getHopperEncPos()-m_initialPosition < Constants.kHopperReverseDist;
   }

}