/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;

/**
 * Add your docs here.
 */
public class Tank_Drive extends Subsystem {
  WPI_TalonSRX flDrive = new WPI_TalonSRX(RobotMap.flDrive);
  WPI_TalonSRX frDrive = new WPI_TalonSRX(RobotMap.frDrive);
  WPI_TalonSRX blDrive = new WPI_TalonSRX(RobotMap.blDrive);
  WPI_TalonSRX brDrive = new WPI_TalonSRX(RobotMap.brDrive);

  DifferentialDrive kopdrive = new DifferentialDrive(flDrive, frDrive);

  public void configDrive() {

    blDrive.follow(flDrive);
    brDrive.follow(frDrive);

    flDrive.setInverted(false);
    frDrive.setInverted(false);
    blDrive.setInverted(false);
    brDrive.setInverted(false);
    kopdrive.setDeadband(0.1);
  }

  public void teleopDrive(Joystick driveControl) {
    double forward = driveControl.getRawAxis(1);
    double turn = driveControl.getRawAxis(4);
    // SmartDashboard.putNumber("fr",frDrive.getOutputCurrent());
    // SmartDashboard.putNumber("br",brDrive.getOutputCurrent());
    // SmartDashboard.putNumber("fl",flDrive.getOutputCurrent());
    // SmartDashboard.putNumber("bl",blDrive.getOutputCurrent());

    
    kopdrive.arcadeDrive(forward, turn);
    }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ArcadeDrive());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}