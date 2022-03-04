package Dummy;

import java.util.Random;

public class Actor {
    private static Random randomizer = new Random();
    public static Actor targetPractice = new Actor("Target Practice");

    private String name;

    /** Actor() - Override of Actor(String) constructor which makes the default name: ""
     */
    public Actor(){
        this("");
    }

    /** Actor() - The constructor for the Actor class
     * @param name A string, the name for this object, just for easy identification later. It's only ever "Target Practice" or "Weapon Owner"
     */
    public Actor(String name){
        this.name = name;
        this.randomize(false); // initialize the random stats
    }

    /** toString() - Converts this object into a more easily readable string, which says that it is a Dummy.Actor, and prints the readable id which was assigned for it at creation
     * @return A String, the string version of this object
     */
    public String toString(){
        return "Dummy.Actor: \""+name+"\"";
    }

    private boolean human;
    private boolean bug;
    private boolean animal;
    private boolean ghoul;
    private boolean synth;
    private boolean superMutant;
    private int health;

    public void randomize(){randomize(true);}
    public void randomize(boolean print){
        this.human = Actor.randomizer.nextBoolean(); // random each value
        this.bug = Actor.randomizer.nextBoolean();
        this.ghoul = Actor.randomizer.nextBoolean();
        this.synth = Actor.randomizer.nextBoolean();
        this.superMutant = Actor.randomizer.nextBoolean();
        this.health = Actor.randomizer.nextInt(90)+10;
        if(print){
            System.out.println("============================"); // print info
            System.out.println(this+" has been randomized.");
            System.out.println("\t"+this+" now has "+this.health+" health.");
            if(this.isHuman()){
                System.out.println("\t"+this+" is now a human.");
            }
            if(this.isBug()){
                System.out.println("\t"+this+" is now a bug.");
            }
            if(this.isAnimal()){
                System.out.println("\t"+this+" is now an animal.");
            }
            if(this.isGhoul()){
                System.out.println("\t"+this+" is now a ghoul.");
            }
            if(this.isSynth()){
                System.out.println("\t"+this+" is now a synth.");
            }
            if(this.isSuperMutant()){
                System.out.println("\t"+this+" is now a super mutant.");
            }
            System.out.println("============================");
        }
    }

    /** setHuman() - Dummy method used for development. Sets the human variable until it is next randomized
     * @param val A boolean, the new value
     */
    public void setHuman(boolean val){this.human = val;}
    /** setBug() - Dummy method used for development. Sets the bug variable until it is next randomized
     * @param val A boolean, the new value
     */
    public void setBug(boolean val){this.bug = val;}
    /** setAnimal() - Dummy method used for development. Sets the animal variable until it is next randomized
     * @param val A boolean, the new value
     */
    public void setAnimal(boolean val){this.animal = val;}
    /** setGhoul() - Dummy method used for development. Sets the ghoul variable until it is next randomized
     * @param val A boolean, the new value
     */
    public void setGhoul(boolean val){this.ghoul = val;}
    /** setSynth() - Dummy method used for development. Sets the synth variable until it is next randomized
     * @param val A boolean, the new value
     */
    public void setSynth(boolean val){this.synth = val;}
    /** setSuperMutant() - Dummy method used for development. Sets the superMutant variable until it is next randomized
     * @param val A boolean, the new value
     */
    public void setSuperMutant(boolean val){this.superMutant = val;}
    /** setHealth() - Dummy method used for development. Sets the health variable until it is next randomized
     * @param val A boolean, the new value
     */
    public void setHealth(int val){this.health = val;}

    
    /** isHuman() - Dummy method used for development. Pretends to check if the Actor is a human, but this is randomized
     * @return A boolean, the most recently randomized human variable
     */
    public boolean isHuman(){return this.human;}
    
    /** isBug() - Dummy method used for development. Pretends to check if the Actor is a bug, but this is randomized
     * @return A boolean, the most recently randomized bug variable
     */
    public boolean isBug(){return this.bug;}
    
    /** isAnimal() - Dummy method used for development. Pretends to check if the Actor is an animal, but this is randomized
     * @return A boolean, the most recently randomized animal variable
     */
    public boolean isAnimal(){return this.animal;}
    
    /** isGhoul() - Dummy method used for development. Pretends to check if the Actor is a ghoul, but this is randomized
     * @return A boolean, the most recently randomized ghoul variable
     */
    public boolean isGhoul(){return this.ghoul;}
    
    /** isSynth() - Dummy method used for development. Pretends to check if the Actor is a synth, but this is randomized
     * @return A boolean, the most recently randomized synth variable
     */
    public boolean isSynth(){return this.synth;}
    
    /** isSuperMutant() - Dummy method used for development. Pretends to check if the Actor is a superMutant, but this is randomized
     * @return A boolean, the most recently randomized superM variable
     */
    public boolean isSuperMutant(){return this.superMutant;}

    private int Strength = 5;
    private int Perception = 5;
    private int Endurance = 5;
    private int Charisma = 5;
    private int Intelligence = 5;
    private int Agility = 5;
    private int Luck = 5;

    /** getStrength() - Dummy method used for development. Pretends to get a stat, but really just gets the saved one
     * @return An int, returns the Strength, which is always 5
     */
    public int getStrength(){return Strength;}
    /** getPerception() - Dummy method used for development. Pretends to get a stat, but really just gets the saved one
     * @return An int, returns the Perception, which is always 5
     */
    public int getPerception(){return Perception;}
    /** getEndurance() - Dummy method used for development. Pretends to get a stat, but really just gets the saved one
     * @return An int, returns the Endurance, which is always 5
     */
    public int getEndurance(){return Endurance;}
    /** getCharisma() - Dummy method used for development. Pretends to get a stat, but really just gets the saved one
     * @return An int, returns the Charisma, which is always 5
     */
    public int getCharisma(){return Charisma;}
    /** getIntelligence() - Dummy method used for development. Pretends to get a stat, but really just gets the saved one
     * @return An int, returns the Intelligence, which is always 5
     */
    public int getIntelligence(){return Intelligence;}
    /** getAgility() - Dummy method used for development. Pretends to get a stat, but really just gets the saved one
     * @return An int, returns the Agility, which is always 5
     */
    public int getAgility(){return Agility;}
    /** getLuck() - Dummy method used for development. Pretends to get a stat, but really just gets the saved one
     * @return An int, returns the Luck, which is always 5
     */
    public int getLuck(){return Luck;}

    /** die() - Dummy method used for development. Pretends to kill the Actor
     */
    public void die(){
        this.health = 0;
        System.out.println(this+" died.");
    }
    
    /** takeDamage() - Dummy method used for development. Pretends to damage the Actor
     * @param damage An int, the damage to take (negative numbers will heal)
     */
    public void takeDamage(int damage){
        this.health = this.health - damage;
        System.out.println(this+" took "+damage+" damage.");
        if(this.health <= 0){
            this.die();
        }
    }
}
