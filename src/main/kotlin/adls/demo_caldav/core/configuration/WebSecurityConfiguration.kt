package adls.demo_caldav.core.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class WebSecurityConfiguration(): WebSecurityConfigurerAdapter()  {
    fun createCorsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val configuration = CorsConfiguration().applyPermitDefaultValues().apply {
            addAllowedMethod("DELETE")
            addAllowedMethod("PUT")
            addAllowedMethod("PATCH")
            addAllowedMethod("POST")
            addAllowedMethod("PUT")
            addAllowedMethod("GET")
        }
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .cors().configurationSource(createCorsConfigurationSource())
    }
}
