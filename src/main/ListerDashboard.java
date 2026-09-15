import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;

public class ListerDashboard extends JFrame {

    private Lister currentLister;

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

        JLabel welcomeLabel = new JLabel(
                "Welcome, " + currentLister.getName()
        );

        welcomeLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

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
                BorderFactory.createEmptyBorder(
                        45, 100, 45, 100
                )
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
        contentPanel.add(Box.createVerticalStrut(30));

        contentPanel.add(createListingButton);
        contentPanel.add(Box.createVerticalStrut(12));

        contentPanel.add(myListingsButton);
        contentPanel.add(Box.createVerticalStrut(12));

        contentPanel.add(applicationsButton);
        contentPanel.add(Box.createVerticalStrut(12));

        contentPanel.add(profileButton);
        contentPanel.add(Box.createVerticalStrut(12));

        contentPanel.add(logoutButton);

        mainPanel.add(contentPanel, BorderLayout.CENTER);

        createListingButton.addActionListener(e ->
                showCreateListingForm()
        );

        myListingsButton.addActionListener(e ->
                showMyListings()
        );

        applicationsButton.addActionListener(e ->
                showApplications()
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
                new Dimension(360, 42)
        );

        button.setPreferredSize(
                new Dimension(360, 42)
        );

        button.setMinimumSize(
                new Dimension(360, 42)
        );

        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.setBackground(
                new Color(255, 140, 0)
        );

        button.setForeground(Color.WHITE);

        button.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        button.setFocusPainted(false);

        return button;
    }

    private void showCreateListingForm() {

        JDialog dialog = new JDialog(
                this,
                "Create Listing",
                true
        );

        dialog.setSize(620, 610);
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 35, 20, 35
                )
        );

        JLabel heading = new JLabel(
                "Create New Listing",
                SwingConstants.CENTER
        );

        heading.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        heading.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 0, 15, 0
                )
        );

        mainPanel.add(heading, BorderLayout.NORTH);

        JPanel formPanel =
                new JPanel(new GridBagLayout());

        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets = new Insets(7, 8, 7, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JTextField titleField =
                new JTextField(20);

        JTextField priceField =
                new JTextField(20);

        JTextField startDateField =
                new JTextField(20);

        JTextField endDateField =
                new JTextField(20);

        JComboBox<String> boroughBox =
                new JComboBox<>(
                        BerlinLocations.getBoroughs()
                );

        JComboBox<String> neighbourhoodBox =
                new JComboBox<>();

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

        JScrollPane descriptionScroll =
                new JScrollPane(descriptionArea);

        descriptionScroll.setPreferredSize(
                new Dimension(280, 70)
        );

        Dimension fieldSize =
                new Dimension(280, 30);

        titleField.setPreferredSize(fieldSize);
        priceField.setPreferredSize(fieldSize);
        startDateField.setPreferredSize(fieldSize);
        endDateField.setPreferredSize(fieldSize);

        boroughBox.setPreferredSize(fieldSize);
        neighbourhoodBox.setPreferredSize(fieldSize);
        roomTypeBox.setPreferredSize(fieldSize);

        JButton choosePictureButton =
                new JButton("Choose Picture");

        JLabel pictureLabel =
                new JLabel("No picture selected");

        final String[] selectedImagePath = {null};

        choosePictureButton.addActionListener(e -> {

            JFileChooser chooser =
                    new JFileChooser();

            int result =
                    chooser.showOpenDialog(dialog);

            if (result ==
                    JFileChooser.APPROVE_OPTION) {

                File selectedFile =
                        chooser.getSelectedFile();

                String fileName =
                        selectedFile
                                .getName()
                                .toLowerCase();

                if (!fileName.endsWith(".jpg")
                        && !fileName.endsWith(".jpeg")
                        && !fileName.endsWith(".png")) {

                    JOptionPane.showMessageDialog(
                            dialog,
                            "Please choose a JPG, JPEG or PNG image.",
                            "Invalid Image",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                selectedImagePath[0] =
                        selectedFile.getAbsolutePath();

                pictureLabel.setText(
                        selectedFile.getName()
                );
            }
        });

        String firstBorough =
                (String) boroughBox.getSelectedItem();

        for (String neighbourhood :
                BerlinLocations.getNeighbourhoods(
                        firstBorough
                )) {

            neighbourhoodBox.addItem(
                    neighbourhood
            );
        }

        boroughBox.addActionListener(e -> {

            String borough =
                    (String) boroughBox.getSelectedItem();

            neighbourhoodBox.removeAllItems();

            for (String neighbourhood :
                    BerlinLocations.getNeighbourhoods(
                            borough
                    )) {

                neighbourhoodBox.addItem(
                        neighbourhood
                );
            }
        });

        int row = 0;

        addFormRow(
                formPanel,
                gbc,
                row++,
                "Title:",
                titleField
        );

        addFormRow(
                formPanel,
                gbc,
                row++,
                "Borough:",
                boroughBox
        );

        addFormRow(
                formPanel,
                gbc,
                row++,
                "Neighbourhood:",
                neighbourhoodBox
        );

        addFormRow(
                formPanel,
                gbc,
                row++,
                "Monthly Price (€):",
                priceField
        );

        addFormRow(
                formPanel,
                gbc,
                row++,
                "Start Date (YYYY-MM-DD):",
                startDateField
        );

        addFormRow(
                formPanel,
                gbc,
                row++,
                "End Date (YYYY-MM-DD):",
                endDateField
        );

        addFormRow(
                formPanel,
                gbc,
                row++,
                "Room Type:",
                roomTypeBox
        );

        // Description
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;

        formPanel.add(
                new JLabel("Description:"),
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        formPanel.add(
                descriptionScroll,
                gbc
        );

        row++;

        // Picture
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        formPanel.add(
                new JLabel("Picture:"),
                gbc
        );

        JPanel picturePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                8,
                                0
                        )
                );

        picturePanel.setBackground(Color.WHITE);
        picturePanel.add(choosePictureButton);
        picturePanel.add(pictureLabel);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        formPanel.add(
                picturePanel,
                gbc
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        buttonPanel.setBackground(Color.WHITE);

        JButton createButton =
                new JButton("Create");

        JButton cancelButton =
                new JButton("Cancel");

        createButton.setPreferredSize(
                new Dimension(110, 35)
        );

        cancelButton.setPreferredSize(
                new Dimension(110, 35)
        );

        createButton.setBackground(
                new Color(255, 140, 0)
        );

        createButton.setForeground(Color.WHITE);
        createButton.setFocusPainted(false);
        cancelButton.setFocusPainted(false);

        buttonPanel.add(createButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        createButton.addActionListener(e -> {

            try {

                String title =
                        titleField.getText().trim();

                String borough =
                        (String)
                                boroughBox.getSelectedItem();

                String neighbourhood =
                        (String)
                                neighbourhoodBox.getSelectedItem();

                String roomType =
                        (String)
                                roomTypeBox.getSelectedItem();

                String description =
                        descriptionArea.getText().trim();

                if (!Validator.notEmpty(title)
                        || !Validator.notEmpty(
                                priceField.getText()
                        )
                        || !Validator.notEmpty(
                                startDateField.getText()
                        )
                        || !Validator.notEmpty(
                                endDateField.getText()
                        )
                        || !Validator.notEmpty(
                                description
                        )) {

                    JOptionPane.showMessageDialog(
                            dialog,
                            "Please complete all required fields.",
                            "Invalid Input",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                double price =
                        Double.parseDouble(
                                priceField
                                        .getText()
                                        .trim()
                        );

                if (!Validator.validPrice(price)) {

                    JOptionPane.showMessageDialog(
                            dialog,
                            "Price must be greater than zero.",
                            "Invalid Price",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                LocalDate startDate =
                        LocalDate.parse(
                                startDateField
                                        .getText()
                                        .trim()
                        );

                LocalDate endDate =
                        LocalDate.parse(
                                endDateField
                                        .getText()
                                        .trim()
                        );

                if (!Validator.validDates(
                        startDate,
                        endDate
                )) {

                    JOptionPane.showMessageDialog(
                            dialog,
                            "End date must be after the start date.",
                            "Invalid Dates",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                if (!BerlinLocations.validLocation(
                        borough,
                        neighbourhood
                )) {

                    JOptionPane.showMessageDialog(
                            dialog,
                            "Please select a valid Berlin location.",
                            "Invalid Location",
                            JOptionPane.ERROR_MESSAGE
                    );

                    return;
                }

                Listing listing =
                        new Listing(
                                DataStore.getNextListingId(),
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

                if (selectedImagePath[0] != null) {

                    listing.setImagePath(
                            selectedImagePath[0]
                    );
                }

                DataStore.listings.add(listing);

                JOptionPane.showMessageDialog(
                        dialog,
                        "Listing created successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dialog.dispose();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        dialog,
                        "Please enter a valid price.",
                        "Invalid Price",
                        JOptionPane.ERROR_MESSAGE
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        dialog,
                        "Please enter dates in YYYY-MM-DD format.",
                        "Invalid Date",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        cancelButton.addActionListener(e ->
                dialog.dispose()
        );

        dialog.add(mainPanel);
        dialog.setVisible(true);
    }

    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label,
            JComponent component) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        panel.add(
                new JLabel(label),
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        panel.add(
                component,
                gbc
        );
    }

    private void showMyListings() {

        JDialog dialog = new JDialog(
                this,
                "My Listings",
                true
        );

        dialog.setSize(750, 550);
        dialog.setLocationRelativeTo(this);

        JPanel listingsPanel = new JPanel();

        listingsPanel.setLayout(
                new BoxLayout(
                        listingsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        listingsPanel.setBackground(Color.WHITE);

        listingsPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel heading =
                new JLabel("My Listings");

        heading.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        heading.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        listingsPanel.add(heading);
        listingsPanel.add(
                Box.createVerticalStrut(20)
        );

        boolean foundListing = false;

        for (Listing listing :
                DataStore.listings) {

            if (listing.getListerId()
                    == currentLister.getUserId()) {

                foundListing = true;

                JPanel card = new JPanel();

                card.setLayout(
                        new BoxLayout(
                                card,
                                BoxLayout.Y_AXIS
                        )
                );

                card.setBackground(Color.WHITE);

                card.setAlignmentX(
                        Component.LEFT_ALIGNMENT
                );

                card.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(
                                        new Color(
                                                255,
                                                140,
                                                0
                                        ),
                                        2
                                ),
                                BorderFactory.createEmptyBorder(
                                        15,
                                        15,
                                        15,
                                        15
                                )
                        )
                );

                if (listing.getImagePath() != null) {

                    File imageFile =
                            new File(
                                    listing.getImagePath()
                            );

                    if (imageFile.exists()) {

                        ImageIcon originalIcon =
                                new ImageIcon(
                                        listing.getImagePath()
                                );

                        Image scaledImage =
                                originalIcon
                                        .getImage()
                                        .getScaledInstance(
                                                220,
                                                140,
                                                Image.SCALE_SMOOTH
                                        );

                        JLabel imageLabel =
                                new JLabel(
                                        new ImageIcon(
                                                scaledImage
                                        )
                                );

                        imageLabel.setAlignmentX(
                                Component.LEFT_ALIGNMENT
                        );

                        card.add(imageLabel);
                        card.add(
                                Box.createVerticalStrut(10)
                        );
                    }
                }

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
                                "Location: "
                                        + listing.getBorough()
                                        + ", "
                                        + listing.getNeighbourhood()
                        );

                JLabel price =
                        new JLabel(
                                "Price: €"
                                        + listing.getPrice()
                                        + " per month"
                        );

                JLabel dates =
                        new JLabel(
                                "Available: "
                                        + listing.getStartDate()
                                        + " to "
                                        + listing.getEndDate()
                        );

                JLabel roomType =
                        new JLabel(
                                "Room Type: "
                                        + listing.getRoomType()
                        );

                JLabel description =
                        new JLabel(
                                "<html>Description: "
                                        + listing.getDescription()
                                        + "</html>"
                        );

                card.add(title);
                card.add(Box.createVerticalStrut(8));
                card.add(location);
                card.add(price);
                card.add(dates);
                card.add(roomType);
                card.add(description);

                card.add(
                        Box.createVerticalStrut(12)
                );

                JPanel buttonPanel =
                        new JPanel(
                                new FlowLayout(
                                        FlowLayout.LEFT
                                )
                        );

                buttonPanel.setBackground(Color.WHITE);

                JButton editButton =
                        new JButton("Edit");

                JButton deleteButton =
                        new JButton("Delete");

                editButton.setBackground(
                        new Color(255, 140, 0)
                );

                editButton.setForeground(Color.WHITE);
                editButton.setFocusPainted(false);
                deleteButton.setFocusPainted(false);

                buttonPanel.add(editButton);
                buttonPanel.add(deleteButton);

                card.add(buttonPanel);

                editButton.addActionListener(e -> {

                    dialog.dispose();
                    editListing(listing);
                });

                deleteButton.addActionListener(e -> {

                    deleteListing(listing);

                    dialog.dispose();
                    showMyListings();
                });

                listingsPanel.add(card);

                listingsPanel.add(
                        Box.createVerticalStrut(15)
                );
            }
        }

        if (!foundListing) {

            JLabel noListings =
                    new JLabel(
                            "You have not created any listings yet."
                    );

            noListings.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            16
                    )
            );

            listingsPanel.add(noListings);
        }

        JScrollPane scrollPane =
                new JScrollPane(listingsPanel);

        scrollPane.setBorder(null);

        dialog.add(scrollPane);
        dialog.setVisible(true);
    }

    private void editListing(Listing listing) {

        JTextField titleField =
                new JTextField(
                        listing.getTitle(),
                        20
                );

        JTextField priceField =
                new JTextField(
                        String.valueOf(
                                listing.getPrice()
                        ),
                        20
                );

        JTextArea descriptionArea =
                new JTextArea(
                        listing.getDescription(),
                        4,
                        20
                );

        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        JPanel editPanel =
                new JPanel(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 8, 8, 8);

        gbc.anchor =
                GridBagConstraints.WEST;

        addFormRow(
                editPanel,
                gbc,
                0,
                "Title:",
                titleField
        );

        addFormRow(
                editPanel,
                gbc,
                1,
                "Monthly Price (€):",
                priceField
        );

        JScrollPane descriptionScroll =
                new JScrollPane(
                        descriptionArea
                );

        descriptionScroll.setPreferredSize(
                new Dimension(250, 80)
        );

        addFormRow(
                editPanel,
                gbc,
                2,
                "Description:",
                descriptionScroll
        );

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        editPanel,
                        "Edit Listing",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (result !=
                JOptionPane.OK_OPTION) {

            showMyListings();
            return;
        }

        try {

            String title =
                    titleField.getText().trim();

            String description =
                    descriptionArea
                            .getText()
                            .trim();

            if (!Validator.notEmpty(title)
                    || !Validator.notEmpty(
                            priceField.getText()
                    )
                    || !Validator.notEmpty(
                            description
                    )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please complete all required fields.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE
                );

                showMyListings();
                return;
            }

            double price =
                    Double.parseDouble(
                            priceField
                                    .getText()
                                    .trim()
                    );

            if (!Validator.validPrice(price)) {

                JOptionPane.showMessageDialog(
                        this,
                        "Price must be greater than zero.",
                        "Invalid Price",
                        JOptionPane.ERROR_MESSAGE
                );

                showMyListings();
                return;
            }

            listing.setTitle(title);
            listing.setPrice(price);
            listing.setDescription(description);

            JOptionPane.showMessageDialog(
                    this,
                    "Listing updated successfully.",
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
        }

        showMyListings();
    }

    private void deleteListing(
            Listing listing) {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this listing?",
                        "Delete Listing",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            DataStore.listings.remove(
                    listing
            );

            DataStore.applications.removeIf(
                    application ->
                            application.getListingId()
                                    == listing.getListingId()
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Listing deleted successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    // Shows applications for this Lister's listings
    private void showApplications() {

        JDialog dialog = new JDialog(
                this,
                "Manage Applications",
                true
        );

        dialog.setSize(750, 550);
        dialog.setLocationRelativeTo(this);

        JPanel applicationsPanel =
                new JPanel();

        applicationsPanel.setLayout(
                new BoxLayout(
                        applicationsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        applicationsPanel.setBackground(
                Color.WHITE
        );

        applicationsPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel heading =
                new JLabel(
                        "Manage Applications"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        heading.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        applicationsPanel.add(heading);

        applicationsPanel.add(
                Box.createVerticalStrut(20)
        );

        boolean foundApplication = false;

        for (Application application :
                DataStore.applications) {

            Listing listing =
                    findListing(
                            application.getListingId()
                    );

            if (listing != null
                    && listing.getListerId()
                    == currentLister.getUserId()) {

                foundApplication = true;

                JPanel card =
                        new JPanel();

                card.setLayout(
                        new BoxLayout(
                                card,
                                BoxLayout.Y_AXIS
                        )
                );

                card.setBackground(Color.WHITE);

                card.setAlignmentX(
                        Component.LEFT_ALIGNMENT
                );

                card.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(
                                        new Color(
                                                255,
                                                140,
                                                0
                                        ),
                                        2
                                ),
                                BorderFactory.createEmptyBorder(
                                        15,
                                        15,
                                        15,
                                        15
                                )
                        )
                );

                JLabel listingTitle =
                        new JLabel(
                                listing.getTitle()
                        );

                listingTitle.setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                18
                        )
                );

                JLabel student =
                        new JLabel(
                                "Student ID: "
                                        + application.getStudentId()
                        );

                JLabel status =
                        new JLabel(
                                "Status: "
                                        + application.getStatus()
                        );

                JLabel date =
                        new JLabel(
                                "Applied: "
                                        + application.getAppliedAt()
                        );

                card.add(listingTitle);

                card.add(
                        Box.createVerticalStrut(8)
                );

                card.add(student);
                card.add(status);
                card.add(date);

                if ("Pending".equalsIgnoreCase(
                        application.getStatus()
                )) {

                    card.add(
                            Box.createVerticalStrut(10)
                    );

                    JPanel buttons =
                            new JPanel(
                                    new FlowLayout(
                                            FlowLayout.LEFT
                                    )
                            );

                    buttons.setBackground(
                            Color.WHITE
                    );

                    JButton acceptButton =
                            new JButton("Accept");

                    JButton rejectButton =
                            new JButton("Reject");

                    acceptButton.setBackground(
                            new Color(
                                    255,
                                    140,
                                    0
                            )
                    );

                    acceptButton.setForeground(
                            Color.WHITE
                    );

                    acceptButton.setFocusPainted(
                            false
                    );

                    rejectButton.setFocusPainted(
                            false
                    );

                    acceptButton.addActionListener(
                            e -> {

                                application.setStatus(
                                        "Accepted"
                                );

                                JOptionPane.showMessageDialog(
                                        dialog,
                                        "Application accepted."
                                );

                                dialog.dispose();
                                showApplications();
                            }
                    );

                    rejectButton.addActionListener(
                            e -> {

                                application.setStatus(
                                        "Rejected"
                                );

                                JOptionPane.showMessageDialog(
                                        dialog,
                                        "Application rejected."
                                );

                                dialog.dispose();
                                showApplications();
                            }
                    );

                    buttons.add(acceptButton);
                    buttons.add(rejectButton);

                    card.add(buttons);
                }

                applicationsPanel.add(card);

                applicationsPanel.add(
                        Box.createVerticalStrut(15)
                );
            }
        }

        if (!foundApplication) {

            JLabel noApplications =
                    new JLabel(
                            "No applications have been submitted yet."
                    );

            noApplications.setFont(
                    new Font(
                            "Arial",
                            Font.PLAIN,
                            16
                    )
            );

            applicationsPanel.add(
                    noApplications
            );
        }

        JScrollPane scrollPane =
                new JScrollPane(
                        applicationsPanel
                );

        scrollPane.setBorder(null);

        dialog.add(scrollPane);
        dialog.setVisible(true);
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

    private void showProfile() {

        JOptionPane.showMessageDialog(
                this,
                "Name: "
                        + currentLister.getName()
                        + "\nEmail: "
                        + currentLister.getEmail()
                        + "\nRole: "
                        + currentLister.getRole(),
                "Profile",
                JOptionPane.INFORMATION_MESSAGE
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