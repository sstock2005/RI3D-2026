package frc.robot.subsystems;

import static frc.robot.Constants.FeederConstants.kFeederMotorStatorCurrentLimit;
import static frc.robot.Constants.FeederConstants.kFeederMotorSupplyCurrentLimit;
import static frc.robot.Constants.FeederConstants.kFeederPort;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class FeederSubsystem extends SubsystemBase {
    private TalonFX feeder;
    private TalonFXConfiguration feederConfig;

    public FeederSubsystem(){
        feeder = new TalonFX(kFeederPort);

        // Setup feeder motor with current limits, non inverted, brake mode.
        feederConfig = new TalonFXConfiguration()
            .withCurrentLimits(
                new CurrentLimitsConfigs()
                    .withStatorCurrentLimit(kFeederMotorStatorCurrentLimit)
                    .withSupplyCurrentLimit(kFeederMotorSupplyCurrentLimit)
                    .withStatorCurrentLimitEnable(true)
                    .withSupplyCurrentLimitEnable(true)
            )
            .withMotorOutput(
                new MotorOutputConfigs()
                    .withNeutralMode(NeutralModeValue.Brake)
                    .withInverted(InvertedValue.Clockwise_Positive)
            )
        ;

        //apply configs
        feeder.getConfigurator().apply(feederConfig);

        // enable motor watchdog
        feeder.setSafetyEnabled(false);
    }

    // set motor speed (% of max)
    public void setFeederSpeed(double speed){
        feeder.set(speed);
    }

    public void Stop(){
        feeder.stopMotor();
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Feeder Set Speed", feeder.get());
    }
}
