package learning.DSWII.code.business.user2.response;

import learning.DSWII.code.business.ResponseGeneral;

public class ResponseLogin extends ResponseGeneral {
    public Object data;
    public Object token;

    public void setData(Object data){
        this.data=data;
    }
    public void setToken(Object token){
        this.token=token;
    }
}
