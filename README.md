# Tribes app
Tribes web application where you can set up your own kingdom and battle with others.

# Update your environment variables

Before running the application, set up your local environment variables as per below:


| Variable name  | Variable value |
| ------------- | ------------- |
| DATASOURCE_URL  | jdbc:mysql://localhost/tribes?serverTimezone=GMT-6  |
| DATASOURCE_USERNAME | {your local mysql username} |
| DATASOURCE_PASSWORD  | {your local mysql password}  |
| HIBERNATE_DIALECT | org.hibernate.dialect.MySQL5Dialect  |
| SECRET_KEY | {your local secret key} | 