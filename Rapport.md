# Transmission du Projet

## 1. Point d’avancement

### Fonctionnalités réalisées

TODO DONE 
- [] [x] Les bots peuvent connecter deux villes sur n'importe quelle route.
- [] [x] Les bots peuvent tirer des cartes destinations, wagons selon les règles du jeu- [] [x] Les bots peuvent collecter des meeples dans les villes rattachées aux connections qu'ils achètent, une seule fois par ville, selon les règles.
- [] [x] Une planche de jeu complète peut être créée, avec toutes ses villes, toutes ses connections et tous les meeples de chaque ville. Il s'agit de la carte du Ticket To Ride Germany.
- [] [x] Les bots peuvent jouer sur cette map, effectuer des actions et altérer l'état du jeu après chaque tours.
- [] [x] Les bots peuvent gagner des points selon les règles du jeu (ie en achetant des connections, en complétant des cartes destinations, en récoltant des meeples).
- [] [x] Les bots peuvent savoir si leur carte destination est complétée.
- [] [x] Les bots utilisent l'algorithme de Dijkstra pour trouver le plus court chemin entre deux villes.
- [x] [] Un des bots (répertoire Overlap) est encore en phase d'intégration.
- [x] [] Plusieurs tests sont encore à implémenter (65% de coverage)
- [x] [] Tests 

### Gestion des logs
- Explication des choix techniques pour les logs
- Format utilisé (ex: JSON, texte brut, etc.)
- Emplacement des fichiers de logs

### Statistiques en CSV
- L'ensemble de la gestion des statistiques CSV se trouve dans la classe StatsAnalyse 
- Le fichier CSV est toujours présent et doit être vidé à la main quand on veut prendre des nouvelles stats 
- Une lecture du fichier CSV est effectuée pour connaitre les stats actuels.
  Puis une première écriture ce fait pour mettre à jour les informations des bots déjà connue.
  Enfin une deuxième écriture est réalisé pour ajouter les stats des nouveaux bots.
- Données stockées : 
  - Le nom du bot
  - Son nombre de parties jouées
  - Son nombre de victoires
  - Son taux de victoire
  - Son nombre de points total gagné

### Bot spécifique et comparaison
- Présentation du bot spécifique demandé
- Comparaison avec le meilleur bot développé
- Analyse des performances et explication des résultats

## 2. Architecture et qualité

### Architecture du projet
- Présentation de l’architecture globale (diagramme si nécessaire)
- Justification des choix techniques

### Documentation et ressources
- Emplacement de la Javadoc et autres documentations
- Références aux classes et modules principaux

### Qualité du code
- Analyse de l’état actuel du code
- Liste des parties bien structurées
- Liste des parties à refactorer et explications
- Résumé des résultats SONAR (ou autre outil d’analyse de code)

---

> **Note** : N’hésitez pas à contacter l’ancienne équipe en cas de besoin.

