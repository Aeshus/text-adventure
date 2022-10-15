public class Player {
  private String name;
  private int monstersDefeated;
  final private Console console;

  public Player(String playerName, Console terminal) {
    name = playerName;
    console = terminal;
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
    System.out.println("\n" + name.toUpperCase() + " (int): ");
    int str = Integer.parseInt(console.getInput());
    System.out.println("\n-~-~-~-~-~-~-~\n");

    return str;
  }
}
