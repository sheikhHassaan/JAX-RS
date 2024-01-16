package Common;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
    public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    public static final String AUTHORIZATION_VALUE_PREFIX = "Basic ";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
        if ((authHeader != null) && (authHeader.size() > 0)) {
            String authToken = authHeader.get(0).replace(AUTHORIZATION_VALUE_PREFIX, "");

            byte[] bytes = Base64.getDecoder().decode(authToken);
            String decodedString = new String(bytes);

            StringTokenizer stringTokenizer = new StringTokenizer(decodedString, ":");

            String username = stringTokenizer.nextToken();
            String passphrase = stringTokenizer.nextToken();

            if ("user".equals(username) && "password".equals(passphrase)) {
                return;
            }
        }
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User not authorized for request.").build());
    }
}
