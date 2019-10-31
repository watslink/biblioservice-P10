****************************************** BiblioService **********************************************

Service permettant la gestion des livres, utilisateurs et emprunts pour une bibliothéque.
Projet Maven multimodules.
JEE 8.

-----
BDD
-----

Créer une base de données avec les scripts de création et de données de démos présent dans le dossier "Scripts Bdd"

--------------
Configuration
--------------
1-
Module biblioserice-consumer: mettre à jour le fichier resources/database.properties avec les identifiants
de votre base de données

2-
Module biblioservice-business: possibilité de configurer la durée (en semaines) de prêt d'un livre et sa prolongation
dans resources/borrowing.properties (valeurs par défaut: 4 semaines pour les 2)

--------
Package
--------

Packager l'application avec la commande "package mvn"

------------
Déploiement
------------

Deployer l'artefact "biblioservice-API-1.0-SNAPSHOT.war" du module biblioservice-API dans un serveur d'application
Tomcat 9

-----
WSDL
-----
Les WSDL des 3 services (Book, Borrowing, User) sont dans le dossier "wsdl", ils sont également disponible après
déploiement de l'appication à ces adresses:

http://localhost:8080/biblioservice_API_war/bookAPI?wsdl
http://localhost:8080/biblioservice_API_war/userAPI?wsdl
http://localhost:8080/biblioservice_API_war/borrowingAPI?wsdl

(Adresses à adpter selon localisation du serveur et port utilisé)

--------
SOAP UI
--------

Les projets tests SOAP UI sont disponibles dans le dossier "SoapUI-tests"




