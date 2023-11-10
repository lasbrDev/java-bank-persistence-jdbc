package br.com.lasbr.bank.domain.client;

import java.util.Objects;

    public class Clliente {

        private String nome;
        private String cpf;
        private String email;

        public Clliente(String nome, String cpf, String email) {
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
            Clliente clliente = (Clliente) object;
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
