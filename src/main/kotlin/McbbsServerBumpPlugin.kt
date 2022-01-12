package com.dakuo

import com.dakuo.service.FriendMessageListener
import com.dakuo.service.McbbsUseMagic
import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.utils.info


object McbbsServerBumpPlugin : KotlinPlugin(
    JvmPluginDescription(
        id = "com.dakuo.McbbsServerBumpPlugin",
        name = "McbbsServerBumpPlugin",
        version = "1.0-SNAPSHOT",
    ) {
        author("大阔")
        info("""Mcbbs服务器顶帖插件""")
    }
) {
    override fun onEnable() {
        Config.reload()
        Cookies.reload()

        GlobalEventChannel.subscribeAlways<FriendMessageEvent> {event->FriendMessageListener(event).monitor() }

//        logger.info { "Plugin loaded" }
    }
}

object Config : AutoSavePluginConfig("config"){
    val admin by value<Int>(0);
}

object Cookies : AutoSavePluginData("cookies"){
    var cookies : Map<String,String> by value();
}