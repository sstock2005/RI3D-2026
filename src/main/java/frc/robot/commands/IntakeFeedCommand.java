package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeFeedCommand extends Command {
    private final IntakeSubsystem m_intakeSubsystem;
    // private final FeedSubsystem m_feedSubsystem;
    private final boolean m_inverted;

    /*
     * Design Question: Do we turn the feed motor while we intake?
     */
    public IntakeFeedCommand(IntakeSubsystem intakeSubsystem /*, FeedSubsystem feedSubsystem */, boolean inverted) {
        m_intakeSubsystem = intakeSubsystem;
        // m_feedSubsystem = feedSubsystem;
        m_inverted = inverted;
        addRequirements(intakeSubsystem /*, feedSubsystem */);
    }
    
    @Override
    public void initialize() {}
    
    // Run intake wheel in desired direction
    @Override
    public void execute() {
        if (m_inverted) {
            m_intakeSubsystem.setIntakeWheel(Constants.IntakeConstants.kWheelSpeed);
            // m_feedSubsystem.setFeed(Constants.FeedConstants.kFeedSpeed);
        } else {
            m_intakeSubsystem.setIntakeWheel(-Constants.IntakeConstants.kWheelSpeed);
            // m_feedSubsystem.setFeed(-Constants.FeedConstants.kFeedSpeed);
        }
    }
    
    // Stop the intake wheel
    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.setIntakeWheel(0.0);
        // m_feedSubsystem.setFeed(Constants.FeedConstants.kFeedSpeed);
    }
    
    // Never finishes on its own
    @Override
    public boolean isFinished() {
        return false;
    }
}