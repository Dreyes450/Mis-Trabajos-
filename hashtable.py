# -*- coding: utf-8 -*-
"""
Created on Mon Sep 23 01:10:29 2024

@author: Daniel
"""

class HashTable:
    def __init__(self, size):
        # Inicializamos la tabla hash con una lista de tamaño fijo
        self.size = size
        self.table = [[] for _ in range(size)]  # Creamos una lista de listas para manejar colisiones

    def hash_function(self, key):
        # Calculamos el índice usando el hash de la clave
        return hash(key) % self.size

    def insert(self, key, value):
        # Insertamos una clave-valor en la tabla
        index = self.hash_function(key)
        bucket = self.table[index]
        for i, kv in enumerate(bucket):
            k, v = kv
            if k == key:
                bucket[i] = (key, value)  # Si la clave ya existe, actualizamos el valor
                return
        bucket.append((key, value))  # Si no, añadimos la nueva clave-valor

    def get(self, key):
        # Buscamos un valor basado en la clave
        index = self.hash_function(key)
        bucket = self.table[index]
        for k, v in bucket:
            if k == key:
                return v  # Si la clave se encuentra, retornamos el valor
        return None  # Si la clave no está, retornamos None

    def delete(self, key):
        # Eliminamos una clave de la tabla
        index = self.hash_function(key)
        bucket = self.table[index]
        for i, kv in enumerate(bucket):
            k, v = kv
            if k == key:
                del bucket[i]  # Eliminamos el par clave-valor
                return True
        return False  # Si no se encuentra, retornamos False

# Ejemplo de uso
hash_table = HashTable(10)
hash_table.insert("nombre", "Carlos")
hash_table.insert("edad", 25)
hash_table.insert("ciudad", "Madrid")

print(hash_table.get("nombre"))  # Carlos
print(hash_table.get("edad"))    # 25
print(hash_table.get("ciudad"))  # Madrid

hash_table.delete("edad")
print(hash_table.get("edad"))    # None
