package com.example.todolist.database



import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.todolist.data.Note


class DBManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "NotesDB"


        private const val TABLE_NOTES = "NotesTable"
        private const val KEY_ID = "id"
        private const val KEY_TITLE = "title"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_TIME = "timeanddate"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = ("CREATE TABLE $TABLE_NOTES ($KEY_ID INTEGER PRIMARY KEY,"
                + " $KEY_TITLE TEXT,"
                + " $KEY_DESCRIPTION TEXT,"
                + " $KEY_TIME TEXT)")

        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NOTES")
        onCreate(db)
    }


    @SuppressLint("Range")
    fun loadTable(): MutableList<Note> {
        val dataList = ArrayList<Note>()
        val selectQuery = "SELECT * FROM $TABLE_NOTES"
        val db = readableDatabase
        val cursor:Cursor?


        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            //db.execSQL(selectQuery)
            return ArrayList()
        }



        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val title = cursor.getString(cursor.getColumnIndex(KEY_TITLE))
                val description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
                val time = cursor.getString(cursor.getColumnIndex(KEY_TIME))



                val note = Note(
                    id = id,
                    title = title,
                    description = description,
                    time = time
                )
                dataList.add(note)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return dataList
    }

   /* fun getCalibrationDataBySerial(serial: Int): CalibrationData? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_NOTES,
            null, // Получаем все столбцы
            "$KEY_SERIAL = ?",
            arrayOf(serial.toString()),
            null, null, null
        )

        return if (cursor.moveToFirst()) {
            val calibrationData = CalibrationData(
                serial = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_SERIAL)),
                referenceTemperatureUpper = cursor.getFloat(cursor.getColumnIndexOrThrow(KEY_REFERENCE_TEMPERATURE_UPPER)),
                referenceTemperatureLower = cursor.getFloat(cursor.getColumnIndexOrThrow(KEY_REFERENCE_TEMPERATURE_LOWER)),
                referenceHumidityUpper = cursor.getFloat(cursor.getColumnIndexOrThrow(KEY_REFERENCE_HUMIDITY_UPPER)),
                referenceHumidityLower = cursor.getFloat(cursor.getColumnIndexOrThrow(KEY_REFERENCE_HUMIDITY_LOWER)),
                offsetTemperatureUpper = cursor.getFloat(cursor.getColumnIndexOrThrow(KEY_OFFSET_TEMPERATURE_UPPER)),
                offsetTemperatureLower = cursor.getFloat(cursor.getColumnIndexOrThrow(KEY_OFFSET_TEMPERATURE_LOWER)),
                offsetHumidityUpper = cursor.getFloat(cursor.getColumnIndexOrThrow(KEY_OFFSET_HUMIDITY_UPPER)),
                offsetHumidityLower = cursor.getFloat(cursor.getColumnIndexOrThrow(KEY_OFFSET_HUMIDITY_LOWER)),
                typeOfSensor = cursor.getInt(cursor.getColumnIndexOrThrow(KEY_SENSOR_TYPE)),
                0f,
                true
            )
            cursor.close()
            calibrationData
        } else {
            cursor.close()
            null
        }
    }*/



 /*   fun addData(calibrationData: CalibrationData): Long {
        val db = writableDatabase

        // Проверяем, существует ли запись с данным серийным номером
        val cursor = db.query(
            TABLE_NOTES,
            arrayOf(KEY_SERIAL),
            "$KEY_SERIAL = ?",
            arrayOf(calibrationData.serial.toString()),
            null, null, null
        )

       val values = ContentValues().apply {
            // Заполняем только те поля, которые содержат данные
            if (calibrationData.referenceTemperatureUpper != 0f) {
                put(KEY_REFERENCE_TEMPERATURE_UPPER, calibrationData.referenceTemperatureUpper)
            }
            if (calibrationData.referenceTemperatureLower != 0f) {
                put(KEY_REFERENCE_TEMPERATURE_LOWER, calibrationData.referenceTemperatureLower)
            }
            if (calibrationData.referenceHumidityUpper != 0f) {
                put(KEY_REFERENCE_HUMIDITY_UPPER, calibrationData.referenceHumidityUpper)
            }
            if (calibrationData.referenceHumidityLower != 0f) {
                put(KEY_REFERENCE_HUMIDITY_LOWER, calibrationData.referenceHumidityLower)
            }
            if (calibrationData.offsetTemperatureUpper != 0f) {
                put(KEY_OFFSET_TEMPERATURE_UPPER, calibrationData.offsetTemperatureUpper)
            }
            if (calibrationData.offsetTemperatureLower != 0f) {
                put(KEY_OFFSET_TEMPERATURE_LOWER, calibrationData.offsetTemperatureLower)
            }
            if (calibrationData.offsetHumidityUpper != 0f) {
                put(KEY_OFFSET_HUMIDITY_UPPER, calibrationData.offsetHumidityUpper)
            }
            if (calibrationData.offsetHumidityLower != 0f) {
                put(KEY_OFFSET_HUMIDITY_LOWER, calibrationData.offsetHumidityLower)
            }
            put(KEY_SENSOR_TYPE, calibrationData.typeOfSensor)
        }

        return if (cursor.moveToFirst()) {
            // Запись существует, обновляем только определенные поля
            db.update(
                TABLE_NOTES,
                values,
                "$KEY_SERIAL = ?",
                arrayOf(calibrationData.serial.toString())
            ).toLong()
        } else {
            // Записи нет, создаем новую
            values.put(KEY_SERIAL, calibrationData.serial)
            db.insert(TABLE_NOTES, null, values)
        }.also {
            cursor.close()
        }
    }*/


    fun addData(note: Note): Long {
        val db = writableDatabase


        val values = ContentValues().apply {
            put(KEY_TITLE, note.title)
            put(KEY_DESCRIPTION, note.description)
            put(KEY_TIME, note.time)
        }
        return db.insert(TABLE_NOTES, null, values)
    }


    fun clearTable(): Int {
        val db = writableDatabase
        return db.delete(TABLE_NOTES, null, null)
    }



    /*
        )*/

}