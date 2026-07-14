package br.com.cravoecanela.view;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Cravo e Canela Manager");

        setSize(1000,700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setResizable(false);

        add(new CadastroProdutoPanel());

        setVisible(true);

    }

}