
package com.nunclear.app.dao;

import java.util.List;
import com.nunclear.app.modelo.ResultadoTO;

public interface ResultadoDaoI {

    List<ResultadoTO> listar();

    int create(ResultadoTO re);

    int update(ResultadoTO reup);

    int delete(ResultadoTO rede);
}
