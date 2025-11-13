# Configuration GitHub Actions

## GitHub Packages Maven

Le SDK est automatiquement déployé sur GitHub Packages Maven lors de la création d'un tag.

**Aucun secret requis** - Le workflow utilise le `GITHUB_TOKEN` automatiquement fourni par GitHub Actions.

## Déploiement

Le workflow se déclenche automatiquement lors de la création d'un tag `v*` (exemple : `v1.0.0`).

## Installation du SDK

Pour utiliser ce SDK depuis GitHub Packages, ajoutez ce repository Maven dans votre `pom.xml`:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/factpulse/sdk-java</url>
    </repository>
</repositories>

<dependency>
    <groupId>fr.factpulse</groupId>
    <artifactId>factpulse-sdk</artifactId>
    <version>VERSION</version>
</dependency>
```

Pour accéder à GitHub Packages, configurez votre `~/.m2/settings.xml`:

```xml
<settings>
    <servers>
        <server>
            <id>github</id>
            <username>VOTRE_USERNAME_GITHUB</username>
            <password>VOTRE_PERSONAL_ACCESS_TOKEN</password>
        </server>
    </servers>
</settings>
```

Créez un Personal Access Token avec le scope `read:packages` sur: https://github.com/settings/tokens
