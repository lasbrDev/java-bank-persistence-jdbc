package br.com.lasbr.bank.domain.costumer;

import java.util.Objects;

    public class Cliente {

        private String nome;
        private String cpf;
        private String email;

        public Cliente(String nome, String cpf, String email) {
            this.nome = nome;
            this.cpf = cpf;
            this.email = email;
        }

        public String getNome() {
            return nome;
        }

        public String getCpf() {
            return cpf;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Cliente clliente = (Cliente) object;
            return cpf.equals(clliente.cpf);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nome, cpf, email);
        }

        @Override
        public String toString() {
            return "Clliente{" +
                    "nome='" + nome + '\'' +
                    ", cpf='" + cpf + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
