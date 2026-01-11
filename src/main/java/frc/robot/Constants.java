package frc.robot;

public class Constants {
    public static final int kDriverControllerPort = 0;

    public static final class DrivetrainConstants {
        public static final int kLeftLeaderPort = 2;
        public static final int kLeftFollowerPort = 3;
        public static final int kRightLeaderPort = 4;
        public static final int kRightFollowerPort = 5;
        public static final double kStickDeadband = 0.1;
    }

    public static final class IntakeConstants {
        // Port Constants
        public static final int kIntakeRotationPort = 0;
        public static final int kIntakeWheelPort = 0;
        public static final int kIntakeRotationEncoderChannelA = 0;
        public static final int kIntakeRotationEncoderChannelB = 0;

        // Motor Configuration Constants
        public static final int kSupplyCurrentLimit = 60;
        public static final int kIntakeRotationStatorCurrentLimit = 120;
        public static final int kIntakeRotationWheelStatorCurrentLimit = 120;
    }
}