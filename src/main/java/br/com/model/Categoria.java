package br.com.model;

/**
 * Entidade Categoria.
 * Representa uma categoria de produtos no sistema LojaWeb.
 */
public class Categoria {

    private Integer idCategoria;
    private String nomeCategoria;
    private String descricao;

    /**
     * Construtor padrão sem argumentos.
     */
    public Categoria() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param idCategoria ID único da categoria
     * @param nomeCategoria Nome descritivo da categoria
     * @param descricao Descrição adicional sobre a categoria
     */
    public Categoria(Integer idCategoria, String nomeCategoria, String descricao) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.descricao = descricao;
    }

    /**
     * Obtém o ID único da categoria.
     *
     * @return ID da categoria
     */
    public Integer getIdCategoria() {
        return idCategoria;
    }

    /**
     * Define o ID único da categoria.
     *
     * @param idCategoria ID a ser definido
     */
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * Obtém o nome da categoria.
     *
     * @return Nome da categoria
     */
    public String getNomeCategoria() {
        return nomeCategoria;
    }

    /**
     * Define o nome da categoria.
     *
     * @param nomeCategoria Nome a ser definido
     */
    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    /**
     * Obtém a descrição da categoria.
     *
     * @return Descrição
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição da categoria.
     *
     * @param descricao Descrição a ser definida
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
