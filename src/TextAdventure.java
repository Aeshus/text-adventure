import java.lang.reflect.Method;
import java.util.ArrayList;

public class TextAdventure {
    private Console console;
    private Player hero;
    private ArrayList<Method> locations;
    private ArrayList<String> monstersFought;

    public TextAdventure() {
        console = new Console("Great Text Adventure");
        hero = new Player("Game", console);
        createLocations();
    }

    private ArrayList<Method> createLocations() {
        locations = new ArrayList<Method>();

        try {
            for (int i = 0; i < 20; i++) {
                String region = "region" + i;
                locations.add(this.getClass().getDeclaredMethod(region));
            }
        } catch (Exception ignored) {

        }
        return locations;
    }

    private void region0() {
        System.out.println("0");
    }
    private void region1() {
        System.out.println("1");
    }
    private void region2() {
        System.out.println("2");
    }
    private void region3() {
        System.out.println("3");
    }

    public void play() {
        tutorial();

        try {
            for (Method location : locations) {
                location.invoke(this);
            }
        } catch (Exception e) {

        }
    }

    private void tutorial() {
        console.setImage("./assets/animals.jpg");
        System.out.println("Welcome adventurer!");
        System.out.println("What happens to be your name?");
        hero.setName(hero.nextLine());
        System.out.println("Welcome to this marvelous journey, "+hero.getName()+"!");
        System.out.println("This is a pretty janky journey as they come, so lets get the ground rules straight:");
        System.out.println("- All you will do is hunt monsters, and decide if you fight or run");
        System.out.println("- It's all up to random chance and not skill :)");
        System.out.println("- Once you visit all 9 zones and don't die, you win and you get to see how many monsters you killed!");
        System.out.println("- The 9 zones will be randomized for each adventure :P");

        System.out.println("\nTalking about zones, which one do you want to go to first?");
    }

    private void nextRegion(int region) {

    }
}
