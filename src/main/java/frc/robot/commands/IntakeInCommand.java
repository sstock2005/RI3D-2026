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
        m_intakeSubsystem.setIntakePosition(Constants.IntakeConstants.kEncoderHomePosition);
    }
    
    @Override
    public void end(boolean interrupted) {}
    
    @Override
    public boolean isFinished() {
        return false;
    }
}