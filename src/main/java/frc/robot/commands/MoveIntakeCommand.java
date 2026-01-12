package frc.robot.commands;

import frc.robot.Constants;
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
        double currentLeftPos = m_intakeSubsystem.getLeftIntakePosition();
        double currentRightPos = m_intakeSubsystem.getRightIntakePosition();

        if ((m_speed > 0 && (currentLeftPos >= Constants.IntakeConstants.kIntakeRotationMaxPosition || currentRightPos >= Constants.IntakeConstants.kIntakeRotationMaxPosition)) ||
            (m_speed < 0 && (currentLeftPos <= Constants.IntakeConstants.kIntakeRotationMinPosition || currentRightPos <= Constants.IntakeConstants.kIntakeRotationMinPosition))) {
            m_intakeSubsystem.setIntakeRotation(0.0);
            return;
        }
        
        m_intakeSubsystem.setIntakeRotation(m_speed);
    }
    
    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.setIntakeRotation(0.0);
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}