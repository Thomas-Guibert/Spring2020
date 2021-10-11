# TP3 MMM : Le framework Spring

###### Guibert Thomas & Koopman Valentin

## GitHub du TP3

Lien : https://github.com/Thomas-Guibert/Spring2020/tree/GuibertDev

## GitHub des autres TP

#### TP1 + TP2 partie 1 :

Lien : https://github.com/Thomas-Guibert/tpjpa2021/tree/GuibertDevTP2

#### TP2 partie 2 :

Lien : https://github.com/Thomas-Guibert/JaxRSOpenAPI/tree/GuibertDevT2Part2

## But du TP

Ce TP a pour but de ce TP est de se familiariser avec Spring et d'adapter les TP 1 et 2 à celui-ci.

Sur les conseils d'Adrien Le Roch nous n'avons pas réellement fait la première partie du TP qui portait sur le modèle de la banque, nous nous sommes contenté de regarder comment cela était mis en place grace aux exemples donnés dans les liens GitHub.

Pour ce TP, nous nous sommes donc concentré sur la partie 3, l'adaptation des deux Tp précédent à Spring et nous avons mis un peu de Spring AOP commence la partie 2 du TP le montrait.

## Comment lancer le TP

Avant tout pour lancer le projet, il faut mettre en place une base de données, comme une base mysql avec docker par exemple :

```
docker run --name some-mysql  -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123 -d mysql:latest
```

```
docker run --name myadmin -d --link some-mysql:db -p 8082:80 phpmyadmin
```

De préférence avec le mot de passe "123" étant utilisé de base dans la configuration du TP.

Il faut y créé une base de données nommée "testspringdata".

Ensuite, il suffit d'exécuter la class *"SampleDataJpaApplication"* situer dans la package *"sample.data.jpa"*.

Puis enfin d'utiliser Postman pour utiliser les controller et ainsi modifier 

## Les classes métiers

Tout le long de TAA nous avons utilisé un modèle à trois entités:

- User : qui ont un ID, un prénom, un nom, un mail, un mot de passe et une liste de rendez-vous
- Professionnel : qui hérite d'User, ils ont tous les paramètre de ceci en plus d'avoir une URL et un job
- Rendez-Vous : qui ont un ID, une date, une durer, un user, un professionnel et une description

Ainsi un Professionnel et un User peuvent avoir plusieurs rendez-vous et un rendez-vous à un seul User et un seul professionnel (ManyToOne - OneToMany)

Ces classes métiers se trouve dans le package *"sample.data.jpa.domain"*.

Les classes métier n'ont pas vraiment changé en passant sur Spring. Elle garde les annotations telles que Entity, Id ou ManyToOne. 

Lors du TP2 un problème du chargement infini a été rencontré, en effet le Json qui chargeait un user, chargeait le rendez-vous rattacher, mais il chargeait à nouveau l'User car il était contenu dans celui-ci. C'est pour cela que l'annotation "@JsonIgnore" a été ajoutée.

## Les DAO avec Spring

Comme vu en cours les DAO en string sont plus simples à mettre en place. Plus besoin de code, seulement des requêtes JPQL.

Les DAO sont trouvable dans le package *"sample.data.jpa.service"*.

## Les controllers

Pour les Controller, nous nous basons sur l'API Rest developper au TP2, ils servent à agir sur la base de donnée en utilisant la DAO crée plutôt. Les controllers permettent de faire 4 actions : ajoutées de nouveau element, mettre à jour les attributs des elements, récupérer information contenue dans la base et enfin supprimer des éléments.

Pour passer des Rest à Spring, il a juste fallus dapter le fichier avec les bonnes annotations. Le code est disponible dans le package *"sample.data.jpa.web"*.

## AOP

La partie AOP est située dans le package *"sample.data.jpa.monitor"*. Celui-ci a pour but d'afficher un Logger après l'exécution d'un service dans un controller, en affichant quelle méthode a été executé que ce soit pour User, Professionnel ou RendezVous.

## Amélioration possible et conclusion

À la fin de ce TP nous obtenons le côté backend complet de l'application, il ne manquerait donc que le frontend avec une interface web. Celle-ci n'a pas été faite quand elle n'était techniquqment pas demandée mais grace au servelet vu au TP2, nous aurons pu mettre cela en place assez facilement.

Au final, l'UE TAA nous a permis de comprendre et de savoir développer le côté backend d'une application, mais aussi un petit peu frontend avec les Servelets. Ils sont à aussi permet de comprendre le framework Spring (Spring Boot, Spring Data), ce qui nous sera très utile plus tard dans le développement d'applications.
