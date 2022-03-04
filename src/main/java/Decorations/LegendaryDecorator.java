package Decorations;

import WeaponFunctionality.Weapon;
import WeaponFunctionality.WeaponData;

public abstract class LegendaryDecorator extends BaseDecorator {
    /** LegendaryDecorator() - The constructor, this simply passes the given weapon to the superclass, to be placed in the private subject variable
     * @param subject A Weapon, the weapon or decorator (which holds a weapon) which is going to be decorated by this instance
     */
    public LegendaryDecorator(Weapon subject){
        super(subject);
    }

    /** initializeSubjectDataWrapper() - This is called by the superclass, BaseDecorator, so that it is only called once when constructing this instance
     * @param subject A Weapon, the weapon that this has just been initialzed with
     */
    public void initializeSubjectDataWrapper(Weapon subject){
        // get the WeaponData stored in the subject, then wrap it in a LegendaryDataDecorator, then put it back in the subject's WeaponData slot
        subject.setData(new LegendaryDataDecorator(subject.getData()));
    }

    /** LegendaryDataDecorator - the DataDecorator which is paired with LegendaryDecorator
     */
    public class LegendaryDataDecorator extends BaseDataDecorator {
        /** LegendaryDataDecorator() - The constructor, this just passes the WeaponData down to the superclass, BaseDataDecorator
         * @param subject the WeaponData which is being decorated
         */
        public LegendaryDataDecorator(WeaponData subject){
            super(subject);
        }

        /** getSuffix() - This acts like a getter for the suffix variable, but because of Java's (dumb) inheritance, I can't just have private member variables. The suffix is mostly "", but Legendary and Unique decorators alter this
         * @return A String, the suffix of this weapon
         */
        public String getSuffix(){return "★";}
    }
}