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
import frc.robot.commands.CargoDeliver;
import frc.robot.commands.CargoDown;
import frc.robot.commands.CargoIntake;
import frc.robot.commands.CargoUp;
import frc.robot.commands.HatchReset;
import frc.robot.commands.HatchRetrieve;
import frc.robot.commands.HatchScore;
import frc.robot.commands.ClimberStop;
import frc.robot.commands.ClimberGo;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  Joystick driver = new Joystick(0);
  Joystick command = new Joystick(1);
  public Joystick returnJoystickDrive(){
    return driver;
  }
    public OI(){
      Button Reset = new JoystickButton(command, 1); // Button A
      Button Retrieve = new JoystickButton(command, 2); // Button B
      Button Score = new JoystickButton(command, 3); // Button X
      
      Button Up = new JoystickButton(command, 5); // left bumper
      Button Down = new JoystickButton(command, 6); // right bumper
      
      Button Intake = new JoystickButton(command, 7); // 3 line button
      Button Deliver = new JoystickButton(command, 8); // double square button
      
      Button ClimberGo = new JoystickButton(driver, 1); //A on the drivers remote
      Button ClimberStop = new JoystickButton(driver, 2); //B on the drivers remote

      
      Intake.whileHeld(new CargoIntake());
      Deliver.whileHeld(new CargoDeliver());
      
      Up.whileHeld(new CargoUp());
      Down.whileHeld(new CargoDown());
      
      Reset.whileHeld(new HatchReset());
      Retrieve.whileHeld(new HatchRetrieve());
      Score.whileHeld(new HatchScore());
      
      ClimberGo.whileHeld(new ClimberGo());
      ClimberStop.whileHeld(new ClimberStop());
  }
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
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
