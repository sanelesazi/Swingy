package com.ssibiya.swingy.model.characters.heroes;

public class Earthling extends Hero
{
    public Earthling(String name, int level)
    {
        super(name, level);
        this.type = "Earthling";
        this.attack = 20;
        this.defense = 35;
        this.speed = 25;
        this.att_speed = this.speed + this.attack;
        this.def_speed = this.speed + this.defense;
        this.icon = 'A';
        this.power = "Destructo Disk";
        this.defaultPhrase = ": Leave Earth now ";
        this.phraseOfDesperation = ": Hehehe I put up a good fight against you ";
    }
}
