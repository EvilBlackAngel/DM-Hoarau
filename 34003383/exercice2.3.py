# coding: utf-8

from random import *
from queue import *
from tkinter import *
from threading import Thread, RLock
import time

class ThreadProducteur(Thread):
    def __init__(self,texte, q, tempsSommeil, nom):
        Thread.__init__(self)
        self.texte = Label(fen, text="Thread Producteur : Ajout de l'entier .")
        self.texte.pack()
        self.message = texte
        self.q = q
        self.n=0
        self.tempsSommeil = tempsSommeil
        self.name = nom
        self.daemon = True
      


    def run(self):
        while True:
            self.ajout = randint(1,100)
            self.q.put(self.ajout, block=True,timeout=None)
            self.texte["text"] = "Thread Producteur : Ajout de l'entier {} .".format(self.ajout)
            self.ajout=0
            self.texte.pack(expand=True, timeout=None)
            time.sleep(self.tempsSommeil)
            

        
class ThreadConsommateur(Thread):
    def __init__(self,texte, q, tempsSommeil, nom):
        Thread.__init__(self)
        self.texte = Label(fen, text="Thread Consommateur: Retrait de .")
        self.texte.pack()
        self.message= texte
        self.q = q
        self.tempsSommeil = tempsSommeil
        self.name = nom
        self.daemon = True
        

        
    def run(self):
        while True:
            self.retrait = self.q.get(block=True, timeout=None)
            self.texte["text"] = "Thread Consommateur: Retrait de {} .".format(self.retrait)
            self.texte.pack(expand=True, fill='both')
            time.sleep(self.tempsSommeil)

def Clavier(event):
    touche = event.keysym
    if touche == 'Return':
        fen.destroy()
        
if __name__ == "__main__":
    # Création de l'entier partagé.
    q = Queue(maxsize=5)
    fen = Tk()
    fen.title('Opérations sur file')
    fen.state('zoomed')
    fen.bind("<Key>", Clavier)
    texte = Text(fen, wrap=NONE)
 
    # Création et lancement des threads.
    p = ThreadProducteur(texte,q, 1, "p")
    c = ThreadConsommateur(texte,q, 1, "c")

    
    p.start()
    c.start()
    
    # Attente frappe utilisateur pour stopper les threads.
    fen.mainloop()

    



