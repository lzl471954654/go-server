package utils

import beans.Package
import com.google.gson.Gson
import org.json.JSONObject
import java.io.InputStream
import java.net.Socket
import java.nio.charset.Charset
import java.util.concurrent.LinkedBlockingQueue

private val gson = Gson()
private val sendList = LinkedBlockingQueue<Pair<Socket,Package>>()

private val sendThread = Thread{
        while (true){
            try {
                val pair = sendList.take()
                println("Thread:${Thread.currentThread().id}\tsend data is :${pair.second.toString()}")
                sendJson(pair.second,pair.first)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
}

public fun startSendThread(){
    sendThread.start()
}

public fun sendPackage(pkg: Package,socket: Socket){
    sendList.put(Pair(socket,pkg))
}

private fun readBytesArrayWithStream(size : Int,input : InputStream):ByteArray{
    var i = 0
    val bytes = ByteArray(size)
    while (i<size){
        bytes[i] = input.read().toByte()
        i++
    }
    return bytes
}

fun getPackage(socket: Socket):Package{
    val json = readJson(socket)
    println("Thread:${Thread.currentThread().id}\tGet a Package:$json")
    val pkg = gson.fromJson<Package>(json,Package::class.java)
    return pkg
}

fun readJson(socket: Socket): String {
    val input = socket.getInputStream()
    val bytes = readBytesArrayWithStream(4,input)
    val size = IntConvertUtils.getIntegerByByteArray(bytes)
    println("Thread:${Thread.currentThread().id}\tdata size is :$size")
    val dataBytes = readBytesArrayWithStream(size,input)
    val jsonText = String(dataBytes, Charset.forName("UTF-8"))
    return jsonText
}

fun sendJson(pkg : Package, socket: Socket){
    val jsonText = gson.toJson(pkg)
    val dataBytes = jsonText.toByteArray(Charset.forName("UTF-8"))
    val size = dataBytes.size
    val sizeBytes = IntConvertUtils.getIntegerBytes(size)
    socket.getOutputStream().write(sizeBytes)
    socket.getOutputStream().write(dataBytes)
}