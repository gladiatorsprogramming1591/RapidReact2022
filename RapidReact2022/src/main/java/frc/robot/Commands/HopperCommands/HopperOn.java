package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.HopperSubsystem;

public class HopperOn extends CommandBase {

   HopperSubsystem m_hopperSubsystem;

   public HopperOn(HopperSubsystem hopperSubsystem) {
      m_hopperSubsystem = hopperSubsystem;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(m_hopperSubsystem);
   }
    
   // Called when the command is initially scheduled.
   @Override
   public void initialize() {
      System.out.println("HopperSubsystem Calling HopperReverse");
   }

   // Called every time the scheduler runs while the command is scheduled.
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
      return false;
   }
   
}

