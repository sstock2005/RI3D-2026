package frc.robot.commands;

import static frc.robot.Constants.IntakeConstants.kIntakeRotationMaxPosition;
import static frc.robot.Constants.IntakeConstants.kIntakeRotationMinPosition;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class MoveIntakeCommand extends Command {
    private final IntakeSubsystem m_intakeSubsystem;
    private final double m_speed;

    public MoveIntakeCommand(IntakeSubsystem subsystem, double speed) {
        m_intakeSubsystem = subsystem;
        m_speed = speed;
        addRequirements(subsystem);
    }

    @Override
    public void initialize() {}
    
    @Override
    public void execute() {
        double pos = m_intakeSubsystem.getLeftIntakePosition();

        if (m_speed > 0.0 &&
            pos >= kIntakeRotationMaxPosition) {
            m_intakeSubsystem.setIntakeRotation(0.0);
            return;
        }

        if (m_speed < 0.0 &&
            pos <= kIntakeRotationMinPosition) {
            m_intakeSubsystem.setIntakeRotation(0.0);
            return;
        }

        m_intakeSubsystem.setIntakeRotation(m_speed);
    }

    
    // Stop intake rotation on command end
    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.setIntakeRotation(0.0);
    }
    
    // Never finishes on its own
    @Override
    public boolean isFinished() {
        return false;
    }
}