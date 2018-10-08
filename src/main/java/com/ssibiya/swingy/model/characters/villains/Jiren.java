package com.ssibiya.swingy.model.characters.villains;

import com.ssibiya.swingy.model.characters.villains.Villain;
import com.ssibiya.swingy.model.characters.villains.VillainActions;

public class Jiren extends Villain implements VillainActions
{
    public Jiren(int level, int mapSize)
    {
        super("Jiren", level, mapSize);
        this.type = "Jiren";
        this.attack = 6 * ((level + 1) * 10);
        this.defense = 6 * ((level) * 10);
        this.speed = 3 * ((level + 2) * 10);
        this.att_speed = this.speed + this.attack;
        this.def_speed = this.speed + this.defense;
        this.icon = 'J';
        this.power = "-_-";
        this.phrase = ": -_- ";
        this.phraseOfDesperation = ": Worthy Warrior ";
    }
}
