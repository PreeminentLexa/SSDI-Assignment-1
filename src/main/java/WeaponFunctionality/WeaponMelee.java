package WeaponFunctionality;

import Dummy.Actor;

public class WeaponMelee extends Weapon {
    // The standard sets of melee attack speeds. This is in seconds between shot
    public static float SPEED_VSLOW = 5;
    public static float SPEED_SLOW = 2;
    public static float SPEED_NORMAL = 1;
    public static float SPEED_FAST = 0.5f;
    public static float SPEED_VFAST = 0.2f;

    // The standard text displays of melee attack speeds
    public static String SPEED_VSLOW_TEXT = "Very Slow";
    public static String SPEED_SLOW_TEXT = "Slow";
    public static String SPEED_NORMAL_TEXT = "Normal";
    public static String SPEED_FAST_TEXT = "Fast";
    public static String SPEED_VFAST_TEXT = "Very Fast";
    public static String SPEED_UNK_TEXT = "Unknown";

    /** WeaponMelee() - The default constructor
     * @param data A WeaponData, the internal core methods of the weapon
     */
    public WeaponMelee(WeaponData data){
        super(data);
    }

    /** getROFText() - Used to convert the rate of fire into a readable string. For melee, a text description of the speed
     * @return A string, the converted text for use
     */
    public String getROFText(){
        float ROFCompare = this.getData().getROF();
        String override = this.getData().ROFTextOverride();
        if(null != override){
            return override;
        }
        if(ROFCompare == WeaponMelee.SPEED_VSLOW){return WeaponMelee.SPEED_VSLOW_TEXT;}
        if(ROFCompare == WeaponMelee.SPEED_SLOW){return WeaponMelee.SPEED_SLOW_TEXT;}
        if(ROFCompare == WeaponMelee.SPEED_NORMAL){return WeaponMelee.SPEED_NORMAL_TEXT;}
        if(ROFCompare == WeaponMelee.SPEED_FAST){return WeaponMelee.SPEED_FAST_TEXT;}
        if(ROFCompare == WeaponMelee.SPEED_VFAST){return WeaponMelee.SPEED_VFAST_TEXT;}
        return SPEED_UNK_TEXT; // just a default fallback. In the case that there's no text override, but the speed is custom
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

        // check for actors in swing range
        this.addHit(Actor.targetPractice);

        return this.clearHitInfo();
    }
}
