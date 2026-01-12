package frc.robot;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;

public class Constants {
    public static final int kDriverControllerPort = 0;

    public static final class DrivetrainConstants {
        public static final int kLeftLeaderPort = 2;
        public static final int kLeftFollowerPort = 3;
        public static final int kRightLeaderPort = 4;
        public static final int kRightFollowerPort = 5;
        public static final double kStickDeadband = 0.1;

        // Scaling factors for teleop control
        public static final double kDriveScaling = 0.5;
        public static final double kRotationScaling = 0.5;
    }
    
    public static final class ShooterConstants {
        // Motor ports
        public static final int kShooterMotorPort = 8;

        // PID & Feed forward
        public static final double kP = 0; // Proportional Gain
        public static final double kI = 0; // Integral Gain
        public static final double kD = 0; // Derivative Gain
        public static final double kV = 0.12; // Velocity Feedforward Gain

        // Toleranced
        public static final AngularVelocity kTolerance = RotationsPerSecond.of(5);

        // Current limits
        public static final Current kShooterMotorStatorCurrentLimit = Amps.of(120);
        public static final Current kShooterMotorSupplyCurrentLimit = Amps.of(60);

        // Shoot Speeds
        public static final double kShootCloseSpeed = 50;
    }

    public static final class FeederConstants{
        //Motor port
        public static final int kFeederPort = 9;
        
        // Speed Constant
        public static final double kFeederSpeed = 1;

        // Current limits
        public static final Current kFeederMotorStatorCurrentLimit = Amps.of(120);
        public static final Current kFeederMotorSupplyCurrentLimit = Amps.of(40);
    }

    public static final class ClimbConstants {
        public static final int kClimbMotorPort = 11;

        // Current limits
        public static final int kClimbStatorCurrentLimit = 120;
        public static final int kClimbSupplyCurrentLimit = 60;

        // climb motor run speed
        public static final double kClimbSpeed = .1;

        // motor soft limits
        public static final int kClimbUpperLimit = 0;
        public static final int kClimbLowerLimit = 0;
    }

    public static final class IntakeConstants {
        // Port Constants
        public static final int kIntakeRotationRightPort = 6;
        public static final int kIntakeRotationLeftPort = 10;
        public static final int kIntakeWheelPort = 7;

        // Motor Configuration Constants
        public static final Current kSupplyCurrentLimit = Amps.of(60);
        public static final Current kIntakeRotationStatorCurrentLimit = Amps.of(120);
        public static final Current kIntakeRotationWheelStatorCurrentLimit = Amps.of(120);

        // Speed Constants
        public static final double kRotationSpeed = 0.05;
        public static final double kWheelSpeed = 0.8;

        public static final double kS = 0.25; // Add 0.25 V output to overcome static friction
        public static final double kV = 0.12; // A velocity target of 1 rps results in 0.12 V output
        public static final double kA = 0.01; // An acceleration of 1 rps/s requires 0.01 V output
        public static final double kP = 4.8; // A position error of 2.5 rotations results in 12 V output
        public static final double kI = 0; // no output for integrated erro
        public static final double kD = 0.1; // A velocity error of 1 rps results in 0.1 V output
        public static final int velocity = 1;
        public static final int acceleration = 2;

        // Intake Rotation Limits
        public static final double kIntakeRotationMaxPosition = 5.25;
        public static final double kIntakeRotationMinPosition = 0.0;
    }
}