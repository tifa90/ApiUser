# ApiUser

###Information general

Le but de cet api est de permettre:

* L'enregistrement d'un utilisateur
* L'affichage d'un utilisateur

Un utilisateur(User) est defini par ces attributs :


* username
* birthdate
* country of residence
* phone (optionnel)
* gender(optionnel)

###Pre-requis 

Pour utiliser cet api, il faut avoir :

* JDK version 17
* Maven 3.8

###Configuration

Pour lancer le projet, il faut tout d'abprd le cloner et apres faire le run.
Le clone se fait a partir de github car le projet y est despose

1. git clone https://github.com/tifa90/ApiUser
2. Build le projet avec un terminal en utilisant ces commandes :

	a.  mvn clean install 
	b.  mvn spring-boot:run

#Test avec Postman
Les tests api ont ete fait avec postman.
Afin d'acceder au fichier de test le telecharger du dossier postman_Collection qui se trouve dans le dossier src/main/resources

