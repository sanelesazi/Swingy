package com.ssibiya.swingy.model.characters.heroes;

public class Namekian extends Hero
{

    public Namekian(String name, int level)
    {
        super(name, level);
        this.type = "Namekian";
        this.attack = 25;
        this.defense = 20;
        this.speed = 30;
        this.att_speed = this.speed + this.attack;
        this.def_speed = this.speed + this.defense;
        this.icon = 'A';
        this.power = "Canon Beam";
        this.defaultPhrase = ": I'm a proud Namek, take this ";
        this.phraseOfDesperation = ": If you kill me, the dragon ballz will die with me ";
    }
}
