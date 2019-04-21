package utils

import beans.Package
import beans.Room
import roomMap
import java.io.IOException
import java.net.Socket
import java.net.SocketException

public class Connection(val socket:Socket) : Thread(){

    @Volatile
    private var loopFlag = true
    private var roomName = ""
    private var isClient = false
    override fun run() {
        try{
            while (loopFlag){
                val pkg = getPackage(socket)
                parsePackage(pkg)
            }
        }catch (e:IOException){
            e.printStackTrace()
        }catch (e:SocketException){
            e.printStackTrace()
        }finally {
            if (!roomName.isEmpty()){
                if (isClient)
                    roomMap[name]?.host?.exitLoop()
                else
                    roomMap[name]?.client?.exitLoop()
            }
            exitLoop()
        }
    }

    fun parsePackage(pkg: Package){
        when(pkg.type){
            Package.CREATE_ROOM->{
                isClient = false
                roomName = pkg.roomName
                val room = Room()
                room.host = this
                room.name = roomName
                roomMap[roomName] = room
                sendPackage(Package(Package.CREATE_SUC),socket)
            }
            Package.CONNECT_ROOM->{
                isClient = true
                roomName = pkg.roomName
                val room = roomMap[roomName]
                if (room != null && room.client == null){
                    val nPkg = Package(Package.CONNECT_SUC)
                    room.client = this
                    // 通知连接方 连接成功
                    sendPackage(nPkg,socket)
                    // 通知房主 游戏开始
                    sendPackage(Package(Package.GAME_START),room.host.socket)

                }else{
                    val nPkg = Package(Package.CONNECT_FAL)
                    sendPackage(nPkg,socket)
                    socket.close()
                    exitLoop()
                }
            }
            Package.LUOZI->{
                if (this == roomMap[pkg.roomName]!!.client){
                    sendPackage(pkg,roomMap[pkg.roomName]!!.host.socket)
                }else{
                    sendPackage(pkg,roomMap[pkg.roomName]!!.client.socket)
                }
            }
        }
    }

    public fun exitLoop(){
        loopFlag = false
        socket.close()
    }
}