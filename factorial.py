# -*- coding: utf-8 -*-
"""
Created on Thu Sep 26 22:23:44 2024

@author: Daniel
"""

import time

# Factorial sin recursividad
def factorial_1(n):
    tamal = 1
    for i in range(2, n + 1):
        tamal = tamal * i  # Cambié la multiplicación por 'i' en vez de 1
    return tamal  # El return está fuera del ciclo

# Ejemplo de uso
numerito = 5
inicio = time.time()
print(f"El factorial de {numerito} es {factorial_1(numerito)}")
fin = time.time()
print(f"El tiempo de ejecución es: {fin - inicio} s")

# Factorial con recursividad
def factorial_2(n):
    if n == 0 or n == 1:
        return 1
    else:
        return n * factorial_2(n - 1)




    