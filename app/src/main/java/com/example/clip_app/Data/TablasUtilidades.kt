package Data

class TablasUtilidades {
    val TABLE_NAME_USER="usuario"
    val TABLE_NAME_RECORDATORIO="recordatorio"
    val COL_ID="id"
    val COL_NAME="name"
    val COL_FECHA="fecha"
    val COL_HORA="hora"
    val COL_DATE="date"
    val COL_CATEGORIA="categories"
    val COL_DESCRIPCION="descripcion"
    val CREAR_TABLA_USUARIO="CREATE TABLE " + TABLE_NAME_USER +" (" +
            COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_NAME + " VARCHAR(256))"
    val CREAR_TABLA_RECORDATORIO="CREATE TABLE " + TABLE_NAME_RECORDATORIO +" (" +
            COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_NAME + " VARCHAR(256)," +
            COL_FECHA +" TEXT,"+
            COL_HORA +" TEXT,"+
            COL_DATE +" TEXT,"+
            COL_CATEGORIA +" VARCHAR(256),"+
            COL_DESCRIPCION +" VARCHAR NOT NULL)"

}