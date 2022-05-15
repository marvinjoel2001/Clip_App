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
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.clip_app.Data.Recordatorio
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.android.synthetic.main.fragment_new_recorder_.*
import java.util.*

class EditFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //consultarDatosID(id)

        et_editCategoria.setOnClickListener(){
            desplegarAlertDialog()
        }
        btn_guardar_edit.setOnClickListener(){
            if (ComprobarVacios()){
            editarDataBase()
            it.findNavController().navigate(R.id.action_editFragment_to_opcionAlarmFragment3)
            }else{
                Toast.makeText(context,"Campos vacios", Toast.LENGTH_SHORT).show()
            }
        }
        et_editFecha.setOnClickListener(){

            showDatePickerDialog()
        }
        et_editHora.setOnClickListener(){
            showTimePickerDialog()
        }
    }
    fun desplegarAlertDialog(){
        var builder= AlertDialog.Builder(activity)
        var list:Array<String> = activity?.resources!!.getStringArray(R.array.choice_items)
        builder.setTitle("Seleccione una categoria")
        //carga la lista en el builder del alert dialog
        builder.setSingleChoiceItems(list,1){dialogInterface, i->
            et_editCategoria.setText(list[i])}
        //marca por si se elije ok
        builder.setPositiveButton("Ok"){ dialogInterface: DialogInterface, i:Int ->
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

    fun editarDataBase(){
        //conecta con la base de datos
        var db= DataBase(requireContext())
        //comprueba que no aiga ningun campo vacio
        var recorda= Recordatorio(id,et_editNombre.text.toString(),et_editFecha.text.toString(),et_editHora.text.toString(),et_editFecha.text.toString()+" "+et_editHora.text.toString(),et_editCategoria.text.toString(),et_editDescripcion.text.toString())
            //accede al metodo aguardar de la bse de datos y lo guarda
        db.updateRecordatorio(recorda)
            //vacia los edittext
        VaciarCampos()


    }
    private fun ComprobarVacios():Boolean{
        var CamposVacios:Boolean=false
        if (et_editNombre.text.toString().length > 0 &&
            et_editCategoria.text.toString().length > 0 &&
            et_editFecha.text.toString().length > 0 &&
            et_editHora.text.toString().length> 0 &&
            et_editDescripcion.text.toString().length>0){
            CamposVacios=true
        }
       return CamposVacios
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
                    et_editFecha.setText("$year-$monthOfYear-$dayOfMonth")
                }, year, month, day)
            }
        //show datepicker
        dpd?.show()
    }
    private fun showTimePickerDialog(){
        val c = Calendar.getInstance()
        val hour=c.get(Calendar.HOUR_OF_DAY)
        val minute=c.get(Calendar.MINUTE)
        val tdp=
            activity?.let {
                TimePickerDialog(it,android.R.style.Theme_Holo_Dialog, TimePickerDialog.OnTimeSetListener(function = { TimePicker, hour, minute ->
                    et_editHora.setText("$hour:$minute")
                }),hour,minute,true)
            }
        tdp?.show()
    }
    fun consultarDatosID(id:Int){
        var db= DataBase(requireContext())
        var data=db.readRecuerdoId(id)
        et_editNombre.setText(data.name)
        et_editFecha.setText(data.fecha)
        et_editCategoria.setText(data.categoria)
        et_editHora.setText(data.hora)
        et_editDescripcion.setText(data.desc)
    }

}