import javax.swing.*;
import java.awt.*;

public class ListerDashboard extends JFrame {

    private Lister currentLister;
    private JLabel welcomeLabel;

    public ListerDashboard(Lister lister) {

        this.currentLister = lister;

        setTitle("Berlin Sublet - Lister Dashboard");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(255, 140, 0));
        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        );

        JLabel titleLabel = new JLabel("BERLIN SUBLET");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        welcomeLabel = new JLabel(
                "Welcome, " + currentLister.getName()
        );
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(welcomeLabel, BorderLayout.EAST);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Dashboard content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(
                new BoxLayout(contentPanel, BoxLayout.Y_AXIS)
        );
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(60, 250, 60, 250)
        );

        JLabel heading = new JLabel("Lister Dashboard");
        heading.setFont(new Font("Arial", Font.BOLD, 26));
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton createListingButton =
                createButton("Create Listing");

        JButton myListingsButton =
                createButton("My Listings");

        JButton applicationsButton =
                createButton("Manage Applications");

        JButton profileButton =
                createButton("Profile");

        JButton logoutButton =
                createButton("Logout");

        contentPanel.add(heading);
        contentPanel.add(Box.createVerticalStrut(35));

        contentPanel.add(createListingButton);
        contentPanel.add(Box.createVerticalStrut(15));

        contentPanel.add(myListingsButton);
        contentPanel.add(Box.createVerticalStrut(15));

        contentPanel.add(applicationsButton);
        contentPanel.add(Box.createVerticalStrut(15));

        contentPanel.add(profileButton);
        contentPanel.add(Box.createVerticalStrut(15));

        contentPanel.add(logoutButton);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Temporary button tests
        createListingButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Create Listing will be added next."
                )
        );

        myListingsButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "My Listings will be added next."
                )
        );

        applicationsButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this,
                        "Manage Applications will be added later."
                )
        );

        profileButton.addActionListener(e ->
                showProfile()
        );

        logoutButton.addActionListener(e ->
                logout()
        );

        add(mainPanel);
        setVisible(true);
    }

    private JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setMaximumSize(
                new Dimension(500, 50)
        );

        button.setPreferredSize(
                new Dimension(500, 50)
        );

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(255, 140, 0));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);

        return button;
    }

    private void showProfile() {

        JOptionPane.showMessageDialog(
                this,
                "Name: " + currentLister.getName()
                        + "\nEmail: " + currentLister.getEmail()
                        + "\nRole: " + currentLister.getRole(),
                "Profile",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void logout() {

        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            new LoginFrame();
        }
    }
}