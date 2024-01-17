#lang scheme
;Funcion checkear:
;  Esta funcion verifica si la cantidad coincide con el largo
;  de la lista.
;Parametros:
;  cantidad: Es el supuesto numero de elementos que tiene la lista.
;  lista: La lista que contiene los elementos para ver si coinciden con la cantidad.
;Retorno:
;  Al ejecutar la funcion mediante la linea de comandos, se retornara
;  #t o #f dependiendo de si se cumplen o no las diferentes condiciones.
(define (checkear cantidad lista)
  (cond
    ((= cantidad 0) (null? lista))
    ((null? lista) (= cantidad 0))
    (else (checkear (- cantidad 1) (cdr lista)))
    )
  )