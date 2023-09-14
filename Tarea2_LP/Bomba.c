#include "Tierra.h"
#include "Bomba.h"
#include "Tablero.h"
#include <stdio.h>
#include <stdlib.h>

extern int tipo_bomba;
extern int** hay_bomba;
extern int dimension;

/*
***
int fila: Se requiere de la fila en donde se intentará explotar la bomba si es posible.
int columna: Se requiera de la columna en se intentará explotar la bomba si es posible.
***
La función TryExplotar, intenta explotar una bomba en unas coordenadas entregadas, se reduce
el contador de la bomba en 1, luego si el contador está en 0, se llama a la explosión de la bomba
correspondiente, dependiendo del tipo de bomba que es.
***
No retorna nada debido a que es de tipo void.
***
*/
void TryExplotar(int fila, int columna){
    Bomba* bomba = (Bomba*)tablero[fila][columna];
    if(hay_bomba[fila][columna] == 1){
        bomba->contador_turnos --;
        if(bomba->contador_turnos == 0){
            if(bomba->explotar == ExplosionPunto){
                ExplosionPunto(fila, columna);
            } else{
                ExplosionX(fila, columna);
            }
        }
    }
    return;
}

/*
***
int fila: Se requiere de la fila en donde se borrará la bomba.
int columna: Se requiera de la columna en donde se borrará la bomba.
***
La función BorrarBomba, elimina la bomba que se encuentra en la celda de las coordenadas
entregadas en la función, se restaura en el tablero la tierra_debajo y se borra la bomba liberando la memoria.
También se actualiza la matriz hay_bomba, indicando que ya no hay una bomba en esa posición.
***
No retorna nada debido a que es de tipo void.
***
*/
void BorrarBomba(int fila, int columna){
    Bomba* bomba = (Bomba*)tablero[fila][columna];
    Tierra* tierra_debajo;
    tierra_debajo = bomba->tierra_debajo;
    free(bomba);
    tablero[fila][columna] = tierra_debajo;
    hay_bomba[fila][columna] = 0;
    return;
}

/*
***
int fila: Se requiere de la fila en donde se hará la ExplosionPunto.
int columna: Se requiera de la columna en donde se hará la ExplosionPunto.
***
La función ExplosionPunto, realiza la explosión en forma de punto (solo una celda), se establece la vida
de la tierra_debajo en 0, ya que esta explosión resta 3 a la vida y el máximo de vida que puede tener una tierra
es 3. Luego se llama a BorrarBomba para eliminar la bomba en esa posición.
***
No retorna nada debido a que es de tipo void.
***
*/
void ExplosionPunto(int fila, int columna){
    Bomba* bomba = (Bomba*)tablero[fila][columna];
    Tierra* tierra_debajo;
    tierra_debajo = bomba->tierra_debajo;
    tierra_debajo->vida = 0;
    BorrarBomba(fila, columna);
    return;
}

/*
***
int fila: Se requiere de la fila en donde se hará la ExplosionX.
int columna: Se requiera de la columna en donde se hará la ExplosionX.
***
La función ExplosionX, realiza la explosión en forma de x, impactando a 5 celdas, en esta explosión se le resta
1 a la vida de cada celda implicada en la explosión, mediante dos arreglos se calculan las posiciones a partir del
origen de la explosión, se consideran los casos en los que la explosión se sale del rango de la mariz, dando la vuelta
al tablero, después se llama a BorrarBomba en la posición original en donde estaba la bomba, en los demas caso se baja la
vida de la tierra o tierra_debajo en su defecto.
***
No retorna nada debido a que es de tipo void.
***
*/
void ExplosionX(int fila, int columna){
    int i, fila_og, columna_og;
    fila_og = fila;
    columna_og = columna;
    int modificaciones_x[5] = {0, -1, 1, -1, 1};
    int modificaciones_y[5] = {0, -1, -1, 1, 1};
    for(i = 0; i < 5; i++){
        fila = fila_og + modificaciones_x[i];
        columna = columna_og + modificaciones_y[i];
        if(columna == -1){
            columna = dimension - 1;
        } else if(columna == dimension){
            columna = 0;
        }
        if(fila == -1 ){
            fila = dimension - 1;
        } else if(fila == dimension){
            fila = 0;
        }
        if((fila == fila_og) && (columna == columna_og)){
            Bomba* bomba = (Bomba*)tablero[fila][columna];
            Tierra* tierra_debajo;
            tierra_debajo = bomba->tierra_debajo;
            tierra_debajo->vida --;
            if(tierra_debajo->vida < 0){
                tierra_debajo->vida = 0;
            }
            BorrarBomba(fila, columna);

        } else if(hay_bomba[fila][columna] == 1){
            Bomba* bomba = (Bomba*)tablero[fila][columna];
            Tierra* tierra_debajo;
            tierra_debajo = bomba->tierra_debajo;
            tierra_debajo->vida --;
            if(tierra_debajo->vida < 0){
                tierra_debajo->vida = 0;
            }
        } else{
            Tierra* tierra = (Tierra*)tablero[fila][columna];
            tierra->vida --;
            if(tierra->vida < 0){
                tierra->vida = 0;
            }
        }
    }
    return;
}