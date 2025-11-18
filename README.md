# FactPulse SDK Java

Client Java officiel pour l'API FactPulse - Facturation électronique française.

## 🎯 Fonctionnalités

- **Factur-X** : Génération et validation de factures électroniques (profils MINIMUM, BASIC, EN16931, EXTENDED)
- **Chorus Pro** : Intégration avec la plateforme de facturation publique française
- **AFNOR PDP/PA** : Soumission de flux conformes à la norme XP Z12-013
- **Signature électronique** : Signature PDF (PAdES-B-B, PAdES-B-T, PAdES-B-LT)
- **Traitement asynchrone** : Support Celery pour opérations longues
- **Java 8+** : Compatible avec Java 8 et versions supérieures

## 🚀 Installation

### Maven

```xml
<dependency>
    <groupId>fr.factpulse</groupId>
    <artifactId>factpulse-sdk</artifactId>
    <version>2.0.0</version>
</dependency>
```

### Gradle

```groovy
implementation 'fr.factpulse:factpulse-sdk:2.0.0'
```

## 📖 Démarrage rapide

### 1. Authentification

```java
import fr.factpulse.ApiClient;
import fr.factpulse.api.TraitementFactureApi;
import fr.factpulse.auth.HttpBearerAuth;

// Configuration du client
ApiClient defaultClient = new ApiClient();
defaultClient.setBasePath("https://factpulse.fr/api/facturation");

HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
bearerAuth.setBearerToken("votre_token_jwt");

TraitementFactureApi apiInstance = new TraitementFactureApi(defaultClient);
```

### 2. Générer une facture Factur-X

```java
import com.google.gson.Gson;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

// Données de la facture
Map<String, Object> factureData = new HashMap<>();
factureData.put("numero_facture", "FAC-2025-001");
factureData.put("date_facture", "2025-01-15");
factureData.put("montant_total_ht", "1000.00");
factureData.put("montant_total_ttc", "1200.00");

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

// ... (similaire pour destinataire et lignes_de_poste)

Gson gson = new Gson();
String jsonFacture = gson.toJson(factureData);

// Générer le PDF Factur-X
File pdfFile = apiInstance.genererFactureApiV1TraitementGenererFacturePost(
    jsonFacture,
    "EN16931",
    "pdf"
);

// Sauvegarder
Files.copy(pdfFile.toPath(), Paths.get("facture.pdf"));
```

### 3. Soumettre une facture complète (Chorus Pro / AFNOR PDP)

```java
Map<String, Object> destination = new HashMap<>();
destination.put("type", "chorus_pro");
Map<String, String> credentials = new HashMap<>();
credentials.put("login", "votre_login_chorus");
credentials.put("password", "votre_password_chorus");
destination.put("credentials", credentials);

Map<String, Object> requestBody = new HashMap<>();
requestBody.put("facture", factureData);
requestBody.put("destination", destination);

Object response = apiInstance.soumettreFactureCompleteApiV1TraitementFacturesSoumettreCompletePost(requestBody);
System.out.println("Facture soumise : " + response);
```

## 🔑 Obtention du token JWT

### Via l'API

```java
import okhttp3.*;
import com.google.gson.Gson;

OkHttpClient client = new OkHttpClient();
MediaType JSON = MediaType.parse("application/json; charset=utf-8");

Map<String, String> credentials = new HashMap<>();
credentials.put("username", "votre_email@example.com");
credentials.put("password", "votre_mot_de_passe");

Gson gson = new Gson();
String json = gson.toJson(credentials);

RequestBody body = RequestBody.create(JSON, json);
Request request = new Request.Builder()
    .url("https://factpulse.fr/api/token/")
    .post(body)
    .build();

Response response = client.newCall(request).execute();
Map<String, String> result = gson.fromJson(response.body().string(), Map.class);
String token = result.get("access");
```

**Accès aux credentials d'un client spécifique :**

Si vous gérez plusieurs clients et souhaitez accéder aux credentials (Chorus Pro, AFNOR PDP) d'un client particulier, ajoutez le champ `client_uid` :

```java
credentials.put("client_uid", "identifiant_client");  // UID du client cible
```

### Via le Dashboard

1. Connectez-vous sur https://factpulse.fr/api/dashboard/
2. Générez un token API
3. Copiez et utilisez le token dans votre configuration

## 📚 Ressources

- **Documentation API** : https://factpulse.fr/api/facturation/documentation
- **Code source** : https://github.com/factpulse/sdk-java
- **Issues** : https://github.com/factpulse/sdk-java/issues
- **Support** : contact@factpulse.fr

## 📄 Licence

MIT License - Copyright (c) 2025 FactPulse
