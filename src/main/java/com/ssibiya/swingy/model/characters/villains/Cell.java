package com.ssibiya.swingy.model.characters.villains;

public class Cell extends Villain implements VillainActions
{
    public Cell(int level, int mapSize)
    {
        super("Cell", level, mapSize);
        this.type = "Cell";
        this.attack = 4 * ((level + 1) * 3); //12 --> 24 --> 36 --> 48
        this.defense = 4 * ((level + 1) * 5); //20 --> 40 --> 60 --> 80
        this.speed = 8 * ((level + 1) * 3); //24 --> 48 --> 72 --> 96
        this.att_speed = this.speed + this.attack;
        this.def_speed = this.speed + this.defense;
        this.icon = 'C';
        this.power = "Suction Tail";
        this.phrase = ": I will suck you in ";
        this.phraseOfDesperation = ": Oh No! I need Android 17, I cannot die in the hands of ";
    }
}
