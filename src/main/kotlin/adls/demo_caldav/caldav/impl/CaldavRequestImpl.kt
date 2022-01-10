package adls.demo_caldav.caldav.impl

import adls.demo_caldav.auth_connector.BasicAuth
import adls.demo_caldav.auth_connector.context.BasicAuthCaldavConnector
import adls.demo_caldav.auth_token.entity.AuthServiceType
import adls.demo_caldav.auth_token.entity.AuthToken
import adls.demo_caldav.auth_token.entity.AuthType
import adls.demo_caldav.caldav.CaldavRequest
import com.github.caldav4j.CalDAVConstants
import com.github.caldav4j.methods.HttpCalDAVReportMethod
import com.github.caldav4j.model.request.CalendarData
import com.github.caldav4j.model.request.CalendarQuery
import com.github.caldav4j.model.request.CompFilter
import com.github.caldav4j.model.request.TimeRange
import com.github.caldav4j.model.response.CalendarDataProperty
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.DateTime
import net.fortuna.ical4j.model.component.VEvent
import org.apache.http.impl.client.HttpClients
import org.apache.jackrabbit.webdav.property.DavPropertyName
import org.apache.jackrabbit.webdav.property.DavPropertyNameSet
import org.springframework.stereotype.Component
import kotlin.jvm.Throws

@Component
class CaldavRequestImpl(
    private val basicAuthCaldavConnector: BasicAuthCaldavConnector
): CaldavRequest {
    override fun getItemEventsByDates(
        start: DateTime,
        end: DateTime,
        authToken: AuthToken,
        caldavUrl: String
    ): List<VEvent> = getHttpCalDavReportMethod(
        properties = DavPropertyNameSet().apply {
            add(DavPropertyName.GETETAG)
        },
        filter = CompFilter(Calendar.VCALENDAR).apply {
            addCompFilter(CompFilter(net.fortuna.ical4j.model.Component.VEVENT).apply {
                timeRange = TimeRange(
                    start,
                    end
                )
            })
        },
        caldavUrl = caldavUrl
    ).apply {
        getAuthHeaderRequest(authToken).let {
            setHeader(
                it.first,
                it.second
            )
        }
    }.let { method ->
        val httpResponse = HttpClients.createDefault().execute(method)
        val multiStatusResponses = method.getResponseBodyAsMultiStatus(httpResponse).responses
        val vEventsSubLists = multiStatusResponses.map {
            CalendarDataProperty
                .getCalendarfromResponse(it)
                .getComponents<VEvent>(net.fortuna.ical4j.model.Component.VEVENT) as List<VEvent>
        }
        val resultsVEvents = mutableListOf<VEvent>()
        vEventsSubLists.forEach {
            it.forEach { vEvent ->
                resultsVEvents.add(vEvent)
            }
        }
        return resultsVEvents
    }

    override fun getCalendarByCaldavUrl(
        caldavUrl: String,
        authToken: AuthToken
    ): Calendar =
        getHttpCalDavReportMethod(
            properties = DavPropertyNameSet().apply {
                add(DavPropertyName.GETETAG)
            },
            filter = CompFilter(Calendar.VCALENDAR),
            caldavUrl = caldavUrl
        ).apply {
            getAuthHeaderRequest(authToken).let {
                setHeader(
                    it.first,
                    it.second
                )
            }
        }.let { method ->
            val httpResponse = HttpClients.createDefault().execute(method)
            val multiStatusResponses = method.getResponseBodyAsMultiStatus(httpResponse).responses
            return CalendarDataProperty.getCalendarfromResponse(multiStatusResponses.first())
        }

    @Throws(IllegalArgumentException::class)
    private fun getAuthHeaderRequest(authToken: AuthToken): Pair<String, String> {
        require(authToken.externalServiceType == AuthServiceType.CALDAV) {
            "External service is wrong"
        }
       return when (checkNotNull(authToken.authType)) {
            AuthType.BASIC -> basicAuthCaldavConnector.processAuth(
                BasicAuth().apply {
                    this.executeByAuthProtocol(checkNotNull(authToken.token))
                },
                checkNotNull(authToken.user?.id)
            )
            // AuthType.AUTH20 -> Auth20CaldavConnector
            // AuthType.JWT -> JWTCaldavConnector
            else -> throw IllegalArgumentException("Not supported auth type protocok")
        }
    }
    private fun getHttpCalDavReportMethod(
        properties: DavPropertyNameSet,
        filter: CompFilter,
        caldavUrl: String
    ): HttpCalDAVReportMethod =
        HttpCalDAVReportMethod(
            caldavUrl,
            CalendarQuery(properties, filter, CalendarData(), true, true),
            CalDAVConstants.DEPTH_1
        )
}
