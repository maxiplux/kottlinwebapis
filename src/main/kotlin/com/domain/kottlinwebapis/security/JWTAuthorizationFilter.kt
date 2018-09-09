package security

import config.SecurityConstants.HEADER_STRING
import config.SecurityConstants.SECRET
import config.SecurityConstants.TOKEN_PREFIX
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import java.io.IOException
import java.util.*
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authManager: AuthenticationManager) : BasicAuthenticationFilter(authManager), Filter {
    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun init(filterConfig: FilterConfig?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var log = LoggerFactory.getLogger(JWTAuthorizationFilter::class.java)

    @Throws(IOException::class, ServletException::class)
    fun doFilterInternal(
            req: HttpServletRequest,
            res: HttpServletResponse,
            chain: FilterChain
    ) {
        val header = req.getHeader(HEADER_STRING)
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res)
            return
        }
        val authentication = getAuthentication(req)
        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(req, res)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(HEADER_STRING)
        return if (token != null) {
            val claims = Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(SECRET.toByteArray()))
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
            val user = claims
                    .body
                    .subject

            val authorities = ArrayList<GrantedAuthority>()
            (claims.body["auth"] as MutableList<*>).forEach { role -> authorities.add(SimpleGrantedAuthority(role.toString())) }

            if (user != null) {
                UsernamePasswordAuthenticationToken(user, null, authorities)
            } else null
        } else null
    }
}
