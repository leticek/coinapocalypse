/**
 * @author Adam Śmieja
 * @version 1.0
 */
package core.player;

public class PlayerAttributes {

    private static double speedX;
    private static int accountBalance;
    private static int wingManValue;
    private static int numberOfWingMan;

    /**
     *
     * @return vrací počet víl
     */

    public static int getNumberOfWingMan() {
        return numberOfWingMan;
    }

    /**
     *
     * @param numberOfWingMan nastavuje počet víl
     */
    public static void setNumberOfWingMan(int numberOfWingMan) {
        PlayerAttributes.numberOfWingMan = numberOfWingMan;
    }

    /**
     *
     * @return vrací rychlost v ose X
     */
    public static double getSpeedX() {
        return speedX;
    }

    /**
     *
     * @param speedX nová rychlost v ose X
     */
    public static void setSpeedX(double speedX) {
        PlayerAttributes.speedX = speedX;
    }

    /**
     *
     * @return vrací stav účtu
     */
    public static int getAccountBalance() {
        return accountBalance;
    }

    /**
     *
     * @param accountBalance nastaví stav účtu
     */
    public static void setAccountBalance(int accountBalance) {
        PlayerAttributes.accountBalance = accountBalance;
    }

    /**
     *
     * @return vrací hodnotu jedné víly
     */
    public static int getWingManValue() {
        return wingManValue;
    }

    /**
     *
     * @param wingManValue nová hodnota jedné víly
     */
    public static void setWingManValue(int wingManValue) {
        PlayerAttributes.wingManValue = wingManValue;
    }
}
