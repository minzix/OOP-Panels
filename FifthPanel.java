import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FifthPanel extends JPanel {
    private boolean happy = false;
    private boolean soso = true;
    private boolean mad = false;

    public FifthPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border

        JLabel titleLabel = new JLabel("<html><br>&lt;나만의 차트: 나의 현재 기분&gt;</html>");
        titleLabel.setHorizontalAlignment(JLabel.CENTER); 
        add(titleLabel, BorderLayout.NORTH); 
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(new FaceChartPanel(), BorderLayout.CENTER);
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton happyButton = new JButton("Happy");
        happyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                happy = true;
                soso = false;
                mad = false;
                repaint();
            }
        });

        JButton sosoButton = new JButton("SoSo");
        sosoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                happy = false;
                soso = true;
                mad = false;
                repaint();
            }
        });

        JButton madButton = new JButton("Mad");
        madButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                happy = false;
                soso = false;
                mad = true;
                repaint();
            }
        });

        buttonPanel.add(happyButton);
        buttonPanel.add(sosoButton);
        buttonPanel.add(madButton);

        return buttonPanel;
    }

    private class FaceChartPanel extends JPanel {
        private double mouthPosition = 0.5; // Store the position of the mouth as a ratio

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the face
            g.setColor(new Color(251, 206, 177)); 
            int radius = Math.min(getWidth(), getHeight()) / 2;
            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;
            g.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

            // Draw the eyes
            g.setColor(Color.BLACK);
            int eyeWidth = radius / 4;
            int eyeHeight = radius / 2;
            int eyeSpacing = radius / 4;
            int eyeY = centerY - eyeHeight / 2;
            int leftEyeX = centerX - eyeSpacing - eyeWidth;
            int rightEyeX = centerX + eyeSpacing;
            g.fillOval(leftEyeX, eyeY, eyeWidth, eyeHeight);
            g.fillOval(rightEyeX, eyeY, eyeWidth, eyeHeight);

            // Draw the eyebrows
            int eyebrowLength = radius / 3;
            int eyebrowY = eyeY - eyebrowLength;
            g.drawLine(leftEyeX, eyebrowY, leftEyeX + eyeWidth, eyebrowY);
            g.drawLine(rightEyeX, eyebrowY, rightEyeX + eyeWidth, eyebrowY);

            // Draw the mouth
            int mouthY = centerY + radius / 2;
            int mouthWidth = radius / 2;
            int mouthHeight = radius / 4;

            if (happy || soso || mad) {
                g.setColor(new Color(251, 206, 177));
                int mouthX = centerX - mouthWidth / 2;
                int mouthPositionX = (int) (mouthX + mouthPosition * mouthWidth);
                g.drawLine(mouthPositionX, mouthY, mouthPositionX, mouthY);
            } else {
                g.setColor(Color.BLACK);
                g.drawLine(centerX - mouthWidth / 2, mouthY, centerX + mouthWidth / 2, mouthY);
            }

            // 추가 요구사항에 따른 실선 그리기
            if (happy) {
                g.setColor(Color.BLACK);
                int arcWidth = (int) (mouthWidth + mouthPosition * mouthWidth) + 15;
                g.drawArc(centerX - arcWidth / 2, mouthY - mouthHeight / 2, arcWidth, 50, 0, -180);
            } else if (soso) {
                g.setColor(Color.BLACK);
                g.drawLine(centerX - mouthWidth / 2, mouthY, centerX + mouthWidth / 2, mouthY);
            } else if (mad) {
                g.setColor(Color.BLACK);
                int arcWidth = (int) (mouthWidth + mouthPosition * mouthWidth) + 15;
                g.drawArc(centerX - arcWidth / 2, mouthY - mouthHeight / 2, arcWidth, 50, 0, 180);
            }
        }
    }
}