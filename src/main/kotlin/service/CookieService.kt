package com.dakuo.service

import com.dakuo.Cookies

object CookieService {

    fun setCookies(cookies:String){
        Cookies.cookies = getCookies(cookies)

    }

    fun getCookies(cookies: String): Map<String, String> {
        var cookies = cookies
        val map: MutableMap<String, String> = HashMap()
        cookies = cookies.replace(" ", "")
        val split = cookies.split(";").toTypedArray()
        for (s in split) {
            val split1 = s.split("=").toTypedArray()
            map[split1[0]] = split1[1]
        }
        return map
    }
}