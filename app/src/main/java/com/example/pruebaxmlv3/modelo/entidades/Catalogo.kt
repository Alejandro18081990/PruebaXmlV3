package com.example.pruebaxmlv3.modelo.entidades

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "catalogo")
data class Catalogo constructor(
    @field: ElementList(inline = true, entry = "pelicula")
    var peliculas: List<Pelicula> = mutableListOf()
)

@Root(name = "pelicula")
data class Pelicula constructor(
    @field:Element(name = "titulo")
    var titulo: String = "",

    @field:Element(name = "director")
    var director: String = "",

    @field:Element(name = "genero")
    var genero: String = "",

    @field:Element(name = "anio")
    var anio: String = "",

    @field:Element(name = "calificacion")
    var calificacion: String = "",

    @field:Element(name = "duración")
    var duracion: String = "",
)
