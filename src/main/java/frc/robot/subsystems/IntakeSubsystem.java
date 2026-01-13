package frc.robot.subsystems;

import static frc.robot.Constants.IntakeConstants.kIntakeRotationLeftPort;
import static frc.robot.Constants.IntakeConstants.kIntakeRotationRightPort;
import static frc.robot.Constants.IntakeConstants.kIntakeWheelPort;
import static frc.robot.Constants.IntakeConstants.kSupplyCurrentLimit;
import static frc.robot.Constants.IntakeConstants.kIntakeRotationStatorCurrentLimit;
import static frc.robot.Constants.IntakeConstants.kIntakeRotationWheelStatorCurrentLimit;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    private TalonFX intakeRotationLeft;
    private TalonFX intakeRotationRight;
    private TalonFX intakeWheel;
    private TalonFXConfiguration intakeRotationLeftConfig;
    private TalonFXConfiguration intakeRotationRightConfig;
    private TalonFXConfiguration intakeWheelConfig;
    
    public IntakeSubsystem() {
        intakeRotationLeft = new TalonFX(kIntakeRotationLeftPort);
        intakeRotationRight = new TalonFX(kIntakeRotationRightPort);
        intakeWheel = new TalonFX(kIntakeWheelPort);

        intakeRotationLeftConfig = new TalonFXConfiguration();
        intakeRotationRightConfig = new TalonFXConfiguration();
        intakeWheelConfig = new TalonFXConfiguration();

        // Set Brake Mode and Current Limits
        intakeRotationLeftConfig
            .withMotorOutput(
                new MotorOutputConfigs()
                    .withNeutralMode(NeutralModeValue.Brake)
                    .withInverted(InvertedValue.CounterClockwise_Positive)
            )
            .withCurrentLimits(
                new CurrentLimitsConfigs()
                    .withSupplyCurrentLimit(kSupplyCurrentLimit)
                    .withStatorCurrentLimit(kIntakeRotationStatorCurrentLimit)
                    .withSupplyCurrentLimitEnable(true)
                    .withStatorCurrentLimitEnable(true)
            );

        // Right intake motor is inverted
        intakeRotationRightConfig
            .withMotorOutput(
                new MotorOutputConfigs()
                    .withNeutralMode(NeutralModeValue.Brake)
                    .withInverted(InvertedValue.Clockwise_Positive) // Inverted
            )
            .withCurrentLimits(
                new CurrentLimitsConfigs()
                    .withSupplyCurrentLimit(kSupplyCurrentLimit)
                    .withStatorCurrentLimit(kIntakeRotationStatorCurrentLimit)
                    .withSupplyCurrentLimitEnable(true)
                    .withStatorCurrentLimitEnable(true)
            );

        intakeWheelConfig
            .withMotorOutput(
                new MotorOutputConfigs()
                    .withNeutralMode(NeutralModeValue.Brake)
                    .withInverted(InvertedValue.Clockwise_Positive)
            )
            .withCurrentLimits(
                new CurrentLimitsConfigs()
                    .withSupplyCurrentLimit(kSupplyCurrentLimit)
                    .withStatorCurrentLimit(kIntakeRotationWheelStatorCurrentLimit)
                    .withSupplyCurrentLimitEnable(true)
                    .withStatorCurrentLimitEnable(true)
            );

        intakeRotationLeft.getConfigurator().apply(intakeRotationLeftConfig);
        intakeRotationRight.getConfigurator().apply(intakeRotationRightConfig);
        intakeWheel.getConfigurator().apply(intakeWheelConfig);
    }

    // Set speed for both intake rotation motors (% of max)
    public void setIntakeRotation(double speed) {
        intakeRotationLeft.set(speed);
        intakeRotationRight.set(speed);
    }

    // Set speed for intake wheel motor (% of max)
    public void setIntakeWheel(double speed) {
        intakeWheel.set(speed);
    }

    // Stop intake wheel motor
    public void stopIntakeRotation() {
        intakeRotationLeft.stopMotor();
        intakeRotationRight.stopMotor();
    }
    
    public double getLeftIntakePosition() {
        return intakeRotationLeft.getPosition().getValueAsDouble();
    }

    @Override
    public void periodic() {}
}
