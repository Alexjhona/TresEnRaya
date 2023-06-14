
package com.nunclear.app.modelo;

import lombok.Data;

@Data
public class ResultadoTO {
    private int id_resultado;
    private String nombre_partida;
    private String nombre_jugador1;
    private String nombre_jugador2;
    private String ganador;
    private int punto;
    private String estado;
}
