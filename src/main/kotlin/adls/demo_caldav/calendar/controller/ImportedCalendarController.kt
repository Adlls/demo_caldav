package adls.demo_caldav.calendar.controller

import adls.demo_caldav.calendar.service.ImportCalendarService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping("v1/calendar")
class ImportedCalendarController(
    private val importCalendarService: ImportCalendarService
) {

    @Operation(summary = "Импорт caldav по id пользователя")
    @PostMapping("/import/caldav/yandex/{userId}")
    fun importCaldavUrlByUserId(
        @PathVariable userId: Long,
        @RequestParam caldavUrl: String,
        @RequestParam login: String,
        @RequestParam password: String) =
        importCalendarService.importCalDavByUserId(userId, caldavUrl, login, password)
}
