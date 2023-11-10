package br.com.lasbr.bank.domain.account;

import br.com.lasbr.bank.domain.RegraDeNegocioException;
import br.com.lasbr.bank.domain.costumer.Cliente;

import java.util.HashSet;
import java.util.Set;

    public class ContaService {

        private Set<Conta> contas = new HashSet<>();

        public Set<Conta> listarContasAbertas() {
            return contas;
        }

        public void abrir(DadosAberturaConta dadosDaConta) {
            var cliente = new Cliente(dadosDaConta.dadosCliente());
            var conta = new Conta(dadosDaConta.numero(), cliente);
            if (contas.contains(conta)) {
                throw new RegraDeNegocioException("Já existe outra conta aberta com o mesmo número!");
            }

            contas.add(conta);
        }
    }
