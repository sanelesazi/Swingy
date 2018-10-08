package com.ssibiya.swingy.model.characters.heroes;

import com.ssibiya.swingy.model.characters.villains.Villain;

public interface HeroActions {
    int fight(Villain enemy);
    int flee(Villain enemy);
    void gainXP(Villain enemy);
    void increaseDef();
    void powerUp();
    void rejuvenate();
}
