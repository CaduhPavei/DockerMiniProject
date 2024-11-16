package senac.dockerMiniProject.entities.enums;

import java.util.Arrays;

public enum Sexo {
    NÃO_INFORMADO,
    MASCULINO,
    FEMININO,
    OUTROS;

    public static boolean isValid(Sexo sexo) {
        return sexo != null && Arrays.asList(values()).contains(sexo);
    }
}
