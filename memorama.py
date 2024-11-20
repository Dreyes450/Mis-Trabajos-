# -*- coding: utf-8 -*-
"""
Created on Mon Sep 23 01:05:26 2024

@author: Daniel
"""

# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

import pygame
import random

# Incializar pygame
pygame.init()

# Definir constantes
ANCHO_VENTANA = 600 #Pixeles
ALTO_VENTANA = 800
MARGEN = 10 
NUM_FILAS = 4
NUM_COLUMNAS = 4
TAMAÑO_CARTA = 100

#Colores 
Blanco = (255, 255, 255)
Negro = (0,0,0)
Granate = (128, 0, 0)
Rojo = (246, 51, 51)
Azul = (63, 172, 235)

#Cargar imagenes
imagenes = [
            "messi.png",
            "cr7.png",
            "modric.png",
            "mbappe.png",
            "neymar.png",
            "hakimi.png",
            "harry_kane.png",
            "vvd.png",
    ]
imagenes_cartas = [pygame.image.load(img) for img in imagenes]
imagen_reverso = pygame.image.load("mundial.png")

#Rendimiento imagenes a tamaño de las cartas
imagenes_cartas = [pygame.transform.scale(img, (TAMAÑO_CARTA, TAMAÑO_CARTA)) for img in imagenes_cartas]
imagen_reverso = pygame.transform.scale(imagen_reverso, (TAMAÑO_CARTA, TAMAÑO_CARTA))

#Clase pila para manejar las acciones del juego
class Pila:
    def __init__(self):
        self.elementos = []
    
    def push(self, item):
        self.elemetos.append(item)
        
    def pop(self):
        if not self.esta_vacia():
            return self.elementos.pop()
        return None
    
    def esta_vacia(self):
        return len(self.elementos) == 0
    
    def vaciar(self):
        self.elmentos = []
        
#Clase que representa cada carta del juego
class Carta:
    def __init__ (self, id_carta, imagen_frontal, pos):
        self.id_carta = id.carta #Identificador único de la carta
        self.imagen_frontal = imagen_frontal #Imagen cuando la carta esta volteada
        self.imagen_reverso = imagen_reverso #Imagen cuando la carta esta oculta
        self.esta_voleteada = False
        self.es_par_encontrado = False #Indicador de si es parte de un par ya encontrado
        self.pos = pos #Posición en la pantalla
    
    def voltear(self):
        #Método para voltear la carta
        if not self.es_par_encontrado: #Solo permite voltear si no es parte de un par ya encontrado
            self.esta_voleteada = not self.esta_voleteada
            
    def es_par(self, otra_carta):
        #Verifica si otra carta es el par de esta
        return self.id_carta == otra_carta.id_carta
        
    def dibujar(self, ventana):
        #Dibujar  la carta en la pantalla con un marco
        if self.esta_voleteada:
            ventana.bilit(self.imagen_frontal, self.pos)
        else:
            ventana.bilit(self.imagen_reverso, self.pos)
        #Dibujar un marco
        pygame.draw.rect(ventana, Granate, (*self.pos, TAMAÑO_CARTA, TAMAÑO_CARTA))

#Clse principal del juego
class Memorama:
    def __init__(self):
        #Inicializar la ventana
        self.ventana = pygame.display.set_mode((ANCHO_VENTANA, ALTO_VENTANA))
        pygame.display.set_caption("Memorama Mundial Qatar 2022")
        
        self.cartar_seleccionadas = []
        self.pila_movimientos = Pila() #Crear una pila para guardar los movimientos
        self.cartas = []
        self.mensaje = "" #Mensaje que se muestra en pantalla
        self.temporizador = 0 #Temporizador para controlar el volteo de cartas
        self.ganador = False #Variablre que indica si el jugador ha ganado
        
        #Crear los botones de reiniciar y salir
        espacio_entre_botones = 40
        boton_ancho = 120
        boton_alto = 30
        
        self.boton_reiniciar = pygame.Rect(ANCHO_VENTANA // 2 - boton_ancho - espacio_entre_botones, ALTO_VENTANA - 60, boton_ancho, boton_alto)
        self.boton_salir = pygame.Rect(ANCHO_VENTANA // 2 + espacio_entre_botones, ALTO_VENTANA - 60, boton_ancho, boton_alto)
        
        self.crear_tablero()
        
    def crear_tablero(self):
        #Metodo para crear el tablero de cartas del juego
        total_cartas = NUM_FILAS * NUM_COLUMNAS
        cartas_ids = list(range(len(imagenes_cartas))) * 2 #Duplicar las cartas
        random.shuffle(cartas_ids)
        
        #Distribuir cartas de forma uniforme en la ventana
        espacio_horizontal = (ANCHO_VENTANA + (NUM_COLUMNAS * TAMAÑO_CARTA) - ((NUM_COLUMNAS - 1)*MARGEN))//2
        espacio_vertical = (ALTO_VENTANA - 100 - (NUM_FILAS * TAMAÑO_CARTA) - ((NUM_FILAS - 1) * MARGEN))//2
        
        for fila in range(NUM_FILAS):
            for columna in range(NUM_COLUMNAS):
                id_carta = cartas_ids.pop()
                imagen_frontal = imagenes_cartas[id_carta]
                pos_x = espacio_horizontal + columna * (TAMAÑO_CARTA + MARGEN)
                pos_y = espacio_vertical + fila * (TAMAÑO_CARTA + MARGEN)
                carta = Carta(id_carta, imagen_frontal, (pos_x, pos_y))
                self.cartas.append(carta)
        
        
    def manejar_eventos(self):
        #Metodo para manejar los eventos del juego
        for evento in pygame.event.get():
            if evento.type == pygame.QUIT:
                return False
            if evento.type == pygame.MOUSEBUTTONDOWN:
                mouse_x, mouse_y = evento.pos
                
            #Verificar si se ha cliqueado el boton de reiniciar
            if self.boton_reiniciar.collidepoint(mouse_x, mouse_y):
                self.reiniciar_juego()
                
            #Verificar si se ha cliqueado el boton salir
            if self.boton_salir.collidepoint(mouse_x, mouse_y):
                return False
                
            for carta in self.cartas:
                if carta.pos[0] <= mouse_x <= carta.pos[0] + TAMAÑO_CARTA and carta.pos[1] <= mouse_y:
                    if len(self.cartar_seleccionadas) < 2 and not carta.esta_voleteada:
                        carta.voltear()
                        self.cartas_seleccioandas.append(carta)
        return True
    
    def actualizar(self):
        #Método para actualizar el estado del juego
        if len(self.cartas_seleccionadas) == 2:
            carta1, carta2 = self.cartas_seleccionadas
            if carta1.es_par(carta2):
                self.mensaje = "Encontraste el par"
                carta1.es_par_encontrado = True
                carta2.es_par_encontrado = True
                self.cartas_seleccionadas = []
            else:
                self.temporizador += 1
                if self.temporizador > 60:
                    carta1.voltear()
                    carta2.voltear()
                    self.cartas_seleccionadas = []
                    self.temporizador = 0
                    self.mensaje = ""
        #Verificar si el jugador ha ganado
        if all(carta.es_par_encontrado for carta in self.cartas):
            self.ganador = True
            self.mensaje = "GANASTE"
            
    def dibujar(self):
        #Metodo para dibujar las cartas, los botones y el mensaje en pantalla
        self.ventana.fill(Blanco)
        for carta in self.cartas:
            carta.dibujar(self.ventana)
            
        #Dibujar el boton de reiniciar
        pygame.draw.rect(self.ventana, Azul, self.boton_reiniciar)
        fuente = pygame.font.SysFont(None, 36)
        texto_reiniciar = fuente.render("Reiniciar", True, Blanco)
        texto_reiniciar_rect = texto_reiniciar.get_rect(center = self.boton_reiniciar.center)
        self.ventana.bilit(texto_reiniciar, texto_reiniciar_rect)
        
        #Dibujar el boton de salir
        pygame.draw.rect(self.ventana, Rojo, self.boton_salir)
        fuente = pygame.font.SysFont(None, 36)
        texto_salir = fuente.render("Salir", True, Blanco)
        texto_salir_rect = texto_salir.get_rect(center = self.boton_salir.center)
        self.ventana.bilit(texto_salir, texto_salir_rect)
        
        #Mostrar el mensaje de resultado
        texto_mensaje = fuente.render(self.mensaje, True, Negro)
        self.ventana.bilit(texto_mensaje, (ANCHO_VENTANA//2 - 100, 20))
        
        pygame.display.flip()
        
    def reiniciar_juego(self):
        #Metodo para reiniciar el juego y reorganizar las cartas
        self.cartas = []
        self.mensaje = ""
        self.ganador = False
        self.temporizador = 0
        self.crear_tablero()

#Función principal
def main():
    juego = Memorama()
    reloj = pygame.time.Clock()
    corriendo = True
    while corriendo:
        corriendo = juego.manejar_eventos()
        juego.actualizar()
        juego.dibujar()
        reloj.tick(60) #60 FPS para fluidez
    pygame.quit()
    
if __name__ == "__main__":
    main()   