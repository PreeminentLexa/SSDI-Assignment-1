package Decorations.DefinedDecorations;

import Decorations.LegendaryDecorator;
import Dummy.Actor;
import WeaponFunctionality.Weapon;
import WeaponFunctionality.WeaponData;

import java.util.Random;

public class LegendaryDecorator_Medic extends LegendaryDecorator {
    public LegendaryDecorator_Medic(Weapon subject){super(subject);}

    /** initializeSubjectDataWrapper() - This is called by the superclass, BaseDecorator, so that it is only called once when constructing this instance
     * @param subject A Weapon, the weapon that this has just been initialzed with
     */
    public void initializeSubjectDataWrapper(Weapon subject){
        // get the WeaponData stored in the subject, then wrap it in a LegendaryDataDecorator_Medic, then put it back in the subject's WeaponData slot
        subject.setData(new LegendaryDataDecorator_Medic(subject.getData()));
    }

    /** LegendaryDataDecorator_Medic - the DataDecorator which is paired with LegendaryDecorator_Medic
     */
    public class LegendaryDataDecorator_Medic extends LegendaryDecorator.LegendaryDataDecorator {
        /** LegendaryDataDecorator_Medic() - The constructor, this just passes the WeaponData down to the superclass, BaseDataDecorator
         * @param subject the WeaponData which is being decorated
         */
        public LegendaryDataDecorator_Medic(WeaponData subject){super(subject);}

        /** getName() - Prefaces the name of this weapon with "Medic's ", if the lower decorators/weapon return the name as "9mm", this will return "Medic's 9mm"
         * @return A String, the altered name of this weapon
         */
        public String getName(){
            return "Medic's "+this.getSubject().getName();
        }
        
        /** calculateDamage() - Used to calculate the damage which should be dealt to the given Actor. In this case, we deal negative damage, to heal the target
        * @param hit An Actor, this is the actor who has been hit.
        * @return An int, the damage which has been dealt
        */
        public int calculateDamage(Actor hit){
            return -Math.abs(this.getSubject().calculateDamage(hit));
        }

        /** getAttackString() - Used to format the text output of hitting an Actor
        * @param hit An Actor, this is the actor who has been hit
        * @param damage An int, the damage which has been dealt
        * @return A String, the formatted output
        */
        public String getAttackString(Actor hit, int damage){
            if(0 < damage){
                // Probably can't happen
                return this.getSubject().getAttackString(hit, damage);
            }
            return this.getParent()+" healed "+hit+" for "+this.getGenericDamageText(-damage)+" health.";
        }
        
        /** printInfo() - Prints the legendary information about the gun
        * @param linePrefix - A prefix to add to the start of each line, for example "\t"
        */
        public void printInfo(String linePrefix){
            this.getSubject().printInfo(linePrefix);
            System.out.println(linePrefix+"Legendary - Medic:");
            System.out.println(linePrefix+"\tHeals instead of dealing damage.");
        }
    }
}