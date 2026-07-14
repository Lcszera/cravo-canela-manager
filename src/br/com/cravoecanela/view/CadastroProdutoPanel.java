package br.com.cravoecanela.view;

import br.com.cravoecanela.enums.TipoProduto;

import javax.swing.*;
import java.awt.*;

public class CadastroProdutoPanel extends JPanel {

    private JTextField txtNome;
    private JTextField txtDescricao;
    private JComboBox<TipoProduto> cbTipo;
    private JTextField txtValor;
    private JTextField txtQuantidade;
    private JTextField txtEstoqueMinimo;
    private JTextField txtPrateleira;
    private JTextField txtColuna;
    private JTextField txtPalavrasChave;

    private JButton btnSalvar;

    public CadastroProdutoPanel() {

        setLayout(new BorderLayout());

        JPanel formulario = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNome = new JTextField(25);
        txtDescricao = new JTextField(25);
        cbTipo = new JComboBox<>(TipoProduto.values());
        txtValor = new JTextField();
        txtQuantidade = new JTextField();
        txtEstoqueMinimo = new JTextField();
        txtPrateleira = new JTextField();
        txtColuna = new JTextField();
        txtPalavrasChave = new JTextField();

        btnSalvar = new JButton("Salvar Produto");

        adicionarCampo(formulario, gbc,0,"Nome:",txtNome);
        adicionarCampo(formulario, gbc,1,"Descrição:",txtDescricao);
        adicionarCampo(formulario, gbc,2,"Tipo:",cbTipo);
        adicionarCampo(formulario, gbc,3,"Valor:",txtValor);
        adicionarCampo(formulario, gbc,4,"Quantidade:",txtQuantidade);
        adicionarCampo(formulario, gbc,5,"Estoque mínimo:",txtEstoqueMinimo);
        adicionarCampo(formulario, gbc,6,"Prateleira:",txtPrateleira);
        adicionarCampo(formulario, gbc,7,"Coluna:",txtColuna);
        adicionarCampo(formulario, gbc,8,"Palavras-chave:",txtPalavrasChave);

        gbc.gridx = 1;
        gbc.gridy = 9;

        formulario.add(btnSalvar, gbc);

        add(formulario, BorderLayout.NORTH);

    }

    private void adicionarCampo(JPanel panel,
                                GridBagConstraints gbc,
                                int linha,
                                String texto,
                                Component componente){

        gbc.gridx = 0;
        gbc.gridy = linha;

        panel.add(new JLabel(texto), gbc);

        gbc.gridx = 1;

        panel.add(componente, gbc);

    }

}