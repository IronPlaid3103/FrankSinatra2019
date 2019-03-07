/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick driver = new Joystick(ControllerMap.DRIVER_PORT);
  public Joystick operator = new Joystick(ControllerMap.OPERATOR_PORT);

  public OI() {
    initDriverControls(driver);
    initOperatorControls(operator);
  }

  private void initDriverControls(Joystick joystick) {
    if (!joystick.getName().equals("")) {
      Button ClimberGo = new JoystickButton(joystick, ControllerMap.X);
      Button ClimberStop = new JoystickButton(joystick, ControllerMap.B);
      ClimberGo.whileHeld(new ClimberGo());
      ClimberStop.whileHeld(new ClimberStop());

      Button CameraSwitch = new JoystickButton(joystick, ControllerMap.BUMPER_LEFT);
      CameraSwitch.whenPressed(new SwitchCamera());

      Button LimelightFollow = new JoystickButton(joystick, ControllerMap.A);
      LimelightFollow.whileHeld(new LimelightFollow());
    }
  }

  private void initOperatorControls(Joystick joystick) {
    if (!joystick.getName().equals("")) {
      Button HatchRetrieve = new JoystickButton(joystick, ControllerMap.B);
      Button HatchScore = new JoystickButton(joystick, ControllerMap.X);
      HatchRetrieve.whileHeld(new HatchRetrieve());
      HatchScore.whileHeld(new HatchScore());

      Button CargoUp = new JoystickButton(joystick, ControllerMap.BUMPER_LEFT);
      Button CargoDown = new JoystickButton(joystick, ControllerMap.BUMPER_RIGHT);
      CargoUp.whileHeld(new CargoUp());
      CargoDown.whileHeld(new CargoDown());

      Button CargoIntake = new JoystickButton(joystick, ControllerMap.LOGO_LEFT);
      Button CargoDeliver = new JoystickButton(joystick, ControllerMap.LOGO_RIGHT);
      CargoIntake.whenPressed(new CargoIntake());
      CargoDeliver.whileHeld(new CargoDeliver());
    }
  }

  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
