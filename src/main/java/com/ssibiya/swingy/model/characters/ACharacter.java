package com.ssibiya.swingy.model.characters;

import com.ssibiya.swingy.model.characters.heroes.HeroActions;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import java.lang.*;
import java.util.Random;

@Data
public abstract class ACharacter{
    @Getter @Setter protected String name;
    protected String type;
    protected int level;
    protected int defense;
    protected int attack;
    protected int experience;
    protected int hitpoints;
    protected int x_coordinate;
    protected int y_coordinate;
    protected char icon;

    protected static int maxHP = 100;
    protected static int levelLimit = 0;
    protected int speed;
    protected int att_speed;
    protected int def_speed;
    protected String power;
    protected String phrase = "";
    protected String phraseOfDesperation = "";
    protected Random rand = new Random();
    protected final static String [] artifactTypes = {"Weapon", "Armor", "Helm"};

    protected ACharacter(String nme, int lvl)
    {
        this.name = nme;
        this.level = lvl;
        this.experience = 0;
        this.hitpoints = maxHP;
    }

    public void moveLeft(char [][]map)
    {
        if (x_coordinate - 1 >= 0)
            this.x_coordinate -= 1;
    }

    public void moveRight(char [][]map)
    {
        if (this.getX_coordinate() + 1 < map.length)
            this.x_coordinate += 1;
    }

    public void moveUp(char [][]map)
    {
        if (y_coordinate - 1 >= 0)
            this.y_coordinate -= 1;
    }

    public void moveDown(char [][]map)
    {
        if (y_coordinate + 1 < map.length)
            this.y_coordinate += 1;
    }

    public void randomMove(char [][]map)
    {
//        Random rand = new Random();

        int n = rand.nextInt(4) + 0;
        int random = n % 4;
        switch (random)
        {
            case 0:
                moveDown(map);
                break ;
            case 1:
                moveLeft(map);
                break ;
            case 2:
                moveRight(map);
                break ;
            case 3:
                moveUp(map);
                break ;
                default:
                    return;
        }
    }

    public void move()
    {
        Random rand = new Random();

        int n = rand.nextInt(50) + 1;
        int random = n % 4;
        switch (random)
        {
            case 0:
                x_coordinate += 1;
            case 1:
                x_coordinate -= 1;
            case 2:
                y_coordinate += 1;
            case 3:
                y_coordinate -= 1;
        }
    }
}
