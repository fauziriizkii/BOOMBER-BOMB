import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HighScore extends JFrame {
    Font titleFont = new Font("Monospaced", Font.PLAIN, 40);
    Font normalFont = new Font("Monospaced", Font.PLAIN, 20);
    JLabel highScore;
    JLabel dummyData;
    MainMenu mainMenu;


    public HighScore(MainMenu mainMenu){
        super("Highscore");
        this.mainMenu = mainMenu;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(600,400);
        setBackground(Color.black);

        JPanel hsPanel = new JPanel();
        hsPanel.setBackground(Color.BLACK);
        hsPanel.setLayout(new BorderLayout());
        highScore = new JLabel("Highscore");
        highScore.setFont(titleFont);
        highScore.setForeground(Color.WHITE);
        highScore.setHorizontalAlignment(JLabel.CENTER);
        hsPanel.add(highScore, BorderLayout.NORTH);

        dummyData = new JLabel();
        dummyData.setFont(normalFont);
        dummyData.setForeground(Color.WHITE);
        dummyData.setHorizontalAlignment(JLabel.CENTER);
        readFile();

        hsPanel.add(dummyData,BorderLayout.CENTER);
        add(hsPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    public void readFile(){
        try{
            File hs = new File("highscore.txt");
            Scanner reader = new Scanner(hs);
            while(reader.hasNextLine()){
                String data = reader.nextLine();
                dummyData.setText("<html>" + dummyData.getText() + "<br/>" + data);
            }
            reader.close();
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void dispose(){
        super.dispose();
        mainMenu.setVisible(true);
    }

    
}
