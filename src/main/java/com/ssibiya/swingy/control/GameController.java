package com.ssibiya.swingy.control;

import com.ssibiya.swingy.db.DB;
import com.ssibiya.swingy.model.characters.HeroInfo;
import com.ssibiya.swingy.model.characters.villains.Villain;
import com.ssibiya.swingy.model.characters.CharacterFactory;
import com.ssibiya.swingy.model.characters.heroes.Hero;
import com.ssibiya.swingy.model.map.GenerateMap;
import com.ssibiya.swingy.view.console.StartScreen;
import com.ssibiya.swingy.view.console.View;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {
    protected GenerateMap map = null;
    protected int currentLevel = 0;
    protected StartScreen screen = new StartScreen();
    public ResultSet result;

    public static Hero player;
    public List<HeroInfo> heroInfo = new ArrayList<>();
    protected List<Villain> villains = new ArrayList<>();
    protected Movement movement = new Movement();
    DB db = new DB();

    public int startGame() throws NullPointerException
    {
        String choice = "";
        while (true) {
            if (currentLevel == 0)
            {
                if (initPlayer() == 1)
                    map = new GenerateMap(player.getLevel());
                else
                    return 0;
            }
            else
                map = new GenerateMap(player.getLevel());
            player.setX_coordinate(map.getSize() / 2);
            player.setY_coordinate(map.getSize() / 2);

//            map.initNewMap();
            movement.updatePlayer(player, map);
            initVillains(map.getSize() * (player.getLevel() + 1));
            screen.printMap(map.getMap());
            if (gamePlay() == 0) {
                while (true) {
                    screen.promptRetry();
                    choice = View.getInput();
                    if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("YES"))
                        return 1;
                    else if (choice.equalsIgnoreCase("N") || choice.equalsIgnoreCase("NO"))
                        return 0;
                    else
                        screen.invalidInput(1);
                }
            }
        }
    }
    public void getDBPlayer(){
        DB db = new DB();

        try {
            result = db.getAllHeroStats();

//            System.out.println("Done Getting Users");
//            while (result.next()) {
//                System.out.println("USERS");
//                System.out.println(result.getInt("id") + " " + result.getString("name"));
//            }
//            System.out.println("Done printing Users");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int createPlayer(HeroInfo savedHero){
        player = CharacterFactory.newHero(savedHero.getHeroType(), savedHero.getHeroName(), savedHero.getHeroLVL());
        player.setHitpoints(savedHero.getHeroHP());
        player.setExperience(savedHero.getHeroXP());
        player.setAttack(savedHero.getHeroATT());
        player.setDefense(savedHero.getHeroDEF());
        player.setSpeed(savedHero.getHeroSPD());
        player.setAtt_speed(savedHero.getHeroATTSPD());
        player.setDef_speed(savedHero.getHeroDEFSPD());
//        try {
//            db.insertNewHeroStats(player);
//        }catch (Exception e){System.out.println(e.getMessage());}
        return 1;
    }

    private int initPlayer()
    {
        String characterType, characterName;
        HeroInfo savedHero;

        getDBPlayer();
        while (true) {
            if ((savedHero = screen.characterChoice(result, heroInfo)) != null) {
                heroInfo.clear();
                return createPlayer(savedHero);
            }
            characterType = screen.printCharacterSelection();
            if (characterType == null)
                return -1;
            characterName = screen.printEnterName();
            if (characterName == null)
                return -1;
            else if (characterName.length() > 2) {
                screen.printWelcome(characterType, characterName);
                break ;
            }
        }
        player = CharacterFactory.newHero(characterType, characterName, currentLevel);
        try {db.insertNewHeroStats(player);
        }catch (Exception e){System.out.println(e.getMessage());}
        return 1;
    }

    public int gamePlay()
    {
        String direction;
        while (true)
        {
            direction = View.getInput();
            if (direction.equalsIgnoreCase("W"))
                player.moveUp(map.getMap());
            else if (direction.equalsIgnoreCase("S"))
                player.moveDown(map.getMap());
            else if (direction.equalsIgnoreCase("A"))
                player.moveLeft(map.getMap());
            else if (direction.equalsIgnoreCase("D"))
                player.moveRight(map.getMap());
            else if (direction.equalsIgnoreCase("Q"))
                return 0;
            else
                screen.invalidInput(3);
            map.initNewMap();
            movement.updatePlayer(player, map);
            if (movement.checkCollision(player, villains) == 0)
                return 0;
            if (player.getLevel() > currentLevel)
            {
                currentLevel = player.getLevel();
                villains.removeAll(villains);
                break ;
            }
            movement.moveVillains(villains, map.getMap());
            movement.populateVillains(villains, map);
            screen.printMap(map.getMap());
            if (villains.isEmpty())
                initVillains(map.getSize() * (player.getLevel() + 1));
        }
        return 1;
    }

    private void initVillains(int numOfEnemies)
    {
        String [] DBZTypes = {"Cell", "Frieza", "MajinBuu"};
        String [] DBSTypes = {"Jiren", "Hit", "Topo"};
        Random rand = new Random();
        Villain newVillain;
        int random;

        if (player.getLevel() <= 3) {
            for (int i = 0; i < numOfEnemies; i++) {
                random = rand.nextInt(3) + 0;
                newVillain = CharacterFactory.newVillain(DBZTypes[random], player.getLevel(), map.getSize());
                villains.add(newVillain);
            }
        }
        else
        {
            for (int i = 0; i < numOfEnemies; i++) {
                random = rand.nextInt(3) + 0;
                newVillain = CharacterFactory.newVillain(DBSTypes[random], player.getLevel(), map.getSize());
                villains.add(newVillain);
            }
        }
        movement.populateVillains(villains, map);
    }
}
