package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class Vision {
    Pose2d pose1 = new Pose2d(0.0, 0.0, new Rotation2d(0.0)); // we can define setpoints (pose2ds) here

    Pose2d[] aprilPose = new Pose2d[23];

    Pose2d april1 = new Pose2d(657.37, 25.80, new Rotation2d(0.0));
    Pose2d april2 = new Pose2d(657.37, 291.20, new Rotation2d(0.0));
    Pose2d april3 = new Pose2d(455.15, 317.15, new Rotation2d(0.0));
    Pose2d april4 = new Pose2d(365.20, 241.64, new Rotation2d(0.0));
    Pose2d april5 = new Pose2d(365.20, 75.39, new Rotation2d(0.0));
    Pose2d april6 = new Pose2d(530.49, 130.17, new Rotation2d(0.0));
    Pose2d april7 = new Pose2d(546.87, 158.50, new Rotation2d(0.0));
    Pose2d april8 = new Pose2d(530.49, 186.83, new Rotation2d(0.0));
    Pose2d april9 = new Pose2d(497.77, 186.83, new Rotation2d(0.0));
    Pose2d april10 = new Pose2d(481.39, 158.50, new Rotation2d(0.0));
    Pose2d april11 = new Pose2d(497.77, 130.17, new Rotation2d(0.0));
    Pose2d april12 = new Pose2d(33.51, 25.80, new Rotation2d(0.0));
    Pose2d april13 = new Pose2d(33.51, 291.20, new Rotation2d(0.0));
    Pose2d april14 = new Pose2d(325.68, 241.64, new Rotation2d(0.0));
    Pose2d april15 = new Pose2d(325.68, 75.39, new Rotation2d(0.0));
    Pose2d april16 = new Pose2d(235.73, -0.15, new Rotation2d(0.0));
    Pose2d april17 = new Pose2d(160.39, 130.17, new Rotation2d(0.0));
    Pose2d april18 = new Pose2d(144.00, 158.50, new Rotation2d(0.0));
    Pose2d april19 = new Pose2d(160.39, 186.83, new Rotation2d(0.0));
    Pose2d april20 = new Pose2d(193.10, 186.83, new Rotation2d(0.0));
    Pose2d april21 = new Pose2d(209.49, 158.50, new Rotation2d(0.0));
    Pose2d april22 = new Pose2d(193.10, 130.17, new Rotation2d(0.0));

    aprilPose[0] = null;
    aprilPose[1] = april1;
    aprilPose[2] = april2;
    aprilPose[3] = april3;
    aprilPose[4] = april4;
    aprilPose[5] = april5;
    aprilPose[6] = april6;
    aprilPose[7] = april7;
    aprilPose[8] = april8;
    aprilPose[9] = april9;
    aprilPose[10] = april10;
    aprilPose[11] = april11;
    aprilPose[12] = april12;
    aprilPose[13] = april13;
    aprilPose[14] = april14;
    aprilPose[15] = april15;
    aprilPose[16] = april16;
    aprilPose[17] = april17;
    aprilPose[18] = april18;
    aprilPose[19] = april19;
    aprilPose[20] = april20;
    aprilPose[21] = april21;
    aprilPose[22] = april22;

}
