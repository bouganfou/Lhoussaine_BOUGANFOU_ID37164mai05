# gestion_scolaire_2023

Gestion scolaire qui contient la structure des étudiants
  - Les étudiants sont liés à une seule classe
  - Un seul enseignant par classe
  - L'étudiant doit contenir les informations suivantes: ID, Prénom, Nom de famille
  - L'enseignant doit contenir les informations suivantes: ID, Prénom, Nom de famille
  - La classe doit contenir les informations suivantes: ID, nom
De plus, créez une API RESTful en utilisant Java Spring Boot avec les éléments suivants :
  - Sécuriser l'API après la connexion en utilisant un jeton JWT généré.
  - Obtenir la liste des étudiants avec les éléments suivants :
    - Filtres : Nom de la classe et/ou Nom complet de l'enseignant
    - Tous les étudiants de la liste seront retournés en cas d'absence de valeur de filtres
    - Paginé
  - Tests unitaires (facultatif si vous avez le temps)
