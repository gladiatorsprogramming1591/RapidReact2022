package frc.robot.Commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class XButtonTest extends CommandBase {

    public XButtonTest(){
        SmartDashboard.putBoolean("X Pressed", false);
    }

    public void initialize() {
        SmartDashboard.putBoolean("X Pressed", true);
    }

    public void end() {
        SmartDashboard.putBoolean("X Pressed", false);       
    }
}
