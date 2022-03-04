package WeaponFunctionality;

import Decorations.BaseDataDecorator;
import Decorations.BaseDecorator;
import Decorations.DefinedDecorations.LegendaryDecorator_Lucky;
import Decorations.DefinedDecorations.UniqueDecorator;
import Decorations.LegendaryDecorator;
import Dummy.Actor;

public class WeaponRanged extends Weapon {
    /** WeaponMelee() - The default constructor
     * @param data A WeaponData, the internal core methods of the weapon
     */
    public WeaponRanged(WeaponData data){
        super(data);
    }

    /** getROFText() - Used to convert the rate of fire into a readable string. For melee, a text description of the speed
     * @return A string, the converted text for use
     */
    public String getROFText(){
        int RPM = (int)(60/this.getData().getROF());
        return RPM+" RPM";
    }

    private Actor[] hitInfo; // just used as a temporary almost static table, it's reset each time a hit is calculated
    private int hitCount;

    /** initializeHitInfo() - Clears hitInfo and hitCount
     */
    private void initializeHitInfo(){
        this.hitInfo = new Actor[1];
        this.hitCount = 0;
    }

    /** checkHitInfoSize() - Used to check if the hitInfo is big enough to hold another hit Actor. If it isn't, then this doubles the size
     */
    private void checkHitInfoSize(){
        if(this.hitInfo.length <= hitCount){
            Actor[] newHitInfo = new Actor[this.hitInfo.length*2];
            for(int i = 0;i < hitCount;i++){
                newHitInfo[i] = hitInfo[i];
            }
        }
    }

    /** addHit() - Used to add an actor to the list of hit actors
     * @param hit An Actor, the actor who has been hit
     */
    private void addHit(Actor hit){
        checkHitInfoSize();
        this.hitInfo[this.hitCount] = hit;
        this.hitCount++;
    }

    /** clearHitInfo() - Used to clear the hitInfo
     * @return An array of Actors, this is the actors who have been hit, during this set of hits
     */
    private Actor[] clearHitInfo(){
        Actor[] oldHitInfo = this.hitInfo;
        this.hitInfo = null;
        return oldHitInfo;
    }

    /** doAttack() - This is where the weapon checks who it has hit
     * @return An array of Actors, the actors which have been effected by the attack
     */
    public Actor[] doAttack(){
        this.initializeHitInfo();
        int numShots = this.getData().getNumShots();
        for(int i = 0;i < numShots;i++){
            // hitscan
            this.addHit(Actor.targetPractice);
        }
        return this.clearHitInfo();
    }
}
