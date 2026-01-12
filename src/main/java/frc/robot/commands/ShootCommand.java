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

    // Set shooter to close speed
    @Override
    public void initialize() {
        m_ShooterSubsystem.SetVelocity(kShootCloseSpeed);
    }

    // Run feeder only when shooter is in tolerance
    @Override
    public void execute() {
        if(m_ShooterSubsystem.AtVelocity()) {
            m_FeederSubsystem.setFeederSpeed(kFeederSpeed);
        }
        else {
            m_FeederSubsystem.Stop();
        }
    }

    // Stop both shooter and feeder
    @Override
    public void end(boolean interrupted) {
        m_ShooterSubsystem.Stop();
        m_FeederSubsystem.Stop();
    }

    // Never finishes on its own
    @Override
    public boolean isFinished() {
        return false;
    }
}
