package DefinedWeapons;

import WeaponFunctionality.WeaponMelee;
import WeaponFunctionality.Weapon;
import WeaponFunctionality.WeaponData;

public class Knife extends WeaponData {
    
    /** getName() - Psudo getter for the name of this Weapon
     * @return A String, the name
     */
    public String getName(){return "Knife";}
    
    /** getGenericDamage() - Psudo getter for the damage of this Weapon
     * @return A int, the damage
     */
    public int getGenericDamage(){return 10;}
    
    /** getROF() - Psudo getter for the ROF of this Weapon
     * @return A float, the ROF
     */
    public float getROF(){return WeaponMelee.SPEED_VFAST;}

    /** create() - A utility function used to create a Knife.
     * This is done, because the important info of the weapon is a WeaponData, which is internal,
     * @return Weapon, the weapon which this WeaponData has been placed into
     */
    public static Weapon create(){
        return new WeaponMelee(new Knife());
    }
}
