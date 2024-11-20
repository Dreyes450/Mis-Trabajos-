# -*- coding: utf-8 -*-
"""
Created on Sat Nov  2 21:32:50 2024

@author: Daniel
"""

import time
import random

def Merge_Sort(arr):
    n = len(arr)
    #Verificar si la lista tiene más de un elemento (Sino, ya está ordenada)
    if n > 1:
        #Encontrar el punto medio de la lista
        medio = n//2
        #Dividir la lista en dos mitades
        izq = arr[0:medio]
        der = arr[medio:n]
        #Llamar a merge sort recursivamente en ambas mitades
        Merge_Sort(izq)
        Merge_Sort(der)
        #Inicializamos los indices para recorrer cada mitad y la lista principal
        i = 0 #Para izquierda
        j = 0 #Para derecha
        k = 0 #Para original
        #Combinamos las 2 mitades en una sola lista ordenada
        while i < len(izq) and j < len(der):
            if izq[i] < der[j]:
                arr[k] = izq[i]
                i += 1
            else:
                arr[k] = der[j]
                j += 1
            k += 1
        #Agregar los elementos restantes de izquierda si los hay
        while i < len(izq):
            arr[k] = izq[i]
            i += 1
            k += 1
        #Agregar los elementos restantes de derecha si lo hay
        while j < len(der):
            arr[k] = der[j]
            j += 1
            k += 1
    return arr

