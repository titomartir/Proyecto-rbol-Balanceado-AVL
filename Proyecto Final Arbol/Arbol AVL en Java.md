# Árbol AVL en Java

## Descripción del Proyecto
Este proyecto implementa un árbol binario de búsqueda autobalanceable (AVL) en Java. Los árboles AVL mantienen su balance automáticamente después de cada inserción o eliminación, garantizando que la altura del árbol sea siempre logarítmica en relación al número de nodos. Esto asegura operaciones eficientes de búsqueda, inserción y eliminación con una complejidad de tiempo de O(log n).

### Características principales:
- **Inserción y eliminación** con auto-balanceo.
- **Búsqueda** eficiente de valores.
- **Visualización gráfica** del árbol en formato DOT (para Graphviz) y en consola.
- **Ejemplos predefinidos** para demostrar rotaciones AVL (LL, RR, LR, RL).
- **Exportación** del árbol a formato de texto o DOT.

## Capturas de Entrada
<!-- Espacio reservado para capturas de entrada -->
![Inserción de valores](insertar_valores.png)  
*(Ejemplo de inserción de valores: 39, 22, 17, 3, 29, 71, 48, 46, 100, 82, 101)*

## Captura de Salida
<!-- Espacio reservado para captura del árbol AVL -->
![Árbol AVL resultante](arbol_avl.png)  
*(Visualización del árbol AVL balanceado después de las inserciones)*

## Instrucciones para Ejecutar el Proyecto

### Requisitos:
- **Java JDK 8 o superior** instalado.
- **Graphviz** (opcional, para generar imágenes del árbol). Descargar desde [graphviz.org](https://graphviz.org/).

### Pasos:
1. **Clonar o descargar** el proyecto:
   ```bash
   git clone [URL_DEL_REPOSITORIO]