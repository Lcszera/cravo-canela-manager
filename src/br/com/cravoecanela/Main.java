package br.com.cravoecanela;

import br.com.cravoecanela.config.DatabaseInitializer;
import br.com.cravoecanela.view.MainFrame;
import javax.swing.SwingUtilities;

import br.com.cravoecanela.config.DatabaseInitializer;
import br.com.cravoecanela.view.MainFrame;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initializeDatabase();

        SwingUtilities.invokeLater(() -> new MainFrame());

    }

}