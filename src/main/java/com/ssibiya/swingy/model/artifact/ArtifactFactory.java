package com.ssibiya.swingy.model.artifact;

public abstract class ArtifactFactory {
    public static Artifact newArtifact(String type)
    {
        switch(type)
        {
            case "Weapon":
                return new Ki();
            case "Armor":
                return new Teleport();
            case "Helm":
                return new Sensu_Bean();
            default:
                return null;
        }
    }
}
