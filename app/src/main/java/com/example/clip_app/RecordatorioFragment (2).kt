package com.example.clip_app

import Data.DataBase
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_recordatorio.*

class RecordatorioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recordatorio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var Id:Int=0
        btn_editar_floating.setOnClickListener(){
            it.findNavController().navigate(R.id.action_recordatorioFragment_to_editFragment)
        }
        btn_borrar.setOnClickListener(){
            DesplegarAlertDialog(Id)
        }


    }
    private fun DesplegarAlertDialog(id:Int){
        val eliminarEstado:Boolean
        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle("Eliminar")
        alertDialogBuilder.setMessage("Estas seguro de eliminar este recordatorio")
        alertDialogBuilder.setPositiveButton("Eliminar"){ dialogInterface, which ->
            EliminarRecordatorio(id)
            dialogInterface.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Cancel"){dialogInterface, i ->
            dialogInterface.cancel()
        }
        alertDialogBuilder.show()

    }
    private fun EliminarRecordatorio(Id: Int){
        var db=DataBase(requireContext())
        db.deleteRecordatorio(Id)

    }
}