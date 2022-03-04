package WeaponFunctionality;

import Decorations.BaseDataDecorator;
import Dummy.Actor;

public abstract class WeaponData {
    private Weapon parent; // The weapon this is the data of

    /** setParent() - Setter for the parent variable
     * @param parent A Weapon, the weapon that this is the data of
     */
    public void setParent(Weapon parent){this.parent = parent;}

    /** getParent - Getter for the parent variable
     * @return A Weapon, the weapon that this is the data of
     */
    public Weapon getParent(){return this.parent;}

    /** toString() - Used to convert the weapon to a string
     * @return - A String, the name of the weapon, possibly with a suffix
     */
    public String toString(){
        return this.getParent()+" : "+this.getClass(); // print the parent's toString, then this object's class
    }

    /** printInfo() - Prints information about the gun
     * @param linePrefix - A prefix to add to the start of each line, for example "\t"
     */
    public void printInfo(String linePrefix){}

    /** getName() - This acts like a getter for the name variable, but because of Java's (dumb) inheritance, I can't just have private member variables
     * @return A String, the name of this weapon
     */
    public abstract String getName();

    /** getName() - This acts like a getter for the suffix variable, but because of Java's (dumb) inheritance, I can't just have private member variables. The suffix is mostly "", but Legendary and Unique decorators alter this
     * @return A String, the suffix of this weapon
     */
    public String getSuffix(){return "";}

    /** getGenericDamage() - This acts like a getter for the damage variable - it's called generic because I was going to do more with damage types - but because of Java's (dumb) inheritance, I can't just have private member variables
     * @return An integer, the damage of this weapon
     */
    public abstract int getGenericDamage();

    /** getGenericDamageText() - This is essentially unused. It was going to be used to style different damage types in different ways
     * @param dmg An int, this is the damage which has been dealt. It is passed, because it may have been altered from the generic damage
     * @return A String, the print out of the damage of this weapon
     */
    public String getGenericDamageText(int dmg){
        if(this.getNumShots() > 1){
            return this.getNumShots()+"x"+dmg;
        }
        return Integer.toString(dmg);
    }

    /** getROF() - This acts like a getter for the rate of fire variable, but because of Java's (dumb) inheritance, I can't just have private member variables.
     * @return A float, the rate of fire of this gun, in seconds between each shot
     */
    public abstract float getROF();

    /** calculateDamage() - Used to calculate the damage which should be dealt to the given Actor. This is usually altered by decorators
     * @param hit An Actor, this is the actor who has been hit. It is passed, because sometimes information about the hit Actor will change the damage dealt (for example the BugSlayer decorator increases the damage only if the hit Actor is a bug)
     * @return An int, the damage which has been dealt
     */
    public int calculateDamage(Actor hit){
        return this.getGenericDamage();
    }

    /** getAttackString() - Used to format the text output of hitting an Actor
     * @param hit An Actor, this is the actor who has been hit
     * @param damage An int, the damage which has been dealt
     * @return A String, the formatted output
     */
    public String getAttackString(Actor hit, int damage){
        return this.getParent()+" dealt "+this.getGenericDamageText(damage)+" damage to "+hit+".";
    }

    /** getReloadTime() - This acts like a getter for the reload time variable, but because of Java's (dumb) inheritance, I can't just have private member variables.
     * @return A float, the time it takes to reload this gun, returned as seconds
     */
    public float getReloadTime(){return 0;}

    /** getMaxClip() - This acts like a getter for the max clip variable, but because of Java's (dumb) inheritance, I can't just have private member variables.
     * @return An int, the amount of bullets in a clip
     */
    public int getMaxClip(){return 0;}

    /** defaultClipCount() - This acts like a getter for the default clip count variable, but because of Java's (dumb) inheritance, I can't just have private member variables.
     * @return An int, the amount of clips this gun should have when it's created (unused)
     */
    public int defaultClipCount(){return 0;}

    /** getNumShots() - This acts like a getter for the numshots variable, but because of Java's (dumb) inheritance, I can't just have private member variables.
     * @return An int, the amount of bullets fired on each shot. (used for shotguns to have pellets)
     */
    public int getNumShots(){return 1;}

    /** ROFTextOverride() - Used to change the text that a melee weapon will display for it's speed
     * @return A String or null. If it's null, then it will use the default methods inside WeaponMelee
     */
    public String ROFTextOverride(){return null;}
}
