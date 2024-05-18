package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Jogador;
import model.Time;

public class JogadoresDao implements ICrud<Jogador> {
    private GenericDao gDao;

    public JogadoresDao(GenericDao gDao) {
        this.gDao = gDao;
    }

    @Override
    public void inserir(Jogador j) throws SQLException, ClassNotFoundException {
        Connection c = gDao.getConnection();
        String sql = "INSERT INTO jogador (nome, dataNasc, altura, peso, timeCodigo) VALUES (?,?,?,?,?)";

        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, j.getNome());
        ps.setString(2, j.getDataNasc().toString());
        ps.setFloat(3, j.getAltura());
        ps.setFloat(4, j.getPeso());
        ps.setInt(5, j.getTime().getCodigoTime());
        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public void atualizar(Jogador j) throws SQLException, ClassNotFoundException {
        Connection c = gDao.getConnection();
        String sql = "UPDATE jogador SET nome= ?, dataNasc =?, altura =?, peso =?, timeCodigo =? WHERE id = ?";

        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, j.getNome());
        ps.setString(2, j.getDataNasc().toString());
        ps.setFloat(3, j.getAltura());
        ps.setFloat(4, j.getPeso());
        ps.setInt(5, j.getTime().getCodigoTime());
        ps.setInt(6, j.getId());

       

        ps.close();
        c.close();
    }


    @Override
    public void excluir(Jogador j) throws SQLException, ClassNotFoundException {
        Connection c = gDao.getConnection();
        String sql = "DELETE FROM jogador WHERE id = ?";

        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, j.getId());

        ps.execute();
        ps.close();
        c.close();
    }

    @Override
    public Jogador consultar(Jogador j) throws SQLException, ClassNotFoundException {
        Connection c = gDao.getConnection();
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT j.Id AS ID_Jogador, j.nome AS nomeJogador, j.dataNasc AS Nascimento, j.altura AS Altura, j.peso AS Peso, ");
        sql.append("t.codigo AS codTime, t.nome AS nomeTime, t.cidade AS Cidade ");
        sql.append("FROM jogador j ");
        sql.append("INNER JOIN time t ON j.timeCodigo = t.codigoTime "); 
        sql.append("WHERE j.Id = ?");

        PreparedStatement ps = c.prepareStatement(sql.toString());
        ps.setInt(1, j.getId());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Time time = new Time();
            time.setCodigoTime(rs.getInt("codTime"));
            time.setNome(rs.getString("nomeTime"));
            time.setCidade(rs.getString("Cidade"));

            j.setNome(rs.getString("nomeJogador"));
            j.setDataNasc(LocalDate.parse(rs.getString("Nascimento")));
            j.setAltura(rs.getFloat("Altura"));
            j.setPeso(rs.getFloat("Peso"));
            j.setTime(time);
        }

        rs.close();
        ps.close();
        c.close();

        return j;
    }

    @Override
    public List<Jogador> listar() throws SQLException, ClassNotFoundException {
        List<Jogador> jogadores = new ArrayList<>();
        Connection c = gDao.getConnection();
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT j.Id AS ID_Jogador, j.nome AS nomeJogador, j.dataNasc AS Nascimento, j.altura AS Altura, j.peso AS Peso, ");
        sql.append("t.codigoTime AS codTime, t.nome AS nomeTime, t.cidade AS Cidade ");
        sql.append("FROM jogador j ");
        sql.append("INNER JOIN time t ON j.timeCodigo = t.codigoTime "); 


        PreparedStatement ps = c.prepareStatement(sql.toString());

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Jogador j = new Jogador();
            Time time = new Time();
            time.setCodigoTime(rs.getInt("codTime"));
            time.setNome(rs.getString("nomeTime"));
            time.setCidade(rs.getString("Cidade"));

            j.setId(rs.getInt("ID_Jogador"));
            j.setNome(rs.getString("nomeJogador"));
            j.setDataNasc(LocalDate.parse(rs.getString("Nascimento")));
            j.setAltura(rs.getFloat("Altura"));
            j.setPeso(rs.getFloat("Peso"));
            j.setTime(time);

            jogadores.add(j);
        }

        rs.close();
        ps.close();
        c.close();

        return jogadores;
    }
}
