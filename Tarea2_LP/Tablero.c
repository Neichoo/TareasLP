#include "Tierra.h"
#include "Bomba.h"
#include "Tablero.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void*** tablero;
int dimension;
int** hay_bomba;
int** tesoro_encontrado;
int tipo_bomba;
int fila_juego;
int columna_juego;
int accion;
int pasa_turno;
int contador_bombas_encontradas;
int contador_bombas_totales;

/*
***
int n: Variable de tipo entera, la cual corresponde al tamaño del tablero (nxn) en el cual se realizará el juego.
***
La función IniciarTablero inicia el tablero del juego con la respectiva dimensión nxn, cada celda contiene un struct de tipo Tierra,
a esta Tierra se le asignan algunos valores, como la vida (la cual es aleatoria y su valor puede ser entre 1 y 3), también almacena la 
posibilidad de ser un tesoro (5% de probabilidad de serlo).
Simultáneamente se inicia otra matriz de nxn, la cual servirá mas adelante, esta matriz almacena si hay una bomba o no en esa posición, 
como es el inicio, todas valen 0. También son establecidas algunas variables globales con su respectivo valor.
***
No retorna nada debido a que es de tipo void.
***
*/
void IniciarTablero(int n){
    int i, j;
    srand(time(0));
    contador_bombas_totales = 0;
    tipo_bomba = 0;
    dimension = n;
    tablero = (void ***)malloc(n *sizeof(void **));
    hay_bomba = (int**)malloc(n * sizeof(int *));
    for(i = 0; i < dimension; i++){
        tablero[i] = (void**)malloc(n *sizeof(void*));
        hay_bomba[i] = (int*)malloc(n *sizeof(int));
        for(j = 0; j < dimension; j++){
            tablero[i][j] = (void*)malloc(sizeof(Tierra));
            hay_bomba[i][j] = 0;
            Tierra* tierra = (Tierra*)tablero[i][j];
            tierra->vida = ((rand() % 3) + 1);
            tierra->es_tesoro = (((rand() % 5) == 0)?1 : 0);
            if(tierra->es_tesoro == 1){
                contador_bombas_totales ++;
            }
        }
    }
    return;
}

/*
***
No requiere de ningún parámetro.
***
La función PasarTurno, pasa de turno en el juego, recorre todas las celdas de la matriz hay_bomba
buscando si hay una bomba o no, en caso de que haya llama a la función TryExplotar en las coordenadas donde
se encontró la bomba.
***
No retorna nada debido a que es de tipo void.
***
*/
void PasarTurno(){
    int i, j;
    for(i = 0; i < dimension; i++){
        for(j = 0; j < dimension; j++){
            if(hay_bomba[i][j] == 1){
                TryExplotar(i, j);
            }
        }
    }
    return;
}

/*
***
Bomba* b: Se requiere de la Bomba "b" que se colocará en el tablero.
int fila: Se requiere de la fila en donde se colocará la bomba.
int columna: Se requiera de la columna en donde se colocará la bomba.
***
La función ColocarBomba coloca una bomba "b", en la celda que sea indicada en los parámetros,
esta acción se realiza siempre y cuando no haya una bomba previamente en esa posición, al colocar la bomba,
esta misma es asignada al tablero, dependiendo de qué tipo de bomba es, se le asigna un contador de turnos u otro,
además de también almacenar al tipo de bomba que corresponde.(ExplosionPunto implica 1 turno y ExplosionX implica 3 turnos).
***
No retorna nada debido a que es de tipo void.
***
*/
void ColocarBomba(Bomba* b, int fila, int columna) {
    fila = fila - 1;
    columna = columna - 1;
    Tierra* tierra = (Tierra*)tablero[fila][columna];
    if(hay_bomba[fila][columna] == 0){
        tablero[fila][columna] = b;
        Bomba* bomba = (Bomba*)tablero[fila][columna];
        bomba->tierra_debajo = tierra;
        hay_bomba[fila][columna] = 1;
        if(tipo_bomba == 1){
            bomba->contador_turnos = 1;
            bomba->explotar = ExplosionPunto;
        } else if(tipo_bomba == 2){
            bomba->contador_turnos = 3;
            bomba->explotar = ExplosionX;
        }
    }
    return;
}

/*
***
No requiere de ningún parámetro.
***
La función MostrarTablero, le muestra al usuario el estado actual del tablero, va imprimiendo la vida
de cada celda, en caso de ser bomba le imprime una "o" y si ya es un tesoro encontrado, le imprimirá un "*".
***
No retorna nada debido a que es de tipo void.
***
*/
void MostrarTablero() {
    contador_bombas_encontradas = 0;
    int i, j;
    for(i = 0; i < dimension; i++){
        printf("\n");
        for(j = 0; j < dimension; j++){
            if(j == dimension-1){
                if(hay_bomba[i][j] == 1){
                    printf(" o ");
                } else{
                    Tierra* tierra = (Tierra*)tablero[i][j];
                    if((tierra->vida == 0) && (tierra->es_tesoro == 1)){
                        contador_bombas_encontradas ++;
                        printf(" * ");
                    } else{
                        printf(" %d ", tierra->vida);
                    }
                }
            } else{
                if(hay_bomba[i][j] == 1){
                    printf(" o |");
                } else{
                    Tierra* tierra = (Tierra*)tablero[i][j];
                    if((tierra->vida == 0) && (tierra->es_tesoro == 1)){
                        contador_bombas_encontradas ++;
                        printf(" * |");
                    } else{
                        printf(" %d |", tierra->vida);
                    }
                }
            }
        }
    }
    printf("\n\n");
    return;
}

/*
***
No requiere de ningún parámetro.
***
La función MostrarBombas, itera por todas las celdas del tablero y le muestra al usuario  por consola
(mediante texto), información acerca de las bombas que se encuentran actualmente en el tablero, le entrega
las coordenadas, turnos para que explote, la vida de la tierra debajo de la bomba y el tipo de bomba.
***
No retorna nada debido a que es de tipo void.
***
*/
void MostrarBombas(){
    int i, j;
    for(i = 0; i < dimension; i++){
        printf("\n");
        for(j = 0; j < dimension; j++){
            if(hay_bomba[i][j] == 1){
                Bomba* bomba = (Bomba*)tablero[i][j];
                if(bomba->contador_turnos > 0){
                    printf("Turnos para explotar: %d\n", bomba->contador_turnos);
                    printf("Coordenada: %d, %d\n", i+1, j+1);
                    if(bomba->explotar == ExplosionPunto){
                        printf("Forma Explosión: ExplosionPunto0\n");
                    } else{
                        printf("Forma Explosión: ExplosionX\n");
                    }
                    printf("Vida de tierra: %d\n", bomba->tierra_debajo->vida);
                }   
            }
        }
    }
    printf("\n");
    return;
}

/*
***
No requiere de ningún parámetro.
***
La función VerTesoros, itera por todas las celdas del tablero y muestra en la consola todos los tesoros que
se encuentran en el tablero mediante el símbolo "*". Si no hay tesoro se muestra una bomba en caso de que haya
y si tampoco hay bomba, simplemente se muestra la vida de la tierra.
***
No retorna nada debido a que es de tipo void.
***
*/
void VerTesoros(){
    int i, j;
    for(i = 0; i < dimension; i++){
        printf("\n");
        for(j = 0; j < dimension; j++){
            if(j == dimension-1){
                if(hay_bomba[i][j] == 1){
                    Bomba* bomba = (Bomba*)tablero[i][j];
                    if(bomba->tierra_debajo->es_tesoro == 1){
                        printf(" * ");
                    } else{
                        printf(" o ");
                    }
                } else{
                    Tierra* tierra = (Tierra*)tablero[i][j];
                    if(tierra->es_tesoro == 1){
                        printf(" * ");
                    } else{
                        printf(" %d ", tierra->vida);
                    }
                }
            } else{
                if(hay_bomba[i][j] == 1){
                    Bomba* bomba = (Bomba*)tablero[i][j];
                    if(bomba->tierra_debajo->es_tesoro == 1){
                        printf(" * |");
                    } else{
                        printf(" o |");
                    }
                } else{
                    Tierra* tierra = (Tierra*)tablero[i][j];
                    if(tierra->es_tesoro == 1){
                        printf(" * |");
                    } else{
                        printf(" %d |", tierra->vida);
                    }
                }
            }
        }
    }
    printf("\n\n");
    return;
}

/*
***
No requiere de ningún parámetro.
***
La función BorrarTablero, itera por todo el tablero y la matriz hay_bomba, liberando la memoria
dinámica que fue solicitada en IniciarTablero.
***
No retorna nada debido a que es de tipo void.
***
*/
void BorrarTablero(){
    int i, j;
    for(i = 0; i < dimension; i++){
        for(j = 0; j < dimension; j++){
            if(hay_bomba[i][j] == 1){
                BorrarBomba(i,j);
            }
            free(tablero[i][j]);
        }
    }
    for(i = 0; i < dimension; i++){
        free(hay_bomba[i]);
        free(tablero[i]);
    }
    free(hay_bomba);
    free(tablero);
    return;
}