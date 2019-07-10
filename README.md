# MovieDB
Android project as test in Rappi

Link a la prueba: https://goo.gl/QKBeh7

1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?

El propósito de este principio SOLID es buscar o lograr desacoplar la aplicación o el producto que se estemos desarrollando, consiste en que cada clase tiene por sí una única responsabilidad, por ejemplo un repositorio de datos remoto solo debe responsabilizarse por el manejo de datos de una fuente remota y no por datos o información de una fuente de datos local. 

2. Qué características tiene, según su opinión, un “buen” código o código limpio?

Para mi debemos partir de tener definido un patrón de diseño , cumplir sus reglas o principios cual sea que fuese seleccionado para aplicar, tener claramente definido el alcance de cada una de sus clase y módulos dentro del desarrollo de nuestra aplicación.

Comentar donde sea necesario siempre será una gran ayuda para comprender el código, cumplir estándares de codificación y en lo posible manejar reglas en cuanto a uso de componentes, nombramiento de variables, reglas de indentación, etc. 


Una vez acabada la prueba describa en un readme brevemente:
1. Las capas de la aplicación (por ejemplo capa de persistencia, vistas, red, negocio, etc) y qué clases pertenecen a cual.

Para el desarrollo de la prueba hice uso de un patrón de diseño MVVM, las vistas (V) en este caso son fragmentos haciendo uso de DataBinding; los VM ViewModels, son viewmodels propios de Android y estos hacen uso de Repositorios, lo que llamamos el Modelo.

Iniciaremos con el Modelo

En el paquete repository, encontraremos el Repository para Movies y para el MovieDetail, cada uno de estos repositorios obtiene una dependencia a una correspondiente fuente de datos local y externa (remota). Para la fuente de datos local se ha usado Room y para la fuente de datos remota se ha hecho uso de Retrofit.

Las fuentes de datos la encontraremos en el paquete “data” que a su vez tiene definido paquetes para las fuentes de datos local y remota, así como entidades de Dominio que son las usadas en la vista.

En room hemos definido las entidades de nuestra base de datos local, así como cada uno de los Daos que necesitaremos para la aplicación, en Remote hemos definido nuestros DTO y el Api de Movies (MovieDB) necesario para la prueba.

En ambos casos, tanto local como remoto, hemos definido DataStores que define nuestros contratos para el manejo de información según la fuente de datos.

Mediante funciones de extensión, nos encargamos de transformar nuestros DTO a objetos de la DB y estos a objetos de dominio.

Seguiremos con nuestros viewmodels

En el paquete viewmodels encontraremos los viewmodels usados para la prueba, un viewmodel para la lista de videos según categoría y la búsqueda de estos y otro viewmodel para el detalle de cada película seleccionada.

Cada uno de estos recibe una instancia del repositorio que usará para el manejo de la información., recordemos estos repositorios tendrán acceso a información local y remota. 

Hago uso de una BaseViewModel para variables comunes para el uso de Coroutines Kotlin

“Todas las instancias de las dependencias para cada uno de los componentes anteriores, hacen uso de KOIN para la inyección de estas dependencias”, ver paquete “di”.

View
En el paquete de UI encontraremos la actividad que contendrá los distintos fragmentos usados para la prueba, se hace uso del NavigationController el cual nos facilita la reutilización de fragmentos y la navegación entre la lista de películas y su detalle.

Cada uno de estos fragmentos fue hecho usando DataBinding y ConstraintLayout, además tienen unos adaptadores que nos permiten ver elementos en forma de lista -> RecyclerViews.

Otros
Tenemos un paquete de utilidades y el paquete de app en donde tenemos nuestra clase Application la cual inicia la inyección de dependencias con KOIN. 


2. La responsabilidad de cada clase creada.

app.MoviesApplication
Nuestra clase de application que inicia la inyección de dependencias

data.domain
Contiene nuestras clases de dominio las cuales representan objetos como Película, vídeos de la película, géneros, etc. 

data.source.local
contiene las interfaces que nos dan el contrato o comportamiento para cada uno de los datastore, lista de películas o detalle de película.

data.source.local.room.MoviesDataBase
Definicion de la base de datos local de la aplicación

data.source.local.room
contiene las implementaciones de los DataStoreLocal

data.source.local.room.dao
contiene los distintos Daos usados en la base de datos local.

data.source.local.room.entities
contiene las clases que representan las tablas de nuestra base de datos local.

data.source.remote
contiene las interfaces que nos dan el contrato o comportamiento para cada uno de los datastore, lista de películas o detalle de película.

data.source.remote.NetworkApiStatus
Enum que nos facilita el control de los estados de peticiones en los viewmodels

data.source.remote.retrofit
contiene las implementaciones de los datastore remotos, la definición del retrofit para la aplicación.

data.source.remote.retrofit.MoviesRetrofit
La definición de nuestro retrofit para la aplicación

data.source.remote.retrofit.MoviesAPI
Clase que contiene la definición de los servicios a usar del api de movie db.

data.source.remote.retrofit.dtos
Contiene los distintos DTOS usados para las respuestas de los servicios del movie db API.

di.MoviesModule
Contiene la definición de las dependencias para la aplicación

repository.movies.MoviesRepository
El repositorio para las listas de películas y su búsqueda tanto local como remota. 

repository.movies.MovieDetailRepository
El repositorio para el detalle de una película y su lista de videos

ui.MainActivity
Actividad unica de la aplicación, la cual hará uso de fragments para mostrar la información

ui.movies.MoviesFragment
Fragmento que gestionará la lista de peliculas por categoria y la búsqueda de estas. es reutilizado para las 3 categorías solicitadas así como para la búsqueda Online. 

ui.movies.IContractMovies
Definición de interfaces para la Vista, el ViewModel y el Model

ui.movies.MoviesFragment.adapter
Contiene el adaptador para la lista de películas y el listener para la selección de una de estas.

ui.detail.MovieDetailFragment
Fragmento que gestionará el detalle de una pelicula
ui.detail.IContractMovieDetail
Definición de interfaces para la Vista, el ViewModel y el Model

ui.detail.MovieDetailFragment.adapter
Contiene los distintos adaptadores usados para el detalle de una pelicula

viewmodels.BaseViewModel
ViewModel base que contiene variables para el uso de coroutines

viewmodels.movies.MoviesViewModel
ViewModel usado para la lista de películas, hace uso de un repositorio

viewmodels.detail.MovieDetailViewModel
ViewModel usado para el detalle de la pelicula, hace uso de un repositorio

utils.BindingAdapter
Distintas utilidades de binding usadas en el proyecto

utils.movies.MoviesFilter
Filtro utilizado para diferenciar la lista de peliculas

utils.Result
Una sealed class que nos facilita los resultados de las consultas






