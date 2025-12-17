import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Simulator {
    private List<Actor> actors;
    private PopulationGenerator generator;


    private int totalBirths;
    private int totalDeaths;


    public Simulator(int foxes, int rabbits) {
        actors = new ArrayList<>();
        generator = new PopulationGenerator();
        generator.populate(actors, foxes, rabbits);
    }


    public void simulateOneStep() {
        List<Actor> newActors = new ArrayList<>();
        totalBirths = 0;
        totalDeaths = 0;


        Iterator<Actor> it = actors.iterator();
        while(it.hasNext()) {
            Actor actor = it.next();
            actor.act(newActors);


            if(!actor.isAlive()) {
                it.remove();
                totalDeaths++;
            }
        }


        totalBirths = newActors.size();
        actors.addAll(newActors);
    }


    // Return current stats as an int array: {foxes, rabbits, births, deaths}
    public int[] getCurrentStats() {
        int foxes = 0, rabbits = 0;
        for(Actor actor : actors) {
            if(actor instanceof Fox) foxes++;
            if(actor instanceof Rabbit) rabbits++;
        }
        return new int[]{foxes, rabbits, totalBirths, totalDeaths};
    }
}