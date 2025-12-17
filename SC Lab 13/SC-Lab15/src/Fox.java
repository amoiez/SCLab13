import java.util.List;


public class Fox extends Animal {
    private static final int BREEDING_AGE = 10;
    private static final int MAX_AGE = 50;
    private static final double BREEDING_PROBABILITY = 0.08;
    private static final int MAX_LITTER_SIZE = 2;


    public void act(List<Actor> newActors) {
        incrementAge();
        if(isAlive()) {
            int births = breed();
            for(int b = 0; b < births; b++) {
                newActors.add(new Fox());
            }
        }
    }


    protected int getBreedingAge() { return BREEDING_AGE; }
    protected int getMaxAge() { return MAX_AGE; }
    protected double getBreedingProbability() { return BREEDING_PROBABILITY; }
    protected int getMaxLitterSize() { return MAX_LITTER_SIZE; }
}