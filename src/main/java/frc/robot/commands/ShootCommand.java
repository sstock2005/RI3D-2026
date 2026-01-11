package frc.robot.commands;

import static frc.robot.Constants.ShooterConstants.kShootCloseSpeed;
import static frc.robot.Constants.FeederConstants.kFeederSpeed;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FeederSubsystem;
import frc.robot.subsystems.ShooterSubsystem;


public class ShootCommand extends Command{
    private final ShooterSubsystem m_ShooterSubsystem;
    private final FeederSubsystem m_FeederSubsystem;

    public ShootCommand(ShooterSubsystem shooterSubsystem, FeederSubsystem feederSubsystem) {
        m_ShooterSubsystem = shooterSubsystem;
        m_FeederSubsystem = feederSubsystem;

        addRequirements(shooterSubsystem, feederSubsystem);
    }

    @Override
    public void initialize() {
        m_ShooterSubsystem.SetVelocity(kShootCloseSpeed);
    }

    @Override
    public void execute() {
        if(m_ShooterSubsystem.AtVelocity()) {
            m_FeederSubsystem.setFeederSpeed(kFeederSpeed);
        }
        else {
            m_FeederSubsystem.Stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_ShooterSubsystem.Stop();
        m_FeederSubsystem.Stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
