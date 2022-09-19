In games, the clock doesn't stop and "wait" for input.
It updates the screen constantly. That's why Frames Per Second exist.
It uses a Thread to do so.

To have this living game updating, we use a Thread.
Where we use a thread, we implement the Runnable interface, with the overriding method run();

On the Thread, we will:
* UPDATE: Update information (character position, etc);
  * This one is made by the method update();
* DRAW : draw the screen with the update information;
  * This one is made by the method paintComponent();

To reduce the processing speed to match the 60 FPS, we can divide the processing speed (1.000.000.000 nanoseconds) by the desired FPS.