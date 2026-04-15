package controller;

import fi.jyu.ohj2.jseppane.kirjasto.Paneelit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import model.Kirja;
import model.Lainaus;

import java.time.LocalDate;

public class TietoController {

    @FXML private ListView<String> kirjaTieto;
    @FXML private ListView<String> lainaTilanne;
    @FXML private ListView<String> palautusPaiva;
    @FXML private ListView<String> lainauksia;
    @FXML private ListView<String> lainausHistoria;
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

        }

        else {

            historia.add("Ei lainauksia");
        }


        lainausHistoria.setItems(historia);
    }


    /**
     * Metodi, joka yksinkertaisesti siirtää käyttäjän takaisin päänäkymään.
     */
    @FXML
    private void peruuta() {

        Paneelit.vaihdaPaneeli("kirjastoPaneeli.fxml");
    }
}