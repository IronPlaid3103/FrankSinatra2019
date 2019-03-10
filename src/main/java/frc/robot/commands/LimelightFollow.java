/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class LimelightFollow extends Command {
  double lastError = 0;
  double error_sum = 0;

  double maxOutput = 0;

  double kP = 0.021;
  double kI = 0.0;
  double kD = 0.15;

  public LimelightFollow() {
    requires(Robot.kopchassis);

    if (!Robot.preferences.containsKey("Limelight.kP")) {
      Robot.preferences.putDouble("Limelight.kP", kP);
    }
    if (!Robot.preferences.containsKey("Limelight.kI")) {
      Robot.preferences.putDouble("Limelight.kI", kI);
    }
    if (!Robot.preferences.containsKey("Limelight.kD")) {
      Robot.preferences.putDouble("Limelight.kD", kD);
    }

    kP = Robot.preferences.getDouble("Limelight.kP", 0.0);
    kI = Robot.preferences.getDouble("Limelight.kI", 0.0);
    kD = Robot.preferences.getDouble("Limelight.kD", 0.0);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    error_sum = 0;

    // turn on the Limelight LED
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    double x = tx.getDouble(0);

    error_sum += x;

    double P = kP * x;
    double I = kI * error_sum;
    double D = kD * (lastError - x);

    lastError = x;

    double output = P + I - D;

    if (output > maxOutput)
      maxOutput = output;
    SmartDashboard.putNumber("maxOutput", maxOutput);
    SmartDashboard.putNumber("output", output);
    SmartDashboard.putNumber("error", lastError);

    Robot.kopchassis.limelightDrive(Robot.m_oi.driver, output);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // return the Limelight LED to pipeline default (ENSURE IT'S OFF IN LIMELIGHT CONFIG)
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    super.interrupted();
  }
}
