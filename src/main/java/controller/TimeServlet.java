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
        List<Time> time = new ArrayList<>();
        Time t = new Time();

        if (!cmd.contains("Listar")) {
            t.setCodigoTime(Integer.parseInt(codigo));
        }
        if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
            t.setNome(nome);
            t.setCidade(cidade);
        }

        try {
            if (cmd.contains("Cadastrar")) {
                cadastrarTime(t);
                saida = "Time cadastrado com sucesso";
            }
            if (cmd.contains("Alterar")) {
                alterarTime(t);
                saida = "Time Alterado com sucesso";
            }
            if (cmd.contains("Excluir")) {
                excluirTime(t);
                saida = "Time excluido com sucesso";
            }
            if (cmd.contains("Buscar")) {
                t = buscarTime(t);
            }
            if (cmd.contains("Listar")) {
                time = listarTime();
            }

        } catch (SQLException | ClassNotFoundException e) {
            erro = e.getMessage();
        } finally {
            request.setAttribute("saida", saida);
            request.setAttribute("erro", erro);
            request.setAttribute("Time", t);
            request.setAttribute("time", time);

            RequestDispatcher rd = request.getRequestDispatcher("Times.jsp");
            rd.forward(request, response);
        }

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
