/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ClimberStop;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {

  Solenoid push = new Solenoid(RobotMap.climberSolenoid);

  public void pushup() {
    push.set(true);
  }

  public void pushstop() {
    push.set(false);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberStop());
  }
}
