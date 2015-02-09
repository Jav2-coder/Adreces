/**
 *
 */
package net.jimenezjavier.Adreces;

import java.net.URL;

/**
 *
 * @author alumne1daw
 *
 */
public class Adreca {

    /**
     * Valor que marca la posició que ocupa cada
     * URL en el nostre programa.
     */
    private int id;

    /**
     * Direcció web que emmagatzema el nostre programa.
     */
    private URL adreca;

    /**
     * Funció encarregada d'afegir noves adreces url.
     *
     * @param identify Variable Integer encarregada de donar una
     * numeració a cada url que anem entrant.
     * @param adress Variable URL encarregada de passar les url
     * que volem emmagatzemar en el nostre programa.
     */
    public Adreca(final int identify, final URL adress) {

        setAdreca(adress);
        setId(identify);

    }
    /**
     * Mètode encarregat de recuperar una adreça.
     *
     * @return retorna una variable URL.
     */
    public final URL getAdreca() {
        return adreca;
    }

    /**
     * Mètode encarregat d'afegir noves adreces.
     *
     * @param adress Variable URL.
     */
    public final void setAdreca(final URL adress) {
        this.adreca = adress;
    }

    /**
     * Mètode encarregat de recuperar la posició
     * de la url que mostrem en el programa.
     *
     * @return retorna una variable Integer.
     */
    public final int getId() {
        return id;
    }

    /**
     * Mètode per afegir la posició de la URL.
     *
     * @param iD Variable Integer.
     */
    public final void setId(final int iD) {
        id = iD;
    }

}
