# -*- coding: utf-8 -*-
"""
Created on Mon Sep  9 19:03:07 2024

@author: Daniel
"""

class Cola:
    def __init__(self):
        self.items = []

    def is_empty(self):
        # Verificar que la cola está vacía
        return len(self.items) == 0

    def encolar(self, item):
        # Agregar un elemento al final de la cola
        self.items.append(item)

    def desencolar(self):
        # Eliminar el elemento frontal
        if self.is_empty():
            raise IndexError("La cola está vacía")
        return self.items.pop(0)

    def size(self):
        # Obtener el tamaño de la cola
        return len(self.items)

    def peek(self):
        # Obtener el primer elemento
        if self.is_empty():
            raise IndexError("La cola está vacía")
        return self.items[0]


class Pacientes:
    def __init__(self, nombre, hora_llegada):
        self.nombre = nombre
        self.hora_llegada = hora_llegada


def main():
    queue = Cola()
    print("Bienvenido al sistema")
    while True:
        print("Opciones:")
        print("1. Agregar paciente")
        print("2. Atender al siguiente paciente")
        print("3. Ver al siguiente paciente")
        print("4. Salir")
        option = input("Elige una opción: ")

        if option == "1":
            nombre = input("Ingrese el nombre del paciente: ")
            hora_llegada = input("Introduce la hora de llegada (ejemplo 10:00 am): ")
            paciente = Pacientes(nombre, hora_llegada)
            queue.encolar(paciente)
            print(f"Paciente {nombre} añadido a la cola\n")

        elif option == "2":
            if not queue.is_empty():
                paciente = queue.desencolar()
                print(f"Paciente {paciente.nombre} atendido\n")
            else:
                print("La cola está vacía\n")

        elif option == "3":
            if not queue.is_empty():
                paciente = queue.peek()
                print(f"Siguiente paciente a atender: {paciente.nombre}, Hora de llegada: {paciente.hora_llegada}\n")
            else:
                print("Ya no hay pacientes\n")

        elif option == "4":
            break

        else:
            print("Opción no válida\n")


if __name__ == "__main__":
    main()
