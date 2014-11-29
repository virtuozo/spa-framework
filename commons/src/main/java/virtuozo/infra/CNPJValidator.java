package virtuozo.infra;

import virtuozo.infra.api.Validator;

public class CNPJValidator extends Validator<CNPJValidator, String> {

  private static final CNPJValidator instance = new CNPJValidator();

  private CNPJValidator() {
    super();
  }

  public static CNPJValidator get() {
    return instance;
  }

  public boolean delegateValidation(String cnpj) {
    cnpj = CNPJFormat.get().unformat(cnpj);

    if (cnpj == null) {
      return false;
    }

    int soma = 0;
    String cnpj_calc = cnpj.substring(0, 12);
    char[] chr_cnpj = cnpj.toCharArray();

    // Primeira parte
    for (int i = 0; i < 4; i++) {
      if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
        soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
      }
    }

    for (int i = 0; i < 8; i++) {
      if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
        soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
      }
    }

    int dig = 11 - (soma % 11);
    cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

    // Segunda parte
    soma = 0;
    for (int i = 0; i < 5; i++) {
      if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
        soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
      }
    }

    for (int i = 0; i < 8; i++) {
      if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
        soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
      }
    }

    dig = 11 - (soma % 11);
    cnpj_calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

    return cnpj.equals(cnpj_calc);
  }
}