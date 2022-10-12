import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.List;

public class Console extends JFrame {
    private JTextArea textArea;
    private String consoleText;
    private JLabel consoleImage;

    private final PipedInputStream inPipe = new PipedInputStream();
    private final PipedInputStream outPipe = new PipedInputStream();

    PrintWriter inWriter;

    Console(String title, int width, int height) {
        // Set the title of the JFrame to input title
        super(title);

        // Initialize consoleText to empty (not NULL)
        consoleText = "";

        // Initialize the streams
        initializeStreams();

        // Create the GUI elements
        buildGui(width, height);

        // Edit key-binds for console use
        initializeKeys();

        // Create the CaretListener & stop it from deleting console text
        initializeCaretListener();

        // Manages writing new scanner inputs to the console
        initializeSwingWorker();

        // Disable editing it (re-enabled for user input)
        textArea.setEditable(false);
    }

    private void initializeSwingWorker() {
        new SwingWorker<Void, String>() {
            protected Void doInBackground() {
                // Initialize Scanner
                Scanner stdin = new Scanner(outPipe);

                // Repeat while there's still another line in the scanner
                while (stdin.hasNextLine()) {

                    // Sends data to the "process" method
                    publish(stdin.nextLine());
                }

                // Close the scanner
                stdin.close();
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                // Append each line to the console
                for (String line : chunks) {
                    textArea.append("\n"+line);
                }

                // Update content of text area
                consoleText = textArea.getText();
            }
        }.execute();
    }

    private void initializeCaretListener() {
        CaretListener caretListen = caretEvent -> {

            // Get current caret position
            int caretPos = caretEvent.getDot();

            // If the caret is behind the original text & the original text is less than or equal to the current text, jump it to the end of the original
            if (caretPos < consoleText.length() && textArea.getText().length() >= consoleText.length()) {
                textArea.setCaretPosition(consoleText.length());
            }

            // If the caret is behind the original text & the current text is less than the original, jump it to the end of the current.
            // Shouldn't really ever happen as we are trying to stop them from deleting content.
            if (caretPos < textArea.getText().length() && textArea.getText().length() < consoleText.length()) {
                textArea.setCaretPosition(textArea.getText().length());
            }
        };

        textArea.addCaretListener(caretListen);
    }

    private void handleEnter(KeyEvent event) {
        // Get the current length and original length
        int currLength = textArea.getText().length();
        int oldLength = consoleText.length();

        event.consume();

        // If the current length is different from the original, steal everything new
        if (currLength > oldLength) {
            inWriter.println(textArea.getText().substring(oldLength, currLength));
        }

        // If the current length is less than or equal to the original length, it's failed
        if (currLength <= oldLength) {
            inWriter.println((String) null);
        }

        // Update consoleText to the text box's text
        consoleText = textArea.getText();
    }

    private void initializeKeys() {
        // Contains an int enum which controls its focus
        textArea.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event){
                switch (event.getKeyCode()) {
                    case KeyEvent.VK_BACK_SPACE -> handleBackspace(event);
                    case KeyEvent.VK_ENTER -> handleEnter(event);
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    private void handleBackspace(KeyEvent event) {
        // Get current length of the text area & the original length before editing
        int currLength = textArea.getText().length();
        int oldLength = consoleText.length();


        // If there's been deleted content, set the text to the original
        if (currLength <= oldLength) {
            // Stops it from executing the backspace event code
            event.consume();

            // Kept to stop people from doing weird tricks and deleting it
            textArea.setText(consoleText);
        }
    }

    private void buildGui(int width, int height) {
        // Create the parent element
        JPanel panel = new JPanel(new BorderLayout());

        // Initialize console text area
        textArea = new JTextArea(20,40);

        // Create scroll bar & put the console text area into it
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Parent the scroll pane to the panel and center it
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create image label
        consoleImage = new JLabel();

        // Parent the console image to the panel and put it at the top.
        panel.add(consoleImage, BorderLayout.NORTH);

        // Add the panel component to the global component.
        add(panel);

        // Make the application quit on exit.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Enable the global element.
        setVisible(true);

        // Set the size of the global element
        setSize(width, height);
    }

    private void initializeStreams() {
        // Make the GUI console the new System.in
        System.setIn(inPipe);

        // Redirect System.out to the PrintStream
        try {
            System.setOut(new PrintStream(new PipedOutputStream(outPipe), true));
        } catch (Exception e) {
            System.out.println("[ERROR]: Failed to initiate the PipedOutputStream\n"+e);
        }

        // Create the PrintWriter from the PrintStream.
        try {
            inWriter = new PrintWriter(new PipedOutputStream(inPipe), true);
        } catch (Exception e) {
            System.out.println("[ERROR]: Failed to initiate the PrintWriter\n"+e);
        }
    }

    public Void setImage(String imagePath) {
        // Get the file path
        File stream = new File(Paths.get(imagePath).toString());

        // Initialize image, can't be null as it'd return early if it can't read the file
        ImageIcon image;

        // Reads file, returns early at fail
        try {
            image = new ImageIcon(ImageIO.read(stream));
        } catch (IOException e) {
            System.out.println("[ERROR]: Could not read file "+imagePath+" to input stream");
            return null;
        }

        // Scale the image appropriately
        final Image img = image.getImage().getScaledInstance(this.getWidth(), -1, Image.SCALE_SMOOTH);

        // Create a new image icon
        image = new ImageIcon(img);

        // Set the icon of the console image to the image
        consoleImage.setIcon(image);

        return null;
    }
}
