package bootpoc.controllers

import bootpoc.entities.User
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PocRestController {

    @RequestMapping("/bootpoc")
    def helloWorld() {
        return [
                bogus1: [
                        bogus2: 'bogus',
                        bogus3: new User()
                ]
        ]
    }

}