package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class MoveToPose extends Command {

  private Pose2d targetPose;
  private final Drivetrain m_drivetrain;
  PIDController xController = new PIDController(10, 0, 0);
  PIDController yController = new PIDController(10, 0, 0);
  PIDController thetaController = new PIDController(4.5, 0, 3);
  double xSpeed;
  double ySpeed;
  double thetaSpeed;

  /**
   * Constructs a MoveToPose command.
   *
   * @param targetPose the target position of the robot
   */
  public MoveToPose(Drivetrain drivetrain, Pose2d targetPose) {
    addRequirements(drivetrain);
    m_drivetrain = drivetrain;
    this.targetPose = targetPose;
  }

  @Override
  public void initialize() {
    SmartDashboard.putNumber("currentx", m_drivetrain.getPose().getX());
    SmartDashboard.putNumber("currenty", m_drivetrain.getPose().getY());
    SmartDashboard.putNumber("targetx", targetPose.getX());
    SmartDashboard.putNumber("targety", targetPose.getY());
  }

  @Override
  public void execute() {
    xSpeed = xController.calculate(m_drivetrain.getPose().getX(), targetPose.getX());
    ySpeed = yController.calculate(m_drivetrain.getPose().getY(), targetPose.getY());
    thetaSpeed = thetaController.calculate(m_drivetrain.getPose().getRotation().getDegrees(),
        targetPose.getRotation().getRadians());
    thetaController.enableContinuousInput(-Math.PI, Math.PI);
    m_drivetrain.swerveDrive.drive(new ChassisSpeeds(xSpeed * 0.01, ySpeed * 0.01, thetaSpeed * 0.01));

    // prints
    // SmartDashboard.putNumber("deltax", targetPose.getX() - m_drivetrain.getX());
    // SmartDashboard.putNumber("deltay", targetPose.getY() - m_drivetrain.getY());
    // SmartDashboard.putNumber("deltatheta",
    // targetPose.getRotation().getDegrees() -
    // m_drivetrain.getRotation().getDegrees());
  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.swerveDrive.driveFieldOriented(new ChassisSpeeds(0, 0, 0));
  }

  @Override
  public boolean isFinished() {
    return m_drivetrain.m_poseEstimator.getEstimatedPosition().equals(targetPose)
        || (Math.abs(xSpeed) < 0.1 && Math.abs(ySpeed) < 0.1 && Math.abs(thetaSpeed) < 0.05);
  }
}
