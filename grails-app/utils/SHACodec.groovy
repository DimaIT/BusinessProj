import java.security.MessageDigest

/**
 * Created by dimait on 04.10.15.
 */
class SHACodec {
    static encode = { target ->
        MessageDigest md = MessageDigest.getInstance('SHA')
        md.update(target.getBytes('UTF-8'))
        return new String(md.digest()).encodeAsBase64()
    }
}
