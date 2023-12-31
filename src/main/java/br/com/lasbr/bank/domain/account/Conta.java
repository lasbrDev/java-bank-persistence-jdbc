package br.com.lasbr.bank.domain.account;

import br.com.lasbr.bank.domain.costumer.Cliente;

import java.math.BigDecimal;
import java.util.Objects;

    public class Conta {

        private Integer numero;
        private BigDecimal saldo;
        private Cliente titular;
        private Boolean estaAtiva;

        public Conta(Integer numero, BigDecimal saldo, Cliente titular, Boolean estaAtiva) {
            this.numero = numero;
            this.saldo = saldo;
            this.titular = titular;
            this.estaAtiva = estaAtiva;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Conta conta = (Conta) object;
            return numero.equals(conta.numero);
        }

        @Override
        public int hashCode() {
            return Objects.hash(numero, saldo, titular);
        }

        @Override
        public String toString() {
            return "Conta{" +
                    "numero=" + numero +
                    ", saldo=" + saldo +
                    ", titular=" + titular +
                    '}';
        }

        public Integer getNumero() {
            return numero;
        }

        public BigDecimal getSaldo() {
            return saldo;
        }

        public Cliente getTitular() {
            return titular;
        }

        public boolean possuiSaldo() {
            return this.saldo.compareTo(BigDecimal.ZERO) != 0;
        }

        public void sacar(BigDecimal valor) {
            this.saldo = this.saldo.subtract(valor);
        }

        public Boolean getEstaAtiva() {
            return estaAtiva;
        }
    }
