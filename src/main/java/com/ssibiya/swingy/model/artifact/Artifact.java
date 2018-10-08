package com.ssibiya.swingy.model.artifact;


import lombok.Data;
import lombok.Getter;

@Data
public class Artifact {
    @Getter
    protected String name;
    protected String type;
    protected int power;

    Artifact(int pwr, String nme, String typ){
        this.name = nme;
        this.type = typ;
        this.power = pwr;
    }
}
