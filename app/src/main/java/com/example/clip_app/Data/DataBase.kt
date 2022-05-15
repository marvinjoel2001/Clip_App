package Data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.clip_app.Data.Recordatorio
import java.security.AccessController.getContext

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
    fun comprobarUser():Boolean {
        var count = 0
        val mDbHelper = this.readableDatabase
        val db = this.writableDatabase
        val tabla = tablas.TABLE_NAME_USER
        val cursor: Cursor? = db.rawQuery("SELECT count(*) FROM $tabla", null)
        try {
            if (cursor != null) if (cursor.getCount() > 0) {
                cursor.moveToFirst()
                count = cursor.getInt(0)
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close()
            }

            if (count > 0)
                return false
             else
                return true
        }
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
    fun readUser():String{
        val db = this.readableDatabase
        val query = "Select * from " + tablas.TABLE_NAME_USER
        val result = db.rawQuery(query,null)
        var usuario=""
        if(result.moveToFirst()){
                usuario = result.getString(result.getColumnIndex(tablas.COL_NAME))
        }
        result.close()
        db.close()
        return usuario
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
                    result.getInt(result.getColumnIndex(tablas.COL_ID)),
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
    fun deleteRecordatorio(id:String){
        val db = this.writableDatabase
        db.delete(tablas.TABLE_NAME_RECORDATORIO, tablas.COL_ID+"=?", arrayOf(id))
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
    fun readRecuerdoId(id:String):Recordatorio {
        var listR: MutableList<Recordatorio> = ArrayList()
        var key=id
        var recordatorio3=Recordatorio(key.toInt(),"no da","no da","no da","no da","no da","no da")
        val db = this.readableDatabase
        val query="select * from "+ tablas.TABLE_NAME_RECORDATORIO + " WHERE id='$key' "
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            var recordatorio2 = Recordatorio(
                key.toInt(),
                result.getString(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5),
                result.getString(6)
            )
            result.close()
            db.close()
            return recordatorio2
        }
        return recordatorio3
    }
}

