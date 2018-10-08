package com.ssibiya.swingy;

import com.ssibiya.swingy.control.GameController;
import com.ssibiya.swingy.view.console.StartScreen;
import com.ssibiya.swingy.view.gui.Window;

public class Swingy
{
    public static void main( String[] args ) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("console")) {
                StartScreen screen = new StartScreen();
                GameController gameController = new GameController();

                while (true) {
                    screen.clearConsole();
                    screen.printWelcomeScreen();
                    if (gameController.startGame() == 0)
                        return;
                }
            }
            else if (args[0].equalsIgnoreCase("gui")){
                Window win = new Window();
            }
            else
                System.out.println("Usage: java");
        }
    }
}
