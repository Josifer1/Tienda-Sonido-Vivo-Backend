package api_sonido_vivo.utilidades;

// Misma regla del dígito verificador que ya tienes en el frontend (validaciones.js),
// repetida aquí porque la API nunca debe confiar solo en lo que valida el navegador.
public final class ValidadorRun {

    private ValidadorRun() {
    }

    // El RUN va sin puntos ni guion, con 7 a 9 caracteres. Ejemplo: 123456785 o 1234564K
    public static boolean esValido(String run) {
        if (run == null) {
            return false;
        }
        String valor = run.trim().toUpperCase();
        if (!valor.matches("^\\d{6,8}[0-9K]$")) {
            return false;
        }

        String cuerpo = valor.substring(0, valor.length() - 1);
        char digitoVerificador = valor.charAt(valor.length() - 1);

        int suma = 0;
        int multiplicador = 2;
        for (int i = cuerpo.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(cuerpo.charAt(i)) * multiplicador;
            multiplicador = (multiplicador == 7) ? 2 : multiplicador + 1;
        }

        int resto = 11 - (suma % 11);
        char digitoEsperado;
        if (resto == 11) {
            digitoEsperado = '0';
        } else if (resto == 10) {
            digitoEsperado = 'K';
        } else {
            digitoEsperado = (char) ('0' + resto);
        }
        return digitoVerificador == digitoEsperado;
    }
}