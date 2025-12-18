public class Person{
    private final int idNumber;
    private final String name;
    private final String email;
    private final int mobileNumber;

    private Person(Builder builder){
        this.idNumber = builder.idNumber;
        this.name = builder.name;
        this.email = builder.email;
        this.mobileNumber = builder.mobileNumber;
    }

    public static class Builder{

        //Required parameters are final
        private final int idNumber;
        private final String name;

        //Optional parameters
        private String email = null;
        private int mobileNumber = 0;

        public Builder(int idNumber, String name) {
            this.idNumber = idNumber;
            this.name = name;
        }

        public Builder setEmail(String email){
            this.email = email;
            return this;
        }
        public Builder setMobileNumber(int mobileNumber){
            this.mobileNumber = mobileNumber;
            return this;
        }

        public Person build(){
            return new Person(this);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "idNumber=" + idNumber +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }
}