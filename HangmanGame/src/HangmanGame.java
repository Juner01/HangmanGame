import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

public class HangmanGame extends JFrame {

    private Theme currentTheme;
    private String secretWord;
    private String secretClue;
    private StringBuilder guessedWordDisplay;
    private int incorrectGuesses;
    private static final int MAX_INCORRECT_GUESSES = 6;

    private HangmanCanvas hangmanCanvas;
    private JLabel wordLabel;
    private JLabel guessesLeftLabel;
    private JPanel mainPanel;
    private JPanel keyboardPanel;
    private JButton clueButton;

    public HangmanGame() {
        setTitle("Hangman");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createHomeScreen();
        setVisible(true);

        // Add a key listener to handle PC keyboard input
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                if (Character.isLetter(key)) {
                    // Find the corresponding button and simulate a click
                    char guess = Character.toUpperCase(key);
                    for (Component comp : keyboardPanel.getComponents()) {
                        if (comp instanceof JButton) {
                            JButton button = (JButton) comp;
                            if (button.getText().charAt(0) == guess) {
                                button.doClick();
                                return;
                            }
                        }
                    }
                }
            }
        });
        setFocusable(true);
        requestFocusInWindow();
    }

    private void createHomeScreen() {
        if (mainPanel != null) {
            remove(mainPanel);
        }

        mainPanel = new CloudyPanel();
        mainPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Hangman", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 72));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 40));

        JButton marioButton = new JButton("Super Mario");
        marioButton.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        marioButton.setPreferredSize(new Dimension(200, 60));
        marioButton.setBackground(new Color(255, 215, 0));
        marioButton.setForeground(Color.BLACK);
        marioButton.addActionListener(e -> startGame(new MarioTheme()));

        JButton tvButton = new JButton("TV Shows");
        tvButton.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        tvButton.setPreferredSize(new Dimension(200, 60));
        tvButton.setBackground(new Color(255, 215, 0));
        tvButton.setForeground(Color.BLACK);
        tvButton.addActionListener(e -> startGame(new TVTheme()));

        JButton foodButton = new JButton("Food");
        foodButton.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        foodButton.setPreferredSize(new Dimension(200, 60));
        foodButton.setBackground(new Color(255, 215, 0));
        foodButton.setForeground(Color.BLACK);
        foodButton.addActionListener(e -> startGame(new FoodTheme()));

        JButton scoresButton = new JButton("High Scores");
        scoresButton.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        scoresButton.setPreferredSize(new Dimension(200, 60));
        scoresButton.setBackground(new Color(255, 215, 0));
        scoresButton.setForeground(Color.BLACK);
        scoresButton.addActionListener(e -> viewHighScores());

        buttonPanel.add(marioButton);
        buttonPanel.add(tvButton);
        buttonPanel.add(foodButton);
        buttonPanel.add(scoresButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void createGameScreen(JPanel themePanel, Color groundColor, Color buttonColor) {
        remove(mainPanel);
        mainPanel = themePanel;
        mainPanel.setLayout(new BorderLayout());

        hangmanCanvas = new HangmanCanvas();
        mainPanel.add(hangmanCanvas, BorderLayout.CENTER);

        JPanel topDisplayPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        topDisplayPanel.setOpaque(false);

        wordLabel = new JLabel("", SwingConstants.CENTER);
        wordLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        wordLabel.setForeground(Color.WHITE);

        guessesLeftLabel = new JLabel("", SwingConstants.CENTER);
        guessesLeftLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        guessesLeftLabel.setForeground(Color.WHITE);

        clueButton = new JButton("Clue");
        clueButton.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
        clueButton.setBackground(buttonColor);
        clueButton.setForeground(Color.BLACK);
        clueButton.addActionListener(e -> showClue());

        topDisplayPanel.add(wordLabel);
        topDisplayPanel.add(guessesLeftLabel);
        topDisplayPanel.add(clueButton);
        mainPanel.add(topDisplayPanel, BorderLayout.NORTH);

        mainPanel.add(keyboardPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
        
        requestFocusInWindow();
    }

    // This method has been reverted to create clickable JButtons
    private void createKeyboard(Color groundColor, Color buttonColor) {
        keyboardPanel = new JPanel();
        keyboardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        keyboardPanel.setBackground(groundColor);

        for (char c = 'a'; c <= 'z'; c++) {
            JButton button = new JButton(String.valueOf(c).toUpperCase());
            button.setPreferredSize(new Dimension(50, 50));
            button.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
            button.setBackground(buttonColor);
            button.setForeground(Color.BLACK);
            button.addActionListener(new GuessListener(c, buttonColor));
            
            keyboardPanel.add(button);
        }
    }
    
    // This is the GuessListener class that handles button clicks
    private class GuessListener implements ActionListener {
        private char letter;
        private Color buttonColor;
    
        public GuessListener(char letter, Color buttonColor) {
            this.letter = letter;
            this.buttonColor = buttonColor;
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            button.setEnabled(false);
    
            char guess = Character.toLowerCase(letter);
    
            boolean correctGuess = false;
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == guess) {
                    guessedWordDisplay.setCharAt(i * 2, guess);
                    correctGuess = true;
                }
            }
    
            if (!correctGuess) {
                incorrectGuesses++;
                button.setBackground(Color.RED);
            } else {
                button.setBackground(new Color(50, 205, 50));
            }
    
            updateUI();
            checkGameState();
            
            // This call is necessary to regain keyboard focus
            HangmanGame.this.requestFocusInWindow();
        }
    }

    private void startGame(Theme selectedTheme) {
        this.currentTheme = selectedTheme;
        
        createKeyboard(currentTheme.getGroundColor(), currentTheme.getButtonColor()); 

        JPanel themePanel = currentTheme.getBackgroundPanel();
        Color groundColor = currentTheme.getGroundColor();
        Color buttonColor = currentTheme.getButtonColor();

        createGameScreen(themePanel, groundColor, buttonColor);

        Random rand = new Random();
        String[] words = currentTheme.getWords();
        String[] clues = currentTheme.getClues();
        int wordIndex = rand.nextInt(words.length);
        secretWord = words[wordIndex].toLowerCase();
        secretClue = clues[wordIndex];

        guessedWordDisplay = new StringBuilder();
        for (int i = 0; i < secretWord.length(); i++) {
            guessedWordDisplay.append("_ ");
        }
        incorrectGuesses = 0;
        updateUI();
    }

    private void showClue() {
        JOptionPane.showMessageDialog(this, secretClue, "Clue", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateUI() {
        wordLabel.setText(guessedWordDisplay.toString());
        guessesLeftLabel.setText("Guesses Left: " + (MAX_INCORRECT_GUESSES - incorrectGuesses));
        hangmanCanvas.setIncorrectGuesses(incorrectGuesses);
        hangmanCanvas.setWordToDisplay(guessedWordDisplay.toString());
    }
    
    private void checkGameState() {
        if (guessedWordDisplay.toString().replaceAll(" ", "").equals(secretWord)) {
            JOptionPane.showMessageDialog(this, "You win! The word was: " + secretWord, "Game Over", JOptionPane.INFORMATION_MESSAGE);
            startGame(currentTheme);
        } else if (incorrectGuesses >= MAX_INCORRECT_GUESSES) {
            String playerName = JOptionPane.showInputDialog(this, "You lose! The word was: " + secretWord + "\n\nEnter your name to save your score:");
            if (playerName != null && !playerName.trim().isEmpty()) {
                ScoreManager.saveScore(playerName, "Lost");
            }

            int choice = JOptionPane.showConfirmDialog(this, "Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                createHomeScreen();
            } else {
                System.exit(0);
            }
        }
    }

    private void saveCurrentScore() {
        String playerName = JOptionPane.showInputDialog(this, "You won! Enter your name to save your score:");
        if (playerName != null && !playerName.trim().isEmpty()) {
            int score = secretWord.length() * (MAX_INCORRECT_GUESSES - incorrectGuesses + 1);
            ScoreManager.saveScore(playerName, String.valueOf(score));
        }
    }

    private void viewHighScores() {
        List<String> scores = ScoreManager.loadScores();
        if (scores.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No scores saved yet.", "High Scores", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder("High Scores:\n");
            for (String score : scores) {
                sb.append(score).append("\n");
            }
            JOptionPane.showMessageDialog(this, sb.toString(), "High Scores", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HangmanGame());
    }
}