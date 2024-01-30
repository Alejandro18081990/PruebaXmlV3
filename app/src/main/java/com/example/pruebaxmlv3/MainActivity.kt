package com.example.pruebaxmlv3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.pruebaxmlv3.modelo.entidades.Pelicula
import com.example.pruebaxmlv3.modelo.modelView.ModelViewPelicula

class MainActivity : AppCompatActivity() {
    lateinit var modelViewPelicula: ModelViewPelicula
    lateinit var listaPeliculas: MutableList<Pelicula>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        modelViewPelicula = ViewModelProvider(this)[ModelViewPelicula::class.java]


        var peliculas = arrayOf(
            Pelicula(
                "El caballero Oscuro",
                "Christopher Nolan",
                "Ciencia ficción",
                "2008",
                "10",
                "130"
            ),
            Pelicula("Drive", "Nicolas Wending", "Acción", "2012", "8", "110"),
            Pelicula("El señor de los anillos", "Peter Jackson", "Aventura", "2003", "9", "160")
        )

        //Operación de escritura en fichero interno
        for (pelicula in peliculas)
            modelViewPelicula.addPelicula(pelicula)

        //Lectura de fichero xml interno
        listaPeliculas = modelViewPelicula.procesarFicheroXmlInterno()
        for (pelicula in listaPeliculas)
            Log.d("VerPeliculasInterno", String.format("-%s", pelicula))

        //Lectura de fichero xml simple
        listaPeliculas = modelViewPelicula.procesarFicheroXml()
        for (pelicula in listaPeliculas)
            Log.d("VerPeliculasSimple", String.format("-%s", pelicula))

        //Lectura de fichero Xml usando SAX
        listaPeliculas = modelViewPelicula.procesarFicheroSAX()
        Log.d("Longitud", listaPeliculas.size.toString())
        for (pelicula in listaPeliculas)
            Log.d("VerPeliculasSAX", String.format("-%s", pelicula))
    }
}