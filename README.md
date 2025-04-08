Se requiere un sistema para gestionar un juego de dominó entre dos jugadores. 

El juego debe incluir:
 - Cada ficha tiene dos valores numéricos (ej: 2-5) y puede rotarse.
 - Espacio para colocar fichas en una cuadrícula 20x20. ( o más grande)
 - Cada uno tiene 7 fichas en su mano, un puntaje con  tiempo por turno que es un multiplicador para los puntos.

Reglas:
  - Las fichas solo pueden colocarse si coinciden en valor con los extremos del tablero.
  - Se debe llevar registro del puntaje (sumar/restar puntos por fichas colocadas dependiendo del tiempo que tardo en colocarlas, entre mas rápido más puntos le da).
  - Las fichas pueden rotarse para ajustar su posición.


Requerimientos Funcionales
  - Crear 28 fichas únicas al inicio.
  - Barajar fichas y repartir 7 a cada jugador.
  - Permitir rotar una ficha.
  - Verificar si una ficha puede colocarse en el tablero ().
  - Alternar turnos entre jugadores.
  - Finalizar la partida cuando un jugador se quede sin fichas o no haya movimientos válidos.
  - Controlar el tiempo máximo por turno.
  - Mostrar gráficamente las fichas y el tablero. (literalmente dibujar la ficha en texto)
  - Verificar y Controlar como embonan las fichas en el tablero (5-1 con 1-7 por ejemplo)


Tips:
  - Identificar las Entidades que interactuarán en el programa:
    por ejemplo clases necesarias (ej: Ficha, Jugador, Tablero, Embonable)
  - Piense en cómo gestionar la rotación de fichas (¿se requiere una enumeración?).
  - Definir cuales son las relaciones entre las entidades:
  - ¿Qué clases dependen de otras? (El tablero contiene fichas).
  - ¿Qué interfaces son necesarias para desacoplar funcionalidades? (como el tiempo o pintar las fichas)

Definir los atributos y los métodos de cada clase o interfaz:
Por ejemplo si se quiere embonar una ficha que atributos de la ficha ocupo?(valor_izq, valor_der en Ficha).
Si queremos embonar que métodos se deben ejecutar (verificarEmbonar(), embonarFicha())!

Diagrama de clases UML.
[Editor _ Mermaid Chart-2025-04-06-195102](https://github.com/user-attachments/assets/b793fcbc-7c1b-4455-bc78-cc8a2d776b61)
