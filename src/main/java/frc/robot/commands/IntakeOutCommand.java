package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeOutCommand extends Command {
    private final IntakeSubsystem m_intakeSubsystem;
    
    public IntakeOutCommand(IntakeSubsystem subsystem) {
        m_intakeSubsystem = subsystem;
        addRequirements(subsystem);
    }
    
    @Override
    public void initialize() {}
    
    @Override
    public void execute() {
        // While intake is not to out position, go towards out position
        // Includes a grace of 10 so we don't get too close
        // Assumes encoder "home" is 0 and out position is towards positive
        // Assumes intake rotates positive
        if (m_intakeSubsystem.getEncoder().get() < Constants.IntakeConstants.kEncoderOutPosition - 10) {
            m_intakeSubsystem.setIntakeRotation(Constants.IntakeConstants.kRotationSpeed);
        }
        else {
            m_intakeSubsystem.setIntakeRotation(0.0);
        }
    }
    
    @Override
    public void end(boolean interrupted) {
        // Just in case
        m_intakeSubsystem.setIntakeRotation(0.0);
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}