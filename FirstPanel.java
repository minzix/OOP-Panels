import java.awt.*;
import javax.swing.*; //for creating and managing JFrame, JPanel, JButton, JLabel, and JFileChooser.
import java.awt.event.*; //for handling button click events
import javax.swing.filechooser.FileNameExtensionFilter; // to filter and select image files (extensions: jpg, jpeg, png, gif) when choosing an image file
import java.awt.geom.Ellipse2D; //to define a circular shape for clipping and displaying the chosen image in a circular panel

class FirstPanel extends JPanel {
    private CircularPanel imagePanel;
    private JLabel descriptionLabel;
    private JButton chooseImageButton;
    private JButton showInfoButton;
    private JLabel infoLabel;

    public FirstPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border

        // Initialize the image panel
        imagePanel = new CircularPanel();

        // Initialize the description label
        descriptionLabel = new JLabel("<html>안녕하세요, 제 한 학기 학교생활을 보여주는 패널들을 소개합니다!"
                + "<br>차트를 이용해서 제가 수강하는 강의들과 각각 제출해야 하는 과제의 수를 나타내고, "
                + "<br>나만의 차트에서는 제 하루 일과를 간단하게 나타낸 일정표를 만들었으며 "
                + "<br>과제파일을 업로드해서 과제 조건에 맞는 글자수인지 검사할 수 있는 페이지를 제작했습니다."
                + "<br><br><br></html>");


        // Initialize the choose image button
        chooseImageButton = new JButton("이미지 선택");
        chooseImageButton.setPreferredSize(new Dimension(100, 30)); // 버튼 크기 조정
        chooseImageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseImage();
            }
        });

        // Initialize the show info button
        showInfoButton = new JButton("정보 보기");
        showInfoButton.setPreferredSize(new Dimension(100, 30)); // 버튼 크기 조정
        showInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showInfoDialog();
            }
        });

        // Initialize the info label
        infoLabel = new JLabel();
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setVisible(false);

        // Initialize the button panel to hold the buttons and description label
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(chooseImageButton);
        buttonPanel.add(showInfoButton);

        // Add components to the panel
        add(imagePanel, BorderLayout.CENTER); // Change to image panel
        add(descriptionLabel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.NORTH); // Add the button panel to the top
        add(infoLabel, BorderLayout.EAST); // Add the info label next to the circular image area
        
    }

    // Method for choosing an image
    private void chooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("이미지 파일", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String imagePath = fileChooser.getSelectedFile().getPath();
            ImageIcon imageIcon = new ImageIcon(imagePath);
            imagePanel.setImage(imageIcon.getImage());
        }
    }

    // Method for showing the info dialog
    private void showInfoDialog() {
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JTextField nameField = new JTextField();
        JTextField studentIdField = new JTextField();
        JTextField majorField = new JTextField();

        panel.add(new JLabel("이름:"));
        panel.add(nameField);
        panel.add(new JLabel("학번:"));
        panel.add(studentIdField);
        panel.add(new JLabel("학과:"));
        panel.add(majorField);

        int result = JOptionPane.showConfirmDialog(this, panel, "정보 입력",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String studentId = studentIdField.getText();
            String major = majorField.getText();

            String info = "이름: " + name + "<br>학번: " + studentId + "<br>학과: " + major;
            infoLabel.setText("<html>" + info + "</html>");
            infoLabel.setVisible(true);
        }
    }
}

//Custom panel class for drawing circular panels
class CircularPanel extends JPanel {
    private Image image;

    public void setImage(Image image) {
        this.image = image;
        repaint(); // Call repaint to redraw the panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            Ellipse2D.Double circle = new Ellipse2D.Double(50, 30, 200, 200);
            
            g2d.setClip(circle);
            g2d.drawImage(image,50,30, 200, 200, this);
            g2d.dispose();
        }
    }
} 
