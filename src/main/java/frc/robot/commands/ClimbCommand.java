package frc.robot.commands;

import static frc.robot.Constants.ClimbConstants.kClimbSpeed;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbCommand extends Command{
    ClimbSubsystem m_ClimbSubsystem;
    int m_direction;

    public ClimbCommand(ClimbSubsystem climbSubsystem, int direction) {
        m_ClimbSubsystem = climbSubsystem;
        m_direction = direction;

        addRequirements(climbSubsystem);
    }

    @Override
    public void initialize() {
        m_ClimbSubsystem.SetSpeed(m_direction * kClimbSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        m_ClimbSubsystem.Stop();
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
