public class TextAdventure {
    private Console console;
    private Player hero;

    public TextAdventure() {
        console = new Console("Great Text Adventure", 600, 600);
        hero = new Player();
    }

    public void play() {
        System.out.println("Hi!");
    }
}
