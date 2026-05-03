===========================================================
  AI Lab - Java Conversion
  Original: C++/Prolog  -->  Converted: Java
===========================================================

REQUIREMENTS
------------
- Java 17 or higher (uses records in missionaries_and_cannibals/Main.java)
- No external libraries needed — pure Java standard library only
- javac and java must be on your PATH

===========================================================
PROJECT STRUCTURE
===========================================================

AI_Lab_Java/
├── 8_puzzle_problem/
│   ├── AStar.java          - A* Search (f = g + h, misplaced tiles heuristic)
│   ├── BestFirst.java      - Greedy Best First Search (h only)
│   ├── DFS.java            - Depth First Search
│   ├── DLS.java            - Depth Limited Search
│   └── Main.java           - BFS (shortest path)
│
├── 8_queens/
│   ├── CSP.java            - Constraint Satisfaction + Backtracking
│   ├── DFS.java            - DFS for queen placement
│   ├── DLS.java            - Depth Limited Search
│   └── Main.java           - BFS queen placement
│
├── ai_non_ai_technique/
│   └── Main.java           - Tic-Tac-Toe: Non-AI (first-cell) vs AI (Minimax)
│
├── animal_identification/
│   └── AnimalIdentification.java  - Expert system (from Prolog) - interactive Q&A
│
├── bird_classification/
│   └── BirdClassification.java    - Rule-based bird classifier (from Prolog)
│
├── cryptarithmetic/
│   └── CSP.java            - SEND + MORE = MONEY solver via backtracking
│
├── disease_classification/
│   └── DiseaseClassification.java - Disease diagnosis expert system (from Prolog)
│
├── family_tree/
│   └── FamilyTree.java     - Family relationships rule engine (from Prolog)
│
├── family_tree_expert_system/
│   └── FamilyTreeExpertSystem.java - Interactive family tree query menu (from Prolog)
│
├── graph_coloring/
│   └── CSP.java            - Graph coloring via CSP backtracking
│
├── maze_problem/
│   ├── DFS.java            - DFS maze solver
│   ├── DLS.java            - Depth Limited Search maze solver
│   └── Main.java           - BFS shortest path maze solver
│
├── missionaries_and_cannibals/
│   ├── DFS.java            - DFS solution
│   ├── DLS.java            - Depth Limited Search solution
│   └── Main.java           - BFS shortest solution
│
├── sentiment_analysis/
│   └── SentimentAnalysis.java  - Keyword-based sentiment classifier (from Prolog)
│
├── shortest_route_finding/
│   ├── AStar.java          - A* on weighted graph A->G
│   └── BestFirstSearch.java - Best First Search on graph A->G
│
├── skillset_matching/
│   └── SkillsetMatching.java  - Job matching expert system (from Prolog)
│
├── vege_fruit_classification/
│   └── VegeFruitClassification.java - Fruit/vegetable classifier (from Prolog)
│
├── vehicle_classification/
│   └── VehicleClassification.java   - Vehicle rule classifier (from Prolog)
│
└── water_jug_bfs/
    ├── DFS.java            - DFS water jug solver
    ├── DLS.java            - Depth Limited Search water jug solver
    └── Main.java           - BFS water jug solver

===========================================================
HOW TO COMPILE AND RUN
===========================================================

--- OPTION 1: Compile and run a single file ---

  cd AI_Lab_Java/<folder>
  javac <FileName>.java
  java <ClassName>

  Example:
    cd AI_Lab_Java/8_puzzle_problem
    javac Main.java
    java Main

--- OPTION 2: Compile all files at once (from project root) ---

  find AI_Lab_Java -name "*.java" -exec javac {} \;

--- OPTION 3: Compile and run all (Unix/Mac/Linux shell script) ---

  for f in $(find AI_Lab_Java -name "*.java"); do
    dir=$(dirname "$f")
    javac "$f"
    echo "Compiled: $f"
  done

===========================================================
INTERACTIVE PROGRAMS (require user input)
===========================================================

These programs prompt the user for input during runtime:

1. ai_non_ai_technique/Main.java
   - Choose mode (1=Non-AI, 2=AI Minimax), then enter row/col for each move

2. animal_identification/AnimalIdentification.java
   - Answer yes/no to feature questions to identify an animal

3. disease_classification/DiseaseClassification.java
   - Answer yes/no to symptom questions to get a diagnosis

4. family_tree_expert_system/FamilyTreeExpertSystem.java
   - Choose from menu options and enter a person's name

5. skillset_matching/SkillsetMatching.java
   - Answer yes/no to skill questions to get a job recommendation

6. sentiment_analysis/SentimentAnalysis.java
   - Type a sentence to get positive/negative/neutral classification

===========================================================
NOTES ON PROLOG CONVERSIONS
===========================================================

The following files were originally written in Prolog (.pl) and have
been reimplemented as Java rule-based systems:

  animal.pl          --> AnimalIdentification.java  (if-else + Q&A verify)
  birds.pl           --> BirdClassification.java     (static rule methods)
  disease.pl         --> DiseaseClassification.java  (if-else + Q&A verify)
  tree.pl            --> FamilyTree.java             (relation methods + loops)
  tree.pl (expert)   --> FamilyTreeExpertSystem.java (interactive menu)
  sentiment.pl       --> SentimentAnalysis.java      (keyword counting)
  skill.pl           --> SkillsetMatching.java        (forall check + Q&A)
  vegetables.pl      --> VegeFruitClassification.java (map-based facts)
  vehicles.pl        --> VehicleClassification.java   (map-based facts)

Each Prolog predicate is translated to a Java method.
Facts are stored in static Maps/Sets.
Rules become boolean methods.
Dynamic predicates (assert/retract) become Maps with user input caching.

===========================================================
