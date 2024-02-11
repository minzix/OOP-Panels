import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SecondPanel extends JPanel {
    private Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.YELLOW, Color.MAGENTA};
    private String[] courseNames = {"객체지향프로그래밍", "데이터구조", "물과공기의화학", "딥러닝", "웹프로그래밍기초", "오픈소스프로그래밍"};
    private int[] numberOfAssignments = {10, 1, 1, 4, 8, 4};
    private PieChartPanel pieChartPanel; // Add a reference to the PieChartPanel

    public SecondPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border

        JLabel titleLabel = new JLabel("<html><br>&lt;내가 수강하는 강의별 과제 개수에 대한 원형 차트&gt;</html>");

        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Align the label to the center
        add(titleLabel, BorderLayout.NORTH); // Add the label to the north (top) position

        pieChartPanel = new PieChartPanel();
        add(pieChartPanel);

        JButton randomizeButton = new JButton("Randomize"); // Create the Randomize button
        randomizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pieChartPanel.randomizeColors(); // Call the randomizeColors() method when the button is clicked
            }
        });
        add(randomizeButton, BorderLayout.SOUTH); // Add the button to the south (bottom) position
    }

    private class PieChartPanel extends JPanel {
        private double[] anglePercentages; // Array to store the angle percentages

        public PieChartPanel() {
            setPreferredSize(new Dimension(300, 300));
            anglePercentages = new double[courseNames.length];
            calculateAnglePercentages(); // Calculate the angle percentages
        }

        private void calculateAnglePercentages() {
            int total = 0;
            for (int i = 0; i < numberOfAssignments.length; i++) {
                total += numberOfAssignments[i];
            }

            for (int i = 0; i < numberOfAssignments.length; i++) {
                anglePercentages[i] = (double) numberOfAssignments[i] / total; // Calculate the angle percentages based on the ratio
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int startAngle = 0;
            int arcAngle;

            for (int i = 0; i < anglePercentages.length; i++) {
                arcAngle = (int) (360 * anglePercentages[i]);

                g.setColor(colors[i]);
                g.fillArc(50, 50, 200, 200, startAngle, arcAngle);

                // Draw small circles for each color
                g.setColor(colors[i]);
                g.fillOval(260, 70 + i * 30, 10, 10);

                // Draw course names next to the small circles
                g.setColor(Color.BLACK);
                g.drawString(courseNames[i], 275, 78 + i * 30);

                startAngle += arcAngle;
            }
        }

        public void randomizeColors() {
            for (int i = 0; i < colors.length; i++) {
                colors[i] = generateRandomColor();
            }
            repaint(); // Repaint the panel to reflect the color changes
        }

        private Color generateRandomColor() {
            int r = (int) (Math.random() * 256);
            int g = (int) (Math.random() * 256);
            int b = (int) (Math.random() * 256);
            return new Color(r, g, b);
        }
    }
}
