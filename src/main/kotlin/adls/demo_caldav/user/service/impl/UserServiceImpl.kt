package adls.demo_caldav.user.service.impl

import adls.demo_caldav.core.service.impl.BaseServiceImpl
import adls.demo_caldav.user.entity.User
import adls.demo_caldav.user.repository.UserRepository
import adls.demo_caldav.user.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(): BaseServiceImpl<Long, User, UserRepository>(), UserService {
}
