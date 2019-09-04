package com.yuniq.yuniq

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.security.MessageDigest
import javax.servlet.http.HttpSession

@Controller
class HtmlContoller {

    @Autowired
    lateinit var repository: UserRepository

    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("title", "Home")
        return "index"
    }

    fun crypto(ss: String): String {
        val sha = MessageDigest.getInstance("SHA-256")
        val hexa = sha.digest(ss.toByteArray())
        val crypto_str = hexa.fold("", {str, it -> str + "%02x".format(it)})
        return crypto_str
    }

    @GetMapping("/{formType}")
    fun htmlForm(model: Model, @PathVariable formType: String): String {
        var response : String = ""
        if(formType.equals("sign")) {
            response = "sign"
        } else if (formType.equals("login")) {
            response = "login"
        }

        model.addAttribute("title", response)

        return response
    }

    @PostMapping("/sign")
    fun postSign(model: Model,
                 @RequestParam(value = "id") userId: String,
                 @RequestParam(value = "password") password: String
    ): String {
        try {
            val cryptoPass=crypto(password)
            val user = repository.save(User(userId, cryptoPass))
            println(user.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        model.addAttribute("title", "Home")

        return "index"
    }

    @PostMapping("/login")
    fun postLogin(model: Model,
                  session: HttpSession,
                  @RequestParam(value = "id") userId: String,
                  @RequestParam(value = "password") password: String
    ): String {
        println(password)
        var pageName = ""
        println(userId)
        try {
            val cryptoPass = crypto(password)
            println(22)
            val db_user = repository.findByUserId(userId)
            println(33)

            if(db_user != null) {
                println(44)
                val db_pass = db_user.password
                if(cryptoPass.equals(db_pass))
                    session.setAttribute("userId", db_user.userId)
                model.addAttribute("title", "welcome")
                model.addAttribute("userId", userId)
                pageName = "welcome"
            } else {

                model.addAttribute("title", "login")
                pageName = "login"
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return pageName
    }
}