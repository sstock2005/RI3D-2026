package frc.robot.subsystems;

import static frc.robot.Constants.ShooterConstants.kShooterMotorPort;
import static frc.robot.Constants.ShooterConstants.kP;
import static frc.robot.Constants.ShooterConstants.kI;
import static frc.robot.Constants.ShooterConstants.kD;
import static frc.robot.Constants.ShooterConstants.kTolerance;
import static frc.robot.Constants.ShooterConstants.kV;
import static frc.robot.Constants.ShooterConstants.kShooterMotorStatorCurrentLimit;
import static frc.robot.Constants.ShooterConstants.kShooterMotorSupplyCurrentLimit;

import com.ctre.phoenix6.BaseStatusSignal;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
    private TalonFX shooterMotor;
    private TalonFXConfiguration shooterMotorConfig;
    private VelocityVoltage shooterController;
    private double targetRps = 0.0;

    public ShooterSubsystem() {
        shooterMotor = new TalonFX(kShooterMotorPort);       

        // Setup leader w/ current limits, non inverted, coast mode, pid controller.
        shooterMotorConfig = new TalonFXConfiguration()
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
                    .withKV(kV)
            );
            
        // apply configs and pid controller
        shooterMotor.getConfigurator().apply(shooterMotorConfig);
        shooterController = new VelocityVoltage(0.0).withSlot(0);

        // enable motor watchdog
        shooterMotor.setSafetyEnabled(true);
    }

    // Set shooter wheel velocity in rotations per second
    public void SetVelocity(double rotationsPerSecond) {
        targetRps = rotationsPerSecond;
        shooterMotor.setControl(shooterController.withVelocity(rotationsPerSecond));
    }

    // Stop the shooter wheel
    public void Stop() {
        targetRps = 0.0;
        shooterMotor.stopMotor();
    }

    // Return if shooter is within the tolerance in constants
    public boolean AtVelocity() {
        // Make sure encoder data is fresh
        BaseStatusSignal.refreshAll(shooterMotor.getVelocity(), shooterMotor.getAcceleration());

        // Get acceleration compensated velocity
        var v = BaseStatusSignal.getLatencyCompensatedValue(
            shooterMotor.getVelocity(), shooterMotor.getAcceleration());

        // Return if the rps difference of target vs actual is less than tolerance
        return Math.abs(v.in(RotationsPerSecond) - targetRps) < kTolerance.in(RotationsPerSecond);
    }

    // continue setting velocity to prevent watchdog trigger
    @Override
    public void periodic() {
        shooterMotor.setControl(shooterController.withVelocity(targetRps));
    }
}
