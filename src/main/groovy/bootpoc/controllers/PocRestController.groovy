package bootpoc.controllers
import bootpoc.entities.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcOperations
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.sql.ResultSet

@RestController
class PocRestController {

    private static final Logger log = LoggerFactory.getLogger(PocRestController.class)

    @Autowired
    private NamedParameterJdbcOperations namedParameterJdbcTemplate

    @Autowired
    private JdbcOperations jdbcTemplate

    @RequestMapping('/bootpoc')
    def helloWorld() {

        List<User> user = jdbcTemplate.query('select * from User where id = ?', { ResultSet rs ->
        } as RowMapper<User>, 1);

        return [
                bogus1: [
                        bogus2: 'bogus',
                        bogus3: new User()
                ]
        ]
    }

}