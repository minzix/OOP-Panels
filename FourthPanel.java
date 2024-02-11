import javax.swing.*;
import java.awt.*;

public class FourthPanel extends JPanel {
	// Array representing the total task counts for each subject
    private int[] totalCounts = {10, 1, 1, 4, 8, 4}; 
    private int[] completedCounts = {7, 0, 0, 3, 5, 3};
    private String[] subjects = {
            "객체지향\n프로그래밍",
            "데이터\n구조",
            "물과공기의\n화학",
            "딥러닝",
            "웹프로그래밍\n기초",
            "오픈소스\n프로그래밍"
    }; // Array containing the names of the subjects
    private Color uncompletedColor = Color.RED; // Color for the uncompleted tasks
    private Color lineColor = Color.YELLOW; // Color for the lines
    private Color completedColor = Color.BLUE; // Color for the completed tasks
    private int barWidth = 10; // Width of each bar

    public FourthPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Width of each bar

        JLabel titleLabel = new JLabel("<html><br>&lt;내가 수강하는 강의별 과제 개수에 대한 그래프&gt;</html>");
        // Add labels
        titleLabel.setHorizontalAlignment(JLabel.CENTER); 
        add(titleLabel, BorderLayout.NORTH);
        add(new ChartPanel(), BorderLayout.CENTER);
    }

    private class ChartPanel extends JPanel {
        public ChartPanel() {
            setPreferredSize(new Dimension(500, 300));
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int startX = 50;
            int endX = getWidth() - 50;
            int startY = getHeight() - 50;
            int endY = 50;

            int xGap = (endX - startX) / (subjects.length - 1);

            // Draw the graph
            for (int i = 0; i < subjects.length; i++) {
                int x = startX + xGap * i;

                // Total task count
                int totalHeight = (int) ((double) totalCounts[i] / getMaxCount() * (startY - endY));
                int totalY = startY - totalHeight;

                // Completed task count
                int completedHeight = (int) ((double) completedCounts[i] / totalCounts[i] * totalHeight);
                int completedY = totalY + (totalHeight - completedHeight);

                // Draw bar graphs
                g.setColor(uncompletedColor);
                g.fillRect(x - barWidth / 2, totalY, barWidth, totalHeight);

                g.setColor(completedColor); 
                g.fillRect(x - barWidth / 2, completedY, barWidth, completedHeight);
            }

            // Draw the yellow graph            
            g.setColor(lineColor);
            for (int i = 0; i < subjects.length - 1; i++) {
                int startXPos = startX + xGap * i;
                int endXPos = startX + xGap * (i + 1);

                int startYPos = startY - (int) ((double) totalCounts[i] / getMaxCount() * (startY - endY));
                int endYPos = startY - (int) ((double) totalCounts[i + 1] / getMaxCount() * (startY - endY));

                g.drawLine(startXPos, startYPos, endXPos, endYPos);
            }

            // Draw the graph labels
            g.setColor(Color.BLACK);
            g.setFont(new Font("Dialog", Font.BOLD, 12));

            for (int i = 0; i < subjects.length; i++) {
                int x = startX + xGap * i;
                int y = startY + 45;

                String[] lines = subjects[i].split("\n");
                int lineHeight = g.getFontMetrics().getHeight(); // Calculate the line height
                int totalLineHeight = lineHeight * lines.length; // Calculate the total line height

                for (int j = 0; j < lines.length; j++) {
                    int textWidth = g.getFontMetrics().stringWidth(lines[j]);
                    int textX = x - textWidth / 2;
                    int textY = y - totalLineHeight + (j * lineHeight);
                    g.drawString(lines[j], textX, textY); // Draw the string for each line
                }
            }
            // Draw the maximum task count value
            g.drawString(String.valueOf(getMaxCount()), startX - 30, endY);
        }
        // Get the maximum task count
        private int getMaxCount() {
            int max = 0;
            for (int count : totalCounts) {
                if (count > max) {
                    max = count;
                }
            }
            return max;
        }
    }
}
