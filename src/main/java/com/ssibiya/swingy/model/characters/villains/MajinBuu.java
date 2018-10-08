package com.ssibiya.swingy.model.characters.villains;

public class MajinBuu extends Villain implements VillainActions
{
    public MajinBuu(int level, int mapSize) {
        super("Majin Buu", level, mapSize);
        this.type = "Majin Buu";
        this.attack = 5 * ((level + 1) * 5); //25 ---> 50 --> 75 --> 100
        this.defense = 4 * ((level + 1) * 6); //24 ---> 48 --> 72 --> 96
        this.speed = 4 * ((level + 1) * 4); //16 ---> 32 --> 48 --> 64
        this.att_speed = this.speed + this.attack;
        this.def_speed = this.speed + this.defense;
        this.icon = 'M';
        this.power = "Chocolate Beam";
        this.phrase = ": Booooohooo --> ";
        this.phraseOfDesperation = ": Oooooh Boo MAD!! ";
    }
}
