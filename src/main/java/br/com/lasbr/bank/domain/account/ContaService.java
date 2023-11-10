package br.com.lasbr.bank.domain.account;

import java.util.HashSet;
import java.util.Set;

    public class ContaService {

        private Set<Conta> contas = new HashSet<>();

        public Set<Conta> listarContasAbertas() {
            return contas;
        }


    }
