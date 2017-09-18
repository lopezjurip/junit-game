# Tarea 1

> Patricio López Juri (1263476J) (pelopez2@uc.cl)

## Razones

* La razón de cada test está puesto en los `.java` sobre el respectivo test.

## Bug fixing

### `CreatesHighLifeBoardTestCase` -> `testIsAlive`

* Probando el método `isAlive` se descubrió que `isAlive` retornaba `true` cuando la celda a consultar se salía de los bordes horizontales. Se corrigió a `false`.

* Se descubrió que el método `setCell` tenía dos errores (ambos corregidos):
  * Asignaba mal la variable `i` de largo cuando se salía horizontalmente.
  * No asignaba realmente el valor.

### `GameRulesCaseTest` -> `testBornRule`

* Se descubrió que la regla de _nacer_ estaba mal puesta. Se corrigió a `3 OR 6`.

### `GameRulesCaseTest` -> `shouldSurvive`

* Se descubrió un error booleano. Se corrió de `AND` a `OR`.
* Se descubrió que `countAliveNeighbors` se contaba a sí mismo cuando se le preguntaba por los vecinos. Se corrigió para que no lo cuente.

## Análisis

### 1) Ventajas de hacer Unit Testing

Dado un set de requerimientos y/o reglas del software es fácil crear test unitarios que validen la correctitud de las características a probar.

Así también al largo plazo el costo de corregir, mantener y mejorar el software es más barato y menos propenso a errores. Un desarrollador o incluso alguien no tan familiarizado con la programación puede leer los encabezados o descripciones de los test unitarios y entender qué están verificando.

Por ejemplo, es sencillo entender que esto verifica una regla del juego:

```java
/**
  * Test born rule according to HighLife rules.
  * "... a cell is born if it has 3 or 6 neighbors."
  */
@Test
public void testBornRule() {
  // ...
}
```

Por otro lado, si es necesario hacer un cambio en el siguiente fragmento, podemos hacer un test de regresión para comprobar que no rompimos nada anterior:

```java
/**
  * To check if, at the next iteration, the specified cell, if dead,
  * should be reborn.
  */
public boolean shouldBeBorn(int i, int j) {
  // ...
}
```

### 2) Limitantes

En primer lugar, los test unitarios no verifican el comportamiento del componente en un sistema con más componentes. Es necesario realizar test de integración una vez que pasan los test-unitarios.

El gran costo de depender de un sistema aparte es al momento de realizar los test-unitarios que dependen de la interacción con ese otro sistema. Esto porque es necesario hacer _mocks_ o simularlo de algún modo, lo que es bastante costoso en términos de horas hombre.

A un nivel más micro y en base a este juego, podemos pensar a la GUI como un componente externo. Para realizar un testing más intensivo en el proyecto deberíamos:

* Testear (unitariamente) las reglas del juego por si solas
* Realizar un _mock_ de la interfaz gráfica para poder testear el juego y su interacción con una _API gráfica_, aunque sea _"de mentiras"_.
* Realizar test unitarios de la interfaz gráfica
  * Ej: Se marca un cuadrado al llamar cierto método.
  * Ej: Se crea con los dimensiones de pixeles definidas, etc.
* Realizar un _mock_ de la lógica para probarlo con la interfaz.
* Finalmente, configurar test de integración donde probemos a la lógica funcionando con la interfaz.

Lo mencionado anteriormente requiere aún más lineas de código y trabajo que el MVP del software en sí.

En segundo lugar, los test unitarios dificultan cuando existen segmentos que dependen de variables aleatorias. Esto porque el output del programa se vuelve no determinista y se requiere:

* Configurar una _seed_ con tal de que las funciones _random_  sean deterministas.
* Correr el test múltiples veces.

Por ejemplo en el fragmento:

```java
public HighLifeBoard(int length, int width, boolean random){
  // ...

  if(random) {
    for(int i = 0; i < length; i++)
      for(int j = 0; j < width; j++) {
        // 30% chance of being alive
        this.board[i][j] = Math.random() > 0.7 ? true : false; // <- AQUÍ
      }
  }
}
```

La primera opción puede traer problemas dificultando la cobertura del test, la segunda es muy ingenua y puede traer problemas (y costos) al tener sistemas de CI.
