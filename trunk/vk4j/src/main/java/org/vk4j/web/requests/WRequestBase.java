package org.vk4j.web.requests;

import org.apache.http.client.methods.HttpUriRequest;
import org.vk4j.api.Request;

/**
 * Created by Vladimir Grachev.
 * Date: Oct 1, 2010
 * Time: 9:49:06 AM
 */
public abstract class WRequestBase implements Request {

    private boolean primary;

    public WRequestBase() {
        this.primary = true;
    }

    public WRequestBase(boolean primary) {
        this.primary = primary;    
    }

    public boolean isPrimaryRequest() {
        return primary; 
    }
}
