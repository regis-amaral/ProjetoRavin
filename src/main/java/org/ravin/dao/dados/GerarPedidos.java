package org.ravin.dao.dados;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ravin.models.Pedido;
import org.ravin.models.Produto;
import org.ravin.utils.enums.StatusPreparo;

public class GerarPedidos {

    private static List<Produto> produtos = GerarProdutos.montaLista();

    public static List<Pedido> montaLista() {
        List<Pedido> listaPedidos = new ArrayList<>();

        StatusPreparo[] statusValues = StatusPreparo.values(); // Cria um array de ENUMS que serão percorridos no for

        // for loop do tamanho do Array<Produtos>
        for (int i = 1; i <= produtos.size(); i++) {
            Produto produto = produtos.get(i - 1); // correção de índice
            int quantidade = i; // quantidade sobre a cada loop
            // dentro dos status possíveis, vai percorrer o array até seu tamanho máximo
            StatusPreparo status = statusValues[i % statusValues.length];

            Pedido pedido = new Pedido(produto, quantidade, status);
            pedido.setId(i);
            pedido.setCodigo("COD" + i);
            pedido.setDataHoraSolicitacao(new Timestamp(System.currentTimeMillis()));
            pedido.setDataHoraInicioPreparo(new Timestamp(System.currentTimeMillis()));
            pedido.setTempoPreparoRestante(Duration.ofMinutes(i * 10));
            pedido.setObservacao("Observação " + i);
            pedido.setCriadoEm(new Date());
            pedido.setCriadoPor("Funcionario " + i);
            pedido.setAlteradoEm(new Date());
            pedido.setAlteradoPor("Funcionario " + i);

            listaPedidos.add(pedido);
        }

        return listaPedidos;
    }

}
