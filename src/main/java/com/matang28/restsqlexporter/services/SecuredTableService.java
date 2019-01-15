package com.matang28.restsqlexporter.services;

import com.matang28.restsqlexporter.services.core.ISecuredTableService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@Component
public class SecuredTableService implements ISecuredTableService{

    private static final String SECRET_KEY_1 = "ssdkF$HUy2A#D%kd";
    private static final String SECRET_KEY_2 = "weJiSEvR5yAC5ftB";

    private IvParameterSpec ivParameterSpec;
    private SecretKeySpec secretKeySpec;
    private Cipher cipher;

    public SecuredTableService() throws Exception{
        ivParameterSpec = new IvParameterSpec(SECRET_KEY_1.getBytes("UTF-8"));
        secretKeySpec = new SecretKeySpec(SECRET_KEY_2.getBytes("UTF-8"), "AES");
        cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }


    @Override
    public String encrypt(String object) throws IllegalArgumentException {
            try{
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
                byte[] encrypted = cipher.doFinal(object.getBytes());
                return Base64.encodeBase64String(encrypted);
            }
            catch (Exception e){
                throw new IllegalArgumentException();
            }
    }

    @Override
    public String decrypt(String object) throws IllegalArgumentException {
            try{
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
                byte[] decryptedBytes = cipher.doFinal(Base64.decodeBase64(object));
                return new String(decryptedBytes);
            }
            catch (Exception e){
                throw new IllegalArgumentException();
            }
    }
}
