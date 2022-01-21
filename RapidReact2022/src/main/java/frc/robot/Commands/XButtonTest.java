package frc.robot.Commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class XButtonTest extends CommandBase {

    private int loopcount = 0;

    public XButtonTest(){
        // SmartDashboard.putBoolean("X Pressed", false);
    }

    @Override
    public void initialize() {
        System.out.println("XButtonTest init");
        SmartDashboard.putBoolean("X Pressed", false);
    }

    @Override
    public void execute() {
        if ((++loopcount % 50) == 0) {
            System.out.println("XButtonTest exec");
        }
        SmartDashboard.putBoolean("X Pressed", true);
    }

    public void end() {
        System.out.println("XButtonTest end");
        SmartDashboard.putBoolean("X Pressed", false);       
    }
}
