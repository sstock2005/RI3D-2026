package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Amps;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase 
{
    TalonFX intakeRotation;
    TalonFX intakeWheel;

    Encoder rotationEncoder;
    
    public IntakeSubsystem() {
        intakeRotation = new TalonFX(Constants.IntakeConstants.kIntakeRotationPort);
        intakeWheel = new TalonFX(Constants.IntakeConstants.kIntakeWheelPort);
        rotationEncoder = new Encoder(Constants.IntakeConstants.kIntakeRotationEncoderChannelA, Constants.IntakeConstants.kIntakeRotationEncoderChannelB);

        var intakeRotationConfig = new TalonFXConfiguration();
        var intakeWheelConfig = new TalonFXConfiguration();

        // Set Brake Mode and Current Limits
        intakeRotationConfig
            .withMotorOutput(
                new MotorOutputConfigs()
                    .withNeutralMode(NeutralModeValue.Brake)
            )
            .withCurrentLimits(
                new CurrentLimitsConfigs()
                    .withStatorCurrentLimit(Amps.of(Constants.IntakeConstants.kIntakeRotationStatorCurrentLimit))
                    .withStatorCurrentLimitEnable(true)
                    .withSupplyCurrentLimit(Amps.of(Constants.IntakeConstants.kSupplyCurrentLimit))
                    .withSupplyCurrentLimitEnable(true)
            );

        intakeWheelConfig
            .withMotorOutput(
                new MotorOutputConfigs()
                    .withNeutralMode(NeutralModeValue.Brake)
            )
            .withCurrentLimits(
                new CurrentLimitsConfigs()
                    .withStatorCurrentLimit(Amps.of(Constants.IntakeConstants.kIntakeRotationWheelStatorCurrentLimit))
                    .withStatorCurrentLimitEnable(true)
                    .withSupplyCurrentLimit(Amps.of(Constants.IntakeConstants.kSupplyCurrentLimit))
                    .withSupplyCurrentLimitEnable(true)
            );

        intakeRotation.getConfigurator().apply(intakeRotationConfig);
        intakeWheel.getConfigurator().apply(intakeWheelConfig);

        // The Motor Safety feature acts as a watchdog timer for an individual motor. 
        // It operates by maintaining a timer that tracks how long it has been since the feed() 
        // method has been called for that actuator. Code in the Driver Station class initiates a 
        // comparison of these timers to the timeout values for any actuator with safety 
        // enabled every 5 received packets (100ms nominal). 
        intakeRotation.setSafetyEnabled(true);
        intakeWheel.setSafetyEnabled(true);
    }

    public void setIntakeRotation(double speed) {
        intakeRotation.set(speed);
    }

    public void setIntakeWheel(double speed) {
        intakeWheel.set(speed);
    }

    public Encoder getEncoder() {
        return rotationEncoder;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Intake Wheel Set Speed", intakeWheel.get());
        SmartDashboard.putNumber("Intake Rotation Set Speed", intakeRotation.get());
    }
}
