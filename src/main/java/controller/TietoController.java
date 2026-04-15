package controller;

import fi.jyu.ohj2.jseppane.kirjasto.Paneelit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Kirja;
import model.Kirjasto;
import model.Lainaus;

import java.time.LocalDate;

public class TietoController {

    @FXML private ListView<String> kirjaTieto;
    @FXML private ListView<String> lainaTilanne;
    @FXML private ListView<String> palautusPaiva;
    @FXML private ListView<String> lainauksia;
    @FXML private ListView<String> lainausHistoria;
    @FXML private TextField uusiNimi;
    @FXML private TextField uusiTekija;
    @FXML private TextField uusiISBN;
    private Kirja kirja;

    /**
     * Alustaa yksittäisen kirja tietonäkymän.
     * Hakee valitun kirjan ja näyttää sen perustiedot, lainatilanteen, palautustiedot, lainausmäärän sekä lainaushistorian käyttöliittymässä.
     *
     */
    @FXML
    public void initialize() {

        kirja = Paneelit.getValittuKirja();

        /// Tarkistaa sen, että kirja on valittu
        if (kirja == null){

            return;
        }

        paivitaNakyma();
    }


    /**
     * Päivittää näkymän valitun kirjan tiedoilla.
     * Metodi asettaa kirjan perustiedot (nimi, tekijä, ISBN), lainatilanteen, palautustiedon (myöhästyminen mukaan lukien),
     * lainausmäärän ja lainaushistorian ListView-komponentteihin.
     *
     */
    private void paivitaNakyma() {
        /// Kirjan tiedot
        kirjaTieto.setItems(FXCollections.observableArrayList(kirja.getNimi() + " - " + kirja.getTekija() + " | ISBN: " + kirja.getIsbn()));
        lainaTilanne.setItems(FXCollections.observableArrayList(kirja.isLainassa() ? "LAINASSA: " + kirja.getNykyinenLainaaja() : "VAPAA"));
        palautusPaiva.setItems(FXCollections.observableArrayList(kirja.isLainassa() && kirja.getLainausHistoria() != null && !kirja.getLainausHistoria().isEmpty() ? (LocalDate.now().isAfter(LocalDate.parse(kirja.getLainausHistoria().get(kirja.getLainausHistoria().size() - 1).getLainattu()).plusMonths(1)) ? "MYÖHÄSSÄ" : "Palautus kesken") : "Ei lainassa"));
        lainauksia.setItems(FXCollections.observableArrayList("Lainattu: " + kirja.getLainausMaara() + " kertaa"));

        /// Lainaushistoria päivitetään
        ObservableList<String> historia = FXCollections.observableArrayList();

        /// Kirjan lainaustilanne
        if (kirja.getLainausHistoria() != null && !kirja.getLainausHistoria().isEmpty()) {

            for (Lainaus lainaus : kirja.getLainausHistoria()) {

                historia.add(lainaus.getLainaaja() + " | " + lainaus.getLainattu() + " -> " + (lainaus.getPalautettu() != null ? lainaus.getPalautettu() : "kesken"));
            }

        } else {

            historia.add("Ei lainauksia");
        }

        lainausHistoria.setItems(historia);
    }


    /**
     * Tallentaa käyttäjän tekemät muutokset valittuun kirjaan.
     * Metodi lukee syöttökenttien (nimi, tekijä, ISBN) arvot ja päivittää
     * kirjan tiedot, mikäli kentät eivät ole tyhjiä tai null-arvoisia.
     *
     */
    @FXML
    private void tallennaMuutos() {

        if (kirja == null){

            return;
        }

        /// Tallentaa uuden nimen
        if (uusiNimi.getText() != null && !uusiNimi.getText().trim().isEmpty()) {

            kirja.setNimi(uusiNimi.getText().trim());
        }

        /// Tallentaa uuden tekijän
        if (uusiTekija.getText() != null && !uusiTekija.getText().trim().isEmpty()) {

            kirja.setTekija(uusiTekija.getText().trim());
        }

        /// Tallentaa uuden ISBN-tunnisteen
        if (uusiISBN.getText() != null && !uusiISBN.getText().trim().isEmpty()) {

            String isbn = uusiISBN.getText().trim();

            ///Laukaisee varoitusikkunan, jos ISBN-tunniste on virheellinen
            if (isbn.length() != 13) {

                Paneelit.naytaVaroitus("Virhe", "ISBN-tunnisteen täytyy olla 13 numeroa pitkä");
                return;
            }

            kirja.setIsbn(isbn);
        }


        Kirjasto.getInstance().tallenna();

        /// Tyhjentää syöttäkentät
        uusiNimi.clear();
        uusiTekija.clear();
        uusiISBN.clear();

        paivitaNakyma();
    }


    /**
     * Metodi, joka yksinkertaisesti siirtää käyttäjän takaisin päänäkymään.
     */
    @FXML
    private void peruuta() {

        Paneelit.vaihdaPaneeli("kirjastoPaneeli.fxml");
    }
}