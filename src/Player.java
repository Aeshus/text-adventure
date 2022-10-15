public class Player {
  private String name;
  final private Console console;
  private int lives;

  public Player(String playerName, Console terminal) {
    name = playerName;
    console = terminal;
    lives = 3;
  }

  public void changeLives(int life) {
    lives += life;
    System.out.println("\nLives: "+getLives()+"\n");
  }

  public int getLives() {
    return lives;
  }

  public String getName() {
    return name;
  }

  public void setName(String newName) {
    name = newName;
  }

  public String nextLine() {
    System.out.println("\n" + name.toUpperCase() + " (string): ");
    String str = console.getInput();
    System.out.println("\n-~-~-~-~-~-~-~\n");

    return str;
  }

  public int nextInt() {
    while (true) {
      System.out.println("\n" + name.toUpperCase() + " (int): ");

      String num = console.getInput();
      System.out.println("\n-~-~-~-~-~-~-~\n");
      try {
        return Integer.parseInt(num);
       } catch (Exception ignored) {
         System.out.println("Please input an integer");
      }
    }
  }

  public boolean nextBool() {
    while (true) {
      System.out.println("\n" + name.toUpperCase() + " (bool): ");
      switch (console.getInput().toLowerCase()) {
        case "n", "no", "f", "false" -> {
          System.out.println("\n-~-~-~-~-~-~-~\n");
          return false;
        }
        case "y", "yes", "t", "true" -> {
          System.out.println("\n-~-~-~-~-~-~-~\n");
          return true;
        }
        default -> System.out.println("Please input an integer");
      }
    }
  }

  public String matchInput(String[] matches) {
    while(true) {

      System.out.print("\nPotential Inputs: { ");
      for (String match : matches) {
        System.out.print("\"" + match + "\" ");
      }
      System.out.print("}");

      System.out.println("\n" + name.toUpperCase() + " (String): ");

      String str = console.getInput();

      for (String match : matches) {
        if (str.equalsIgnoreCase(match)) {

          // Return the game-specified input to make the API easier
          System.out.println("\n-~-~-~-~-~-~-~\n");
          return match;
        }
      }
    }
  }
}

