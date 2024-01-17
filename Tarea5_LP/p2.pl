/*
 *** Hechos:
 * cerradura,cercania.
 ***
 *** Reglas:
 *verificar.
 ***
 *** Funcionamiento:
 * La regla verificar, calcula un E, el cual es la diferencia absoluta
 * entre la n-esima cifra de la cerradura original y la n-esima cifra
 * que ingresa el usuario.(La cerradura original es un hecho elegido
 * arbitrariamente en este caso.) Luego este E se pasa junto con el R
 * donde se quiere guardar el resultado, a un hecho llamado cercania,
 * el cual verifica el valor de E y le asigna la salida correspondiente a R.
 ***
*/
cerradura(1,4,5,1,0).
verificar(X1,X2,X3,X4,X5,R):-
    cerradura(X11,X12,X13,X14,X15),
    E is (abs(X1-X11) + abs(X2-X12) + abs(X3-X13) + abs(X4-X14) + abs(X5-X15))/5,
    cercania(E,R).
cercania(E,'Contraseña descubierta'):-
    E==0.
cercania(E,'Cerca'):-
    E<1.
cercania(E,'Lejos'):-
    E>=1.
