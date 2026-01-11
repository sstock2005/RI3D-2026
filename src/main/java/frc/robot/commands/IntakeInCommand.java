package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeInCommand extends Command {
    private final IntakeSubsystem m_intakeSubsystem;
    
    public IntakeInCommand(IntakeSubsystem subsystem) {
        m_intakeSubsystem = subsystem;
        addRequirements(subsystem);
    }
    
    @Override
    public void initialize() {}
    
    @Override
    public void execute() {
        // While intake is not to home position, go towards home position
        // Includes a grace of 10 so we don't get too close
        // Assumes encoder "home" is 0
        // Assumes intake rotates positive so we give it negative speed to rotation towards home
        if (m_intakeSubsystem.getEncoder().get() > Constants.IntakeConstants.kEncoderHomePosition + 10) {
            m_intakeSubsystem.setIntakeRotation(-Constants.IntakeConstants.kRotationSpeed);
        }
        else {
            m_intakeSubsystem.setIntakeRotation(0.0);
        }
    }
    
    @Override
    public void end(boolean interrupted) {}
    
    @Override
    public boolean isFinished() {
        return false;
    }
}