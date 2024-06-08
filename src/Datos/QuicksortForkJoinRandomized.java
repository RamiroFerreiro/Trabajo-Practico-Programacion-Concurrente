package Datos;
import java.util.Random;
import java.util.concurrent.RecursiveAction;

public class QuicksortForkJoinRandomized extends RecursiveAction {

	 	private int[] array; //array que será ordenado
	    private int inicio; //índice de inicio
	    private int fin; //índice de fin
	    private static final Random RANDOM_NUMBER_GEN = new Random(); //genera números aleatorios

	    //Constructor con arreglo e índice inicio y fin
	    public QuicksortForkJoinRandomized(int[] array, int inicio, int fin){
	        this.array = array;
	        this.inicio = inicio;
	        this.fin = fin;
	    }
	    //Constructor solo con arreglo 
	    public QuicksortForkJoinRandomized(int[] array){
	        this(array, 0, array.length - 1);
	    }
	    
	    //Se ejecuta QuickSort aleatorio utilizando ForkJoin
	    @Override
	    protected void compute() {
	    	///condicional de corte de recursividad. Identico al secuencial.
	        if(inicio < fin){
	        	
	        	//Se obtiene la posicion del pivote para utilizar como indice
	            int q = partition(array, inicio, fin); 
	            
	            //Se crean nuevas tareas para ordenar las particiones y las invoco de manera que se ejecuten concurrentemente.
	            invokeAll(new QuicksortForkJoinRandomized(array, inicio, q - 1),//izq
	                      new QuicksortForkJoinRandomized(array, q + 1, fin)); //der
	        }
	    }
	    //Método de particion
	    private static int partition(int[] A, int inicio, int fin){
	    	
	        //Se elije un numero aleatorio para obtener la posicion del pivote 
	        int randomIndex = RANDOM_NUMBER_GEN.nextInt(fin - inicio + 1) + inicio;
	        
	        //En el arrelgo "A" se realiza el intercambio de valores
	        swap(A, randomIndex, fin);
	        
	        //Una vez mezclados los valores tomo el valor de último elemento como pivote
	        int pivote = A[fin];
	        
	        //El índice "i" representa el último menor
	        int i = inicio - 1;
	       
	        ///recorre los elementos de izquierda a derecha busca los menores o iguales y los coloca a la izq.
	        for(int j = inicio; j <= fin - 1; j++){
	        	
	            if(A[j] <= pivote){
	            	
	                i = i + 1;
	                swap(A, i, j);
	                
	            }
	        }
	        
	        //Coloca al pivote en la posicion siguiente al ultimo menor
	        swap(A, i + 1, fin);
	        return i + 1; //retorna la posicion del pivote 
	    }

	    //método auxiliar para realizar los intermcambios de elementos
	    private static void swap(int[] A, int i, int j){
	        int aux = A[i];
	        A[i] = A[j];
	        A[j] = aux;
	    }
}
