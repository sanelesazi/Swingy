package com.ssibiya.swingy.model.characters.villains;

import com.ssibiya.swingy.model.artifact.Artifact;
import com.ssibiya.swingy.model.artifact.ArtifactFactory;
import com.ssibiya.swingy.model.characters.ACharacter;
import com.ssibiya.swingy.model.characters.heroes.Hero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
@Data
public abstract class Villain extends ACharacter
{
    @Getter
    protected Artifact artifact;
    protected boolean equipped = false;

    public Villain(String name, int level, int mapSize)
    {
        super(name, level);
        int x = rand.nextInt(mapSize - 1) + 0;
        int y = rand.nextInt(mapSize - 1) + 0;
        this.icon = 'V';
        this.x_coordinate = x;
        this.y_coordinate = y;
        this.type = name;
        int n = rand.nextInt(20);
        if (n % 2 == 0) {
            Artifact newArtifact = ArtifactFactory.newArtifact(artifactTypes[n % 3]);
            artifact = newArtifact;
            equipped = true;
        }
    }

    public int retaliate(Hero enemy)
    {
        int n = rand.nextInt(this.attack + this.defense);

        if ((enemy.getDef_speed() - n) < this.att_speed){
            if (this.hitpoints <= 30)
                phrase = phraseOfDesperation;
            enemy.setHitpoints((enemy.getHitpoints() - this.attack) + enemy.getDefense() / 3);
        }
        else
            return 0;
        if (enemy.getHitpoints() <= 0)
            enemy.setHitpoints(0);
        if (enemy.getHitpoints() > maxHP)
            enemy.setHitpoints(100);
        return 1;
    }
}
