package frc.robot.subsystems;

import static frc.robot.Constants.ClimbConstants.kClimbMotorPort;
import static frc.robot.Constants.ClimbConstants.kClimbStatorCurrentLimit;
import static frc.robot.Constants.ClimbConstants.kClimbSupplyCurrentLimit;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimbSubsystem extends SubsystemBase {
    private TalonFX climbMotor;
    private TalonFXConfiguration climbMotorConfig;

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
                    .withInverted(InvertedValue.CounterClockwise_Positive) // Inverted
                    .withNeutralMode(NeutralModeValue.Brake)
            );
            
        // create motor and apply config
        climbMotor = new TalonFX(kClimbMotorPort);
        climbMotor.getConfigurator().apply(climbMotorConfig);
    }

    public void Stop() {
        climbMotor.stopMotor();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Climb Encoder Tick", climbMotor.getPosition().getValueAsDouble());
    }

    public void SetSpeed(double speed) {
        climbMotor.set(speed);
    }

    public double getPosition() {
        return climbMotor.getPosition().getValueAsDouble();
    }
}