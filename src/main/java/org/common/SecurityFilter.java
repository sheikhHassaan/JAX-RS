package org.common;

import java.util.List;
import java.util.Base64;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import java.util.StringTokenizer;
import static org.common.EnvironmentVariables.*;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerRequestContext;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) {
        List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
        if ((authHeader != null) && (!authHeader.isEmpty())) {  // !authHeader.isEmpty()authHeader.size() > 0
            String authToken = authHeader.get(0).replace(AUTHORIZATION_VALUE_PREFIX, "");

            byte[] bytes = Base64.getDecoder().decode(authToken);
            String decodedString = new String(bytes);

            StringTokenizer stringTokenizer = new StringTokenizer(decodedString, ":");

            String username = stringTokenizer.nextToken();
            String passphrase = stringTokenizer.nextToken();

            if (USERNAME.equals(username) && PASSWORD.equals(passphrase)) { //
                return;
            }
        }
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User not authorized for request.").build());
    }
}
