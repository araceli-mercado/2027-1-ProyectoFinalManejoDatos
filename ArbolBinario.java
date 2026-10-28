/**
 * Maneja un árbol binario de enteros
 * @author Araceli Mercado
 * 
 */
public class ArbolBinario {
    int info;
    ArbolBinario izq;
    ArbolBinario der;

    public String inOrden() {
        String salida = "";

        if (izq != null) {
            salida += izq.inOrden();
        }
        salida += info + " ";
        if (der != null) {
            salida += der.inOrden();
        }
        
        return salida;
    }

    /**
     * Lista los nodos del árbol en posorden.
     * @return Una cadena con el árbol en posorden.
     */
    public String posOrden() {
        String salida = "";

        if (izq != null) {
            salida += izq.posOrden();
        }
        if (der != null) {
            salida += der.posOrden();
        }
        salida += info + " ";
        
        return salida;
    }

    /**
     * Lista el árbol en preorden.
     * @return Los nodos del árbol en preorden.
     */
    public String preOrden() {
        return toString();
    }

    /**
     * Decide si el árbol está vacío, es decir, no tiene hijos y su información es -10,000.
     * @return
     */
    public boolean isVacio() {
        return info == -10_000
                && izq == null
                && der == null;
    }

    /* **************CONSTRUCTORES**************** */
    public ArbolBinario() {
        super();
        info = -10_000;
        izq = null;
        der = null;
    }
    public ArbolBinario(int inf) {
        super();
        info = inf;
        izq = null;
        der = null;
    }
    public ArbolBinario(ArbolBinario izquierdo, ArbolBinario derecho, int inf) {
        super();
        info = inf;
        izq = izquierdo;
        der = derecho;
    }
    /* **************CONSTRUCTORES**************** */

    /* **************UTILERÍA**************** */
    public String toString() {
        String salida = "";

        salida += info + " ";
        if (izq != null) {
            salida += izq.toString();
        }
        if (der != null) {
            salida += der.toString();
        }
        return salida;
    }
    public boolean equals(ArbolBinario otro) {
        if (otro == null) {
            return false;
        }
        if (this.info != otro.info) {
            return false;
        }
        if (this.izq != null) { // hijo izq no nulo
            if (otro.izq != null) {  // hijo izq de otro no nulo
                if (!this.izq.equals(otro.izq)) {
                    return false;
                }
            } else { // hijo izq de otro nulo
                return false;
            }
        }
        if (this.der != null) { // hijo der no nulo
            if (otro.der != null) {  // hijo der de otro no nulo
                if (!this.der.equals(otro.der)) {
                    return false;
                }
            } else { // hijo der de otro nulo
                return false;
            }
        } 

        return true;
    }
    /* **************UTILERÍA**************** */

    /* **************ACCESO**************** */
    public void setInfo(int inf) {
        this.info = inf;
    }
    public int getInfo() {
        return info;
    }
    public ArbolBinario getIzq() {
        return izq;
    }
    public void setIzq(ArbolBinario izq) {
        this.izq = izq;
    }
    public ArbolBinario getDer() {
        return der;
    }
    public void setDer(ArbolBinario der) {
        this.der = der;
    }
    
    /* **************ACCESO**************** */
}
