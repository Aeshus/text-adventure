import java.lang.reflect.Method;
import java.util.ArrayList;

public class TextAdventure {
  private final Console console;
  private final Player hero;
  private ArrayList<Method> locations;
  private int regions;

  public TextAdventure() {
    console = new Console("Great Text Adventure");
    hero = new Player("Game", console);
    regions = 0;
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

    System.out.println("\nWhat do you do?");

    String choice = hero.matchInput(new String[]{"Talk","Run", "Fight"});

    if (choice.equals("Talk")) {
      System.out.println("You attempt to talk to the bear");

      int rand = (int)(Math.random() * 5);

      if (rand == 0 || rand == 1) {
        // Death
        System.out.println("The bear continues to approach you...");
        System.out.println("You realize that this bear is incapable of human intelligence and has decided to kill you");

        hero.changeLives(-1);

        if (hero.isDead()) {
          System.out.println("The bear charges you, eating your head");
          System.out.println("You died");
          endGame();
        }

        System.out.println("The bear jumps at 'cha and swipes you");
        System.out.println("At least you now have a cool scar...?");
      } else if (rand == 2) {
        // Discussion

        System.out.println("As the bear approaches, you see that he is actually walking on two legs instead of the usual four");
        System.out.println("The bear calls out to you, adventurer");

        System.out.println("MR. BEAR: Hello fine gentlemen, why have you come to this great forest?");

        choice = hero.matchInput(new String[]{"Murder","Riches", "Fun"});

        if (choice.equals("Murder")) {

          System.out.println("Hey, That's not very cool thing smh");

          if ((int)(Math.random() * 3) == 0) {
            System.out.println("The bear looks super not happy with you");

            hero.changeLives(-1);
            if (hero.isDead()) {
              System.out.println("He crane-kicks you and turns you into a eunuch");
              System.out.println("Just the thought of it makes you fall over and die");
              endGame();
            }

            System.out.println("The bear lounges at you, concussing your head");
            System.out.println("You black out and the bear walks away");
          } else {
            System.out.println("But I am in no place to judge as I happen to be bear.");
            System.out.println("Good-day!");
          }
        } else if (choice.equals("Riches")) {
          System.out.println("Well... I'm currently don't have my money on me right now.");
          System.out.println("So, maybe come back later?");
        } else {
          System.out.println("Well son, you've come to the right place!");
          System.out.println("Disco balls and lights appear everywhere around you!!");
          System.out.println("Everyone starts dancing and it's actually pretty epic");
          System.out.println("Mr Bear Man gives you some totally not illegal moonshine");

          if ((int)(Math.random() * 5) == 0) {
            System.out.println("You immediately down it and pass out");
            System.out.println("Who could've expected this...");
            hero.changeLives(-1);

            if (hero.isDead()) {
              System.out.println("It just happened that this time, you never woke up :)");
              endGame();
            }

            System.out.println("You down it really quickly and it burns your throat");
            System.out.println("You then scream out in agony and crash the epic party >:(");
            System.out.println("You run away");
          } else {

            System.out.println("You wake up the next day and have a hangover, but what do you expect?");
            System.out.println("Surprisingly, you're fine");
          }
        }

      } else if (rand == 3) {
        // Nothing

        System.out.println("Bear stare at you");
        System.out.println("Bear turn around");
        System.out.println("Bear leave");
      } else {
        // Get extra life

        System.out.println("The bear comes up to you and hands you some epic gold");
        System.out.println("You grab it and run off");

        hero.changeLives(1);
      }
    } else if (choice.equals("Run")) {
      System.out.println("You start running away!");

      if ((int)(Math.random() * 4) == 0) {
        System.out.println("The bear jumps in front of you!");
        System.out.println("You end up running straight into his world-famous bear-hug!");

        hero.changeLives(-1);
        if (hero.isDead()) {
          System.out.println("You get crushed by his weight and die");
          endGame();
        }

        System.out.println("He breaks a few of your bones but you manage to still escape");
        System.out.println("Idk how you can still walk, but not like I care");
      }

      System.out.println("You go sonic-speed and run away!");
      System.out.println("You survive!");
    } else {
      System.out.println("You fight the bear!!!!");

      choice = hero.matchInput(new String[]{"Punch","Kick", "Bite"});

      if (choice.equals("Punch")) {
        System.out.println("You decide to go against the bear mono-et-mono");
        System.out.println("The bear accepts the duel, and waits for your first move");

        choice = hero.matchInput(new String[]{"Left", "Right"});

        System.out.println("You jab at the bear to the "+choice+"!");

        if (choice.equals("Left") && (int)(Math.random()*2) == 0) {
          System.out.println("The bear blocked you jab!");

          hero.changeLives(-1);
          if (hero.isDead()) {
            System.out.println("It rips off your arm :)");
            endGame();
          }

          System.out.println("It breaks you arm");

        } else if (choice.equals("Right") && (int)(Math.random()*2) == 0){
          System.out.println("The bear parried your absolute buffoonery");

          hero.changeLives(-1);
          if (hero.isDead()) {
            System.out.println("As a result, you're so disapointed in yourself that you fall over and die :)");
            endGame();
          } else {

            System.out.println("The parry injures your hand and you run away :/");
          }
        }

        System.out.println("The hit connects and the bear falls over!");
        System.out.println("The bear, with it's pride hurt, runs away!");


      } else if (choice.equals("Kick")) {
        System.out.println("You decide to kick the bear in it's face.");

        if (!((int)(Math.random() * 3) == 0)) {
          System.out.println("You hit connects but...");
          System.out.println("But all it seemed to do was piss it off...");

          hero.changeLives(-1);
          if (hero.isDead()) {
            System.out.println("The bear then mauls you...");
            endGame();
          }

          System.out.println("The bear jumps towards you and bites a chunk off of your hand.");
          System.out.println("Prolly wasn't the best but hey");
          System.out.println("At least you're still alive...");
        }
      } else {
        System.out.println("You uhh.... Bite the bear?");
        System.out.println("Are you serious?");
        System.out.println("Could you not have thought of anything better?\n");

        System.out.println("Whelp...");

        if ((int)(Math.random()*2) == 0) {
          System.out.println("You bite the bear and shocker -- it bit you back...");

          hero.changeLives(-1);
          if (hero.isDead()) {
            System.out.println("You eventually bleed out of the big chunk of your body missing...");
            endGame();
          }

          System.out.println("You bite it and it bites you... Ok then");
          System.out.println("At least you ain't dead...");
        } else {

          if ((int) (Math.random() * 4) == 0) {
            System.out.println("Bruh what...");
            System.out.println("I'm not gonna question it...");
            hero.changeLives(1);
            System.out.println("Just... Why...");
          } else {
            System.out.println("Well, it then let go as it thought you died...");
            System.out.println("Soo... Good job not dying?");
          }
        }
      }
    }

    regions += 1;
  }

  // Graveyard Region
  @SuppressWarnings("unused")
  private void region1() {
    console.setImage("./assets/graveyard.jpg");

    System.out.println("You walk into this magnificent graveyard");
    System.out.println("You can tell it's a graveyard because of the graves :)");

    System.out.println("\nOn the topic of graves, would you like to go in?");

    String choice = hero.matchInput(new String[]{"Leave", "Enter"});

    if (choice.equals("Enter")) {
      System.out.println("You go further into the graveyard...");
      System.out.println("You see a large house in the middle of the graveyard (as one does)...");
      System.out.println("Where would you like to explore?");

      choice = hero.matchInput(new String[]{"Inside", "Outside"});

      if (choice.equals("Inside")) {
        System.out.println("You choose to explore inside the epic house!");
        System.out.println("You notice the door is locked, so you must lockpick it.");

        System.out.println("To lockpick the door:");
        System.out.println("- There's a random number, 1 through 10");
        System.out.println("- You have to guess within 1 number of the rng");
        System.out.println("Good luck!");

        int intchoice = hero.nextInt();
        int computer = (int)(Math.random()*10);

        if (intchoice > (computer - 1) && intchoice < (computer + 1)) {
          System.out.println("You successfully open the door and get in unnoticed");
          System.out.println("Looking inside, there's just some chest.");

          if ((int)(Math.random() * 3) == 0) {
            System.out.println("It might be a mimic :)");
          }

          if ((int)(Math.random() * 3) == 0) {
            System.out.println("You open the chest and find the Necronomicon!");
            System.out.println("You have no idea how to use it but at least you can now summon Spooky Scary Skeletons");
            hero.changeLives(1);
          } else {
            System.out.println("You open the chest and it turns out to be a mimic!");

            choice = hero.matchInput(new String[]{"Fight", "Run"});

            if (choice.equals("Fight")) {
              System.out.println("You decide to fight the Mimic!");

              if ((int)(Math.random()*3) == 0) {
                System.out.println("You punch the mimic in it's mouth and it dies");
                System.out.println("As it dies, it pukes up an item!");
                System.out.println("It's some weird potion!");

                hero.changeLives(1);
              } else {
                System.out.println("You try to punch the mimic but it grabs onto your arm and pulls you in!");

                hero.changeLives(-1);
                if (hero.isDead()) {
                  System.out.println("The mimic then chops your body to pieces and eats you alive :)");
                  System.out.println("A pretty gruesome death");

                  endGame();
                }

                System.out.println("You start flailing around and manage to dislodge yourself");
                System.out.println("You then run away like a baby :/");
              }
            } else {
              System.out.println("You try to run away!");

              if ((int)(Math.random()*4) == 0) {
                System.out.println("As you try to run away, you trip on a grave");

                hero.changeLives(-1);
                if (hero.isDead()) {
                  System.out.println("In the process of tripping over the grave, you awaken the zombie underneath");
                  System.out.println("It grabs you and pulls you down");

                  endGame();
                }

                System.out.println("You only take minor blunt trauma damage, and keep fleeing anyways");
              }

              System.out.println("You manage to successfully flee");
            }
          }
        } else {
          System.out.println("Oh no! You got it wrong!");
          System.out.println("You make such a racket that some skeleton guy came outside");

          hero.changeLives(-1);
          if (hero.isDead()) {
            System.out.println("The skeleton guy punches you hard across the head, killing you instantly.");

            endGame();
          }

          System.out.println("He just bonks you on the head and you pass out");
        }
      } else {
        System.out.println("As you explore outside, you come across this skeleton man");
        System.out.println("He asks you if you want to play rock-paper-scissors");

        boolean boolchoice = hero.nextBool();

        if (boolchoice) {
          System.out.println("Rules for the game:");
          System.out.println("- Best of 3");
          System.out.println("- Its just rock paper scissors");

          int player = 0;
          int enemy = 0;

          while(true) {
            int random = (int) (Math.random() * 3);
            choice = hero.matchInput(new String[]{"Rock", "Paper", "Scissors"});

            if ((choice.equals("Rock") && random == 3) || (choice.equals("Paper") && random == 1) || (choice.equals("Scissors") && random == 2)) {
              System.out.println("You guessed correctly!");
              player++;
            } else {
              System.out.println("You guessed incorrectly!");
              enemy++;
            }

            if (player == 2) {
              System.out.println("You won rock-paper-scissors!");

              if (!((int)(Math.random()*4) == 0)) {
                System.out.println("The Skeleton guy says good game and leaves");
              } else {
                System.out.println("The Skeleton guy says that you deserve a reward for being so good");
                hero.changeLives(1);
              }
              break;
            }

            if (enemy == 2) {
              System.out.println("You lost rock-paper-scissors! :P");
              System.out.println("Skill issue");

              if ((int)(Math.random()*2) == 0) {
                System.out.println("The Skeleton wishes to take his reward for beating you...");

                hero.changeLives(-1);
                if (hero.isDead()) {
                  System.out.println("The Skeleton clubs you across the head and drags you dead body to do... Stuff");

                  endGame();
                }

                System.out.println("The Skeleton rips off you funny-bone and takes it for his \"collection\"");
              } else {
                System.out.println("The Skeleton says good game and suspiciously walks away");
                System.out.println("... Maybe he was cheating?");
              }

              break;
            }
          }
        } else {
          System.out.println("The skeleton seems somewhat displeased with you...");

          if ((int)(Math.random()*4) == 0) {
            System.out.println("As a result, he attacks you :)");

            hero.changeLives(-1);
            if (hero.isDead()) {
              System.out.println("He socks you in the gut and you die");
              System.out.println("Pretty sad tbh");

              endGame();
            }

            System.out.println("He punches you in your stomach, making you wince to the ground");
            System.out.println("He walks away, moaning about kids these days");
          }
        }
      }

    } else {
      System.out.println("You try to leave...");

      if ((int)(Math.random()*3) == 0) {
        System.out.println("You succeed at leaving... Wow... Such Adventure...");
      } else {
        System.out.println("And you notice the gate is locked... What do we do now...");

        choice = hero.matchInput(new String[]{"Lockpick", "Jump Fence"});

        if (choice.equals("Lockpick")) {
          System.out.println("Fun lockpick minigame time!11!1!");
          System.out.println("There's a random number, 1 through 10");
          System.out.println("You have to guess within a number of the rng");
          System.out.println("Good luck!");

          int intchoice = hero.nextInt();
          int computer = (int)(Math.random()*10);

          if (intchoice > (computer - 1) && intchoice < (computer + 1)) {
            System.out.println("Great guess!");
            System.out.println("You actually manage to escape!");
            System.out.println("I'm disapointed that you didn't go inside but hey, at least you were able to play this terrible minigame!");

            hero.changeLives(1);
          } else {
            System.out.println("Oh no! You got it wrong!");
            System.out.println("The lock looks very displeased at you... Well...");
            System.out.println("Might as well jump the fence...");

            hero.changeLives(-1);
            if (hero.isDead()) {
              System.out.println("You manage to somehow impale yourself against the fence...");
              endGame();
            }

            System.out.println("You jump the fence and hit some thorn bushes on the outside...");
            System.out.println("You ask yourself why they put them there but hey... Stops people like you...");
          }
        } else {

          if (!((int)(Math.random() * 3) == 0)) {
            hero.changeLives(-1);
            if (hero.isDead()) {
              System.out.println("You manage to somehow impale yourself against the fence...");
              endGame();
            }

            System.out.println("You jump the fence and hit some thorn bushes on the outside...");
            System.out.println("You ask yourself why they put them there but hey... Stops people like you...");
          } else {
            System.out.println("You manage to jump the fence successfully");
            System.out.println("Probably would've been safer to lockpick though...");
          }
        }
      }
    }

    regions++;
  }

  // Mountain Region
  @SuppressWarnings("unused")
  private void region2() {
    console.setImage("./assets/mountains.jpg");

    // Idk
  }

  // Ocean Region
  @SuppressWarnings("unused")
  private void region3() {
    console.setImage("./assets/ocean.jpg");

    // Fishing, Boat, Kraken
  }

  // Forest Region
  @SuppressWarnings("unused")
  private void region4() {
    console.setImage("./assets/forest.jpg");

    // Forest?
  }

  // Pumpkin Region
  @SuppressWarnings("unused")
  private void region5() {
    console.setImage("./assets/pumpkintrio.jpg");

    // 3 pumpkins :0
  }

  // Corn Region
  @SuppressWarnings("unused")
  private void region6() {
    console.setImage("./assets/corn.jpg");

    // Mr Scarecrow Man
  }

  // Desert Region
  @SuppressWarnings("unused")
  private void region7() {
    console.setImage("./assets/desert.jpg");
    // Make them walk for a long time :)

    while(true) {
      String choice = hero.matchInput(new String[]{"Stop", "Walk"});
      if (choice.equals("Stop")) {
        System.out.println("You give up on finding the elderado.");
        System.out.println("I expected great things from you...");
        break;
      } else if (choice.equals("Walk")) {
        if ((int)(Math.random()*5) == 0) {
          System.out.println("You found an oasis!");
          System.out.println("It's waters are glistening in the sun and there are palm trees abound");
          System.out.println("We're just gonna ignore the fact that there's no animals or anything else nearby...");

          choice = hero.matchInput(new String[]{"Drink", "Abstain"});

          if (choice.equals("Drink") && ((int)(Math.random() * 3) == 0)) {
            System.out.println("You go down to the oasis for a drink...");
            System.out.println("It turns out it's the spring of life!");
            System.out.println("What a great boon!");

            hero.changeLives(2);
          } else if (choice.equals("Abstain") && ((int)(Math.random() * 2) == 0)) {
            System.out.println("You correctly abstain from drinking the water");
            System.out.println("The water was actually just an illusion...");
            System.out.println("You decide to just leave");

            hero.changeLives(1);
          } else {
            if (choice.equals("Drink")) {
              System.out.println("You drink the water, and it turned out to be contaminated");
              System.out.println("Who could've expected that a person who's only drunk sterilized isn't compatable with random spring water?");
              System.out.println("You should really make better life choices...");

              hero.changeLives(-1);
            } else {
              System.out.println("You choose to not drink your only source of water...");
              System.out.println("You are forced to return as you're super parched");

              hero.changeLives(-1);
            }
          }

          break;
        }

        System.out.println("More desert... Just more desert...");
        System.out.println("I don't know what you expected to find out here...");

        if ((int)(Math.random() * 3) == 0) {
          System.out.println("Hint: maybe keep walking?");
        }
      }
    }

    regions++;
  }

  // Beach Region
  @SuppressWarnings("unused")
  private void region8() {
    console.setImage("./assets/beach.jpg");

    // Seagull
  }

  // Sky Region
  @SuppressWarnings("unused")
  private void region9() {
    console.setImage("./assets/sky.jpg");

    System.out.println("You find your self in... The Sky?");
    System.out.println("You probably died already, so now you're on the quest to Detroit: Become Human:tm:");

    System.out.println("\nYou are approached by an Angel");

    System.out.println("ANGEL: Hey kid, are you good at math?");

    boolean boolchoice = hero.nextBool();

    if (boolchoice) {
      System.out.println("Great!");

      int first;
      int second;

      while(true) {
        first = (int)(Math.random()*100);
        second = (int)(Math.random()*100);

        System.out.println("\nSolve this simple equation for me:");
        System.out.println(first +" + "+ second + " = ?");
        if (hero.nextInt() == (first + second)) {
          System.out.println((first+second) +" is correct!");

          if (((int) (Math.random() * 5)) == 0) {
            System.out.println("That is all; thanks!");
            break;
          } else if (((int) (Math.random() * 20)) == 0) {
            System.out.println("Really great job!");
            System.out.println("Here is your reward:");
            hero.changeLives(1);
            break;
          }
        } else {
          System.out.println("Bro you got it wrong...");
          System.out.println("Did you lie to me??1?!?/?!//?");

          hero.changeLives(-1);
          if (hero.isDead()) {
            System.out.println("The Angel then thanos-snaps you out of existance of your crimes");
            endGame();
          }

          System.out.println("The Angel, in anger, throws you back to earth");
          System.out.println("Maybe you shouldn't lie about your skills, smh");
          break;
        }
      }
    } else {
      System.out.println("Bruh man...");
      System.out.println("I didn't expect kindergarteners to play this game...");
      System.out.println("Just... Get out of here then");

      if ((int)(Math.random()*4) == 0) {
        System.out.println("The Angel shoves you out of the sky and you come crashing down to the ground");

        hero.changeLives(-1);
        if (hero.isDead()) {
          System.out.println("You go splat on the ground");
          System.out.println("A seagull is happily slurping up your body :)");

          endGame();
        }

        System.out.println("You manage to not die and instead breaks a few bones");
        System.out.println("Idk how you even got up there...");
        System.out.println("Let alone how you thought you'd get down");
      } else {
        System.out.println("The Angel teleports you down like woosh and now you're not there anymore!");
      }
    }

    // Angel
    regions++;
  }

  public void play() {
    tutorial();

      while (true) {
        continueJourney();
        region9();
      }

    // nextRegion();
  }

  private void continueJourney() {
    System.out.println("\n-~-~-~-~-~-~-~\n");
    System.out.println("Would you like to continue on your journey?");

    boolean choice = hero.nextBool();

    if (!choice) {
      if (regions == 0) {
        System.out.println("Bro... Why are you giving up already?");
        System.out.println("That's just... Sad");
      } else if (regions <= 3) {
        System.out.println("You did moderately well");
        System.out.println("I'm surprised you haven't gotten killed by the RNG yet...");
        System.out.println("Maybe I should change the odds a bit... ;)");
      } else if (regions <= 5) {
        System.out.println("Wow, you managed to do 1/2 of it");
        System.out.println("That's an achievement if I've ever seen one");
      } else if (regions <= 10) {
        System.out.println("DANG!!!");
        System.out.println("You managed to survive this whole game?");
        System.out.println("Props to you!");
      } else  {
        System.out.println("You're probably hacking, but who cares");
        System.out.println("Great job!");
      }

      endGame();
    }
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
    System.out.println("\nYou have ended your journey!");
    System.out.println("You have visited: " + regions + " regions!");

    // Intentionally repeat this, breaking the control flow
    while(true) {
      console.getTextArea().setEditable(false);
      console.closeScanner();
    }
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
