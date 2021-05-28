package com.patates.gamercontrol.ui.yardimciSiniflar

import android.content.ContentValues
import android.content.Context
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object Db {
    fun createDb(context: Context){
        try {
            val db=context.openOrCreateDatabase("Games", Context.MODE_PRIVATE,null)

            db.execSQL("CREATE TABLE IF NOT EXISTS \"Times\" (\n" +
                    "\t\"TimeID\"\tINTEGER,\n" +
                    "\t\"GameID\"\tINTEGER,\n" +
                    "\t\"StarTime\"\tTEXT,\n" +
                    "\t\"StopTime\"\tTEXT," +
                    "\tFOREIGN KEY(\"GameID\") REFERENCES \"Games\"(\"GameID\"),\n" +
                    "\tPRIMARY KEY(\"TimeID\" AUTOINCREMENT)\n" +
                    ");\n")

            db.execSQL("CREATE TABLE IF NOT EXISTS\"Games\" (" +
                    "\"GameID\"INTEGER," +
                    "\"GameName\"TEXT," +
                    "\"GameImageId\"INTEGER," +
                    "PRIMARY KEY(\"GameID\" AUTOINCREMENT)" +
                    ");")
            db.close()
        }catch (e:Exception){println(e.message)}
    }
    fun getGame(gameId:Int,context: Context):Game?{
        val db=context.openOrCreateDatabase("Games", Context.MODE_PRIVATE,null)
        val cursor=db.rawQuery("select * from Games where GameId=$gameId",null)
        val gameIdIndex=cursor.getColumnIndex("GameID")
        val gameNameIndex=cursor.getColumnIndex("GameName")
        val gameImageIdIndex=cursor.getColumnIndex("GameImageId")
        var game:Game?=null
        while (cursor.moveToNext()){
            var id=cursor.getInt(gameIdIndex)
            var name=cursor.getString(gameNameIndex)
            var imageId=cursor.getInt(gameImageIdIndex)
            game=Game(id,name,imageId)
        }
        db.close()
        return game
    }
    fun addGame(game: Game,context: Context){
        val db=context.openOrCreateDatabase("Games", Context.MODE_PRIVATE,null)
        try {
            val contentValues = ContentValues()
            contentValues.put("GameName",game.gameName)
            contentValues.put("GameImageId", game.gameImageId)
            db.insert("Games",null,contentValues)
            db.close()
        }catch (e: Exception){

        }
    }
    fun getGameList(context: Context):ArrayList<Game>{
        val games=ArrayList<Game>()
        val db=context.openOrCreateDatabase("Games", Context.MODE_PRIVATE,null)
        val cursor=db.rawQuery("select * from Games ",null)
        val gameIdIndex=cursor.getColumnIndex("GameID")
        val gameNameIndex=cursor.getColumnIndex("GameName")
        val gameImageIdIndex=cursor.getColumnIndex("GameImageId")
        while (cursor.moveToNext()){
            var id=cursor.getInt(gameIdIndex)
            var name=cursor.getString(gameNameIndex)
            var imageId=cursor.getInt(gameImageIdIndex)
            var game=Game(id,name,imageId)
            games.add(game)
        }
        db.close()
        return games
    }
    fun addStartGame(gameId: Int,context: Context){
        val db=context.openOrCreateDatabase("Games", Context.MODE_PRIVATE,null)
        try {
            val contentValues = ContentValues()
            contentValues.put("GameID",gameId)
            var date=Date()
            contentValues.put("StarTime",date.toString())
            var id=db.insert("Times",null,contentValues)
            Sp.add("timeId",id.toInt(),context)
            db.close()
        }catch (e: Exception){

        }
    }
    fun updateStopGame(context: Context){
        var timeId=Sp.get<Int>("timeId",context)
        val db=context.openOrCreateDatabase("Games", Context.MODE_PRIVATE,null)
        try {
            val sqlString="update Times set StopTime=? where TimeId=$timeId"
            val statement=db.compileStatement(sqlString)
            var date=Date()
            statement.bindString(1,date.toString())
            statement.execute()
            db.close()
        }catch (e: Exception){

        }
    }
    fun getTimeList(context: Context):ArrayList<PlayTime>{
        val times=ArrayList<PlayTime>()
        val db=context.openOrCreateDatabase("Games", Context.MODE_PRIVATE,null)
        val cursor=db.rawQuery("select * from Times ",null)
        val timeIdIndex=cursor.getColumnIndex("TimeID")
        val gameIdIndex=cursor.getColumnIndex("GameID")
        val startTimeIndex=cursor.getColumnIndex("StarTime")
        val stopTimeIdIndex=cursor.getColumnIndex("StopTime")
        while (cursor.moveToNext()){
            var timeId=cursor.getInt(timeIdIndex)
            var gameId=cursor.getInt(gameIdIndex)
            var startTime=cursor.getString(startTimeIndex)
            var stopTime=cursor.getString(stopTimeIdIndex)
            var playTime=PlayTime(timeId,gameId, stringToDate(startTime), stringToDate(stopTime))
            times.add(playTime)
        }
        db.close()
        return times
    }
    private fun stringToDate(dateString:String): Date {
        val sdf3 = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
        var d1 = sdf3.parse(dateString)
        return d1
    }
}