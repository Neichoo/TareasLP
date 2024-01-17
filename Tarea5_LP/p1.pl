/*
 *** Hechos:
 * cifrado.
 ***
 *** Reglas:
 * descifrar.
 ***
 *** Funcionamiento:
 * La regla descifrar, toma el input del usuario y
 * de a 2 bits comprueba a que cifrado pertenece, luego
 * entrega la respuesta correspondiente.
 ***
*/

cifrado([0,0], a).
cifrado([0,1], g).
cifrado([1,0], c).
cifrado([1,1], t).

descifrar([], []).
descifrar([Bit1,Bit2|RestoDeBits], [Base|RestoDeBase]):-
    cifrado([Bit1,Bit2],Base),
    descifrar(RestoDeBits,RestoDeBase).
