package com.ssibiya.swingy.model.characters;

import com.ssibiya.swingy.model.characters.heroes.Earthling;
import com.ssibiya.swingy.model.characters.heroes.Hero;
import com.ssibiya.swingy.model.characters.heroes.Namekian;
import com.ssibiya.swingy.model.characters.heroes.Saiyan;
import com.ssibiya.swingy.model.characters.villains.*;

public abstract class CharacterFactory
{
    public static Hero newHero(String type, String name, int level)
    {
        switch(type)
        {
            case "Saiyan":
                return new Saiyan(name, level);
            case "Namekian":
                return new Namekian(name, level);
            case "Earthling":
                return new Earthling(name, level);
                default:
                    return null;
        }
    }

    public static Villain newVillain(String type, int level, int mapSize)
    {
        switch(type)
        {
            case "Cell":
                return new Cell(level, mapSize);
            case "Frieza":
                return new Frieza(level, mapSize);
            case "MajinBuu":
                return new MajinBuu(level, mapSize);
            case "Jiren":
                return new Jiren(level, mapSize);
            case "Hit":
                return new Hit(level, mapSize);
            case "Topo":
                return new Topo(level, mapSize);
            default:
                return null;
        }
    }

}
