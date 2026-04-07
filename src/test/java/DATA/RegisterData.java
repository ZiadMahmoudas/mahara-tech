package DATA;

public class RegisterData {
    private String username;
    private String password;
    private String email;
    private String confirmEmail;
    private String firstName;
    private String lastName;
    private String city;
    private String nationalId;
    private String linkedin;
    private String countryCode;
    private String university;
    private String faculty;

    private RegisterData(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.confirmEmail = builder.confirmEmail;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.city = builder.city;
        this.nationalId = builder.nationalId;
        this.linkedin = builder.linkedin;
        this.countryCode = builder.countryCode;
        this.university = builder.university;
        this.faculty = builder.faculty;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getConfirmEmail() { return confirmEmail; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getCity() { return city; }
    public String getNationalId() { return nationalId; }
    public String getLinkedin() { return linkedin; }
    public String getCountryCode() { return countryCode; }
    public String getUniversity() { return university; }
    public String getFaculty() { return faculty; }

    public static class Builder {
        private String username;
        private String password;
        private String email;
        private String confirmEmail;
        private String firstName;
        private String lastName;
        private String city;
        private String nationalId;
        private String linkedin;
        private String countryCode;
        private String university;
        private String faculty;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder confirmEmail(String confirmEmail) {
            this.confirmEmail = confirmEmail;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder nationalId(String nationalId) {
            this.nationalId = nationalId;
            return this;
        }

        public Builder linkedin(String linkedin) {
            this.linkedin = linkedin;
            return this;
        }

        public Builder countryCode(String countryCode) {
            this.countryCode = countryCode;
            return this;
        }

        public Builder university(String university) {
            this.university = university;
            return this;
        }

        public Builder faculty(String faculty) {
            this.faculty = faculty;
            return this;
        }

        public RegisterData build() {
            return new RegisterData(this);
        }
    }
}