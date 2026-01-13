package frc.robot.commands;

import static frc.robot.Constants.ShooterConstants.kShootCloseSpeed;
import static frc.robot.Constants.FeederConstants.kFeederSpeed;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


public class ShootCommand extends Command{
    private final ShooterSubsystem m_shooterSubsystem;
    private final FeederSubsystem m_feederSubsystem;

    public ShootCommand(ShooterSubsystem shooterSubsystem, FeederSubsystem feederSubsystem) {
        m_shooterSubsystem = shooterSubsystem;
        m_feederSubsystem = feederSubsystem;
        addRequirements(shooterSubsystem, feederSubsystem);
    }

    @Override
    public void initialize() {
        m_shooterSubsystem.setVelocity(kShootCloseSpeed);
    }

    @Override
    public void execute() {
        if(m_shooterSubsystem.AtVelocity()) {
            m_feederSubsystem.setFeederSpeed(kFeederSpeed);
        }
        else {
            m_feederSubsystem.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_shooterSubsystem.stop();
        m_feederSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
