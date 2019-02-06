/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.HatchReset;

/**
 * Add your docs here.
 */
public class Hatch extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Solenoid finger = new Solenoid(3);
  Solenoid pusher = new Solenoid(1);

  public void score() {
    finger.set(true);
    // TODO: we need to pause here for half of a second
    pusher.set(true);
  }

  public void reset() {
    pusher.set(false);
    finger.set(false);
  }

  public void retrieve() {
    finger.set(true);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // TODO: set default command to be reset
    setDefaultCommand(new HatchReset());

  }
}
