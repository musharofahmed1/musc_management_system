import javax.swing.JFrame;
import java.awt.Color;
public class NoticeBoard extends JFrame {
    public NoticeBoard() {
        setTitle("Notice Board");
        setSize(1040, 508); // Set the size to match panel3
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close only this window, not the whole application
        getContentPane().setBackground(new Color(220,220,220)); // Set background color
        
        // Set location to 100 pixels from the left and 100 pixels from the top of the screen
        setLocation(230, 140);
    }
}
