# Documentation

### Interface :
Notre interface est construite de la manière suivante:
- Un tableau contenant les calculs et les résultats des diférentes opérations.
  ![Tableau des calculs](/images/table.JPG)
- Un champ texte en bas où nous rentrons notre calcul que nous voulons faire
  ![Champ de texte](/images/textfield.JPG)
- Une liste des variables déclarées 
  ![Liste des variables déclarées](/images/variables.JPG)
- Une liste des fonctions déclarées
  ![Liste des fonctions déclarées](/images/fonctions.JPG)
- Un bouton permettant de tracer les graphiques des fonctions déclarées
  ![Bouton graph](/images/boutongraph.JPG)
  
---

### Les fonctionnalités :
- Nous pouvons effectuer des calculs mathémathiques contenant les opérations : +, -, *, /, ()
    - Pour cela vous devez entrer votre calcul dans le champ de texte mentionné avant puis il faut appuyer sur ajouter pour que le programme effectue le calcul et l'ajoute dans la table.
    - Vous pouvez ensuite modifier votre calcul dans la table si celui ne vous convient plus.
- Nous pouvons également effectuer des calculs avec des fonctions mathématiques basiques : sin, cos, exp, ln, sqrt
    - Pour cela, procéder de la même manière que les calculs de base
        - Exemple : 3+sin(0)
        ![Fonctions mathématiques](/images/mathfunctions.JPG)
- Nous pouvons déclarer des variables : x=3 par exemple et faire des calculs avec.
    - Pour déclarer la variable, il faut aussi l'entrer dans le champ de texte et cliquer sur ajouter
    - Vous pouvez voir dans la liste sur le côté toutes les variables que vous avez déclaré
- Nous pouvons déclarer des fonctions :
    - Exemple : f(x)=3+x dans le champ de texte et appuyer sur le bouton ajouter, vous verrez alors celle-ci apparaître dans la liste des fonctions déclarées
      ![Fonction déclarée](/images/fonctiondeclaree.JPG)
    - Vous pouvez également déclarer une fonction à l'aide d'une autre fonction :
        - Exemple : g(x)=f(x)+3 (f doit toute fois être déclarée)
- Nous pouvons effectuer des calculs avec ces fonctions déclarées :
    - Exemple : si vous entrez 3+f(2)*6
    ![Fonction utilisée dans le calcul](/images/feval.JPG)
- Nous pouvons tracer les graphes des fonctions déclarées précédemment :
    - Pour tracer un graphe, vous devez sélectionner la fonction dont vous souhaitez afficher le graphique dans la liste des fonctions déclarées puis appuyer sur le bouton Graph en dessous.
    ![Affichage graphique](/images/graphfunction.JPG)
    - Vous obtiendrez alors une nouvelle fenêtre avec le graphique de la fonction évaluée de -30 à 30 avec un pas de 0.1
    ![Graphique f(x)=3+x](/images/graph3+x.JPG)

---

### Construction du programme

- Nous avons un premier fichier **Main** qui démarre l'application et charge notre **main.fxml**

- Nous avons ensuite un **Controller** attaché à l'AnchorPane principal, celui s'occupe de gérer l'interactions avec le TextField, les Buttons, les ListView et la TableView.
Ce Controller va utiliser le **Tokenizer** et le **Parser** pour faire les calculs (nous reviendrons après sur leurs rôles).
Il gère tout ce qui est création de graphique, affichage des éléments dans les différents tableaux.
Il se sert du model contenant tous les calculs, renvoyant les variables et les fonctions (CalculModel)

- Nous avons ensuite le **Tokenizer** :
    - Son rôle est de recevoir l'entrée de notre champ de texte et de la transformer en une liste de tokens utilisée par le Parser.
    - Pour créer la liste de tokens, celui-ci se sert de regex que nous avons renseignées (**TokenDefinition** class)
        - Exemple : si l'entrée est 3+6*2, nous aurions la liste de tokens suivante :
            - token : 9 (NUMBER), sequence: "3"
            - token : 1 (PLUS), sequence: "+"
            - token : 9 (NUMBER), sequence: "6"
            - token : 3 (MULT), sequence: "*"
            - token : 9 (NUMBER), sequence: "2"
    - Il se sert d'une classe Token qui contient des membres statiques renseignant tous les Tokens possibles.
    
- Nous avons ensuite le **Parser** :
    - Son rôle est d'à partir de la liste de tokens du tokenizer de renvoyer le noeud principal de l'arbre d'expressions qui permet d'avoir une priorité sur les opérations
        - Nous avons donc différentes classes représentant différents types d'entrées que nous pourrions avoir **ConstantNode** pour NUMBER, **AdditionNode** pour PLUS, **FunctionExpressionNode** pour f(x)
        - D'autres classes utiles comme **SequenceNode** permettent d'avoir plus que 2 branches à un noeud PLUS ou il peut y en avoir une multitude
            - Par exemple : pour l'entrée 2+3+2
        - Nous avons également une classe **ParserException** qui permet de définir une erreur de parsing. 
