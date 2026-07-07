package br.com.cravoecanela;
import br.com.cravoecanela.config.ConnectionFactory;
import br.com.cravoecanela.config.DatabaseInitializer;

public class Main {

    public static void main(String[] args) {

        DatabaseInitializer.initializeDatabase();

    }

    }   