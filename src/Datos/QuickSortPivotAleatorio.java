package Datos;

import java.util.Random;

public class QuickSortPivotAleatorio {
	
	//Función que realiza el ordenamiento QuickSort. Se envía por parámetro: el arreglo, el índice inicial y el índice final.
    public static void sort(int arreglo[], int bajo, int alto) { 
    	
    	//Condición de corte de la recursividad. Se ejecuta mientras los algoritmos se puedan recorrer.
    	if (bajo < alto) { 
    		
        	//pi es la posición del pivote, la cual ya se encuentra ordenada. A la vez funciona como índice de las particiones.
            int pi = partition(arreglo, bajo, alto); 
            
            //Se vuelve a ordenar de manera recursiva las partes divididas por el pivote
            sort(arreglo, bajo, pi-1); //izq
            sort(arreglo, pi+1, alto); //derecha
        } 
    } 
	
	 
     
    /*	
    Esta función toma como pivote al valor alojado en la ultima posición del arreglo. Coloca a valores más pequeños a su
    izquierda y deja los valores más grandes a su derecha. Al finaliza el pivote queda bien ordenado y en su posición 
    definitiva. 	
    */
    static int partition(int arreglo[], int bajo, int alto){ 
        
        random(arreglo,bajo,alto); //Coloca al final del arreglo un pivote aleatorio
        
        int pivot = arreglo[alto]; //Se toma como pivote el ultimo (que es un aleatorio)
     
        //El  índice "i" es la posición de el último menor ordenado. Arranca en -1 al principio.
        int i = (bajo-1); 
        
        //El índice j recorre desde la posición más baja del arreglo hasta la posición mas alta - 1. No llega al pivote.
        for (int j = bajo; j < alto; j++) { 
            
        	//Si el elemento actual es menor al pivote
            if (arreglo[j] < pivot) { 
            	//aumento el indice "i" porque voy a intercambiar y colocar a la izquierda un nuevo "Ultimo menor"
                i++; 
 
                //se realiza el intercambio y se coloca al elemento a la izquierda
                int aux = arreglo[i]; 
                arreglo[i] = arreglo[j]; 
                arreglo[j] = aux; 
            } 
        } 
 
        /*
        Una vez que se ha colocado todos los valores menores que el pivote a la izquierda del arreglo, se coloca el pivote 
        en la posición siguiente al "ultimo menor", es decir, se hace el intercambio entre arreglo[i+1] y  arreglo[alto].
        */
        
        int aux = arreglo[i+1]; 
        arreglo[i+1] = arreglo[alto]; 
        arreglo[alto] = aux; 
        
        //se retorna el pivote
        return i+1; 
    } 
    
    //Esta función selecciona un valor aleatorio del arreglo y lo coloca al final del arreglo para después usarlo como pivote.
  	static void random(int arreglo[],int bajo,int alto){ 
       
  		  // Se inicializa una instancia rand
          Random rand= new Random(); 
          
          //Se genera una posición aleatoria entre la posiciones pasadas por parametro. Esta va  a ser la posicion del pivote.
          int pivot = rand.nextInt(alto-bajo)+bajo; 
          
          
          int aux=arreglo[pivot];  //guardamos en aux el del valor pivote
          arreglo[pivot]=arreglo[alto]; //en la posicion del pivote colocamos el valor de la ultima posicion del arreglo
          arreglo[alto]=aux; //en la ultima posicion colocamos el valor del pivote
      }
} 
