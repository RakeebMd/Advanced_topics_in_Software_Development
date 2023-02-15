public class SecurityMock implements Security {
    @Override
    public boolean IsDealerAuthorized(String dealerid, String dealeraccesskey) {
        if (dealerid.equals("XXX-1234-ABCD-1234") && dealeraccesskey.equals("kkklas8882kk23nllfjj88290")) {
            return true;
        } else {
            return false;
        }
    }
}
