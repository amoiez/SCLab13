import java.util.List;


public class Rabbit extends Animal {
    private static final int BREEDING_AGE = 5;
    private static final int MAX_AGE = 40;
    private static final double BREEDING_PROBABILITY = 0.12;
    private static final int MAX_LITTER_SIZE = 4;


    public void act(List<Actor> newActors) {
        incrementAge();
        if(isAlive()) {
            int births = breed();
            for(int b = 0; b < births; b++) {
                newActors.add(new Rabbit());
            }
        }
    }


    protected int getBreedingAge() { return BREEDING_AGE; }
    protected int getMaxAge() { return MAX_AGE; }
    protected double getBreedingProbability() { return BREEDING_PROBABILITY; }
    protected int getMaxLitterSize() { return MAX_LITTER_SIZE; }
}