/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.CargoStop;

/**
 * Add your docs here.
 */
public class Cargo extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX flTalon = new WPI_TalonSRX(RobotMap.flTalon);
  WPI_TalonSRX mechTalon1 = new WPI_TalonSRX(RobotMap.mechTalon1);
  WPI_TalonSRX mechTalon2 = new WPI_TalonSRX(RobotMap.mechTalon2);

  Solenoid climberbreak = new Solenoid(7); // change this when we know which port

  public void CargoInit() {
    // double intake = JoystickButton(1);
    // double deliver = JoystickButton(4);

    flTalon.setInverted(false);

    mechTalon1.setInverted(true);
    mechTalon2.setInverted(false);

    flTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    flTalon.setSelectedSensorPosition(0);

    flTalon.config_kP(0, .1);
    flTalon.config_kI(0, 0);
    flTalon.config_kD(0, 0);
    flTalon.config_kF(0, 0);

    flTalon.setSensorPhase(true);
  }

  public void level2() {
    int position = flTalon.getSelectedSensorPosition();
    double angle = (double) position * 4096.0 / 360.0;
    if (angle > 49.1446) {
      flTalon.set(.6);
    }
    else if (angle < 49.1446) {
      flTalon.set(-.6);
    }
  }

  public void level3() {
    flTalon.set(ControlMode.Position, (60.107*360.0/4096.0));
  }

  public void cargobreak() {
    climberbreak.set(true);
  }

  public void up() {
    flTalon.set(1);
  }

  public void down() {
    flTalon.set(-1);
  }

  public void intake() {
    mechTalon1.set(-1);
    mechTalon2.set(-1);

  }

  public void deliver() {
    mechTalon1.set(1);
    mechTalon2.set(1);
  }

  public void stop() {
    mechTalon1.set(0);
    mechTalon2.set(0);
    flTalon.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new CargoStop());
  }
}
