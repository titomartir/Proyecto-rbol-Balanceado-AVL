/**
 * Clase AVLTree implementa un árbol binario de búsqueda autobalanceable (AVL)
 */
public class AVLTree {
    Node raiz; // Raíz del árbol AVL
    private static final String NOMBRE_ARCHIVO = "prueba_de_arbol.dot";
    
    /**
     * Constructor para un árbol AVL vacío
     */
    public AVLTree() {
        raiz = null;
    }
    
    /**
     * Obtiene la altura de un nodo (o 0 si es null)
     * @param nodo El nodo cuya altura se desea obtener
     * @return La altura del nodo, o 0 si el nodo es null
     */
    private int getAltura(Node nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }
    
    /**
     * Calcula el factor de balance para un nodo
     * @param nodo El nodo para calcular su factor de balance
     * @return altura(izquierda) - altura(derecha)
     */
    private int getFactorBalance(Node nodo) {
        if (nodo == null) {
            return 0;
        }
        return getAltura(nodo.izquierda) - getAltura(nodo.derecha);
    }
    
    /**
     * Actualiza la altura de un nodo basado en la altura de sus hijos
     * @param nodo El nodo cuya altura se actualizará
     */
    private void actualizarAltura(Node nodo) {
        if (nodo != null) {
            nodo.altura = Math.max(getAltura(nodo.izquierda), getAltura(nodo.derecha)) + 1;
        }
    }
    
    /**
     * Realiza una rotación simple a la derecha
     * @param y El nodo sobre el cual se realiza la rotación
     * @return La nueva raíz del subárbol rotado
     */
    private Node rotarDerecha(Node y) {
        Node x = y.izquierda;
        Node T2 = x.derecha;
        
        // Realizar rotación
        x.derecha = y;
        y.izquierda = T2;
        
        // Actualizar alturas
        actualizarAltura(y);
        actualizarAltura(x);
        
        // Retornar nueva raíz
        return x;
    }
    
    /**
     * Realiza una rotación simple a la izquierda
     * @param x El nodo sobre el cual se realiza la rotación
     * @return La nueva raíz del subárbol rotado
     */
    private Node rotarIzquierda(Node x) {
        Node y = x.derecha;
        Node T2 = y.izquierda;
        
        // Realizar rotación
        y.izquierda = x;
        x.derecha = T2;
        
        // Actualizar alturas
        actualizarAltura(x);
        actualizarAltura(y);
        
        // Retornar nueva raíz
        return y;
    }
    
    /**
     * Método público para insertar un valor en el árbol
     * @param valor El valor a insertar
     */
    public void insertar(int valor) {
        raiz = insertar(raiz, valor);
        guardarDotAutomatico();
        displayTree();
    }
    
    /**
     * Método recursivo para insertar un valor en el árbol y balancearlo
     * @param nodo El nodo raíz del subárbol donde se inserta
     * @param valor El valor a insertar
     * @return La nueva raíz del subárbol balanceado
     */
    private Node insertar(Node nodo, int valor) {
        // 1. Realizar inserción BST normal
        if (nodo == null) {
            return new Node(valor);
        }
        
        if (valor < nodo.valor) {
            nodo.izquierda = insertar(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = insertar(nodo.derecha, valor);
        } else {
            // Valores duplicados no se permiten (retornar el mismo nodo)
            return nodo;
        }
        
        // 2. Actualizar altura del nodo actual
        actualizarAltura(nodo);
        
        // 3. Calcular factor de balance
        int balance = getFactorBalance(nodo);
        
        // 4. Aplicar rotaciones si es necesario
        
        // Caso Izquierda-Izquierda
        if (balance > 1 && getFactorBalance(nodo.izquierda) >= 0) {
            return rotarDerecha(nodo);
        }
        
        // Caso Derecha-Derecha
        if (balance < -1 && getFactorBalance(nodo.derecha) <= 0) {
            return rotarIzquierda(nodo);
        }
        
        // Caso Izquierda-Derecha
        if (balance > 1 && getFactorBalance(nodo.izquierda) < 0) {
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }
        
        // Caso Derecha-Izquierda
        if (balance < -1 && getFactorBalance(nodo.derecha) > 0) {
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
        }
        
        // Si no se requiere balanceo, retornar el nodo sin cambios
        return nodo;
    }
    
    /**
     * Encuentra el nodo con el valor mínimo en un subárbol
     * @param nodo La raíz del subárbol
     * @return El nodo con el valor mínimo
     */
    private Node nodoValorMinimo(Node nodo) {
        Node actual = nodo;
        
        // El valor mínimo estará en el nodo más a la izquierda
        while (actual.izquierda != null) {
            actual = actual.izquierda;
        }
        
        return actual;
    }
    
    /**
     * Método público para eliminar un valor del árbol
     * @param valor El valor a eliminar
     */
    public void eliminar(int valor) {
        raiz = eliminar(raiz, valor);
        guardarDotAutomatico();
        displayTree();
    }
    
    /**
     * Método recursivo para eliminar un valor y balancear el árbol
     * @param nodo El nodo raíz del subárbol donde se elimina
     * @param valor El valor a eliminar
     * @return La nueva raíz del subárbol balanceado
     */
    private Node eliminar(Node nodo, int valor) {
        // 1. Realizar eliminación BST normal
        if (nodo == null) {
            return null; // Valor no encontrado
        }
        
        // Buscar el nodo a eliminar
        if (valor < nodo.valor) {
            nodo.izquierda = eliminar(nodo.izquierda, valor);
        } 
        else if (valor > nodo.valor) {
            nodo.derecha = eliminar(nodo.derecha, valor);
        } 
        else {
            // Nodo encontrado, realizar eliminación
            
            // Caso 1: Nodo hoja o con un solo hijo
            if (nodo.izquierda == null) {
                return nodo.derecha;
            } 
            else if (nodo.derecha == null) {
                return nodo.izquierda;
            }
            
            // Caso 2: Nodo con dos hijos
            // Obtener el sucesor inorden (mínimo valor en subárbol derecho)
            Node sucesor = nodoValorMinimo(nodo.derecha);
            
            // Copiar el valor del sucesor al nodo actual
            nodo.valor = sucesor.valor;
            
            // Eliminar el sucesor
            nodo.derecha = eliminar(nodo.derecha, sucesor.valor);
        }
        
        // Si el árbol tenía un solo nodo y fue eliminado
        if (nodo == null) {
            return null;
        }
        
        // 2. Actualizar altura
        actualizarAltura(nodo);
        
        // 3. Calcular factor de balance
        int balance = getFactorBalance(nodo);
        
        // 4. Balancear si es necesario
        
        // Caso Izquierda-Izquierda
        if (balance > 1 && getFactorBalance(nodo.izquierda) >= 0) {
            return rotarDerecha(nodo);
        }
        
        // Caso Izquierda-Derecha
        if (balance > 1 && getFactorBalance(nodo.izquierda) < 0) {
            nodo.izquierda = rotarIzquierda(nodo.izquierda);
            return rotarDerecha(nodo);
        }
        
        // Caso Derecha-Derecha
        if (balance < -1 && getFactorBalance(nodo.derecha) <= 0) {
            return rotarIzquierda(nodo);
        }
        
        // Caso Derecha-Izquierda
        if (balance < -1 && getFactorBalance(nodo.derecha) > 0) {
            nodo.derecha = rotarDerecha(nodo.derecha);
            return rotarIzquierda(nodo);
        }
        
        return nodo;
    }
    
    /**
     * Método público para buscar un valor en el árbol
     * @param valor El valor a buscar
     * @return true si el valor existe, false en caso contrario
     */
    public boolean buscar(int valor) {
        return buscar(raiz, valor);
    }
    
    /**
     * Método recursivo para buscar un valor en el árbol
     * @param nodo El nodo raíz del subárbol donde se busca
     * @param valor El valor a buscar
     * @return true si el valor existe, false en caso contrario
     */
    private boolean buscar(Node nodo, int valor) {
        if (nodo == null) {
            return false;
        }
        
        if (valor == nodo.valor) {
            return true;
        }
        
        if (valor < nodo.valor) {
            return buscar(nodo.izquierda, valor);
        } else {
            return buscar(nodo.derecha, valor);
        }
    }
    
    /**
     * Método público para imprimir el árbol gráficamente
     */
    public void printTree() {
        if (raiz == null) {
            System.out.println("Árbol vacío");
            return;
        }
        
        printTree(raiz, 0);
        System.out.println(); // Línea extra para separar visualizaciones
    }
    
    /**
     * Método recursivo para imprimir el árbol gráficamente
     * @param nodo El nodo raíz del subárbol a imprimir
     * @param nivel El nivel de profundidad en el árbol
     */
    private void printTree(Node nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        
        // Imprime primero la rama derecha
        printTree(nodo.derecha, nivel + 1);
        
        // Imprime el nodo actual con la indentación adecuada
        for (int i = 0; i < nivel; i++) {
            System.out.print("    "); // 4 espacios por nivel
        }
        System.out.println(nodo.valor + " [" + getFactorBalance(nodo) + "]"); // Muestra el valor y el factor de balance
        
        // Imprime la rama izquierda
        printTree(nodo.izquierda, nivel + 1);
    }
    
    /**
     * Método alternativo para mostrar el árbol de forma más gráfica
     * con líneas y conexiones entre nodos
     */
    public void displayTree() {
        System.out.println("\n============= Árbol AVL =============");
        if (raiz == null) {
            System.out.println("Árbol vacío");
            return;
        }
        
        // Usar el TreePrinter para una visualización más estética
        TreePrinter.printNode(raiz);
        
        System.out.println("\nInformación adicional:");
        StringBuilder sb = new StringBuilder();
        visualizeTree(raiz, sb, "", true);
        System.out.println(sb.toString());
        System.out.println("=====================================\n");
    }
    
    /**
     * Obtiene la raíz del árbol
     * @return El nodo raíz del árbol
     */
    public Node getRaiz() {
        return raiz;
    }
    
    /**
     * Método auxiliar para visualizar el árbol con líneas de conexión
     * @param nodo El nodo actual
     * @param sb StringBuilder para construir la visualización
     * @param prefix Prefijo para la línea actual
     * @param isLast Indica si es el último hijo
     */
    private void visualizeTree(Node nodo, StringBuilder sb, String prefix, boolean isLast) {
        if (nodo != null) {
            sb.append(prefix);
            sb.append(isLast ? "└── " : "├── ");
            
            // Imprimir el valor del nodo y su factor de balance
            sb.append(nodo.valor).append(" [FB=").append(getFactorBalance(nodo)).append("]").append("\n");
            
            // Construir prefijo para los hijos
            String childPrefix = prefix + (isLast ? "    " : "│   ");
            
            // Procesar hijo derecho
            visualizeTree(nodo.derecha, sb, childPrefix, nodo.izquierda == null);
            
            // Procesar hijo izquierdo
            if (nodo.izquierda != null) {
                visualizeTree(nodo.izquierda, sb, childPrefix, true);
            }
        }
    }

    /**
     * Guarda automáticamente el árbol en formato DOT
     */
    private void guardarDotAutomatico() {
        AVLTreeExporter.exportarADOT(this, NOMBRE_ARCHIVO);
        generarImagenAutomatica();
    }

    /**
     * Genera la imagen PNG automáticamente
     */
    private void generarImagenAutomatica() {
        try {
            ProcessBuilder pb = new ProcessBuilder("dot", "-Tpng", NOMBRE_ARCHIVO, "-o", "prueba_de_arbol.png");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            process.waitFor();
            
            // Abrir la imagen automáticamente (Windows)
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "start", "prueba_de_arbol.png").start();
            }
            // Para Mac/Linux puedes añadir más condiciones aquí
            
        } catch (Exception e) {
            System.err.println("Error al generar la imagen: " + e.getMessage());
        }
    }
}