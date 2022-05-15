package com.example.clip_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_eleccion_anadir.*

class EleccionAnadirFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eleccion_anadir, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_crear.setOnClickListener(){
            it.findNavController().navigate(R.id.action_eleccionAnadirFragment_to_newRecorder_Fragment2)
        }
        btn_codigo.setOnClickListener(){
            it.findNavController().navigate(R.id.action_eleccionAnadirFragment_to_inputCodigoFragment2)
        }
    }


}