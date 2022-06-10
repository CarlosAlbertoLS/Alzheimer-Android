# Alzheimer Android App

***Manual de desarrollador***

se listaran las versiones de las librerias usadas

1. Firebase
  * firebase-bom versión 29.1.0
  * firebase-core version 20.1.2
  * firebase-storage version 20.0.1
  * firebase-analitics-ktx
  * firebase-auth-ktx
  * firebase-firestore-ktx
2. Navigation Drawer
  - drawerlayout versión 1.1.1
  - coordinatorlayout versión 1.1.0
3. Android Studio
  * gradle Plugin Version 7.1.3
  - Gradle Version 7.2
  + Android Studio 2021.2.1

***Detalles e imagenes de la aplicación***

Al iniciar la aplicación esta lanzara un Splash Screen mientras carga los datos, pasados unos segundos se mostrara la pantalla principal donde se muestra un formulario y para iniciar seción junto con los batones de inicio, registro de usuario y la solicitud de recuperar la contraseña esta interfaz se visualiza de la siguiente forma:

>><img src="https://firebasestorage.googleapis.com/v0/b/alzheimer-50944.appspot.com/o/Img%2FScreenshot_20220523-114102_Alzheimer.jpg?alt=media&token=3fbff22d-c805-4eaf-89e3-9db4f360a630" width="175"/>

si el usuario no se encuentra registrado debe realizar el registro que al inicio solo pide correo y contraseña y que se identifique con uno de los 4 roles siendo:
+ Pasiente
+ Familiar
+ Doctor
+ Enfermera/o

la pantalla de este formulario se visualiza de la siguiente manera:

>><img src="https://firebasestorage.googleapis.com/v0/b/alzheimer-50944.appspot.com/o/Img%2FScreenshot_20220523-130049_Alzheimer.jpg?alt=media&token=97803ca7-629f-4124-94e3-65ccc4860042" width="175"/>

al registrarse como pasiente se habre otro formulario con datos especificos como se puede observar:

>><img src="https://firebasestorage.googleapis.com/v0/b/alzheimer-50944.appspot.com/o/Img%2FScreenshot_20220523-114734_Alzheimer.jpg?alt=media&token=8984a24a-2116-4eb9-90a0-607e9bb56405" width="175"/>

tanto como si el usuario esta registrado e inicia sesión como cuando el usuario que no estaba registrado termina de llanar los formularios, se va a mostrar la pantalla de inicio donde se puede observar datos de que es el Alzheimer y sus tipos, esta pantalla se isualiza de la siguiente manera:

>><img src="https://firebasestorage.googleapis.com/v0/b/alzheimer-50944.appspot.com/o/Img%2FScreenshot_20220523-115613_Alzheimer.jpg?alt=media&token=6965fbb0-6baa-47c8-9130-9fcdc034f1de" width="175"/>

si el usuario interactua con el menu se visualizara de la siguiente manera:

>><img src="https://firebasestorage.googleapis.com/v0/b/alzheimer-50944.appspot.com/o/Img%2FScreenshot_20220523-115905_Alzheimer.jpg?alt=media&token=7584ee03-fc6c-489e-aefd-13ee608f6172" width="175"/>

La parte principal del proyecto es poder grabar o cargar un video para que pueda ser anaizado con la red neuronal [aqui va el otro repositorio](https://www.google.com/) las siguientes imagenes muestran el apartado de video de la aplicacion, la seleccion de donde sera tomado el video y la carga de este

>><img src="https://firebasestorage.googleapis.com/v0/b/alzheimer-50944.appspot.com/o/Img%2FScreenshot_20220523-115620_Alzheimer.jpg?alt=media&token=23b70fe9-bb93-4470-9d10-e6bf0e6c47e0" width="175"/>
>><img src="https://firebasestorage.googleapis.com/v0/b/alzheimer-50944.appspot.com/o/Img%2FScreenshot_20220523-115629_Alzheimer.jpg?alt=media&token=99a6609e-da1b-4f0c-a746-bc2c234acce1" width="175"/>
>><img src="https://firebasestorage.googleapis.com/v0/b/alzheimer-50944.appspot.com/o/Img%2FScreenshot_20220523-115727_Alzheimer.jpg?alt=media&token=0df9bdb7-40bc-4a2c-a48a-232aaf8c5a46" width="175"/>

para probar a aplicación se puede descargar [aquí](https://firebasestorage.googleapis.com/v0/b/titulacionalzheimer.appspot.com/o/app%2FAlzheimer.apk?alt=media&token=1924e7b9-b052-48b6-a5f1-19dcc4bcb838):

>><img src="https://firebasestorage.googleapis.com/v0/b/alzheimer-50944.appspot.com/o/Img%2Fqr-code.png?alt=media&token=5eb4597a-9d28-44db-90c8-84a156cca50b" width="350"/>
