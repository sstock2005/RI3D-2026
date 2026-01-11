package frc.robot.subsystems;

import static frc.robot.Constants.ShooterConstants.kShooterLeaderPort;
import static frc.robot.Constants.ShooterConstants.kP;
import static frc.robot.Constants.ShooterConstants.kShooterFollowerPort;
import static frc.robot.Constants.ShooterConstants.kI;
import static frc.robot.Constants.ShooterConstants.kD;
import static frc.robot.Constants.ShooterConstants.kTolerance;
import static frc.robot.Constants.ShooterConstants.kShooterMotorStatorCurrentLimit;
import static frc.robot.Constants.ShooterConstants.kShooterMotorSupplyCurrentLimit;

import com.ctre.phoenix6.BaseStatusSignal;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.MotorAlignmentValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
    private TalonFX shooterLeader;
    private TalonFXConfiguration shooterLeaderConfig;
    private TalonFX shooterFollower;
    private TalonFXConfiguration shooterFollowerConfig;
    private VelocityTorqueCurrentFOC shooterController;
    private double targetRps = 0.0;

    public ShooterSubsystem() {
        shooterLeader = new TalonFX(kShooterLeaderPort);
        shooterFollower = new TalonFX(kShooterFollowerPort);        
        shooterController = new VelocityTorqueCurrentFOC(0.0);

        // Setup leader w/ current limits, non inverted, coast mode, pid controller.
        shooterLeaderConfig = new TalonFXConfiguration()
            .withCurrentLimits(
                new CurrentLimitsConfigs()
                    .withStatorCurrentLimit(kShooterMotorStatorCurrentLimit)
                    .withSupplyCurrentLimit(kShooterMotorSupplyCurrentLimit)
                    .withStatorCurrentLimitEnable(true)
                    .withSupplyCurrentLimitEnable(true)
            )
            .withMotorOutput(
                new MotorOutputConfigs()
                    .withNeutralMode(NeutralModeValue.Coast)
                    .withInverted(InvertedValue.Clockwise_Positive)
            )
            .withSlot0(
                new Slot0Configs()
                    .withKP(kP)
                    .withKI(kI)
                    .withKD(kD)
            );

        // setup follower motor to follow inverted
        shooterFollowerConfig = new TalonFXConfiguration()
            .withMotorOutput(new MotorOutputConfigs().withNeutralMode(NeutralModeValue.Coast));
            
        // apply configs
        shooterLeader.getConfigurator().apply(shooterLeaderConfig);
        shooterFollower.getConfigurator().apply(shooterFollowerConfig);

        // set motor to follow
        shooterFollower.setControl(new Follower(shooterLeader.getDeviceID(), MotorAlignmentValue.Opposed));

        // enable motor watchdog
        shooterLeader.setSafetyEnabled(true);
        shooterFollower.setSafetyEnabled(true);
    }

    public void SetVelocity(double rotationsPerSecond) {
        targetRps = rotationsPerSecond;
        shooterLeader.setControl(shooterController.withVelocity(rotationsPerSecond));
    }

    public void Stop() {
        targetRps = 0.0;
        shooterLeader.stopMotor();
    }

    public boolean AtVelocity() {
        // Make sure encoder data is fresh
        BaseStatusSignal.refreshAll(shooterLeader.getVelocity(), shooterLeader.getAcceleration());

        // Get acceleration compensated velocity
        var v = BaseStatusSignal.getLatencyCompensatedValue(
            shooterLeader.getVelocity(), shooterLeader.getAcceleration());

        // Return if the rps difference of target vs actual is less than tolerance
        return Math.abs(v.in(RotationsPerSecond) - targetRps) < kTolerance.in(RotationsPerSecond);
    }

    @Override
    public void periodic() {
        shooterLeader.setControl(shooterController.withVelocity(targetRps));
    }
}
