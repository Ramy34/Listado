Ejercicio 2 en Android.

Realizar una aplicación que presente un listado dinámico de elementos. Los elementos deberán ser capturados por el usuario en un formulario con validaciones, permitiendo capturar tantos elementos como se deseen.

El tipo de elementos será definido por el desarrollador sobre cualquier tema que le interese.

Ejemplos:

    Automóviles
    Películas
    Videojuegos
    Deportes
    Artistas

La aplicación deberá implementar una clase para el tipo de elementos, como se ha visto en el taller con la clase Alumno o la clase Anime.

El desarrollador puede definir la cantidad de detalles a capturar por cada elemento. Ejemplo:

Para una película:

    ID (no se capturará, pero debe ser parte de cada elemento y diferente para cada uno)
    Título
    Género
    Año
    Calificación

Por lo menos se deberán capturar 3 detalles (sin incluir el ID) y uno de ellos mediante un Spinner o menú desplegable (Dropdown menu) con opciones finitas y definidas.

Ejemplo 1: Para la captura de automóviles, se puede capturar la marca con opciones definidas como: Ford, Volkswagen, Nissan y Toyota.

Ejemplo 2: Para la captura de películas, se puede capturar el género con opciones definidas como: Comedia, Terror, Drama, Romance y Animación.

Así también, la aplicación deberá mostrar un botón para que al final se puedan desplegar todos los elementos dados de alta en otro Activity mediante un ListView o contenedor similar.

Para lo anterior, se debe crear también un prototipo de celda personalizado con lo que el desarrollador considere necesario, pero que tenga por lo menos un elemento de imagen (ImageView) que cambie con respecto a lo que el usuario capturó con el Spinner. Ejemplo: para el caso de los automóviles pueden mostrar ahí el logo de la marca o en el caso de las películas alguna imagen referente al género de cada película. 

Finalmente, al dar clic en algún elemento del listado se deberá mandar llamar un mensaje Toast que indique el ID del elemento seleccionado. Ejemplo: “El elemento seleccionado tiene el ID: 2354”.

Extras que se agregaron:

    Sonidos en los botones en el registro de empresas y en el listado al presionar el imageView.
    La opción de borrar todos los registros hechos.
