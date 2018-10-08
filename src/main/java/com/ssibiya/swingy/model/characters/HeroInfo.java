package com.ssibiya.swingy.model.characters;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class HeroInfo {
    @Getter @Setter
    String heroName;
    String heroType;
    int heroHP;
    int heroXP;
    int heroLVL;
    int heroATT;
    int heroDEF;
    int heroSPD;
    int heroATTSPD;
    int heroDEFSPD;
    public HeroInfo(String hName, String hType, int hLVL, int hHP, int hXP, int hATT, int hDEF, int hSPD, int hATTSPD, int hDEFSPD){
        heroName = hName;
        heroType = hType;
        heroLVL = hLVL;
        heroHP = hHP;
        heroXP = hXP;
        heroATT = hATT;
        heroDEF = hDEF;
        heroSPD = hSPD;
        heroATTSPD = hATTSPD;
        heroDEFSPD = hDEFSPD;
    }
}
