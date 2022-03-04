package Decorations.DefinedDecorations;

import Decorations.BaseDataDecorator;
import Decorations.BaseDecorator;
import WeaponFunctionality.Weapon;
import WeaponFunctionality.WeaponData;

public class UniqueDecorator extends BaseDecorator {
    public UniqueDecorator(Weapon subject, UniqueDecorator_StatHolder stats){
        super(subject);
        ((UniqueDataDecorator)subject.getData()).setStatOverride(stats);
    }

    /** initializeSubjectDataWrapper() - This is called by the superclass, BaseDecorator, so that it is only called once when constructing this instance
     * @param subject A Weapon, the weapon that this has just been initialzed with
     */
    public void initializeSubjectDataWrapper(Weapon subject){
        // get the WeaponData stored in the subject, then wrap it in a UniqueDataDecorator, then put it back in the subject's WeaponData slot
        subject.setData(new UniqueDataDecorator(subject.getData()));
    }

    /** stats() - Shorter way to create a stat holder instance
     * @return A UniqueDecorator_StatHolder, the fresh stat holder
     */
    public static UniqueDecorator_StatHolder stats(){
        return new UniqueDecorator_StatHolder();
    }

    /** UniqueDecorator_StatHolder - A structure of data used by the Unique Decorator, to fully ovewrite certain stats
     */
    public static class UniqueDecorator_StatHolder {
        // These ints and floats have default values, so that I know when they aren't altered
        public String name;
        public int damage = -1;
        public float rateOfFire = Float.NaN;
        public float reloadTime = Float.NaN;
        public int clipSize = -1;
        public int countOfClips = -1;
        public int numShots = -1;

        /** UniqueDecorator_StatHolder() - Simple constructor, does nothing
         */
        public UniqueDecorator_StatHolder(){}

        /** setName() - Setter for the name variable
         * @param name A String, the new name
         * @return A UniqueDecorator_StatHolder, this instance, used for chaining setters
         */
        public UniqueDecorator_StatHolder setName(String name){
            this.name = name;
            return this;
        }

        /** setDamage() - Setter for the damage variable
         * @param damage An int, the new damage
         * @return A UniqueDecorator_StatHolder, this instance, used for chaining setters
         */
        public UniqueDecorator_StatHolder setDamage(int damage){
            this.damage = damage;
            return this;
        }

        /** setROF() - Setter for the rateOfFire variable
         * @param rateOfFire An float, the new rateOfFire
         * @return A UniqueDecorator_StatHolder, this instance, used for chaining setters
         */
        public UniqueDecorator_StatHolder setROF(float rateOfFire){
            this.rateOfFire = rateOfFire;
            return this;
        }

        /** setReloadTime() - Setter for the reloadTime variable
         * @param reloadTime An float, the new reloadTime
         * @return A UniqueDecorator_StatHolder, this instance, used for chaining setters
         */
        public UniqueDecorator_StatHolder setReloadTime(float reloadTime){
            this.reloadTime = reloadTime;
            return this;
        }

        /** setClipSize() - Setter for the clipSize variable
         * @param clipSize An int, the new clipSize
         * @return A UniqueDecorator_StatHolder, this instance, used for chaining setters
         */
        public UniqueDecorator_StatHolder setClipSize(int clipSize){
            this.clipSize = clipSize;
            return this;
        }

        /** setCountOfClips() - Setter for the countOfClips variable
         * @param countOfClips An int, the new damage
         * @return A UniqueDecorator_StatHolder, this instance, used for chaining setters
         */
        public UniqueDecorator_StatHolder setCountOfClips(int countOfClips){
            this.countOfClips = countOfClips;
            return this;
        }

        /** setNumShots() - Setter for the numShots variable
         * @param numShots An int, the new numShots
         * @return A UniqueDecorator_StatHolder, this instance, used for chaining setters
         */
        public UniqueDecorator_StatHolder setNumShots(int numShots){
            this.numShots = numShots;
            return this;
        }
    }

    /** UniqueDataDecorator - the DataDecorator which is paired with UniqueDecorator
     */
    public class UniqueDataDecorator extends BaseDataDecorator {
        /** LegendaryDataDecorator() - The constructor, this just passes the WeaponData down to the superclass, BaseDataDecorator
         * @param subject the WeaponData which is being decorated
         */
        public UniqueDataDecorator(WeaponData subject){super(subject);}

        private UniqueDecorator_StatHolder stats;

        /** setStatOverride() - Used to set the stat holder object of this decorator
         * @param stats A UniqueDecorator_StatHolder, the stats which this decorator should overwrite
         */
        public void setStatOverride(UniqueDecorator_StatHolder stats){
            this.stats = stats;
        }

        /** printInfo() - Prints information about the gun
         * @param linePrefix - A prefix to add to the start of each line, for example "\t"
         */
        public void printInfo(String linePrefix){
            this.getSubject().printInfo(linePrefix);
            System.out.println(linePrefix+"Unique:");

            if(null != this.stats.name){
                System.out.println(linePrefix+"\tCustom Name: "+this.stats.name+".");
            }
            if(0 <= this.stats.damage){
                System.out.println(linePrefix+"\tCustom Damage: "+this.stats.damage+".");
            }
            if(!Float.isNaN(this.stats.rateOfFire)){
                System.out.println(linePrefix+"\tCustom Speed: "+this.getParent().getROFText()+".");
            }
            if(!Float.isNaN(this.stats.reloadTime)){
                System.out.println(linePrefix+"\tCustom Reload Time: "+this.stats.reloadTime+".");
            }
            if(0 <= this.stats.clipSize){
                System.out.println(linePrefix+"\tCustom Clip Size: "+this.stats.clipSize+".");
            }
            if(0 <= this.stats.countOfClips){
                System.out.println(linePrefix+"\tCustom Amount of Clips: "+this.stats.countOfClips+".");
            }
            if(0 <= this.stats.numShots){
                System.out.println(linePrefix+"\tCustom Number of Shots: "+this.stats.numShots+".");
            }
        }

        /** getName() - This overwrites the getName getter, IF the name is set in this object's stat holder
         * @return A String, the name of this weapon, or the replaced value
         */
        public String getName(){
            if(null == this.stats.name){
                return this.getSubject().getName();
            }
            return this.stats.name;
        }

        /** getGenericDamage() - This overwrites the getGenericDamage getter, IF the damage is set in this object's stat holder
         * @return An int, the damage of this weapon, or the replaced value
         */
        public int getGenericDamage(){
            if(0 > this.stats.damage){
                return this.getSubject().getGenericDamage();
            }
            return this.stats.damage;
        }

        /** getROF() - This overwrites the getROF getter, IF the rate of fire is set in this object's stat holder
         * @return A float, the rate of fire of this weapon, or the replaced value
         */
        public float getROF(){
            if(Float.isNaN(this.stats.rateOfFire)){
                return this.getSubject().getROF();
            }
            return this.stats.rateOfFire;
        }

        /** getReloadTime() - This overwrites the getReloadTime getter, IF the reload time is set in this object's stat holder
         * @return A float, the reload time of this weapon, or the replaced value
         */
        public float getReloadTime(){
            if(Float.isNaN(this.stats.reloadTime)){
                return this.getSubject().getReloadTime();
            }
            return this.stats.reloadTime;
        }

        /** getMaxClip() - This overwrites the getMaxClip getter, IF the clip size is set in this object's stat holder
         * @return An int, the amount of bullets in each clip of this weapon, or the replaced value
         */
        public int getMaxClip(){
            if(0 > this.stats.clipSize){
                return this.getSubject().getMaxClip();
            }
            return this.stats.clipSize;
        }

        /** defaultClipCount() - This overwrites the defaultClipCount getter, IF the clipcount is set in this object's stat holder
         * @return An int, the amount of clips of this weapon, or the replaced value
         */
        public int defaultClipCount(){
            if(0 > this.stats.countOfClips){
                return this.getSubject().defaultClipCount();
            }
            return this.stats.countOfClips;
        }

        /** getNumShots() - This overwrites the getNumShots getter, IF the numshots is set in this object's stat holder
         * @return An int, the number of shots of this weapon, or the replaced value
         */
        public int getNumShots(){
            if(0 > this.stats.numShots){
                return this.getSubject().getNumShots();
            }
            return this.stats.numShots;
        }

        /** getSuffix() - This acts like a getter for the suffix variable, but because of Java's (dumb) inheritance, I can't just have private member variables. The suffix is mostly "", but Legendary and Unique decorators alter this
         * @return A String, the suffix of this weapon
         */
        public String getSuffix(){return "☠";}
    }
}
