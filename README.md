# Proyecto Listado: Directorio de Empresas en Android

Esta es una aplicación nativa para Android desarrollada en Java que permite capturar, gestionar y visualizar un listado dinámico de empresas. Fue desarrollada originalmente como parte de un ejercicio práctico, evolucionando para incluir múltiples funcionalidades extra.

## 📋 Características Principales

- **Registro de Empresas:** Formulario con validación de datos para registrar una empresa capturando:
  - Nombre de la empresa
  - Correo electrónico (con validación de formato)
  - Teléfono (con validación de longitud/formato)
  - Tipo de industria (Alimenticio, Automotriz, Entretenimiento, Farmacéutico) mediante un menú desplegable (Spinner).
- **Listado Dinámico:** Las empresas registradas se despliegan en una lista que utiliza un adaptador personalizado (`Adaptador.java`).
- **Diseño Personalizado de Celdas:** Cada elemento en la lista muestra la información de la empresa e incluye un ícono (`ImageView`) dinámico que cambia dependiendo del sector al que se dedique la empresa.
- **Interactividad Multimedia:** Al presionar el ícono representativo de la empresa en la lista, se reproduce un efecto de sonido correspondiente a su rubro utilizando `MediaPlayer`.
- **Persistencia de Datos:** El almacenamiento de los registros se gestiona localmente utilizando `SharedPreferences`.

## ⭐ Funcionalidades Extra Implementadas

Además de los requisitos básicos de captura y listado, la aplicación cuenta con los siguientes agregados:

1. **Gestión de Registros (Modificación y Eliminación):** 
   - Permite modificar los datos de cualquier empresa existente tocando el ícono de configuración en su celda.
   - Opción general para borrar **todos** los registros almacenados.
2. **Diálogos de Confirmación:** Se utilizan ventanas de diálogo (`AlertDialog`) para prevenir acciones accidentales (ej. al modificar un registro o al intentar borrar toda la lista).
3. **Validaciones Estrictas:** Alertas y mensajes de error dinámicos ("Número inválido", "Correo inválido", "Dato necesario") si el usuario intenta guardar información incompleta.
4. **Interfaz Mejorada:** Cuenta con un ícono de aplicación personalizado (distinto al de por defecto de Android Studio) y mensajes interactivos (Toast) para notificar el éxito de las operaciones o mostrar el ID interno del registro.

## 🛠️ Tecnologías y Componentes

- **Lenguaje:** Java
- **UI:** XML, ListView, adaptadores personalizados (BaseAdapter), AlertDialogs, Spinners.
- **Almacenamiento:** SharedPreferences (guardado mediante `datos.txt`).
- **Multimedia:** `MediaPlayer` para los efectos de sonido integrados (`raw/alimenticio`, `raw/carreras`, `raw/cine`, `raw/farmaceutico`, `raw/maquina`).

## 🚀 Estructura de Datos (Empresa)

El modelo principal (`Empresa.java`) maneja los siguientes atributos internos por cada registro:
- `ID` (Generado internamente)
- `Nombre`
- `Correo`
- `Teléfono`
- `Tipo`