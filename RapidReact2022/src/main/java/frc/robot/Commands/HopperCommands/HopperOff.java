package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.HopperSubsystem;

public class HopperOff extends CommandBase {

   HopperSubsystem m_hopperSubsystem;

   public HopperOff(HopperSubsystem hopperSubsystem) {
      m_hopperSubsystem = hopperSubsystem;

      addRequirements(m_hopperSubsystem);
   }
    
   @Override
   public void initialize() {
      System.out.println("HopperSubsystem Calling HopperOff");
   }

   @Override
   public void execute() {
      m_hopperSubsystem.hopperOff();
   }
     
   // Called once the command ends or is interrupted.
   public void end(boolean interrupted) {
   }

   // Returns true when the command should end.
   @Override
   public boolean isFinished(){
      return true;
   }
     
}

