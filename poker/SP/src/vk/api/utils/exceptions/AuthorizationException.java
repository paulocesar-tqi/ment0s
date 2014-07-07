package vk.api.utils.exceptions;

/**
 * User:   Evgeny Avsievich
 * E-mail: ray.evg@gmail.com
 * Date:   12/9/11
 * Time:   1:26 AM
 */
public class AuthorizationException extends Exception{
    @Override
    public String getMessage(){
        return "Authorization failed. Bad e-mail/password?";
    }
}
