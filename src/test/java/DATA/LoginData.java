package DATA;

public class LoginData {
    private String username;
    private String password;

    // Constructor private عشان نجبره يستخدم الـ Builder
    private LoginData(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public static class Builder {
        private String username; // الـ Variables نفسها تفضل private عادي
        private String password;

        // الميثود دي لازم تكون public عشان التست يشوفها
        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public LoginData build() {
            return new LoginData(this);
        }
    }
}