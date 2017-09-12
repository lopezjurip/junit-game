# Tarea 1

> Patricio López Juri

## Razones

* La razón de cada test está puesto en los `.java` junto al respectivo test.

## Bug fixing

### `CreatesHighLifeBoardTestCase` -> `testIsAlive`

* Probando el método `isAlive` se descubrió que `isAlive` retornaba `true` cuando la celda a consultar se salía de los border horizontales. Se corrió a `false`.

Se descubrió que el método `setCell` tenía dos errores:
* Asignaba mal la variable `i` de largo cuando se salía horizontalmente.
* No asignaba realmente el valor.

### `GameRulesCaseTest` -> `testBornRule`

* Se descubrió que la regla de nacer estaba mal puesta. Se corrigió a 3 OR 6.

### `GameRulesCaseTest` -> `shouldSurvive`

* Se descubrió un error booleano. Se corrió a OR.
* Se descubrió que `countAliveNeighbors` contaba el mismo cuadro cuando se les preguntaba por los vecinos. Se corrigió para que no lo cuente.
