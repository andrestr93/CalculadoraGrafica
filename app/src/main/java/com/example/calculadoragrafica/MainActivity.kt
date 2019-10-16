package com.example.calculadoragrafica

import android.content.res.Configuration
import android.graphics.Bitmap
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.*
import androidx.core.graphics.toColor
import androidx.core.graphics.toColorLong
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import kotlin.math.log

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.util.*

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import org.w3c.dom.Text


class MainActivity : AppCompatActivity(), View.OnClickListener {


    // DECLARACION DE VARIABLES
    // COMO PRIMER PASO DEBEMOS DE DECLARAR EL TIPO DE OBJETO DE LA INTERFAZ
    // GRAFICA EJEMPLO VAR EDITNOMBRE: EDITTEXT ?= NULL LA INTERROGACION Y EL NULL
    /*
    ES PARA PONERLO EN NULO

     */



    private var operador: String? = null
    private var boton: String? = null
    private var valoranterior = ""
    private var memoria = 0
    private var aux= 0
    private var pantalla: TextView? = null
    private var pantsegun: TextView? = null
    private var but1: Button? = null
    private var but2: Button? = null
    private var but3: Button? = null
    private var but4: Button? = null
    private var but5: Button? = null
    private var but6: Button? = null
    private var but7: Button? = null
    private var but8: Button? = null
    private var but9: Button? = null
    private var but0: Button? = null
    private var butmemonmas: Button? = null
    private var butmemon: Button? = null
    private var butresta: Button? = null
    private var butsuma: Button? = null
    private var butmultiplicacion: Button? = null
    private var butdivision: Button? = null
    private var butigual: Button? = null
    private var butc: Button? = null
    private var butdelete: Button? = null
    private var button17: Button? = null
     private var mp: MediaPlayer? =null
    private var espana: MediaPlayer? = null
    private var valorsegun = ""









    // constante private

    private val URL = "https://www.google.com" // url que se carga por defecto
    private val SEARCH = "/search?q=" // url que conecta con el motor de busqueda de google


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         mp = MediaPlayer.create(this , R.raw.click)
        espana = MediaPlayer.create(this, R.raw.himno)




// CONDICION QUE DICE SEGUN LA ORIENTACION


        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            configuracion()

        }
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

           // webview

            webview.webChromeClient = object: WebChromeClient(){

            }


            // aqui controlamos el refresco cuando empieza y cuando finaliza
            webview.webViewClient = object : WebViewClient(){

                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    return false
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)

                    ventanaweb.setQuery(url, false)

                    refresh.isRefreshing = true
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)

                    refresh.isRefreshing = false
                }

            }

            val settings  = webview.settings

            settings.javaScriptEnabled = true

            webview.loadUrl(URL)


            //refresh

            // funcion se encarga de refrescar la pagina

            refresh.setOnRefreshListener {

                webview.reload()
            }

            ventanaweb.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextChange(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(p0: String?): Boolean {

                    p0?.let {
                        if(URLUtil.isValidUrl(it)) {
                            // Es una url
                            webview.loadUrl(it)
                        } else {
                            // No es una url
                            webview.loadUrl("$URL$SEARCH$it")
                        }
                    }

                    return false
                }

            })

        }
    }

            /*
COMO 2º PASO COGEMOS Y HACEMOS REFERENCIA EL OBJETO QUE NOS HEMOS DECLARADO
CON NUESTRO OBJETO DE NUESTRA INTERFAZ GRAFICA
 */
            fun configuracion() {

                pantalla = findViewById(R.id.textView) as TextView // asociamos la variable
                // antes declarada con el componente grafico de la interfaz grafica
                // se consigue al invocar el findviewid que nos devulve la id
                pantsegun = findViewById(R.id.pantsegundaria) as TextView

                button17 = findViewById(R.id.button17) as Button
                but1 = findViewById(R.id.but1) as Button
                but2 = findViewById(R.id.but2) as Button
                but3 = findViewById(R.id.but3) as Button
                but4 = findViewById(R.id.but4) as Button
                but5 = findViewById(R.id.but5) as Button
                but6 = findViewById(R.id.but6) as Button
                but7 = findViewById(R.id.but7) as Button
                but8 = findViewById(R.id.but8) as Button
                but9 = findViewById(R.id.but9) as Button
                but0 = findViewById(R.id.but0) as Button
                butmemonmas = findViewById(R.id.butmemomas) as Button
                butmemon = findViewById(R.id.butmemon) as Button
                butresta = findViewById(R.id.butrestar) as Button
                butsuma = findViewById(R.id.butsuma) as Button
                butmultiplicacion = findViewById(R.id.butmultiplicar) as Button
                butdivision = findViewById(R.id.butdividir) as Button
                butigual = findViewById(R.id.butigual) as Button
                butc = findViewById(R.id.butc) as Button
                butdelete = findViewById(R.id.butdelete) as Button

                button17!!.setOnClickListener(this)
                but1!!.setOnClickListener(this)
                but2!!.setOnClickListener(this)
                but3!!.setOnClickListener(this)
                but4!!.setOnClickListener(this)
                but5!!.setOnClickListener(this)
                but6!!.setOnClickListener(this)
                but7!!.setOnClickListener(this)
                but8!!.setOnClickListener(this)
                but9!!.setOnClickListener(this)
                but0!!.setOnClickListener(this)
                butmemonmas!!.setOnClickListener(this)
                butmemon!!.setOnClickListener(this)
                butresta!!.setOnClickListener(this)
                butmultiplicacion!!.setOnClickListener(this)
                butdivision!!.setOnClickListener(this)
                butsuma!!.setOnClickListener(this)
                butigual!!.setOnClickListener(this)
                butc!!.setOnClickListener(this)
                butdelete!!.setOnClickListener(this)

            }


    /*
        override fun onSaveInstanceState(bundle: Bundle) {
            super.onSaveInstanceState(bundle)
            bundle.putInt("ultimo valor", Integer.parseInt(pantalla?.text.toString()))
        }

     */


    /*

    override fun onRestoreInstanceState(bundle: Bundle) {
        super.onRestoreInstanceState(bundle)
         numero = bundle.getInt("ultimovalor")
        pantalla?.setText("" + numero)
    }

     */



     /*

    sobreescribimos onbackpresed que lo que hace es
    que si pulsamos en la flecha de atras nos vuelta a la pagina anterior si no
    se sale del navegador si estamos en la pagina de google

      */

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            super.onBackPressed()
        }
    }



            override fun onClick(v: View?) {

                when (v?.id) {

                    R.id.but1 -> {
                        pantalla?.setText(pantalla?.text.toString() + "1")
                        mp?.start()

                    }
                    R.id.but2 -> {
                        pantalla?.setText(pantalla?.text.toString() + "2")
                        mp?.start()

                    }
                    R.id.but3 -> {
                        pantalla?.setText(pantalla?.text.toString() + "3")
                        mp?.start()
                    }
                    R.id.but4 -> {
                        pantalla?.setText(pantalla?.text.toString() + "4")
                        valorsegun = pantalla?.text.toString() + "4"
                        mp?.start()
                    }

                    R.id.but5 -> {
                        pantalla?.setText(pantalla?.text.toString() + "5")
                        mp?.start()
                    }
                    R.id.but6 -> {
                        pantalla?.setText(pantalla?.text.toString() + "6")
                        mp?.start()

                    }
                    R.id.but7 -> {
                        pantalla?.setText(pantalla?.text.toString() + "7")
                        mp?.start()
                    }
                    R.id.but8 -> {
                        pantalla?.setText(pantalla?.text.toString() + "8")
                        mp?.start()

                    }
                    R.id.but9 -> {
                        pantalla?.setText(pantalla?.text.toString() + "9")
                        mp?.start()
                    }
                    R.id.but0 -> {
                        pantalla?.setText(pantalla?.text.toString() + "0")
                        mp?.start()
                    }

                    R.id.butmemon -> {
                        mp?.start()
                        if (memoria==0 ){

                            Toast.makeText(this , "Memoria vacia " , Toast.LENGTH_LONG).show()
                        } else{

                        pantalla?.setText(memoria.toString())

                        }
                    }

                    R.id.butdelete ->{

                        if (memoria==0 ) {

                            Toast.makeText(this , "Memoria vacia"  , Toast.LENGTH_SHORT).show()

                        }else{
                            memoria = 0
                            pantalla?.setText("")
                            pantsegun?.setText("")
                        }

                        mp?.start()
                    }


                    R.id.butdividir -> {
                        mp?.start()

                        if (!pantalla?.text.toString().equals("")|| !pantsegun?.text.toString().equals("")) {
                            valoranterior = pantalla?.text.toString()
                            pantalla?.setText("")
                           // pantsegun?.setText(valoranterior + " / ")
                            operador = "/"
                        }else{

                            Toast.makeText(this , "no hay valores a los que calcular" , Toast.LENGTH_SHORT).show()

                        }

                    }
                    R.id.butmultiplicar -> {
                        mp?.start()
                        valoranterior = pantalla?.text.toString()
                        pantalla?.setText("")
                       // pantsegun?.setText(valoranterior + " X ")
                        operador = "x"
                    }
                    R.id.butsuma -> {

                        mp?.start()

                        valoranterior = pantalla?.text.toString()
                        pantalla?.setText("")
                       // pantsegun?.setText(valoranterior  + " + ")
                        operador = "+"


                    }
                    R.id.butrestar -> {
                        mp?.start()
                        valoranterior = pantalla?.text.toString()
                        pantalla?.setText("")
                       // pantsegun?.setText(valoranterior +  " - ")
                        operador = "-"
                    }


                    R.id.butmemomas -> {

                        mp?.start()

                        var comprueba = pantalla?.text.toString()

                       if (!comprueba.equals("")) {

                           valoranterior = pantalla?.text.toString()

                           aux = valoranterior.toInt() + memoria

                           memoria = aux

                           pantalla?.setText(aux.toString())


                       } else{

                           Toast.makeText(this , "No hay datos a los que sumar"
                               , Toast.LENGTH_SHORT).show()


                       }

                    }

                    R.id.button17 -> {
                      //  espana?.start()

                    }

                    R.id.butigual -> {
                        mp?.start()
                        pantsegun?.setText("")

                            when (operador) {

                                "+" -> {
                                    var comprueba = pantalla?.text.toString()

                                    if (!comprueba.equals("")) {


                                        var resultado =
                                            valoranterior?.toInt()!! + pantalla?.text.toString().toInt()
                                        pantalla?.setText(resultado.toString())

                                    } else {

                                        Toast.makeText(
                                            this,
                                            "Error , operacion incorrecta",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    }


                                }

                                "-" -> {

                                    var comprueba = pantalla?.text.toString()
                                    if (!comprueba.equals("")) {

                                        var resultado =
                                            valoranterior?.toInt()!! - pantalla?.text.toString().toInt()
                                        pantalla?.setText(resultado.toString())

                                    } else {

                                        Toast.makeText(
                                            this,
                                            "Error , operación incorrecta",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                                "/" -> {
                                    var comprueba = pantalla?.text.toString()

                                    if (!comprueba.equals("")) {

                                        var resultado =
                                            valoranterior?.toInt()!! / pantalla?.text.toString().toInt()
                                        pantalla?.setText(resultado.toString())

                                    } else {
                                        Toast.makeText(
                                            this,
                                            "Error , operación incorrecta",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    }
                                }


                                "x" -> {
                                    var comprueba = pantalla?.text.toString()
                                    if (!comprueba.equals("")) {

                                        var resultado =
                                            valoranterior?.toInt()!! * pantalla?.text.toString().toInt()
                                        pantalla?.setText(resultado.toString())

                                    } else {

                                        Toast.makeText(
                                            this,
                                            "Error , operación incorrecta",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                }

                            }
                    }
                    R.id.butc -> {
                        mp?.start()

                        var comprueba = pantalla?.text.toString()
                        var compruebasegun = pantsegun?.text.toString()

                        if (comprueba.equals("") && compruebasegun.equals("")) {

                            Toast.makeText(this, "Pantalla sin datos ", Toast.LENGTH_LONG).show()

                        } else {

                            pantalla?.setText("")
                            pantsegun?.setText("")
                        }


                    }


                }

            }

        }


















