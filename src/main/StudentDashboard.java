import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class StudentDashboard extends JFrame {

    private Student currentStudent;

    private JComboBox<String> boroughBox;
    private JComboBox<String> neighbourhoodBox;

    private JTextField priceField;
    private JTextField startDateField;
    private JTextField endDateField;

    private JPanel resultsPanel;
    private JLabel welcomeLabel;

    public StudentDashboard(Student student) {

        this.currentStudent = student;

        setTitle("Berlin Sublet - Student Dashboard");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Keeps some temporary listings available for testing
        addSampleListings();

        JPanel mainPanel = new JPanel(
                new BorderLayout()
        );

        mainPanel.setBackground(Color.WHITE);

        mainPanel.add(
                createHeader(),
                BorderLayout.NORTH
        );

        mainPanel.add(
                createContent(),
                BorderLayout.CENTER
        );

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createHeader() {

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(
                new Color(240, 120, 30)
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 30, 20, 30
                )
        );

        JLabel title =
                new JLabel("BERLIN SUBLET");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        title.setForeground(Color.WHITE);

        JPanel rightPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        rightPanel.setBackground(
                new Color(240, 120, 30)
        );

        welcomeLabel =
                new JLabel(
                        "Welcome, "
                                + currentStudent.getName()
                );

        welcomeLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        welcomeLabel.setForeground(Color.WHITE);

        JButton applicationsButton =
                new JButton("My Applications");

        JButton profileButton =
                new JButton("Profile");

        JButton logoutButton =
                new JButton("Logout");

        applicationsButton.setBackground(Color.WHITE);
        profileButton.setBackground(Color.WHITE);
        logoutButton.setBackground(Color.WHITE);

        applicationsButton.setForeground(
                new Color(240, 120, 30)
        );

        profileButton.setForeground(
                new Color(240, 120, 30)
        );

        logoutButton.setForeground(
                new Color(240, 120, 30)
        );

        applicationsButton.setFocusPainted(false);
        profileButton.setFocusPainted(false);
        logoutButton.setFocusPainted(false);

        applicationsButton.addActionListener(
                e -> showMyApplications()
        );

        profileButton.addActionListener(
                e -> showProfile()
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        rightPanel.add(welcomeLabel);
        rightPanel.add(
                Box.createHorizontalStrut(10)
        );

        rightPanel.add(applicationsButton);
        rightPanel.add(profileButton);
        rightPanel.add(logoutButton);

        header.add(
                title,
                BorderLayout.WEST
        );

        header.add(
                rightPanel,
                BorderLayout.EAST
        );

        return header;
    }

    private JPanel createContent() {

        JPanel content =
                new JPanel(new BorderLayout());

        content.setBackground(Color.WHITE);

        content.add(
                createSearchPanel(),
                BorderLayout.NORTH
        );

        content.add(
                createResultsPanel(),
                BorderLayout.CENTER
        );

        return content;
    }

    private JPanel createSearchPanel() {

        JPanel panel = new JPanel();

        panel.setBackground(Color.WHITE);

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 60, 20, 60
                )
        );

        JLabel heading =
                new JLabel("Find a Sublet");

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        heading.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel boroughLabel =
                new JLabel("Borough");

        boroughBox =
                new JComboBox<>(
                        BerlinLocations.getBoroughs()
                );

        boroughBox.setMaximumSize(
                new Dimension(400, 35)
        );

        boroughBox.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel neighbourhoodLabel =
                new JLabel("Neighbourhood");

        neighbourhoodBox =
                new JComboBox<>();

        neighbourhoodBox.setMaximumSize(
                new Dimension(400, 35)
        );

        neighbourhoodBox.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel priceLabel =
                new JLabel("Maximum Price (€)");

        priceField =
                new JTextField();

        priceField.setMaximumSize(
                new Dimension(400, 35)
        );

        priceField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel startDateLabel =
                new JLabel(
                        "Available From (YYYY-MM-DD)"
                );

        startDateField =
                new JTextField();

        startDateField.setMaximumSize(
                new Dimension(400, 35)
        );

        startDateField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel endDateLabel =
                new JLabel(
                        "Available Until (YYYY-MM-DD)"
                );

        endDateField =
                new JTextField();

        endDateField.setMaximumSize(
                new Dimension(400, 35)
        );

        endDateField.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JButton searchButton =
                new JButton("Search Listings");

        searchButton.setBackground(
                new Color(240, 120, 30)
        );

        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);

        searchButton.setMaximumSize(
                new Dimension(400, 40)
        );

        searchButton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        boroughBox.addActionListener(
                e -> updateNeighbourhoods()
        );

        searchButton.addActionListener(
                e -> searchListings()
        );

        panel.add(heading);
        panel.add(Box.createVerticalStrut(20));

        panel.add(boroughLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(boroughBox);

        panel.add(Box.createVerticalStrut(10));

        panel.add(neighbourhoodLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(neighbourhoodBox);

        panel.add(Box.createVerticalStrut(10));

        panel.add(priceLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(priceField);

        panel.add(Box.createVerticalStrut(10));

        panel.add(startDateLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(startDateField);

        panel.add(Box.createVerticalStrut(10));

        panel.add(endDateLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(endDateField);

        panel.add(Box.createVerticalStrut(20));
        panel.add(searchButton);

        updateNeighbourhoods();

        return panel;
    }

    private JScrollPane createResultsPanel() {

        resultsPanel = new JPanel();

        resultsPanel.setLayout(
                new BoxLayout(
                        resultsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        resultsPanel.setBackground(Color.WHITE);

        resultsPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 60, 30, 60
                )
        );

        JLabel message =
                new JLabel(
                        "Search for a sublet to see results."
                );

        message.setForeground(Color.GRAY);

        resultsPanel.add(message);

        JScrollPane scrollPane =
                new JScrollPane(resultsPanel);

        scrollPane.setBorder(null);

        scrollPane
                .getVerticalScrollBar()
                .setUnitIncrement(16);

        return scrollPane;
    }

    private void updateNeighbourhoods() {

        String borough =
                (String) boroughBox
                        .getSelectedItem();

        neighbourhoodBox.removeAllItems();

        for (String neighbourhood :
                BerlinLocations.getNeighbourhoods(
                        borough
                )) {

            neighbourhoodBox.addItem(
                    neighbourhood
            );
        }
    }

    private void searchListings() {

        String borough =
                (String) boroughBox
                        .getSelectedItem();

        String neighbourhood =
                (String) neighbourhoodBox
                        .getSelectedItem();

        String priceText =
                priceField.getText().trim();

        String startText =
                startDateField.getText().trim();

        String endText =
                endDateField.getText().trim();

        if (!Validator.notEmpty(priceText)
                || !Validator.notEmpty(startText)
                || !Validator.notEmpty(endText)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all search fields."
            );

            return;
        }

        try {

            double maximumPrice =
                    Double.parseDouble(priceText);

            if (!Validator.validPrice(
                    maximumPrice
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Price must be greater than zero."
                );

                return;
            }

            LocalDate startDate =
                    LocalDate.parse(startText);

            LocalDate endDate =
                    LocalDate.parse(endText);

            if (!Validator.validDates(
                    startDate,
                    endDate
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "End date must be after start date."
                );

                return;
            }

            displayResults(
                    borough,
                    neighbourhood,
                    maximumPrice,
                    startDate,
                    endDate
            );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid price."
            );

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter dates as YYYY-MM-DD."
            );
        }
    }

    private void displayResults(
            String borough,
            String neighbourhood,
            double maximumPrice,
            LocalDate startDate,
            LocalDate endDate) {

        resultsPanel.removeAll();

        JLabel heading =
                new JLabel("Search Results");

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        heading.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        resultsPanel.add(heading);
        resultsPanel.add(
                Box.createVerticalStrut(15)
        );

        int matches = 0;

        for (Listing listing :
                DataStore.listings) {

            boolean correctLocation =
                    listing.getBorough()
                            .equals(borough)

                            && listing
                            .getNeighbourhood()
                            .equals(neighbourhood);

            boolean correctPrice =
                    listing.getPrice()
                            <= maximumPrice;

            boolean correctDates =
                    !startDate.isBefore(
                            listing.getStartDate()
                    )

                            && !endDate.isAfter(
                            listing.getEndDate()
                    );

            if (correctLocation
                    && correctPrice
                    && correctDates
                    && listing.isAvailable()) {

                resultsPanel.add(
                        createListingCard(
                                listing
                        )
                );

                resultsPanel.add(
                        Box.createVerticalStrut(15)
                );

                matches++;
            }
        }

        if (matches == 0) {

            JLabel noResults =
                    new JLabel(
                            "No listings found for your search."
                    );

            noResults.setForeground(Color.GRAY);

            resultsPanel.add(noResults);
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    private JPanel createListingCard(
            Listing listing) {

        JPanel card =
                new JPanel(new BorderLayout());

        card.setBackground(
                new Color(248, 248, 248)
        );

        card.setMaximumSize(
                new Dimension(800, 170)
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        220,
                                        220
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                15, 20, 15, 20
                        )
                )
        );

        // Picture
        if (listing.getImagePath() != null) {

            File imageFile =
                    new File(
                            listing.getImagePath()
                    );

            if (imageFile.exists()) {

                ImageIcon original =
                        new ImageIcon(
                                listing.getImagePath()
                        );

                Image scaled =
                        original.getImage()
                                .getScaledInstance(
                                        170,
                                        110,
                                        Image.SCALE_SMOOTH
                                );

                JLabel imageLabel =
                        new JLabel(
                                new ImageIcon(scaled)
                        );

                imageLabel.setBorder(
                        BorderFactory.createEmptyBorder(
                                0, 0, 0, 15
                        )
                );

                card.add(
                        imageLabel,
                        BorderLayout.WEST
                );
            }
        }

        JPanel information =
                new JPanel();

        information.setBackground(
                new Color(248, 248, 248)
        );

        information.setLayout(
                new BoxLayout(
                        information,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        listing.getTitle()
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        JLabel location =
                new JLabel(
                        listing.getNeighbourhood()
                                + ", "
                                + listing.getBorough()
                );

        JLabel price =
                new JLabel(
                        "€"
                                + listing.getPrice()
                                + " per month"
                );

        JLabel dates =
                new JLabel(
                        listing.getStartDate()
                                + " to "
                                + listing.getEndDate()
                );

        information.add(title);
        information.add(
                Box.createVerticalStrut(5)
        );
        information.add(location);
        information.add(price);
        information.add(dates);

        JButton detailsButton =
                new JButton("View Details");

        detailsButton.setBackground(
                new Color(240, 120, 30)
        );

        detailsButton.setForeground(Color.WHITE);
        detailsButton.setFocusPainted(false);

        detailsButton.addActionListener(
                e -> showListingDetails(
                        listing
                )
        );

        card.add(
                information,
                BorderLayout.CENTER
        );

        card.add(
                detailsButton,
                BorderLayout.EAST
        );

        return card;
    }

    private void showListingDetails(
            Listing listing) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        String details =
                "<html>"
                        + "<b>"
                        + listing.getTitle()
                        + "</b><br><br>"

                        + "Location: "
                        + listing.getNeighbourhood()
                        + ", "
                        + listing.getBorough()
                        + "<br>"

                        + "Price: €"
                        + listing.getPrice()
                        + " per month<br>"

                        + "Available: "
                        + listing.getStartDate()
                        + " to "
                        + listing.getEndDate()
                        + "<br>"

                        + "Room Type: "
                        + listing.getRoomType()
                        + "<br><br>"

                        + "Description:<br>"
                        + listing.getDescription()

                        + "</html>";

        JLabel textLabel =
                new JLabel(details);

        panel.add(
                textLabel,
                BorderLayout.CENTER
        );

        if (listing.getImagePath() != null) {

            File imageFile =
                    new File(
                            listing.getImagePath()
                    );

            if (imageFile.exists()) {

                ImageIcon original =
                        new ImageIcon(
                                listing.getImagePath()
                        );

                Image scaled =
                        original.getImage()
                                .getScaledInstance(
                                        250,
                                        170,
                                        Image.SCALE_SMOOTH
                                );

                JLabel imageLabel =
                        new JLabel(
                                new ImageIcon(scaled)
                        );

                panel.add(
                        imageLabel,
                        BorderLayout.NORTH
                );
            }
        }

        Object[] options = {
                "Apply",
                "Report",
                "Close"
        };

        int choice =
                JOptionPane.showOptionDialog(
                        this,
                        panel,
                        "Listing Details",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (choice == 0) {
            applyForListing(listing);
        }

        if (choice == 1) {
            reportListing(listing);
        }
    }

    private void applyForListing(
            Listing listing) {

        for (Application application :
                DataStore.applications) {

            if (application.getStudentId()
                    == currentStudent.getUserId()

                    && application.getListingId()
                    == listing.getListingId()) {

                JOptionPane.showMessageDialog(
                        this,
                        "You have already applied for this listing."
                );

                return;
            }
        }

        Application application =
                new Application(
                        DataStore
                                .getNextApplicationId(),
                        currentStudent.getUserId(),
                        listing.getListingId()
                );

        DataStore.applications.add(
                application
        );

        JOptionPane.showMessageDialog(
                this,
                "Application submitted successfully.\n"
                        + "Status: Pending"
        );
    }

    private void reportListing(
            Listing listing) {

        String reason =
                JOptionPane.showInputDialog(
                        this,
                        "Why are you reporting this listing?",
                        "Report Listing",
                        JOptionPane.WARNING_MESSAGE
                );

        if (reason == null) {
            return;
        }

        if (!Validator.notEmpty(reason)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a reason for the report."
            );

            return;
        }

        for (Report report :
                DataStore.reports) {

            if (report.getStudentId()
                    == currentStudent.getUserId()

                    && report.getListingId()
                    == listing.getListingId()) {

                JOptionPane.showMessageDialog(
                        this,
                        "You have already reported this listing."
                );

                return;
            }
        }

        Report report =
                new Report(
                        DataStore
                                .getNextReportId(),
                        currentStudent.getUserId(),
                        listing.getListingId(),
                        reason
                );

        DataStore.reports.add(report);

        JOptionPane.showMessageDialog(
                this,
                "Report submitted successfully.\n"
                        + "Status: Pending"
        );
    }

    private void showMyApplications() {

        StringBuilder text =
                new StringBuilder();

        int count = 0;

        for (Application application :
                DataStore.applications) {

            if (application.getStudentId()
                    != currentStudent.getUserId()) {

                continue;
            }

            Listing listing =
                    findListing(
                            application.getListingId()
                    );

            if (listing != null) {

                text.append(
                        listing.getTitle()
                );

                text.append("\n");

                text.append(
                        listing.getNeighbourhood()
                );

                text.append(", ");

                text.append(
                        listing.getBorough()
                );

                text.append("\nStatus: ");

                text.append(
                        application.getStatus()
                );

                text.append(
                        "\n--------------------\n"
                );

                count++;
            }
        }

        if (count == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "You have not submitted any applications yet.",
                    "My Applications",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                text.toString(),
                "My Applications",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private Listing findListing(
            int listingId) {

        for (Listing listing :
                DataStore.listings) {

            if (listing.getListingId()
                    == listingId) {

                return listing;
            }
        }

        return null;
    }

    // Adds temporary examples only if no listings exist
    private void addSampleListings() {

        if (!DataStore.listings.isEmpty()) {
            return;
        }

        DataStore.listings.add(
                new Listing(
                        101,
                        20,
                        "Room near TU Berlin",
                        "Charlottenburg-Wilmersdorf",
                        "Charlottenburg",
                        650,
                        LocalDate.of(
                                2026,
                                10,
                                1
                        ),
                        LocalDate.of(
                                2026,
                                12,
                                31
                        ),
                        "Private Room",
                        "Furnished room close to university."
                )
        );

        DataStore.listings.add(
                new Listing(
                        102,
                        30,
                        "Student Room in Neukölln",
                        "Neukölln",
                        "Neukölln",
                        550,
                        LocalDate.of(
                                2026,
                                9,
                                1
                        ),
                        LocalDate.of(
                                2027,
                                1,
                                31
                        ),
                        "Private Room",
                        "Bright room with good transport connections."
                )
        );

        DataStore.listings.add(
                new Listing(
                        103,
                        40,
                        "Studio in Mitte",
                        "Mitte",
                        "Mitte",
                        850,
                        LocalDate.of(
                                2026,
                                10,
                                1
                        ),
                        LocalDate.of(
                                2027,
                                2,
                                28
                        ),
                        "Studio",
                        "Small furnished studio in central Berlin."
                )
        );
    }

    private void showProfile() {

        String profile =
                "Name: "
                        + currentStudent.getName()

                        + "\nEmail: "
                        + currentStudent.getEmail()

                        + "\nRole: "
                        + currentStudent.getRole()

                        + "\nAccount Created: "
                        + currentStudent.getCreatedAt();

        Object[] options = {
                "Edit Profile",
                "Close"
        };

        int choice =
                JOptionPane.showOptionDialog(
                        this,
                        profile,
                        "My Profile",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (choice == 0) {
            editProfile();
        }
    }

    private void editProfile() {

        JTextField nameField =
                new JTextField(
                        currentStudent.getName()
                );

        JTextField emailField =
                new JTextField(
                        currentStudent.getEmail()
                );

        JPasswordField passwordField =
                new JPasswordField();

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                0,
                                1,
                                5,
                                5
                        )
                );

        panel.add(
                new JLabel("Name")
        );

        panel.add(nameField);

        panel.add(
                new JLabel("Email")
        );

        panel.add(emailField);

        panel.add(
                new JLabel(
                        "New Password (leave blank to keep current)"
                )
        );

        panel.add(passwordField);

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "Edit Profile",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (choice !=
                JOptionPane.OK_OPTION) {

            return;
        }

        String name =
                nameField.getText().trim();

        String email =
                emailField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (!Validator.notEmpty(name)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Name cannot be empty."
            );

            return;
        }

        if (!Validator.validEmail(email)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email."
            );

            return;
        }

        if (!password.isEmpty()
                && !Validator.validPassword(
                        password
                )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password must contain at least 6 characters."
            );

            return;
        }

        currentStudent.setName(name);
        currentStudent.setEmail(email);

        if (!password.isEmpty()) {

            currentStudent.setPassword(
                    password
            );
        }

        welcomeLabel.setText(
                "Welcome, "
                        + currentStudent.getName()
        );

        JOptionPane.showMessageDialog(
                this,
                "Profile updated successfully."
        );
    }

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            dispose();
        }
    }
}