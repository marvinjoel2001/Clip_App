package com.example.clip_app

import Data.DataBase
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.cats_soft.probadoralarmas.MyDatePickerDialog
import com.cats_soft.probadoralarmas.MyTimePickerDialog
import com.example.clip_app.Data.Recordatorio
import kotlinx.android.synthetic.main.fragment_election_alarm.*
import kotlinx.android.synthetic.main.fragment_new_recorder_.*
import java.util.*

class NewRecorder_Fragment : Fragment(){
    private var minute=0
    private var hour=0
    private var dayOfMonth=0
    private var month=0
    private var year=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_recorder_, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            et_newCategoria.setOnClickListener(){
                desplegarAlertDialog()
            }
            btn_guardar.setOnClickListener(){
                guardarDataBase()
            }
            et_newFechan.setOnClickListener(){

                showDatePickerDialog()
            }
            et_newHoran.setOnClickListener(){
                showTimePickerDialog()
            }
    }
    fun desplegarAlertDialog(){
        var builder= AlertDialog.Builder(activity)
        var list:Array<String> = activity?.resources!!.getStringArray(R.array.choice_items)
        builder.setTitle("Seleccione una categoria")
        //carga la lista en el builder del alert dialog
        builder.setSingleChoiceItems(list,0){dialogInterface, i->
            et_newCategoria.setText(list[i])}
        //marca por si se elije ok
        builder.setPositiveButton("Ok"){dialogInterface: DialogInterface, i:Int ->
            dialogInterface.dismiss()
        }
        //marca si se cancela
        builder.setNegativeButton("Cancel"){dialogInterface, i ->
            dialogInterface.cancel()
        }

        //crea en dialog
        val mDialog=builder.create()
        //desplega en builder
        builder.show()

    }
    fun guardarDataBase(){
        //conecta con la base de datos
        var db= DataBase(requireContext())
        //comprueba que no aiga ningun campo vacio
        if (et_newNombre.text.toString().length > 0 &&
            et_newCategoria.text.toString().length > 0 &&
            et_newFechan.text.toString().length > 0 &&
            et_newHoran.text.toString().length> 0 &&
            et_newDescripcion.text.toString().length>0) {
            var recorda=Recordatorio(null,et_newNombre.text.toString(),et_newFechan.text.toString(),et_newHoran.text.toString(),et_newFechan.text.toString()+" "+et_newHoran.text.toString(),et_newCategoria.text.toString(),et_newDescripcion.text.toString())
            //accede al metodo aguardar de la bse de datos y lo guarda
            db.saveRecuerdo(recorda)
            //vacia los edittext
            VaciarCampos()
            //cambia al activity principal una ves guardado
            var intentAll= Intent(activity,BottomNavigationBar::class.java)
            startActivity(intentAll)
            } else {
            //por si los campos estan vacios manda un mensaje
                Toast.makeText(context,"Campos vacios", Toast.LENGTH_SHORT).show()
            }
    }
    private fun VaciarCampos(){
        et_newNombre.setText("")
        et_newCategoria.setText("")
        et_newFechan.setText("")
        et_newHoran.setText("")
        et_newDescripcion.setText("")
    }
    private fun showDatePickerDialog(){
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)
        val dpd =
            activity?.let {
                DatePickerDialog(it, android.R.style.Theme_Holo_Dialog, DatePickerDialog.OnDateSetListener { DatePicker, year, monthOfYear, dayOfMonth ->
                    et_newFechan.setText("$year-$monthOfYear-$dayOfMonth")
                }, year, month, day)
            }
        //show datepicker
        dpd?.show()
    }
    private fun showTimePickerDialog(){
        val c =Calendar.getInstance()
        val hour=c.get(Calendar.HOUR_OF_DAY)
        val minute=c.get(Calendar.MINUTE)
        val tdp=

            activity?.let {
                TimePickerDialog(it,android.R.style.Theme_Holo_Dialog, TimePickerDialog.OnTimeSetListener(function = { TimePicker,hour,minute ->
                modificar(hour,minute)
            }),hour,minute,true)
            }

        tdp?.show()
    }
    fun modificar(hour:Int,minutes:Int){
        var hora=hour
        var minuto=minutes
        var cadena=""
        if(hora<10){
            cadena="0$hora"
        }else{
            cadena="$hora"
        }
        if(minuto<10){
            cadena=cadena+":0$minuto"
        }else{
            cadena=cadena+":$minuto"
        }
        et_newHoran.setText(cadena)

    }
}