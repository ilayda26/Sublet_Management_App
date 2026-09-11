import javax.swing.*;
import java.awt.*;

public class StudentDashboard extends JFrame {

    private JComboBox<String> boroughBox;
    private JComboBox<String> neighbourhoodBox;
    private JTextField priceField;

    public StudentDashboard(Student student) {
        setTitle("Berlin Sublet - Student");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        mainPanel.add(createHeader(student), BorderLayout.NORTH);
        mainPanel.add(createSearchPanel(), BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createHeader(Student student) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(240, 120, 30));
        header.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel title = new JLabel("BERLIN SUBLET");
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(Color.WHITE);

        JLabel welcome = new JLabel("Welcome, " + student.getName());
        welcome.setFont(new Font("Arial", Font.BOLD, 16));
        welcome.setForeground(Color.WHITE);

        header.add(title, BorderLayout.WEST);
        header.add(welcome, BorderLayout.EAST);

        return header;
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 80, 50, 80));

        JLabel heading = new JLabel("Find a Sublet");
        heading.setFont(new Font("Arial", Font.BOLD, 28));
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel boroughLabel = new JLabel("Borough");
        boroughLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        boroughBox = new JComboBox<>(BerlinLocations.getBoroughs());
        boroughBox.setMaximumSize(new Dimension(400, 35));
        boroughBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel neighbourhoodLabel = new JLabel("Neighbourhood");
        neighbourhoodLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        neighbourhoodBox = new JComboBox<>();
        neighbourhoodBox.setMaximumSize(new Dimension(400, 35));
        neighbourhoodBox.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel priceLabel = new JLabel("Maximum Price (€)");
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        priceField = new JTextField();
        priceField.setMaximumSize(new Dimension(400, 35));
        priceField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton searchButton = new JButton("Search Listings");
        searchButton.setBackground(new Color(240, 120, 30));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setMaximumSize(new Dimension(400, 40));
        searchButton.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Updates neighbourhoods when the borough changes
        boroughBox.addActionListener(e -> updateNeighbourhoods());

        searchButton.addActionListener(e -> testSearch());

        panel.add(heading);
        panel.add(Box.createVerticalStrut(30));

        panel.add(boroughLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(boroughBox);

        panel.add(Box.createVerticalStrut(20));

        panel.add(neighbourhoodLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(neighbourhoodBox);

        panel.add(Box.createVerticalStrut(20));

        panel.add(priceLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(priceField);

        panel.add(Box.createVerticalStrut(30));
        panel.add(searchButton);

        updateNeighbourhoods();

        return panel;
    }

    private void updateNeighbourhoods() {
        String borough = (String) boroughBox.getSelectedItem();

        neighbourhoodBox.removeAllItems();

        for (String area : BerlinLocations.getNeighbourhoods(borough)) {
            neighbourhoodBox.addItem(area);
        }
    }

    private void testSearch() {
    String borough = (String) boroughBox.getSelectedItem();
    String neighbourhood = (String) neighbourhoodBox.getSelectedItem();
    String priceText = priceField.getText();

    if (!Validator.notEmpty(priceText)) {
        JOptionPane.showMessageDialog(
                this,
                "Please enter a maximum price."
        );
        return;
    }

    try {
        double price = Double.parseDouble(priceText);

        if (!Validator.validPrice(price)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Price must be greater than zero."
            );
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Searching in " + neighbourhood +
                        ", " + borough +
                        "\nMaximum price: €" + price
        );

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(
                this,
                "Please enter a valid price."
        );
    }
}
}