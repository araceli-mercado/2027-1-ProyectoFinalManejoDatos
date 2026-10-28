/**
 * Implementa un árbol binario de búsqueda.
 * @author Araceli Mercado
 */
public class ABB extends ArbolBinario {

    /* **************ALGORITMO DSW**************** */
    /**
     * Organiza los pasos para balancear el árbol con el algoritmo DSW.
     */
    public void balanceaDSW() {
        if (isVacio()) {
            return;
        }

        // Creamos una raíz temporal para poder cambiar la raíz del árbol.
        ABB pseudoRoot = new ABB();

        // Colgamos a su derecha una copia del árbol.
        pseudoRoot.der = this.clone();

        // Creamos la enredadera
        int cantNodos = creaEnredadera(pseudoRoot);

        // Balanceamos la enredadera.
        creaArbolPerfecto(pseudoRoot, cantNodos);

        // Asignamos la nueva raíz a this.
        ABB raiz = (ABB) pseudoRoot.der;
        if (raiz != null) {
            this.info = raiz.info;
            this.izq = raiz.izq;
            this.der = raiz.der;
        }
    }

    /*
     * Rota cada segundo nodo.
     * @param pseudoRoot Nodo auxiliar cuyo hijo derecho es la raíz del árbol.
     * @param n Número de rotaciones a hacer.
     */
    private void rotaNveces(ABB pseudoRoot, int n) {
        ABB aux = pseudoRoot;

        for (int i = 0; i < n; i++) {  
            ABB hijo = (ABB) aux.der;
            if (hijo != null && hijo.der != null) {
                ABB nieto = (ABB) hijo.der;
                rotaIzquierda(aux, hijo, nieto);
                aux = nieto; // saltamos dos nodos
            }
        }
    }

    /*
     * Crea el árbol balanceado a partir de la enredadera.
     * @param pseudoRoot Nodo auxiliar cuyo subárbol derecho es una enredadera.
     * @param n Número de nodos en el árbol.
     */
    private void creaArbolPerfecto(ABB pseudoRoot, int n) {
        double m = Math.pow(2, Math.log(n)) - 1;

        rotaNveces(pseudoRoot, (int) Math.round(n - m));

        while (m > 1) {
            m /= 2;
            rotaNveces(pseudoRoot, (int) Math.round(m));
        }
    }

    /*
     * Convierte el ABB en una enredadera. Se utiliza un nodo auxiliar (pseudoRoot)
     * para poder cambiar la raíz del árbol.
     * @param pseudoRoot Nodo auxiliar cuyo hijo derecho es la raíz del árbol.
     * @return El número de nodos en el árbol.
     */
    private int creaEnredadera(ABB pseudoRoot) {
        ABB cola = pseudoRoot;
        ABB resto = (ABB) pseudoRoot.der;
        int contadorNodos = 0;

        while (resto != null) {
            if (resto.izq == null) {
                // No hay hijo izquierdo, avanzamos en la enredadera
                cola = resto;
                resto = (ABB) resto.der;
                contadorNodos++;
            } else {
                // Hay hijo izquierdo, rotamos a la derecha sobre el nodo 'resto'
                ABB hijoIzq = (ABB) resto.izq;
                rotaDerecha(cola, resto, hijoIzq);
                // Tras la rotación, el que era hijo izquierdo ahora es la nueva raíz del subárbol
                resto = hijoIzq; 
            }
        }
        return contadorNodos;
    }

    /*
     * Hace una rotación hacia la derecha entre el padre y el hijo.
     * @param abuelo
     * @param padre
     * @param hijo
     */
    private void rotaDerecha(ABB abuelo, ABB padre, ABB hijo) {
        if (abuelo != null) { // padre no es la raíz
            padre.izq = hijo.der;
            hijo.der = padre;
            if (abuelo.izq == padre) {
                abuelo.izq = hijo;
            } else {
                abuelo.der = hijo;
            }            
        }
    }

    /*
     * Hace una rotación hacia la izquierda entre el padre y el hijo.
     * @param abuelo
     * @param padre
     * @param hijo
     */
    private void rotaIzquierda(ABB abuelo, ABB padre, ABB hijo) {
        if (abuelo != null) { // padre no es la raíz
            padre.der = hijo.izq;
            hijo.izq = padre;
            if (abuelo.izq == padre) {
                abuelo.izq = hijo;
            } else {
                abuelo.der = hijo;
            }
        }
    }
    /* **************ALGORITMO DSW**************** */

    /**
     * Obtiene el nodo cuya información se pasa como parámetro.
     * @param inf La información del nodo a obtener.
     * @return El nodo con la información, si está en el árbol; <code>null</code> en otro caso.
     */
    public ABB getNodo(int inf) {
        if (inf == info) {
            return this;
        }
        if (inf < info) {
            if (izq != null) {
                return ((ABB) izq).getNodo(inf);
            } else {
                return null;
            }
        } 
        if (der != null) {
            return ((ABB) der).getNodo(inf);
        }
        return null;
    }

    /* **************BALANCEO CON ARREGLO**************** */

    /**
     * Balancea el árbol usando un arreglo.
     */
    public void balanceaConArreglo() {
        int[] datos = getArregloOrdenado();
        try {
            ABB nuevo = balancea(datos, 0, datos.length - 1);

            //if (nuevo != null) {
                this.info = nuevo.info;
                this.izq = nuevo.izq;
                this.der = nuevo.der;
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene el arreglo de los nodos ya ordenado.
     * @return Un arreglo de enteros ordenado.
     */
    public int[] getArregloOrdenado() {
        String arbolEnTexto = inOrden(); // regresa el árbol ordenado
        String[] arrNodosTexto = arbolEnTexto.split(" ");
        int[] salida = new int[arrNodosTexto.length];

        for (int i = 0; i < salida.length; i++) {
            salida[i] = Integer.parseInt(arrNodosTexto[i]);
        } 

        return salida;
    }
    /*
     * Balancea el árbol mediante un arreglo.
     * @param datos El arreglo de datos ya ordenado.
     * @param primero El índice del primer elemento del arreglo.
     * @param ultimo El índice del último elemento del arreglo.
     * @throws Exception Cuando el arreglo es nullo o está vacío o los índices están fuera de rango.
     */
    private ABB balancea(int[] datos, int primero, int ultimo) throws Exception {
        if (datos == null) {
            throw new Exception("El arreglo de datos es null.");
        }
        if (datos.length == 0) {
            throw new Exception("El arreglo de datos está vacío.");
        }
        if (primero < 0 || ultimo >= datos.length) {
            throw new Exception("Los índices están fuera de rango.");
        }
        if (primero > ultimo) {
            return null;
        }
        ABB salida = new ABB();
        int mitad = (primero + ultimo) / 2;
        
        salida.info = datos[mitad];
        salida.izq = balancea(datos, primero, mitad - 1);
        salida.der = balancea(datos, mitad + 1, ultimo);
        
        return salida;
    }
    /* **************BALANCEO CON ARREGLO**************** */
    
    /**
     * Borra un nodo del árbol.
     * @param inf La información del nodo a borrar.
     */
    public void elimina(int inf) {
        ABB padre = getPadre(inf);
        if (padre == null) { // inf no está en el árbol o queremos borrar la raíz
            if (inf == getInfo()) { // es la raíz
                // TODO arreglar esta parte
                ABB aux = (ABB) izq;
                ABB masDer = aux.nodoMasDer();
                masDer.der = der; // colgamos el derecho en el árbol izq
                der = izq.der;
                izq = izq.izq;
                info = aux.getInfo();
                return;
            } else { // no está en el árbol
                return;
            }
        }
        if (inf < padre.getInfo()) { // es hijo izq
            if (((ABB) padre.izq).isHoja()) {
                padre.izq = null;
            } else { // el nodo a borrar tiene hijo/s
                int unHijoSolo = ((ABB) padre.izq).unHijo();
                if (unHijoSolo == -1) { // el hijo tiene hijo izq
                    padre.izq = padre.izq.izq;
                } else if (unHijoSolo == 1) { // el hijo tiene hijo der
                    padre.izq = padre.izq.der;
                } else { // tiene dos hijos
                    ABB masIzq = ((ABB) padre.izq.der).nodoMasIzq();
                    masIzq.izq = padre.izq.izq;
                    padre.izq = padre.izq.der;
                }
            }
        } else { // es hijo der
            if (((ABB) padre.der).isHoja()) {
                padre.der = null;
            } else { // el nodo a borrar tiene hijo/s
                int unHijoSolo = ((ABB) padre.der).unHijo();
                if (unHijoSolo == -1) { // el hijo tiene hijo izq
                    padre.der = padre.der.izq;
                } else if (unHijoSolo == 1) { // el hijo tiene hijo der
                    padre.der = padre.der.der;
                } else { // tiene dos hijos
                    ABB masDer = ((ABB) padre.der.izq).nodoMasDer();
                    masDer.der = padre.der.der;
                    padre.der = padre.der.izq;
                }
            }
        }

    }

    /*
     * Obtiene el primer nodo del árbol que no tenga hijo izquierdo.
     */
    public ABB nodoMasIzq() {
        if (izq == null) {
            return this;
        }
        return ((ABB) izq).nodoMasIzq();
    }

    /*
     * Obtiene el primer nodo del árbol que no tenga hijo derecho.
     */
    public ABB nodoMasDer() {
        if (der == null) {
            return this;
        }
        return ((ABB) der).nodoMasDer();
    }

    /*
     * Dice si tiene un hijo y cuál.
     * @return -1 si sólo tiene hijo izq, 0 si tiene dos hijos o es hoja y 1 si sólo tiene hijo der
     */
    public int unHijo() {
        if (izq != null && der == null) {
            return -1;
        } else if (izq == null && der != null) {
            return 1;
        } else {
            return 0;
        }
    }

    /*
     * Obtiene el padre del nodo con inf.
     */
    public ABB getPadre(int inf) {
        if (isVacio() || inf == info) {
            return null;
        }
        if (inf < info) {
            if (izq == null) { // inf no está en el árbol
                return null;
            } else {
                if (izq.info == inf) {
                    return this;
                } else {
                    return ((ABB) izq).getPadre(inf);
                }
            }
        } else {
            if (der == null) { // inf no está en el árbol
                return null;
            } else {
                if (der.info == inf) {
                    return this;
                } else {
                    return ((ABB) der).getPadre(inf);
                }
            }
        }
    }

    /*
     * Decide si un nodo es hoja.   
     */
    private boolean isHoja() {
        return izq == null && der == null;
    }

    /**
     * Agrega un nodo al árbol.
     * @param inf La información del nodo que se va a agregar.
     */
    public void inserta(int inf) {
        if (isVacio()) {
            info = inf;
            return;
        }
        if (inf == info) {
            return;
        }
        if (inf < info) { // lo cuelgo del lado izq
            if (izq == null) {
                izq = new ABB(inf);
            } else {
                ((ABB) izq).inserta(inf);
            }
        } else { // lo cuelgo del lado derecho
            if (der == null) {
                der = new ABB(inf);
            } else {
                ((ABB) der).inserta(inf);
            }
        }
    }

    /* **************CONSTRUCTORES**************** */
    public ABB() {
        super();
    }
    public ABB(int inf) {
        super(inf);
    }
    /* **************CONSTRUCTORES**************** */
    /* **************UTILERÍA**************** */
    public ABB clone() {
        if (isVacio()) {
            return new ABB();
        }

        ABB clon = new ABB(info);
        if (izq != null) {
            clon.izq = ((ABB) izq).clone();
        }
        if (der != null) {
            clon.der = ((ABB) der).clone();
        }

        return clon;
    }
    /* **************UTILERÍA**************** */
}
