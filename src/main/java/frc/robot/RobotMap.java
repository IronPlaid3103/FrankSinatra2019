/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  public static int frDrive = 1;
  public static int flDrive = 3;
  public static int brDrive = 2;
  public static int blDrive = 4;
 
  public static int cargoarmTalon1 = 5;
  public static int cargoarmTalon2 = 6;

  public static int cargomechTalon1 = 7;
  public static int cargomechTalon2 = 8;

  public static double cargoPark = 90.0;
  public static double cargoLevel1 = 30.0; //TODO: still needs to be calculated
  public static double cargoLevel2 = 49.1446;
  public static double cargoLevel3  = 60.107;
  public static double cargoArmIntake;  //TODO: do we need a special angle for intake?
  public static double cargoArmAngleTolerance = 3.0; //TODO: adjust after some real world testing; acceptable tolerance of arm position +/- degrees

  public static int hatchPusherSolenoid = 1;
  public static int hatchFingerSolenoid = 3;

  public static int cargoBrakeSolenoid = 7;

  public static int climberSolenoid = 2;
}
