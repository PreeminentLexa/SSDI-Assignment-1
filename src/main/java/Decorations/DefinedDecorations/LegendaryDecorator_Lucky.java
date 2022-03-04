package Decorations.DefinedDecorations;

import Decorations.LegendaryDecorator;
import Dummy.Actor;
import WeaponFunctionality.Weapon;
import WeaponFunctionality.WeaponData;

import java.util.Random;

public class LegendaryDecorator_Lucky extends LegendaryDecorator {
    public LegendaryDecorator_Lucky(Weapon subject){super(subject);}

    /** initializeSubjectDataWrapper() - This is called by the superclass, BaseDecorator, so that it is only called once when constructing this instance
     * @param subject A Weapon, the weapon that this has just been initialzed with
     */
    public void initializeSubjectDataWrapper(Weapon subject){
        // get the WeaponData stored in the subject, then wrap it in a LegendaryDataDecorator_Lucky, then put it back in the subject's WeaponData slot
        subject.setData(new LegendaryDataDecorator_Lucky(subject.getData()));
    }

    /** LegendaryDataDecorator_Lucky - the DataDecorator which is paired with LegendaryDecorator_Lucky
     */
    public class LegendaryDataDecorator_Lucky extends LegendaryDecorator.LegendaryDataDecorator {
        /** LegendaryDataDecorator_Lucky() - The constructor, this just passes the WeaponData down to the superclass, BaseDataDecorator
         * @param subject the WeaponData which is being decorated
         */
        public LegendaryDataDecorator_Lucky(WeaponData subject){super(subject);}

        /** getName() - Prefaces the name of this weapon with "Lucky ", if the lower decorators/weapon return the name as "9mm", this will return "Lucky 9mm"
         * @return A String, the altered name of this weapon
         */
        public String getName(){
            return "Lucky "+this.getSubject().getName();
        }
        private Random randomizer = new Random();
        
        /** calculateDamage() - Used to calculate the damage which should be dealt to the given Actor. In this case, we multiply the damage by 2 if a random number between 0 and 10 is under the weapon holder's luck stat (higher luck stat means more lucky shots)
        * @param hit An Actor, this is the actor who has been hit.
        * @return An int, the damage which has been dealt
        */
        public int calculateDamage(Actor hit){
            int dmg = this.getSubject().calculateDamage(hit);
            if(randomizer.nextInt(10) < this.getParent().getOwner().getLuck()){
                dmg *= 2;
                System.out.println(this.getParent()+" got a lucky shot in!");
            }
            return dmg;
        }
        
        /** printInfo() - Prints the legendary information about the gun
        * @param linePrefix - A prefix to add to the start of each line, for example "\t"
        */
        public void printInfo(String linePrefix){
            this.getSubject().printInfo(linePrefix);
            System.out.println(linePrefix+"Legendary - Lucky:");
            System.out.println(linePrefix+"\tChance of dealing double damage.");
        }
    }
}
