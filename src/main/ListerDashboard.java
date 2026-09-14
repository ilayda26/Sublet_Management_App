import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListerDashboard extends JFrame {

    private Lister currentLister;
    private JLabel welcomeLabel;

    // Temporary storage until database integration
    private List<Listing> listings = new ArrayList<>();
    private int nextListingId = 1;

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

        createListingButton.addActionListener(e ->
                showCreateListingForm()
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

    private void showCreateListingForm() {

        JTextField titleField = new JTextField(20);

        JComboBox<String> boroughBox =
                new JComboBox<>(BerlinLocations.getBoroughs());

        JComboBox<String> neighbourhoodBox =
                new JComboBox<>();

        JTextField priceField = new JTextField(20);
        JTextField startDateField = new JTextField(20);
        JTextField endDateField = new JTextField(20);

        String[] roomTypes = {
                "Private Room",
                "Shared Room",
                "Studio",
                "Entire Apartment"
        };

        JComboBox<String> roomTypeBox =
                new JComboBox<>(roomTypes);

        JTextArea descriptionArea =
                new JTextArea(4, 20);

        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        // Updates neighbourhoods when borough changes
        boroughBox.addActionListener(e -> {

            String borough =
                    (String) boroughBox.getSelectedItem();

            neighbourhoodBox.removeAllItems();

            String[] neighbourhoods =
                    BerlinLocations.getNeighbourhoods(borough);

            for (String neighbourhood : neighbourhoods) {
                neighbourhoodBox.addItem(neighbourhood);
            }
        });

        // Load neighbourhoods for the first borough
        String firstBorough =
                (String) boroughBox.getSelectedItem();

        String[] firstNeighbourhoods =
                BerlinLocations.getNeighbourhoods(firstBorough);

        for (String neighbourhood : firstNeighbourhoods) {
            neighbourhoodBox.addItem(neighbourhood);
        }

        JPanel formPanel =
                new JPanel(new GridLayout(0, 2, 10, 10));

        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);

        formPanel.add(new JLabel("Borough:"));
        formPanel.add(boroughBox);

        formPanel.add(new JLabel("Neighbourhood:"));
        formPanel.add(neighbourhoodBox);

        formPanel.add(new JLabel("Monthly Price (€):"));
        formPanel.add(priceField);

        formPanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        formPanel.add(startDateField);

        formPanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        formPanel.add(endDateField);

        formPanel.add(new JLabel("Room Type:"));
        formPanel.add(roomTypeBox);

        formPanel.add(new JLabel("Description:"));
        formPanel.add(new JScrollPane(descriptionArea));

        int result = JOptionPane.showConfirmDialog(
                this,
                formPanel,
                "Create Listing",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {

            String title =
                    titleField.getText().trim();

            String borough =
                    (String) boroughBox.getSelectedItem();

            String neighbourhood =
                    (String) neighbourhoodBox.getSelectedItem();

            String roomType =
                    (String) roomTypeBox.getSelectedItem();

            String description =
                    descriptionArea.getText().trim();

            if (!Validator.notEmpty(title)
                    || !Validator.notEmpty(description)
                    || !Validator.notEmpty(priceField.getText())
                    || !Validator.notEmpty(startDateField.getText())
                    || !Validator.notEmpty(endDateField.getText())) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please complete all required fields.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            double price =
                    Double.parseDouble(
                            priceField.getText().trim()
                    );

            if (!Validator.validPrice(price)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Price must be greater than zero.",
                        "Invalid Price",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            LocalDate startDate =
                    LocalDate.parse(
                            startDateField.getText().trim()
                    );

            LocalDate endDate =
                    LocalDate.parse(
                            endDateField.getText().trim()
                    );

            if (!Validator.validDates(startDate, endDate)) {

                JOptionPane.showMessageDialog(
                        this,
                        "End date must be after the start date.",
                        "Invalid Dates",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            if (!BerlinLocations.validLocation(
                    borough,
                    neighbourhood)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a valid Berlin location.",
                        "Invalid Location",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            Listing listing = new Listing(
                    nextListingId++,
                    currentLister.getUserId(),
                    title,
                    borough,
                    neighbourhood,
                    price,
                    startDate,
                    endDate,
                    roomType,
                    description
            );

            listings.add(listing);

            JOptionPane.showMessageDialog(
                    this,
                    "Listing created successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid price.",
                    "Invalid Price",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter dates in YYYY-MM-DD format.",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );
        }
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
           
        }
    }
}