package controller;

import fi.jyu.ohj2.jseppane.kirjasto.Paneelit;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Kirja;
import model.Kirjasto;

public class LisaysController {

    /**
     * Controller-luokan FXML- ja tavalliset attribuutit
     */
    @FXML private TextField lisaaNimi;
    @FXML private TextField lisaaTekija;
    @FXML private TextField lisaaIsbn;
    private Kirjasto kirjasto;

    /**
     * Alustaa lisäysnäkymän.
     * Hakee Kirjasto-instanssin käyttöön, jotta kirjoja voidaan lisätä järjestelmään.
     *
     */
    @FXML
    public void initialize() {

        kirjasto = Kirjasto.getInstance();
    }


    /**
     * Lisää uuden kirjan järjestelmään.
     * Metodi lukee syötetyt kentät, tarkistaa että ne eivät ole tyhjiä, luo uuden Kirja-olion ja lisää sen kirjastoon.
     * Metodi laukaisee varoitusikkunan, jos jokin syöttökentistä on tyhjä tai jos ISBN-tunniste ei ole 13 numeroa pitkä
     *
     */
    @FXML
    private void lisaaKirja() {

        String nimi = lisaaNimi.getText();
        String tekija = lisaaTekija.getText();
        String isbn = lisaaIsbn.getText();

        /// Tarkistaa sen, että kaikki kentät ovat täytetty
        if (nimi == null || nimi.trim().isEmpty() || tekija == null || tekija.trim().isEmpty() || isbn == null || isbn.trim().isEmpty()) {

            /// Varsinainen ponnahusikkuna
            Alert virheilmoitus1 = new Alert(Alert.AlertType.WARNING);
            virheilmoitus1.setTitle("Virhe");
            virheilmoitus1.setHeaderText(null);
            virheilmoitus1.setContentText("Täytä kaikki kentät!");
            virheilmoitus1.showAndWait();

            return;
        }

        /// Tarkistaa onko ISBN-tunnisteessa 13 numeroa
        if (isbn.trim().length() != 13) {

            Alert virheilmoitus2 = new Alert(Alert.AlertType.WARNING);
            virheilmoitus2.setTitle("Virhe");
            virheilmoitus2.setHeaderText(null);
            virheilmoitus2.setContentText("ISBN-tunnisteen täytyy olla 13 numeroa pitkä!");
            virheilmoitus2.showAndWait();

            return;
        }

            /// Luo uuden kirja-olion ja lisää sen tietomalliin
            Kirja uusiKirja = new Kirja(nimi.trim(), tekija.trim(), isbn.trim(), false, 0, null);
            kirjasto.lisaaKirja(uusiKirja);

            Paneelit.vaihdaPaneeli("kirjastoPaneeli.fxml");
        }

        /**
         * Metodi, joka yksinkertaisesti siirtää käyttäjän takaisin päänäkymään.
         */
        @FXML
        private void peruuta() {
            Paneelit.vaihdaPaneeli("kirjastoPaneeli.fxml");
        }

}