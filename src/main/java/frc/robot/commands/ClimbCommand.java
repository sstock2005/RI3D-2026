package frc.robot.commands;

import static frc.robot.Constants.ClimbConstants.kClimbLowerLimit;
import static frc.robot.Constants.ClimbConstants.kClimbUpperLimit;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbSubsystem;

public class ClimbCommand extends Command{
    ClimbSubsystem m_climbSubsystem;
    double m_speed;

    public ClimbCommand(ClimbSubsystem climbSubsystem, double speed) {
        m_climbSubsystem = climbSubsystem;
        m_speed = speed;
        addRequirements(climbSubsystem);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double pos = m_climbSubsystem.getPosition();

        if (m_speed > 0.0 &&
            pos >= kClimbUpperLimit) {
            m_climbSubsystem.setSpeed(0.0);
            return;
        }

        if (m_speed < 0.0 &&
            pos <= kClimbLowerLimit) {
            m_climbSubsystem.setSpeed(0.0);
            return;
        }

        m_climbSubsystem.setSpeed(m_speed);
    }

    @Override
    public void end(boolean interrupted) {
        m_climbSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}