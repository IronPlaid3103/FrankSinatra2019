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
  WPI_TalonSRX armTalon = new WPI_TalonSRX(RobotMap.cargoarmTalon);
  WPI_TalonSRX cargomechTalon1 = new WPI_TalonSRX(RobotMap.cargomechTalon1);
  WPI_TalonSRX cargomechTalon2 = new WPI_TalonSRX(RobotMap.cargomechTalon2);

  Solenoid brake = new Solenoid(7); // change this when we know which port

  int finalPositionCounter = 0;

  public void CargoInit() {

    armTalon.setInverted(false);

    cargomechTalon1.setInverted(true);
    cargomechTalon2.setInverted(false);

    cargomechTalon2.follow(cargomechTalon1);

    armTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    armTalon.setSelectedSensorPosition(1024); // 90 degrees

    armTalon.config_kP(0, .1);
    armTalon.config_kI(0, 0);
    armTalon.config_kD(0, 0);
    armTalon.config_kF(0, 0);

    armTalon.setSensorPhase(true);
  }

  public void setArmAngle(double angle) {
    brake.set(false);
    armTalon.set(ControlMode.Position, (angle * 4096 / 360.0));
    int error = armTalon.getClosedLoopError();

    if (error < 34)
      finalPositionCounter++;
    else
      finalPositionCounter = 0;
  }

  public boolean isArmAtPosition() {
    if (finalPositionCounter > 5)
      return true;
    else
      return false;
  }

  public void cargobrake() {
    finalPositionCounter = 0;
    armTalon.set(ControlMode.PercentOutput, 0);
    brake.set(true);
  }

  public void up() {
    armTalon.set(1);
  }

  public void down() {
    armTalon.set(-1);
  }

  public void intake() {
    cargomechTalon1.set(-1);
    cargomechTalon2.set(-1);

  }

  public void deliver() {
    cargomechTalon1.set(1);
    cargomechTalon2.set(1);
  }

  public void stop() {
    cargomechTalon1.set(0);
    cargomechTalon2.set(0);
    armTalon.set(0);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CargoStop());
  }
}
