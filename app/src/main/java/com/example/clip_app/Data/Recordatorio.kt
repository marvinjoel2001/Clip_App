package com.example.clip_app.Data

class Recordatorio {
    var id:Int?=0
    var name:String
    var fecha:String
    var hora:String
    var date:String
    var categoria:String
    var desc:String
    constructor(id:Int?,name:String,fecha:String,hour:String,date:String,cat:String,descripcion:String){
        this.id=id
        this.name=name
        this.fecha=fecha
        this.hora=hour
        this.date=date
        this.categoria=cat
        this.desc=descripcion
    }
    fun SetId(ide:Int){id=ide}
    fun GetId():Int?{return id}
    fun SetName(nombre:String){name=nombre}
    fun GetName():String{return name}
    fun SetFecha(fec: String){fecha=fec}
    fun GetFecha():String{return fecha}
    fun SetHora(hour: String){hora=hour}
    fun GetHora():String{return hora}
    fun SetDate(dat:String){date=dat}
    fun GetDate():String{return date}
    fun SetCategoria(cat:String){categoria=cat}
    fun GetCategoria():String{return categoria}
    fun SetDescripcion(descripcion:String){desc=descripcion }
    fun GetDescripcion():String{return desc }
}
