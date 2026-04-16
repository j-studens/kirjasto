# Kirjasto-sovellus - käyttöohje

JavaFX SceneBuilderilla toteutettu kirjastosovellus,
jolla käyttäjä voi hallita kirjoja ja niiden lainauksia. 
Sovellus tallentaa tiedot JSON-tiedostoon.

## Toiminnallisuus 1 - Päänäkymä
![](/kuvat/toiminallisuus_kirjastanakyma.png)

Kaikki kirjaston kirjat näkyvät listana. Käyttäjän tulee valita jokin kirja ennen
kuin soveltaa siihen toiminnon.

**Toiminnot:**
- Siirtyminen muihin näkymiin:
    - Lisää kirja
    - Lainaa kirja
    - Palauta kirja
    - Näytä tiedot
- Kirjoja voi hakea hakukentällä syöttämällä hakusanan
- Kirjan voi myös poistaa järjestelmästä

---

## Toiminnallisuus 2 - Kirjahaku
![](/kuvat/toiminallisuus_kirjasto.png)
Hakukenttä suodattaa kirjoja.

**Toiminnot:**
- Haku toimii hakusanan perusteella
- Kirjaston lista näyttää vain hakua vastaavat kirjat
- Tyhjällä haulla näytetään kaikki kirjat (oletus)

---

## Toiminnallisuus 3 - Kirjan tiedot
![](/kuvat/toiminallisuus_tiedot.png)

Näyttää valitun kirjan tiedot.

**Näytettävät tiedot:**
- Kirjan nimi, tekijä ja ISBN
- Lainatilanne (vapaa / lainassa)
- Nykyinen lainaaja
- Lainauskertojen määrä
- Koko lainaushistoria

**Toiminnot:**
- Peruuta-näppäin, jolla käyttäjä voi siirtyä takaisin päänäkymään

---

## Toiminnallisuus 4 - Lainaus
![](/kuvat/toiminallisuus_lainaa.png)

Käyttäjä lainaa kirjan syöttämällä kaikki tarvittavat tiedot.

**Toiminnot:**
- Käyttäjä syöttää lainaajan nimen
- Sovellus tarkistaa sen, että kirja ei ole lainassa
- Kirja merkitään lainatuksi
- Lainaustiedot tallennetaan:
    - lainauspäivä (nykyinen päivä)
    - palautuspäivä (1 kuukausi lainauspäivästä)
- Laina tallennetaan historiaan
- Tiedot tallennetaan JSON-tiedostoon
- Peruuta-näppäin, jolla käyttäjä voi siirtyä takaisin päänäkymään

---

## Toiminnallisuus 5 - Palautus
![](/kuvat/toiminallisuus_palauta.png)

Kirjan palauttaminen takaisin kirjastoon.

**Toiminnot:**
- Poistaa nykyisen lainaajan
- Merkitsee kirjan vapaaksi
- Asettaa palautuspäivän lainaushistoriaan
- Tallentaa muutokset JSON-tiedostoon
- Peruuta-näppäin, jolla käyttäjä voi siirtyä takaisin päänäkymään

---

## Toiminnallisuus 6 - Poisto
![](/kuvat/toiminallisuus_poista.png)

Kirjan poistaminen järjestelmästä. Käyttäjän tulee valita jokin kirja ennen
kuin soveltaa siihen toiminnon.

**Toiminnot:**
- Käyttäjä valitsee kirjan listasta päänäkymästä
- Kirja poistetaan kirjastosta
- Muutokset tallennetaan JSON-tiedostoon

---

## Toiminnallisuus 7 - Lisäys
![](/kuvat/toiminallisuus_lisaa.png)

Uuden kirjan lisääminen järjestelmään.

**Toiminnot:**
- Käyttäjä syöttää:
    - kirjan nimi
    - tekijä
    - ISBN
- Sovellus tarkistaa syötteet seuraavilla vaatimuksilla:
    - kaikki kentät pakollisia
    - ISBN-tunnisteessa täytyy olla 13 merkkiä
- Kirja lisätään kirjastoon
- Tiedot tallennetaan JSON-tiedostoon
- Peruuta-näppäin, jolla käyttäjä voi siirtyä takaisin päänäkymään

---

## Toiminnallisuus 7 - Tietojen muokkaaminen
![](/kuvat/uusiTieto.png)

Olemassa olevan kirjan muokkaaminen.

**Toiminnot:**
- Käyttäjä voi muokata kirjan tietoja syöttämällä ainakin seuraavista:
    - kirjan nimi
    - tekijä
    - ISBN (vaatimus: 13 numeroa pitkä)
- Muokkaukset tallennetaan tallenna-painikkeella

---