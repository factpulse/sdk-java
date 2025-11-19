

# StatutTache

Description complète du statut d'une tâche asynchrone.  Le champ `statut` indique l'état Celery de la tâche. Quand `statut=\"SUCCESS\"`, consultez `resultat.statut` pour le résultat métier (\"SUCCES\" ou \"ERREUR\").

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**idTache** | **String** |  |  |
|**statut** | **StatutCelery** | Statut Celery de la tâche (PENDING, STARTED, SUCCESS, FAILURE, RETRY) |  |
|**resultat** | **Map&lt;String, Object&gt;** |  |  [optional] |



