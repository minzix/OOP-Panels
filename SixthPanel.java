import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SixthPanel extends JPanel {
    private JLabel resultLabel;
    private File selectedFile;

    public SixthPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel titleLabel = new JLabel("<html><br>&lt;과제 단어 수 계산기&gt;<br><br></html>");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton uploadButton = new JButton("파일 업로드");
        uploadButton.addActionListener(new UploadButtonListener());
        buttonPanel.add(uploadButton);

        JButton calculateButton = new JButton("계산하기");
        calculateButton.addActionListener(new CalculateButtonListener());
        buttonPanel.add(calculateButton);

        add(buttonPanel, BorderLayout.CENTER);

        JPanel resultPanel = new JPanel(new GridBagLayout());
        resultPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Add top padding to the result panel
        add(resultPanel, BorderLayout.SOUTH);

        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Dialog", Font.BOLD, 36));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        resultPanel.add(resultLabel, gbc);
    }

    private class UploadButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(SixthPanel.this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
            }
        }
    }

    private class CalculateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (selectedFile != null) {
                try {
                    FileReader fileReader = new FileReader(selectedFile);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        content.append(line);
                    }
                    bufferedReader.close();

                    int wordCount = countWords(content.toString());
                    resultLabel.setFont(new Font("Dialog", Font.BOLD, 12)); 
                    resultLabel.setText("<html>파일명: " + selectedFile.getName() + "<br>글자 수: " + content.length() + "<br>단어 수: " + wordCount + "<br><br><br><br><br><br><br><br><br><br><br></html>");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                resultLabel.setText("파일을 먼저 업로드해주세요.");
            }
        }

        private int countWords(String text) {
            String[] words = text.split("\\s+");
            return words.length;
        }
    }
}
