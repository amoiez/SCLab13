import java.awt.Color;
import java.util.List;


public class PopulationGenerator {
    public static final Color FOX_COLOR = Color.BLUE;
    public static final Color RABBIT_COLOR = Color.GREEN;


    public void populate(List<Actor> actors, int foxes, int rabbits) {
        for(int i = 0; i < foxes; i++) {
            actors.add(new Fox());
        }
        for(int i = 0; i < rabbits; i++) {
            actors.add(new Rabbit());
        }
    }
}