# SudokuFinal

Sudoku se juega en una cuadrícula de 9 x 9 celdas. Dentro de las filas y columnas hay 9 sub paneles.
Cada fila, columna y cuadrado (9 espacios cada uno) debe completarse con los números del 1 al 9. Al completar 
las celdas se debera cumplir con 3 reglas: no repetir ningún número dentro de la fila, columna o cuadrado. 

Con el boton iniciar se inicializa el panel de juego, estableciendo el valor de ciertas celdas elegidas al azar 
basandose en una respuesta preestablecida por el creador del juego. A su vez, se inicializa un reloj 
compuesto por minutos y segundos, el cual contabilizara el tiempo que se tarde en finalizar el sudoku.

El panel inicial se conforma por algunas celdas vacias, las cuales podran ser modificadas seleccionando un boton
de la lista de botones que se encuentra por debajo del panel inicial y luego seleccionando la celda en la cual
se desea ubicar dicho valor. Las celdas iniciales no podran ser modificadas en ningun momento del juego.

Para verificar si el valor que le dimos a una celda es correcto no hace falta realizar ninguna accion adicional,
al incertar el valor en la misma celda se corroborara. En el caso de que el valor de una celda no cumpla con 
alguna de las reglas del sudoku, se marcaran aquellas celdas que entren en conflicto con un fondo gris.

Para finalizar el sudoku de manera exitosa se debera haber completado el panel de juego en su totalidad
sin ningun error. Si se desea finalizar el sudoku de manera no exitosa se dera cerrar el frame principal,
en caso de querer reiniciar el juego, debera ser cerrado el frame principal y luego volver a correr el juego.
