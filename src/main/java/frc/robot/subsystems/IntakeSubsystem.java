package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
    TalonFX intakeRotationLeft;
    TalonFX intakeRotationRight;
    TalonFX intakeWheel;
    
    public IntakeSubsystem() {
        intakeRotationLeft = new TalonFX(Constants.IntakeConstants.kIntakeRotationLeftPort);
        intakeRotationRight = new TalonFX(Constants.IntakeConstants.kIntakeRotationRightPort);
        intakeWheel = new TalonFX(Constants.IntakeConstants.kIntakeWheelPort);

        var intakeRotationLeftConfig = new TalonFXConfiguration();
        var intakeRotationRightConfig = new TalonFXConfiguration();
        var intakeWheelConfig = new TalonFXConfiguration();

        // Set Brake Mode and Current Limits
        intakeRotationLeftConfig
            .withMotorOutput(
                new MotorOutputConfigs()
                    .withNeutralMode(NeutralModeValue.Brake)
                    .withInverted(InvertedValue.Clockwise_Positive)
            )
            .withCurrentLimits(
                new CurrentLimitsConfigs()
                    .withSupplyCurrentLimit(Constants.IntakeConstants.kSupplyCurrentLimit)
                    .withStatorCurrentLimit(Constants.IntakeConstants.kIntakeRotationStatorCurrentLimit)
                    .withSupplyCurrentLimitEnable(true)
                    .withStatorCurrentLimitEnable(true)
            );

        intakeRotationRightConfig
            .withMotorOutput(
                new MotorOutputConfigs()
                    .withNeutralMode(NeutralModeValue.Brake)
                    .withInverted(InvertedValue.CounterClockwise_Positive)
            )
            .withCurrentLimits(
                new CurrentLimitsConfigs()
                    .withSupplyCurrentLimit(Constants.IntakeConstants.kSupplyCurrentLimit)
                    .withStatorCurrentLimit(Constants.IntakeConstants.kIntakeRotationStatorCurrentLimit)
                    .withSupplyCurrentLimitEnable(true)
                    .withStatorCurrentLimitEnable(true)
            );

        intakeWheelConfig
            .withMotorOutput(
                new MotorOutputConfigs()
                    .withNeutralMode(NeutralModeValue.Brake)
                    .withInverted(InvertedValue.Clockwise_Positive) // Inverted
            )
            .withCurrentLimits(
                new CurrentLimitsConfigs()
                    .withSupplyCurrentLimit(Constants.IntakeConstants.kSupplyCurrentLimit)
                    .withStatorCurrentLimit(Constants.IntakeConstants.kIntakeRotationWheelStatorCurrentLimit)
                    .withSupplyCurrentLimitEnable(true)
                    .withStatorCurrentLimitEnable(true)
            );

        intakeRotationLeft.getConfigurator().apply(intakeRotationLeftConfig);
        intakeRotationRight.getConfigurator().apply(intakeRotationRightConfig);
        intakeWheel.getConfigurator().apply(intakeWheelConfig);
    }

    public void setIntakeRotation(double speed) {
        intakeRotationLeft.set(speed);
        intakeRotationRight.set(speed);
    }

    public void setIntakeWheel(double speed) {
        intakeWheel.set(speed);
    }

    public double getLeftIntakePosition() {
        return intakeRotationLeft.getPosition().getValueAsDouble();
    }

    public double getRightIntakePosition() {
        return intakeRotationRight.getPosition().getValueAsDouble();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Intake Wheel Set Speed", intakeWheel.get());
        SmartDashboard.putNumber("Left Intake Rotation Set Speed", intakeRotationLeft.get());
        SmartDashboard.putNumber("Right Intake Rotation Set Speed", intakeRotationRight.get());
        SmartDashboard.putNumber("Left Intake Rotation Position", intakeRotationLeft.getPosition().getValueAsDouble());
        SmartDashboard.putNumber("Right Intake Rotation Position", intakeRotationRight.getPosition().getValueAsDouble());
    }
}
