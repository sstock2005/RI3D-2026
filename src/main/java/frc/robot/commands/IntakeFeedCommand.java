package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeFeedCommand extends Command {
    private final IntakeSubsystem m_intakeSubsystem;
    // private final FeedSubsystem m_feedSubsystem;

    /*
     * Design Question: Do we turn the feed motor while we intake?
     */
    public IntakeFeedCommand(IntakeSubsystem intakeSubsystem /*, FeedSubsystem feedSubsystem */) {
        m_intakeSubsystem = intakeSubsystem;
        // m_feedSubsystem = feedSubsystem;
        addRequirements(intakeSubsystem /*, feedSubsystem */);
    }
    
    @Override
    public void initialize() {}
    
    @Override
    public void execute() {
        m_intakeSubsystem.setIntakeWheel(Constants.IntakeConstants.kWheelSpeed);
        // m_feedSubsystem.setFeed(Constants.FeedConstants.kFeedSpeed);
    }
    
    @Override
    public void end(boolean interrupted) {
        m_intakeSubsystem.setIntakeWheel(0.0);
        // m_feedSubsystem.setFeed(Constants.FeedConstants.kFeedSpeed);
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}