package IA;
import javax.naming.directory.*;
import java.util.Hashtable;
import java.util.regex.Pattern;
public class EmailVerifier {
    //source: chatgpt

    // Check the syntax (pattern) of the email
    public static boolean isValidEmailFormat(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(regex, email);
    }

    // Check if the email's domain has MX records
    public static boolean domainHasMXRecord(String email) {
        try {
            String domain = email.substring(email.indexOf("@") + 1);

            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            DirContext ctx = new InitialDirContext(env);

            Attributes attrs = ctx.getAttributes(domain, new String[]{"MX"});
            Attribute attr = attrs.get("MX");

            return attr != null && attr.size() > 0;
        } catch (Exception e) {
            return false; // domain not found or DNS lookup failed
        }
    }

    // Combined method — use this for a single call
    public static boolean isEmailValid(String email) {
        return isValidEmailFormat(email) && domainHasMXRecord(email);
    }
}
