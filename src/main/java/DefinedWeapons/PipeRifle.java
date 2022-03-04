package DefinedWeapons;


import WeaponFunctionality.Weapon;
import WeaponFunctionality.WeaponData;
import WeaponFunctionality.WeaponRanged;

public class PipeRifle extends WeaponData {
    /** getName() - Psudo getter for the name of this Weapon
     * @return A String, the name
     */
    public String getName(){return "Pipe Rifle";}
    
    /** getGenericDamage() - Psudo getter for the damage of this Weapon
     * @return A int, the damage
     */
    public int getGenericDamage(){return 25;}
    
    /** getROF() - Psudo getter for the ROF of this Weapon
     * @return A float, the ROF
     */
    public float getROF(){return 2f;}
    
    /** getReloadTime() - Psudo getter for the reload time of this Weapon
     * @return A float, the reload time
     */
    public float getReloadTime(){return 2;}
    
    /** getMaxClip() - Psudo getter for the max clip of this Weapon
     * @return A int, the max clip
     */
    public int getMaxClip(){return 30;}
    
    /** defaultClipCount() - Psudo getter for the default clip of this Weapon
     * @return A int, the default clip
     */
    public int defaultClipCount(){return 12;}

    /** create() - A utility function used to create a Pipe Rifle.
     * This is done, because the important info of the weapon is a WeaponData, which is internal,
     * @return Weapon, the weapon which this WeaponData has been placed into
     */
    public static Weapon create(){
        return new WeaponRanged(new PipeRifle());
    }
}