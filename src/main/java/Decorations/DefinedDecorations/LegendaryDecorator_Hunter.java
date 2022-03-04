package Decorations.DefinedDecorations;

import Decorations.LegendaryDecorator;
import Dummy.Actor;
import WeaponFunctionality.Weapon;
import WeaponFunctionality.WeaponData;

public class LegendaryDecorator_Hunter extends LegendaryDecorator {
    public LegendaryDecorator_Hunter(Weapon subject){super(subject);}

    /** initializeSubjectDataWrapper() - This is called by the superclass, BaseDecorator, so that it is only called once when constructing this instance
     * @param subject A Weapon, the weapon that this has just been initialzed with
     */
    public void initializeSubjectDataWrapper(Weapon subject){
        // get the WeaponData stored in the subject, then wrap it in a LegendaryDataDecorator_Hunter, then put it back in the subject's WeaponData slot
        subject.setData(new LegendaryDataDecorator_Hunter(subject.getData()));
    }

    /** LegendaryDataDecorator_Hunter - the DataDecorator which is paired with LegendaryDecorator_Hunter
     */
    public class LegendaryDataDecorator_Hunter extends LegendaryDecorator.LegendaryDataDecorator {
        /** LegendaryDataDecorator_Hunter() - The constructor, this just passes the WeaponData down to the superclass, BaseDataDecorator
         * @param subject the WeaponData which is being decorated
         */
        public LegendaryDataDecorator_Hunter(WeaponData subject){super(subject);}

        /** getName() - Prefaces the name of this weapon with "Hunter's ", if the lower decorators/weapon return the name as "9mm", this will return "Hunter's 9mm"
         * @return A String, the altered name of this weapon
         */
        public String getName(){
            return "Hunter's "+this.getSubject().getName();
        }
        
        /** calculateDamage() - Used to calculate the damage which should be dealt to the given Actor. In this case, we multiply the damage by 1.5 if the target is an animal
        * @param hit An Actor, this is the actor who has been hit.
        * @return An int, the damage which has been dealt
        */
        public int calculateDamage(Actor hit){
            int dmg = this.getSubject().calculateDamage(hit);
            if(hit.isAnimal()){
                dmg *= 1.5;
                System.out.println(this.getParent()+" hit an animal!");
            }
            return dmg;
        }
        
        /** printInfo() - Prints the legendary information about the gun
        * @param linePrefix - A prefix to add to the start of each line, for example "\t"
        */
        public void printInfo(String linePrefix){
            this.getSubject().printInfo(linePrefix);
            System.out.println(linePrefix+"Legendary - Hunter:");
            System.out.println(linePrefix+"\tDeals 150% damage to animals.");
        }
    }
}