# PUZZLE (TILTING TILES)

Este proyecto simula un puzzle de baldosas llamado "Tilting tiles", el cual se basa en la interacción entre dos tableros llamados (Starting y Ending) dodne el usuario puede hacer diferentes acciones sobre uno de los dos tableros. Este preyoceto se encuentra organizado en tres paquetes principales, shapes, puzzle y test; los cuales corresponden a las diferentes áreas funcionales del programa. Como lo son: 
#### - La lógica del sistema 
#### - La interfaz interactiva para el usuario
#### - Las pruebas de unidad de funcionamiento


### Para obtener una copia y probar el proyecto en funionamiento necesitas: 
#### - Java (jdk): Necesitas tener instalada una versión de Java Development Kit el cual es un interprete. Lo puedes descargar desde [https://www.oracle.com/co](https://www.oracle.com/co/java/technologies/downloads/) 
#### - Editor de código: Todo el código fuente de este proyecto fue implementado en el editor IntelliJ IDEA, no obstante puedes usar cualquier editor que sea de tu preferencia. 

# Estructura de Diseño 

Para la planificación y estructuración del proyecto, se utilizó Astah para modelar el diagrama de clases, con el objetivo de representar de manera clara y detallada las relaciones entre las distintas clases. Este diagrama no solo proporciona una visión general del sistema, sino que también incluye información específica sobre los atributos y métodos de cada clase. Además, se destacó el uso de conceptos clave como la herencia y las relaciones de asociación entre las clases, lo que facilita la comprensión de la arquitectura del sistema y sus interacciones.

# Estructura del Proyecto

El proyecto está dividido en dos fases: 
- **Shapes**:
  En esta sección del preoyecto estará toda la representación gráfica (figuras geométricas), que se usaron para representar:
    - Baldosas (fichas)
    - Pegante
    - Tablero
 - **Puzzle**:
   En está sección del proyecto estará toda la lógica funcional implementada para el simolador del puzzle. Donde se pueden hacer varias acciones como:
    - Aplicar pegante (addGlue)
    - Agregar baldosa (addTile)
    - Borrar baldosa (deleteTile)
   Entre otras muchas funciones que están implementadas dentro de este simulador del Puzzle "Tilting tiles".
- **Test**:
  Por último, en esta parte del proyecto estarán las pruebas de unidad de los métodos funcionales del puzzle.

  # Pruebas de Aceptación 
1. La prueba de aceptación **acceptanceTestShouldTiltCorrectlyWithDiferentsTypeOfTilesByCarmona** tiene como objetivo cubrir la mayor cantidad posible de funcionalidades del proyecto, asegurando que todas las características principales se comporten según lo esperado. Para ello, se inicia configurando un tablero, que servirá como entorno de prueba. A continuación, se realizan movimientos de "tilt" en diferentes direcciones, evaluando cómo responde el sistema a estos cambios.
Además, se incorporan distintos tipos de baldosas al tablero, lo que permite analizar sus comportamientos individuales y en conjunto, ampliando el alcance de las pruebas de aceptación. Esto garantiza que las interacciones entre las baldosas y el tablero se ejecuten de manera correcta y consistente, validando así los requisitos y la estabilidad del sistema.

2. La prueba de aceptación acceptanceTestShouldMakeHoleAndDeleteDiferentsTypeOfTileByIdarraga está diseñada para verificar que el simulador Puzzle maneje adecuadamente la creación de huecos en el tablero y respete las características y restricciones de las baldosas especiales (FixedTile y FlyingTile). Esta prueba inicia configurando un tablero específico, donde se crea un hueco en una posición determinada mediante el método makeHole. Luego, se aplican movimientos de "tilt" en diferentes direcciones para evaluar cómo el sistema ajusta las posiciones de las fichas cuando se encuentran con algún hueco. A continuación, se agregan baldosas especiales de tipo FlyingTile y FixedTile en posiciones estratégicas para observar sus comportamientos individuales y en conjunto, asegurando que cada tipo de baldosa respete sus restricciones de movimiento.

# Informe análisis estático
Para realizar el análisis estático del código, optamos por utilizar Quodana, ya que surgieron dificultades al intentar hacerlo mediante PMD. Quodana nos proporcionó un informe detallado que evaluó el cumplimiento de las reglas de Java y las buenas prácticas de codificación, permitiéndonos identificar áreas de mejora en el proyecto.

El informe de Quodana mostró de manera clara los cambios implementados y destacó las mejoras realizadas en el código. Esto nos ayudó a optimizar la calidad del software y a asegurar que el proyecto cumpla con estándares elevados de mantenimiento y legibilidad.

Para ver a mas detalle el informe podrás ingresar dando click en este link [https://qodana](https://qodana.cloud/projects/ANl4K/reports/LML5b)
  





