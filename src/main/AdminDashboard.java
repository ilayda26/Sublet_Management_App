import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    private Admin currentAdmin;

    private CardLayout cardLayout;
    private JPanel pagesPanel;

    private JPanel usersPanel;
    private JPanel listingsPanel;
    private JPanel reportsPanel;

    private final Color orange =
            new Color(240, 120, 30);

    public AdminDashboard(Admin admin) {

        this.currentAdmin = admin;

        setTitle("Berlin Sublet - Admin");
        setSize(1100, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        pagesPanel = new JPanel(cardLayout);

        pagesPanel.add(
                createUsersPage(),
                "USERS"
        );

        pagesPanel.add(
                createListingsPage(),
                "LISTINGS"
        );

        pagesPanel.add(
                createReportsPage(),
                "REPORTS"
        );

        JPanel root =
                new JPanel(
                        new BorderLayout()
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

        refreshUsers();
        refreshListings();
        refreshReports();

        setVisible(true);
    }

    private JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                orange
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        25,
                        15,
                        25
                )
        );

        JLabel title =
                new JLabel(
                        "BERLIN SUBLET - ADMIN"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        23
                )
        );

        title.setForeground(
                Color.WHITE
        );

        JPanel menu =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                8,
                                0
                        )
                );

        menu.setBackground(
                orange
        );

        JButton usersButton =
                createButton(
                        "Users"
                );

        JButton listingsButton =
                createButton(
                        "Listings"
                );

        JButton reportsButton =
                createButton(
                        "Reports"
                );

        JButton logoutButton =
                createButton(
                        "Logout"
                );

        usersButton.addActionListener(e -> {

            refreshUsers();

            cardLayout.show(
                    pagesPanel,
                    "USERS"
            );
        });

        listingsButton.addActionListener(e -> {

            refreshListings();

            cardLayout.show(
                    pagesPanel,
                    "LISTINGS"
            );
        });

        reportsButton.addActionListener(e -> {

            refreshReports();

            cardLayout.show(
                    pagesPanel,
                    "REPORTS"
            );
        });

        logoutButton.addActionListener(
                e -> logout()
        );

        menu.add(usersButton);
        menu.add(listingsButton);
        menu.add(reportsButton);
        menu.add(logoutButton);

        header.add(
                title,
                BorderLayout.WEST
        );

        header.add(
                menu,
                BorderLayout.EAST
        );

        return header;
    }

    private JButton createButton(
            String text) {

        JButton button =
                new JButton(text);

        button.setBackground(
                Color.WHITE
        );

        button.setForeground(
                orange
        );

        button.setFocusPainted(
                false
        );

        return button;
    }

    // -----------------------------
    // USERS
    // -----------------------------

    private JScrollPane createUsersPage() {

        usersPanel =
                new JPanel();

        usersPanel.setLayout(
                new BoxLayout(
                        usersPanel,
                        BoxLayout.Y_AXIS
                )
        );

        usersPanel.setBackground(
                Color.WHITE
        );

        usersPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        40,
                        50
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        usersPanel
                );

        scrollPane.setBorder(null);

        scrollPane
                .getVerticalScrollBar()
                .setUnitIncrement(18);

        return scrollPane;
    }

    private void refreshUsers() {

        usersPanel.removeAll();

        JLabel heading =
                createHeading(
                        "Manage Users"
                );

        usersPanel.add(heading);

        usersPanel.add(
                Box.createVerticalStrut(20)
        );

        int count = 0;

        for (User user :
                DataStore.users) {

            usersPanel.add(
                    createUserCard(
                            user
                    )
            );

            usersPanel.add(
                    Box.createVerticalStrut(12)
            );

            count++;
        }

        if (count == 0) {

            usersPanel.add(
                    new JLabel(
                            "No users found."
                    )
            );
        }

        usersPanel.revalidate();
        usersPanel.repaint();
    }

    private JPanel createUserCard(
            User user) {

        JPanel card =
                createRowPanel();

        JPanel information =
                new JPanel();

        information.setLayout(
                new BoxLayout(
                        information,
                        BoxLayout.Y_AXIS
                )
        );

        information.setBackground(
                Color.WHITE
        );

        JLabel name =
                new JLabel(
                        user.getName()
                );

        name.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        JLabel email =
                new JLabel(
                        user.getEmail()
                );

        JLabel role =
                new JLabel(
                        "Role: "
                                + user.getRole()
                );

        JLabel id =
                new JLabel(
                        "User ID: "
                                + user.getUserId()
                );

        information.add(name);
        information.add(email);
        information.add(role);
        information.add(id);

        card.add(
                information,
                BorderLayout.CENTER
        );

        if (!(user instanceof Admin)) {

            JButton deleteButton =
                    new JButton(
                            "Delete User"
                    );

            deleteButton.setBackground(
                    orange
            );

            deleteButton.setForeground(
                    Color.WHITE
            );

            deleteButton.setFocusPainted(
                    false
            );

            deleteButton.addActionListener(
                    e -> deleteUser(user)
            );

            card.add(
                    deleteButton,
                    BorderLayout.EAST
            );
        }

        return card;
    }

    private void deleteUser(
            User user) {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Delete "
                                + user.getName()
                                + "?",
                        "Delete User",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice !=
                JOptionPane.YES_OPTION) {

            return;
        }

        int userId =
                user.getUserId();

        // Remove listings created by the user
        DataStore.listings.removeIf(
                listing ->
                        listing.getListerId()
                                == userId
        );

        // Remove applications submitted by the user
        DataStore.applications.removeIf(
                application ->
                        application.getStudentId()
                                == userId
        );

        // Remove reports submitted by the user
        DataStore.reports.removeIf(
                report ->
                        report.getStudentId()
                                == userId
        );

        DataStore.users.remove(
                user
        );

        JOptionPane.showMessageDialog(
                this,
                "User deleted."
        );

        refreshUsers();
        refreshListings();
        refreshReports();
    }

    // -----------------------------
    // LISTINGS
    // -----------------------------

    private JScrollPane createListingsPage() {

        listingsPanel =
                new JPanel();

        listingsPanel.setLayout(
                new BoxLayout(
                        listingsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        listingsPanel.setBackground(
                Color.WHITE
        );

        listingsPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        40,
                        50
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        listingsPanel
                );

        scrollPane.setBorder(null);

        scrollPane
                .getVerticalScrollBar()
                .setUnitIncrement(18);

        return scrollPane;
    }

    private void refreshListings() {

        listingsPanel.removeAll();

        JLabel heading =
                createHeading(
                        "Manage Listings"
                );

        listingsPanel.add(heading);

        listingsPanel.add(
                Box.createVerticalStrut(20)
        );

        int count = 0;

        for (Listing listing :
                DataStore.listings) {

            listingsPanel.add(
                    createListingRow(
                            listing
                    )
            );

            listingsPanel.add(
                    Box.createVerticalStrut(12)
            );

            count++;
        }

        if (count == 0) {

            listingsPanel.add(
                    new JLabel(
                            "No listings found."
                    )
            );
        }

        listingsPanel.revalidate();
        listingsPanel.repaint();
    }

    private JPanel createListingRow(
            Listing listing) {

        JPanel card =
                createRowPanel();

        JPanel information =
                new JPanel();

        information.setLayout(
                new BoxLayout(
                        information,
                        BoxLayout.Y_AXIS
                )
        );

        information.setBackground(
                Color.WHITE
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

        JLabel price =
                new JLabel(
                        "€"
                                + listing.getPrice()
                                + " / month"
                );

        JLabel owner =
                new JLabel(
                        "Owner ID: "
                                + listing.getListerId()
                );

        information.add(title);
        information.add(location);
        information.add(price);
        information.add(owner);

        JButton deleteButton =
                new JButton(
                        "Remove Listing"
                );

        deleteButton.setBackground(
                orange
        );

        deleteButton.setForeground(
                Color.WHITE
        );

        deleteButton.setFocusPainted(
                false
        );

        deleteButton.addActionListener(
                e ->
                        deleteListing(
                                listing
                        )
        );

        card.add(
                information,
                BorderLayout.CENTER
        );

        card.add(
                deleteButton,
                BorderLayout.EAST
        );

        return card;
    }

    private void deleteListing(
            Listing listing) {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Remove this listing?",
                        "Remove Listing",
                        JOptionPane.YES_NO_OPTION
                );

        if (choice !=
                JOptionPane.YES_OPTION) {

            return;
        }

        int listingId =
                listing.getListingId();

        DataStore.applications.removeIf(
                application ->
                        application.getListingId()
                                == listingId
        );

        DataStore.reports.removeIf(
                report ->
                        report.getListingId()
                                == listingId
        );

        DataStore.listings.remove(
                listing
        );

        JOptionPane.showMessageDialog(
                this,
                "Listing removed."
        );

        refreshListings();
        refreshReports();
    }

    // -----------------------------
    // REPORTS
    // -----------------------------

    private JScrollPane createReportsPage() {

        reportsPanel =
                new JPanel();

        reportsPanel.setLayout(
                new BoxLayout(
                        reportsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        reportsPanel.setBackground(
                Color.WHITE
        );

        reportsPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        50,
                        40,
                        50
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        reportsPanel
                );

        scrollPane.setBorder(null);

        scrollPane
                .getVerticalScrollBar()
                .setUnitIncrement(18);

        return scrollPane;
    }

    private void refreshReports() {

        reportsPanel.removeAll();

        JLabel heading =
                createHeading(
                        "Manage Reports"
                );

        reportsPanel.add(heading);

        reportsPanel.add(
                Box.createVerticalStrut(20)
        );

        int count = 0;

        for (Report report :
                DataStore.reports) {

            reportsPanel.add(
                    createReportRow(
                            report
                    )
            );

            reportsPanel.add(
                    Box.createVerticalStrut(12)
            );

            count++;
        }

        if (count == 0) {

            reportsPanel.add(
                    new JLabel(
                            "No reports found."
                    )
            );
        }

        reportsPanel.revalidate();
        reportsPanel.repaint();
    }

    private JPanel createReportRow(
            Report report) {

        JPanel card =
                createRowPanel();

        JPanel information =
                new JPanel();

        information.setLayout(
                new BoxLayout(
                        information,
                        BoxLayout.Y_AXIS
                )
        );

        information.setBackground(
                Color.WHITE
        );

        JLabel title =
                new JLabel(
                        "Report #"
                                + report.getReportId()
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        JLabel listing =
                new JLabel(
                        "Listing ID: "
                                + report.getListingId()
                );

        JLabel student =
                new JLabel(
                        "Reported by User ID: "
                                + report.getStudentId()
                );

        JLabel reason =
                new JLabel(
                        "Reason: "
                                + report.getReason()
                );

        JLabel status =
                new JLabel(
                        "Status: "
                                + report.getStatus()
                );

        information.add(title);
        information.add(listing);
        information.add(student);
        information.add(reason);
        information.add(status);

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        buttons.setBackground(
                Color.WHITE
        );

        JButton resolveButton =
                new JButton(
                        "Resolve"
                );

        JButton deleteButton =
                new JButton(
                        "Delete"
                );

        resolveButton.setBackground(
                orange
        );

        resolveButton.setForeground(
                Color.WHITE
        );

        resolveButton.setFocusPainted(
                false
        );

        deleteButton.setFocusPainted(
                false
        );

        resolveButton.addActionListener(e -> {

            report.setStatus(
                    "Resolved"
            );

            refreshReports();
        });

        deleteButton.addActionListener(e -> {

            DataStore.reports.remove(
                    report
            );

            refreshReports();
        });

        buttons.add(
                resolveButton
        );

        buttons.add(
                deleteButton
        );

        card.add(
                information,
                BorderLayout.CENTER
        );

        card.add(
                buttons,
                BorderLayout.EAST
        );

        return card;
    }

    // -----------------------------
    // HELPERS
    // -----------------------------

    private JLabel createHeading(
            String text) {

        JLabel heading =
                new JLabel(text);

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

        return heading;
    }

    private JPanel createRowPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setMaximumSize(
                new Dimension(
                        950,
                        115
                )
        );

        panel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        220,
                                        220
                                )
                        ),

                        BorderFactory.createEmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        return panel;
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

            new LoginFrame();
        }
    }
}