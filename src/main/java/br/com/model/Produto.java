package br.com.model;

/**
 * Entidade Produto.
 * Representa um produto cadastrado no sistema LojaWeb.
 * Estende a classe Categoria para reutilizar funcionalidades.
 */
public  class Produto extends Categoria {

    private Integer idProduto;
    private Integer idCategoria;
    private String nomeProduto;
    private Double estoqueProduto;
    private Double precoProduto;

    /**
     * Construtor padrão sem argumentos.
     */
    public Produto() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param idProduto ID único do produto
     * @param idCategoria ID da categoria associada
     * @param nomeProduto Nome do produto
     * @param estoqueProduto Quantidade em estoque
     * @param precoProduto Preço unitário do produto
     */
    public Produto(Integer idProduto, Integer idCategoria, String nomeProduto, Double estoqueProduto, Double precoProduto) {
        this.idProduto = idProduto;
        this.idCategoria = idCategoria;
        this.nomeProduto = nomeProduto;
        this.estoqueProduto = estoqueProduto;
        this.precoProduto = precoProduto;
    }

    public Integer getIdProduto() { return idProduto;}

    public void setIdProduto(Integer idProduto) { this.idProduto = idProduto;}

    public Integer getIdCategoria() { return idCategoria;}

    public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria;}

    public String getNomeProduto() { return nomeProduto; }

    public void setNomeProduto(String nomeProduto) {this.nomeProduto = nomeProduto;}

    public Double getEstoqueProduto() {return estoqueProduto;}

    public void setEstoqueProduto(Double estoqueProduto) {this.estoqueProduto = estoqueProduto;}

    public Double getPrecoProduto() {return precoProduto;}

    public void setPrecoProduto(Double precoProduto) {this.precoProduto = precoProduto;}

}