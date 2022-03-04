package DefinedWeapons;


import WeaponFunctionality.Weapon;
import WeaponFunctionality.WeaponData;
import WeaponFunctionality.WeaponRanged;

public class DoubleBarrelShotgun extends WeaponData {
    /** getName() - Psudo getter for the name of this Weapon
     * @return A String, the name
     */
    public String getName(){return "Double Barrel Shotgun";}
    
    /** getGenericDamage() - Psudo getter for the damage of this Weapon
     * @return A int, the damage
     */
    public int getGenericDamage(){return 5;}
    
    /** getNumShots() - Psudo getter for the num shots of this Weapon
     * @return A int, the num shots
     */
    public int getNumShots(){return 16;}
    /** getROF() - Psudo getter for the ROF of this Weapon
     * @return A float, the ROF
     */
    public float getROF(){return 0.5f;}
    /** getReloadTime() - Psudo getter for the reload time of this Weapon
     * @return A float, the reload time
     */
    public float getReloadTime(){return 7;}
    /** getMaxClip() - Psudo getter for the max clip of this Weapon
     * @return A int, the max clip
     */
    public int getMaxClip(){return 2;}
    /** defaultClipCount() - Psudo getter for the default clip of this Weapon
     * @return A int, the default clip
     */
    public int defaultClipCount(){return 16;}

    /** create() - A utility function used to create a Double Barrel Shotgun.
     * This is done, because the important info of the weapon is a WeaponData, which is internal,
     * @return Weapon, the weapon which this WeaponData has been placed into
     */
    public static Weapon create(){
        return new WeaponRanged(new DoubleBarrelShotgun());
    }
}
