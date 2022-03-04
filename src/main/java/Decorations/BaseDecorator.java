package Decorations;

import WeaponFunctionality.Weapon;
import Dummy.Actor;
import WeaponFunctionality.WeaponData;


public abstract class BaseDecorator extends Weapon {
    private Weapon subject;

    /** BaseDecorator() - The constructor
     * Because a BaseDecorator extends Weapon, it can be cast to Weapon, so it can be stored in a Weapon object.
     * This means that the BaseDecorator can replace the weapon it is storing, and just pass all method calls through to the contained instance.
     * Much like a Russian nesting doll, the stored "Weapon" can just be another decorator. Calling a method on the outermost instance,
     * will pass the request inwards through each layer of decoration, at each layer, the specific decorator can change the input or output, or simply return something.
     * E.G. the "ExclamationPointDecorator" can add an '!' to the end of the getName() which is returned by its stored subject.
     * @param subject A Weapon, either the Weapon which is being decorated, or the outermost decoration of a weapon
     */
    public BaseDecorator(Weapon subject){
        this.subject = subject;
        initializeSubjectDataWrapper(this.subject); // If there's a paired WeaponData decorator, then attach it to the WeaponData of the weapon
    }

    /** initializeSubjectDataWrapper() - This is used to pair a BaseDecorator with a BaseDataDecorator.
     * The WeaponData object held within a Weapon holds internal methods - if they were just in the Weapon class, the decorator would be unable to alter them.
     * The BaseDataDecorator decorates the internal class, but it's often paired with a BaseDecorator. The initializeSubjectDataWrapper creates the paired BaseDataDecorator, and applies it to the WeaponData inside the Weapon
     * This is only called by the BaseDecorator constructor (not the subclass' constructor)
     * @param subject A Weapon, the weapon that this decorates
     */
    public void initializeSubjectDataWrapper(Weapon subject){}

    /** getSubject() - Getter object for the Weapon that this is decorating.
     * @return A Weapon, the weapon that is stored
     */
    public Weapon getSubject(){
        return this.subject;
    }

    // DEFAULT STUFF
    // These methods override all methods in Weapon, so that instead of effecting this instance's super class, it will effect this instance's stored subject Weapon.
    // These can be overridden by subclasses in order to either alter, or replace, the subject's output

    /** printInfo() - An override of PrintInfo(String), to have a default line prefix of ""
     */
    public void printInfo(){this.subject.printInfo();}

    /** printInfo() - Prints information about the gun
     * @param linePrefix - A prefix to add to the start of each line, for example "\t"
     */
    public void printInfo(String linePrefix){this.subject.printInfo(linePrefix);}

    /** setData() - Used to change the internal weapon data of a created instance
     * @param data - A WeaponData, used to give the weapon it's internal data
     */
    public void setData(WeaponData data){this.subject.setData(data);}

    /** getData() - Used to get the weapon's internal data object
     * @return - A WeaponData
     */
    public WeaponData getData(){return this.subject.getData();}

    /** toString() - Used to convert the weapon to a string
     * @return - A String, the name of the weapon, possibly with a suffix
     */
    public String toString(){return this.subject.toString();}

    /** getROFText() - An abstract method, used to convert the rate of fire into a readable string (for ranged, the RPM, for melee, a text description of the speed)
     * @return A string, the converted text for use
     */
    public String getROFText(){return this.subject.getROFText();}

    /** canAttack() - A method to check if this gun can fire. It is chained, so it must pass all requirements of the specific gun, and all of its super classes
     * @return A boolean, true if it can fire, false if it can't
     */
    public boolean canAttack(){return this.subject.canAttack();}

    /** doAttack() - This is where the weapon checks who it has hit
     * @return An array of Actors, the actors which have been effected by the attack
     */
    public Actor[] doAttack(){return this.subject.doAttack();}

    /** attack() - The external part of the attack system, used to trigger an attack on any weapon
     */
    public void attack(){this.subject.attack();}

    /** afterAttack() - This runs misc code after an attack has gone through
     */
    public void afterAttack(){this.subject.afterAttack();}

    /** setOwner() - This isn't used, because it would regularly set the actor who is holding the weapon, but the actors are dummys
     * @param owner An Actor, the new owner of this weapon
     */
    public void setOwner(Actor owner){this.subject.setOwner(owner);}

    /** getOwner() - This is just a getter for the owner field
     * @return An Actor, the actor who is holding this gun
     */
    public Actor getOwner(){return this.subject.getOwner();}
}
