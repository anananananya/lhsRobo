// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  private final PWMSparkMax m_leftFrontDrive = new PWMSparkMax(0);
  private final PWMSparkMax m_leftBackDrive = new PWMSparkMax(1);
// Left side robot motors
MotorControllerGroup m_left = new MotorControllerGroup(m_leftFrontDrive, m_leftBackDrive);

  private final PWMSparkMax m_rightFrontDrive = new PWMSparkMax(18);
  private final PWMSparkMax m_rightBackDrive = new PWMSparkMax(19);
// Right side robot motors
MotorControllerGroup m_right = new MotorControllerGroup(m_rightFrontDrive, m_rightBackDrive);

//  private final PWMSparkMax rightArm = new PWMSparkMax(17);
//  private final PWMSparkMax leftArm = new PWMSparkMax(2);
//  arm motors

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_left, m_right);
  private final XboxController m_controller = new XboxController(0);
  private final Timer m_timer = new Timer();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightFrontDrive.setInverted(true);
    m_rightBackDrive.setInverted(true);
 }

  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    if (m_controller.getXButtonPressed()==true) 
    {
      m_left.set(5);
      m_right.set(5);
    }
    if (m_controller.getXButtonReleased()==true) 
    {
      m_left.set(0);
      m_right.set(0);
    }
  }

  @Override
  public void teleopPeriodic() {
    m_robotDrive.tankDrive(-m_controller.getLeftY(), -m_controller.getRightX());
  }

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}
}
