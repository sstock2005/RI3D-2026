package frc.robot;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class RobotContainer {
    private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    private final FeederSubsystem m_feeder = new FeederSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    private final ClimbSubsystem m_climb = new ClimbSubsystem();
    
    private final CommandXboxController m_driverController = 
        new CommandXboxController(Constants.kDriverControllerPort);

    public RobotContainer() {
        configureBindings();
    }

    private void configureBindings() {
        m_drivetrain.setDefaultCommand(
            m_drivetrain.driveArcade(
                () -> -m_driverController.getLeftY()  * Constants.DrivetrainConstants.kDriveScaling,
                () -> -m_driverController.getRightX() * Constants.DrivetrainConstants.kRotationScaling)
            );

        m_driverController.rightTrigger().whileTrue(new ShootCommand(m_shooter, m_feeder));
        m_driverController.rightBumper().whileTrue(new MoveIntakeCommand(m_intake, Constants.IntakeConstants.kRotationSpeed));
        m_driverController.leftBumper().whileTrue(new MoveIntakeCommand(m_intake, -Constants.IntakeConstants.kRotationSpeed));
        m_driverController.leftTrigger().whileTrue(new IntakeFeedCommand(m_intake, false));
        m_driverController.povUp().whileTrue(new ClimbCommand(m_climb, Constants.ClimbConstants.kClimbSpeed));
        m_driverController.povDown().whileTrue(new ClimbCommand(m_climb, -Constants.ClimbConstants.kClimbSpeed));
        m_driverController.a().whileTrue(new IntakeFeedCommand(m_intake, true));
        }
}   