package arbolesavl;

class Arbol {
    
    private Nodo raiz = null;
    private int nivel = 0;
    private int max = 0;
    
    private String cadena="";
    private String cadena2="";
    private String cadena3="";
    
    public Nodo insertar(Nodo nodo, int valor){
        
        if(nodo == null){
        
            return(new Nodo(valor));
            
        }
        
        int valorRaiz = nodo.getValor();
        
        if(valor < valorRaiz){
            
            nodo.setIzquierdo(insertar(nodo.getIzquierdo(), valor));
            
        } else if(valor > valorRaiz){
        
            nodo.setDerecho(insertar(nodo.getDerecho(), valor));
            
        } else {
        
            return nodo;
        
        }
        
        nodo.setPeso(1 + pesoMaximo(obtenerPeso(nodo.getIzquierdo()), obtenerPeso(nodo.getDerecho())));
        
        int balance = calcularBalance(nodo);
        
        nodo.setBalance(balance);
        
        if( balance > 1 && valor < nodo.getIzquierdo().getValor() ){
        
            return rotarDerecha(nodo);
            
        } else if ( balance < -1 && valor > nodo.getDerecho().getValor() ){
            
            return rotarIzquierda(nodo);
            
        } else if ( balance > 1 && valor > nodo.getIzquierdo().getValor() ){
        
            nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));
            return rotarDerecha(nodo);
            
        } else if ( balance < -1 && valor < nodo.getDerecho().getValor() ){
        
            nodo.setDerecho(rotarDerecha(nodo.getDerecho()));
            return rotarIzquierda(nodo);
            
        }
        
        return nodo;
        
    }
    
    public Nodo retirar(Nodo nodo, int valor){
        
        if (nodo == null){
            
            return nodo;
            
        }

        if (valor < nodo.getValor()){
            
            nodo.setIzquierdo(retirar(nodo.getIzquierdo(),valor)); 
  

        } else if (valor > nodo.getValor())  {
            
            nodo.setDerecho(retirar(nodo.getDerecho(),valor)); 
   
        } else {  
 
            if ((nodo.getIzquierdo() == null) || (nodo.getDerecho() == null)){
                
                Nodo temporal = null;
                
                if (temporal == nodo.getIzquierdo()){  
                    
                    temporal = nodo.getDerecho();  
                
                } else {
                    
                    temporal = nodo.getIzquierdo();  
  
                }
                    
                if (temporal == null){

                    temporal = nodo;  
                    nodo = null; 
                    
                } else {
                    
                    nodo = temporal; 
                    
                }
                
            } else {  
   
                Nodo temp = hallarNodoMenor(nodo.getDerecho());  
   
                nodo.setValor(temp.getValor());
   
                nodo.setDerecho(retirar(nodo.getDerecho(), temp.getValor()));  
                
            }  
        }  

        if (nodo == null){
            
            return nodo;
        
        }       
  
        nodo.setPeso(pesoMaximo(obtenerPeso(nodo.getIzquierdo()), obtenerPeso(nodo.getDerecho())) + 1);  
  
        int balance = calcularBalance(nodo);  
  
        if (balance > 1 && calcularBalance(nodo.getIzquierdo()) >= 0){ 
        
            return rotarDerecha(nodo);  
   
        }
        
        if (balance > 1 && calcularBalance(nodo.getIzquierdo()) < 0){  
            nodo.setIzquierdo(rotarIzquierda(nodo.getIzquierdo()));  
            return rotarDerecha(nodo);  
            
        }  
  
        if (balance < -1 && calcularBalance(nodo.getDerecho()) <= 0){  
         
            return rotarIzquierda(nodo);
            
        }

        if (balance < -1 && calcularBalance(nodo.getDerecho()) > 0){
            
            nodo.setDerecho(rotarDerecha(nodo.getDerecho()));    
            return rotarIzquierda(nodo);  
        
        }  
        
        nodo.setBalance(calcularBalance(nodo));
        
        return nodo;  
    
    }  

    private Nodo rotarDerecha(Nodo q) {
    
        Nodo p = q.getIzquierdo();
        Nodo hijo = p.getDerecho();
        
        p.setDerecho(q);
        q.setIzquierdo(hijo);
        
        q.setPeso(pesoMaximo(obtenerPeso(q.getIzquierdo()), obtenerPeso(q.getDerecho())) + 1); 
        p.setPeso(pesoMaximo(obtenerPeso(p.getIzquierdo()), obtenerPeso(p.getDerecho())) + 1); 
        
        q.setBalance(calcularBalance(q));
        p.setBalance(calcularBalance(p));
        
        return p;
        
    }
    
    private Nodo rotarIzquierda(Nodo q) {
        
        Nodo p = q.getDerecho();
        Nodo hijo = p.getIzquierdo();
        
        p.setIzquierdo(q);
        q.setDerecho(hijo);
        
        q.setPeso(pesoMaximo(obtenerPeso(q.getIzquierdo()), obtenerPeso(q.getDerecho())) + 1); 
        p.setPeso(pesoMaximo(obtenerPeso(p.getIzquierdo()), obtenerPeso(p.getDerecho())) + 1); 
        
        q.setBalance(calcularBalance(q));
        p.setBalance(calcularBalance(p));
        
        return p;
        
    }
    
    int pesoMaximo(int a,int b){
            
            return (a > b) ? a : b ;
            
    }
    
    Nodo hallarNodoMenor(Nodo nodo)  {  
        
        Nodo temp = nodo;  
  
        while (temp.getIzquierdo() != null){  
        
            temp = temp.getIzquierdo();  
  
        }
        
        return temp;  
        
    }
    
    int obtenerPeso(Nodo nodo) { 
        if (nodo == null) 
            return 0; 
  
        return nodo.getPeso(); 
    } 

    int calcularBalance(Nodo nodo) {
        
        if (nodo == null){ 
        
            return 0;
            
        }
  
        return obtenerPeso(nodo.getIzquierdo()) - obtenerPeso(nodo.getDerecho()); 
        
    }
    
    String printInorder(Nodo nodo){
        
        if (nodo == null){ 
            return ""; 
        }
        
        nivel++;
        printInorder(nodo.getIzquierdo()); 
        nivel--;
        
        cadena = cadena + nodo.getValor() +", ";
        
        if(nivel > max){
        
            max = nivel;
            
        }
        
        nivel++;
        printInorder(nodo.getDerecho()); 
        nivel--;
        
        return cadena;
        
    } 
    
    String printPostOrder(Nodo nodo){
        
        if (nodo == null){ 
            return ""; 
        }

        printPostOrder(nodo.getIzquierdo()); 
        
        printPostOrder(nodo.getDerecho()); 
        
        cadena2 = cadena2 + nodo.getValor() +", ";
        
        return cadena2;
        
    } 
    
    String printPreOrder(Nodo nodo){
        
        if (nodo == null){ 
            return ""; 
        }
        
        cadena3 = cadena3 + nodo.getValor() +", ";
        
        printPreOrder(nodo.getIzquierdo()); 
        
        printPreOrder(nodo.getDerecho()); 
        
        return cadena3;
        
    } 
    
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public String getCadena2() {
        return cadena2;
    }

    public void setCadena2(String cadena2) {
        this.cadena2 = cadena2;
    }

    public String getCadena3() {
        return cadena3;
    }

    public void setCadena3(String cadena3) {
        this.cadena3 = cadena3;
    }
    
    
    
}
