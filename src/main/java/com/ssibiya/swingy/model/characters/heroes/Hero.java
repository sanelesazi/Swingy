package com.ssibiya.swingy.model.characters.heroes;

import com.ssibiya.swingy.model.artifact.Artifact;
import com.ssibiya.swingy.model.characters.ACharacter;
import com.ssibiya.swingy.model.characters.villains.Villain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public abstract class Hero extends ACharacter
{
    private static int lvl = 0;
    private final static int []LEVELS = {1000,2450,4800,8050,12200};
    protected String defaultPhrase = "";

    public boolean levelledUp = false;
    @Getter
    protected List<Artifact> artifacts = new ArrayList<>();

    protected Hero(String name, int level) {
        super(name, level);
    }

    public int fight(Villain enemy)
    {
        int n = rand.nextInt(this.attack + this.defense);

        if (this.att_speed > enemy.getDef_speed() - n) {
            if (this.hitpoints <= 30)
                phrase = this.phraseOfDesperation;
            else
                phrase = this.defaultPhrase;
            enemy.setHitpoints((enemy.getHitpoints() - this.attack) + (enemy.getDefense() / 2));
        }
        else
            return 0;
        if (enemy.getHitpoints() <= 0)
            enemy.setHitpoints(0);
        if (enemy.getHitpoints() > 100)
            enemy.setHitpoints(100);
        return 1;
    }

    public int flee(Villain enemy)
    {
        int n = rand.nextInt(enemy.getAtt_speed()) + 0;
        if (enemy.getAtt_speed() + n > this.def_speed){
            return 0;
        }
        else
            return 1;
    }

    public void gainXP(Villain enemy)
    {
        levelledUp = false;
        this.experience += (enemy.getAttack() * enemy.getDefense()) / 2;
        this.hitpoints += enemy.getDefense();
        this.attack += enemy.getAttack() / 3;
        this.defense += enemy.getDefense() / 3;
        this.speed += enemy.getSpeed() / 4;
        this.att_speed += enemy.getAtt_speed() / 3;
        this.def_speed += enemy.getDef_speed() / 3;
        if (this.experience >= LEVELS[lvl]) {
            this.level++;
            this.hitpoints = maxHP;
            this.levelledUp = true;
            lvl++;
            if (lvl > 4)
                lvl = 4;
        }
        if (this.hitpoints > maxHP)
            this.setHitpoints(maxHP);
    }

    public void rejuvenate(Artifact af)
    {
        this.hitpoints += af.getPower();
        if (this.hitpoints > maxHP)
            this.hitpoints = maxHP;
    }

    public void powerUp(Artifact af)
    {
        this.attack += af.getPower();
        this.att_speed = this.attack + this.speed;
    }

    public void increaseDef(Artifact af)
    {
        this.defense += af.getPower();
        this.def_speed = this.defense + this.speed;
    }

    public void doAction(Artifact af){
        switch (af.getType())
        {
            case "Armor":
                increaseDef(af);
                break ;
            case "Weapon":
                powerUp(af);
                break ;
            case "Helm":
                rejuvenate(af);
                break ;
                default:
                    return ;
        }
    }
}
