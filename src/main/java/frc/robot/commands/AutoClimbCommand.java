package frc.robot.commands;

import static frc.robot.Constants.ClimbConstants.kClimbSpeed;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoClimbCommand extends Command {
    ClimbSubsystem m_ClimbSubsystem;
    DrivetrainSubsystem m_DrivetrainSubsystem;

    public AutoClimbCommand(ClimbSubsystem climbSubsystem, DrivetrainSubsystem drivetrainSubsystem) {
        m_ClimbSubsystem = climbSubsystem;
        m_DrivetrainSubsystem = drivetrainSubsystem;

        addRequirements(climbSubsystem, drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        // raise climb
        m_DrivetrainSubsystem.driveArcade(0,0);
        m_ClimbSubsystem.SetSpeed(kClimbSpeed);
        new WaitCommand(2.0);

        // drive against wall
        m_DrivetrainSubsystem.driveArcade(.25, 0);
        m_ClimbSubsystem.Stop();
        new WaitCommand(2.0);

        // lower climb
        m_DrivetrainSubsystem.driveArcade(0, 0);
        m_ClimbSubsystem.SetSpeed(- kClimbSpeed);
        new WaitCommand(2.0);

        // stop climb
        m_ClimbSubsystem.Stop();
    }

    @Override
    public void end(boolean interrupted) {
        m_ClimbSubsystem.Stop();
        m_DrivetrainSubsystem.driveArcade(0, 0);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
