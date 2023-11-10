package br.com.lasbr.bank.domain.account;

import br.com.lasbr.bank.domain.RegraDeNegocioException;
import br.com.lasbr.bank.domain.costumer.Cliente;

import java.math.BigDecimal;
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

        public void encerrar(Integer numeroDaConta) {
            var conta = buscarContaPorNumero(numeroDaConta);
            if (conta.possuiSaldo()) {
                throw new RegraDeNegocioException("Conta não pode ser encerrada pois ainda possui saldo!");
            }
        }

        private Conta buscarContaPorNumero(Integer numero) {
            return contas
                    .stream()
                    .filter(c -> c.getNumero() == numero)
                    .findFirst()
                    .orElseThrow(() -> new RegraDeNegocioException("Não existe conta cadastrada com esse número!"));
        }

        public BigDecimal consultarSaldo(Integer numeroDaConta) {
            var conta = buscarContaPorNumero(numeroDaConta);
            return conta.getSaldo();
        }

        public void realizarSaque(Integer numeroDaConta, BigDecimal valor) {
            var conta = buscarContaPorNumero(numeroDaConta);
            if (valor.compareTo(BigDecimal.ZERO) <= 0) {
                throw new RegraDeNegocioException("Valor do saque deve ser superior a zero!");
            }

            if (valor.compareTo(conta.getSaldo()) > 0) {
                throw new RegraDeNegocioException("Saldo insuficiente!");
            }

            conta.sacar(valor);
        }
    }
