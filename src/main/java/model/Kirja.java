package model;

import java.util.ArrayList;
import java.util.List;

public class Kirja {

    /**
     * Kirja-luokan keskeiset String-luokan-attribuutit
     */
    private String nimi;
    private String tekija;
    private String isbn;
    private String nykyinenLainaaja;

    /**
     * Kirja-luokan muut attribuutit
     */
    private boolean lainassa = false;
    private int lainausMaara = 0;
    private List<Lainaus> lainausHistoria = new ArrayList<>();

    /**
     * Kirja-olion tyhjä konstruktori
     */
    public Kirja() {
    }


    /**
     * Kirja-olion konstruktori, jolla on seuraavat ominaisuudet:
     * @param nimi Kirjan nimi
     * @param tekija Kirjan tekijän sukunimi ja etunimi
     * @param isbn Kirjan ISBN-tunniste
     * @param lainassa Kirjan lainatilanne: onko lainassa vai ei?
     * @param lainausMaara Kuinka monta kertaa kirjaa oli lainattu
     * @param nykyinenLainaaja Kirjan nykyhetkinen lainaaja
     */
    public Kirja(String nimi, String tekija, String isbn, boolean lainassa, int lainausMaara, String nykyinenLainaaja) {
        this.nimi = nimi;
        this.tekija = tekija;
        this.isbn = isbn;
        this.lainassa = lainassa;
        this.lainausMaara = lainausMaara;
        this.nykyinenLainaaja = nykyinenLainaaja;
        this.lainausHistoria = new ArrayList<>();
    }


    /// Getterit. Metodit, jotka hakevat olion ominaisuuksia

    /**
     * Palauttaa kirjan nimen
     * @return Kirjan nimi
     */
    public String getNimi() {

        return nimi;
    }


    /**
     * Palauttaa kirjan tekijän
     * @return Kirjan tekijän nimi
     */
    public String getTekija() {

        return tekija;
    }


    /**
     * Palauttaa kirjan ISBN-tunnisteen
     * @return Kirjan ISBN-tunniste
     */
    public String getIsbn() {

        return isbn;
    }


    /**
     * Palauttaa lainausmäärän
     * @return Lainauskertojen määrä
     */
    public int getLainausMaara() {

        return lainausMaara;
    }


    /**
     * Palauttaa nykyisen lainaajan
     * @return Nykyinen lainaaja (tai null)
     */
    public String getNykyinenLainaaja() {

        return nykyinenLainaaja;
    }


    /**
     * Palauttaa lainaushistorian
     * @return Lista lainauksista
     */
    public List<Lainaus> getLainausHistoria() {

        return lainausHistoria;
    }


    /// Setterit. Metodit, jotka säätää olion ominaisuuksia

    /**
     * Asettaa kirjan ISBN-tunnisteen
     * @param isbn Uusi ISBN-tunniste
     */
    public void setIsbn(String isbn) {

        this.isbn = isbn;
    }


    /**
     * Asettaa lainassa-tilan
     * @param lainassa true, jos on lainassa
     */
    public void setLainassa(boolean lainassa) {

        this.lainassa = lainassa;
    }


    /**
     * Asettaa lainausmäärän
     * @param lainausMaara Uusi lainausmäärä
     */
    public void setLainausMaara(int lainausMaara) {

        this.lainausMaara = lainausMaara;
    }


    /**
     * Asettaa nykyisen lainaajan
     * @param nykyinenLainaaja Lainaajan nimi
     */
    public void setNykyinenLainaaja(String nykyinenLainaaja) {

        this.nykyinenLainaaja = nykyinenLainaaja;
    }


    /**
     * Asettaa lainaushistorian
     * @param lainausHistoria Lista lainauksista
     */
    public void setLainausHistoria(List<Lainaus> lainausHistoria) {

        this.lainausHistoria = lainausHistoria;
    }

    /**
     * Asettaa uuden nimen kirjalle
     * @param nimi Uusi nimi kirjalle
     */
    public void setNimi(String nimi) {

        this.nimi = nimi;
    }

    /**
     * Asettaa uuden tekijän kirjalle
     * @param tekija Uusi tekijä kirjalle
     */
    public void setTekija(String tekija) {

        this.tekija = tekija;
    }

    /// Tarkistajat. Metodit, jotka tarkistaa olion ominaisuuksia

    /**
     * Tarkistaa onko kirja lainassa
     * @return true jos lainassa, muuten false
     */
    public boolean isLainassa() {

        return lainassa;
    }


    /**
     * Metodin override, joka palauttaa kirjan tekstimuotoisen esityksen.
     * Esityksessä näkyy nimi, tekijä, ISBN-tunniste sekä lainatilanne.
     *
     * @return kirjan tiedot merkkijonona
     */
    @Override
    public String toString() {

        return nimi + " - " + tekija + " | ISBN: " + isbn + (lainassa ? " (LAINASSA: " + nykyinenLainaaja + ")" : " (VAPAA)");
    }
}