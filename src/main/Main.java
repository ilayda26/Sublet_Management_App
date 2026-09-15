import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        Database.initialize();

        Database.loadData();

        // Saves everything when the program closes
        Runtime.getRuntime()
                .addShutdownHook(
                        new Thread(
                                Database::saveAll
                        )
                );

        SwingUtilities.invokeLater(
                LoginFrame::new
        );
    }
}