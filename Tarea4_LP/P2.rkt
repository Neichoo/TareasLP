#lang scheme
;Funcion cantidades_simple:
;  Esta funcion usa recursion simple, para aplicarle a un numero
;  una lista de funciones, para despues almacenar sus resultados.
;Parametros:
;  base: Este es el numero base con el que se calculara las funciones.
;  lista: Esta es la lista de funciones que seran evaluadas.
;Retorno:
;  Retorna una lista que contiene los resultados del numero en las distintas funciones.
(define (cantidades_simple base lista)
  (cond
    ((null? lista) '())
    (else
     (cons ((car lista)base)(cantidades_simple base(cdr lista)))
     )
    )
  )

;Funcion cantidades_cola:
;  Esta funcion usa recursion de cola, para aplicarle a un numero
;  una lista de funciones, para despues almacenar sus resultados, tambien usa
;  una funcion auxiliar que procesa los elementos de "lista" para luego producir
;  la nueva lista deseada.
;Parametros:
;  base: Este es el numero base con el que se calculara las funciones.
;  lista: Esta es la lista de funciones que seran evaluadas.
;Retorno:
;  Retorna una lista que contiene los resultados del numero en las distintas funciones.
(define (cantidades_cola base lista)
  (define (recorrer lista aux)
    (cond
      ((null? lista) (reverse aux))
      (else
       (recorrer (cdr lista) (cons ((car lista) base) aux))
       )
      )
    )
  (recorrer lista '())
  )