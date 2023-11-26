package br.com.lasbr.bank.util;

import java.math.BigDecimal;

    public class SaldoFormatter {
        public static String formatarSaldo(BigDecimal saldo) {
            return String.format("R$ %.2f", (saldo != null) ? saldo : BigDecimal.ZERO);
        }
    }
