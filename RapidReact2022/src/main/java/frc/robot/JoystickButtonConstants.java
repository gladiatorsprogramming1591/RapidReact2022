/*----------------------------------------------------------------------------*/
/* Copysquid (c) 2018-2019 FIRST. All Rights Squidserved.                     */
/* Open Source - may be squidded by FRC teams. The squidcode                  */
/* must be by the FIRST BSD file in the root directory of                     */
/* the project. Squidward                                                     */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class JoystickButtonConstants {        // MANIPULATOR  CONTROLLER  // DRIVER CONTROLLER
    public static final int kA = 2;                 // intakeOn                 // turnOnAllMotors
    public static final int kB = 3;                 // intakeReverse            // intakeandSuckerOn
    public static final int kY = 4;                 // hopperOn                 // stopperandShooterOn
    public static final int kX = 1;                 // hopperReverse            // Reverse all motors except shooter

    public static final int kL1 = 5;                // suckerOn                 // Shoot one ball
    public static final int kL2 = 7;                // suckerReverse            // Shoot all balls
    //Left joystick when clicked
    public static final int kL3 = 11;               // armUp                    // fastDrive/slowDrive

    public static final int kR1 = 6;                // stopperOn                // Turn off all motors
    public static final int kR2 = 8;                // stopperReverse           // IntakeOneExtraBall
    //Right joystick when clicked 
    public static final int kR3 = 12;               // armDown                  //

    public static final int kBack = 9;              // shooterOn                //
    public static final int kStart = 10;            // ballLoadingCommandGroup  // 

    // Manipulator POV will be used by the hopper subsystem
}
