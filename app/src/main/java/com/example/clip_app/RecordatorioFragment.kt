package com.example.clip_app

import Data.DataBase
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.KeyCharacterMap
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.clip_app.Data.Recordatorio
import com.example.clip_app.Data.RecordatorioCompartido
import com.example.clip_app.Interface.iComunicaFragments
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recordatorio.*

class RecordatorioFragment : Fragment(){
    lateinit var intentBottom:Intent
    private var Id: String? = null
    private lateinit var IdR:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recordatorio, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intentBottom=Intent(requireContext(),BottomNavigationBar::class.java)
        Id=arguments?.getString("MIID")
        IdR=Id.toString()
        LlenarDescripcion(ConsultaDatos(IdR))
        btn_editar_floating.setOnClickListener(){
            var bundle=Bundle()
            bundle.putString("LAKEY",IdR)
            it.findNavController().navigate(R.id.action_recordatorioFragment_to_editFragment,bundle)
        }
        btn_borrar.setOnClickListener(){
            AlertDialogEliminar(IdR)
        }
        btn_compartir.setOnClickListener(){
            AlertDialogCompartir(IdR)
        }
        btn_volver.setOnClickListener(){
            onDestroy()
            startActivity(intentBottom)
        }
    }
    private fun AlertDialogEliminar(id:String){
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
    private fun EliminarRecordatorio(Id: String){
        var db=DataBase(requireContext())
        db.deleteRecordatorio(Id)
        onDestroy()
        startActivity(intentBottom)
    }
    private fun CompartirRecordatorio(recordatorio: Recordatorio){
        val ref =FirebaseDatabase.getInstance().getReference("recordatorios")
        val recordatorioId=ref.push().key
        val recordatorioC=RecordatorioCompartido(recordatorioId!!,recordatorio.GetName()
            ,recordatorio.GetFecha()
            ,recordatorio.GetHora()
            ,recordatorio.GetDate()
            ,recordatorio.GetCategoria()
            ,recordatorio.GetDescripcion())
        ref.child(recordatorioId).setValue(recordatorioC).addOnCompleteListener{
            Toast.makeText(context,"Generando codigo", Toast.LENGTH_LONG).show()
            withDelay(5000){PasarFragment()}
        }
    }
    private fun ConsultaDatos(id: String):Recordatorio{
        val RecordatorioObjetc:Recordatorio
        val db=DataBase(requireContext())
        RecordatorioObjetc=db.readRecuerdoId(id)
        return RecordatorioObjetc
    }
    private fun LlenarDescripcion(recordatorio: Recordatorio){
        tv_Mnombre.text=recordatorio.GetName()
        tv_Mfecha.text=recordatorio.GetFecha()
        tv_Mhora.text=recordatorio.GetHora()
        tv_Mdescripcion.text=recordatorio.GetDescripcion()
        tv_Mcategoria.text=recordatorio.GetCategoria()
    }
    private fun AlertDialogCompartir(id: String){
        val eliminarEstado:Boolean
        val alertDialogBuilder = AlertDialog.Builder(activity)
        alertDialogBuilder.setTitle("Compartir")
        alertDialogBuilder.setMessage("Desea compartir este recordatorio")
        alertDialogBuilder.setPositiveButton("Compartir"){ dialogInterface, which ->
            CompartirRecordatorio(ConsultaDatos(id))
            dialogInterface.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Cancel"){dialogInterface, i ->
            dialogInterface.cancel()
        }
        alertDialogBuilder.show()
    }
    fun withDelay(delay : Long, block : () -> Unit) { Handler().postDelayed(Runnable(block), delay) }
    private fun PasarFragment(){
        view?.findNavController()!!.navigate(R.id.action_recordatorioFragment_to_codigoFragment)
    }
}