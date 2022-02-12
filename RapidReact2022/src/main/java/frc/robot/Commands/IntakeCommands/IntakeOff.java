package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.IntakeSubsystem;

public class IntakeOff extends CommandBase {

   IntakeSubsystem m_intakeSubsystem;

   public IntakeOff(IntakeSubsystem intakeSubsystem) {
      m_intakeSubsystem = intakeSubsystem;
      // Use addRequirements() here to declare subsystem dependencies. ][\]
      addRequirements(m_intakeSubsystem);
   }
    
   // Called when the command is initially scheduled.
   @Override
   public void initialize() {
      System.out.println("IntakeSubsystem Calling IntakeOff");
   }

   // Called every time the scheduler runs while the command is scheduled.
   @Override
   public void execute() {
      m_intakeSubsystem.intakeOff();
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

