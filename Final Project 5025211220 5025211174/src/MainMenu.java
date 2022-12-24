import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;



public class MainMenu extends JFrame {

    Font titleFont = new Font("Monospaced", Font.PLAIN, 40);
    Font normalFont = new Font("Monospaced", Font.PLAIN, 20);
    JTextField playerName;
    public int gameLevel;
    private About about;

    JRadioButton easy;
    JRadioButton medium;
    JRadioButton hard;

    
    
    
    public MainMenu(){
        super("Simulator Meriam - Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setSize(400, 600);
        setResizable(false);
        setBackground(Color.black);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.setBackground(Color.black);

        JLabel gameTitle = new JLabel("Simulator Meriam");
        gameTitle.setForeground(Color.white);
        gameTitle.setFont(titleFont);
        titlePanel.add(gameTitle);

        add(titlePanel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        GridLayout buttonsLayout = new GridLayout(0,1);
        buttonsLayout.setVgap(5);
        buttonsPanel.setLayout(buttonsLayout);
        buttonsPanel.setBackground(Color.black);

        playerName = new JTextField("Guest");
        playerName.setBackground(Color.gray);
        playerName.setForeground(Color.white);
        playerName.setBorder(null);
        playerName.setHorizontalAlignment(JTextField.CENTER);
        playerName.setFont(normalFont);
        buttonsPanel.add(playerName);

        JButton highScoreButton = new JButton("High Score");
        highScoreButton.setFont(normalFont);
        highScoreButton.setHorizontalAlignment(JLabel.CENTER);
        highScoreButton.setBackground(Color.black);
        highScoreButton.setForeground(Color.white);
        buttonsPanel.add(highScoreButton);

        highScoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
                new HighScore(MainMenu.this).setVisible(true);
			}
		});

        JButton aboutButton = new JButton("About Game");
        about = new About();
        aboutButton.setFont(normalFont);
        aboutButton.setHorizontalAlignment(JLabel.CENTER);
        aboutButton.setBackground(Color.black);
        aboutButton.setForeground(Color.white);
        buttonsPanel.add(aboutButton);

        aboutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				about.setVisible(true);
			}
		});

    // -------------------- LEVEL -------------------- //
        JPanel levelPanel = new JPanel();
        FlowLayout levelLayout = new FlowLayout();
        levelLayout.setHgap(5);
        levelPanel.setLayout(levelLayout);
        levelPanel.setBackground(Color.black);

        // Level - easy
        easy = new JRadioButton("Easy", true);
        easy.setBackground(Color.green);
        easy.setFont(normalFont);

        // Level - medium
        medium = new JRadioButton("Medium", false);
        medium.setBackground(Color.orange);
        medium.setFont(normalFont);

        // Level - hard
        hard = new JRadioButton("Hard", false);
        hard.setBackground(Color.red);
        hard.setFont(normalFont);

        levelPanel.add(easy);
        levelPanel.add(medium);
        levelPanel.add(hard);

        // logical relationship
        ButtonGroup levelGroup = new ButtonGroup();
        levelGroup.add(easy);
        levelGroup.add(medium);
        levelGroup.add(hard);

        buttonsPanel.add(levelPanel);

        easy.addItemListener(new LevelListener(1));
        medium.addItemListener(new LevelListener(2));
        hard.addItemListener(new LevelListener(3));



    // -------------------------------------------------- //

        JButton playButton = new JButton("Play!");
        playButton.setFont(normalFont);
        playButton.setHorizontalAlignment(JLabel.CENTER);
        playButton.setBackground(Color.black);
        playButton.setForeground(Color.white);
        playButton.addActionListener(new PlayGameListener(gameLevel));
        buttonsPanel.add(playButton);
        
        add(buttonsPanel, BorderLayout.CENTER);

        

        pack();
        setLocationRelativeTo(null);
    }

    private class PlayGameListener implements ActionListener{

        public PlayGameListener(int lv){
            gameLevel = lv;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            setVisible(false);
            new GameFrame(MainMenu.this, playerName.getText(), gameLevel).setVisible(true);
        }
    }

    private class LevelListener implements ItemListener{
        private int lv;
        public LevelListener(int l){
            lv = l;
        }

        @Override
        public void itemStateChanged(ItemEvent event){
            gameLevel = lv;
        }

    }
   
}
