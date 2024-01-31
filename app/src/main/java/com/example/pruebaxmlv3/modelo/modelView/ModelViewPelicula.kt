package com.example.pruebaxmlv3.modelo.modelView

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pruebaxmlv3.modelo.daoSimpleXml.DaoSimpleXml
import com.example.pruebaxmlv3.modelo.entidades.Pelicula

class ModelViewPelicula(application: Application) : AndroidViewModel(application) {

    var daoSimpleXml = DaoSimpleXml(application)


    fun addPelicula(pelicula: Pelicula) {
        daoSimpleXml.addPelicula(pelicula)
    }

    fun procesarFicheroXmlInterno(): MutableList<Pelicula> {
        return daoSimpleXml.procesarFicheroXmlInterno()
    }

    fun procesarFicheroXml(): MutableList<Pelicula> {
        return daoSimpleXml.procesarFicheroXml()
    }

    fun procesarFicheroSAX(): MutableList<Pelicula> {
        return daoSimpleXml.procesarArchivoXMLSAX()
    }

    fun procesarFicheroSAXPorCriterio(criterio: String): MutableList<Pelicula> {
        return daoSimpleXml.procesarArchivoXMLSAXPorCriterio(criterio)
    }
}