package frc.robot;

import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
    private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    private final FeederSubsystem m_feeder = new FeederSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    // private final ClimbSubsystem m_climb = new ClimbSubsystem();
    
    private final CommandXboxController m_driverController = 
        new CommandXboxController(Constants.kDriverControllerPort);

    public RobotContainer() {
        configureBindings();
    }

    private void configureBindings() {
        m_drivetrain.setDefaultCommand(
          new ArcadeDriveCommand(m_drivetrain, -m_driverController.getLeftY(), -m_driverController.getRightX())
        );

      m_driverController.rightTrigger().whileTrue(new ShootCommand(m_shooter, m_feeder));
      // m_driverController.rightBumper().onTrue(new IntakeInCommand(m_intake));
      // m_driverController.leftBumper().onTrue(new IntakeOutCommand(m_intake));
      m_driverController.leftTrigger().whileTrue(new IntakeFeedCommand(m_intake, false));
      // m_driverController.povUp().whileTrue(new ClimbUp(m_climb));
      // m_driverController.povDown().whileTrue(new ClimbDown(m_climb));
      m_driverController.a().whileTrue(new IntakeFeedCommand(m_intake, true));
    }
}