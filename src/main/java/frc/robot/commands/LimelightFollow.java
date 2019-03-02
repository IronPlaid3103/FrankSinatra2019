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

  public LimelightFollow() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.kopchassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    error_sum = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");

    double changeInError = lastError - tx.getDouble(0);
    error_sum += tx.getDouble(0);

    // double kp = 0.021;
    // double ki = 0.0;
    // double kd = 0.15;
    double kp = SmartDashboard.getNumber("kP", 0.0);
    double ki = SmartDashboard.getNumber("kI", 0.0);
    double kd = SmartDashboard.getNumber("kD", 0.0);

    double P = kp * tx.getDouble(0);
    double I = ki * error_sum;
    double D = kd * changeInError;

    double output = P + I - D;
    
    SmartDashboard.putNumber("output", output);
    SmartDashboard.putNumber("error", lastError);

   // if(output > 0) output -= 0.1;
   // else output += 0.1;

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
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
