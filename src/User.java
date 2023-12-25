public class User {
    private Account login;
    private String name;
    public User(){
        login=new Account();
    }
    public void createAccount(String s){

        login.register(s);
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }
}
