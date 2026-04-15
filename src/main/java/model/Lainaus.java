package model;

public class Lainaus {

    /**
     * Lainaus-luokan keskeiset attribuutit
     */
    private String isbn;
    private String lainaaja;
    private String lainattu;
    private String palautettu;

    /**
     * Tyhjä lainaus-olio konstruktori
     */
    public Lainaus() {
    }

    /**
     * Lainaus-olion konstruktori, jolla on seuraavat ominaisuudet:
     *
     * @param isbn Lainatun kirjan ISBN-tunniste
     * @param lainaaja Henkilö, joka lainasi kirjan
     * @param lainattu Lainauksen päivämäärä
     * @param palautettu Palautuspäivämäärä
     */
    public Lainaus(String isbn, String lainaaja, String lainattu, String palautettu) {
        this.isbn = isbn;
        this.lainaaja = lainaaja;
        this.lainattu = lainattu;
        this.palautettu = palautettu;
    }


    /// Getterit. Metodit, jotka hakevat olion ominaisuuksia

    /**
     * Palauttaa kirjan ISBN-tunnisteen.
     *
     * @return ISBN-tunniste
     */
    public String getIsbn() {

        return isbn;
    }


    /**
     * Palauttaa lainaajan nimen.
     *
     * @return lainaajan nimi
     */
    public String getLainaaja() {

        return lainaaja;
    }


    /**
     * Palauttaa lainauspäivän.
     *
     * @return lainauspäivämäärä
     */
    public String getLainattu() {

        return lainattu;
    }

    /**
     * Palauttaa palautuspäivän.
     *
     * @return palautuspäivämäärä
     */
    public String getPalautettu() {

        return palautettu;
    }


    /// Setterit. Metodit, jotka säätää olion ominaisuuksia

    /**
     * Asettaa palautuspäivän.
     *
     * @param palautettu palautuspäivämäärä
     */
    public void setPalautettu(String palautettu) {

        this.palautettu = palautettu;
    }


    /**
     * Asettaa ISBN-tunnisteen.
     *
     * @param isbn uusi ISBN-tunniste
     */
    public void setIsbn(String isbn) {

        this.isbn = isbn;
    }
}