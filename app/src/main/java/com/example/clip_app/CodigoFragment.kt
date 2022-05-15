package com.example.clip_app

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.fragment_codigo.*

class CodigoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_codigo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    btn_copiar.setOnClickListener(){
        val clip=ClipData.newPlainText("texto simple",et_codigoNew.text)
        val clipboard =activity?.getSystemService(CLIPBOARD_SERVICE)as ClipboardManager
        clipboard.setPrimaryClip(clip)
        Toast.makeText(requireContext(),"Copiado  en el Portapapeles",Toast.LENGTH_SHORT).show()
    }

    }
}