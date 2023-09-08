import re
error = False
regex_forma_CUPON = r'\s*CUPON\((\d+)(?:\s*,\s*(\d+))?\)\s*'
regex_division_entera_por_cero = r"\s*//\s*0\s*"
regex_formato_invalido = r"\s*\d+\s*\(\s*\d+\s*\)\s*"
regex_cupon_invalido = r"\s*CUPON\(CUPON\(\d+\s*(,\s*\d*)?\s*\)\)\s*"
regex_caracteres_prohibidos = r"\s*[\$#!%&@¬=?¡¿\]}[{;~_'|€£¥¢₪₹₱₣₩₫:\"^]\s*"
regex_numero_operador = r"\s*\d+\s*(\-|\+|\*|\ //)\s*$|\s*(\-|\+|\*|\ //)\s*$"
regex_operador_numero = r"\s*(\-|\+|\*|\ //)\s*\d+\s*$|\s*\d+\s*$"
regex_solo_parentesis = r'^\s*(\(\s*\)\s*)+$'
patrones_primer_orden = r"\s*(\d+|ANS)\s*(\*|//)\s*(\d+|ANS)\s*"
patrones_segundo_orden = r"\s*(\d+|ANS)\s*([\+\-])\s*(\d+|ANS)\s*"
patrones_normales = r"\s*(\d+)\s*(\-|\+|\*|\ //)\s*(\d+)\s*"
regex_palabras_invalidas = r"\s*[^+\-*//ANSCUPON\(\s*\d+(?:\s*,\s*\d+)*\)\(\)]\s*"
regex_ANS = r"\bANS\b(?![\w-])"
contador_linea_actual = 0
letras = r"\s*[a-zA-Z]+\s*"

def CUPON(x,y="20"):
    '''
    *** 
    * x : Tipo string.
    * y : Tipo string, que si no es ingresado al llamar la función, toma como valor predeterminado "20".
    *** 
    Convierte los parámetros "x" e "y" en enteros para posteriormente calcular el valor de "CUPON", siendo este el "y%" de "x" truncado.
    Retorna el valor del "CUPON", tipo entero.
    '''
    truncado = int(x)
    y = int(y)
    sin_porcentaje = y/100.0
    descuento = truncado * sin_porcentaje
    return int(descuento)

def reemplazar_CUPON(bloque):
    '''
    *** 
    * bloque : Tipo string.
    *** 
    Toma el parámetro bloque para posteriormente convertirlo en una lista de lineas, luego se itera sobre ellas buscando mediante expresiones
    regulares los cupones CUPON que tiene un "x" y un "y" opcional, en caso de que exista "y" se llama a la función CUPON con los 2 parámetros, de otra
    manera se llama solamente con el valor "x", luego se reemplaza el CUPON con su valor en la respectiva linea, después de iterar sobre todas las lineas
    se van guardando las nuevas lineas reemplazadas en un nuevo bloque.
    Se retorna un bloque nuevo el cual tiene los valores de los cupones reemplazados.
    '''
    lineas = bloque.split("\n")
    contador_linea = 0
    for linea in lineas:
        CUPONES_encontrados = re.findall(regex_forma_CUPON, linea)
        for CUPONES in CUPONES_encontrados:
            x, y = CUPONES
            if y:
                valor_CUPON = CUPON(int(x), int(y))
                linea_reemplazada = re.sub(f'CUPON\({x}(?:\s*,\s*{y})?\)', str(valor_CUPON), linea)
            else:
                valor_CUPON = CUPON(int(x))
                linea_reemplazada = re.sub(f'CUPON\({x}(?:\s*,\s*{y})?\)', str(valor_CUPON), linea)
            lineas[contador_linea] = linea_reemplazada
        contador_linea += 1
    bloque_reemplazado = "\n".join(lineas)
    return bloque_reemplazado

def estan_cerrados_los_parentesis(linea):
    '''
    *** 
    * linea : Tipo string.
    *** 
    Mediante la linea que se proporciona en el parámetro, se verifica que todos los paréntesis estén bien cerrados y abiertos,
    que coincidan básicamente.
    Se retorna falso ("False") en caso de que no estén bien cerrados o verdadero ("True") en caso de que estén bien cerrados.
    '''
    contador_pa= len(re.findall(r"\(", linea))
    contador_pc = len(re.findall(r"\)", linea))
    if contador_pa != contador_pc:
        return False
    else:
        return True

def hay_division_por_cero(linea):
    '''
    *** 
    * linea : Tipo string.
    *** 
    Mediante la linea que se proporciona en el parámetro, se verifica por medio de expresiones regulares que no haya una división
    entera por cero
    Se retorna verdadero ("True") en caso de que se encuentre una division entera por cero, de lo contrario se retorna falso ("False").
    '''
    if re.search(regex_division_entera_por_cero, linea):
        return True
    else:
        return False

def tiene_formato_invalido(linea):
    '''
    *** 
    * linea : Tipo string.
    *** 
    Mediante la linea que se proporciona en el parámetro, se verifica por medio de expresiones regulares que no haya un mal uso
    de paréntesis, un caso de mal uso es por ejemplo que tenga solamente numeros dentro de ellos o que tenga operadores y nada más.
    Tambien se verifica que no haya alguna palabra extra que no sea CUPON o ANS, aunque falla cuando hay combinaciones entre estas 2 palabas
    como ANSC, ANSCUP, etc. 
    Se retorna verdadero ("True") en caso de que tenga un mal uso de parentesis o alguna palabra inválida, de lo contrario se retorna falso ("False").
    '''
    if re.search(regex_formato_invalido, linea):
        return True
    if re.search(regex_palabras_invalidas, linea):
        return True
    else:
        return False

def caracteres_y_expresiones_invalidas(linea):
    '''
    *** 
    * linea : Tipo string.
    *** 
    Verifica mediante el parámetro linea, que no haya caracteres especiales no permitidos y que no se encuentre la expresión CUPON(CUPON(numero, numero_opcional)).
    Retorna verdadero ("True") en caso de que se encuentren caracteres o expresiones no permitidos, de lo contrario se retorna falso ("False").
    '''
    if re.search(regex_caracteres_prohibidos, linea):
        return True
    if re.search(regex_cupon_invalido, linea):
        return True
    return False

def expresiones_incompletas(linea):
    '''
    *** 
    * linea : Tipo string.
    *** 
    Verifica mediante el parámetro linea, que no haya expresiones con solo un número seguido de un operador y viceversa.
    Retorna verdadero ("True") en caso de que se encuentren errores, de lo contrario se retorna falso ("False").
    '''
    linea = linea.replace("(", "")
    linea = linea.replace(")", "")
    if re.match(regex_numero_operador, linea):
        return True
    if re.match(regex_operador_numero, linea):
        return True
    return False

def palabras_mal_escritas(linea):
    '''
    *** 
    * linea : Tipo string.
    *** 
    Verifica mediante el parámetro linea, que no haya existan palabras mal escritas.
    Retorna verdadero ("True") en caso de que se encuentre una palabra mal escrita, de lo contrario se retorna falso ("False").
    '''
    linea = linea.replace("(", "")
    linea = linea.replace(")", "")
    if re.search(letras, linea):
        return True
    expresiones = re.search(patrones_normales, linea)
    while expresiones:
        numero1 = expresiones.group(1)
        operador = expresiones.group(2)
        numero2 = expresiones.group(3)
        if numero1 == None:
            return True
        elif numero2 == None:
            return True
        elif operador == None:
            return True
        linea = linea.replace(expresiones.group(), "2") 
        expresiones = re.search(patrones_normales, linea)
    return False

def solo_parentesis (linea):
    '''
    *** 
    * linea : Tipo string.
    *** 
    Mediante la linea que se proporciona en el parámetro, se verifica por medio de expresiones regulares que no hayan solo parentesis en una linea.
    Se retorna verdadero ("True") en caso de que se encuentre una linea con solo parentesis, de lo contrario se retorna falso ("False").
    '''
    if re.match(regex_solo_parentesis, linea):
        return True
    return False

def errores(bloque, arch_out):
    '''
    *** 
    * bloque : Tipo string.
    * arch_out : Tipo archivo.
    *** 
    Toma el parámetro bloque para posteriormente convertirlo en una lista de lineas, luego se itera sobre ellas verificando mediante las funciones:
    estan_cerrados_los_parentesis(linea), hay_division_por_cero(linea), tiene_formato_invalido(linea), caracteres_y_expresiones_invalidas(linea), 
    expresiones_incompletas(linea), palabras_mal_escritas(linea) y solo_parentesis(linea).
    Si es que existe algún error en la linea, en caso de que exista, se añade a una lista el numero de la linea en la que hay un error, luego se itera
    sobre las lineas nuevamente realizando la escritura respectiva dependiendo de si la linea tiene error o no.
    Se retorna verdadero ("True") en caso de que exista un error en el bloque, de lo contrario se retorna falso ("Falso").
    '''
    lineas_con_error = []
    lineas = bloque.split("\n")
    contador_linea = 0
    error = False
    for linea in lineas:
        linea = re.sub(regex_ANS, '2', linea)
        cuponcin = re.search(regex_forma_CUPON , linea)
        while cuponcin:
            linea = linea.replace(cuponcin.group(), "2") 
            cuponcin = re.search(regex_forma_CUPON , linea)
        if linea != "":
            error_eclp = estan_cerrados_los_parentesis(linea)
            error_hdpc = hay_division_por_cero(linea)
            error_tfi = tiene_formato_invalido(linea)
            error_cyei = caracteres_y_expresiones_invalidas(linea)
            error_pme = palabras_mal_escritas(linea)
            error_ei = expresiones_incompletas(linea)
            error_sp = solo_parentesis(linea)
            if (error_eclp == False) or (error_hdpc == True) or (error_tfi == True) or (error_cyei == True) or (error_pme == True) or (error_ei == True) or (error_sp == True):
                lineas_con_error.append(contador_linea)
                error = True
        contador_linea += 1
    contador_linea = 0
    if error == True:
        for linea in lineas:
            if linea != "":
                if contador_linea in lineas_con_error:
                    arch_out.write(linea + " = " + "Error" + "\n")
                else:
                    arch_out.write(linea + " = " + "Sin Resolver" + "\n")
                contador_linea += 1
    if error == True:
        arch_out.write("\n")
        return True
    elif error == False:
        return False

def operaciones_binarias(a,b,operador):
    '''
    *** 
    * a : Tipo string.
    * b : Tipo string.
    * operador : Tipo string.
    *** 
    Convierte los parámetros "a" y "b" en enteros, luego se evalá que tipo de parámetro es, luego se realiza
    el cálculo correspondiente con los parámetros. Se considera el caso borde en donde luego de una operacion quede:
    "numero // ANS" con ANS = 0, por lo que se hace print al error pero no se maneja como es esperado en la escritura del texto.
    Retorna el valor de la operación correspondiente entre "a" y "b".
    '''
    global contador_linea_actual
    a = int(a)
    b = int(b)
    if operador =="+":
        resultado = a + b
        return (resultado)
    elif operador =="-":
        resultado = a - b
        return (resultado)
    elif operador =="*":
        resultado = a * b
        return (resultado)
    elif operador =="//":
        if b == 0:
            print("Error: Ha surgido una división entera por cero que no estaba esperada, por lo que el resultado será incorrecto \n Linea: " + str(contador_linea_actual), "(aprox), el resultado de la operacion se ha cambiado por 0.")
            resultado = 0
        else:
            resultado = a // b
        return (resultado)
    else: 
        print("Operador invalido") 

def operaciones(linea, resultado_para_ANS):
    '''
    *** 
    * linea : Tipo string.
    * resultado_para_ANS : Tipo entero.
    *** 
    Se busca en la linea que se toma de los parámetros mediante expresiones regulares multiplicación o división entera en
    un ciclo while para que se resuelvan todas las coincidencias, se verifica si un numero es "ANS" para luego reemplazarlo
    por el parámetro introducido, y después se llama a la función operaciones_binarias(a,b,opreador) para resolver la operación.
    Finalmente se reemplaza en la linea original el resultado de dicha operación. Luego se realiza otro ciclo while que funciona igual
    que el anterior, solo que en este se buscan sumas y restas.
    Después de que se terminan los 2 while se retorna la linea ya resuelta.
    '''
    linea = linea.strip()
    while re.search(patrones_primer_orden, linea):
        operacion = re.search(patrones_primer_orden, linea)
        numero1 = operacion.group(1)
        operador = operacion.group(2)
        numero2 = operacion.group(3)
        if numero1 == "ANS":
            numero1 = resultado_para_ANS
        if numero2 == "ANS":
            numero2 = resultado_para_ANS
        resultado_op = operaciones_binarias(numero1, numero2, operador)
        if resultado_op < 0:
            resultado_op = 0
        linea = linea[:operacion.start()] + str(resultado_op) + linea[operacion.end():]
    while re.search(patrones_segundo_orden, linea):
        operacion = re.search(patrones_segundo_orden, linea)
        numero1 = operacion.group(1)
        operador = operacion.group(2)
        numero2 = operacion.group(3)
        if numero1 == "ANS":
            numero1 = resultado_para_ANS
        if numero2 == "ANS":
            numero2 = resultado_para_ANS
        resultado_op = operaciones_binarias(numero1, numero2, operador)
        if resultado_op < 0:
            resultado_op = 0
        linea = linea[:operacion.start()] + str(resultado_op) + linea[operacion.end():]
    return linea

def resolver_linea(linea, resultado_para_ANS):
    '''
    *** 
    * linea : Tipo string.
    * resultado_para_ANS : Tipo entero.
    *** 
    Verifica que siempre que haya un parentesis abierto en la linea, luego se resuelve lo que esté dentro de los paréntesis mediante la función
    operaciones y además usando recursión para resolver todo.
    '''
    if "(" in linea:
        parentesis_abierto = linea.rfind("(")
        parentesis_cerrado = linea.find(")", parentesis_abierto)
        operacion = linea[parentesis_abierto + 1:parentesis_cerrado]
        resultado_expresion = operaciones(operacion, resultado_para_ANS)
        nueva_expresion = (linea[:parentesis_abierto] + str(resultado_expresion) + linea[parentesis_cerrado + 1 :].lstrip())
        return resolver_linea(nueva_expresion, resultado_para_ANS)
    else:
        return operaciones(linea, resultado_para_ANS)

def desarrollo(bloque, arch_out):
    '''
    *** 
    * bloque : Tipo string.
    * arch_out : Tipo archivo.
    *** 
    La función toma el parámetro bloque para posteriormente convertirlo en una lista de lineas, luego se itera sobre ellas llamando a la función 
    resolver_linea(linea, resultado_para_ANS), se va guardando el resultado de la linea anterior por si se encuentra la expresión "ANS", se verifica
    si hay un paréntesis abierto en la línea, si es que hay parentesis entonces se resuelve la operación dentro de los paréntesis utilizando la función operaciones.
    Luego se reemplaza la expresión resuelta en la línea y se llama a la función resolver_linea de manera recursiva para continuar resolviendo el resto de la línea,
    si no hay más paréntesis, la función llama directamente a la función operaciones para resolver lo que queda de línea.
    No retorna nada.
    '''
    lineas = bloque.split("\n")
    resultado_para_ANS = 0
    bloque_nuevo = reemplazar_CUPON(bloque)
    lineas2 = bloque_nuevo.split("\n")
    linea_original = ""
    i = 0
    for linea in lineas2:
        linea_original = lineas[i]
        if lineas[i] != "" and linea != "":
            resultado_para_ANS = resolver_linea(linea, resultado_para_ANS)
            arch_out.write(linea_original + " = " + str(resultado_para_ANS) + "\n")
        i += 1
    return
        
def main():
    '''
    *** 
    * No contiene parámetros
    *** 
    Esta función es la que concecta todo el programa entre sí, une todo con un orden lógico para que todo
    funcione correctamente, se recorren las lineas y se van guardando en una variable "bloque", la que después
    se envía a funciones como errores(bloque, arch_out) para verificar si contiene errores, en caso de que no tenga, se
    llama a la función desarrollo(bloque, arch_out).
    '''
    global contador_linea_actual
    arch_in = open('problemas.txt', 'r', encoding='utf-8')
    arch_out = open('desarrollos.txt', 'w', encoding='utf-8')
    
    bloque = ""
    for linea in arch_in:
        if re.match(r'^\s*$', linea) and bloque.strip() != "":
            if not errores(bloque, arch_out):
                desarrollo(bloque, arch_out)
                arch_out.write("\n")
            bloque = ""
        else:
            bloque += linea
        contador_linea_actual += 1
    if bloque.strip() != "":
        if not errores(bloque, arch_out):
            desarrollo(bloque, arch_out)
    arch_in.close()
    arch_out.close()
    return

main()