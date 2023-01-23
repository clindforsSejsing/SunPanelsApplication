package com.example.sunpanels.controller

import com.example.sunpanels.dtos.LoginDTO
import com.example.sunpanels.dtos.RegisterDTO
import com.example.sunpanels.entity.Person
import com.example.sunpanels.service.PersonService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/person")
class PersonController(var personService: PersonService) {

    @GetMapping("/all")
    fun getUser(): ResponseEntity<MutableIterable<Person>> {
        return ResponseEntity(personService.getPersons(), HttpStatus.OK)
    }

    @PostMapping("/register")
    fun register(@RequestBody body: RegisterDTO): Any {
        val person = Person()
        person.name = body.name
        person.email = body.email
        person.password = body.password
        return if (personService.savePerson(person) != null) {
            return ResponseEntity.ok(this.personService.savePerson(person))
        } else ResponseEntity.badRequest()

    }

    @PostMapping("/login")
    fun login(@RequestBody body: LoginDTO, response: HttpServletResponse): ResponseEntity<Any> {
        val person = personService.findByEmail(body.email)
            ?: return ResponseEntity.badRequest().body("Expected email not found")
        if (!person.comparePassword(body.password)) {
            return ResponseEntity.badRequest().body("Invalid password!")
        }
        val secretKey : String? = System.getenv("SECRET_KEY")
        val issuer = person.id.toString()
        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))//lasts 1 day then expires
            .signWith(SignatureAlgorithm.HS512, secretKey).compact()


        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        response.addCookie(cookie)
        return ResponseEntity.ok("Login ok")
    }

    @GetMapping("/user")
    fun getAUser(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        try {
            if (jwt == null) {
                return ResponseEntity.status(401).body("Unauthenticated")
            }
            val secretKey : String? = System.getenv("SECRET_KEY")
            val body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).body//göm sedan!!
            return ResponseEntity.ok(personService.getPerson(body.issuer.toLong()))
        } catch (e: Exception) {
            return ResponseEntity.status(401).body("Unauthenticated")
        }
    }

    @PostMapping("/logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0
        response.addCookie(cookie)
        return ResponseEntity.ok("Logout ok")
    }

}

