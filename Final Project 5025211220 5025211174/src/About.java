import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class About{
    private JButton backButton;
	private JPanel about;
	private JFrame frame;
    public About(){
        frame = new JFrame("About");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        backButton = new JButton("Close");
        backButton.setHorizontalAlignment(JLabel.CENTER);
        backButton.setBackground(Color.black);
        backButton.setForeground(Color.white);
        about = new JPanel(new GridBagLayout());
        backButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        frame.getContentPane().setBackground(Color.BLACK);
        about.setBackground(Color.BLACK);
        frame.setResizable(true);
        frame.setLayout(new GridBagLayout());

        JLabel description = new JLabel("<html><h2>Built by</h2>"+
        "    <ol>"+
        "        <li>Fauzi Rizki Pratama - 5025211220</li>"+
        "        <li>Alya Putri Salma - 5025211174</li>"+
        "    </ol>"+
        "    <h2>Description</h2>"+
        "    <p style=\"text-indent: 30px;\">Simulator Meriam merupakan game sederhana yang dirancang menggunakan Java Swing Framework." +
        "    Anda dapat menembakkan bola meriam dari bermacam-macam level yang tersedia dan mendapatkan skor tertinggi. Anda dapat "+
        "    mengontrol meriam menggunakan mouse yang membantu akurasi Anda dalam menembak.</p>");
            
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.CENTER;
        c.ipady = 90;
        c.ipadx = 220;
        about.add(description, c);


        c.ipady = 0;
        c.ipadx = 0;
        c.gridy = 1;
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0,0,0,0);
		this.backButton.setEnabled(true);
        about.add(backButton, c);
		
		frame.add(about);
		frame.pack();
        frame.setSize(400, 500);
		frame.setVisible(false);
        description.setForeground(Color.WHITE);
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
