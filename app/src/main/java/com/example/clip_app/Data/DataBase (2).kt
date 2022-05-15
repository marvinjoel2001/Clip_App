package Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.clip_app.Data.Recordatorio

val tablas=TablasUtilidades()
class DataBase(var context:Context):SQLiteOpenHelper(context,"MyDB",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableRecordatorio =tablas.CREAR_TABLA_RECORDATORIO
        val createTableUser=tablas.CREAR_TABLA_USUARIO
        db?.execSQL(createTableUser)
        db?.execSQL(createTableRecordatorio)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
    //guardar usuario en la base de datos
    fun saveUser(user:User){
        val db=this.writableDatabase
        var cv=ContentValues()
        cv.put(tablas.COL_NAME,user.name)
        var result=db.insert(tablas.TABLE_NAME_USER,null,cv)
        if (result==(-1).toLong())
            Toast.makeText(context,"Error al Guardar",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Guardado",Toast.LENGTH_SHORT).show()
    }
    //guardar recordatorios en la base de datos
    fun saveRecuerdo(recordatorio:Recordatorio){
        val db=this.writableDatabase
        var cv=ContentValues()
        cv.put(tablas.COL_NAME,recordatorio.name)
        cv.put(tablas.COL_FECHA,recordatorio.fecha)
        cv.put(tablas.COL_HORA,recordatorio.hora)
        cv.put(tablas.COL_DATE,recordatorio.date)
        cv.put(tablas.COL_CATEGORIA,recordatorio.categoria)
        cv.put(tablas.COL_DESCRIPCION,recordatorio.desc)
        var result=db.insert(tablas.TABLE_NAME_RECORDATORIO,null,cv)
        if (result==(-1).toLong())
            Toast.makeText(context,"Error al Guardar aqui",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Guardado",Toast.LENGTH_SHORT).show()
    }

    //leer usuario de la base de datos
    fun readUser():MutableList<User>{
        var list : MutableList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from " + tablas.TABLE_NAME_USER
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var user = User(result.getString(result.getColumnIndex(tablas.COL_ID)).toInt(),result.getString(result.getColumnIndex(tablas.COL_NAME)))
                list.add(user)
            }while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
    //leer recordatorio de la base de datos
    fun readRecuerdo():MutableList<Recordatorio> {
        var listR: MutableList<Recordatorio> = ArrayList()
        val db = this.readableDatabase
        val query =
            "Select * from " + tablas.TABLE_NAME_RECORDATORIO + " order by " + tablas.COL_FECHA
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var recordatorio = Recordatorio(
                    result.getString(result.getColumnIndex(tablas.COL_ID)).toInt(),
                    result.getString(result.getColumnIndex(tablas.COL_NAME)),
                    result.getString(result.getColumnIndex(tablas.COL_FECHA)),
                    result.getString(result.getColumnIndex(tablas.COL_HORA)),
                    result.getString(result.getColumnIndex(tablas.COL_DATE)),
                    result.getString(result.getColumnIndex(tablas.COL_CATEGORIA)),
                    result.getString(result.getColumnIndex(tablas.COL_DESCRIPCION))
                )
                listR.add(recordatorio)
            } while (result.moveToNext())
        }
            result.close()
            db.close()
            return listR
    }
    //borrar la base de datos
    fun deleteData(){
        val db=this.writableDatabase
        db.delete(tablas.TABLE_NAME_USER,null, null)
        db.delete(tablas.TABLE_NAME_RECORDATORIO,null,null)
        db.close()
    }
    //borrar usuario de la base de datos
    fun deleteUser(id:Int){
        val db = this.writableDatabase
        db.delete(tablas.TABLE_NAME_USER, tablas.COL_ID+"=?", arrayOf(id.toString()))
        db.close()
    }
    //borrar recordatorio
    fun deleteRecordatorio(id:Int){
        val db = this.writableDatabase
        db.delete(tablas.TABLE_NAME_RECORDATORIO, tablas.COL_ID+"=?", arrayOf(id.toString()))
        db.close()
    }
    //actualizar recordatorio
    fun updateRecordatorio(recordatorio: Recordatorio){
        val db=this.writableDatabase
        val query="Select * from "+ tablas.TABLE_NAME_RECORDATORIO
        val result=db.rawQuery(query,null)
        var cv=ContentValues()
        cv.put(tablas.COL_NAME,recordatorio.GetName())
        cv.put(tablas.COL_FECHA,recordatorio.GetFecha())
        cv.put(tablas.COL_DESCRIPCION,recordatorio.GetDescripcion())
        db.update(tablas.TABLE_NAME_RECORDATORIO,cv, tablas.COL_ID + "=${recordatorio.id}",null)
        result.close()
        db.close()

    }
    //actualiza usuario
    fun updateUser(user: User){
        val db=this.writableDatabase
        val query="Select * from "+ tablas.TABLE_NAME_USER
        val result=db.rawQuery(query,null)
        var cv=ContentValues()
        cv.put(tablas.COL_NAME,user.GetName())
        db.update(tablas.TABLE_NAME_USER,cv, tablas.COL_ID + "=${user.id}",null)
        result.close()
        db.close()

    }
    fun readRecuerdoId(id: Int):Recordatorio {
        var Id=id.toString()
        var listR: MutableList<Recordatorio> = ArrayList()
        val db = this.readableDatabase
        val query="select * from "+ tablas.TABLE_NAME_RECORDATORIO + " WHERE _id=?"
        val result = db.rawQuery(query,arrayOf(Id))
        var recordatorio2 = Recordatorio(
            result.getString(result.getColumnIndex(tablas.COL_ID)).toInt(),
            result.getString(result.getColumnIndex(tablas.COL_NAME)),
            result.getString(result.getColumnIndex(tablas.COL_FECHA)),
            result.getString(result.getColumnIndex(tablas.COL_HORA)),
            result.getString(result.getColumnIndex(tablas.COL_DATE)),
            result.getString(result.getColumnIndex(tablas.COL_CATEGORIA)),
            result.getString(result.getColumnIndex(tablas.COL_DESCRIPCION))
        )
        result.close()
        db.close()
        return recordatorio2
    }

}

