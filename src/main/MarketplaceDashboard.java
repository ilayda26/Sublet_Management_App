import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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
    private JLabel listingCountLabel;

    private JPanel applicationsContent;
    private JPanel myListingsContent;

    private final Color ORANGE =
            new Color(240, 120, 30);

    private final Color ORANGE_HOVER =
            new Color(220, 100, 15);

    private final Color DARK =
            new Color(26, 35, 26);

    private final Color MUTED =
            new Color(105, 110, 102);

    private final Color BACKGROUND =
            new Color(250, 250, 247);

    private final Color BORDER =
            new Color(225, 226, 220);

    private final Color HERO =
            new Color(255, 248, 241);

    public MarketplaceDashboard(User user) {

        currentUser = user;

        setTitle("Berlin Sublet");
        setSize(1280, 820);

        setMinimumSize(
                new Dimension(
                        1050,
                        700
                )
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        cardLayout =
                new CardLayout();

        pagesPanel =
                new JPanel(
                        cardLayout
                );

        pagesPanel.add(
                createMarketplacePage(),
                "MARKETPLACE"
        );

        pagesPanel.add(
                createListRoomPage(),
                "LIST_ROOM"
        );

        pagesPanel.add(
                createApplicationsPage(),
                "APPLICATIONS"
        );

        pagesPanel.add(
                createMyListingsPage(),
                "MY_LISTINGS"
        );

        JPanel root =
                new JPanel(
                        new BorderLayout()
                );

        root.setBackground(
                BACKGROUND
        );

        root.add(
                createHeader(),
                BorderLayout.NORTH
        );

        root.add(
                pagesPanel,
                BorderLayout.CENTER
        );

        add(root);

        refreshListings();

        setVisible(true);
    }

    // -------------------------------------------------
    // HEADER
    // -------------------------------------------------

    private JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                Color.WHITE
        );

        header.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createMatteBorder(
                                0,
                                0,
                                1,
                                0,
                                BORDER
                        ),

                        new EmptyBorder(
                                14,
                                45,
                                14,
                                45
                        )
                )
        );

        JLabel logo =
                new JLabel(
                        "<html>"
                                + "<b>BERLIN</b>"
                                + "<font color='#F0781E'>SUBLET</font>"
                                + "</html>"
                );

        logo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        26
                )
        );

        logo.setForeground(
                DARK
        );

        JPanel menu =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                12,
                                0
                        )
                );

        menu.setBackground(
                Color.WHITE
        );

        JButton browseButton =
                createTextButton(
                        "Browse"
                );

        JButton postButton =
                new RoundedButton(
                        "+ Post sublet",
                        ORANGE,
                        Color.WHITE
                );

        JButton applicationsButton =
                createTextButton(
                        "Applications"
                );

        JButton listingsButton =
                createTextButton(
                        "My Listings"
                );

        JButton profileButton =
                createOutlineButton(
                        "Profile"
                );

        JButton logoutButton =
                createOutlineButton(
                        "Logout"
                );

        browseButton.addActionListener(
                e -> {

                    refreshListings();

                    cardLayout.show(
                            pagesPanel,
                            "MARKETPLACE"
                    );
                }
        );

        postButton.addActionListener(
                e ->
                        cardLayout.show(
                                pagesPanel,
                                "LIST_ROOM"
                        )
        );

        applicationsButton.addActionListener(
                e -> {

                    refreshApplicationsPage();

                    cardLayout.show(
                            pagesPanel,
                            "APPLICATIONS"
                    );
                }
        );

        listingsButton.addActionListener(
                e -> {

                    refreshMyListingsPage();

                    cardLayout.show(
                            pagesPanel,
                            "MY_LISTINGS"
                    );
                }
        );

        profileButton.addActionListener(
                e -> showProfile()
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        menu.add(
                browseButton
        );

        menu.add(
                postButton
        );

        menu.add(
                applicationsButton
        );

        menu.add(
                listingsButton
        );

        menu.add(
                profileButton
        );

        menu.add(
                logoutButton
        );

        header.add(
                logo,
                BorderLayout.WEST
        );

        header.add(
                menu,
                BorderLayout.EAST
        );

        return header;
    }

    private JButton createTextButton(
            String text) {

        JButton button =
                new JButton(
                        text
                );

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(
                DARK
        );

        button.setBackground(
                Color.WHITE
        );

        button.setBorderPainted(
                false
        );

        button.setContentAreaFilled(
                false
        );

        button.setFocusPainted(
                false
        );

        button.setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    private JButton createOutlineButton(
            String text) {

        RoundedButton button =
                new RoundedButton(
                        text,
                        Color.WHITE,
                        DARK
                );

        button.setBorderColor(
                BORDER
        );

        return button;
    }

    // -------------------------------------------------
    // MARKETPLACE
    // -------------------------------------------------

    private JScrollPane createMarketplacePage() {

        JPanel page =
                new JPanel();

        page.setLayout(
                new BoxLayout(
                        page,
                        BoxLayout.Y_AXIS
                )
        );

        page.setBackground(
                BACKGROUND
        );

        page.setBorder(
                new EmptyBorder(
                        28,
                        45,
                        60,
                        45
                )
        );

        JPanel hero =
                createHero();

        hero.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        page.add(
                hero
        );

        page.add(
                Box.createVerticalStrut(
                        26
                )
        );

        JPanel filterCard =
                createFilterPanel();

        filterCard.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        page.add(
                filterCard
        );

        page.add(
                Box.createVerticalStrut(
                        34
                )
        );

        JPanel listingsHeader =
                new JPanel(
                        new BorderLayout()
                );

        listingsHeader.setOpaque(
                false
        );

        listingsHeader.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        70
                )
        );

        listingsHeader.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JPanel titleArea =
                new JPanel();

        titleArea.setOpaque(
                false
        );

        titleArea.setLayout(
                new BoxLayout(
                        titleArea,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel available =
                new JLabel(
                        "●  AVAILABLE NOW"
                );

        available.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        available.setForeground(
                ORANGE
        );

        listingCountLabel =
                new JLabel(
                        "0 listings"
                );

        listingCountLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30
                )
        );

        listingCountLabel.setForeground(
                DARK
        );

        titleArea.add(
                available
        );

        titleArea.add(
                Box.createVerticalStrut(
                        4
                )
        );

        titleArea.add(
                listingCountLabel
        );

        listingsHeader.add(
                titleArea,
                BorderLayout.WEST
        );

        page.add(
                listingsHeader
        );

        page.add(
                Box.createVerticalStrut(
                        16
                )
        );

        // GridBagLayout prevents the cards
        // from stretching across the screen
        listingsGrid =
                new JPanel(
                        new GridBagLayout()
                );

        listingsGrid.setBackground(
                BACKGROUND
        );

        listingsGrid.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        page.add(
                listingsGrid
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        page
                );

        scrollPane.setBorder(
                null
        );

        scrollPane.setBackground(
                BACKGROUND
        );

        scrollPane.getViewport()
                .setBackground(
                        BACKGROUND
                );

        scrollPane
                .getVerticalScrollBar()
                .setUnitIncrement(
                        20
                );

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        return scrollPane;
    }

    // -------------------------------------------------
    // HERO
    // -------------------------------------------------

    private JPanel createHero() {

        RoundedPanel hero =
                new RoundedPanel(
                        32,
                        HERO,
                        BORDER
                );

        hero.setLayout(
                new GridBagLayout()
        );

        // Increased height so buttons are fully visible
        hero.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        440
                )
        );

        hero.setPreferredSize(
                new Dimension(
                        1100,
                        440
                )
        );

        hero.setMinimumSize(
                new Dimension(
                        900,
                        440
                )
        );

        hero.setBorder(
                new EmptyBorder(
                        42,
                        55,
                        42,
                        55
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.fill =
                GridBagConstraints.BOTH;

        gbc.weighty =
                1;

        // Left side
        JPanel left =
                new JPanel();

        left.setOpaque(
                false
        );

        left.setLayout(
                new BoxLayout(
                        left,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel badge =
                new JLabel(
                        "  Student-first housing marketplace  "
                );

        badge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        badge.setForeground(
                DARK
        );

        badge.setOpaque(
                true
        );

        badge.setBackground(
                Color.WHITE
        );

        badge.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDER
                        ),

                        new EmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        badge.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel heroTitle =
                new JLabel(
                        "<html>"
                                + "Find your "
                                + "<font color='#F0781E'>perfect</font>"
                                + "<br>"
                                + "student sublet in Berlin"
                                + "</html>"
                );

        heroTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        42
                )
        );

        heroTitle.setForeground(
                DARK
        );

        heroTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel description =
                new JLabel(
                        "<html>"
                                + "Browse student sublets across Berlin. "
                                + "Simple, flexible accommodation<br>"
                                + "for your semester."
                                + "</html>"
                );

        description.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        17
                )
        );

        description.setForeground(
                MUTED
        );

        description.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JPanel actions =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                0
                        )
                );

        actions.setOpaque(
                false
        );

        actions.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        actions.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        55
                )
        );

        RoundedButton browse =
                new RoundedButton(
                        "Browse listings  →",
                        ORANGE,
                        Color.WHITE
                );

        RoundedButton post =
                new RoundedButton(
                        "Post your room",
                        Color.WHITE,
                        DARK
                );

        post.setBorderColor(
                BORDER
        );

        browse.addActionListener(
                e -> {

                    if (listingsGrid != null) {

                        refreshListings();

                        SwingUtilities.invokeLater(
                                () ->
                                        listingsGrid
                                                .scrollRectToVisible(
                                                        new Rectangle(
                                                                0,
                                                                0,
                                                                1,
                                                                1
                                                        )
                                                )
                        );
                    }
                }
        );

        post.addActionListener(
                e ->
                        cardLayout.show(
                                pagesPanel,
                                "LIST_ROOM"
                        )
        );

        actions.add(
                browse
        );

        actions.add(
                post
        );

        left.add(
                badge
        );

        left.add(
                Box.createVerticalStrut(
                        22
                )
        );

        left.add(
                heroTitle
        );

        left.add(
                Box.createVerticalStrut(
                        18
                )
        );

        left.add(
                description
        );

        left.add(
                Box.createVerticalStrut(
                        24
                )
        );

        left.add(
                actions
        );

        // Right side
        RoundedPanel featureCard =
                new RoundedPanel(
                        28,
                        Color.WHITE,
                        BORDER
                );

        featureCard.setLayout(
                new BoxLayout(
                        featureCard,
                        BoxLayout.Y_AXIS
                )
        );

        featureCard.setBorder(
                new EmptyBorder(
                        30,
                        30,
                        30,
                        30
                )
        );

        JLabel featureTitle =
                new JLabel(
                        "Simple student sublets"
                );

        featureTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        featureTitle.setForeground(
                DARK
        );

        featureTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        featureCard.add(
                featureTitle
        );

        featureCard.add(
                Box.createVerticalStrut(
                        22
                )
        );

        featureCard.add(
                createFeature(
                        "✓",
                        "Berlin-focused listings"
                )
        );

        featureCard.add(
                Box.createVerticalStrut(
                        16
                )
        );

        featureCard.add(
                createFeature(
                        "✓",
                        "Student-created accounts"
                )
        );

        featureCard.add(
                Box.createVerticalStrut(
                        16
                )
        );

        featureCard.add(
                createFeature(
                        "✓",
                        "Flexible availability dates"
                )
        );

        featureCard.add(
                Box.createVerticalStrut(
                        16
                )
        );

        featureCard.add(
                createFeature(
                        "✓",
                        "Direct sublet applications"
                )
        );

        gbc.gridx =
                0;

        gbc.gridy =
                0;

        gbc.weightx =
                0.66;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        35
                );

        hero.add(
                left,
                gbc
        );

        gbc.gridx =
                1;

        gbc.weightx =
                0.34;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        0
                );

        hero.add(
                featureCard,
                gbc
        );

        return hero;
    }

    private JPanel createFeature(
            String icon,
            String text) {

        JPanel panel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        panel.setOpaque(
                false
        );

        panel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel check =
                new JLabel(
                        icon
                );

        check.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        check.setForeground(
                ORANGE
        );

        JLabel label =
                new JLabel(
                        text
                );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );

        label.setForeground(
                MUTED
        );

        panel.add(
                check
        );

        panel.add(
                label
        );

        return panel;
    }

    // -------------------------------------------------
    // FILTERS
    // -------------------------------------------------

    private JPanel createFilterPanel() {

        RoundedPanel filter =
                new RoundedPanel(
                        28,
                        Color.WHITE,
                        BORDER
                );

        filter.setLayout(
                new BoxLayout(
                        filter,
                        BoxLayout.Y_AXIS
                )
        );

        filter.setBorder(
                new EmptyBorder(
                        28,
                        32,
                        28,
                        32
                )
        );

        filter.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        300
                )
        );

        JPanel heading =
                new JPanel(
                        new BorderLayout()
                );

        heading.setOpaque(
                false
        );

        JLabel title =
                new JLabel(
                        "Where are you looking?"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        21
                )
        );

        title.setForeground(
                DARK
        );

        JLabel helper =
                new JLabel(
                        "Choose your Berlin location and optional filters"
                );

        helper.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        helper.setForeground(
                MUTED
        );

        JPanel headingText =
                new JPanel();

        headingText.setOpaque(
                false
        );

        headingText.setLayout(
                new BoxLayout(
                        headingText,
                        BoxLayout.Y_AXIS
                )
        );

        headingText.add(
                title
        );

        headingText.add(
                helper
        );

        heading.add(
                headingText,
                BorderLayout.WEST
        );

        filter.add(
                heading
        );

        filter.add(
                Box.createVerticalStrut(
                        24
                )
        );

        JPanel firstRow =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                18,
                                0
                        )
                );

        firstRow.setOpaque(
                false
        );

        boroughBox =
                new JComboBox<>();

        boroughBox.addItem(
                "All Boroughs"
        );

        for (String borough :
                BerlinLocations.getBoroughs()) {

            boroughBox.addItem(
                    borough
            );
        }

        neighbourhoodBox =
                new JComboBox<>();

        neighbourhoodBox.addItem(
                "All Neighbourhoods"
        );

        priceField =
                new JTextField();

        firstRow.add(
                createInputGroup(
                        "Borough",
                        boroughBox
                )
        );

        firstRow.add(
                createInputGroup(
                        "Neighbourhood",
                        neighbourhoodBox
                )
        );

        firstRow.add(
                createInputGroup(
                        "Maximum Price (€)",
                        priceField
                )
        );

        filter.add(
                firstRow
        );

        filter.add(
                Box.createVerticalStrut(
                        18
                )
        );

        JPanel secondRow =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                18,
                                0
                        )
                );

        secondRow.setOpaque(
                false
        );

        startDateField =
                new JTextField();

        endDateField =
                new JTextField();

        secondRow.add(
                createInputGroup(
                        "Available From (YYYY-MM-DD)",
                        startDateField
                )
        );

        secondRow.add(
                createInputGroup(
                        "Available Until (YYYY-MM-DD)",
                        endDateField
                )
        );

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                22
                        )
                );

        buttons.setOpaque(
                false
        );

        RoundedButton apply =
                new RoundedButton(
                        "Apply Filters",
                        ORANGE,
                        Color.WHITE
                );

        RoundedButton clear =
                new RoundedButton(
                        "Reset",
                        Color.WHITE,
                        DARK
                );

        clear.setBorderColor(
                BORDER
        );

        apply.addActionListener(
                e -> filterListings()
        );

        clear.addActionListener(
                e -> {

                    boroughBox.setSelectedIndex(
                            0
                    );

                    neighbourhoodBox
                            .removeAllItems();

                    neighbourhoodBox.addItem(
                            "All Neighbourhoods"
                    );

                    priceField.setText(
                            ""
                    );

                    startDateField.setText(
                            ""
                    );

                    endDateField.setText(
                            ""
                    );

                    refreshListings();
                }
        );

        buttons.add(
                apply
        );

        buttons.add(
                clear
        );

        secondRow.add(
                buttons
        );

        filter.add(
                secondRow
        );

        boroughBox.addActionListener(
                e ->
                        updateFilterNeighbourhoods()
        );

        return filter;
    }

    private JPanel createInputGroup(
            String label,
            JComponent input) {

        JPanel panel =
                new JPanel();

        panel.setOpaque(
                false
        );

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel fieldLabel =
                new JLabel(
                        label
                );

        fieldLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        fieldLabel.setForeground(
                DARK
        );

        fieldLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        styleInput(
                input
        );

        panel.add(
                fieldLabel
        );

        panel.add(
                Box.createVerticalStrut(
                        7
                )
        );

        panel.add(
                input
        );

        return panel;
    }

    private void styleInput(
            JComponent input) {

        input.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        input.setBackground(
                Color.WHITE
        );

        input.setPreferredSize(
                new Dimension(
                        230,
                        42
                )
        );

        input.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        input.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                BORDER
                        ),

                        new EmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
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

    // -------------------------------------------------
    // LISTINGS
    // -------------------------------------------------

    private void refreshListings() {

        if (listingsGrid == null) {
            return;
        }

        List<Listing> visibleListings =
                new ArrayList<>();

        for (Listing listing :
                DataStore.listings) {

            if (listing.isAvailable()) {

                visibleListings.add(
                        listing
                );
            }
        }

        displayListingCards(
                visibleListings,
                "No listings are currently available."
        );
    }

    private void displayListingCards(
            List<Listing> listings,
            String emptyMessage) {

        listingsGrid.removeAll();

        listingCountLabel.setText(
                listings.size()
                        + (
                        listings.size() == 1
                                ? " listing"
                                : " listings"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.anchor =
                GridBagConstraints.NORTHWEST;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        22,
                        22
                );

        if (listings.isEmpty()) {

            JLabel empty =
                    new JLabel(
                            emptyMessage
                    );

            empty.setFont(
                    new Font(
                            "SansSerif",
                            Font.PLAIN,
                            16
                    )
            );

            empty.setForeground(
                    MUTED
            );

            gbc.gridx =
                    0;

            gbc.gridy =
                    0;

            listingsGrid.add(
                    empty,
                    gbc
            );

        } else {

            int column =
                    0;

            int row =
                    0;

            for (Listing listing :
                    listings) {

                gbc.gridx =
                        column;

                gbc.gridy =
                        row;

                gbc.weightx =
                        0;

                gbc.weighty =
                        0;

                gbc.fill =
                        GridBagConstraints.NONE;

                listingsGrid.add(
                        createListingCard(
                                listing
                        ),
                        gbc
                );

                column++;

                if (column == 3) {

                    column =
                            0;

                    row++;
                }
            }

            // Extra horizontal space stays empty
            // instead of stretching the cards
            gbc.gridx =
                    3;

            gbc.gridy =
                    0;

            gbc.weightx =
                    1;

            gbc.fill =
                    GridBagConstraints.HORIZONTAL;

            listingsGrid.add(
                    Box.createHorizontalGlue(),
                    gbc
            );

            // Keep cards aligned at the top
            gbc.gridx =
                    0;

            gbc.gridy =
                    row + 1;

            gbc.weightx =
                    0;

            gbc.weighty =
                    1;

            gbc.fill =
                    GridBagConstraints.VERTICAL;

            listingsGrid.add(
                    Box.createVerticalGlue(),
                    gbc
            );
        }

        listingsGrid.revalidate();
        listingsGrid.repaint();
    }

    private JPanel createListingCard(
            Listing listing) {

        RoundedPanel card =
                new RoundedPanel(
                        22,
                        Color.WHITE,
                        BORDER
                );

        card.setLayout(
                new BorderLayout()
        );

        // Smaller card, but still large enough
        // to look like a property marketplace card
        Dimension cardSize =
                new Dimension(
                        305,
                        360
                );

        card.setPreferredSize(
                cardSize
        );

        card.setMinimumSize(
                cardSize
        );

        card.setMaximumSize(
                cardSize
        );

        JLabel image =
                createListingImage(
                        listing,
                        305,
                        175
                );

        card.add(
                image,
                BorderLayout.NORTH
        );

        JPanel information =
                new JPanel();

        information.setOpaque(
                false
        );

        information.setLayout(
                new BoxLayout(
                        information,
                        BoxLayout.Y_AXIS
                )
        );

        information.setBorder(
                new EmptyBorder(
                        14,
                        18,
                        16,
                        18
                )
        );

        JLabel location =
                new JLabel(
                        listing.getNeighbourhood()
                                + ", "
                                + listing.getBorough()
                );

        location.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        location.setForeground(
                ORANGE
        );

        location.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel title =
                new JLabel(
                        listing.getTitle()
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        title.setForeground(
                DARK
        );

        title.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel room =
                new JLabel(
                        listing.getRoomType()
                );

        room.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        room.setForeground(
                MUTED
        );

        room.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel price =
                new JLabel(
                        "€"
                                + listing.getPrice()
                                + " / month"
                );

        price.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        price.setForeground(
                DARK
        );

        price.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        RoundedButton details =
                new RoundedButton(
                        "View Details",
                        ORANGE,
                        Color.WHITE
                );

        details.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        details.addActionListener(
                e ->
                        showListingDetails(
                                listing
                        )
        );

        // Everything is deliberately aligned left
        information.add(
                location
        );

        information.add(
                Box.createVerticalStrut(
                        6
                )
        );

        information.add(
                title
        );

        information.add(
                Box.createVerticalStrut(
                        5
                )
        );

        information.add(
                room
        );

        information.add(
                Box.createVerticalStrut(
                        10
                )
        );

        information.add(
                price
        );

        information.add(
                Box.createVerticalStrut(
                        12
                )
        );

        information.add(
                details
        );

        card.add(
                information,
                BorderLayout.CENTER
        );

        return card;
    }

    private JLabel createListingImage(
            Listing listing,
            int width,
            int height) {

        JLabel imageLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER
                );

        imageLabel.setPreferredSize(
                new Dimension(
                        width,
                        height
                )
        );

        imageLabel.setOpaque(
                true
        );

        imageLabel.setBackground(
                new Color(
                        240,
                        238,
                        234
                )
        );

        if (listing.getImagePath()
                != null) {

            File file =
                    new File(
                            listing
                                    .getImagePath()
                    );

            if (file.exists()) {

                ImageIcon original =
                        new ImageIcon(
                                listing
                                        .getImagePath()
                        );

                Image scaled =
                        original
                                .getImage()
                                .getScaledInstance(
                                        width,
                                        height,
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
                "No image available"
        );

        imageLabel.setForeground(
                MUTED
        );

        imageLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        return imageLabel;
    }

    // -------------------------------------------------
    // FILTER LOGIC
    // -------------------------------------------------

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

            Double maxPrice =
                    null;

            LocalDate startDate =
                    null;

            LocalDate endDate =
                    null;

            if (Validator.notEmpty(
                    priceText
            )) {

                maxPrice =
                        Double.parseDouble(
                                priceText
                        );

                if (!Validator.validPrice(
                        maxPrice
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

            List<Listing> matches =
                    new ArrayList<>();

            for (Listing listing :
                    DataStore.listings) {

                if (!listing.isAvailable()) {
                    continue;
                }

                boolean locationMatches =
                        "All Boroughs"
                                .equals(
                                        borough
                                )

                                || listing
                                .getBorough()
                                .equals(
                                        borough
                                );

                if (!"All Neighbourhoods"
                        .equals(
                                neighbourhood
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
                        maxPrice == null

                                || listing
                                .getPrice()
                                <= maxPrice;

                boolean datesMatch =
                        startDate == null

                                || (
                                !startDate
                                        .isBefore(
                                                listing
                                                        .getStartDate()
                                        )

                                        && !endDate
                                        .isAfter(
                                                listing
                                                        .getEndDate()
                                        )
                        );

                if (locationMatches
                        && priceMatches
                        && datesMatch) {

                    matches.add(
                            listing
                    );
                }
            }

            displayListingCards(
                    matches,
                    "No listings match the selected filters."
            );

        } catch (
                NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid price."
            );

        } catch (
                DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Dates must use YYYY-MM-DD."
            );
        }
    }

    // -------------------------------------------------
    // LISTING DETAILS
    // -------------------------------------------------

    private void showListingDetails(
            Listing listing) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                18,
                                18
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        JLabel image =
                createListingImage(
                        listing,
                        430,
                        230
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
                                + listing.getNeighbourhood()
                                + ", "
                                + listing.getBorough()

                                + "<br><br><b>Price:</b> €"
                                + listing.getPrice()
                                + " per month"

                                + "<br><b>Available:</b> "
                                + listing.getStartDate()
                                + " to "
                                + listing.getEndDate()

                                + "<br><b>Room Type:</b> "
                                + listing.getRoomType()

                                + "<br><br>"
                                + listing.getDescription()

                                + "</html>"
                );

        information.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        panel.add(
                information,
                BorderLayout.CENTER
        );

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
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]
                );

        if (choice == 0) {

            applyForListing(
                    listing
            );

        } else if (choice == 1) {

            reportListing(
                    listing
            );
        }
    }

    private void applyForListing(
            Listing listing) {

        for (Application application :
                DataStore.applications) {

            if (application
                    .getStudentId()

                    == currentUser
                    .getUserId()

                    && application
                    .getListingId()

                    == listing
                    .getListingId()) {

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

                        currentUser
                                .getUserId(),

                        listing
                                .getListingId()
                );

        DataStore.applications.add(
                application
        );

        JOptionPane.showMessageDialog(
                this,
                "Application submitted successfully.\nStatus: Pending"
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

                    == currentUser
                    .getUserId()

                    && report
                    .getListingId()

                    == listing
                    .getListingId()) {

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

                        currentUser
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

    // -------------------------------------------------
    // LIST ROOM
    // -------------------------------------------------

    private JScrollPane createListRoomPage() {

        JPanel page =
                createSimplePage(
                        "Post your room",
                        "Create a sublet listing and make it available to students in Berlin."
                );

        RoundedPanel card =
                new RoundedPanel(
                        28,
                        Color.WHITE,
                        BORDER
                );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                new EmptyBorder(
                        35,
                        35,
                        35,
                        35
                )
        );

        card.setMaximumSize(
                new Dimension(
                        650,
                        230
                )
        );

        card.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel title =
                new JLabel(
                        "Have a room available?"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        title.setForeground(
                DARK
        );

        title.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel description =
                new JLabel(
                        "Add the location, price, dates, room type and a picture."
                );

        description.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        description.setForeground(
                MUTED
        );

        description.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        RoundedButton create =
                new RoundedButton(
                        "+ Create New Listing",
                        ORANGE,
                        Color.WHITE
                );

        create.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        create.addActionListener(
                e -> {

                    Lister listingUser =
                            new Lister(
                                    currentUser
                                            .getUserId(),

                                    currentUser
                                            .getName(),

                                    currentUser
                                            .getEmail(),

                                    currentUser
                                            .getPassword()
                            );

                    new ListerDashboard(
                            listingUser
                    );
                }
        );

        card.add(
                title
        );

        card.add(
                Box.createVerticalStrut(
                        8
                )
        );

        card.add(
                description
        );

        card.add(
                Box.createVerticalStrut(
                        26
                )
        );

        card.add(
                create
        );

        page.add(
                card
        );

        return createPageScrollPane(
                page
        );
    }

    // -------------------------------------------------
    // APPLICATIONS
    // -------------------------------------------------

    private JScrollPane createApplicationsPage() {

        applicationsContent =
                createSimplePage(
                        "My Applications",
                        "Track the status of your sublet applications."
                );

        return createPageScrollPane(
                applicationsContent
        );
    }

    private void refreshApplicationsPage() {

        applicationsContent.removeAll();

        addPageHeading(
                applicationsContent,
                "My Applications",
                "Track the status of your sublet applications."
        );

        int count =
                0;

        for (Application application :
                DataStore.applications) {

            if (application.getStudentId()
                    != currentUser
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

            RoundedPanel card =
                    createInfoCard();

            JLabel title =
                    createCardTitle(
                            listing
                                    .getTitle()
                    );

            JLabel location =
                    new JLabel(
                            listing
                                    .getNeighbourhood()
                                    + ", "
                                    + listing
                                    .getBorough()
                    );

            location.setForeground(
                    MUTED
            );

            JLabel status =
                    new JLabel(
                            "Status: "
                                    + application
                                    .getStatus()
                    );

            status.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            14
                    )
            );

            status.setForeground(
                    ORANGE
            );

            card.add(
                    title
            );

            card.add(
                    Box.createVerticalStrut(
                            5
                    )
            );

            card.add(
                    location
            );

            card.add(
                    Box.createVerticalStrut(
                            10
                    )
            );

            card.add(
                    status
            );

            applicationsContent.add(
                    card
            );

            applicationsContent.add(
                    Box.createVerticalStrut(
                            14
                    )
            );

            count++;
        }

        if (count == 0) {

            applicationsContent.add(
                    createEmptyMessage(
                            "You have not submitted any applications yet."
                    )
            );
        }

        applicationsContent.revalidate();
        applicationsContent.repaint();
    }

    // -------------------------------------------------
    // MY LISTINGS
    // -------------------------------------------------

    private JScrollPane createMyListingsPage() {

        myListingsContent =
                createSimplePage(
                        "My Listings",
                        "Manage the rooms you have posted."
                );

        return createPageScrollPane(
                myListingsContent
        );
    }

    private void refreshMyListingsPage() {

        myListingsContent.removeAll();

        addPageHeading(
                myListingsContent,
                "My Listings",
                "Manage the rooms you have posted."
        );

        int count =
                0;

        for (Listing listing :
                DataStore.listings) {

            if (listing
                    .getListerId()

                    != currentUser
                    .getUserId()) {

                continue;
            }

            RoundedPanel row =
                    new RoundedPanel(
                            22,
                            Color.WHITE,
                            BORDER
                    );

            row.setLayout(
                    new BorderLayout(
                            18,
                            0
                    )
            );

            row.setBorder(
                    new EmptyBorder(
                            14,
                            14,
                            14,
                            14
                    )
            );

            row.setMaximumSize(
                    new Dimension(
                            Integer.MAX_VALUE,
                            150
                    )
            );

            row.setAlignmentX(
                    Component.LEFT_ALIGNMENT
            );

            JLabel image =
                    createListingImage(
                            listing,
                            180,
                            120
                    );

            JPanel details =
                    new JPanel();

            details.setOpaque(
                    false
            );

            details.setLayout(
                    new BoxLayout(
                            details,
                            BoxLayout.Y_AXIS
                    )
            );

            JLabel title =
                    createCardTitle(
                            listing
                                    .getTitle()
                    );

            JLabel location =
                    new JLabel(
                            listing
                                    .getNeighbourhood()
                                    + ", "
                                    + listing
                                    .getBorough()
                    );

            location.setForeground(
                    MUTED
            );

            JLabel price =
                    new JLabel(
                            "€"
                                    + listing
                                    .getPrice()
                                    + " / month"
                    );

            price.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            14
                    )
            );

            details.add(
                    title
            );

            details.add(
                    Box.createVerticalStrut(
                            5
                    )
            );

            details.add(
                    location
            );

            details.add(
                    Box.createVerticalStrut(
                            5
                    )
            );

            details.add(
                    price
            );

            row.add(
                    image,
                    BorderLayout.WEST
            );

            row.add(
                    details,
                    BorderLayout.CENTER
            );

            myListingsContent.add(
                    row
            );

            myListingsContent.add(
                    Box.createVerticalStrut(
                            14
                    )
            );

            count++;
        }

        if (count == 0) {

            myListingsContent.add(
                    createEmptyMessage(
                            "You have not created any listings yet."
                    )
            );
        }

        myListingsContent.revalidate();
        myListingsContent.repaint();
    }

    // -------------------------------------------------
    // PROFILE / LOGOUT
    // -------------------------------------------------

    private void showProfile() {

        JOptionPane.showMessageDialog(
                this,

                "Name: "
                        + currentUser.getName()

                        + "\nEmail: "
                        + currentUser.getEmail()

                        + "\nRole: "
                        + currentUser.getRole(),

                "My Profile",

                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void logout() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (result
                == JOptionPane.YES_OPTION) {

            dispose();

            new LoginFrame();
        }
    }

    // -------------------------------------------------
    // PAGE HELPERS
    // -------------------------------------------------

    private JPanel createSimplePage(
            String title,
            String subtitle) {

        JPanel page =
                new JPanel();

        page.setLayout(
                new BoxLayout(
                        page,
                        BoxLayout.Y_AXIS
                )
        );

        page.setBackground(
                BACKGROUND
        );

        page.setBorder(
                new EmptyBorder(
                        45,
                        65,
                        60,
                        65
                )
        );

        addPageHeading(
                page,
                title,
                subtitle
        );

        return page;
    }

    private void addPageHeading(
            JPanel page,
            String title,
            String subtitle) {

        JLabel heading =
                new JLabel(
                        title
                );

        heading.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        32
                )
        );

        heading.setForeground(
                DARK
        );

        heading.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel description =
                new JLabel(
                        subtitle
                );

        description.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );

        description.setForeground(
                MUTED
        );

        description.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        page.add(
                heading
        );

        page.add(
                Box.createVerticalStrut(
                        7
                )
        );

        page.add(
                description
        );

        page.add(
                Box.createVerticalStrut(
                        28
                )
        );
    }

    private JScrollPane createPageScrollPane(
            JPanel page) {

        JScrollPane scrollPane =
                new JScrollPane(
                        page
                );

        scrollPane.setBorder(
                null
        );

        scrollPane.getViewport()
                .setBackground(
                        BACKGROUND
                );

        scrollPane
                .getVerticalScrollBar()
                .setUnitIncrement(
                        20
                );

        return scrollPane;
    }

    private RoundedPanel createInfoCard() {

        RoundedPanel card =
                new RoundedPanel(
                        22,
                        Color.WHITE,
                        BORDER
                );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                new EmptyBorder(
                        22,
                        24,
                        22,
                        24
                )
        );

        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        140
                )
        );

        card.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return card;
    }

    private JLabel createCardTitle(
            String text) {

        JLabel label =
                new JLabel(
                        text
                );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        label.setForeground(
                DARK
        );

        return label;
    }

    private JLabel createEmptyMessage(
            String message) {

        JLabel label =
                new JLabel(
                        message
                );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );

        label.setForeground(
                MUTED
        );

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return label;
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

    // -------------------------------------------------
    // CUSTOM UI COMPONENTS
    // -------------------------------------------------

    private class RoundedPanel
            extends JPanel {

        private int radius;
        private Color backgroundColor;
        private Color borderColor;

        public RoundedPanel(
                int radius,
                Color backgroundColor,
                Color borderColor) {

            this.radius =
                    radius;

            this.backgroundColor =
                    backgroundColor;

            this.borderColor =
                    borderColor;

            setOpaque(
                    false
            );
        }

        @Override
        protected void paintComponent(
                Graphics graphics) {

            Graphics2D g =
                    (Graphics2D)
                            graphics.create();

            g.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g.setColor(
                    backgroundColor
            );

            g.fillRoundRect(
                    0,
                    0,
                    getWidth() - 1,
                    getHeight() - 1,
                    radius,
                    radius
            );

            if (borderColor != null) {

                g.setColor(
                        borderColor
                );

                g.drawRoundRect(
                        0,
                        0,
                        getWidth() - 1,
                        getHeight() - 1,
                        radius,
                        radius
                );
            }

            g.dispose();

            super.paintComponent(
                    graphics
            );
        }
    }

    private class RoundedButton
            extends JButton {

        private Color backgroundColor;
        private Color textColor;
        private Color borderColor;

        public RoundedButton(
                String text,
                Color backgroundColor,
                Color textColor) {

            super(
                    text
            );

            this.backgroundColor =
                    backgroundColor;

            this.textColor =
                    textColor;

            setForeground(
                    textColor
            );

            setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            14
                    )
            );

            setBorder(
                    new EmptyBorder(
                            11,
                            20,
                            11,
                            20
                    )
            );

            setFocusPainted(
                    false
            );

            setContentAreaFilled(
                    false
            );

            setOpaque(
                    false
            );

            setCursor(
                    Cursor.getPredefinedCursor(
                            Cursor.HAND_CURSOR
                    )
            );

            addMouseListener(
                    new MouseAdapter() {

                        @Override
                        public void mouseEntered(
                                MouseEvent e) {

                            if (RoundedButton.this
                                    .backgroundColor
                                    .equals(
                                            ORANGE
                                    )) {

                                RoundedButton.this
                                        .backgroundColor =
                                        ORANGE_HOVER;

                                repaint();
                            }
                        }

                        @Override
                        public void mouseExited(
                                MouseEvent e) {

                            if (RoundedButton.this
                                    .backgroundColor
                                    .equals(
                                            ORANGE_HOVER
                                    )) {

                                RoundedButton.this
                                        .backgroundColor =
                                        ORANGE;

                                repaint();
                            }
                        }
                    }
            );
        }

        public void setBorderColor(
                Color borderColor) {

            this.borderColor =
                    borderColor;
        }

        @Override
        protected void paintComponent(
                Graphics graphics) {

            Graphics2D g =
                    (Graphics2D)
                            graphics.create();

            g.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g.setColor(
                    backgroundColor
            );

            g.fillRoundRect(
                    0,
                    0,
                    getWidth() - 1,
                    getHeight() - 1,
                    28,
                    28
            );

            if (borderColor != null) {

                g.setColor(
                        borderColor
                );

                g.drawRoundRect(
                        0,
                        0,
                        getWidth() - 1,
                        getHeight() - 1,
                        28,
                        28
                );
            }

            g.dispose();

            super.paintComponent(
                    graphics
            );
        }
    }
}