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
    
    // Move intake rotation while respecting position limits
    @Override
    public void execute() {
        double currentLeftPos = m_intakeSubsystem.getLeftIntakePosition();
        double currentRightPos = m_intakeSubsystem.getRightIntakePosition();

        if ((m_speed > 0 && (currentLeftPos >= kIntakeRotationMaxPosition || currentRightPos >= kIntakeRotationMaxPosition)) ||
            (m_speed < 0 && (currentLeftPos <= kIntakeRotationMinPosition || currentRightPos <= kIntakeRotationMinPosition))) {
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