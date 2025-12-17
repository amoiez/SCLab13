import java.util.Random;


public class Randomizer {
    private static final int SEED = 1111;
    private static Random random = new Random(SEED);
    public static boolean useShared = true;
    private static Random sharedRandom = new Random(SEED);


    public static Random getRandom() {
        if(useShared) {
            return sharedRandom;
        }
        return random;
    }


    public static void reset() {
        if(useShared) {
            sharedRandom = new Random(SEED);
        }
    }
}