package com.dakuo.service

import com.dakuo.Cookies
import org.jsoup.Connection
import org.jsoup.Jsoup
import java.util.regex.Pattern

object McbbsUseMagic {

    fun useServerBump(tid:String):Boolean{
        val connection = Jsoup.connect("https://www.mcbbs.net/home.php?mod=magic&mid=mcbbs_mcserver_plus:serverBump&idtype=tid&id=${tid}&infloat=yes&handlekey=a_bump&inajax=1&ajaxtarget=fwin_content_a_bump")
        connection.cookies(Cookies.cookies)
        val document = connection.get()
        val pattern = "<input type=\"hidden\" name=\"formhash\" value=\"(.*)\""

        val compile = Pattern.compile(pattern)
        val matcher = compile.matcher(document.text())
        var formHash = ""
        while (matcher.find()) {
            formHash = matcher.group(1)
        }

        val url1 = connection.url("https://www.mcbbs.net/home.php?mod=magic&action=mybox&infloat=yes&inajax=1")
        val data: MutableMap<String, String> = HashMap()
        data["formhash"] = formHash
        data["handlekey"] = "a_bump"
        data["operation"] = "use"
        data["magicid"] = "32"
        data["tid"] = tid
        data["usesubmit"] = "yes"
        data["operation"] = "use"
        data["magicid"] = "32"
        data["idtype"] = "tid"
        data["tid"] = tid
        url1.data(data)

        val post = url1.post()
        println(post.html())
        return post.html().contains("您操作的主题已提升")
    }

    fun buyServerBump():Boolean{
        val connection = Jsoup.connect("https://www.mcbbs.net/home.php?mod=magic&action=shop&operation=buy&mid=mcbbs_mcserver_plus:serverBump&infloat=yes&handlekey=magics&inajax=1&ajaxtarget=fwin_content_magics")
        connection.cookies(Cookies.cookies)
        val document = connection.get()
        val pattern = "<input type=\"hidden\" name=\"formhash\" value=\"(.*)\""

        val compile = Pattern.compile(pattern)
        val matcher = compile.matcher(document.text())
        var formHash = ""
        while (matcher.find()) {
            formHash = matcher.group(1)
        }

        val url1 = connection.url("https://www.mcbbs.net/home.php?mod=magic&action=shop&infloat=yes&inajax=1")
        val data: MutableMap<String, String> = java.util.HashMap()
        data["formhash"] = formHash
        data["handlekey"] = "magics"
        data["operation"] = "buy"
        data["mid"] = "mcbbs_mcserver_plus:serverBump"
        data["magicnum"] = "1"
        data["operatesubmit"] = "yes"
        url1.data(data)

        val post = url1.post()
        println(post.html())
        return post.html().contains("您用 80 粒金粒 购买了 1 张服务器/交易代理提升卡")
    }
}