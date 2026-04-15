package controller;

import fi.jyu.ohj2.jseppane.kirjasto.Paneelit;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Kirja;
import model.Kirjasto;


public class PalautusController {

    /**
     * Controller-luokan FXML- ja tavalliset attribuutit
     */
    @FXML private ListView<String> kirjaTieto;
    private Kirjasto kirjasto;
    private Kirja kirja;

    /**
     * Alustaa palautusnäkymän.
     * Hakee Kirjasto-instanssin ja valitun kirjan, sekä näyttää kirjan tiedot käyttöliittymässä.
     */
    @FXML
    public void initialize() {

        kirjasto = Kirjasto.getInstance();
        kirja = Paneelit.getValittuKirja();

        /// Keskeyttää metodin, jos valittua kirjaa ei löydy
        if (kirja == null){

            return;
        }

        /// Näyttää kirjan tiedot, kuten nimi, tekijä ja ISBN-tunniste
        kirjaTieto.setItems(FXCollections.observableArrayList(kirja.getNimi() + " - " + kirja.getTekija() + " | ISBN: " + kirja.getIsbn()));
    }

    /**
     * Palauttaa valitun kirjan kirjastoon.
     * Tarkistaa ensin, että kirja on olemassa ja että se on lainassa, minkä jälkeen palauttaa valitun kirjan ja siirtyy takaisin päänäkymään.
     *
     */
    @FXML
    private void palautaKirja() {

        /// Tarkistaa sen, että kirja on valittu
        if (kirja == null){

            return;
        }

        /// Tarkistaa onko kirja lainassa
        if (!kirja.isLainassa()) {

            return;
        }

        /// Palauttaa kirjan
        kirjasto.palautaKirja(kirja);

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