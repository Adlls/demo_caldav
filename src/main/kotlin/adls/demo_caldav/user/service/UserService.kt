package adls.demo_caldav.user.service

import adls.demo_caldav.core.service.BaseService
import adls.demo_caldav.user.entity.User
import adls.demo_caldav.user.repository.UserRepository

interface UserService : BaseService<Long, User, UserRepository> {
}
