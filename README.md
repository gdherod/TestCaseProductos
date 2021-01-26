# Ensayo de prueba de certificación TD

## Test Case 01 Productos.

La empresa Plaplix necesita realizar un mínimo producto viable, una app para mostrar
diferentes productos a posibles futuros compradores.


### Alcance del proyecto.

Actualmente la empresa cuenta con un servicio Rest Mock. Esta contiene dos endpoint:

    1.  El primero entrega un listado de productos con descripción, precio e imagen.
    
    2.  El otro endpoint entrega los detalles de un producto particular por id.


Considerando que para esta primera versión se busca tener una gran cobertura de dispositivos manteniendo los costos de mantención bajos, la API mínima es 23 y el target 30.


La idea es mostrar en un RecyclerView desde la persistencia local el listado inicial. Al hacer clic sobre un elemento, mostrar un detalle de ese elemento.


Lo que se espera que haga la aplicación es que cualquier usuario que la instale sin necesidad de autenticarse, pueda ver una lista de los productos y, ver los detalles del que seleccione y enviar un correo al área de ventas con información predefinida.

    ●   La primera pantalla es una lista de productos.
    
    ●   La segunda pantalla muestra la información en detalle de un producto.
    
    ●   La segunda pantalla tiene un botón que al presionarlo envía el correo descrito 


### Código, arquitectura y dependencias.

Se requiere un código legible y que use la siguiente arquitectura en conjunto con estas
dependencias:

    ●   Versionamiento con git. El código debe quedar en github.com​ u otra plataforma de desarrollo cooperativo.
    
    ●   Seguir los fundamentos de escritura de código (indentación, reutilización, sin código comentado).
    
    ●   Seguir las convenciones de nombres.
    
    ●   La arquitectura tiene que ser MVVM - LiveData - ROOM.
    
    ●   Los request HTTP tienen que ser realizados utilizando Retrofit.
    
    ●   Utilice Kotlin coroutines para manejar el trabajo en segundo plano.
    
    ●   Utilice las bibliotecas que estime necesario para hacer testing.
    
    ●   Al menos 1 test unitarios.
    
    ●   Al menos 1 test instrumental (con el emulador).


### Diseño.

Para evitar confusiones, el jefe de proyecto ha decidido enumerar las especificaciones que no pueden faltar:

    ●   Use la siguiente paleta para los colores de la aplicación:

![<a href="https://www.materialpalette.com/cyan/red" target="_blank">https://www.materialpalette.com/cyan/red</a>](file:///C:\Users\garux\Downloads\paleta-colores-ensayo.png)

    ●   Aunque los tamaños pueden variar, la diagramación de los layouts se debe mantener, esto incluye las filas de la lista y la segunda pantalla.
    
    ●   La primera pantalla es un fragmento que contiene la lista de productos.
    
    ●   La segunda pantalla es una “scrolling activity” y 2 fragmentos, uno para la cabecera colapsable y otro para el cuerpo.
    
    ●   Todos los textos que no sean obtenidos a partir de la API REST deben ser traducibles.


### Endpoints y Modelos de datos.

Ambos endpoints se deben acceder a través del verbo de request HTTP GET.

El primer endpoint es para obtener una lista de los productos

    http://my-json-server.typicode.com/Himuravidal/FakeAPIdata/products/

En esta respuesta se encuentran los productos agrupados por una colección donde cada
producto tiene la data reducida:

#### Reduced product Model
```json
{
"id": 1,
"name": "Samsung Galaxy A21s 64GB",
"price": 167253,
"image": "https://images.samsung.com/is/image/samsung/es-galaxy-a21s-sm-a217fzkoeub-262755098?$
PD_GALLERY_L_JPG$"
}
```

    
A continuación puede ver un ejemplo con 3 objetos:

#### Standard response
```json
[{
"id": 1,
"name": "Samsung Galaxy A21s 64GB",
"price": 167253,
"image":
"https://images.samsung.com/is/image/samsung/es-galaxy-a21s-sm-a217fzkoeub-262755098?$
PD_GALLERY_L_JPG$"
},
{
"id": 2,
"name": "Huawei Nova 7 SE 128GB",
"price": 347760,
"image":
"https://consumer.huawei.com/content/dam/huawei-cbg-site/common/mkt/pdp/phones/nova7-
se/img/mob/huawei-nova7-se-mob.png"
},
{
"id": 3,
"name": "Apple iPhone 7 32GB",
"price": 323760,
"image":
"https://daisycon.io/images/mobile-device/?width=250&height=250&color=ffffff&mobile_device_
brand=apple&mobile_device_model=iphone+7+32gb&mobile_device_color=silver"
}

]
```


El segundo endpoint corresponde al detalle y se accede indicando el id específico.

    http://my-json-server.typicode.com/Himuravidal/FakeAPIdata/details/1

En esta respuesta se encuentra tan sólo 1 objeto producto que corresponde al id, la diferencia es que tiene todos los datos.

#### Full Product detail
```json
{
"id": 1,
"name": "Samsung Galaxy A21s 64GB",
"price": 167253,
"image":
"https://images.samsung.com/is/image/samsung/es-galaxy-a21s-sm-a217fzkoe
ub-262755098?$PD_GALLERY_L_JPG$",
"description": "Tamaño 6,5''\n Densidad 294 ppi\nResolución de
pantalla 720 x 1600",
"lastPrice": 177253,
"credit": true
}
```



### Testing.

Se deben incluir al menos 2 tipos de test distintos para comprobar partes del código.
Algunos tipos de test que se pueden implementar son:

    ●   Test unitario que verifique la respuesta de los endpoints usando un servidor de pruebas como mockserver.

    ●   Test instrumental que compruebe la persistencia de datos con ROOM.
    
    ●   Test unitarios sobre cualquier función.
    
    ●   Test de interfaz de usuario usando Expresso test recorder.


### Correo a Enviar.

Cuando el usuario está viendo el detalle de un producto al hacer clic en el botón flotante tiene que enviar un correo con la siguiente información pre llenada:

    ●   Destinatario: info@plaplix.cl
    
    ●   Asunto: Consulta {PRODUCT_NAME} id {PRODUCT_ID}
    
    ●   Mensaje:

    “Hola
    
    Vi el producto {PRODUCT_NAME} y me gustaría que me contactaran a este correo o al
    siguiente número _________ 
    
    Quedo atento.”


IMPORTANTE: Recuerde reemplazar {PRODUCT_NAME} y {PRODUCT_ID} por la información
correspondiente al producto.

No es necesario que el usuario llene el número de ante mano, conserve los guiones bajos u otro símbolo que le indique al usuario que ahí tiene que escribir su número cuando esté viendo el mensaje en la aplicación de correos.