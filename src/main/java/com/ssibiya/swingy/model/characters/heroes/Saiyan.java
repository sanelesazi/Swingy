package com.ssibiya.swingy.model.characters.heroes;

import java.lang.*;

public class Saiyan extends Hero
{
    public Saiyan(String name, int level) {
        super(name, level);
        this.type = "Saiyan";
        this.attack = 30;
        this.defense = 30;
        this.speed = 30;
        this.att_speed = this.speed + this.attack;
        this.def_speed = this.speed + this.defense;
        this.icon = 'A';

        if (name.equalsIgnoreCase("Vegeta")){
            this.power = "Galick Gun";
            this.defaultPhrase = ": Hmmm Pathetic ";
            this.phraseOfDesperation = ": I'm the prince of all Saiyans ";
        }
        else
        this.power = "Kamehameya";
        this.defaultPhrase = ": You're no match for the Saiyan race ";
        this.phraseOfDesperation = ": I'm a proud Saiyan!!! feel the wrath ";
    }
}
