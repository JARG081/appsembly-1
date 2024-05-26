package appsembly.appsembly.utils;

import java.text.Normalizer;

public class StringUtils {
    public static String quitarTildes(String texto) {
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        return textoNormalizado.replaceAll("[^\\p{ASCII}]", "");
    }
}
