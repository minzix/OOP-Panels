import java.awt.*; // to create windows, panels, buttons, labels, etc.
import javax.swing.*; // to provide additional GUI components

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("My Chart: 2213252 김민지"); // Set the title of the frame
        
        // Set the default close operation to exit the application when the frame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the layout of the frame as a grid with 2 rows and 3 columns
        setLayout(new GridLayout(2, 3));
        
        // Add panels to the frame
        add(new FirstPanel());
        add(new SecondPanel());
        add(new ThirdPanel());
        add(new FourthPanel());
        add(new FifthPanel());
        add(new SixthPanel());
        
        pack(); // Adjust the size of the frame to fit its contents
        setVisible(true); // Make the frame visible
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
