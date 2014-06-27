package bootpoc.entities

import javax.persistence.Entity
import javax.persistence.Id

/**
 * User: victor
 * Date: 6/19/14
 */
@Entity
class User {

    @Id
    Long id
    String username
    String password

}
