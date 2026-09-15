import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MarketplaceDashboard extends JFrame {

    private User currentUser;

    private CardLayout cardLayout;
    private JPanel pagesPanel;

    private JComboBox<String> boroughBox;
    private JComboBox<String> neighbourhoodBox;
    private JTextField priceField;
    private JTextField startDateField;
    private JTextField endDateField;

    private JPanel listingsGrid;

    private final Color orange = new Color(240, 120, 30);
    private final Color lightBackground = new Color(248, 248, 248);

    public MarketplaceDashboard(User user) {

        this.currentUser = user;

        setTitle("Berlin Sublet");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        pagesPanel = new JPanel(cardLayout);

        pagesPanel.add(
                createMarketplacePage(),
                "MARKETPLACE"
        );

        pagesPanel.add(
                createListRoomPage(),
                "LIST_ROOM"
        );

        pagesPanel.add(
                createMyListingsPage(),
                "MY_LISTINGS"
        );

        pagesPanel.add(
                createApplicationsPage(),
                "APPLICATIONS"
        );

        JPanel root =
                new JPanel(new BorderLayout());

        root.add(
                createNavigation(),
                BorderLayout.NORTH
        );

        root.add(
                pagesPanel,
                BorderLayout.CENTER
        );

        add(root);

        setVisible(true);

        refreshListings();
    }

    // ---------------------------------------------------------
    // NAVIGATION
    // ---------------------------------------------------------

    private JPanel createNavigation() {

        JPanel navigation =
                new JPanel(new BorderLayout());

        navigation.setBackground(orange);

        navigation.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        25,
                        15,
                        25
                )
        );

        JLabel logo =
                new JLabel("BERLIN SUBLET");

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        logo.setForeground(Color.WHITE);

        JPanel menu =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        menu.setBackground(orange);

        JButton findButton =
                createNavigationButton(
                        "Find a Sublet"
                );

        JButton listButton =
                createNavigationButton(
                        "List a Room"
                );

        JButton applicationsButton =
                createNavigationButton(
                        "Applications"
                );

        JButton myListingsButton =
                createNavigationButton(
                        "My Listings"
                );

        JButton profileButton =
                createNavigationButton(
                        "Profile"
                );

        JButton logoutButton =
                createNavigationButton(
                        "Logout"
                );

        findButton.addActionListener(e -> {

            refreshListings();

            cardLayout.show(
                    pagesPanel,
                    "MARKETPLACE"
            );
        });

        listButton.addActionListener(e -> {

            cardLayout.show(
                    pagesPanel,
                    "LIST_ROOM"
            );
        });

        applicationsButton.addActionListener(e -> {

            refreshApplicationsPage();

            cardLayout.show(
                    pagesPanel,
                    "APPLICATIONS"
            );
        });

        myListingsButton.addActionListener(e -> {

            refreshMyListingsPage();

            cardLayout.show(
                    pagesPanel,
                    "MY_LISTINGS"
            );
        });

        profileButton.addActionListener(
                e -> showProfile()
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        menu.add(findButton);
        menu.add(listButton);
        menu.add(applicationsButton);
        menu.add(myListingsButton);
        menu.add(profileButton);
        menu.add(logoutButton);

        navigation.add(
                logo,
                BorderLayout.WEST
        );

        navigation.add(
                menu,
                BorderLayout.EAST
        );

        return navigation;
    }

    private JButton createNavigationButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setBackground(Color.WHITE);
        button.setForeground(orange);

        button.setFocusPainted(false);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        return button;
    }

    // ---------------------------------------------------------
    // MARKETPLACE PAGE
    // ---------------------------------------------------------

    private JScrollPane createMarketplacePage() {

        JPanel page =
                new JPanel();

        page.setLayout(
                new BoxLayout(
                        page,
                        BoxLayout.Y_AXIS
                )
        );

        page.setBackground(Color.WHITE);

        page.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        40,
                        50
                )
        );

        JLabel title =
                new JLabel(
                        "Find your next sublet"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        title.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel subtitle =
                new JLabel(
                        "Browse available student accommodation in Berlin."
                );

        subtitle.setForeground(
                Color.GRAY
        );

        subtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        page.add(title);

        page.add(
                Box.createVerticalStrut(5)
        );

        page.add(subtitle);

        page.add(
                Box.createVerticalStrut(25)
        );

        page.add(
                createFilterPanel()
        );

        page.add(
                Box.createVerticalStrut(30)
        );

        JLabel listingsTitle =
                new JLabel(
                        "Available Listings"
                );

        listingsTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        23
                )
        );

        listingsTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        page.add(listingsTitle);

        page.add(
                Box.createVerticalStrut(15)
        );

        listingsGrid =
                new JPanel(
                        new GridLayout(
                                0,
                                3,
                                18,
                                18
                        )
                );

        listingsGrid.setBackground(
                Color.WHITE
        );

        listingsGrid.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        page.add(listingsGrid);

        JScrollPane scrollPane =
                new JScrollPane(page);

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(18);

        return scrollPane;
    }

    private JPanel createFilterPanel() {

        JPanel filter =
                new JPanel(
                        new GridBagLayout()
                );

        filter.setBackground(
                lightBackground
        );

        filter.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        225,
                                        225,
                                        225
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        filter.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        200
                )
        );

        filter.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        6,
                        8,
                        6,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        boroughBox =
                new JComboBox<>();

        boroughBox.addItem(
                "All Boroughs"
        );

        for (String borough :
                BerlinLocations.getBoroughs()) {

            boroughBox.addItem(borough);
        }

        neighbourhoodBox =
                new JComboBox<>();

        neighbourhoodBox.addItem(
                "All Neighbourhoods"
        );

        priceField =
                new JTextField();

        startDateField =
                new JTextField();

        endDateField =
                new JTextField();

        addFilterField(
                filter,
                gbc,
                0,
                0,
                "Borough",
                boroughBox
        );

        addFilterField(
                filter,
                gbc,
                1,
                0,
                "Neighbourhood",
                neighbourhoodBox
        );

        addFilterField(
                filter,
                gbc,
                2,
                0,
                "Maximum Price (€)",
                priceField
        );

        addFilterField(
                filter,
                gbc,
                0,
                1,
                "Available From",
                startDateField
        );

        addFilterField(
                filter,
                gbc,
                1,
                1,
                "Available Until",
                endDateField
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                8,
                                20
                        )
                );

        buttonPanel.setBackground(
                lightBackground
        );

        JButton filterButton =
                new JButton(
                        "Apply Filters"
                );

        JButton clearButton =
                new JButton(
                        "Clear"
                );

        filterButton.setBackground(
                orange
        );

        filterButton.setForeground(
                Color.WHITE
        );

        filterButton.setFocusPainted(
                false
        );

        clearButton.setFocusPainted(
                false
        );

        filterButton.addActionListener(
                e -> filterListings()
        );

        clearButton.addActionListener(e -> {

            boroughBox.setSelectedIndex(0);

            neighbourhoodBox.removeAllItems();

            neighbourhoodBox.addItem(
                    "All Neighbourhoods"
            );

            priceField.setText("");
            startDateField.setText("");
            endDateField.setText("");

            refreshListings();
        });

        buttonPanel.add(filterButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 2;
        gbc.gridy = 1;

        filter.add(
                buttonPanel,
                gbc
        );

        boroughBox.addActionListener(e ->
                updateFilterNeighbourhoods()
        );

        return filter;
    }

    private void addFilterField(
            JPanel panel,
            GridBagConstraints gbc,
            int column,
            int row,
            String label,
            JComponent component) {

        JPanel fieldPanel =
                new JPanel();

        fieldPanel.setLayout(
                new BoxLayout(
                        fieldPanel,
                        BoxLayout.Y_AXIS
                )
        );

        fieldPanel.setBackground(
                lightBackground
        );

        JLabel fieldLabel =
                new JLabel(label);

        fieldLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        component.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        32
                )
        );

        component.setPreferredSize(
                new Dimension(
                        220,
                        32
                )
        );

        fieldPanel.add(fieldLabel);

        fieldPanel.add(
                Box.createVerticalStrut(4)
        );

        fieldPanel.add(component);

        gbc.gridx = column;
        gbc.gridy = row;

        panel.add(
                fieldPanel,
                gbc
        );
    }

    private void updateFilterNeighbourhoods() {

        neighbourhoodBox.removeAllItems();

        neighbourhoodBox.addItem(
                "All Neighbourhoods"
        );

        String borough =
                (String)
                        boroughBox
                                .getSelectedItem();

        if (borough == null
                || borough.equals(
                        "All Boroughs"
                )) {

            return;
        }

        for (String neighbourhood :
                BerlinLocations
                        .getNeighbourhoods(
                                borough
                        )) {

            neighbourhoodBox.addItem(
                    neighbourhood
            );
        }
    }

    // ---------------------------------------------------------
    // LISTING CARDS
    // ---------------------------------------------------------

    private void refreshListings() {

        if (listingsGrid == null) {
            return;
        }

        listingsGrid.removeAll();

        int count = 0;

        for (Listing listing :
                DataStore.listings) {

            if (listing.isAvailable()) {

                listingsGrid.add(
                        createListingCard(
                                listing
                        )
                );

                count++;
            }
        }

        if (count == 0) {

            JLabel none =
                    new JLabel(
                            "No listings are currently available."
                    );

            none.setForeground(
                    Color.GRAY
            );

            listingsGrid.add(none);
        }

        listingsGrid.revalidate();
        listingsGrid.repaint();
    }

    private JPanel createListingCard(
            Listing listing) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setPreferredSize(
                new Dimension(
                        260,
                        330
                )
        );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                220,
                                220,
                                220
                        )
                )
        );

        JLabel imageLabel =
                createListingImage(
                        listing
                );

        card.add(
                imageLabel,
                BorderLayout.NORTH
        );

        JPanel details =
                new JPanel();

        details.setLayout(
                new BoxLayout(
                        details,
                        BoxLayout.Y_AXIS
                )
        );

        details.setBackground(
                Color.WHITE
        );

        details.setBorder(
                BorderFactory.createEmptyBorder(
                        12,
                        14,
                        12,
                        14
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
                        17
                )
        );

        JLabel location =
                new JLabel(
                        listing.getNeighbourhood()
                                + ", "
                                + listing.getBorough()
                );

        location.setForeground(
                Color.DARK_GRAY
        );

        JLabel price =
                new JLabel(
                        "€"
                                + listing.getPrice()
                                + " / month"
                );

        price.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        JLabel roomType =
                new JLabel(
                        listing.getRoomType()
                );

        roomType.setForeground(
                Color.GRAY
        );

        JButton detailsButton =
                new JButton(
                        "View Details"
                );

        detailsButton.setBackground(
                orange
        );

        detailsButton.setForeground(
                Color.WHITE
        );

        detailsButton.setFocusPainted(
                false
        );

        detailsButton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        detailsButton.addActionListener(
                e ->
                        showListingDetails(
                                listing
                        )
        );

        details.add(title);

        details.add(
                Box.createVerticalStrut(7)
        );

        details.add(location);

        details.add(
                Box.createVerticalStrut(5)
        );

        details.add(price);

        details.add(
                Box.createVerticalStrut(5)
        );

        details.add(roomType);

        details.add(
                Box.createVerticalGlue()
        );

        details.add(
                Box.createVerticalStrut(12)
        );

        details.add(detailsButton);

        card.add(
                details,
                BorderLayout.CENTER
        );

        return card;
    }

    private JLabel createListingImage(
            Listing listing) {

        JLabel imageLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER
                );

        imageLabel.setPreferredSize(
                new Dimension(
                        260,
                        170
                )
        );

        imageLabel.setOpaque(true);

        imageLabel.setBackground(
                new Color(
                        235,
                        235,
                        235
                )
        );

        if (listing.getImagePath()
                != null) {

            File file =
                    new File(
                            listing.getImagePath()
                    );

            if (file.exists()) {

                ImageIcon icon =
                        new ImageIcon(
                                listing
                                        .getImagePath()
                        );

                Image scaled =
                        icon.getImage()
                                .getScaledInstance(
                                        260,
                                        170,
                                        Image.SCALE_SMOOTH
                                );

                imageLabel.setIcon(
                        new ImageIcon(
                                scaled
                        )
                );

                return imageLabel;
            }
        }

        imageLabel.setText(
                "No Image Available"
        );

        imageLabel.setForeground(
                Color.GRAY
        );

        return imageLabel;
    }

    // ---------------------------------------------------------
    // FILTER LISTINGS
    // ---------------------------------------------------------

    private void filterListings() {

        String borough =
                (String)
                        boroughBox
                                .getSelectedItem();

        String neighbourhood =
                (String)
                        neighbourhoodBox
                                .getSelectedItem();

        String priceText =
                priceField
                        .getText()
                        .trim();

        String startText =
                startDateField
                        .getText()
                        .trim();

        String endText =
                endDateField
                        .getText()
                        .trim();

        try {

            Double maximumPrice = null;

            LocalDate startDate = null;
            LocalDate endDate = null;

            if (Validator.notEmpty(
                    priceText
            )) {

                maximumPrice =
                        Double.parseDouble(
                                priceText
                        );

                if (!Validator.validPrice(
                        maximumPrice
                )) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Price must be greater than zero."
                    );

                    return;
                }
            }

            if (Validator.notEmpty(
                    startText
            )

                    || Validator.notEmpty(
                    endText
            )) {

                if (!Validator.notEmpty(
                        startText
                )

                        || !Validator.notEmpty(
                        endText
                )) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter both dates."
                    );

                    return;
                }

                startDate =
                        LocalDate.parse(
                                startText
                        );

                endDate =
                        LocalDate.parse(
                                endText
                        );

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
            }

            listingsGrid.removeAll();

            int matches = 0;

            for (Listing listing :
                    DataStore.listings) {

                if (!listing.isAvailable()) {
                    continue;
                }

                boolean locationMatches =
                        borough.equals(
                                "All Boroughs"
                        )

                                || listing
                                .getBorough()
                                .equals(
                                        borough
                                );

                if (!neighbourhood.equals(
                        "All Neighbourhoods"
                )) {

                    locationMatches =
                            locationMatches

                                    && listing
                                    .getNeighbourhood()
                                    .equals(
                                            neighbourhood
                                    );
                }

                boolean priceMatches =
                        maximumPrice == null

                                || listing
                                .getPrice()
                                <= maximumPrice;

                boolean datesMatch =
                        startDate == null

                                || (!startDate.isBefore(
                                listing
                                        .getStartDate()
                        )

                                && !endDate.isAfter(
                                listing
                                        .getEndDate()
                        ));

                if (locationMatches
                        && priceMatches
                        && datesMatch) {

                    listingsGrid.add(
                            createListingCard(
                                    listing
                            )
                    );

                    matches++;
                }
            }

            if (matches == 0) {

                listingsGrid.add(
                        new JLabel(
                                "No listings match these filters."
                        )
                );
            }

            listingsGrid.revalidate();
            listingsGrid.repaint();

        } catch (
                NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid price."
            );

        } catch (
                DateTimeParseException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Dates must use YYYY-MM-DD."
            );
        }
    }

    // ---------------------------------------------------------
    // LIST A ROOM
    // ---------------------------------------------------------

  private JScrollPane createListRoomPage() {

    JPanel page = new JPanel();

    page.setLayout(
            new BoxLayout(
                    page,
                    BoxLayout.Y_AXIS
            )
    );

    page.setBackground(Color.WHITE);

    page.setBorder(
            BorderFactory.createEmptyBorder(
                    35,
                    100,
                    50,
                    100
            )
    );

    JLabel title =
            new JLabel("List your room");

    title.setFont(
            new Font(
                    "Arial",
                    Font.BOLD,
                    30
            )
    );

    title.setAlignmentX(
            Component.LEFT_ALIGNMENT
    );

    JLabel text =
            new JLabel(
                    "Have a room available? Create a listing for students in Berlin."
            );

    text.setForeground(Color.GRAY);

    text.setAlignmentX(
            Component.LEFT_ALIGNMENT
    );

    JButton createButton =
            new JButton(
                    "Create New Listing"
            );

    createButton.setBackground(orange);
    createButton.setForeground(Color.WHITE);
    createButton.setFocusPainted(false);

    createButton.setPreferredSize(
            new Dimension(
                    220,
                    42
            )
    );

    createButton.setMaximumSize(
            new Dimension(
                    220,
                    42
            )
    );

    createButton.setAlignmentX(
            Component.LEFT_ALIGNMENT
    );

    createButton.addActionListener(e -> {

        /*
         * Every normal user can act as a Sublettor.
         * We keep the same user ID so listings remain
         * connected to the correct account.
         */
        Lister listingUser =
                new Lister(
                        currentUser.getUserId(),
                        currentUser.getName(),
                        currentUser.getEmail(),
                        currentUser.getPassword()
                );

        new ListerDashboard(
                listingUser
        );
    });

    page.add(title);

    page.add(
            Box.createVerticalStrut(8)
    );

    page.add(text);

    page.add(
            Box.createVerticalStrut(25)
    );

    page.add(createButton);

    JScrollPane scrollPane =
            new JScrollPane(page);

    scrollPane.setBorder(null);

    scrollPane.getVerticalScrollBar()
            .setUnitIncrement(18);

    return scrollPane;
}

    // ---------------------------------------------------------
    // APPLICATIONS PAGE
    // ---------------------------------------------------------

    private JPanel applicationsContent;

    private JScrollPane createApplicationsPage() {

        applicationsContent =
                new JPanel();

        applicationsContent.setLayout(
                new BoxLayout(
                        applicationsContent,
                        BoxLayout.Y_AXIS
                )
        );

        applicationsContent.setBackground(
                Color.WHITE
        );

        applicationsContent.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        40,
                        50
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        applicationsContent
                );

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(18);

        return scrollPane;
    }

    private void refreshApplicationsPage() {

        applicationsContent.removeAll();

        JLabel heading =
                new JLabel(
                        "Applications"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        applicationsContent.add(
                heading
        );

        applicationsContent.add(
                Box.createVerticalStrut(
                        20
                )
        );

        if (currentUser
                instanceof Student) {

            Student student =
                    (Student)
                            currentUser;

            int count = 0;

            for (Application application :
                    DataStore.applications) {

                if (application
                        .getStudentId()

                        != student
                        .getUserId()) {

                    continue;
                }

                Listing listing =
                        findListing(
                                application
                                        .getListingId()
                        );

                if (listing == null) {
                    continue;
                }

                JLabel row =
                        new JLabel(
                                listing.getTitle()
                                        + " — "
                                        + application
                                        .getStatus()
                        );

                row.setFont(
                        new Font(
                                "Arial",
                                Font.PLAIN,
                                16
                        )
                );

                applicationsContent.add(
                        row
                );

                applicationsContent.add(
                        Box.createVerticalStrut(
                                12
                        )
                );

                count++;
            }

            if (count == 0) {

                applicationsContent.add(
                        new JLabel(
                                "You have not submitted any applications."
                        )
                );
            }

        } else {

            applicationsContent.add(
                    new JLabel(
                            "Application management for Listers will be connected here."
                    )
            );
        }

        applicationsContent.revalidate();
        applicationsContent.repaint();
    }

    // ---------------------------------------------------------
    // MY LISTINGS PAGE
    // ---------------------------------------------------------

    private JPanel myListingsContent;

    private JScrollPane createMyListingsPage() {

        myListingsContent =
                new JPanel();

        myListingsContent.setLayout(
                new BoxLayout(
                        myListingsContent,
                        BoxLayout.Y_AXIS
                )
        );

        myListingsContent.setBackground(
                Color.WHITE
        );

        myListingsContent.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        40,
                        50
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        myListingsContent
                );

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(18);

        return scrollPane;
    }

 private void refreshMyListingsPage() {

    myListingsContent.removeAll();

    JLabel heading =
            new JLabel(
                    "My Listings"
            );

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

    myListingsContent.add(
            heading
    );

    myListingsContent.add(
            Box.createVerticalStrut(20)
    );

    int count = 0;

    for (Listing listing :
            DataStore.listings) {

        // Only show listings created by this user
        if (listing.getListerId()
                != currentUser.getUserId()) {

            continue;
        }

        JPanel row =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        row.setBackground(Color.WHITE);

        row.setMaximumSize(
                new Dimension(
                        900,
                        130
                )
        );

        row.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        row.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        220,
                                        220
                                )
                        ),

                        BorderFactory.createEmptyBorder(
                                12,
                                12,
                                12,
                                12
                        )
                )
        );

        JLabel imageLabel =
                createListingImage(
                        listing
                );

        imageLabel.setPreferredSize(
                new Dimension(
                        150,
                        100
                )
        );

        JPanel details =
                new JPanel();

        details.setLayout(
                new BoxLayout(
                        details,
                        BoxLayout.Y_AXIS
                )
        );

        details.setBackground(
                Color.WHITE
        );

        JLabel titleLabel =
                new JLabel(
                        listing.getTitle()
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        JLabel locationLabel =
                new JLabel(
                        listing.getNeighbourhood()
                                + ", "
                                + listing.getBorough()
                );

        JLabel priceLabel =
                new JLabel(
                        "€"
                                + listing.getPrice()
                                + " / month"
                );

        details.add(titleLabel);

        details.add(
                Box.createVerticalStrut(5)
        );

        details.add(locationLabel);
        details.add(priceLabel);

        row.add(
                imageLabel,
                BorderLayout.WEST
        );

        row.add(
                details,
                BorderLayout.CENTER
        );

        myListingsContent.add(row);

        myListingsContent.add(
                Box.createVerticalStrut(12)
        );

        count++;
    }

    if (count == 0) {

        JLabel message =
                new JLabel(
                        "You have not created any listings yet."
                );

        message.setForeground(
                Color.GRAY
        );

        myListingsContent.add(
                message
        );
    }

    myListingsContent.revalidate();
    myListingsContent.repaint();
}

    // ---------------------------------------------------------
    // LISTING DETAILS
    // ---------------------------------------------------------

    private void showListingDetails(
            Listing listing) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        JLabel image =
                createListingImage(
                        listing
                );

        image.setPreferredSize(
                new Dimension(
                        300,
                        190
                )
        );

        panel.add(
                image,
                BorderLayout.NORTH
        );

        JLabel information =
                new JLabel(
                        "<html>"
                                + "<h2>"
                                + listing.getTitle()
                                + "</h2>"

                                + "<b>Location:</b> "
                                + listing
                                .getNeighbourhood()
                                + ", "
                                + listing
                                .getBorough()

                                + "<br><b>Price:</b> €"
                                + listing
                                .getPrice()
                                + " per month"

                                + "<br><b>Available:</b> "
                                + listing
                                .getStartDate()
                                + " to "
                                + listing
                                .getEndDate()

                                + "<br><b>Room Type:</b> "
                                + listing
                                .getRoomType()

                                + "<br><br>"
                                + listing
                                .getDescription()

                                + "</html>"
                );

        panel.add(
                information,
                BorderLayout.CENTER
        );

        Object[] options;

        if (currentUser
                instanceof Student) {

            options =
                    new Object[]{
                            "Apply",
                            "Report",
                            "Close"
                    };

        } else {

            options =
                    new Object[]{
                            "Close"
                    };
        }

        int choice =
                JOptionPane.showOptionDialog(
                        this,
                        panel,
                        "Listing Details",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (currentUser
                instanceof Student) {

            if (choice == 0) {

                applyForListing(
                        listing
                );
            }

            if (choice == 1) {

                reportListing(
                        listing
                );
            }
        }
    }

    private void applyForListing(
            Listing listing) {

        Student student =
                (Student)
                        currentUser;

        for (Application application :
                DataStore.applications) {

            if (application
                    .getStudentId()

                    == student
                    .getUserId()

                    && application
                    .getListingId()

                    == listing
                    .getListingId()) {

                JOptionPane.showMessageDialog(
                        this,
                        "You already applied for this listing."
                );

                return;
            }
        }

        Application application =
                new Application(
                        DataStore
                                .getNextApplicationId(),

                        student
                                .getUserId(),

                        listing
                                .getListingId()
                );

        DataStore.applications.add(
                application
        );

        JOptionPane.showMessageDialog(
                this,
                "Application submitted successfully."
        );
    }

    private void reportListing(
            Listing listing) {

        Student student =
                (Student)
                        currentUser;

        String reason =
                JOptionPane.showInputDialog(
                        this,
                        "Why are you reporting this listing?"
                );

        if (reason == null) {
            return;
        }

        if (!Validator.notEmpty(
                reason
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a reason."
            );

            return;
        }

        for (Report report :
                DataStore.reports) {

            if (report
                    .getStudentId()

                    == student
                    .getUserId()

                    && report
                    .getListingId()

                    == listing
                    .getListingId()) {

                JOptionPane.showMessageDialog(
                        this,
                        "You already reported this listing."
                );

                return;
            }
        }

        Report report =
                new Report(
                        DataStore
                                .getNextReportId(),

                        student
                                .getUserId(),

                        listing
                                .getListingId(),

                        reason
                );

        DataStore.reports.add(
                report
        );

        JOptionPane.showMessageDialog(
                this,
                "Report submitted successfully."
        );
    }

    // ---------------------------------------------------------
    // PROFILE
    // ---------------------------------------------------------

    private void showProfile() {

        JOptionPane.showMessageDialog(
                this,
                "Name: "
                        + currentUser.getName()

                        + "\nEmail: "
                        + currentUser.getEmail()

                        + "\nRole: "
                        + currentUser.getRole(),

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

    private Listing findListing(
            int listingId) {

        for (Listing listing :
                DataStore.listings) {

            if (listing
                    .getListingId()

                    == listingId) {

                return listing;
            }
        }

        return null;
    }
}