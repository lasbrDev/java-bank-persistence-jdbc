package br.com.lasbr.bank.domain;

    public class RegraDeNegocioException extends RuntimeException {
        public RegraDeNegocioException(String mensagem) {
            super(mensagem);
        }
    }
