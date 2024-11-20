# -*- coding: utf-8 -*-
"""
Created on Thu Sep 26 22:47:31 2024

@author: Daniel
"""

import time 

# Función para calcular Fibonacci
def fibonacci_1(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fibonacci_1(n-1) + fibonacci_1(n-2)

# Ejemplo de uso
numerito = 50
inicio = time.time()
print(f"La serie de Fibonacci para el número {numerito} es {fibonacci_1(numerito)}")
fin = time.time()
print(f"El tiempo de ejecución es: {fin - inicio} s")
