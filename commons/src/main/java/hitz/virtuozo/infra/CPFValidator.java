package hitz.virtuozo.infra;

import hitz.virtuozo.infra.api.Validator;

public class CPFValidator extends Validator<CPFValidator, String> {

  private static final int tamanho = 11;

  private static final CPFValidator instance = new CPFValidator();

  private CPFValidator() {
    super();
  }

  public static CPFValidator get() {
    return instance;
  }

  public boolean delegateValidation(String value) {
    value = CPFFormat.get().unformat(value);

    if (value == null || value.length() != tamanho || this.isPadrao(value)) {
      return false;
    }

    return this.getDigitoVerificador(value.substring(0, 9)).equals(value.substring(9, tamanho));
  }

  private boolean isPadrao(String cpf) {
    return cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666")
        || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999");
  }

  private String getDigitoVerificador(String value) {
    Integer segundoDigito;
    int soma = 0;
    int peso = 10;

    for (int i = 0; i < value.length(); i++) {
      soma += Integer.parseInt(value.substring(i, i + 1)) * peso--;
    }

    Integer primeiroDigito = tamanho - (soma % tamanho);
    if (soma % tamanho == 0 | soma % tamanho == 1) {
      primeiroDigito = new Integer(0);
    }

    soma = 0;
    peso = tamanho;

    for (int i = 0; i < value.length(); i++) {
      soma += Integer.parseInt(value.substring(i, i + 1)) * peso--;
    }

    soma += primeiroDigito.intValue() * 2;

    segundoDigito = tamanho - (soma % tamanho);
    if (soma % tamanho == 0 | soma % tamanho == 1) {
      segundoDigito = new Integer(0);
    }

    return primeiroDigito.toString() + segundoDigito.toString();
  }
}