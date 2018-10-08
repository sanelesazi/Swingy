package com.ssibiya.swingy.view.console;

import com.ssibiya.swingy.model.artifact.Artifact;
import com.ssibiya.swingy.model.characters.HeroInfo;
import com.ssibiya.swingy.model.characters.heroes.Hero;
import com.ssibiya.swingy.model.characters.villains.Villain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.ResultSet;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class StartScreen extends View
{
    public void printWelcomeScreen()
    {
        System.out.println(GREEN + "Welcome to DBZ Swingy");
        System.out.println("It is a world of strength, power and survival");
        System.out.println("Be smart about your hero choice");
        System.out.println("Everyone is out to get you!\n" + RESET);
    }

    private void gameExit() {
        System.out.println("Exiting game...");
    }

    public HeroInfo characterChoice(ResultSet dbResult, List<HeroInfo> heroInfo)
    {
        int i = 0;
        int choice;
        String strChoice;
        HeroInfo hi;
        try{
            System.out.println(YELLOW + "Select from previously saved heroes:" + RESET);
            while (dbResult.next()){
                String dbName = dbResult.getString("name");
                String dbType = dbResult.getString("type");
                int dblevel = dbResult.getInt("level");
                int dbHP = dbResult.getInt("hitpoints");
                int dbXP = dbResult.getInt("experience");
                int dbATT = dbResult.getInt("attack");
                int dbDEF = dbResult.getInt("defense");
                int dbSPD = dbResult.getInt("speed");
                int dbATTSPD = dbResult.getInt("attspd");
                int dbDEFSPD = dbResult.getInt("defSpd");

                hi = new HeroInfo(dbName, dbType, dblevel, dbHP, dbXP, dbATT, dbDEF, dbSPD, dbATTSPD, dbDEFSPD);
                heroInfo.add(hi);
                System.out.println(RED + dbResult.getInt("id") + ") " + dbResult.getString("name") + RESET);
                i++;
            }
            if (i > 0){
                while (true) {
                    System.out.println(YELLOW + "Select numbers from 1 - " + i + " | Press C to create a new hero" + RESET);
                    try{
                        strChoice = getInput();
                        if (strChoice.equalsIgnoreCase("C"))
                            return null;
                        else
                            choice = Integer.parseInt(strChoice);
                        if (choice <= i){
                            System.out.println(YELLOW + "Welcome Back " + heroInfo.get(choice -1).getHeroName() + RESET);
                            return heroInfo.get(choice - 1);
                        }
                    }catch ( NumberFormatException e){
                        System.out.println("Invalid input, please try again " + e.getMessage());
                    }
                }
            }
        }catch (Exception e){System.out.println("No Heroes found in Database ");}
        return null;
    }

    public void printSuccessDB(String type){
        switch(type){
            case "Database":
                System.out.println(YELLOW + "Successfully connected to database Swingy" + RESET);
                break ;
            case "Table":
                System.out.println(YELLOW + "Successfully created Heroes table" + RESET);
                break ;
            case "Insert":
                System.out.println(YELLOW + "Successfully loaded hero" + RESET);
                break ;
            case "Update":
                System.out.println(YELLOW + "Successfully saved progress" + RESET);
                break ;
        }
    }

    public String printCharacterSelection()
    {
        String characterType;
        String [] heroTypes = {"Saiyan", "Namekian", "Earthling"};

        while (true) {
            System.out.println(YELLOW + "CREATE NEW: Select Hero Type" + RESET);
            System.out.println("[1] Saiyan | [2] Namekian | [3] Earthling - Input relevant (1-3) number");
            System.out.println(RED + " Press Q to Quit" + RESET);
            characterType = getInput();
            switch (characterType)
            {
                case "1":
                    return heroTypes[Integer.parseInt(characterType) - 1];
                case "2":
                    return heroTypes[Integer.parseInt(characterType) - 1];
                case "3":
                    return heroTypes[Integer.parseInt(characterType) - 1];
                case "Q":
                    gameExit();
                    return null;
                case "q":
                    gameExit();
                    return null;
                    default:
                        System.out.println(RED + "Invalid Input | Please select using numbers 1-3" + RESET);
            }
        }
    }

    public String printEnterName()
    {
        String characterName;
        while (true)
        {
            System.out.println("Enter your character name: ");
            System.out.println(RED + " Press Q to Quit | B to go back to hero selection" + RESET);
            characterName = getInput();
            if (characterName.length() >= 3 && characterName.length() <= 16)
                return characterName;
            else if (characterName.equalsIgnoreCase("Q")) {
                gameExit();
                return null;
            }
            else if (characterName.equalsIgnoreCase("B"))
                return characterName;
            else
                System.out.println("Enter a name with 3 - 16 characters");
        }
    }

    public void printWelcome(String characterType, String characterName)
    {
        System.out.println(YELLOW + "Welcome " + characterType + " " + characterName + " | Look out for villains");
        System.out.println("Movement keys: W (North), S (South), A (West), D (East)" + RESET);
    }

    public void printMap(char [][]map)
    {
        System.out.println(YELLOW + "Movement keys: W (North), S (South), A (West), D (East) || Q to quit");
        for (char [] row: map)
        {
            for (char block: row) {
                if (block == '0')
                    System.out.print("[" + block + "]");
                else if (block == 'C' || block == 'F' || block == 'M' || block == 'T' || block == 'J' || block == 'H') //Villains
                    System.out.print(RED + "[" + block + "]" + RESET);
                else if (block == 'A')
                    System.out.print(GREEN + "[" + block + "]" + RESET);
            }
            System.out.println();
        }
    }

    public void promptFightFlee(Villain villain)
    {
        System.out.println(YELLOW + "You have encountered a " + villain.getType() + " villain");
        System.out.println("Will you fight or flee " + villain.getType() + " Press F to Fight, Press R to Flee" + RESET);
    }

    public void successfulFlee(Villain villain)
    {
        System.out.println(GREEN + "You successfully fled from " + villain.getName() + RESET);
    }

    public void tookArtifact(int decision, Villain villain)
    {
        if (decision == 1) {
            System.out.println(YELLOW + "You picked up " + villain.getArtifact().getName() + "(" + villain.getArtifact().getType() + ")");
            System.out.println(">>>>>>>>>>>>>>>> YOU ARE NOW EQUIPPED WITH " + villain.getArtifact().getType().toUpperCase() + " <<<<<<<<<<<<<<<<" + RESET);
        }
        else
            System.out.println(YELLOW + "You left the " + villain.getArtifact().getName() + "(" + villain.getArtifact().getType() + ")" + RESET);
    }

    public void failedFlee(Villain villain)
    {
        System.out.println(RED + "Unable to flee from " + villain.getName());
        System.out.println(YELLOW + "Prepare to fight!!" + RESET);
    }

    public void fight(int i, Hero player, Villain enemy)
    {
        if (i == 0)
            System.out.println(RED +player.getName() + " Missed" + RESET);
        else if (i == 1) {
            System.out.println(GREEN + player.getName() + RESET + player.getPhrase() + enemy.getType());
            System.out.println(YELLOW + enemy.getName() + " Got hit!" + RESET);
        }
    }

    public void retaliate(int i, Villain enemy, Hero player)
    {
        if (i == 1) {
//            if (enemy.getType().equalsIgnoreCase("Frieza"))
//                System.out.println(RED + enemy.getName() + RESET + enemy.getPhrase() + player.getType());
//            else
            System.out.println("\t\t\t\t\t\t\t\t" + RED + enemy.getName() + RESET + enemy.getPhrase() + player.getType());
            System.out.println("\t\t\t\t\t\t\t\t" +YELLOW + player.getName() + " Got hit!" + RESET);
        }
        else
            System.out.println("\t\t\t\t\t\t\t\t" + RED + enemy.getName() + " Missed" + RESET);
    }

    public void printStats(Villain villain, Hero player)
    {
        System.out.println(YELLOW + "========================================================" + RESET);
        System.out.println(GREEN + player.getName() + "\t\t\t\t\t\t\t\t\t" + RED + villain.getName() + RESET); //name title
        System.out.println(GREEN + "HP: " + player.getHitpoints() + "\t\t\t\t\t\t\t\t\t" + RED + "HP: " + villain.getHitpoints() + RESET);
        System.out.println(GREEN + "LVL: " + player.getLevel() + "\t\t\t\t\t\t\t\t\t" + RED + "LVL " + villain.getLevel() + RESET);
        try {
            System.out.println(GREEN + "XP: " + player.getExperience() + "\t\t\t\t\t\t\t\t\t" + PURPLE + "Equipped: " + villain.getArtifact().getName() + RESET);
        }catch (NullPointerException e){
            System.out.println(GREEN + "XP: " + player.getExperience() + "\t\t\t\t\t\t\t\t\t" + PURPLE + "Equipped: Nothing" + RESET);
        }
    }

    public void printActions(Hero player)
    {
        int numberOfPowers = 1;

        if (player.getHitpoints() == 0)
            System.out.println(YELLOW + "\nPress relevant number for attack " + player.getName().toUpperCase() + " IS ON HIS LAST BREATH" + RESET);
        else
            System.out.println(YELLOW + "\nPress relevant number for attack" + RESET);
        System.out.print(GREEN + "(" + numberOfPowers + ") " + RESET + player.getPower() + " ");
        numberOfPowers++;
        for (Artifact af: player.getArtifacts())
        {
            System.out.print(GREEN + "(" + numberOfPowers + ") " + RESET + af.getName() + " ");
            numberOfPowers++;
        }
        System.out.println(RED + "\nPress 'R' to flee fight" + RESET);
    }

    public void doAction(Artifact af, Hero player)
    {
        switch (af.getType())
        {
            case "Weapon":
                System.out.println(YELLOW + player.getName() + " Used " + af.getName() + " To gain attack points by " + af.getPower() + RESET);
                break ;
            case "Armor":
                System.out.println(YELLOW + player.getName() + " Used " + af.getName() + " To gain defense points by " + af.getPower() + RESET);
                break ;
            case "Helm":
                System.out.println(YELLOW + player.getName() + " Used " + af.getName() + " To gain hp points by " + af.getPower() + RESET);
                break ;
                default:
                    System.out.println(YELLOW + "No action performed" + RESET);
        }
    }

    public void printArtifactDropped(Villain villain)
    {
        System.out.println(RED + villain.getName() + " Dropped " + GREEN + villain.getArtifact().getName());
        System.out.println("Would you like to pick up artifact?" + RESET);
        System.out.println(RED + "NO (N) " + RESET + "| " + GREEN + "YES (Y)" + RESET);
    }

    public void printSummary(Villain villain, Hero player)
    {
        int displayXP = 0;

        displayXP = (villain.getDefense() * villain.getAttack()) / 2;
        if (player.levelledUp)
            System.out.println(GREEN + player.getName().toUpperCase() + " TRANSFORMED TO LEVEL: " + player.getLevel());
        System.out.println(YELLOW + "============== You gained some experience ==============" + RESET);
        System.out.println("============== GAINED ========================= TOTAL");
        System.out.println(GREEN + "XP:\t\t+" + displayXP + "\t\t\t\t" + player.getExperience ());
        System.out.println("HP:\t\t+" + villain.getDefense() + "\t\t\t\t" + player.getHitpoints());
        System.out.println("ATT:\t\t+" +  villain.getAttack() / 3 + "\t\t\t\t" + player.getAttack());
        System.out.println("DEF:\t\t+" + villain.getDefense() / 3 + "\t\t\t\t" + player.getDefense());
        System.out.println("SP.ATT:\t\t+" + villain.getAtt_speed() / 3 + "\t\t\t\t" + player.getAtt_speed());
        System.out.println("SP.DEF:\t\t+" + villain.getDef_speed() / 3 + "\t\t\t\t" + player.getDef_speed());
        System.out.println("SPEED:\t\t+" + villain.getSpeed() / 4 + "\t\t\t\t" + player.getSpeed());
        System.out.println(YELLOW + "=================== Press C to continue ================" + RESET);
    }

    public void printError(String err){
        System.out.println(RED + "Error: " + err + RESET);
    }

    public void invalidInput(int i)
    {
        if (i == 1)
            System.out.println(RED + "Invalid input, press relevant key" + RESET);
        else if (i == 2)
            System.out.println(RED + "You missed because of invalid input" + RESET);
        else if (i == 3)
            System.out.println(RED + "Invalid input, movement keys: W (North), S (South), A (West) and D (East)" + RESET);
    }

    public void printWin(Villain villain)
    {
        System.out.println();
        System.out.println(GREEN + "=================================================================================");
        System.out.println("\t\tYou emerged victorious against " + villain.getName());
        System.out.println("=================================================================================" + RESET);
        System.out.println();
    }

    public void promptRetry()
    {
        System.out.println(GREEN + "WOULD YOU LIKE TO RETRY?");
        System.out.println(YELLOW + "Press Y (Yes) to retry or N (No) to Exit game" + RESET);
    }

    public void gameOver(Villain villain)
    {
        System.out.println();
        System.out.println("=================================================================================");
        System.out.println(YELLOW + "Mission failed, you were killed by " + villain.getName());
        System.out.println(RED + "GAME OVER!" + RESET);
    }

}
