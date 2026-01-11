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
    }
    
    public static final class ShooterConstants {
        // Motor ports
        public static final int kShooterMotorPort = 8;

        // PID & Feed forward
        public static final double kP = 0; // Proportional Gain
        public static final double kI = 0; // Integral Gain
        public static final double kD = 0; // Derivative Gain
        public static final double kV = 0; // Velocity Feedforward Gain

        // Tolerance
        public static final AngularVelocity kTolerance = RotationsPerSecond.of(5);

        // Current limits
        public static final Current kShooterMotorStatorCurrentLimit = Amps.of(120);
        public static final Current kShooterMotorSupplyCurrentLimit = Amps.of(60);

        // Shoot Speeds
        public static final double kShootCloseSpeed = .5;
    }

    public static final class FeederConstants{
        //Motor port
        public static final int kFeederPort = 9;
        
        // Speed Constant
        public static final double kFeederSpeed = 0.1;

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
        public static final int kIntakeRotationLeaderPort = 6;
        public static final int kIntakeRotationFollowerPort = 10;
        public static final int kIntakeWheelPort = 7;
        public static final int kIntakeRotationEncoderChannelA = 0;
        public static final int kIntakeRotationEncoderChannelB = 0;

        // Motor Configuration Constants
        public static final Current kSupplyCurrentLimit = Amps.of(60);
        public static final Current kIntakeRotationStatorCurrentLimit = Amps.of(120);
        public static final Current kIntakeRotationWheelStatorCurrentLimit = Amps.of(120);

        // Encoder Positions
        public static final int kEncoderHomePosition = 0;
        public static final int kEncoderOutPosition = 0;

        // Speed Constants
        public static final double kRotationSpeed = 0.02;
        public static final double kWheelSpeed = 0.02;
    }
}