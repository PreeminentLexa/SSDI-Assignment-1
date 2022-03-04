package Decorations;

import Dummy.Actor;
import WeaponFunctionality.Weapon;
import WeaponFunctionality.WeaponData;

public abstract class BaseDataDecorator extends WeaponData {
    private WeaponData subject;

    /** BaseDataDecorator() - The constructor
     * Because a BaseDataDecorator extends WeaponData, it can be cast to WeaponData, so it can be stored in a WeaponData object.
     * This means that the BaseDataDecorator can replace the weapon data it is storing, and just pass all method calls through to the contained instance.
     * Much like a Russian nesting doll, the stored "WeaponData" can just be another data decorator. Calling a method on the outermost instance,
     * will pass the request inwards through each layer of decoration, at each layer, the specific decorator can change the input or output, or simply return something.
     * E.G. the "ExclamationPointDecorator" can add an '!' to the end of the getName() which is returned by its stored subject.
     * @param subject A WeaponData, either the WeaponData which is being decorated, or the outermost decoration of a WeaponData
     */
    public BaseDataDecorator(WeaponData subject){
        this.subject = subject;
    }

    /** getSubject() - Getter object for the Weapon that this is decorating.
     * @return A Weapon, the weapon that is stored
     */
    public WeaponData getSubject(){
        return this.subject;
    }

    /** getUnmoddedRoot() - This is a recursive method, which will look at each level of decoration, until it finds the one which stores a WeaponData.
     * This allows calling a method without ANY interference from any decorators.
     * @return A WeaponData, the Weapon at the center of all the decorators
     */
    public WeaponData getUnmoddedRoot(){
        if(this.subject instanceof BaseDataDecorator){
            return ((BaseDataDecorator)this.subject).getUnmoddedRoot();
        }
        return this.subject;
    }

    // DEFAULT STUFF
    // These methods override all methods in WeaponData, so that instead of effecting this instance's super class, it will effect this instance's stored subject WeaponData.
    // These can be overridden by subclasses in order to either alter, or replace, the subject's output

    /** setParent() - Setter for the parent variable
     * @param parent A Weapon, the weapon that this is the data of
     */
    public void setParent(Weapon parent){this.subject.setParent(parent);}

    /** getParent - Getter for the parent variable
     * @return A Weapon, the weapon that this is the data of
     */
    public Weapon getParent(){return this.subject.getParent();}

    /** toString() - Used to convert the weapon to a string
     * @return - A String, the name of the weapon, possibly with a suffix
     */
    public String toString(){return this.subject.toString();}

    /** printInfo() - Prints information about the gun
     * @param linePrefix - A prefix to add to the start of each line, for example "\t"
     */
    public void printInfo(String linePrefix){this.subject.printInfo(linePrefix);}

    /** getName() - This acts like a getter for the name variable, but because of Java's (dumb) inheritance, I can't just have private member variables
     * @return A String, the name of this weapon
     */
    public String getName(){return this.subject.getName();}

    /** getSuffix() - This acts like a getter for the suffix variable, but because of Java's (dumb) inheritance, I can't just have private member variables. The suffix is mostly "", but Legendary and Unique decorators alter this
     * @return A String, the suffix of this weapon
     */
    public String getSuffix(){return "";}

    /** getGenericDamage() - This acts like a getter for the damage variable - it's called generic because I was going to do more with damage types - but because of Java's (dumb) inheritance, I can't just have private member variables
     * @return An integer, the damage of this weapon
     */
    public int getGenericDamage(){return this.subject.getGenericDamage();}

    /** getGenericDamageText() - This is essentially unused. It was going to be used to style different damage types in different ways
     * @param dmg An int, this is the damage which has been dealt. It is passed, because it may have been altered from the generic damage
     * @return A String, the print out of the damage of this weapon
     */
    public String getGenericDamageText(int dmg){return this.subject.getGenericDamageText(dmg);}

    /** getROF() - This acts like a getter for the rate of fire variable, but because of Java's (dumb) inheritance, I can't just have private member variables.
     * @return A float, the rate of fire of this gun, in seconds between each shot
     */
    public float getROF(){return this.subject.getROF();}

    /** calculateDamage() - Used to calculate the damage which should be dealt to the given Actor. This is usually altered by decorators
     * @param hit An Actor, this is the actor who has been hit. It is passed, because sometimes information about the hit Actor will change the damage dealt (for example the BugSlayer decorator increases the damage only if the hit Actor is a bug)
     * @return An int, the damage which has been dealt
     */
    public int calculateDamage(Actor hit){return this.subject.calculateDamage(hit);}

    /** getAttackString() - Used to format the text output of hitting an Actor
     * @param hit An Actor, this is the actor who has been hit
     * @param damage An int, the damage which has been dealt
     * @return A String, the formatted output
     */
    public String getAttackString(Actor hit, int damage){return this.subject.getAttackString(hit, damage);}

    /** getReloadTime() - This acts like a getter for the reload time variable, but because of Java's (dumb) inheritance, I can't just have private member variables.
     * @return A float, the time it takes to reload this gun, returned as seconds
     */
    public float getReloadTime(){return this.subject.getReloadTime();}

    /** getMaxClip() - This acts like a getter for the max clip variable, but because of Java's (dumb) inheritance, I can't just have private member variables.
     * @return An int, the amount of bullets in a clip
     */
    public int getMaxClip(){return this.subject.getMaxClip();}

    /** defaultClipCount() - This acts like a getter for the default clip count variable, but because of Java's (dumb) inheritance, I can't just have private member variables.
     * @return An int, the amount of clips this gun should have when it's created (unused)
     */
    public int defaultClipCount(){return this.subject.defaultClipCount();}

    /** getNumShots() - This acts like a getter for the numshots variable, but because of Java's (dumb) inheritance, I can't just have private member variables.
     * @return An int, the amount of bullets fired on each shot. (used for shotguns to have pellets)
     */
    public int getNumShots(){return this.subject.getNumShots();}

    /** ROFTextOverride() - Used to change the text that a melee weapon will display for it's speed
     * @return A String or null. If it's null, then it will use the default methods inside WeaponMelee
     */
    public String ROFTextOverride(){return this.subject.ROFTextOverride();}
}
