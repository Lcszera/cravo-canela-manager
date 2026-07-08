package br.com.cravoecanela.entities;
import br.com.cravoecanela.enums.TipoProduto;

public class Produto {

    private Integer id;
    private String nome;
    private String descricao;
    private TipoProduto tipo;
    private Double valor;
    private Integer quantidade;
    private Integer estoqueMinimo;
    private Integer prateleira;
    private Integer coluna;
    private String palavrasChave;

    public Produto() {
    }

    public Produto(Integer id, String nome, String descricao, TipoProduto tipo, Double valor, Integer quantidade, Integer estoqueMinimo, Integer prateleira, Integer coluna, String palavrasChave) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipo = tipo;
        this.valor = valor;
        this.quantidade = quantidade;
        this.estoqueMinimo = estoqueMinimo;
        this.prateleira = prateleira;
        this.coluna = coluna;
        this.palavrasChave = palavrasChave;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public Integer getPrateleira() {
        return prateleira;
    }

    public Integer getColuna() {
        return coluna;
    }

    public String getPalavrasChave() {
        return palavrasChave;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setEstoqueMinimo(Integer estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public void setPrateleira(Integer prateleira) {
        this.prateleira = prateleira;
    }

    public void setColuna(Integer coluna) {
        this.coluna = coluna;
    }

    public void setPalavrasChave(String palavrasChave) {
        this.palavrasChave = palavrasChave;
    }

    @Override
    public String toString() {
        return nome;
    }

}
