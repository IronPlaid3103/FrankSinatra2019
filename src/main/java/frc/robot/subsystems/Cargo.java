/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.CargoStop;

/**
 * Add your docs here.
 */
public class Cargo extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX frTalon = new WPI_TalonSRX(RobotMap.frTalon);
  WPI_TalonSRX flTalon = new WPI_TalonSRX(RobotMap.flTalon);
  WPI_TalonSRX mechTalon1 = new WPI_TalonSRX(RobotMap.mechTalon1);
  WPI_TalonSRX mechTalon2 = new WPI_TalonSRX(RobotMap.mechTalon2);

  public void CargoInit() {
    // double intake = JoystickButton(1);
    // double deliver = JoystickButton(4);

    frTalon.follow(flTalon);

    frTalon.setInverted(false);
    flTalon.setInverted(false);

    mechTalon1.setInverted(true);
    mechTalon2.setInverted(false);

    initDefaultCommand();

  }

  public void up() {
    frTalon.set(1);
    flTalon.set(1);
  }

  public void down() {
    frTalon.set(-1);
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
    frTalon.set(0);
    flTalon.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
