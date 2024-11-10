package database;

public class User {
        private int userId;
        private String username;
        private String email;
        private String password;

        // Default constructor
        public User() {}

        // Constructor with parameters
        public User(int userId, String username, String email, String password) {
            this.userId = userId;
            this.username = username;
            this.email = email;
            this.password = password;
        }

        // Getters and Setters
        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        // toString() method for easy printing
        @Override
        public String toString() {
            return "User{" +
                    "userId=" + userId +
                    ", username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }

