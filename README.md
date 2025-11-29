# FactPulse SDK Java

Client Java officiel pour l'API FactPulse - Facturation électronique française.

## Fonctionnalités

- **Factur-X** : Génération et validation de factures électroniques (profils MINIMUM, BASIC, EN16931, EXTENDED)
- **Chorus Pro** : Intégration avec la plateforme de facturation publique française
- **AFNOR PDP/PA** : Soumission de flux conformes à la norme XP Z12-013
- **Signature électronique** : Signature PDF (PAdES-B-B, PAdES-B-T, PAdES-B-LT)
- **Client simplifié** : Authentification JWT et polling intégrés via `helpers`

## Installation

### Maven

```xml
<dependency>
    <groupId>fr.factpulse</groupId>
    <artifactId>factpulse-sdk</artifactId>
    <version>2.0.30</version>
</dependency>
```

### Gradle

```groovy
implementation 'fr.factpulse:factpulse-sdk:2.0.30'
```

## Démarrage rapide

Le package `helpers` offre une API simplifiée avec authentification et polling automatiques :

```java
import org.openapitools.client.helpers.FactPulseClient;
import org.openapitools.client.helpers.MontantHelpers;
import static org.openapitools.client.helpers.MontantHelpers.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// Créer le client
FactPulseClient client = new FactPulseClient(
    "votre_email@example.com",
    "votre_mot_de_passe"
);

// Construire la facture avec les helpers
Map<String, Object> factureData = new LinkedHashMap<>();
factureData.put("numeroFacture", "FAC-2025-001");
factureData.put("dateFacture", "2025-01-15");
factureData.put("fournisseur", fournisseur(
    "Mon Entreprise SAS", "12345678901234",
    "123 Rue Example", "75001", "Paris"
));
factureData.put("destinataire", destinataire(
    "Client SARL", "98765432109876",
    "456 Avenue Test", "69001", "Lyon"
));
factureData.put("montantTotal", montantTotal(1000.00, 200.00, 1200.00, 1200.00));
factureData.put("lignesDePoste", Arrays.asList(
    ligneDePoste(1, "Prestation de conseil", 10, 100.00, 1000.00)
));
factureData.put("lignesDeTva", Arrays.asList(
    ligneDeTva("20.00", 1000.00, 200.00)
));

// Générer le PDF Factur-X
byte[] pdfBytes = client.genererFacturx(factureData, "facture_source.pdf", "EN16931");

Files.write(Paths.get("facture_facturx.pdf"), pdfBytes);
```

## Helpers disponibles (classe MontantHelpers)

### montant(value)

Convertit une valeur en string formaté pour les montants monétaires.

```java
import static org.openapitools.client.helpers.MontantHelpers.montant;

montant(1234.5);      // "1234.50"
montant("1234.56");   // "1234.56"
montant(null);        // "0.00"
```

### montantTotal(ht, tva, ttc, aPayer, ...)

Crée un objet MontantTotal complet.

```java
import static org.openapitools.client.helpers.MontantHelpers.montantTotal;

Map<String, Object> total = montantTotal(
    1000.00,        // ht
    200.00,         // tva
    1200.00,        // ttc
    1200.00,        // aPayer
    50.00,          // remiseTtc (optionnel)
    "Fidélité",     // motifRemise (optionnel)
    100.00          // acompte (optionnel)
);
```

### ligneDePoste(numero, denomination, quantite, montantUnitaireHt, montantTotalLigneHt, ...)

Crée une ligne de facturation.

```java
import static org.openapitools.client.helpers.MontantHelpers.ligneDePoste;

Map<String, Object> ligne = ligneDePoste(
    1,
    "Prestation de conseil",
    5,
    200.00,
    1000.00,  // montantTotalLigneHt requis
    "20.00",  // tauxTva
    "S",      // categorieTva: S, Z, E, AE, K
    "HEURE",  // unite: FORFAIT, PIECE, HEURE, JOUR...
    null      // options Map (reference, etc.)
);
```

### ligneDeTva(tauxManuel, montantBaseHt, montantTva, categorie)

Crée une ligne de ventilation TVA.

```java
import static org.openapitools.client.helpers.MontantHelpers.ligneDeTva;

Map<String, Object> tva = ligneDeTva(
    "20.00",    // tauxManuel
    1000.00,    // montantBaseHt
    200.00,     // montantTva
    "S"         // categorie: S, Z, E, AE, K
);
```

### adressePostale(ligne1, codePostal, ville, ...)

Crée une adresse postale structurée.

```java
import static org.openapitools.client.helpers.MontantHelpers.adressePostale;

Map<String, Object> adresse = adressePostale(
    "123 Rue de la République",
    "75001",
    "Paris",
    "FR",           // pays (défaut: "FR")
    "Bâtiment A",   // ligne2 (optionnel)
    null            // ligne3 (optionnel)
);
```

### fournisseur(nom, siret, adresseLigne1, codePostal, ville, options)

Crée un fournisseur complet avec calcul automatique du SIREN et TVA intra.

```java
import static org.openapitools.client.helpers.MontantHelpers.fournisseur;

Map<String, Object> f = fournisseur(
    "Ma Société SAS",
    "12345678901234",
    "123 Rue Example",
    "75001",
    "Paris",
    Map.of("iban", "FR7630006000011234567890189")
);
// SIREN et TVA intracommunautaire calculés automatiquement
```

### destinataire(nom, siret, adresseLigne1, codePostal, ville, options)

Crée un destinataire (client) avec calcul automatique du SIREN.

```java
import static org.openapitools.client.helpers.MontantHelpers.destinataire;

Map<String, Object> d = destinataire(
    "Client SARL",
    "98765432109876",
    "456 Avenue Test",
    "69001",
    "Lyon",
    null
);
```

## Mode Zero-Trust (Chorus Pro / AFNOR)

Pour passer vos propres credentials sans stockage côté serveur :

```java
import org.openapitools.client.helpers.*;

ChorusProCredentials chorusCreds = new ChorusProCredentials(
    "votre_client_id",
    "votre_client_secret",
    "votre_login",
    "votre_password",
    true  // sandbox
);

AFNORCredentials afnorCreds = new AFNORCredentials(
    "https://api.pdp.fr/flow/v1",
    "https://auth.pdp.fr/oauth/token",
    "votre_client_id",
    "votre_client_secret"
);

FactPulseClient client = new FactPulseClient(
    "votre_email@example.com",
    "votre_mot_de_passe",
    null,  // apiUrl
    null,  // clientUid
    chorusCreds,
    afnorCreds,
    2000,  // pollingInterval
    120000,  // pollingTimeout
    1  // maxRetries
);
```

## Ressources

- **Documentation API** : https://factpulse.fr/api/facturation/documentation
- **Support** : contact@factpulse.fr

## Licence

MIT License - Copyright (c) 2025 FactPulse
