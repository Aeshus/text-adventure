import java.lang.reflect.Method;
import java.util.ArrayList;

public class TextAdventure {
  private final Console console;
  private final Player hero;
  private ArrayList<Method> locations;
  private final ArrayList<String> monstersFought;

  public TextAdventure() {
    console = new Console("Great Text Adventure");
    hero = new Player("Game", console);
    monstersFought = new ArrayList<>();
    createLocations();
  }

  // Iteratively register all the locations and apply it to an Arraylist.
  private void createLocations() {
    locations = new ArrayList<>();

    try {
      // Register all regions by running until it throws an error.
      for (int i = 0; i < 100; i++) {
        // Load each function with "Region#" as it's name, recursively
        locations.add(this.getClass().getDeclaredMethod("region" + i));
      }
    } catch (Exception ignored) {
    }
  }

  // Forest Region
  @SuppressWarnings("unused")
  private void region0() {
    console.setImage("./assets/animals.jpg");

    System.out.println("You find yourself in some dumb forest with some dumb animals");
    System.out.println(
        "The dumbest animal of them all -- the king of the forest -- is some derpy bear");
    System.out.println("It's probably approaching you...?");

    System.out.println("\nDo you fight?");
  }

  // Graveyard Region
  @SuppressWarnings("unused")
  private void region1() {
    console.setImage("./assets/graveyard.jpg");
  }

  // Mountain Region
  @SuppressWarnings("unused")
  private void region2() {
    console.setImage("./assets/mountains.jpg");
  }

  // Ocean Region
  @SuppressWarnings("unused")
  private void region3() {
    console.setImage("./assets/ocean.jpg");
  }

  // Forest Region
  @SuppressWarnings("unused")
  private void region4() {
    console.setImage("./assets/forest.jpg");
  }

  // Pumpkin Region
  @SuppressWarnings("unused")
  private void region5() {
    console.setImage("./assets/pumpkintrio.jpg");
  }

  public void play() {
    tutorial();

    nextRegion();
  }

  // Randomly loads the next region
  private void nextRegion() {
    if (locations.size() == 0) {
      endGame();
      return;
    }

    // Generates a random number from 0 -> size;
    int randomRegion = (int) (Math.random() * locations.size());

    try {
      // Invoke said region
      locations.get(randomRegion).invoke(this);
    } catch (Exception ignored) {
    }

    // Remove the region after executing it
    locations.remove(randomRegion);
  }

  // Winning Screen
  private void endGame() {
    System.out.println("You have ended your journey!");
    System.out.println("You have killed: ");
    if (monstersFought.size() == 0) {
      System.out.print("Nothing...?");
    }

    for (String monster : monstersFought) {
      System.out.print(monster);
    }
    System.out.println("Making your score " + monstersFought.size());
  }

  private void tutorial() {
    console.setImage("./assets/animals.jpg");
    System.out.println("Welcome adventurer!");
    System.out.println("What happens to be your name?");
    hero.setName(hero.nextLine());
    System.out.println("Welcome to this marvelous journey, " + hero.getName() + "!");
    System.out.println(
        "This is a pretty shoddy journey as they come, so lets get the ground rules straight:");
    System.out.println("- All you will do is hunt monsters, and decide if you fight or run");
    System.out.println("- It's all up to random chance and not skill :)");
    System.out.println("- The zones will be randomized for each adventure :P");

    System.out.println("\nTalking about zones, would you like to go to the next zone?");
  }
}
