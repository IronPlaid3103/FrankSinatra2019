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
import frc.robot.commands.HatchReset;

/**
 * Add your docs here.
 */
public class Hatch extends Subsystem {

  Solenoid finger = new Solenoid(RobotMap.hatchFingerSolenoid);
  Solenoid pusher = new Solenoid(RobotMap.hatchPusherSolenoid);

  public void score() {
    finger.set(true);
    // TODO: we may need to pause between - if so, we will need to change this to a Command Group and add WaitCommand between
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
    setDefaultCommand(new HatchReset());
  }
}
