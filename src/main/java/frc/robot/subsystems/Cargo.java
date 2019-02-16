/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
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

  //https://www.chiefdelphi.com/t/talonsrx-isfinished-for-close-loop-control/340082
  int finalPositionCounter = 0;

  public Cargo() {

    armTalon.setInverted(false);

    armTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    armTalon.setSelectedSensorPosition(1024); // 90 degrees

    //https://phoenix-documentation.readthedocs.io/en/latest/ch16_ClosedLoop.html
    //TODO: instead of setting these here first, tune them using Phoenix Tuner, then set here in code using those values
    armTalon.config_kP(0, .1);
    armTalon.config_kI(0, 0);
    armTalon.config_kD(0, 0);
    armTalon.config_kF(0, 0);

    armTalon.setSensorPhase(true);

    armTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 30);

    cargomechTalon1.setInverted(true);
    cargomechTalon2.setInverted(false);

    cargomechTalon2.follow(cargomechTalon1);
  }

  public void setArmAngle(double angle) {
    brake.set(false);
    armTalon.set(ControlMode.Position, (angle * 4096.0 / 360.0));
    int error = armTalon.getClosedLoopError();

    //TODO: 34 encoder units is roughly equal to 3 degrees, so this allows for a threshold of +/- 3 degrees off target
    //      >> adjust this if necessary, setting to appropriate *encoder units*
    //      https://www.ctr-electronics.com/downloads/api/java/html/classcom_1_1ctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_base_motor_controller.html#a64275de55a2c1012d6a2935b9a7b0938
    if (error < 34)
      finalPositionCounter++;
    else
      finalPositionCounter = 0; // reset if we went pass the threshold
  }

  public boolean isArmAtPosition() {
    //TODO: determine if this is a sufficient number of counts/loops (5 ~= 100ms)
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
