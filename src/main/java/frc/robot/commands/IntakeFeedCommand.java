package frc.robot.commands;

import static frc.robot.Constants.IntakeConstants.kWheelSpeed;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeFeedCommand extends Command {
    private final IntakeSubsystem m_intakeSubsystem;
    private final boolean m_inverted;

    public IntakeFeedCommand(IntakeSubsystem intakeSubsystem, boolean inverted) {
        m_intakeSubsystem = intakeSubsystem;
        m_inverted = inverted;
        addRequirements(intakeSubsystem);
    }
    
    @Override
    public void initialize() {}
    
    @Override
    public void execute() {
        if (m_inverted) {
            m_intakeSubsystem.setIntakeWheel(kWheelSpeed);
        } else {
            m_intakeSubsystem.setIntakeWheel(-kWheelSpeed);
        }
    }
    
    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.setIntakeWheel(0.0);
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}