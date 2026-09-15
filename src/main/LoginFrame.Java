import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    private final Color orange =
            new Color(240, 120, 30);

    public LoginFrame() {

        setTitle("Berlin Sublet - Login");
        setSize(650, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                Color.WHITE
        );

        // Header
        JPanel header =
                new JPanel();

        header.setBackground(
                orange
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel logo =
                new JLabel(
                        "BERLIN SUBLET"
                );

        logo.setForeground(
                Color.WHITE
        );

        logo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        header.add(logo);

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        // Login form
        JPanel wrapper =
                new JPanel(
                        new GridBagLayout()
                );

        wrapper.setBackground(
                Color.WHITE
        );

        JPanel form =
                new JPanel();

        form.setLayout(
                new BoxLayout(
                        form,
                        BoxLayout.Y_AXIS
                )
        );

        form.setBackground(
                Color.WHITE
        );

        form.setPreferredSize(
                new Dimension(
                        360,
                        300
                )
        );

        JLabel heading =
                new JLabel(
                        "Welcome back"
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

        JLabel subtitle =
                new JLabel(
                        "Login to continue to Berlin Sublet."
                );

        subtitle.setForeground(
                Color.GRAY
        );

        subtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        emailField =
                new JTextField();

        passwordField =
                new JPasswordField();

        setFieldSize(
                emailField
        );

        setFieldSize(
                passwordField
        );

        JButton loginButton =
                new JButton(
                        "Login"
                );

        loginButton.setBackground(
                orange
        );

        loginButton.setForeground(
                Color.WHITE
        );

        loginButton.setFocusPainted(
                false
        );

        loginButton.setMaximumSize(
                new Dimension(
                        360,
                        40
                )
        );

        loginButton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JButton registerButton =
                new JButton(
                        "Create an Account"
                );

        registerButton.setBorderPainted(
                false
        );

        registerButton.setContentAreaFilled(
                false
        );

        registerButton.setForeground(
                orange
        );

        registerButton.setFocusPainted(
                false
        );

        registerButton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        form.add(heading);

        form.add(
                Box.createVerticalStrut(5)
        );

        form.add(subtitle);

        form.add(
                Box.createVerticalStrut(25)
        );

        addField(
                form,
                "Email",
                emailField
        );

        addField(
                form,
                "Password",
                passwordField
        );

        form.add(
                Box.createVerticalStrut(15)
        );

        form.add(loginButton);

        form.add(
                Box.createVerticalStrut(8)
        );

        form.add(registerButton);

        wrapper.add(form);

        mainPanel.add(
                wrapper,
                BorderLayout.CENTER
        );

        loginButton.addActionListener(
                e -> login()
        );

        registerButton.addActionListener(
                e -> {

                    dispose();

                    new RegisterFrame();
                }
        );

        add(mainPanel);

        setVisible(true);
    }

    private void setFieldSize(
            JComponent component) {

        component.setMaximumSize(
                new Dimension(
                        360,
                        34
                )
        );

        component.setPreferredSize(
                new Dimension(
                        360,
                        34
                )
        );

        component.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );
    }

    private void addField(
            JPanel panel,
            String label,
            JComponent field) {

        JLabel fieldLabel =
                new JLabel(label);

        fieldLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        panel.add(fieldLabel);

        panel.add(
                Box.createVerticalStrut(4)
        );

        panel.add(field);

        panel.add(
                Box.createVerticalStrut(12)
        );
    }

    private void login() {

        String email =
                emailField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        if (!Validator.notEmpty(email)
                || !Validator.notEmpty(password)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your email and password.",
                    "Login",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        User user =
                DataStore.findUser(
                        email,
                        password
                );

        if (user == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Incorrect email or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        dispose();

       if (user instanceof Admin) {

    new AdminDashboard(
            (Admin) user
    );

} else {

    new MarketplaceDashboard(
            user
    );
}
    }
}