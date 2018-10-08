package com.ssibiya.swingy.model.characters.villains;

import com.ssibiya.swingy.model.characters.villains.Villain;
import com.ssibiya.swingy.model.characters.villains.VillainActions;

public class Hit extends Villain implements VillainActions
{
    public Hit(int level, int mapSize) {
        super("Hit", level, mapSize);
        this.type = "Hit";
        this.attack = 4 * ((level + 1) * 10);
        this.defense = 4 * ((level + 1) * 10);
        this.speed = 3 * ((level + 1) * 6);
        this.att_speed = this.speed + this.attack;
        this.def_speed = this.speed + this.defense;
        this.icon = 'H';
        this.power = "Time skip";
        this.phrase = ": You're too slow ";
        this.phraseOfDesperation = ": How did you figure time skip ";
    }
}
