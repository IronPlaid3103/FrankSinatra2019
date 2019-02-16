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
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  public static int frDrive = 1;
  public static int flDrive = 3;
  public static int brDrive = 2;
  public static int blDrive = 4;
 
  public static int cargoarmTalon = 6;

  public static int cargomechTalon1;
  public static int cargomechTalon2;

  public static double cargoPark = 0.0;
  public static double cargoLevel1 = 0.0; //still needs to be calculated
  public static double cargoLevel2 = 49.1446;
  public static double cargoLevel3  = 60.107;

}
