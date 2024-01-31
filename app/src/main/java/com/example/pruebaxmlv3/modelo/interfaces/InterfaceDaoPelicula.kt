package com.example.pruebaxmlv3.modelo.interfaces

import com.example.pruebaxmlv3.modelo.entidades.Pelicula

interface InterfaceDaoPelicula {

    fun addPelicula(pelicula: Pelicula)

    fun procesarFicheroXml(): MutableList<Pelicula>

    fun procesarArchivoXMLSAX(): MutableList<Pelicula>

    fun procesarArchivoXMLSAXPorCriterio(critero: String): MutableList<Pelicula>

    fun procesarFicheroXmlInterno(): MutableList<Pelicula>

    fun copiarArchivo()
}