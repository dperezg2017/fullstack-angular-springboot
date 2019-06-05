package com.bolsadeideas.spring.boot.backend.apirest.auth;

public class JwtConfig {

	public static final String LLAVE_SECRETA="alguna.clave.secreta.12345678";

	public static final String RSA_PRIVADA ="-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIIEpAIBAAKCAQEAybtxbosjtSOxudyu4yIvXy/NM2eS0c0dRwvif6YR3u3doTwP\r\n" + 
			"2yQMYx3H+Di92v/Q9QzVetiYM1C3rWdmVThbNxJ4UUAbTF/xYSkszH6YuTYVtUUl\r\n" + 
			"4ClBisDoTo3Z0m9moSLJ4Bqi2FqbAjbyPBFZZTTdGgUqGSTk8an0Tx8iQvdulIx6\r\n" + 
			"KzCeY5RZIJuhvyDfn5e1IbmKBDfM5KXoMajGfDz14y47bkbi65umbEXxdEKqn0nS\r\n" + 
			"bBmtR7msc0smdcL4YupembGqwX5o5ByRcd07jiD7bew1AmT4knECMEyYI0dJOKm9\r\n" + 
			"X5jqVc+zrqTspTIB/o5m8SAfSryDOSkS+R/N7QIDAQABAoIBAQC/33cOS20VFvQt\r\n" + 
			"8cat5o/kG1UzdWnh/xO/xYATJWOOA/RvLO9v4aOeim7umxmJORQIX8KU63ooJLfv\r\n" + 
			"SI++srGfegPSVAUi6YZugXlIr4iXcOfI2BIkWVkrnjvWq3jofGjDDpGwg+Urn1Ic\r\n" + 
			"EyLnGyFJ77P00cCu1bakXKAwuU4fHgKrztyF2azNc7phhRdcIBU+F4u4yY788A9h\r\n" + 
			"Z4PuEVAlwPw222YddlSpU5KB8IZRJuYorWY1ZMAPqxg0q6jmwhjM0b1wJDxKg2Ta\r\n" + 
			"8YnOn4T/vkFPDtbFC70kGxVuCyqxtoJTqIww4GSNodij6uPaE5zeyzO+n6cy25h9\r\n" + 
			"5T6VhJDBAoGBAPgSaxBwpPshmQ2Gg+vQO5X/aPIrhsNl+n/F135ookm0VLsX62Zr\r\n" + 
			"kqDiHHkW0rJ04JzCyzegvN4vxz6Z8FuiuKA0Dg/OMjZptF4FVtvNI7rBwQtEdfW2\r\n" + 
			"YG3iTLahjkq/dz67knq0P521VN3KMvkVKmQHZs/xz7O0mmk7u6RbBASlAoGBANAt\r\n" + 
			"5lZ/SkkyY74t9bwWq1NGtuw5/4XGtnyQxwxUhhBDh1TXNJ5A9G6XGh1vEJjbqhNh\r\n" + 
			"+qVlKYx8aeQvu1CJDfGlbtlz7GrLnys/Z/66tSKKJECR4BU+IEdvGHxPZ3iVLlPD\r\n" + 
			"bi39Q0fJ8Qf9SR8ZnyAN368SnnwvOi3LqToJsDmpAoGAfwc3adC0uqAGtqVV8i/M\r\n" + 
			"A1ApVjCxrmcO8dTGN0WvLJy58qAZ+3VEPTvrppnoRFeTGNKqqw6VCBVhKo47RUE6\r\n" + 
			"11YwiSlmDvTr1mVXh/AwxpYmmvVwjeTY1gvWioKJ0X7fufDk3g5ksQZEsdmzZlbP\r\n" + 
			"QS+FFyBU4kmt9AsRV+T/Vr0CgYEAqTdWDqMjKAJcjx7eqwemNWe6kqPGLuBKP1CT\r\n" + 
			"a1NdMeUiIPVZIPXdefM62AgKbqXHRkATM9PaBQawMDoYQStWeUCmrP0Mg/aFp+q/\r\n" + 
			"RpBPYgb11sJ8aef45vfH6GvGyH5CjPXDHoDJ6pWcZYkdobj63lxIoQ86Yeklmkn8\r\n" + 
			"gV+UrhkCgYApu7MldXRvLh58jgTOLsigCAkQriZR5ZsAVUA6sKoQenyZLeUCl8On\r\n" + 
			"/424kxdAJnB3EsIYVVxSuabHgaJUCM4gSCOj3MFG838E8KxpsCXrrTuVoflBjZm0\r\n" + 
			"yEEt54Qg/3Kk6BuGw0Jk7dhshe2F5vBcJ6fXhA4swl82RX1DvvBCpw==\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	public static final String RSA_PUBLICA ="-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAybtxbosjtSOxudyu4yIv\r\n" + 
			"Xy/NM2eS0c0dRwvif6YR3u3doTwP2yQMYx3H+Di92v/Q9QzVetiYM1C3rWdmVThb\r\n" + 
			"NxJ4UUAbTF/xYSkszH6YuTYVtUUl4ClBisDoTo3Z0m9moSLJ4Bqi2FqbAjbyPBFZ\r\n" + 
			"ZTTdGgUqGSTk8an0Tx8iQvdulIx6KzCeY5RZIJuhvyDfn5e1IbmKBDfM5KXoMajG\r\n" + 
			"fDz14y47bkbi65umbEXxdEKqn0nSbBmtR7msc0smdcL4YupembGqwX5o5ByRcd07\r\n" + 
			"jiD7bew1AmT4knECMEyYI0dJOKm9X5jqVc+zrqTspTIB/o5m8SAfSryDOSkS+R/N\r\n" + 
			"7QIDAQAB\r\n" + 
			"-----END PUBLIC KEY-----";
}
