// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.SpinFeeder;
import frc.robot.subsystems.Drivetrain;

import java.io.File;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.pathplanner.lib.auto.NamedCommands;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final Drivetrain m_drivetrain = new Drivetrain(new File(Filesystem.getDeployDirectory(),
      "swerve"));

  // Joystick object
  public final Joystick m_joystick = new Joystick(1);
  // Triggers on the joystick
  private Trigger inputSpin = new Trigger(() -> m_joystick.getRawButton(6));
  private Trigger navxResetButton = new Trigger(() -> m_joystick.getRawButton(3));
  private Trigger toPoseButton = new Trigger(() -> m_joystick.getRawButton(1)); // Work in Progress - Horatio
  private Trigger zeroWheels = new Trigger(() -> m_joystick.getRawButton(2));
  // feeder motor
  private TalonSRX feederMotor = new TalonSRX(22);
  Command spinFeederCommand = new SpinFeeder(feederMotor);

  public RobotContainer() {
    // NamedCommands.registerCommand("TestMe", Commands.runOnce(() ->
    // SmartDashboard.putNumber("AAAA", 911)));
    NamedCommands.registerCommand("Print", Commands.runOnce(() -> System.out.println("THDJAKLHRUAESITYADU ILSYF")));
    NamedCommands.registerCommand("TestMe", Commands.runOnce(() -> {
      System.out.println("TestMe command executed!");
      SmartDashboard.putNumber("GGGGGGGGG", 911);
    }));

    NamedCommands.registerCommand("spin", spinFeederCommand);

    m_drivetrain.setupPathPlanner();
    // NamedCommands.registerCommand("turn", Commands.run(()->{twist(true);}));
    NamedCommands.registerCommand("spin", getSpinMotorCommand());
    // NamedCommands.registerCommand("print",
    // Commands.print("fghjfgjfghjfghjfghjfghj"));

    configureBindings();
  }

  public Command getSpinMotorCommand() {
    return Commands.runOnce(() -> {
      System.out.println("SPIN COMMAND IS BEING CALLED!");
    });
  }

  /**
   * Use this method to define your trigger->command mapping s. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  Pose2d test = new Pose2d(11.0, 0.0, new Rotation2d(1.0));

  private void configureBindings() {
    m_drivetrain.setDefaultCommand(m_drivetrain.driveCommand(() -> m_joystick.getRawAxis(1) * -1,
        () -> m_joystick.getRawAxis(0) * -1,
        () -> m_joystick.getRawAxis(2) * -1));

    navxResetButton.onTrue(Commands.runOnce(m_drivetrain::zeroGyro));
    toPoseButton.onTrue(Commands.runOnce(() -> m_drivetrain
        .toPose(new Pose2d(m_drivetrain.getX() - 1, m_drivetrain.getY() - 1, m_drivetrain.getRotation()))));
    zeroWheels.onTrue(Commands.runOnce(m_drivetrain::zeroWheels));
    inputSpin.whileTrue(spinFeederCommand);

    // Add this to configureBindings()
    Trigger testCommandTrigger = new Trigger(() -> m_joystick.getRawButton(5)); // or use another unused button
    testCommandTrigger.onTrue(Commands.runOnce(() -> {
      System.out.println("Manually triggering the TestMe command");
      Command testCommand = NamedCommands.getCommand("TestMe");
      if (testCommand != null) {
        testCommand.schedule();
      } else {
        System.out.println("ERROR: TestMe command not found in registry!");
      }
    }));
  }

  public Drivetrain getDrivetrain() {
    return m_drivetrain;
  }

  /**
   * 
   * 
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {

    return m_drivetrain.getAutonomousCommand("sigma");
    // return m_drivetrain.getAutonomousCommand();
  }

  public void setMotorBrake(boolean brake) {
    m_drivetrain.setMotorBrake(brake);
  }

}