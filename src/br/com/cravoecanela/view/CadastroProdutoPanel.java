package br.com.cravoecanela.view;

import br.com.cravoecanela.dao.ProdutoDAO;
import br.com.cravoecanela.entities.Produto;
import br.com.cravoecanela.enums.TipoProduto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;
    private JTextField txtPesquisar;
    private JButton btnPesquisar;
    private Integer idProdutoSelecionado;
    private JButton btnSalvar;
    private JButton btnAtualizar;
    private JButton btnExcluir;

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
        txtPesquisar = new JTextField(20);
        btnPesquisar = new JButton("Pesquisar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");

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

        modeloTabela = new DefaultTableModel();

        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Tipo");
        modeloTabela.addColumn("Quantidade");
        modeloTabela.addColumn("Prateleira");
        modeloTabela.addColumn("Coluna");

        tabelaProdutos = new JTable(modeloTabela);

        JScrollPane scroll = new JScrollPane(tabelaProdutos);

        JPanel painelPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));

        painelPesquisa.add(new JLabel("Pesquisar:"));
        painelPesquisa.add(txtPesquisar);
        painelPesquisa.add(btnPesquisar);

        JPanel painelBotoes = new JPanel();

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);

        formulario.add(painelBotoes, gbc);

        tabelaProdutos.getSelectionModel().addListSelectionListener(e -> preencherFormulario());

        add(painelPesquisa, BorderLayout.SOUTH);

        add(scroll, BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> salvarProduto());
        btnPesquisar.addActionListener(e -> pesquisarProduto());

        btnSalvar.addActionListener(e -> salvarProduto());
        btnPesquisar.addActionListener(e -> pesquisarProduto());
        btnAtualizar.addActionListener(e -> atualizarProduto());
        btnExcluir.addActionListener(e -> excluirProduto());

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

    private void salvarProduto() {

        try {

            Produto produto = new Produto();

            produto.setNome(txtNome.getText());
            produto.setDescricao(txtDescricao.getText());
            produto.setTipo((TipoProduto) cbTipo.getSelectedItem());
            String valor = txtValor.getText().replace(",", ".");
            produto.setValor(Double.parseDouble(valor));
            produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            produto.setEstoqueMinimo(Integer.parseInt(txtEstoqueMinimo.getText()));
            produto.setPrateleira(Integer.parseInt(txtPrateleira.getText()));
            produto.setColuna(Integer.parseInt(txtColuna.getText()));
            produto.setPalavrasChave(txtPalavrasChave.getText());

            ProdutoDAO dao = new ProdutoDAO();

            dao.salvar(produto);

            carregarProdutos();

            JOptionPane.showMessageDialog(this,
                    "Produto cadastrado com sucesso!");

            limparCampos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Erro ao cadastrar produto.");

            e.printStackTrace();

        }

    }

    private void atualizarProduto() {

        if (idProdutoSelecionado == null) {

            JOptionPane.showMessageDialog(this,
                    "Selecione um produto na tabela.");

            return;

        }

        try {

            Produto produto = new Produto();

            produto.setId(idProdutoSelecionado);

            produto.setNome(txtNome.getText());
            produto.setDescricao(txtDescricao.getText());
            produto.setTipo((TipoProduto) cbTipo.getSelectedItem());

            String valor = txtValor.getText().replace(",", ".");
            produto.setValor(Double.parseDouble(valor));

            produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
            produto.setEstoqueMinimo(Integer.parseInt(txtEstoqueMinimo.getText()));
            produto.setPrateleira(Integer.parseInt(txtPrateleira.getText()));
            produto.setColuna(Integer.parseInt(txtColuna.getText()));
            produto.setPalavrasChave(txtPalavrasChave.getText());

            ProdutoDAO dao = new ProdutoDAO();

            dao.atualizar(produto);

            carregarProdutos();

            limparCampos();

            idProdutoSelecionado = null;

            JOptionPane.showMessageDialog(this,
                    "Produto atualizado com sucesso!");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Erro ao atualizar produto.");

            e.printStackTrace();

        }

    }

    private void limparCampos() {

        txtNome.setText("");
        txtDescricao.setText("");
        txtValor.setText("");
        txtQuantidade.setText("");
        txtEstoqueMinimo.setText("");
        txtPrateleira.setText("");
        txtColuna.setText("");
        txtPalavrasChave.setText("");

        cbTipo.setSelectedIndex(0);

        txtNome.requestFocus();

    }

    private void carregarProdutos() {

        modeloTabela.setRowCount(0);

        ProdutoDAO dao = new ProdutoDAO();

        for (Produto p : dao.listarTodos()) {

            modeloTabela.addRow(new Object[]{

                    p.getId(),
                    p.getNome(),
                    p.getTipo(),
                    p.getQuantidade(),
                    p.getPrateleira(),
                    p.getColuna()

            });

        }

    }

    private void pesquisarProduto() {

        String filtro = txtPesquisar.getText().trim();

        modeloTabela.setRowCount(0);

        ProdutoDAO dao = new ProdutoDAO();

        if (filtro.isEmpty()) {

            carregarProdutos();
            return;

        }

        for (Produto p : dao.pesquisar(filtro)) {

            modeloTabela.addRow(new Object[]{

                    p.getId(),
                    p.getNome(),
                    p.getTipo(),
                    p.getQuantidade(),
                    p.getPrateleira(),
                    p.getColuna()

            });

        }

    }

    private void preencherFormulario() {

        int linha = tabelaProdutos.getSelectedRow();

        if (linha == -1) {
            return;
        }

        idProdutoSelecionado = (Integer) modeloTabela.getValueAt(linha, 0);

        ProdutoDAO dao = new ProdutoDAO();

        Produto produto = dao.buscarPorId(idProdutoSelecionado);

        txtNome.setText(produto.getNome());
        txtDescricao.setText(produto.getDescricao());
        cbTipo.setSelectedItem(produto.getTipo());
        txtValor.setText(String.valueOf(produto.getValor()));
        txtQuantidade.setText(String.valueOf(produto.getQuantidade()));
        txtEstoqueMinimo.setText(String.valueOf(produto.getEstoqueMinimo()));
        txtPrateleira.setText(String.valueOf(produto.getPrateleira()));
        txtColuna.setText(String.valueOf(produto.getColuna()));
        txtPalavrasChave.setText(produto.getPalavrasChave());

        tabelaProdutos.clearSelection();

    }

    private void excluirProduto() {

        if (idProdutoSelecionado == null) {

            JOptionPane.showMessageDialog(this,
                    "Selecione um produto na tabela.");

            return;
        }

        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir este produto?",
                "Confirmação",
                JOptionPane.YES_NO_OPTION
        );

        if (resposta == JOptionPane.YES_OPTION) {

            ProdutoDAO dao = new ProdutoDAO();

            dao.excluir(idProdutoSelecionado);

            carregarProdutos();

            limparCampos();

            idProdutoSelecionado = null;

            JOptionPane.showMessageDialog(this,
                    "Produto excluído com sucesso!");

        }

    }


}