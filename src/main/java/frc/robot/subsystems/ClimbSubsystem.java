/*package frc.robot.subsystems;

import static frc.robot.Constants.ClimbConstants.kClimbLeaderPort;
import static frc.robot.Constants.ClimbConstants.kClimbFollowerPort;
import static frc.robot.Constants.ClimbConstants.kClimbStatorCurrentLimit;
import static frc.robot.Constants.ClimbConstants.kClimbSupplyCurrentLimit;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
    private TalonFX climbLeader;
    private TalonFX climbFollower;
    private TalonFXConfiguration climbMotorConfig;
    private VelocityTorqueCurrentFOC climbController;
    private double targetRps = 0.0;

    public ClimbSubsystem() {
        // configure motor current limit with brake mode
        climbMotorConfig = new TalonFXConfiguration()
            .withCurrentLimits(
                new CurrentLimitsConfigs()
                    .withStatorCurrentLimit(kClimbStatorCurrentLimit)
                    .withSupplyCurrentLimit(kClimbSupplyCurrentLimit)
                    .withStatorCurrentLimitEnable(true)
                    .withSupplyCurrentLimitEnable(true)
            )
            .withMotorOutput(
                new MotorOutputConfigs()
                    .withInverted(InvertedValue.Clockwise_Positive)
                    .withNeutralMode(NeutralModeValue.Brake)
            );
            
        // create motor and apply config
        climbLeader = new TalonFX(kClimbLeaderPort);
        climbLeader.getConfigurator().apply(climbMotorConfig);
        climbFollower = new TalonFX(kClimbFollowerPort);
        climbFollower.getConfigurator().apply(climbMotorConfig);
        climbFollower.setControl(new Follower(kClimbLeaderPort, MotorAlignmentValue.Opposed));

        // enable watchdog
        climbLeader.setSafetyEnabled(true);
        climbFollower.setSafetyEnabled(true);
    }

    public void Stop() {
        targetRps = 0;
        climbLeader.stopMotor();
    }

    @Override
    public void periodic() {
        climbLeader.setControl(climbController.withVelocity(targetRps));
    }

    public void SetSpeed(double rps) {
        targetRps = rps;
        climbLeader.setControl(climbController.withVelocity(targetRps));
    }
}
*/