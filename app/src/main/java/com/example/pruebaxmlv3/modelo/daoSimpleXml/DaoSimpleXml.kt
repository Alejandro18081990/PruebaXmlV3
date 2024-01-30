package com.example.pruebaxmlv3.modelo.daoSimpleXml

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.example.pruebaxmlv3.modelo.entidades.Catalogo
import com.example.pruebaxmlv3.modelo.entidades.Pelicula
import com.example.pruebaxmlv3.modelo.handlers.PeliculasXmlHandler
import com.example.pruebaxmlv3.modelo.interfaces.InterfaceDaoPelicula
import org.simpleframework.xml.core.Persister
import java.io.*
import javax.xml.parsers.SAXParserFactory

class DaoSimpleXml(var context: Context) : InterfaceDaoPelicula {

    private val serializer = Persister()
    private var nombreFichero = "peliculas.xml"
    var listaPeliculas = mutableListOf<Pelicula>()

    override fun addPelicula(pelicula: Pelicula) {
        try {
            listaPeliculas.add(pelicula)
            val listaPeliculas = Catalogo(listaPeliculas)
            val outputStream = context.openFileOutput(nombreFichero, MODE_PRIVATE)
            serializer.write(listaPeliculas, outputStream)
            outputStream.close()
        } catch (e: IOException) {
            Log.d("ErrorAnadir", e.message.toString())
        }
        Log.d("Añadida", "Pelicula añadida")
    }

    override fun procesarFicheroXmlInterno(): MutableList<Pelicula> {
        var peliculas = mutableListOf<Pelicula>()
        try {
            val file = File(context.filesDir, nombreFichero)
            val inputStream = FileInputStream(file)
            val peliculasList = serializer.read(Catalogo::class.java, inputStream)
            peliculas.addAll(peliculasList.peliculas)
            inputStream.close()
        } catch (e: java.lang.Exception) {
            Log.d("ErrorLeerInterno", e.message.toString())
        }
        return peliculas
    }


    override fun procesarFicheroXml(): MutableList<Pelicula> {
        var inputStream: InputStream? = null
        var reader: InputStreamReader? = null
        try {
            inputStream = context.assets.open(nombreFichero)
            reader = InputStreamReader(inputStream)
            val peliculasListType = serializer.read(Catalogo::class.java, reader, false)
            listaPeliculas.addAll(peliculasListType.peliculas)
            reader.close()
            inputStream.close()
        } catch (e: java.lang.Exception) {
            Log.d("ErrorProcesarSimple", e.message.toString())
        }
        return listaPeliculas
    }

    override fun procesarArchivoXMLSAX(): MutableList<Pelicula> {
        try {
            val factory = SAXParserFactory.newInstance()
            val parser = factory.newSAXParser()
            val handler = PeliculasXmlHandler()
            val inputStream = context.assets.open(nombreFichero)
            parser.parse(inputStream, handler)
            listaPeliculas = handler.peliculas
        } catch (e: java.lang.Exception) {
            Log.d("ErrorProcesarSAX", e.message.toString())
        }
        return listaPeliculas
    }


    override fun copiarArchivo() {
        TODO("Not yet implemented")
    }
}