public abstract class Animal implements Actor {
    private int age;
    private boolean alive;

    // Make VERBOSE public so Main can access it
    public static boolean VERBOSE = false;

    public Animal() {
        age = 0;
        alive = true;
        if(VERBOSE) {
            System.out.println(getClass().getSimpleName() + " born (age=" + age + ")");
        }
    }

    // --- Age handling ---
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    protected void incrementAge() {
        age++;
        if(VERBOSE) {
            System.out.println(getClass().getSimpleName() + " aged to " + age);
        }
        if(age > getMaxAge()) {
            alive = false;
            if(VERBOSE) {
                System.out.println(getClass().getSimpleName() + " died of old age");
            }
        }
    }

    // --- Breeding logic ---
    public boolean canBreed() {
        return age >= getBreedingAge();
    }

    protected int breed() {
        if(canBreed() && Randomizer.getRandom().nextDouble() <= getBreedingProbability()) {
            int births = Randomizer.getRandom().nextInt(getMaxLitterSize()) + 1;
            if(VERBOSE) {
                System.out.println(getClass().getSimpleName() + " bred " + births + " offspring");
            }
            return births;
        }
        return 0;
    }

    public boolean isAlive() {
        return alive;
    }

    protected void setDead() {
        alive = false;
        if(VERBOSE) {
            System.out.println(getClass().getSimpleName() + " died");
        }
    }

    // --- Abstract hooks ---
    protected abstract int getBreedingAge();
    protected abstract int getMaxAge();
    protected abstract double getBreedingProbability();
    protected abstract int getMaxLitterSize();
}