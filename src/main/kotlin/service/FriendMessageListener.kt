package com.dakuo.service

import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.content
import net.mamoe.mirai.message.data.toPlainText

class FriendMessageListener(private val event: FriendMessageEvent) {

    suspend fun monitor(){
        val plainText = getPlainText(event.message)
        val split = plainText.split(" ")
        if (split[0].equals("#setcookie")){
            if (split[1].isEmpty()){
                event.friend.sendMessage("格式错误! 正确格式: #setcookie cookie")
                return
            }
            CookieService.setCookies(plainText.removePrefix("#setcookie"))
            event.friend.sendMessage("设置成功")
            return
        }
        if (split[0].equals("#顶帖")){
            if (split.size == 1){
                event.friend.sendMessage("格式错误! 正确格式: #顶帖 帖子id")
                return
            }
            val useServerBump = McbbsUseMagic.useServerBump(split[1])
            if (useServerBump){
                event.friend.sendMessage("success")
            }else{
                event.friend.sendMessage("error")
            }
        }
    }

    private fun getPlainText(str: MessageChain):String{
        val content = str.content.toPlainText();
        return if (content.equals("")) "" else content.contentToString().trim()
    }

}