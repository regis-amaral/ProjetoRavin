package views.cliente;

import controllers.interfaces.IClienteController;
import models.Cliente;
import utils.DateUtils;

import java.util.Date;

import static views.View.exibeDialogo;
import static views.View.solicitaEntradaDeDado;
import static views.cliente.SubmenuCliente.imprimeCliente;

public class AtualizarClienteView {
    static void atualizar(IClienteController clienteController) {

        // TODO ver se existe alguma maneira mais simples de mostrar submenus

        try {
            String cpf = solicitaEntradaDeDado("Informe o CPF do cliente que deseja alterar:");
            Cliente cliente = clienteController.recuperarPorCpf(cpf);

            if (cliente != null) {
                cliente.setNome(solicitaEntradaDeDado("Nome:", cliente.getNome()));
                cliente.setTelefone(solicitaEntradaDeDado("Telefone:", cliente.getTelefone()));
                cliente.setEndereco(solicitaEntradaDeDado("Endereço: ", cliente.getEndereco()));
                cliente.setCpf(solicitaEntradaDeDado("CPF:", cliente.getCpf()));
                cliente.setNascimento(DateUtils.stringToDate(solicitaEntradaDeDado("Data de nascimento: \nFormato: dd/mm/yyyy", DateUtils.dateToString(cliente.getNascimento()))));
                cliente.setObservacao(solicitaEntradaDeDado("Observação", cliente.getObservacao()));
                cliente.setAlergias(solicitaEntradaDeDado("Alergias: ", cliente.getAlergias()));
                boolean vip = solicitaEntradaDeDado("VIP?\n 0 - Não \n 1 - Sim", cliente.isVip() ? "1" : "0").equals("1") ? true : false;
                cliente.setVip(vip);
                boolean ativo = solicitaEntradaDeDado("Ativo?\n 0 - Não \n 1 - Sim", cliente.getAtivo() ? "1" : "0").equals("1") ? true : false;
                cliente.setAtivo(ativo);
                cliente.setAlteradoEm(new Date());
                cliente.setAlteradoPor(null);
                clienteController.atualizar(cliente);
                exibeDialogo("Cliente atualizado com sucesso!");
                imprimeCliente(clienteController.recuperarPorCpf(cliente.getCpf()));
            } else {
                exibeDialogo("Cliente não encontrado!");
            }
        } catch (Exception e) {
            exibeDialogo("Dado informado inválido!\nCadastro não finalizado...");
            e.printStackTrace();
        }
    }
}
