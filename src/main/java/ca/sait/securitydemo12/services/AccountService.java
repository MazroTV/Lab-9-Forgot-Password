package ca.sait.securitydemo12.services;

import ca.sait.securitydemo12.dataaccess.UserDB;
import ca.sait.securitydemo12.models.User;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccountService {
    
    public User login(String email, String password) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }

public void resetPassword (String email, String path, String url) {
String uuid = UUID.randomUUID().toString();
UserDB userdb = new UserDB();
String link = url + "?uuid=" + uuid;

try {
User user = userdb.get(email);
if (user != null){

 Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);

String userEmail = user.getEmail();
String subject = "Notes- App - Resetting Your Password";
String template = path + "resetpassword.html";

HashMap<String, String> tags = new HashMap<>();
tags.put("firstname", user.getFirstName());
tags.put("lastname", user.getLastName());
tags.put("password", user.getPassword());
tags.put("link", link);


GmailService.sendMail(userEmail, subject, template, tags);

user.setResetPasswordUuid(uuid);
userdb.update(user);

} else {
    throw new Exception("User not found");
}



}catch(Exception ex){
Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, "Your password was unsuccessfully reset! " + email, ex);
}


}

public boolean changePassword (String email, String password, String uuid) {
UserDB userdb = new UserDB();
try {

User user = userdb.get(email);

if (user != null && user.getResetPasswordUuid().equals(uuid)){
user.setPassword(password);
user.setResetPasswordUuid(null);
userdb.update(user);
Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful password change");
return true;

}else {
throw new Exception("User not found");
}
}catch (Exception e){
   Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, "Unuccessful password change", e);
 return false; 
}

}



}
