package br.com.web;

import br.com.dao.CategoriaDAO;
import br.com.model.Categoria;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet responsável por gerenciar operações com Categorias.
 * Implementa operações CRUD (Create, Read, Update, Delete) através de requisições HTTP.
 * Mapeado para a rota /categorias.
 */
@WebServlet("/categorias")
public class CategoriaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    /**
     * Processa requisições GET para operações de leitura.
     * Parâmetro "acao" define o tipo de operação:
     * - listar: exibe todas as categorias
     * - novo: abre formulário para nova categoria
     * - editar: abre formulário para edição
     * - excluir: exclui uma categoria
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if (acao == null || acao.equals("listar")) {
            listar(request, response);
            return;
        }

        switch (acao) {
            case "novo":
                request.getRequestDispatcher("/WEB-INF/views/categoria-form.jsp").forward(request, response);
                break;
            case "editar":
                abrirEdicao(request, response);
                break;
            case "excluir":
                excluir(request, response);
                break;
            default:
                listar(request, response);
                break;
        }
    }

    /**
     * Processa requisições POST para operações de escrita (inserção/atualização).
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idCategoria = request.getParameter("idCategoria");
        String nomeCategoria = request.getParameter("nomeCategoria");
        String descricao = request.getParameter("descricao");

        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(nomeCategoria);
        categoria.setDescricao(descricao);

        if (idCategoria == null || idCategoria.isBlank()) {
            categoriaDAO.inserir(categoria);
        } else {
            categoria.setIdCategoria(Integer.parseInt(idCategoria));
            categoriaDAO.atualizar(categoria);
        }

        response.sendRedirect(request.getContextPath() + "/categorias");
    }

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Categoria> categorias = categoriaDAO.listar();
        request.setAttribute("listaCategorias", categorias);
        request.getRequestDispatcher("/WEB-INF/views/categoria-consulta.jsp").forward(request, response);
    }

    private void abrirEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Categoria categoria = categoriaDAO.buscarPorId(id);
        request.setAttribute("categoria", categoria);
        request.getRequestDispatcher("/WEB-INF/views/categoria-form.jsp").forward(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        categoriaDAO.excluir(id);
        response.sendRedirect(request.getContextPath() + "/categorias");
    }
}
