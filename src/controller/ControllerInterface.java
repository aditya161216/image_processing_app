package controller;

/**
 * Represents the list of operations that a controller should support. The controller should
 * be able to run the program, send messages to the view and execute commands.
 */
public interface ControllerInterface {

  /**
   * Runs the controller. The controller will execute commands based on inputs when running.
   */
  void runProgram();

  /**
   * Sends a message to the view. If the message was unable to be sent relays this information
   * to the user.
   *
   * @param message the message to be sent.
   */
  void sendMessage(String message);

}
