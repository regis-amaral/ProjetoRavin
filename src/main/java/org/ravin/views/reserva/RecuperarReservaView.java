package org.ravin.views.reserva;

import org.ravin.controllers.interfaces.IClienteController;
import org.ravin.controllers.interfaces.IMesaController;
import org.ravin.controllers.interfaces.IReservaController;
import org.ravin.models.Cliente;
import org.ravin.models.Mesa;
import org.ravin.models.Reserva;
import org.ravin.utils.exceptions.EntidadeNaoEncontradaException;

import static org.ravin.views.View.exibeDialogo;
import static org.ravin.views.View.solicitaEntradaDeDado;

public class RecuperarReservaView {

    static void listarReservas(IReservaController reservaController) {
        StringBuilder texto = new StringBuilder();
        for (Reserva reserva : reservaController.recuperarTodos()) {
            texto.append(reserva.toString()).append("\n");
        }
        exibeDialogo(texto.toString());
    }

    static void listarReservasPorCliente(IReservaController reservaController, IClienteController clienteController) {
        try {
            String cpf = solicitaEntradaDeDado("Informe o CPF do cliente para listar suas reservas:");
            Cliente cliente = clienteController.recuperarPorCpf(cpf);

            if (cliente != null) {
                StringBuilder texto = new StringBuilder();
                for (Reserva reserva : reservaController.recuperarReservasPorCliente(cliente)) {
                    texto.append(reserva.toString()).append("\n");
                }
                exibeDialogo(texto.toString());
            } else {
                exibeDialogo("Cliente não encontrado com o CPF informado!");
            }
        } catch (EntidadeNaoEncontradaException e) {
            exibeDialogo("Cliente não encontrado com o CPF informado!");
        }
    }

    static void listarReservasPorMesa(IReservaController reservaController, IMesaController mesaController) throws EntidadeNaoEncontradaException {
        String codigoMesa = solicitaEntradaDeDado("Informe o Código da mesa para listar suas reservas:");
        Mesa mesa = mesaController.recuperarPorCodigo(codigoMesa);

        if (mesa != null) {
            StringBuilder texto = new StringBuilder();
            for (Reserva reserva : reservaController.recuperarReservasPorMesa(mesa)) {
                texto.append(reserva.toString()).append("\n");
            }
            exibeDialogo(texto.toString());
        } else {
            exibeDialogo("Mesa não encontrada com o Código informado!");
        }
    }
}
