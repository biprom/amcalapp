package com.biprom.bram.backend;

import com.biprom.bram.backend.data.entity.mongodbEntities.Artikel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface StockRepository extends MongoRepository<Artikel, String> {
    List<Artikel> findByArtikelCodeMatchesRegex(String msg);
    List<Artikel> findByArtikelCodeContainsIgnoreCase(String msg);
    List<Artikel> findByStockAantal(int aantalStock);
    List<Artikel> findByAantalInBulk(int aantalBulk);
    List<Artikel> findByBulkNummerMatchesRegex(String msg);
    List<Artikel> findByCommentaarToevoegingMatchesRegex(String msg);
    List<Artikel> findByArtikelTypeMatchesRegex(String msg);
    List<Artikel> findByArtikelTypeMatchesAndPompTypeContainsIgnoreCase(String artikelType, String pompType);
    List<Artikel> findByPompTypeMatchesRegex(String msg);
    List<Artikel> findByAsAfdichtingMatchesRegex(String msg);
    List<Artikel> findByAsAfdichtingMatchesAndPompTypeContainsIgnoreCase(String asAfdichting, String pompType);
    List<Artikel> findByKitSlijtdelenMatchesRegex(String msg);
    List<Artikel> findByKitSlijtdelenMatchesAndPompTypeContainsIgnoreCase(String kitSlijtDelen, String pompType);
    List<Artikel> findByPompKapVoetMatchesRegex(String msg);
    List<Artikel> findByPompKapVoetMatchesAndPompTypeContainsIgnoreCase(String pompKapVoet, String pompType);
    List<Artikel> findByKitORingenMatchesRegex(String msg);
    List<Artikel> findByKitORingenMatchesAndPompTypeContainsIgnoreCase(String kitORingen, String pompType);
    List<Artikel> findByKitWaaiersKamerMatchesRegex(String msg);
    List<Artikel> findByKitWaaiersKamerMatchesAndPompTypeContainsIgnoreCase(String kitORingen, String pompType);
    List<Artikel> findByMantelTrekstangTreklatPompasMatchesRegex(String msg);
    List<Artikel> findByMantelTrekstangTreklatPompasMatchesAndPompTypeContainsIgnoreCase(String mantelTrekstangTreklat, String pompType);
    List<Artikel> findByAllerleiMatchesRegex(String msg);
    List<Artikel> findByAllerleiMatchesAndPompTypeContainsIgnoreCase(String allerlei, String pompType);
    List<Artikel> findByDatePrijsBetween(LocalDate start, LocalDate einde);
}
