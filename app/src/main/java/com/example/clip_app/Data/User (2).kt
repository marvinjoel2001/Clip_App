package Data

class User {
    var id:Int?=0
    var name:String
    constructor(id:Int?,name:String){
        this.id=id
        this.name=name
    }
    fun SetName(nombre: String){name=nombre }
    fun GetName():String{return name }
}