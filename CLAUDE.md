# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is **PI4_2526_PBase**, a student assignment project for the ADDA (Algorithm Design and Data Structures) course (2025–2026). The goal is to implement three combinatorial optimization exercises using multiple algorithmic techniques: **Backtracking (BT)**, **A\* Search**, and **Dynamic Programming (PD)**.

The project depends on the ADDA v6 framework located at:
`C:\Users\User\Documents\US\Segundo\Segundo cuatri\ADDA\lab\practica4\adda_v6-master\adda_v6-master\`

## Build System

- **IDE**: Eclipse (no Maven/Gradle). Build and run via Eclipse Run Configurations.
- **Java**: Java 9+ modules. The module is declared in `src/module-info.java` as `pi4Base_2526`, requiring the `grafos` module.
- **No standalone build command**: must be built inside Eclipse with the full multi-project workspace open (PI4_2526_PBase + all ADDA v6 projects as dependencies).

## Running Tests

Tests are plain `main` classes (no JUnit). Each is run as a Java Application in Eclipse:

- `tests.ejercicio1.TestsBT` / `TestsAStar` / `TestsPD` / `TestsBTManual` / `TestsPDManual`
- `tests.ejercicio2.*` — same structure
- `tests.ejercicio3.*` — same structure

Data classes (`Datos1`, `Datos2`, `Datos3`) each have their own `main` to verify file parsing. Input files live under `datos_entrada/ejercicio{1,2,3}/DatosEntrada{1,2,3}.txt`.

## Architecture

### Problem structure (all three exercises follow the same pattern)

Each exercise has:
- **`DatosX.java`** — static data class. Call `DatosX.iniDatos(file)` before any other method. Data is stored in static fields; re-calling `iniDatos` replaces them.
- **`SolucionX.java`** — solution wrapper. Receives a `List<Integer>` of actions (or a `GraphPath`) and evaluates validity.
- **`tests/ejercicioX/TestsBT.java`** etc. — empty stubs with `// TODO Consulte los ejemplos del repositorio`.

### Implementing an algorithm (BT / A\* / PD)

Look at the ADDA v6 `EjemplosAlgoritmos` module for complete worked examples (knapsack, coin change, graph colouring, etc.). The pattern is always:

1. **Vertex record** implementing `VirtualVertex<V, E, Action>`:
   - `goal()` — true when the last decision has been made
   - `goalHasSolution()` — whether the goal state is feasible
   - `isValid()` — pruning condition (return false to cut the branch)
   - `actions()` — list of alternatives at this vertex
   - `neighbor(a)` / `edge(a)` — produce the next vertex/edge for action `a`
   - `greedyAction()` — best greedy action (used to seed BT)

2. **Edge record** implementing `VirtualEdge<V, E>` (usually a simple wrapper over the action).

3. **Heuristic class** (for A\*): a static method `heuristic(V v)` returning an admissible `Double` estimate.

4. **Build and run the algorithm**:
   ```java
   // BT with greedy initialisation:
   EGraph<MyVertex, MyEdge> graph = EGraph.virtual(initialVertex)
       .pathType(PathType.Sum)
       .type(Type.Max)          // or Min
       .heuristic(MyHeuristic::heuristic)
       .build();
   BT<MyVertex, MyEdge, MySolution> bt = BT.ofGreedy(graph);
   Optional<GraphPath<MyVertex, MyEdge>> gp = bt.search();
   MySolution sol = MyVertex.getSolucion(gp.get());

   // A*:
   AStar<MyVertex, MyEdge> as = AStar.of(graph);
   Optional<GraphPath<MyVertex, MyEdge>> gp = as.search();
   ```

### Exercise-specific notes

| Exercise | Problem | Key constraint | Data accessors |
|---|---|---|---|
| 1 | Select candidates (maximise valuation, cover all skills, within budget, no incompatibilities) | `Datos1.getPresupuestoMax()`, `getSonIncompatibles(i,j)` | Static `Datos1.*` |
| 2 | Assign elements to containers (each element goes to exactly one compatible container, respect capacity) | `Datos2.getPuedeUbicarse(elem, cont)`, `getTamElemento(i)`, `getTamContenedor(j)` | Static `Datos2.*` |
| 3 | Route through a city graph visiting monuments within a time/effort budget | `Datos3.grafo` (JGraphT), `Datos3.maxTime`, `tiempo(i,j)`, `esfuerzo(i,j)` | Static `Datos3.*` |

### Key ADDA v6 library classes

| Class | Package | Purpose |
|---|---|---|
| `BT<V,E,S>` | `us.lsi.graphs.alg` | Backtracking with optional greedy seed |
| `AStar<V,E>` | `us.lsi.graphs.alg` | A\* search |
| `EGraph<V,E>` | `us.lsi.graphs.virtual` | Builder for virtual search graphs |
| `VirtualVertex<V,E,A>` | `us.lsi.graphs.virtual` | Interface to implement for vertices |
| `GreedyOnGraph<V,E>` | `us.lsi.graphs.alg` | Greedy traversal (used to seed BT) |
| `GraphsReader` | `us.lsi.graphs` | Parses graph files for exercise 3 |
| `List2`, `Set2`, `String2`, `Files2` | `us.lsi.common` | Collection/IO utilities |

### Graph file format (exercise 3)

`GraphsReader.newGraph(file, Interseccion::ofFormat, Calle::ofFormat, Graphs2::simpleGraph)` parses a text file where vertex lines carry `id [name relevance]` and edge lines carry `src dst tiempo esfuerzo`.
