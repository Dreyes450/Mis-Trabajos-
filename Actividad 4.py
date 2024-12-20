# -*- coding: utf-8 -*-
"""
Created on Mon Sep  9 19:00:40 2024

@author: Daniel
"""

class Nodo:
    def __init__(self, dato):
        self.dato = dato
        self.siguiente = None

class Pila:
    def __init__(self):
        self.superior = None

    def apilar(self, dato):
        print(f"Agregando {dato} a la cima de la pila")
        nuevo_nodo = Nodo(dato)
        if self.superior is None:
            self.superior = nuevo_nodo
        else:
            nuevo_nodo.siguiente = self.superior
            self.superior = nuevo_nodo

    def desapilar(self):
        # Si no hay datos en el nodo superior, regresamos
        if self.superior is None:
            print("No hay ningún elemento")
            return
        print(f"Desapilar {self.superior.dato}")
        self.superior = self.superior.siguiente

    def imprimir(self):
        # Recorrer la pila e imprimir valores
        nodo_temporal = self.superior
        while nodo_temporal is not None:
            print(f"[{nodo_temporal.dato}]", end=" ")
            nodo_temporal = nodo_temporal.siguiente
        print("\n")

# Uso de la pila
pila = Pila()
pila.apilar("Leon S Kennedy")
pila.apilar("Bob Esponja")
pila.apilar("Henry Cavill")
pila.imprimir()
pila.desapilar()
pila.imprimir()
