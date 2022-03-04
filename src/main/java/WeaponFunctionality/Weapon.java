package WeaponFunctionality;

import Decorations.BaseDecorator;
import Dummy.Actor;

import java.time.Duration;
import java.time.Instant;

public abstract class Weapon {
    private Actor owner; // The actor holding this weapon
    private Instant lastAttacked; // The last time this weapon was fired (used for rate of fire)
    private WeaponData data; //  The internal data of this weapon

    /** Weapon() - The default constructor, just used for the BaseDecorator class to technically extend this, but not have its own .data
     */
    public Weapon(){
        if(!(this instanceof BaseDecorator)){
            throw new IllegalArgumentException("Weapon.Weapon() is only permitted for BaseDecorator");
        }
    }

    /** Weapon() - The main constructor, used to create a weapon
     * @param data - A WeaponData, used to give the weapon it's internal data
     */
    public Weapon(WeaponData data){
        this.data = data;
        this.owner = new Actor("Weapon Owner"); // create a dummy
    }

    /** printInfo() - An override of PrintInfo(String), to have a default line prefix of ""
     */
    public void printInfo() {printInfo("");}

    /** printInfo() - Prints information about the gun
     * @param linePrefix - A prefix to add to the start of each line, for example "\t"
     */
    public void printInfo(String linePrefix){
        System.out.println(linePrefix+"============================");
        System.out.println(linePrefix+this); // Print this name
        System.out.println(linePrefix+"Damage: "+this.getData().getGenericDamageText(this.getData().getGenericDamage())+"."); // Print the damage
        int ammo =
                this.getData().getMaxClip()*
                this.getData().defaultClipCount();
        if(ammo > 0){
            System.out.println(linePrefix+"Ammo: "+ammo+"."); // Print the ammo details of this weapon (not well integrated)
        }
        System.out.println(linePrefix+"Speed: "+this.getROFText()+"."); // Print the speed of the weapon (for melee weapons, it will be something like "very fast", for a ranged weapon, it will be the rounds per minute)
        this.getData().printInfo(linePrefix); // prompt the internal data to print *its* information. The only place this is used for printing the information of a legendary decorator
        System.out.println(linePrefix+"============================");
    }

    /** setData() - Used to change the internal weapon data of a created instance
     * @param data - A WeaponData, used to give the weapon it's internal data
     */
    public void setData(WeaponData data){
        this.data = data;
        data.setParent(this);
    }

    /** getData() - Used to get the weapon's internal data object
     * @return - A WeaponData
     */
    public WeaponData getData(){
        return this.data;
    }

    /** toString() - Used to convert the weapon to a string
     * @return - A String, the name of the weapon, possibly with a suffix
     */
    public String toString(){
        String suffix = this.getData().getSuffix(); // get the suffix of the object. For legendary items, this is a star, for unique items, it's a skull, for everything else, it's blank
        return this.getData().getName()+(""==suffix?"":" "+suffix);
    }

    /** getROFText() - An abstract method, used to convert the rate of fire into a readable string (for ranged, the RPM, for melee, a text description of the speed)
     * @return A string, the converted text for use
     */
    public abstract String getROFText();

    /** checkCanAttackROF() - A private check, used to make sure the weapon obeys its rate of fire. It's private because canAttack is chained all the way back to this class.
     * @return A boolean, true if the gun hasn't fired too recently, false if the gun has fired too recently
     */
    private boolean checkCanAttackROF(){
        if(null == this.lastAttacked){
            return true;
        }
        Instant now = Instant.now();
        if((Duration.between(this.lastAttacked, now).toMillis()/1000) < this.getData().getROF()){
            return false;
        }
        return true;
    }

    /** canAttack() - A method to check if this gun can fire. It is chained, so it must pass all requirements of the specific gun, and all of its super classes
     * @return A boolean, true if it can fire, false if it can't
     */
    public boolean canAttack(){ // this is to be chained. Lower levels can run: if(!super.canAttack()){return false;}
        if(!this.checkCanAttackROF()){return false;}
        // more here
        return true;
    }

    /** doAttack() - An abstract method, which MeleeWeapon and RangedWeapon must implement. This is where the weapon checks who it has hit
     * @return An array of Actors, the actors which have been effected by the attack
     */
    public abstract Actor[] doAttack();

    /** attack() - The external part of the attack system, used to trigger an attack on any weapon
     */
    public void attack(){
        if(!canAttack()){return;} // check if we can attack
        Actor[] hits = doAttack(); // get the list of actors who have been hit
        for(Actor hit : hits){
            if(null != hit){ // check we've hit a valid actor
                int damage = this.getData().calculateDamage(hit); // check the damage that this weapon will do to this actor
                System.out.println(this.getData().getAttackString(hit, damage)); // print the text
                hit.takeDamage(damage); // damage the actor (this must be done last or it will print "x has died" before "y has shot x")
            }
        }
        afterAttack(); // post attack code, this isn't overridden anywhere, but it can be (it should probably be in WeaponData)
    }

    /** afterAttack() - This runs misc code after an attack has gone through
     */
    public void afterAttack(){ // this is to be chained. Lower levels should run: super.afterAttack();
        this.lastAttacked = Instant.now(); // set the last attacked variable, so we can check the rate of fire before the next attack attempt
    }

    /** setOwner() - This isn't used, because it would regularly set the actor who is holding the weapon, but the actors are dummys
     * @param owner An Actor, the new owner of this weapon
     */
    public void setOwner(Actor owner){
        this.owner = owner;
    }

    /** getOwner() - This is just a getter for the owner field
     * @return An Actor, the actor who is holding this gun
     */
    public Actor getOwner(){
        return this.owner;
    }
}
// ☠ ☢ ☣ ↯ ⌖ ★