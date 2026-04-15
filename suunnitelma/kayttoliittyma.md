# Käyttöliittymän suunnitelma

Sovellus on JavaFX-pohjainen kirjastojärjestelmä, jossa käyttäjä voi hallita kirjoja ja niiden
lainauksia. Käyttöliittymä koostuu useasta eri näkymästä.

---

## Näkymä 1 - Päänäkymä

![](/suunnitelma/kuvat/kirjasto_projekti.png)

**Olennaiset toiminnot**

Päänäkymä on sovelluksen varsinainen näkymä, josta käyttäjä voi navigoida kaikki
oleelliset toiminnot

Käyttäjä näkee listan kaikista kirjoista (nimi, tekijä, ISBN),
hakukentän kirjojen suodattamiseen ja painikkeet eri toimintoihin.
Käyttäjä voi, hakea kirjoja hakusanan perusteella, valita kirjan listasta,
siirtyä kirjan tietoihin, lainaa kirjoja, palauttaa kirjoja ja lisätä uusia kirjoja.

**Olennaiset komponentit**

- `ListView<Kirja>` - kirjojen näyttäminen
- `TextField` - hakutoiminto
- `Button` - navigointi ja muut toiminnot
- `ButtonBar` - painikkeiden ryhmittely
- `Alert` - ponnahdusikkuna virheilmoituksiin
- `Controller` - (KirjastoController)

---

## Näkymä 2 - Kirjan lisäys

![](/suunnitelma/kuvat/kirjasto_projekti(1).png)

**Olennaiset toiminnot**

Näkymässä käyttäjä voi lisätä uuden kirjan syöttämällä tekstikenttään kirjan nimen,
tekijän nimen, ISBN-tunnisteen, tallentaa kirjan järjestelmään ja perua toiminnon kokonaan.

**Olennaiset komponentit**

- `TextField` - syöttökentät
- `Button` - tallenna- ja peruuta näppäimet
- `Alert` - ponnahdusikkuna virheilmoituksiin
- `Controller` - (LisaysController)
---

## Näkymä 3 - Lainausnäkymä

![](/suunnitelma/kuvat/kirjasto_projekti(3).png)

**Olennaiset toiminnot**

Näkymässä käyttäjä voi lainaa valitun kirjan. Käyttäjä näkee valitun kirjan tiedot, lainauspäivän
ja palautuspäivän, joka on oletuksena 1 kuukausi lainauspäivästä. Käyttäjä voi
syöttää lainaajan nimen, lainata kirjan tai perua toiminnon kokonaan palaamalla päänäkymälle.

**Olennaiset komponentit**

- `TextField` - syötettävät tiedot
- `ListView<String>` - kirjan tiedot
- `Button` - tallenna- ja peruuta näppäimet
- `Controller` - (LainausController)


---

## Näkymä 4 - Palautusnäkymä

![](/suunnitelma/kuvat/kirjasto_projekti(4).png)

**Olennaiset toiminnot**

Näkymässä käyttäjä voi palauttaa aiemmin lainatun kirjan. Käyttäjä näkee kirjan tiedot, ja
voi palauttaa kirjan, jos se on lainattu.

**Olennaiset komponentit**

- `ListView<String>` - jirjan tiedot
- `Button` - palauta- ja peruuta näppäimet
- `Controller` - (PalautusController)


---

## Näkymä 5 - Kirjan tiedot

![](/suunnitelma/kuvat/kirjasto_projekti(2).png)

**Olennaiset toiminnot**

Käyttäjä näkee valitun kirjan kaikki olennaiset tiedot, kuten perustiedot, lainatilanne,
nykyisen lainaajan, lainauskertojen määrän ja koko lainaushistorian. Käyttäjä voi pelkästään
palata takaisin päänäkymälle.

**Olennaiset komponentit**

- `ListView<String>` - kirjan tiedot
- `Label` - otsikot ja selitteet
- `Button` - peruuta-näppäin
- `Controller` - (TietoController)

---