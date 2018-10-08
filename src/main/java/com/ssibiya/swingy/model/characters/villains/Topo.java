package com.ssibiya.swingy.model.characters.villains;

import com.ssibiya.swingy.model.characters.villains.Villain;
import com.ssibiya.swingy.model.characters.villains.VillainActions;

public class Topo extends Villain implements VillainActions
{
    public Topo(int level, int mapSize) {
        super("Topo", level, mapSize);
        this.type = "Topo";
        this.attack = 4 * ((level + 1) * 9);
        this.defense = 3 * ((level + 1) * 10);
        this.speed = 5 * ((level) * 8);
        this.att_speed = this.speed + this.attack;
        this.def_speed = this.speed + this.defense;
        this.icon = 'T';
        this.power = "Destruction";
        this.phrase = ": Justice will be served ";
        this.phraseOfDesperation = ": I am GOD! ";
    }
}
