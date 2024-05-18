package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import model.Time;
import persistence.GenericDao;
import persistence.TimeDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/time")
public class TimeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public TimeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            carregarTimes(request);
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("erro", e.getMessage());
        }
        RequestDispatcher rd = request.getRequestDispatcher("Times.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmd = request.getParameter("botao");
        String codigo = request.getParameter("codigo");
        String nome = request.getParameter("nome");
        String cidade = request.getParameter("cidade");

        String saida = "";
        String erro = "";
        Time t = new Time();

        if (codigo != null && !codigo.isEmpty()) {
            t.setCodigoTime(Integer.parseInt(codigo));
        }
        if (nome != null) {
            t.setNome(nome);
        }
        if (cidade != null) {
            t.setCidade(cidade);
        }

        try {
            if ("Cadastrar".equals(cmd)) {
                cadastrarTime(t);
                saida = "Time cadastrado com sucesso";
            } else if ("Atualizar".equals(cmd)) {
                alterarTime(t);
                saida = "Time atualizado com sucesso";
            } else if ("Excluir".equals(cmd)) {
                excluirTime(t);
                saida = "Time excluído com sucesso";
            } else if ("Buscar".equals(cmd)) {
                t = buscarTime(t);
                request.setAttribute("Time", t);
            }
        } catch (SQLException | ClassNotFoundException e) {
            erro = e.getMessage();
        } finally {
            request.setAttribute("saida", saida);
            request.setAttribute("erro", erro);
            try {
                carregarTimes(request);
            } catch (SQLException | ClassNotFoundException e) {
                request.setAttribute("erro", e.getMessage());
            }
            RequestDispatcher rd = request.getRequestDispatcher("Times.jsp");
            rd.forward(request, response);
        }
    }

    private void carregarTimes(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        List<Time> times = listarTime();
        request.setAttribute("times", times);
    }

    private void cadastrarTime(Time t) throws SQLException, ClassNotFoundException {
        GenericDao gDao = new GenericDao();
        TimeDao tDao = new TimeDao(gDao);
        tDao.inserir(t);
    }

    private void alterarTime(Time t) throws SQLException, ClassNotFoundException {
        GenericDao gDao = new GenericDao();
        TimeDao tDao = new TimeDao(gDao);
        tDao.atualizar(t);
    }

    private void excluirTime(Time t) throws SQLException, ClassNotFoundException {
        GenericDao gDao = new GenericDao();
        TimeDao tDao = new TimeDao(gDao);
        tDao.excluir(t);
    }

    private Time buscarTime(Time t) throws SQLException, ClassNotFoundException {
        GenericDao gDao = new GenericDao();
        TimeDao tDao = new TimeDao(gDao);
        return tDao.consultar(t);
    }

    private List<Time> listarTime() throws SQLException, ClassNotFoundException {
        GenericDao gDao = new GenericDao();
        TimeDao tDao = new TimeDao(gDao);
        return tDao.listar();
    }
}
