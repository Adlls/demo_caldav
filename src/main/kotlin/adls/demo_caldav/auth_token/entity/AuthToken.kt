package adls.demo_caldav.auth_token.entity

import adls.demo_caldav.core.entity.BaseEntity
import adls.demo_caldav.user.entity.User
import javax.persistence.*

@Entity
@Table(name = "auth_token")
data class AuthToken(
    @Id
    var id: Long? = null,
    @Column(name = "token")
    var token: String? = null,
    @Column(name = "auth_type")
    var authType: AuthType? = null,
    @Column(name = "source_type")
    var sourceType: SourceType? = null,
    @Column(name = "external_service_type")
    var externalServiceType: AuthServiceType? = null,
    @JoinColumn(name = "user_id")
    @OneToOne
    var user: User? = null
): BaseEntity()
