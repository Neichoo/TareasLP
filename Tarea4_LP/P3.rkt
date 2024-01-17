#lang scheme
;Funcion checkear:
;  Lo que hace esta funcion es verificar si la "cantidad" es igual a
;  el largo de la "lista", si no son iguales, entonces calcula la diferencia
;  entre ambas.
;Parametros:
;  cantidad: Es el numero con el cual se verificara en conjunto con el largo de la lista.
;  lista: Es la lista a verificar con la cantidad.
;Retorno:
;  Retorna la diferencia de cantidad y lista en caso de existir, con una sublista de lista.
(define (checkear cantidad lista)
  (cond
    ((= cantidad 0) (if (null? lista) '() (list (length lista) lista)))
    ((null? lista)
     (if (= cantidad 0)
         '()
         (list (- cantidad 0) '())))
    ((< (length lista) cantidad)
     (list (- cantidad (length lista)) (car lista)))
    (else (checkear (- cantidad 1) (cdr lista)))
  )
)

;Funcion armar_lista:
;  Lo que hace esta funcion es construir a partir de la lista "stock", una nueva lista
;  que contiene lo entregado por la funcion checkear.
;Parametros:
;  stock: Lista que contiene el stock a verificar de los productos.
;Retorno:
;  Retorna los productos que faltan y cuanta cantidad falta de cada producto.
(define (armar_lista stock)
  (cond
    ((null? stock)
    '())
    (else
     (cons (checkear (car(car stock)) (car(cdr(car stock)))) (armar_lista (cdr stock))))
    )
  )
 
   
