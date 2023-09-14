#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "Tierra.h"
#include "Bomba.h"
#include "Tablero.h"

/*
***
int argc: Número de argumentos en la línea de comandos.
char const *argv[]: Un puntero a un arreglo de caracteres constantes.
***
La función main es el núcleo del programa en si, esta función enlaza todo lo necesario para poder realizar un uso correcto
del programa, permite al usuario seleccionar opciones que lo ayudarán a avanzar en el juego y finalizar este mismo.
El objetivo del juego es encontrar todos los tesoros que existen en el tablero.
***
Retorna 0 en caso de que el programa se ejecute como lo esperado.
***
*/
int main(int argc, char const *argv[])
{
    int forzar_salida;
    int contador, fila_x, columna_y;
    extern int dimension;
    extern int tipo_bomba;
    extern int accion;
    extern int pasa_turno;
    extern int contador_bombas_encontradas;
    extern int contador_bombas_totales;
    forzar_salida = 1;
    pasa_turno = 0;
    contador = 1;
    while (1) {
        printf("Bienvenido a TreasureFinder!\nIndique el tamaño del tablero a jugar:\n1.7x7 2.10x10 3.12x12 \nSu input: ");
        scanf("%d", &dimension);
        if(dimension == 1){
            dimension = 7;
            printf("\n");
            break;
        } else if(dimension == 2){
            dimension = 10;
            printf("\n");
            break;
        } else if(dimension == 3){
            dimension = 12;
            printf("\n");
            break;
        } else{
            printf("Error: Ingrese una opción válida (1, 2 o 3).\n");
        }
    }
    IniciarTablero(dimension);
    printf("Empezando juego... ¡listo!\nTablero (Turno %d)\n", contador);
    MostrarTablero();
    while (1){
        if(contador >= 2){
            MostrarTablero();
            printf("Tablero (Turno %d)\n",contador);
        }
        if(contador_bombas_encontradas == contador_bombas_totales){
            printf("Felicitaciones, has ganado!\n");
            BorrarTablero();
            break;
        }
        printf("Seleccione una acción:\n1.Colocar Bomba 2.Mostrar Bombas 3.Mostrar Tesoros 4.Forzar Salida\nEscoja una opción: ");
        scanf("%d", &accion);
        if(accion == 1){
            Bomba* b= (Bomba*)malloc(sizeof(Bomba));
            printf("Indique coordenadas de la bomba\nFila: ");
            scanf("%d", &fila_x);
            printf("\nColumna: ");
            scanf("%d", &columna_y);
            if((fila_x > 0) && (fila_x <= dimension) && (columna_y > 0) && (columna_y <= dimension)){
                printf("\nIndique forma en que explota la bomba\n1.Punto  2.X\nSu input: ");
                scanf("%d", &tipo_bomba);
                ColocarBomba(b, fila_x, columna_y);
                PasarTurno();
                contador ++;
            }
        } else if(accion == 2){
            MostrarBombas();
        } else if(accion == 3){
            VerTesoros();
        } else if(accion == 4){
            forzar_salida = 0;
        }
        if(forzar_salida == 0){
            printf("Se procederá a cerrar el juego!\n");
            BorrarTablero();
            break;
        }
    }
    return 0;
}