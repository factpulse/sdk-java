# Configuration GitHub Actions

## Configuration Maven Central

1. Créez un compte sur https://central.sonatype.org/
2. Demandez l'accès au groupId `fr.factpulse`

## Secrets GitHub requis

1. Allez sur https://github.com/factpulse/sdk-java/settings/secrets/actions
2. Ajoutez les secrets :
   - `OSSRH_USERNAME` : Votre username Sonatype
   - `OSSRH_TOKEN` : Votre token Sonatype
   - `MAVEN_GPG_PRIVATE_KEY` : Votre clé GPG privée (pour signer les artifacts)
   - `MAVEN_GPG_PASSPHRASE` : La passphrase de votre clé GPG

## Déploiement

Le workflow se déclenche automatiquement lors de la création d'un tag `v*` (exemple : `v1.0.0`).
