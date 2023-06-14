
package com.nunclear.app.dao;

import com.nunclear.app.conexion.ConnS;
import com.nunclear.app.modelo.ResultadoTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultadoDao implements ResultadoDaoI {

    private ConnS instance = ConnS.getInstance();
    private Connection conexion = instance.getConnection();
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public List<ResultadoTO> listar() {
        List<ResultadoTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM resultados";
        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ResultadoTO to = new ResultadoTO();
                to.setId_resultado(rs.getInt("id_resultado"));
                to.setNombre_partida(rs.getString("nombre_partida"));
                to.setNombre_jugador1(rs.getString("nombre_jugador1"));
                to.setNombre_jugador2(rs.getString("nombre_jugador2"));
                to.setGanador(rs.getString("ganador"));
                to.setPunto(rs.getInt("punto"));
                to.setEstado(rs.getString("estado"));
                lista.add(to);
                System.out.println("sefsefse" + to.getNombre_partida());
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public int create(ResultadoTO re) {
        int exec = 0;
        String sql = "INSERT INTO resultados (nombre_partida, nombre_jugador1, nombre_jugador2, ganador, punto, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, re.getNombre_partida());
            ps.setString(2, re.getNombre_jugador1());
            ps.setString(3, re.getNombre_jugador2());
            ps.setString(4, "");
            ps.setInt(5, 0);
            ps.setString(6, "Jugando");
            exec = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return exec;
    }

    @Override
    public int update(ResultadoTO reup) {
        int exec = 0;
        String sql = "UPDATE resultados SET ganador = ?, punto = ?, estado = ? WHERE nombre_partida = ?";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, reup.getGanador());
            ps.setInt(2, reup.getPunto());
            ps.setString(3, reup.getEstado());
            ps.setString(4, reup.getNombre_partida());
            exec = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return exec;
    }

    @Override
    public int delete(ResultadoTO rede) {
        int exec = 0;
        String sql = "DELETE FROM resultados WHERE nombre_partida = ?";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(1, rede.getNombre_partida());
            exec = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return exec;
    }
}
