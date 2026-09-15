import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JComboBox<String> roleBox;

    private final Color orange =
            new Color(240, 120, 30);

    public RegisterFrame() {

        setTitle("Berlin Sublet - Register");
        setSize(650, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(Color.WHITE);

        // Header
        JPanel header =
                new JPanel();

        header.setBackground(orange);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel logo =
                new JLabel("BERLIN SUBLET");

        logo.setForeground(Color.WHITE);

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

        // Form area
        JPanel formWrapper =
                new JPanel(
                        new GridBagLayout()
                );

        formWrapper.setBackground(Color.WHITE);

        JPanel form =
                new JPanel();

        form.setLayout(
                new BoxLayout(
                        form,
                        BoxLayout.Y_AXIS
                )
        );

        form.setBackground(Color.WHITE);

        form.setPreferredSize(
                new Dimension(
                        360,
                        380
                )
        );

        JLabel heading =
                new JLabel(
                        "Create your account"
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
                        "Find a sublet or list your own room."
                );

        subtitle.setForeground(Color.GRAY);

        subtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        nameField =
                new JTextField();

        emailField =
                new JTextField();

        passwordField =
                new JPasswordField();

        String[] roles = {
                "Student",
                "Lister"
        };

        roleBox =
                new JComboBox<>(roles);

        setFieldSize(nameField);
        setFieldSize(emailField);
        setFieldSize(passwordField);
        setFieldSize(roleBox);

        JButton registerButton =
                new JButton(
                        "Create Account"
                );

        registerButton.setBackground(
                orange
        );

        registerButton.setForeground(
                Color.WHITE
        );

        registerButton.setFocusPainted(
                false
        );

        registerButton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        registerButton.setMaximumSize(
                new Dimension(
                        360,
                        40
                )
        );

        JButton loginButton =
                new JButton(
                        "Already have an account? Login"
                );

        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setForeground(orange);
        loginButton.setFocusPainted(false);

        loginButton.setAlignmentX(
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
                "Name",
                nameField
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

        addField(
                form,
                "Account Type",
                roleBox
        );

        form.add(
                Box.createVerticalStrut(18)
        );

        form.add(registerButton);

        form.add(
                Box.createVerticalStrut(8)
        );

        form.add(loginButton);

        formWrapper.add(form);

        mainPanel.add(
                formWrapper,
                BorderLayout.CENTER
        );

        registerButton.addActionListener(
                e -> registerUser()
        );

        loginButton.addActionListener(e -> {

            dispose();

            new LoginFrame();
        });

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

    private void registerUser() {

        String name =
                nameField
                        .getText()
                        .trim();

        String email =
                emailField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        String role =
                (String)
                        roleBox
                                .getSelectedItem();

        if (!Validator.notEmpty(name)
                || !Validator.notEmpty(email)
                || !Validator.notEmpty(password)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all fields.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (!Validator.validEmail(email)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address.",
                    "Invalid Email",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (!Validator.validPassword(
                password
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password must contain at least 6 characters.",
                    "Invalid Password",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (DataStore.emailExists(email)) {

            JOptionPane.showMessageDialog(
                    this,
                    "An account with this email already exists.",
                    "Email Already Registered",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        int userId =
                DataStore.getNextUserId();

        User user;

        if ("Lister".equals(role)) {

            user =
                    new Lister(
                            userId,
                            name,
                            email,
                            password
                    );

        } else {

            user =
                    new Student(
                            userId,
                            name,
                            email,
                            password
                    );
        }

        DataStore.users.add(user);

        JOptionPane.showMessageDialog(
                this,
                "Account created successfully.",
                "Registration Complete",
                JOptionPane.INFORMATION_MESSAGE
        );

        dispose();

        new LoginFrame();
    }
}