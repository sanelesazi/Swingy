package com.ssibiya.swingy.control;

import com.ssibiya.swingy.db.DB;
import com.ssibiya.swingy.model.characters.heroes.Hero;
import com.ssibiya.swingy.model.characters.villains.Villain;
import com.ssibiya.swingy.model.map.GenerateMap;
import com.ssibiya.swingy.view.console.StartScreen;
import com.ssibiya.swingy.view.console.View;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class Movement{

    protected StartScreen screen = new StartScreen();
    DB db = new DB();

    public void updatePlayerLocation(Hero player, char [][]map)
    {
        View.clearConsole();
        for (int y = 0; y < map.length; y++) //row
        {
            for (int x = 0; x < map.length; x++) //column
            {
                if (player.getX_coordinate() == x && player.getY_coordinate() == y && map[y][x] != 'A')
                    map[y][x] = player.getIcon();
            }
        }
    }

    public void updateVillainLocation(Villain enemy, char [][]map)
    {
        View.clearConsole();
        for (int y = 0; y < map.length; y++) //row
        {
            for (int x = 0; x < map.length; x++) //column
            {
                if (enemy.getX_coordinate() == x && enemy.getY_coordinate() == y && map[y][x] != 'A')
                    map[y][x] = enemy.getIcon();
            }
        }
    }

    public void updatePlayer(Hero player, GenerateMap map)
    {
        updatePlayerLocation(player, map.getMap());
    }

    public void populateVillains(List<Villain> villains, GenerateMap map)
    {
        for (Villain vil: villains) {
            updateVillainLocation(vil, map.getMap());
//            System.out.println("Villain: " + vil.getType() + " is at X: " + vil.getX_coordinate() + " and Y: " + vil.getY_coordinate());
        }
    }

    public int checkCollision(Hero player, List<Villain> villains){
        String decision = null;
        Iterator iterVillains = villains.listIterator();
        Villain vil;

        while (iterVillains.hasNext())
        {
            int forceFight = 0;
            vil = (Villain)iterVillains.next();
            if (vil.getX_coordinate() == player.getX_coordinate() && vil.getY_coordinate() == player.getY_coordinate())
            {
                screen.promptFightFlee(vil);
                while (true)
                {
                    if (forceFight == 0)
                        decision = View.getInput();
                    if (decision.equalsIgnoreCase("F") || forceFight == 1) {
                        if (simulateFight(vil, player) == 1) {
                            iterVillains.remove();
                            while (true){
                                decision = View.getInput();
                                if (decision.equalsIgnoreCase("C"))
                                    break ;
                                else
                                    screen.invalidInput(1);
                            }
                            break;
                        }
                        else if (simulateFight(vil, player) == 2)
                            return 1;
                        else if (simulateFight(vil, player) == 0){
                            screen.gameOver(vil);
                            return 0;
                        }
                    }
                    else if (decision.equalsIgnoreCase("R")) {
                        if (attemptFlee(vil, player) == 1) {
                            vil.move();
                            break;
                        }
                        else
                            forceFight = 1;
                    }
                    else
                        screen.invalidInput(1);
                }
            }
        }
        return 1;
    }

    public void moveVillains(List<Villain> villains, char [][]map)
    {
        int i = 0;
        for (Villain vil: villains)
        {
            if (i % 2 == 0)
                vil.randomMove(map);
            i++;
        }
    }

    private int attemptFlee(Villain villain, Hero player)
    {
        if (player.flee(villain) == 1) {
            screen.successfulFlee(villain);
            return 1;
        }
        else
            screen.failedFlee(villain);
        return 0;
    }

    private int simulateFight(Villain villain, Hero player)
    {
        String option;
        int opt = 0;

        while (true) {
            screen.printStats(villain, player);
            screen.printActions(player);
            option = View.getInput();
            try {opt = Integer.parseInt(option);
            }catch (NumberFormatException e){
                e.getMessage();
            }
            if (option.equalsIgnoreCase("1")) {
                screen.fight(player.fight(villain), player, villain);
            }
            else if (option.equalsIgnoreCase("R")){
                if (attemptFlee(villain, player) == 1)
                    return 2;
            }
            else if (opt >= 2 && opt <= 4){
                try {
                    player.doAction(player.getArtifacts().get(opt - 2));
                    screen.doAction(player.getArtifacts().get(opt - 2), player);
                    player.getArtifacts().remove(opt - 2);
                } catch (IndexOutOfBoundsException e){
                    screen.invalidInput(2);
                }
            }
            else
                screen.invalidInput(2);
            screen.retaliate(villain.retaliate(player), villain, player);
            if (villain.getHitpoints() == 0) {
                screen.printWin(villain);
                if (villain.isEquipped()) {
                    while (true)
                    {
                        screen.printArtifactDropped(villain);
                        option = View.getInput();
                        if (option.equalsIgnoreCase("Y") || option.equalsIgnoreCase("YES")) {
                            player.getArtifacts().add(villain.getArtifact());
                            screen.tookArtifact(1, villain);
                            break ;
                        }
                        else if (option.equalsIgnoreCase("N") || option.equalsIgnoreCase("NO")) {
                            screen.tookArtifact(0, villain);
                            break ;
                        }
                        else
                            screen.invalidInput(1);
                    }
                }
                player.gainXP(villain);
                try {
                    db.updateHeroStats(player);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                screen.printSummary(villain, player);
                return 1;
            }
            else if (player.getHitpoints() == 0) {
                return 0;
            }
        }
    }
}
