package Decorations.DefinedDecorations;

import Decorations.LegendaryDecorator;
import Dummy.Actor;
import WeaponFunctionality.Weapon;
import WeaponFunctionality.WeaponData;

import java.util.Random;

public class LegendaryDecorator_TwoShot extends LegendaryDecorator {
    public LegendaryDecorator_TwoShot(Weapon subject){super(subject);}

    /** initializeSubjectDataWrapper() - This is called by the superclass, BaseDecorator, so that it is only called once when constructing this instance
     * @param subject A Weapon, the weapon that this has just been initialzed with
     */
    public void initializeSubjectDataWrapper(Weapon subject){
        // get the WeaponData stored in the subject, then wrap it in a LegendaryDataDecorator_TwoShot, then put it back in the subject's WeaponData slot
        subject.setData(new LegendaryDataDecorator_TwoShot(subject.getData()));
    }
    
    /** doAttack() - This gets the subject to run doAttack twice, then adds the resulting arrays together
     * @return An array of Actors, the actors which have been effected by the attack
     */
    public Actor[] doAttack(){
        System.out.println(this+" shoots twice!");
        Actor[] firstBullet = this.getSubject().doAttack(); // doAttack once
        Actor[] additionalBullet = this.getSubject().doAttack(); // doAttack a second time
        Actor[] compiled = new Actor[firstBullet.length+additionalBullet.length]; // create an array which can hold everything
        for(int i = 0;i < firstBullet.length;i++){ // move all values from firstBullet to compiled
            compiled[i] = firstBullet[i];
        }
        for(int i = 0;i < additionalBullet.length;i++){ // move all values from additionalBullet to compiled
            compiled[firstBullet.length+i] = additionalBullet[i];
        }
        return compiled;
    }

    /** LegendaryDataDecorator_TwoShot - the DataDecorator which is paired with LegendaryDecorator_TwoShot
     */
    public class LegendaryDataDecorator_TwoShot extends LegendaryDecorator.LegendaryDataDecorator {
        /** LegendaryDataDecorator_TwoShot() - The constructor, this just passes the WeaponData down to the superclass, BaseDataDecorator
         * @param subject the WeaponData which is being decorated
         */
        public LegendaryDataDecorator_TwoShot(WeaponData subject){super(subject);}

        /** getName() - Prefaces the name of this weapon with "Two Shot ", if the lower decorators/weapon return the name as "9mm", this will return "Two Shot 9mm"
         * @return A String, the altered name of this weapon
         */
        public String getName(){
            return "Two Shot "+this.getSubject().getName();
        }
        
        /** printInfo() - Prints the legendary information about the gun
        * @param linePrefix - A prefix to add to the start of each line, for example "\t"
        */
        public void printInfo(String linePrefix){
            this.getSubject().printInfo(linePrefix);
            System.out.println(linePrefix+"Legendary - Two Shot:");
            System.out.println(linePrefix+"\tFires double the projectiles.");
        }
    }
}