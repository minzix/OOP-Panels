import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ThirdPanel extends JPanel {
	// Array representing the number of tasks for each course
    private int[] taskCounts = {10, 1, 1, 4, 8, 4};
    // Array containing the names of the courses
    private String[] courseNames = {
        "객체지향\n프로그래밍",
        "데이터\n구조",
        "물과공기의화학",
        "딥러닝",
        "웹프로그래밍\n기초",
        "오픈소스\n프로그래밍"
    };
    // Array of colors to be used for each bar
    private Color[] barColors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA};

    public ThirdPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border
        
        // Create a label for the title and align it to the center
        JLabel titleLabel = new JLabel("<html><br>&lt;내가 수강하는 강의별 과제 개수에 대한 막대차트&gt;</html>");
        
        // Add labels
        titleLabel.setHorizontalAlignment(JLabel.CENTER); 
        add(titleLabel, BorderLayout.NORTH); 
        add(new BarChartPanel(), BorderLayout.CENTER);
        
        // Create a button to change the colors of the bars
        JButton changeColorButton = new JButton("Randomize");
        changeColorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                
                // Create a new color array
                Color[] newBarColors = new Color[barColors.length];
                for (int i = 0; i < newBarColors.length; i++) {
                    newBarColors[i] = getRandomColor();

                    // Ensure that the new color does not overlap with existing colors
                    while (colorOverlap(newBarColors, i)) {
                        newBarColors[i] = getRandomColor();
                    }
                }

                barColors = newBarColors; // Assign the new color array
                repaint(); // Repaint the panel to reflect the color changes
            }
        });
        add(changeColorButton, BorderLayout.SOUTH);
    }
    // Generate a random color
    private Color getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }
    // Check if a new color overlaps with existing colors
    private boolean colorOverlap(Color[] colors, int currentIndex) {
        for (int i = 0; i < currentIndex; i++) {
            if (colors[i].equals(colors[currentIndex])) {
                return true;
            }
        }
        return false;
    }
    // Inner class representing the bar chart panel
    private class BarChartPanel extends JPanel {
        public BarChartPanel() {
            setPreferredSize(new Dimension(300, 300));
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int startY = 50;// Y-coordinate of the start point for the bars
            int endY = getHeight() - 50; // Y-coordinate of the end point for the bars

            int barWidth = (getWidth() - 100) / taskCounts.length; // Width of each bar
            int currentX = 50; // X-coordinate of the current bar

            g.setColor(Color.BLACK);
            g.drawLine(currentX, startY, currentX, endY); // Vertical axis
            g.drawLine(currentX, endY, getWidth() - 50, endY); // Horizontal axis
            
            // Calculate the height of the bar based on the task count
            for (int i = 0; i < taskCounts.length; i++) {
                int barHeight = (int) ((double) taskCounts[i] / 10 * (endY - startY));

                g.setColor(barColors[i]);
                g.fillRect(currentX, endY - barHeight, barWidth, barHeight);

                g.setColor(Color.BLACK);
     
                String[] lines = courseNames[i].split("\n"); // Split the course name into multiple lines
                int lineHeight = g.getFontMetrics().getHeight();
                
                for (int j = 0; j < lines.length; j++) {
                    int lineY = endY + lineHeight * (j + 1);
                    g.drawString(lines[j], currentX + (barWidth / 2) - g.getFontMetrics().stringWidth(lines[j]) / 2, lineY);
                }

                currentX += barWidth; // Move to the next bar position
            }
        }
    }
}
