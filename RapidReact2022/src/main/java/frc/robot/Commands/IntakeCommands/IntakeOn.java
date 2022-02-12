package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.IntakeSubsystem;

public class IntakeOn extends CommandBase {

   IntakeSubsystem m_intakeSubsystem;

   public IntakeOn(IntakeSubsystem intakeSubsystem) {
      m_intakeSubsystem = intakeSubsystem;
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(m_intakeSubsystem);
   }
    
   // Called when the command is initially scheduled.
   @Override
   public void initialize() {
      System.out.println("IntakeSubsystem Calling IntakeReverse");
   }

   // Called every time the scheduler runs while the command is scheduled.
   @Override
   public void execute() {
      m_intakeSubsystem.intakeOn();
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

