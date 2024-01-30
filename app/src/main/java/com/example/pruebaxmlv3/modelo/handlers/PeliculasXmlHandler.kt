package com.example.pruebaxmlv3.modelo.handlers

import android.media.metrics.PlaybackErrorEvent
import android.util.Log
import com.example.pruebaxmlv3.modelo.entidades.Catalogo
import com.example.pruebaxmlv3.modelo.entidades.Pelicula
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class PeliculasXmlHandler : DefaultHandler() {

    private val cadena = StringBuilder()
    private var pelicula: Pelicula? = null
    var peliculas: MutableList<Pelicula> = mutableListOf()

    @Throws(SAXException::class)
    override fun startDocument() {
        cadena.clear()
        peliculas = mutableListOf()
        println("startDocument")
    }

    @Throws(SAXException::class)
    override fun startElement(
        uri: String,
        nombreLocal: String,
        nombre: String,
        attributes: Attributes
    ) {
        cadena.setLength(0)
        if (nombre == "pelicula") {
            pelicula = Pelicula()
        }
    }

    @Throws(SAXException::class)
    override fun characters(ch: CharArray, start: Int, length: Int) {
        cadena.append(ch, start, length)
        println("dato final: $cadena")
    }

    @Throws(SAXException::class)
    override fun endElement(uri: String, nombreLocal: String, nombre: String) {
        when (nombre) {
            "titulo" -> pelicula?.titulo = cadena.toString()
            "director" -> pelicula?.director = cadena.toString()
            "genero" -> pelicula?.genero = cadena.toString()
            "anio" -> pelicula?.anio = cadena.toString()
            "calificacion" -> pelicula?.calificacion = cadena.toString()
            "duración" -> pelicula?.duracion = cadena.toString()
            //Aquí se añade la película
            "pelicula" -> peliculas.add(pelicula!!)
        }
    }

    @Throws(SAXException::class)
    override fun endDocument() {
        println("endDocument")

    }
}