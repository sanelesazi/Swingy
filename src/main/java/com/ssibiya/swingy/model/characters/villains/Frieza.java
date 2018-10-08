package com.ssibiya.swingy.model.characters.villains;

public class Frieza extends Villain implements VillainActions
{
    public Frieza(int level, int mapSize) {
        super("Frieza", level, mapSize);
        this.type = "Frieza";
        this.attack = 6 * ((level + 1) * 5); //30 --> 60 --> 90 --> 120
        this.defense = 3 * ((level + 1) * 4); //12 --> 24 --> 36 --> 48
        this.speed = 5 * ((level + 1) * 4); //20 --> 40 --> 60 --> 80
        this.att_speed = this.speed + this.attack;
        this.def_speed = this.speed + this.defense;
        this.icon = 'F';
        this.power = "Finger Beam";
        this.phrase = ": Hmmm worthless ";
        this.phraseOfDesperation = ": I will not be beaten by an insect of a ";
    }
}
