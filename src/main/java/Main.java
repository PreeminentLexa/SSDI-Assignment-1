import Decorations.DefinedDecorations.*;
import Decorations.LegendaryDecorator;
import DefinedWeapons.*;
import Dummy.Actor;
import WeaponFunctionality.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Create an entirely unmodified weapon, just as a demo
        Weapon sampleRegularWeapon = DoubleBarrelShotgun.create(); // create a Double Barrel
        System.out.println("sampleRegularWeapon:");
        sampleRegularWeapon.printInfo("\t"); // print its info


        // Create an entirely unmodified weapon, which will be given legendary later
        Weapon sampleRegularWeaponToBeMadeLegendary = TripleBarrelShotgun.create(); // create a Triple Barrel
        System.out.println("\n\nsampleRegularWeaponToBeMadeLegendary:");
        sampleRegularWeaponToBeMadeLegendary.printInfo("\t"); // print its info
        sampleRegularWeaponToBeMadeLegendary = new LegendaryDecorator_TwoShot(sampleRegularWeaponToBeMadeLegendary); // give the Triple Barrel the legendary wrapper "Two Shot"
        System.out.println("\n\nsampleRegularWeaponToBeMadeLegendary (after being made legendary):");
        sampleRegularWeaponToBeMadeLegendary.printInfo("\t"); // print its info again

        // Create some legendary weapons, which start as legendary
        Weapon sampleLegendaryWeapon_BugSlayer = new LegendaryDecorator_BugSlayer(SuperSledge.create()); // create a Super Sledge with a Bug Slayer wrapper
        Weapon sampleLegendaryWeapon_Medic = new LegendaryDecorator_Medic(TripleBarrelShotgun.create()); // create a Triple Barrel with a Medic wrapper
        System.out.println("\n\nsampleLegendaryWeapon_BugSlayer:");
        sampleLegendaryWeapon_BugSlayer.printInfo("\t"); // print its info
        System.out.println("\n\nsampleLegendaryWeapon_Medic:");
        sampleLegendaryWeapon_Medic.printInfo("\t"); // print its info

        // create a weapon with layers of legendary abilities
        Weapon sampleVeryLegendaryWeapon = new LegendaryDecorator_Assassin( // wrap the knife in a Assassin wrapper
                                            new LegendaryDecorator_Hunter( // wrap the knife in a Hunter wrapper
                                            new LegendaryDecorator_GhoulSlayer( // wrap the knife in a Ghoul Slayer wrapper
                                            new LegendaryDecorator_BugSlayer( // wrap the knife in a Bug Slayer wrapper
                                                Knife.create() // create a knife
                                            ))));
        System.out.println("\n\nsampleVeryLegendaryWeapon:");
        sampleVeryLegendaryWeapon.printInfo("\t"); // print its info

        // Create a weapon with unique properties
        Weapon sampleUniqueWeapon = new UniqueDecorator(PipeRifle.create(), UniqueDecorator.stats() // wrap a new pipe rifle in a unique decorator, with a new decorator stat holder
                .setName("Lexa's Water Pistol") // set the name of the stat holder to "Lexa's Water Pistol"
                .setDamage(0) // set the damage of the stat holder to be 0
                .setClipSize(9999) // set the clip size of the stat holder to be 9999
                .setROF(0.01f)); // set the rate of fire of the stat holder to allow firing once every 0.01 seconds

        System.out.println("\n\nsampleUniqueWeapon:");
        sampleUniqueWeapon.printInfo("\t"); // print its info

        // run a looping display of being able to add a legendary wrapper to a gun
        createLegendaryTest();
    }
    private static void createLegendaryTest(){ // this is how the loop is reset
        // create a weapon which can be modified over and over again 
        Weapon sampleLegendaryPicker = new UniqueDecorator(PipeRifle.create(), UniqueDecorator.stats() // create a pipe rifle, and wrap it in a unique decorator, with a new stat holder
                .setName("Legendary Selector")); // set the name of the stat holder to "Legendary Selector"

        // start the test with this weapon
        createLegendaryTest(sampleLegendaryPicker);
    }
    private static void createLegendaryTest(Weapon sampleLegendaryPicker){

        System.out.println("\n\nsampleLegendaryPicker:");
        sampleLegendaryPicker.printInfo("\t"); // print its info

        System.out.println("Pick a legendary decorator to add to sampleLegendaryPicker:");
        String[] options = new String[]{ // print some options
                "Start again with a fresh Legendary Selector Pipe Rifle",
                "Assassin",
                "Bug Slayer",
                "Ghoul Slayer",
                "Hunter",
                "Lucky",
                "Medic",
                "Two Shot",
                "Unmodified",
        };
        for(int i = 0;i < options.length;i++){
            System.out.println(i+". "+options[i]);
        }
        boolean hasAnswer = false;
        int result = 0;
        Scanner scanner = new Scanner(System.in);
        while(!hasAnswer){ // loop until we have an answer
            result = getIntInLine(scanner.nextLine()); // convert the line to a number, -1 if no number could be parsed
            if(0 > result) { // number couldn't be parsed (or was out of range, but negative numbers shouldn't be read)
                System.out.println("A number wasn't found in your answer. Please try again");
            } else if(options.length < result){ // number was out of the range of the options
                System.out.println("The number found in your answer was too high. Please try again");
            } else { // we've got a real answer
                hasAnswer = true;
            }
        }
        switch(options[result]){ // check WHICH answer was selected (this could keep the result as an index, but I think it's easier to read if we move it back to being text)
            case "Start again with a fresh Legendary Selector Pipe Rifle":
                createLegendaryTest(); // reset the gun and start the loop again
                return;
            case "Assassin":
                sampleLegendaryPicker = new LegendaryDecorator_Assassin(sampleLegendaryPicker); // wrap the gun in an "Assassin" wrapper
                break;
            case "Bug Slayer":
                sampleLegendaryPicker = new LegendaryDecorator_BugSlayer(sampleLegendaryPicker); // wrap the gun in a "Bug Slayer" wrapper
                break;
            case "Ghoul Slayer":
                sampleLegendaryPicker = new LegendaryDecorator_GhoulSlayer(sampleLegendaryPicker); // wrap the gun in a "Ghoul Slayer" wrapper
                break;
            case "Hunter":
                sampleLegendaryPicker = new LegendaryDecorator_Hunter(sampleLegendaryPicker); // wrap the gun in a "Hunter" wrapper
                break;
            case "Lucky":
                sampleLegendaryPicker = new LegendaryDecorator_Lucky(sampleLegendaryPicker); // wrap the gun in a "Lucky" wrapper
                break;
            case "Medic":
                sampleLegendaryPicker = new LegendaryDecorator_Medic(sampleLegendaryPicker); // wrap the gun in a "Medic" wrapper
                break;
            case "Two Shot":
                sampleLegendaryPicker = new LegendaryDecorator_TwoShot(sampleLegendaryPicker); // wrap the gun in a "Two Shot" wrapper
                break;
        }
        System.out.println("You have given sampleLegendaryPicker "+options[result]); // tell the user what's happened
        sampleLegendaryPicker.printInfo(); // print the guns info
        int betweenShots = 5;
        int shotCount = 5;
        testWeapon(sampleLegendaryPicker,betweenShots,shotCount); // shoot the gun 5 times, with a 5 second delay between each (otherwise ROF would get in the way)
        System.out.println("Do you want to go again? (type y/n)"); // check if the user wants to go again
        String answer = scanner.nextLine().trim().substring(0,1).toLowerCase();
        if(answer.equals("y")) {
            createLegendaryTest(sampleLegendaryPicker); // loop with the end result gun, so we can get lots of wrappers around one another
        }
    }
    private static int getIntInLine(String line){
        if(line.matches("^\\d+$")) { // if we've only got a number
            return Integer.parseInt(line); // return that number
        }
        String splitNumbers = line.replaceAll("[^\\d]+"," ").replaceAll(" +"," "); // replace non numbers with spaces, then replace all strings of lots of spaces with a single one. This results in a set of numbers with spaces separating those which were separated in the string
        if(splitNumbers.length() > 1){
            String[] allNumbers = splitNumbers.split(" ");
            if(allNumbers[0].length() == 0){ // our string didn't start with a number, so the first index is an empty string (this might not happen with Java, but it happens with PHP and I'm running out of time rapidly)
                allNumbers[0] = allNumbers[1];
            }
            return Integer.parseInt(splitNumbers.split(" ")[0]); // just throw back the first number we have
        }
        return -1; // we have no number
    }
    private static void testWeapon(Weapon weapon, int betweenShots, int shotCount){
        try{
            Actor.targetPractice.randomize(); // randomize the target practice dummy
            weapon.attack(); // use the weapon to attack
            System.out.println("\n\n\n\n");
            if(shotCount <= 1){return;} // if we've done all the loops we want to, then return
            TimeUnit.SECONDS.sleep(betweenShots); // wait between shots to avoid getting stopped by the rate of fire
            testWeapon(weapon, betweenShots, shotCount-1); // recursively loop whilst decrementing shotcount
        } catch(InterruptedException ie){} // I don't care
    }
}
