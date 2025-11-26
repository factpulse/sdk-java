# FactPulse SDK Java

Client Java officiel pour l'API FactPulse - Facturation électronique française.

## 🎯 Fonctionnalités

- **Factur-X** : Génération et validation de factures électroniques (profils MINIMUM, BASIC, EN16931, EXTENDED)
- **Chorus Pro** : Intégration avec la plateforme de facturation publique française
- **AFNOR PDP/PA** : Soumission de flux conformes à la norme XP Z12-013
- **Signature électronique** : Signature PDF (PAdES-B-B, PAdES-B-T, PAdES-B-LT)
- **Client simplifié** : Authentification JWT et polling intégrés via `helpers`
- **Java 8+** : Compatible avec Java 8 et versions supérieures

## 🚀 Installation

### Maven

```xml
<dependency>
    <groupId>fr.factpulse</groupId>
    <artifactId>factpulse-sdk</artifactId>
    <version>2.0.15</version>
</dependency>
```

### Gradle

```groovy
implementation 'fr.factpulse:factpulse-sdk:2.0.15'
```

## 📖 Démarrage rapide

### Méthode recommandée : Client simplifié avec helpers

Le package `helpers` offre une API simplifiée avec authentification et polling automatiques :

```java
import org.openapitools.client.helpers.FactPulseClient;
import org.openapitools.client.helpers.FactPulseClientConfig;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// Créer le client (authentification automatique)
FactPulseClientConfig config = new FactPulseClientConfig(
    "votre_email@example.com",
    "votre_mot_de_passe"
);
FactPulseClient client = new FactPulseClient(config);

// Données de la facture
Map<String, Object> factureData = new HashMap<>();
factureData.put("numero_facture", "FAC-2025-001");
factureData.put("date_facture", "2025-01-15");

Map<String, Object> fournisseur = new HashMap<>();
fournisseur.put("nom", "Mon Entreprise SAS");
fournisseur.put("siret", "12345678901234");
Map<String, String> adresseFournisseur = new HashMap<>();
adresseFournisseur.put("ligne_un", "123 Rue Example");
adresseFournisseur.put("code_postal", "75001");
adresseFournisseur.put("nom_ville", "Paris");
adresseFournisseur.put("pays_code_iso", "FR");
fournisseur.put("adresse_postale", adresseFournisseur);
factureData.put("fournisseur", fournisseur);

Map<String, Object> destinataire = new HashMap<>();
destinataire.put("nom", "Client SARL");
destinataire.put("siret", "98765432109876");
Map<String, String> adresseDestinataire = new HashMap<>();
adresseDestinataire.put("ligne_un", "456 Avenue Test");
adresseDestinataire.put("code_postal", "69001");
adresseDestinataire.put("nom_ville", "Lyon");
adresseDestinataire.put("pays_code_iso", "FR");
destinataire.put("adresse_postale", adresseDestinataire);
factureData.put("destinataire", destinataire);

Map<String, Object> montantTotal = new HashMap<>();
montantTotal.put("montant_ht_total", "1000.00");
montantTotal.put("montant_tva", "200.00");
montantTotal.put("montant_ttc_total", "1200.00");
montantTotal.put("montant_a_payer", "1200.00");
factureData.put("montant_total", montantTotal);

// Lire le PDF source
byte[] pdfSource = Files.readAllBytes(Paths.get("facture_source.pdf"));

// Générer le PDF Factur-X (polling automatique)
byte[] pdfBytes = client.genererFacturx(
    factureData,
    pdfSource,
    "EN16931",  // profil
    "pdf",      // format
    true,       // sync (attend le résultat)
    null        // timeout (utilise la valeur par défaut)
);

// Sauvegarder
Files.write(Paths.get("facture_facturx.pdf"), pdfBytes);
```

### Méthode alternative : SDK brut

Pour un contrôle total, utilisez le SDK généré directement :

```java
import org.openapitools.client.ApiClient;
import org.openapitools.client.api.TraitementFactureApi;
import okhttp3.*;
import com.google.gson.Gson;
import java.util.*;

// 1. Obtenir le token JWT
OkHttpClient httpClient = new OkHttpClient();
MediaType JSON = MediaType.parse("application/json; charset=utf-8");

Map<String, String> credentials = new HashMap<>();
credentials.put("username", "votre_email@example.com");
credentials.put("password", "votre_mot_de_passe");

Gson gson = new Gson();
RequestBody body = RequestBody.create(gson.toJson(credentials), JSON);
Request request = new Request.Builder()
    .url("https://factpulse.fr/api/token/")
    .post(body)
    .build();

Response response = httpClient.newCall(request).execute();
Map<String, String> result = gson.fromJson(response.body().string(), Map.class);
String token = result.get("access");

// 2. Configurer le client
ApiClient apiClient = new ApiClient();
apiClient.setBasePath("https://factpulse.fr/api/facturation");
apiClient.setAccessToken(token);

// 3. Appeler l'API
TraitementFactureApi api = new TraitementFactureApi(apiClient);
Object apiResponse = api.genererFactureApiV1TraitementGenererFacturePost(
    gson.toJson(factureData),
    "EN16931",
    "pdf",
    new File("facture_source.pdf")
);

// 4. Polling manuel pour récupérer le résultat
String taskId = ((Map<String, Object>) apiResponse).get("id_tache").toString();
// ... (implémenter le polling)
```

## 🔧 Avantages des helpers

| Fonctionnalité | SDK brut | helpers |
|----------------|----------|---------|
| Authentification | Manuelle | Automatique |
| Refresh token | Manuel | Automatique |
| Polling tâches async | Manuel | Automatique (backoff) |
| Retry sur 401 | Manuel | Automatique |

## 🔑 Options d'authentification

### Client UID (multi-clients)

Si vous gérez plusieurs clients :

```java
FactPulseClientConfig config = new FactPulseClientConfig(
    "votre_email@example.com",
    "votre_mot_de_passe"
).setClientUid("identifiant_client");
```

### Configuration avancée

```java
FactPulseClientConfig config = new FactPulseClientConfig(
    "votre_email@example.com",
    "votre_mot_de_passe"
)
    .setApiUrl("https://factpulse.fr")  // URL personnalisée
    .setPollingInterval(2000)  // Intervalle de polling initial (ms)
    .setPollingTimeout(120000)  // Timeout de polling (ms)
    .setMaxRetries(2);  // Tentatives en cas de 401
```

## 💡 Formats de montants acceptés

L'API accepte plusieurs formats pour les montants :

```java
// String (recommandé pour la précision)
String montant = "1234.56";

// Number (double)
double montant = 1234.56;

// Integer
int montant = 1234;

// BigDecimal
BigDecimal montant = new BigDecimal("1234.56");

// Helper de formatage
String montantFormate = FactPulseClient.formatMontant(1234.5);  // "1234.50"
```

## 📚 Ressources

- **Documentation API** : https://factpulse.fr/api/facturation/documentation
- **Code source** : https://github.com/factpulse/sdk-java
- **Issues** : https://github.com/factpulse/sdk-java/issues
- **Support** : contact@factpulse.fr

## 📄 Licence

MIT License - Copyright (c) 2025 FactPulse
