package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import java.util.function.DoubleSupplier;

import static frc.robot.Constants.DrivetrainConstants.kLeftLeaderPort;
import static frc.robot.Constants.DrivetrainConstants.kLeftFollowerPort;
import static frc.robot.Constants.DrivetrainConstants.kRightLeaderPort;
import static frc.robot.Constants.DrivetrainConstants.kRightFollowerPort;

import com.revrobotics.spark.SparkBase.PersistMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkLowLevel.MotorType;

public class DrivetrainSubsystem extends SubsystemBase
{
    private final SparkMax leftLeader;
    private final SparkMax leftFollower;
    private final SparkMax rightLeader;
    private final SparkMax rightFollower;

    private final DifferentialDrive drive;
    
    public DrivetrainSubsystem()
    {
        // Initialize the SPARKs
        leftLeader = new SparkMax(kLeftLeaderPort, MotorType.kBrushless);
        leftFollower = new SparkMax(kLeftFollowerPort, MotorType.kBrushless);
        rightLeader = new SparkMax(kRightLeaderPort, MotorType.kBrushless);
        rightFollower = new SparkMax(kRightFollowerPort, MotorType.kBrushless);

        // Create new SPARK MAX configuration objects.
        SparkMaxConfig globalConfig = new SparkMaxConfig();
        SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
        SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
        SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();

        // Set default parameters and set left leader config
        globalConfig
            .smartCurrentLimit(50)
            .idleMode(IdleMode.kBrake);
        
        // Apply global config and invert since it is on the opposite side
        rightLeaderConfig
            .apply(globalConfig)
            .inverted(true);
        
        // Apply global config and set leader for follower mode
        leftFollowerConfig
            .apply(globalConfig)
            .follow(leftLeader);

        // Apply the global config and set the leader for follower mode
        rightFollowerConfig
            .apply(globalConfig)
            .follow(rightLeader);


        // Apply configuration
        // kResetSafeParameters is used to get to a known state.
        // kPersistParameters is used to ensure config
        leftLeader.configure(globalConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        leftFollower.configure(leftFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightLeader.configure(rightLeaderConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightFollower.configure(rightFollowerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        drive = new DifferentialDrive(leftLeader, rightLeader);
    }

    @Override
    public void periodic()
    {
        SmartDashboard.putNumber("Left Out", leftLeader.getAppliedOutput());
        SmartDashboard.putNumber("Right Out", rightLeader.getAppliedOutput());
    }

    // Command factory to create command to drive the robot with joystick inputs.
    public Command driveArcade(DoubleSupplier xSpeed, DoubleSupplier zRotation) {
        return this.run(() -> drive.arcadeDrive(xSpeed.getAsDouble(), zRotation.getAsDouble()));
    }
}