package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import model.Jogador;
import model.Time;
import persistence.GenericDao;
import persistence.JogadoresDao;
import persistence.TimeDao;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/jogador")
public class JogadorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public JogadorServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Time> times = listarTime(); 
            request.setAttribute("times", times);
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("erro", e.getMessage());
        }
        RequestDispatcher rd = request.getRequestDispatcher("Jogador.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmd = request.getParameter("botao");
        String codigo = request.getParameter("Timecodigo");
        String Id = request.getParameter("Id");
        String nome = request.getParameter("nome");
        String dataStr = request.getParameter("nascimento");
        String altura = request.getParameter("altura");
        String peso = request.getParameter("peso");

        String saida = "";
        String erro = "";
        List<Jogador> jogadores = new ArrayList<>();
        Jogador j = new Jogador();
Time t = new Time();
        if (!cmd.contains("Listar")) {
            j.setId(Integer.parseInt(Id));
        }


        if (!codigo.isEmpty()) {
            t.setCodigoTime(Integer.parseInt("1")) ; 
        }

        if (cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
            j.setNome(nome);
            j.setAltura(Float.parseFloat(altura));
            j.setPeso(Float.parseFloat(peso));

            try {
              
                if (!dataStr.isEmpty()) {
                    j.setDataNasc(Date.valueOf(dataStr)); 
                }
            } catch (IllegalArgumentException e) {
                erro = "Formato de data inválido. Use o formato AAAA-MM-DD.";
            }
        }

        try {
            if (cmd.contains("Cadastrar")) {
                cadastrarJogador(j);
                saida = "Jogador cadastrado com sucesso";
            } else if (cmd.contains("Alterar")) {
                alterarJogador(j);
                saida = "Jogador Alterado com sucesso";
            } else if (cmd.contains("Excluir")) {
                excluirJogador(j);
                saida = "Jogador excluído com sucesso";
            } else if (cmd.contains("Buscar")) {
                j = buscarJogador(j);
            } else if (cmd.contains("Listar")) {
                jogadores = listarJogadores();
            }
        } catch (SQLException | ClassNotFoundException e) {
            erro = e.getMessage();
        } finally {
            request.setAttribute("saida", saida);
            request.setAttribute("erro", erro);
            request.setAttribute("jogadores", jogadores);

            RequestDispatcher rd = request.getRequestDispatcher("Jogadores.jsp");
            rd.forward(request, response);
        }
    }


    private void cadastrarJogador(Jogador j) throws SQLException, ClassNotFoundException {
        if (j.getTime() == null) {
            throw new IllegalArgumentException("O jogador não possui um time associado.");
        }

        GenericDao gDao = new GenericDao();
        JogadoresDao jDao = new JogadoresDao(gDao);
        jDao.inserir(j);
    }


    private void alterarJogador(Jogador j) throws SQLException, ClassNotFoundException {
        GenericDao gDao = new GenericDao();
        JogadoresDao jDao = new JogadoresDao(gDao);
        jDao.atualizar(j);
    }

    private void excluirJogador(Jogador j) throws SQLException, ClassNotFoundException {
        GenericDao gDao = new GenericDao();
        JogadoresDao jDao = new JogadoresDao(gDao);
        jDao.excluir(j);
    }

    private Jogador buscarJogador(Jogador j) throws SQLException, ClassNotFoundException {
        GenericDao gDao = new GenericDao();
        JogadoresDao jDao = new JogadoresDao(gDao);
        return jDao.consultar(j);
    }

    private List<Jogador> listarJogadores() throws SQLException, ClassNotFoundException {
        GenericDao gDao = new GenericDao();
        JogadoresDao jDao = new JogadoresDao(gDao);
        return jDao.listar();
    }
    private List<Time> listarTime() throws SQLException, ClassNotFoundException {
        GenericDao gDao = new GenericDao();
        TimeDao tDao = new TimeDao(gDao);
        return tDao.listar();
    }
}
